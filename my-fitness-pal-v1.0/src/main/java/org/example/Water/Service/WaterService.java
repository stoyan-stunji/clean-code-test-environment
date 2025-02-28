package org.example.Water.Service;

import org.example.ID.ID;
import org.example.Water.WaterDetails;

public interface WaterService {
    void logWaterIntake(ID userID, WaterDetails waterDetails);
}
