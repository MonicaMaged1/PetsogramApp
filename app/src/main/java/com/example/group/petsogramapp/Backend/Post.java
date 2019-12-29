package com.example.group.petsogramapp.Backend;

import android.graphics.Bitmap;

public class Post extends Document
{
    private String petID;
    private String postPhotoID;
    private String caption;
    private String publisherName;
    private int likeCount;
    private int commentCount;

    public Post(){}
    
    public Post(String petID, String postPhotoID, String caption, String publisherName)
    {
        this.petID = petID;
        this.postPhotoID = postPhotoID;
        this.caption = caption;
        this.publisherName = publisherName;
        this.likeCount = 0;
        this.commentCount = 0;
    }

    public String getPetID() {return petID;}
    public String getPostPhotoID() {return postPhotoID;}
    public String getCaption() {return caption;}
    public String getPublisherName() {return publisherName;}
    public int getLikeCount() {return likeCount;}
    public int getCommentCount() {return commentCount;}
}
