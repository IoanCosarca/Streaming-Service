package com.example.demo.Model;

/**
 * Interface for the actions on the observer of the Observable design pattern (the Client class).
 */
public interface Observer {
    /**
     * Method to describe the behaviour when a certain video changes its status.
     * @param name name of the video
     * @param status status of the video
     */
    void update(String name, VideoStatus status);

    /**
     * Method to describe the behaviour when a client tries to watch a video.
     * @param video the video to be assigned
     */
    void watch(Video video);

    /**
     * Method to describe the behaviour when a client ends or stops watching a video.
     * @param video video to be removed
     */
    void stopWatching(Video video);
}
