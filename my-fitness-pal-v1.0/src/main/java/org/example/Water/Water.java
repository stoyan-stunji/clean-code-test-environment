package org.example.Water;

import org.example.ID.ID;

public class Water
{
    private final ID userID;
    private WaterDetails waterDetails;

    public Water(ID userID, WaterDetails waterDetails) {
        this.userID = userID;
        this.waterDetails = waterDetails;
    }

    public ID getUserID() {
        return userID;
    }

    public Integer getMililitres() {
        return waterDetails.mililitres();
    }

    public String getDateOfDrinking() {
        return waterDetails.dateOfDrinking();
    }

}
