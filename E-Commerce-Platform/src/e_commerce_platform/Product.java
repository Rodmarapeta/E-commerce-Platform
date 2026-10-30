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
    public int quantity;

    public Product(int productId, String productName, double productPrice, String productCategory, int productQuantity) {
        id = productId;
        name = productName;
        price = productPrice;
        category = productCategory;
        quantity = productQuantity;

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
    
    public int getQuantity() {
        return quantity;
    }
    
}
