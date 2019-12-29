package com.example.group.petsogramapp.Backend;

import java.util.ArrayList;

public class Pet extends Document
{
    private String userID;
    private String Name;
    private String Gender;
    private String Biography;
    private String Age;
    private String Type;
    private String Species;
    private ArrayList<String> Photos;
    private int followerCount;
    private int followingCount;
    private int postCount;

    public Pet(){}

    public Pet(String userID, String Name, String Gender, String Biography, String Age, String Type, String Species)
    {
        this.userID = userID;
        this.Name = Name;
        this.Gender = Gender;
        this.Biography = Biography;
        this.Age = Age;
        this.Type = Type;
        this.Species = Species;
        Photos = new ArrayList<>();
        this.followerCount = 0;
        this.followingCount = 0;
        this.postCount = 0;
    }

    public void addPhoto(String photoID){Photos.add(photoID);}
    public String getUserID() {return userID;}
    public String getName() {return Name;}
    public String getGender() {return Gender;}
    public String getBiography() {return Biography;}
    public String getAge() {return Age;}
    public String getType() {return Type;}
    public String getSpecies() {return Species;}
    public ArrayList<String> getPhotos() {return Photos;}
    public int getFollowerCount() {return followerCount;}
    public int getFollowingCount(){return followingCount;}
    public int getPostCount() {return postCount;}
}
