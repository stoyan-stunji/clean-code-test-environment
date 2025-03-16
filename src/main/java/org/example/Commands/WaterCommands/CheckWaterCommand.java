package org.example.Commands.WaterCommands;

import org.example.Commands.Command;
import org.example.Item.Repository.Water.WaterRepo;
import org.example.Item.Service.Water.WaterService;

public class CheckWaterCommand implements Command {
    private final WaterService waterService;
    private final String username;
    private final String dateOfDrinking;

    public CheckWaterCommand(WaterService waterService, String username, String dateOfDrinking) {
        this.waterService = waterService;
        this.username = username;
        this.dateOfDrinking = dateOfDrinking;
    }

    public boolean execute() {
        String fileContent = waterService.getWaterForUserByDate(username, dateOfDrinking);
        System.out.println(fileContent);
        return true;
    }

    public String getCommandName() {
        return "06. Check Water Intake";
    }
}
