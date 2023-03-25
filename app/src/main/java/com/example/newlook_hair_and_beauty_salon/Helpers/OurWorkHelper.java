package com.example.newlook_hair_and_beauty_salon.Helpers;

public class OurWorkHelper {

    int image;
    String work_name, price;

    public OurWorkHelper(int image, String work_name, String price) {
        this.image = image;
        this.work_name = work_name;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
