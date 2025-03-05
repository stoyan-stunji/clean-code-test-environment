package org.example.Commands.ForWater;

import org.example.Commands.Command;
import org.example.LogHandler.LogHandler;
import org.example.Water.Service.WaterService;
import org.example.Water.WaterDetails;

import java.util.logging.Logger;

public class AddWaterCommand implements Command {
    private final WaterService waterService;
    private final String username;
    private final WaterDetails waterDetails;
    private final LogHandler logger;

    public AddWaterCommand(WaterService waterService, String username,
                           WaterDetails waterDetails, LogHandler logger) {
        this.waterService = waterService;
        this.username = username;
        this.waterDetails = waterDetails;
        this.logger = logger;
    }

    public void execute() {
        waterService.logWaterIntake(username, waterDetails);
        String logMessage = username + " added water on " + waterDetails.dateOfDrinking()
                                     + ' ' +  waterDetails.mililitres() + " ml.";
        logger.publish(logMessage);
    }
}
