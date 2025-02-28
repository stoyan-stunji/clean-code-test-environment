package org.example.User;

public class User
{
    private int id;
    private final UserAccountDetails userAccountDetails;

    public User(int id, UserAccountDetails userAccountDetails) {
        this.id = id;
        this.userAccountDetails = userAccountDetails;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return userAccountDetails.username();
    }

    public String getPassword() {
        return userAccountDetails.password();
    }
}
