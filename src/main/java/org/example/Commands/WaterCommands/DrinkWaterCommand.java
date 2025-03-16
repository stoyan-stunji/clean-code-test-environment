package org.example.Commands.WaterCommands;

import org.example.Commands.Command;
import org.example.Item.Items.Water;
import org.example.Item.Service.Water.WaterService;

public class DrinkWaterCommand implements Command {
    private final WaterService waterService;
    private final Water water;

    public DrinkWaterCommand(WaterService waterService, Water water) {
        this.waterService = waterService;
        this.water = water;
    }

    public boolean execute() {
        if (!waterService.saveWaterDetailsToRepo(water)) {
            String exceptionMessage = "DrinkWaterCommand::execute::fail_to_save_water_intake_for_user:"
                    + water.getUsername();
            throw new RuntimeException(exceptionMessage);
        }
        return true;
    }

    public String getCommandName() {
        return "05. Drink Water";
    }
}
