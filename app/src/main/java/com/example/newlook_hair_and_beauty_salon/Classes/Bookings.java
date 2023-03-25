package com.example.newlook_hair_and_beauty_salon.Classes;

//Class To Hold Booking Variables To Be Stored In The Database

import java.util.Date;

public class Bookings {

    //Declaring Variables
    public String first_name, last_name, contact, service_type, appointment_date, confirmation_method, message;

    //Default Constructor


    public Bookings() {
    }

    //Creating A Non Default Constructor
    public Bookings(String first_name, String last_name, String contact, String service_type,String appointment_date, String confirmation_method, String message) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.contact = contact;
        this.service_type = service_type;
        this.appointment_date = appointment_date;
        this.confirmation_method = confirmation_method;
        this.message = message;

    }

    //Creating Getters And Setters For Data Retrieval
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getConfirmation_method() {
        return confirmation_method;
    }

    public void setConfirmation_method(String confirmation_method) {
        this.confirmation_method = confirmation_method;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
