package com.example.newlook_hair_and_beauty_salon.Classes;

public class LoginUser {

    //Declaring Variables To Hold Inputted Values For Saving Into The  Real Time Database
    String firstName, lastName;
    String emailAddress;

    //Non Default Constructor
    public LoginUser(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;


    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String toString()
    {
        return "Full Name " + firstName + " " + lastName;
    }

}
