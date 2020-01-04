package com.example.group.petsogramapp.ui.notifications;

public class Notification {
    private String userId;
    private String Text;
    private String postId;
    private boolean isPost;

    public Notification(String userId, String text, String postId, boolean isPost) {
        this.userId = userId;
        Text = text;
        this.postId = postId;
        this.isPost = isPost;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public boolean isPost() {
        return isPost;
    }

    public void setPost(boolean post) {
        isPost = post;
    }
}
