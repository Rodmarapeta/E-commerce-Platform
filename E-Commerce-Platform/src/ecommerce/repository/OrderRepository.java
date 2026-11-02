package ecommerce.repository;

import ecommerce.model.Order;
import ecommerce.model.OrderItem;
import ecommerce.model.ShippingInfo;
import ecommerce.util.Util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Flat-file order storage so history survives restarts.
 * One order per line, pipe separated; items are id~name~qty~unitPrice joined by ';'.
 */
public class OrderRepository {
    private static final DateTimeFormatter DAY = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final Path file;
    private final List<Order> orders = new ArrayList<>();

    public OrderRepository(Path file) {
        this.file = file;
        load();
    }

    public List<Order> all() {
        return new ArrayList<>(orders);
    }

    public void save(Order order) {
        append(order);
        orders.add(order);
    }

    public String nextOrderId() {
        String prefix = "ORD-" + LocalDate.now().format(DAY) + "-";
        int sequence = 0;
        for (Order order : orders) {
            if (order.getOrderId().startsWith(prefix)) {
                sequence++;
            }
        }
        return prefix + String.format("%04d", sequence + 1);
    }

    private void load() {
        if (!Files.exists(file)) {
            return;
        }
        try {
            for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
                if (line.isBlank()) {
                    continue;
                }
                Order order = decode(line);
                if (order != null) {
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read orders: " + e.getMessage());
        }
    }

    private void append(Order order) {
        try {
            Path parent = file.toAbsolutePath().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(file, encode(order) + System.lineSeparator(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IllegalStateException("Could not save order to " + file + ": " + e.getMessage(), e);
        }
    }

    private static String encode(Order order) {
        StringBuilder items = new StringBuilder();
        for (OrderItem item : order.getProductList()) {
            if (items.length() > 0) {
                items.append(';');
            }
            items.append(clean(item.getProductId())).append('~')
                    .append(clean(item.getProductName())).append('~')
                    .append(item.getQuantity()).append('~')
                    .append(Util.plain(item.getUnitPrice()));
        }
        ShippingInfo ship = order.getShipping();
        return String.join("|",
                clean(order.getOrderId()),
                clean(order.getUserId()),
                Util.iso(order.getPlacedAt()),
                clean(order.getStatus()),
                Util.plain(order.getSubtotal()),
                Util.plain(order.getVat()),
                Util.plain(order.getShippingFee()),
                Util.plain(order.getTotalPrice()),
                clean(order.getPaymentMethod()),
                clean(order.getPaymentReference()),
                clean(ship.fullName()),
                clean(ship.email()),
                clean(ship.phone()),
                clean(ship.address()),
                clean(ship.city()),
                clean(ship.postalCode()),
                items.toString());
    }

    private static Order decode(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length < 17) {
            return null;
        }
        try {
            List<OrderItem> items = new ArrayList<>();
            if (!parts[16].isEmpty()) {
                for (String raw : parts[16].split(";")) {
                    String[] fields = raw.split("~", -1);
                    if (fields.length < 4) {
                        continue;
                    }
                    items.add(new OrderItem(fields[0], fields[1], Double.parseDouble(fields[3]),
                            Integer.parseInt(fields[2])));
                }
            }
            ShippingInfo shipping = new ShippingInfo(parts[10], parts[11], parts[12], parts[13], parts[14], parts[15]);
            LocalDateTime placedAt = Util.parseIso(parts[2]);
            return new Order(parts[0], parts[1], items,
                    Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]),
                    Double.parseDouble(parts[7]), parts[3], parts[8], parts[9], shipping, placedAt);
        } catch (RuntimeException e) {
            System.err.println("Skipping malformed order line: " + e.getMessage());
            return null;
        }
    }

    private static String clean(String value) {
        if (value == null) {
            return "";
        }
        return value.replace('|', '/').replace('~', '/').replace(';', '/').trim();
    }
}
