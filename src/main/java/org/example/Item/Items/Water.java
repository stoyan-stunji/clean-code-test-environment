package org.example.Item.Items;

import org.example.Item.Item;

public class Water extends Item {
    private final String mililitres;

    public Water(String username, String mililitres, String dateOfDrinking) {
        super(username, dateOfDrinking);
        this.mililitres = mililitres;
    }

    public String getUsername() {
        return super.getUsername();
    }

    public String getMililitres() {
        return mililitres;
    }

    public String getDateOfDrinking() {
        return super.getDateOfConsumption();
    }
}
