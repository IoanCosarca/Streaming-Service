package com.example.demo.Model;

/**
 * Class that is mapped with the database table with the same name.
 */
public class History {
    private Long userID;
    private Long videoID;
    private String date;
    private String time;

    public History() {
    }

    public History(Long userID, Long videoID, String date, String time)
    {
        this.userID = userID;
        this.videoID = videoID;
        this.date = date;
        this.time = time;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
