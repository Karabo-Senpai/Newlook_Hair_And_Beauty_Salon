package com.example.newlook_hair_and_beauty_salon.Classes;

public class SalonServices {

    String serviceType , serviceDescription , servicePrice , image_url;

    public SalonServices() {
    }

    public SalonServices(String serviceType, String serviceDescription, String servicePrice, String image_url) {
        this.serviceType = serviceType;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
        this.image_url = image_url;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceName) {
        this.serviceType = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
