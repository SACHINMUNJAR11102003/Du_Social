package com.example.socialmedia3.Model;

public class NotificationModel {

    int profileImage;
    String datetime,notification;

    public NotificationModel(int profileImage, String datetime, String notification) {
        this.profileImage = profileImage;
        this.datetime = datetime;
        this.notification = notification;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getdatetime() {
        return datetime;
    }

    public void setdatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
