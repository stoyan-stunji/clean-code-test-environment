package org.example.Item.Utility.Water;


import org.example.Item.Bank.Water.WaterBank;
import org.example.Item.Items.Water;

public class WaterUtilityImpl implements WaterUtility {
    private final WaterBank waterBank;

    public WaterUtilityImpl(WaterBank waterBank) {
        this.waterBank = waterBank;
    }

    public boolean saveItemDetailsToFile(String username, Water water) {
        return waterBank.save(water);
    }
}
