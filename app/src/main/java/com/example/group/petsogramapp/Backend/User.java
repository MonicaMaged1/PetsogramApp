package com.example.group.petsogramapp.Backend;

import java.util.ArrayList;
public class User extends Document
{
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String homeAddress;
    private ArrayList<String> Pets;

    public User(){}

    public User(String fullName, String emailAddress, String phoneNumber, String homeAddress)
    {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.Pets = new ArrayList<>();
    }

    public void addPhoto(String petID){Pets.add(petID);}
    public String getFullName() {return fullName;}
    public String getEmailAddress() {return emailAddress;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getHomeAddress() {return homeAddress;}
    public ArrayList<String> getPets(){return Pets;}
}
