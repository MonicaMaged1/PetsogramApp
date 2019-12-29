package com.example.group.petsogramapp.Backend;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class Pet extends Document
{
    private String userID;
    private Bitmap profilePhoto;
    private String Name;
    private String Gender;
    private String Biography;
    private String Birthday;
    private int Age;
    private String Type;
    private String Species;
    private ArrayList<String> Photos;
    private int followerCount;
    private int postCount;

    public Pet(){}

    public Pet(String userID, Bitmap profilePhoto, String Name, String Gender, String Biography, int Age, String Type, String Species)
    {
        this.userID = userID;
        this.profilePhoto = profilePhoto;
        this.Name = Name;
        this.Gender = Gender;
        this.Biography = Biography;
        this.Age = Age;
        this.Type = Type;
        this.Species = Species;
        Photos = new ArrayList<>();
        this.followerCount = 0;
        this.postCount = 0;
    }

    public String getUserID() {return userID;}
    public Bitmap getProfilePhoto() {return profilePhoto;}
    public String getName() {return Name;}
    public String getGender() {return Gender;}
    public String getBiography() {return Biography;}
    public String getBirthday() {return Birthday;}
    public int getAge() {return Age;}
    public String getType() {return Type;}
    public String getSpecies() {return Species;}
    public ArrayList<String> getPhotos() {return Photos;}
    public int getFollowerCount() {return followerCount;}
    public int getPostCount() {return postCount;}
}
