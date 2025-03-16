package org.example.Item.Service.Water;

import org.example.Item.Items.Water;

public interface WaterService {
    boolean saveWaterDetailsToRepo(Water water);
    String getWaterForUserByDate(String username, String dateOfDrinking);
}
