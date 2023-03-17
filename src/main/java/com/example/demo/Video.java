package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String channel;
    private String genre;
    private boolean ageRestriction;
    @Column(unique = true)
    private String link;
    private int startHour;
    private int endHour;

    public Video() {
    }

    public Video(int id, String name, String channel, String genre, boolean ageRestriction, String link, int startHour, int endHour)
    {
        this.id = id;
        this.name = name;
        this.channel = channel;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.link = link;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Video(String name, String channel, String genre, boolean ageRestriction, String link, int startHour, int endHour)
    {
        this.name = name;
        this.channel = channel;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.link = link;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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
}
