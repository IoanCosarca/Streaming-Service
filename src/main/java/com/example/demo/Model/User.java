package com.example.demo.Model;

import jakarta.persistence.*;

/**
 * Class that is mapped with the database table with the same name.
 */
abstract class User {
    private Long userID;
    private String type;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {}

    public User(Long userID, String type, String firstName, String lastName, String email, String password)
    {
        this.userID = userID;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String type, String firstName, String lastName, String email, String password)
    {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
