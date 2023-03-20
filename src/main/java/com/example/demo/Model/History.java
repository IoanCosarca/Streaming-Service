package com.example.demo.Model;

import jakarta.persistence.*;

/**
 * Class that is mapped with the database table with the same name.
 */
public class History {
    private Long id;
    private Long userID;
    private Long videoID;

    public History() {
    }

    public History(Long id, Long userID, Long videoID)
    {
        this.id = id;
        this.userID = userID;
        this.videoID = videoID;
    }

    public History(Long userID, Long videoID)
    {
        this.userID = userID;
        this.videoID = videoID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getVideoID() {
        return videoID;
    }

    public void setVideoID(Long videoID) {
        this.videoID = videoID;
    }
}
