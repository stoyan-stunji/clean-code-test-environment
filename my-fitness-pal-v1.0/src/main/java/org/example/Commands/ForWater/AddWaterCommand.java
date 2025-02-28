package org.example.Commands.ForWater;

import org.example.Commands.Command;
import org.example.ID.ID;
import org.example.Water.Service.WaterService;
import org.example.Water.WaterDetails;

public class AddWaterCommand implements Command {
    private final WaterService waterService;
    private final ID userId;
    private final WaterDetails waterDetails;

    public AddWaterCommand(WaterService waterService, ID userId, WaterDetails waterDetails) {
        this.waterService = waterService;
        this.userId = userId;
        this.waterDetails = waterDetails;
    }

    public boolean execute() {
        waterService.logWaterIntake(userId, waterDetails);
        return true;
    }
}
