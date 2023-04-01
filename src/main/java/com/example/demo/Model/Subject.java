package com.example.demo.Model;

/**
 * Interface for the actions on the subject of the Observable design pattern (the Video class).
 */
public interface Subject {
    /**
     * Method to describe the behaviour when a client accesses a video.
     */
    void onAccess();

    /**
     * Method to describe the behaviour when a video state changes.
     */
    void onChange();

    /**
     * Method to describe the behaviour when the end hour of a video is met.
     */
    void onEndHour();
}
