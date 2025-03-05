package org.example.Commands.ForWater;

import org.example.Commands.Command;
import org.example.LogHandler.LogHandler;
import org.example.Water.Repository.WaterRepository;

import java.util.Optional;

public class CheckWaterIntakeCommand implements Command {
    private final WaterRepository waterRepository;
    private final String username;
    private final String date;
    private final LogHandler logger;

    public CheckWaterIntakeCommand(WaterRepository waterRepository, String username, String date, LogHandler logger) {
        this.waterRepository = waterRepository;
        this.username = username;
        this.date = date;
        this.logger = logger;
    }

    public void execute() {
        Optional<String> waterData = waterRepository.getByBothUsernameAndDate(username, date);
        if(waterData.isEmpty()) {
            throw new IllegalArgumentException("CheckWaterIntakeCommand: execute() failed");
        }
        System.out.println(waterData.get());
        String logMessage = username + " checked water on " + date + ".";
        logger.publish(logMessage);
        logger.publish(waterData.get());
    }
}
