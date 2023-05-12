package com.example.demo.Model;

/**
 * Class that is mapped with the database table with the same name.
 */
public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(Long userID, String firstName, String lastName, String email, String password)
    {
        super(userID, UserType.ADMIN, firstName, lastName, email, password);
    }

    public Admin(String firstName, String lastName, String email, String password)
    {
        super(UserType.ADMIN, firstName, lastName, email, password);
    }
}
