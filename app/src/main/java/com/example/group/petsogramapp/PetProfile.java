package com.example.group.petsogramapp;


public class PetProfile extends Document
{
    private String userID;

    public PetProfile(){}

    public PetProfile(String userID)
    {
        this.userID = userID;
    }

    public String getUserID() {return userID;}
}
