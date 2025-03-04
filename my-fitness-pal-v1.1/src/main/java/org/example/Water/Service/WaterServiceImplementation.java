package org.example.Water.Service;

import org.example.ID.ID;
import org.example.Water.Repository.WaterRepository;
import org.example.Water.Water;
import org.example.Water.WaterDetails;

import java.util.Optional;

public class WaterServiceImplementation implements WaterService {
    private final WaterRepository waterRepository;

    public WaterServiceImplementation(WaterRepository waterRepository) {
        this.waterRepository = waterRepository;
    }

    public void logWaterIntake(ID userID, WaterDetails waterDetails) {
        Water water = new Water(userID, waterDetails);
        waterRepository.save(water);
    }
}
