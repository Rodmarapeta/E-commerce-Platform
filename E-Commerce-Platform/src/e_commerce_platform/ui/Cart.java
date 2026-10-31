
package e_commerce_platform.ui;

import e_commerce_platform.Product;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Product> cartProducts = new ArrayList<>();

    public void addProduct(Product product) {
        if (product != null) {
            cartProducts.add(product);
        }
    }

    public void removeProduct(Product product) {
        cartProducts.remove(product);
    }

    public List<Product> getCartProducts() {
        return new ArrayList<>(cartProducts);
    }

    public double getTotalPrice() {
        double total = 0.0;

        for (Product product : cartProducts) {
            total += product.getPrice();
        }

        return total;
    }

    public boolean isEmpty() {
        return cartProducts.isEmpty();
    }

        public void clearCart() {
        cartProducts.clear();
    }
}