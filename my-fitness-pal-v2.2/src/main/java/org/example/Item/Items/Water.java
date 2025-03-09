package org.example.Item.Items;

import org.example.Item.Item;

public class Water extends Item {
    private final Integer mililitres;
    private final String dateOfDrinking;

    public Water(String username, Integer mililitres, String dateOfDrinking) {
        super(username);
        this.mililitres = mililitres;
        this.dateOfDrinking = dateOfDrinking;
    }

    public Integer getMililitres() {
        return mililitres;
    }

    public String getDateOfDrinking() {
        return dateOfDrinking;
    }
}
