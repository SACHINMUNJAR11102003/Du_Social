package com.example.socialmedia3.Model;

public class ConnectionsModel {
    String name,profilePhoto;

    public ConnectionsModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public ConnectionsModel(String name, String profilePhoto) {
        this.name = name;
        this.profilePhoto = profilePhoto;
    }


    @Override
    public String toString() {
        // Return the string representation of the item for the dropdown
        return getName();
    }




}
