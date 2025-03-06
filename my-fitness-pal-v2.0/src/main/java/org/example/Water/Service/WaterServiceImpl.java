package org.example.Water.Service;

import org.example.Water.Bank.WaterBank;
import org.example.Water.Water;
import org.example.Water.WaterDetails;

public class WaterServiceImpl implements WaterService {
    private final WaterBank waterRepository;

    public WaterServiceImpl(WaterBank waterRepository) {
        this.waterRepository = waterRepository;
    }

    public boolean logWaterIntake(String username, WaterDetails waterDetails) {
        Water water = new Water(username, waterDetails);
        return waterRepository.save(water);
    }
}
