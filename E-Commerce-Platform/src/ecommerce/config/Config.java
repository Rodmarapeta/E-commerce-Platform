package ecommerce.config;

/**
 * Store-wide settings. Change CURRENCY here if your group uses pesos.
 */
public final class Config {
    public static final String APP_NAME = "ShopSphere";
    public static final String CURRENCY = "₱";
    public static final double VAT_RATE = 0.0;
    public static final double SHIPPING_FEE = 50.00;
    public static final double FREE_SHIPPING_THRESHOLD = 500.00;
    public static final String ORDERS_FILE = "data/orders.txt";

    private Config() {
    }
}
