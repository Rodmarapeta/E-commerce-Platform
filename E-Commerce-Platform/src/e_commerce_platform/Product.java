package e_commerce_platform;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Rodmar
 */
public class Product {
    public int id;
    public String name;
    public double price;
    public String category;

    public Product(int productId, String productName, double productPrice, String productCategory) {
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
