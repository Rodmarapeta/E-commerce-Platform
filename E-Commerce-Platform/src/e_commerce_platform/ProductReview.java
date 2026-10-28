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
public class ProductReview {

    private int productId;
    private String reviewer;
    private int stars;
    private String comment;

    private static final List<ProductReview> reviews = new ArrayList<>();

    public ProductReview(int productId, String reviewer,
                         int stars, String comment) {
        this.productId = productId;
        this.reviewer = reviewer;
        this.stars = stars;
        this.comment = comment;
    }

    public int getProductId() {
        return productId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

public static void addReview(ProductReview review) {
    if (review != null && review.getStars() >= 1 && review.getStars() <= 5) {
        reviews.add(review);
    }
  }
}