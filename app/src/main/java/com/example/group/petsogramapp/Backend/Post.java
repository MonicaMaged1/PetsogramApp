package com.example.group.petsogramapp.Backend;

import android.graphics.Bitmap;

public class Post extends Document
{
    private String petID;
    private Bitmap postPhoto;
    private String Caption;
    private String publisherName;
    private int likeCount;
    private int commentCount;

    public Post(){}

    public Post(String petID, Bitmap postPhoto, String Caption, String publisherName)
    {
        this.petID = petID;
        this.postPhoto = postPhoto;
        this.Caption = Caption;
        this.publisherName = publisherName;
        this.likeCount = 0;
        this.commentCount = 0;
    }

    public String getPetID() {return petID;}
    public Bitmap getPostPhoto() {return postPhoto;}
    public String getCaption() {return Caption;}
    public String getPublisherName() {return publisherName;}
    public int getLikeCount() {return likeCount;}
    public int getCommentCount() {return commentCount;}
}
