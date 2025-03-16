package org.example.Item;

public class Item {
    private String username;
    private final String dateOfConsumption;

    public Item(String username, String dateOfConsumption) {
        this.username = username;
        this.dateOfConsumption = dateOfConsumption;
    }

    public boolean setUsername(String username) {
        this.username = username;
        return true;
    }

    public String getUsername() {
        return username;
    }

    public String getDateOfConsumption() {
        return dateOfConsumption;
    }
}
