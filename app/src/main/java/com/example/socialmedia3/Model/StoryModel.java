package com.example.socialmedia3.Model;

public class StoryModel {
    int profileImage,storyImage;
    String userName;

    public StoryModel(int profileImage, int storyImage, String userName) {
        this.profileImage = profileImage;
        this.storyImage = storyImage;
        this.userName = userName;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public int getStoryImage() {
        return storyImage;
    }

    public void setStoryImage(int storyImage) {
        this.storyImage = storyImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
