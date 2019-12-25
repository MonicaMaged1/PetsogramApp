package com.example.group.petsogramapp;

import android.util.Log;

public class User extends Document
{
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String homeAddress;

    public User(){}

    public User(String fullName, String emailAddress, String phoneNumber, String homeAddress)
    {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
    }

    public String getFullName() {return fullName;}
    public String getEmailAddress() {return emailAddress;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getHomeAddress() {return homeAddress;}
}
