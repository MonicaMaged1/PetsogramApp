package com.example.group.petsogramapp.ui.home;

public class Comment {
    private String Comment;
    private String Publisher;

    public Comment(String comment, String publisher) {
        Comment = comment;
        Publisher = publisher;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }
}
