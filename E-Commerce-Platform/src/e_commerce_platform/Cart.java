/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_commerce_platform;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodmar
 */

public class Cart {

    private List<Product> cartProducts = new ArrayList<>();

    public void addProduct(Product product){
        cartProducts.add(product);
    }

    public List<Product> getCartProducts(){
        return cartProducts;
    }

    public void removeProduct(Product product){
        cartProducts.remove(product);
    }

    public double getTotalPrice(){
        double total = 0;

        for(Product product: cartProducts){
            total += product.getPrice();
        }
        return total;
    }

    public void clearCart(){
        cartProducts.clear();
    }
}