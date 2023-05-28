package com.example.demo.Model;

/**
 * Class that is mapped with the database table with the same name.
 */
public class Client extends User implements Observer {
    private int age;
    private String country;

    /**
     * Field telling which video is the client currently watching.
     */
    private Video videoWatching;

    public Client() {
        super();
    }

    public Client(Long userID, String firstName, String lastName, String email, String password, int age, String country)
    {
        super(userID, UserType.CLIENT, firstName, lastName, email, password);
        this.age = age;
        this.country = country;
        this.videoWatching = new Video();
    }

    public Client(String firstName, String lastName, String email, String password, int age, String country)
    {
        super(UserType.CLIENT, firstName, lastName, email, password);
        this.age = age;
        this.country = country;
        this.videoWatching = new Video();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Video getVideoWatching() {
        return videoWatching;
    }

    public void setVideoWatching(Video videoWatching) {
        this.videoWatching = videoWatching;
    }

    /**
     * Notifies the client about a video's status.
     * @param name name of the video
     * @param status status of the video
     */
    @Override
    public void update(String name, VideoStatus status)
    {
        if (!this.videoWatching.getName().equals(name)) {
            System.out.println(this.getFirstName() + ", Video " + name + " is now " + status.toString());
        }
    }

    /**
     * Tries to assign a video to the client.
     * @param video video that the client will try to watch
     */
    @Override
    public void watch(Video video)
    {
        if (video.getStatus() != VideoStatus.BUSY && video.getStatus() != VideoStatus.UNAVAILABLE)
        {
            this.videoWatching = video;
            video.onAccess();
        }
        else {
            denied(video.getName());
        }
    }

    /**
     * Rejects a video from being assigned to the client.
     * @param videoName name of the video
     */
    public void denied(String videoName) {
        System.out.println("Sorry " + getFirstName() + ", you can't watch the video " + videoName);
    }

    /**
     * Client ends or stops watching a video, making it free for others.
     * @param video the video that the client is not watching anymore
     */
    @Override
    public void stopWatching(Video video)
    {
        this.videoWatching = new Video();
        video.onChange();
    }
}
