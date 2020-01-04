package com.example.group.petsogramapp.Backend;

import java.util.ArrayList;

public class Pet extends Document
{
    private String userID;
    private String name;
    private String gender;
    private String biography;
    private String age;
    private String type;
    private String species;
    private ArrayList<String> photos;
    private int followerCount;
    private int followingCount;
    private int postCount;

    public Pet(){}

    public Pet(String userID, String Name, String Gender, String Biography, String Age, String Type, String Species)
    {
        this.userID = userID;
        this.name = Name;
        this.gender = Gender;
        this.biography = Biography;
        this.age = Age;
        this.type = Type;
        this.species = Species;
        this.photos = new ArrayList<>();
        this.followerCount = 0;
        this.followingCount = 0;
        this.postCount = 0;
    }

    public void addPhoto(String photoID){photos.add(photoID);}
    public String getUserID() {return userID;}
    public String getName() {return name;}
    public String getGender() {return gender;}
    public String getBiography() {return biography;}
    public String getAge() {return age;}
    public String getType() {return type;}
    public String getSpecies() {return species;}
    public ArrayList<String> getPhotos() {return photos;}
    public int getFollowerCount() {return followerCount;}
    public int getFollowingCount(){return followingCount;}
    public int getPostCount() {return postCount;}
}
