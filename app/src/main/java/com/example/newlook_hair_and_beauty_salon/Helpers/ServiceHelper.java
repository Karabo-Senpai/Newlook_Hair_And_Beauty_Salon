package com.example.newlook_hair_and_beauty_salon.Helpers;

public class ServiceHelper {

    //Declaring Variables
    int image;
    String service_name, service_1, service_2;

    //Non Default Constructor


    public ServiceHelper(int image, String service_name, String service_1, String service_2) {
        this.image = image;
        this.service_name = service_name;
        this.service_1 = service_1;
        this.service_2 = service_2;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_1() {
        return service_1;
    }

    public void setService_1(String service_1) {
        this.service_1 = service_1;
    }

    public String getService_2() {
        return service_2;
    }

    public void setService_2(String service_2) {
        this.service_2 = service_2;
    }
}
