package org.example.Commands.ForWater;

import org.example.Commands.Command;
import org.example.Water.Service.WaterService;
import org.example.Water.WaterDetails;

public class AddWaterCommand implements Command {
    private final WaterService waterService;
    private final String username;
    private final WaterDetails waterDetails;

    public AddWaterCommand(WaterService waterService, String username, WaterDetails waterDetails) {
        this.waterService = waterService;
        this.username = username;
        this.waterDetails = waterDetails;
    }

    public boolean execute() {
        return waterService.logWaterIntake(username, waterDetails);
    }
}
