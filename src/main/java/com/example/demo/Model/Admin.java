package com.example.demo.Model;

import jakarta.persistence.*;

public class Admin extends User {
    private Long id;

    public Admin() {
        super();
    }

    public Admin(Long id, Long userID, String firstName, String lastName, String email, String password)
    {
        super(userID, "ADMIN", firstName, lastName, email, password);
        this.id = id;
    }

    public Admin(String firstName, String lastName, String email, String password)
    {
        super("ADMIN", firstName, lastName, email, password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
