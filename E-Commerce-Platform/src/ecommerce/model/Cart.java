package ecommerce.model;

import ecommerce.util.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    public static class Line {
        private final Product product;
        private int quantity;

        Line(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double lineTotal() {
            return Util.round(product.getPrice() * quantity);
        }
    }

    private final Map<String, Line> lines = new LinkedHashMap<>();

    public void add(Product product) {
        add(product, 1);
    }

    public void add(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            return;
        }
        Line line = lines.get(product.getId());
        if (line == null) {
            lines.put(product.getId(), new Line(product, quantity));
        } else {
            line.setQuantity(line.getQuantity() + quantity);
        }
    }

    public void remove(String productId) {
        lines.remove(productId);
    }

    public void clear() {
        lines.clear();
    }

    public boolean isEmpty() {
        return lines.isEmpty();
    }

    public int distinctCount() {
        return lines.size();
    }

    public int totalUnits() {
        int total = 0;
        for (Line line : lines.values()) {
            total += line.getQuantity();
        }
        return total;
    }

    public double subtotal() {
        double total = 0;
        for (Line line : lines.values()) {
            total += line.lineTotal();
        }
        return Util.round(total);
    }

    public List<Line> getLines() {
        return new ArrayList<>(lines.values());
    }
}
