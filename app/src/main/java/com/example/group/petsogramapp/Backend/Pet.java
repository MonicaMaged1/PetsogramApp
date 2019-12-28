package com.example.group.petsogramapp.Backend;


public class Pet extends Document
{
    private String userID;

    public Pet(){}

    public Pet(String userID)
    {
        this.userID = userID;
    }

    public String getUserID() {return userID;}
}
