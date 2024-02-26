package com.example.socialmedia3.Model;

public class PostsModel {

    String name,about,date,postingImage,profilePhoto,userID;
    int likes,comments;

    public PostsModel(String name, String about, String date, String postingImage, String profilePhoto, String userID) {
        this.name = name;
        this.about = about;
        this.date = date;
        this.postingImage = postingImage;
        this.profilePhoto = profilePhoto;
        this.userID = userID;
    }

    public PostsModel() {
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPostingImage() {
        return postingImage;
    }

    public void setPostingImage(String postingImage) {
        this.postingImage = postingImage;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public PostsModel(String username, String postImage, String about, String profilePhoto, String date) {
        this.postingImage=postImage;
        this.name=username;
        this.profilePhoto=profilePhoto;
        this.about = about;
        this.date = date;
    }






}
