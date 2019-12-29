package com.example.group.petsogramapp.Backend;

import java.util.ArrayList;

public class Pet extends Document
{
    private String profilePhotoID;
    private String userID;
    private String name;
    private String gender;
    private String biography;
    private String age;
    private String type;
    private String species;
    private int followerCount;
    private int followingCount;
    private int postCount;
    private ArrayList<String> photos;

    public Pet(){}

    public Pet(String profilePhotoID, String userID, String name, String gender, String biography, String age, String type, String species)
    {
        this.profilePhotoID = profilePhotoID;
        this.userID = userID;
        this.name = name;
        this.gender = gender;
        this.biography = biography;
        this.age = age;
        this.type = type;
        this.species = species;
        this.followerCount = 0;
        this.followingCount = 0;
        this.postCount = 0;
        this.photos = new ArrayList<>();
    }

    public void addPhoto(String photoID){photos.add(photoID);}
    public String getProfilePhotoID(){return profilePhotoID;}
    public String getUserID() {return userID;}
    public String getName() {return name;}
    public String getGender() {return gender;}
    public String getBiography() {return biography;}
    public String getAge() {return age;}
    public String getType() {return type;}
    public String getSpecies() {return species;}
    public int getFollowerCount() {return followerCount;}
    public int getFollowingCount(){return followingCount;}
    public int getPostCount() {return postCount;}
    public ArrayList<String> getPhotos() {return photos;}
}
