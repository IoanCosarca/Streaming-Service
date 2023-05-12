package com.example.demo.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class that is mapped with the database table with the same name.
 */
public class Video implements Subject {
    private Long id;
    private String name;
    private String channel;
    private VideoGenre genre;
    private boolean ageRestriction;
    private String link;
    private int startHour;
    private int endHour;
    private VideoStatus status;

    private List<Client> viewers = new ArrayList<>();

    public Video() {
        this.name = "";
    }

    public Video(Long id, String name, String channel, VideoGenre genre, boolean ageRestriction, String link, int startHour, int endHour)
    {
        this.id = id;
        this.name = name;
        this.channel = channel;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.link = link;
        this.startHour = startHour;
        this.endHour = endHour;
        this.status = initializeStatus();
    }

    public Video(String name, String channel, VideoGenre genre, boolean ageRestriction, String link, int startHour, int endHour)
    {
        this.name = name;
        this.channel = channel;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.link = link;
        this.startHour = startHour;
        this.endHour = endHour;
        this.status = initializeStatus();
    }

    private VideoStatus initializeStatus()
    {
        Calendar h = Calendar.getInstance();
        if (h.get(Calendar.HOUR_OF_DAY) >= this.startHour && h.get(Calendar.HOUR_OF_DAY) <= this.endHour) {
            return VideoStatus.AVAILABLE;
        }
        return VideoStatus.UNAVAILABLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public VideoGenre getGenre() {
        return genre;
    }

    public void setGenre(VideoGenre genre) {
        this.genre = genre;
    }

    public boolean isAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(boolean ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public VideoStatus getStatus() {
        return status;
    }

    public void setStatus(VideoStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", channel='" + channel + '\'' +
                ", genre='" + genre + '\'' +
                ", ageRestriction=" + ageRestriction +
                ", link='" + link + '\'' +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * Adds a client observer to the viewers list
     * @param client the observer to be added and notified
     */
    public void addClient(Client client) {
        viewers.add(client);
    }

    /**
     * Adds a list of client observers to the viewers list
     * @param clients all the current observers
     */
    public void addClients(List<Client> clients) {
        viewers.addAll(clients);
    }

    /**
     * Removes a client observer from the viewers list
     * @param client observer to be removed
     */
    public void removeClient(Client client) {
        viewers.remove(client);
    }

    /**
     * Once a certain change happened to the video, notify all Clients
     */
    public void notifyViewers()
    {
        for (Client client : viewers) {
            client.update(this.name, this.status);
        }
    }

    /**
     * If a video was accessed, set the status to BUSY and notify the others.
     */
    @Override
    public void onAccess()
    {
        this.status = VideoStatus.BUSY;
        notifyViewers();
    }

    /**
     * If the video status has changed (not BUSY or UNAVAILABLE), set the status to AVAILABLE and notify the others.
     */
    @Override
    public void onChange()
    {
        this.status = VideoStatus.AVAILABLE;
        notifyViewers();
    }

    /**
     * If it is past the end hour of a video, set the status to UNAVAILABLE and notify the others.
     */
    @Override
    public void onEndHour()
    {
        this.status = VideoStatus.UNAVAILABLE;
        notifyViewers();
    }
}
