package e_commerce_platform;

import ecommerce.model.ShippingInfo;
import ecommerce.repository.OrderRepository;
import ecommerce.service.CheckoutService;
import ecommerce.service.PaymentService;
import ecommerce.ui.CheckoutDialog;

import java.awt.Window;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

/** Connects the team's screens to the checkout module. */
public final class CheckoutLauncher {

    private static CheckoutService checkout;

    private CheckoutLauncher() {
    }

    private static CheckoutService checkout() {
        if (checkout == null) {
            checkout = new CheckoutService(
                    new OrderRepository(Path.of("data", "orders.txt")),
                    new PaymentService());
        }
        return checkout;
    }

    /** Opens checkout at the shipping step; returns the order, or null if cancelled. */
    public static ecommerce.model.Order openCheckout(Window owner, Cart cart) {
        ecommerce.model.User user = toUser();
        ecommerce.model.Cart converted = toCheckoutCart(cart);
        ShippingInfo prefilled = new ShippingInfo(user.getName(), user.getEmail(), "", "", "", "");
        CheckoutDialog dialog = new CheckoutDialog(owner, user, converted, checkout(), 1, prefilled);
        dialog.setVisible(true);
        return dialog.getPlacedOrder();
    }

    static ecommerce.model.Cart toCheckoutCart(Cart cart) {
        ecommerce.model.Cart converted = new ecommerce.model.Cart();
        Map<String, Product> products = new LinkedHashMap<>();
        Map<String, Integer> quantities = new LinkedHashMap<>();
        for (Product product : cart.getCartProducts()) {
            String key = String.valueOf(product.getId());
            products.putIfAbsent(key, product);
            quantities.merge(key, 1, Integer::sum);
        }
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            converted.add(toCheckoutProduct(entry.getValue()), quantities.get(entry.getKey()));
        }
        return converted;
    }

    private static ecommerce.model.Product toCheckoutProduct(Product product) {
        return new ecommerce.model.Product("P-" + product.getId(), product.getName(),
                product.getPrice(), product.getCategory(), 0.0);
    }

    private static ecommerce.model.User toUser() {
        String email = UserSession.getEmail();
        if (email == null || email.isBlank()) {
            email = "guest@mail.com";
        }
        String name = UserSession.getUsername();
        if (name == null || name.isBlank()) {
            name = email.contains("@") ? email.substring(0, email.indexOf('@')) : email;
        }
        int id = UserSession.getUserId();
        String userId = id > 0 ? "U-" + id : "U-" + Math.abs(email.hashCode());
        return new ecommerce.model.User(userId, name, email);
    }
}
