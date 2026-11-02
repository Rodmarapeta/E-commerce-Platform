package ecommerce.ui;

import ecommerce.model.Order;
import ecommerce.model.OrderItem;
import ecommerce.model.ShippingInfo;
import ecommerce.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReceiptDialog extends JDialog {
    private static final int WIDTH = 46;

    public ReceiptDialog(Window owner, Order order) {
        super(owner, "Receipt \u00B7 " + order.getOrderId(), ModalityType.APPLICATION_MODAL);
        setSize(480, 620);
        setMinimumSize(new Dimension(420, 520));
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        getContentPane().setBackground(UiTheme.BG);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UiTheme.SURFACE);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, UiTheme.BORDER),
                new EmptyBorder(16, 20, 16, 20)));
        JPanel titles = new JPanel();
        titles.setOpaque(false);
        titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));
        titles.add(UiTheme.heading("Receipt"));
        titles.add(UiTheme.muted(order.getOrderId()));
        header.add(titles, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JTextArea receipt = new JTextArea(buildReceipt(order));
        receipt.setEditable(false);
        receipt.setFont(UiTheme.MONO);
        receipt.setForeground(UiTheme.TEXT);
        receipt.setBackground(UiTheme.SURFACE);
        receipt.setBorder(new EmptyBorder(16, 18, 16, 18));
        JScrollPane scroll = new JScrollPane(receipt);
        scroll.setBorder(new EmptyBorder(14, 14, 14, 14));
        scroll.getViewport().setBackground(UiTheme.SURFACE);
        add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        footer.setBackground(UiTheme.BG);
        JButton close = UiTheme.primary("Close");
        close.addActionListener(e -> dispose());
        footer.add(close);
        add(footer, BorderLayout.SOUTH);
    }

    private static String buildReceipt(Order order) {
        ShippingInfo ship = order.getShipping();
        StringBuilder text = new StringBuilder();
        text.append(center("ShopSphere")).append('\n');
        text.append(center("ORDER RECEIPT")).append('\n');
        text.append(dashes()).append('\n');
        text.append(String.format("%-11s%s%n", "Order", order.getOrderId()));
        text.append(String.format("%-11s%s%n", "Date", Util.dateTime(order.getPlacedAt())));
        text.append(String.format("%-11s%s%n", "Customer", ship.fullName()));
        text.append(String.format("%-11s%s%n", "Status", order.getStatus()));
        text.append(dashes()).append('\n');
        for (OrderItem item : order.getProductList()) {
            text.append(trim(item.getProductName(), WIDTH)).append('\n');
            text.append(row("  " + item.getQuantity() + " x " + Util.money(item.getUnitPrice()),
                    Util.money(item.lineTotal()))).append('\n');
        }
        text.append(dashes()).append('\n');
        text.append(row("Subtotal", Util.money(order.getSubtotal()))).append('\n');
        text.append(row("VAT (12%)", Util.money(order.getVat()))).append('\n');
        text.append(row("Shipping", order.getShippingFee() == 0 ? "Free" : Util.money(order.getShippingFee()))).append('\n');
        text.append(row("TOTAL", Util.money(order.getTotalPrice()))).append('\n');
        text.append(dashes()).append('\n');
        text.append("Paid via ").append(order.getPaymentMethod()).append('\n');
        text.append("Ref: ").append(order.getPaymentReference()).append('\n');
        text.append('\n');
        text.append("Ship to\n");
        text.append("  ").append(ship.fullName()).append('\n');
        if (!Util.isBlank(ship.address())) {
            text.append("  ").append(ship.address()).append('\n');
        }
        String cityLine = (ship.city() + " " + ship.postalCode()).trim();
        if (!cityLine.isEmpty()) {
            text.append("  ").append(cityLine).append('\n');
        }
        if (!Util.isBlank(ship.phone())) {
            text.append("  ").append(ship.phone()).append('\n');
        }
        text.append('\n');
        text.append(center("Thank you for shopping with ShopSphere")).append('\n');
        return text.toString();
    }

    private static String center(String text) {
        int padding = Math.max(0, (WIDTH - text.length()) / 2);
        return " ".repeat(padding) + text;
    }

    private static String row(String left, String right) {
        int padding = Math.max(1, WIDTH - left.length() - right.length());
        return left + " ".repeat(padding) + right;
    }

    private static String dashes() {
        return "-".repeat(WIDTH);
    }

    private static String trim(String text, int max) {
        return text.length() <= max ? text : text.substring(0, max - 2) + "..";
    }
}
