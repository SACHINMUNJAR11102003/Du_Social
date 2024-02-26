package com.example.socialmedia3.Model;

public class SendRequestModel {


    String profilePhoto,name,profession,institution,userID,date_time;
    private int connection_counter=0;

    public SendRequestModel() {
    }

    public SendRequestModel(String profilePhoto, String name, String profession, String institution,String date_time) {
        this.profilePhoto = profilePhoto;
        this.name = name;
        this.date_time=date_time;
        this.profession = profession;
        this.institution = institution;
    }

    public int getConnection_counter() {
        return connection_counter;
    }

    public void setConnection_counter(int connection_counter) {
        this.connection_counter = connection_counter;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public void setUsername(String username) {
        this.name = username;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
}
