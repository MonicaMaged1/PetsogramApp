package com.example.group.petsogramapp.Backend;

import java.util.ArrayList;
public class User extends Document
{
    private String profilePhotoID;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String homeAddress;
    private ArrayList<String> pets;

    public User(){}

    public User(String profilePhotoID, String fullName, String emailAddress, String phoneNumber, String homeAddress)
    {
        this.profilePhotoID = profilePhotoID;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.pets = new ArrayList<>();
    }

    public void addPhoto(String petID){pets.add(petID);}
    public String getProfilePhotoID(){return profilePhotoID;}
    public String getFullName() {return fullName;}
    public String getEmailAddress() {return emailAddress;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getHomeAddress() {return homeAddress;}
    public ArrayList<String> getPets(){return pets;}
}
