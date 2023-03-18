package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table
public class Client extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;
    private String country;

    public Client() {
        super();
    }

    public Client(Long id, Long userID, String firstName, String lastName, String email, String password, int age, String country)
    {
        super(userID, "CLIENT", firstName, lastName, email, password);
        this.id = id;
        this.age = age;
        this.country = country;
    }

    public Client(String firstName, String lastName, String email, String password, int age, String country)
    {
        super("CLIENT", firstName, lastName, email, password);
        this.age = age;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
