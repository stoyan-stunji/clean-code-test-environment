package org.example.Item.Service.Water;


import org.example.Item.Repository.Water.WaterRepo;
import org.example.Item.Items.Water;

public class WaterServiceImpl implements WaterService {
    private final WaterRepo waterRepo;

    public WaterServiceImpl(WaterRepo waterRepo) {
        this.waterRepo = waterRepo;
    }

    public boolean saveWaterDetailsToRepo(Water water) {
        return waterRepo.saveForUser(water);
    }

    public String getWaterForUserByDate(String username, String dateOfDrinking) {
        return waterRepo.returnContentFromFileForUser(username, dateOfDrinking);
    }
}
