package com.example.newlook_hair_and_beauty_salon.Helpers;

public class ProductsHelper {


    String image_url, productName, productDescription, productAvailability,productPrice,product_type;

    public ProductsHelper() {
    }

    public ProductsHelper(String image_url, String productName, String productDescription, String productAvailability, String productPrice, String product_type) {
        this.image_url = image_url;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productAvailability = productAvailability;
        this.productPrice = productPrice;
        this.product_type = product_type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
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

    public String getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(String productAvailability) {
        this.productAvailability = productAvailability;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }
}
