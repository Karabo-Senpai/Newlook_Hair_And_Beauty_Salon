package com.example.newlook_hair_and_beauty_salon.Helpers;

import java.io.Serializable;

public class ProductDetailsHelper implements Serializable {


    String productName, productDescription, productAvailability, image_url;

    int productPrice;

    public ProductDetailsHelper() {
    }

    public ProductDetailsHelper(String productName, String productDescription, int productPrice, String productAvailability, String image_url) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productAvailability = productAvailability;
        this.image_url = image_url;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(String productAvailability) {
        this.productAvailability = productAvailability;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
