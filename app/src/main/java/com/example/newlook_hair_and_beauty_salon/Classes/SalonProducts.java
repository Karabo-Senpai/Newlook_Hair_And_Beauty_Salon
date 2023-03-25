package com.example.newlook_hair_and_beauty_salon.Classes;

import java.io.Serializable;

public class SalonProducts implements Serializable {

//Declaring Variables
String productName , productDescription , productPrice ,productAvailability, image_url;

    public SalonProducts() {
    }

    public SalonProducts(String productName, String productDescription, String productPrice, String productAvailability,String image_url) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productAvailability = productAvailability;
        this.image_url = image_url;
    }

    //Creating Getters And Setters
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
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
