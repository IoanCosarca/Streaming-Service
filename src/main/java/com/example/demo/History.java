package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class History {
    private String userID;
    private String videoID;

    public History() {
    }

    public History(String userID, String videoID)
    {
        this.userID = userID;
        this.videoID = videoID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }
}
