package e_commerce_platform;

public class Product {
    private int id;
    private String name;
    private double price;
    private String category;

    public Product(int productId, String productName,
                   double productPrice, String productCategory) {
        id = productId;
        name = productName;
        price = productPrice;
        category = productCategory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
