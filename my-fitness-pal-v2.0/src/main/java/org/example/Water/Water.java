package org.example.Water;

public class Water
{
    private final String username;
    private final WaterDetails waterDetails;

    public Water(String username, WaterDetails waterDetails) {
        this.username = username;
        this.waterDetails = waterDetails;
    }

    public String getUsername() {
        return username;
    }

    public Integer getMililitres() {
        return waterDetails.mililitres();
    }

    public String getDateOfDrinking() {
        return waterDetails.dateOfDrinking();
    }

}
