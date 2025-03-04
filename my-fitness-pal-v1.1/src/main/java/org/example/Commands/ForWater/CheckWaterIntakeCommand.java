package org.example.Commands.ForWater;

import org.example.Commands.Command;
import org.example.ID.ID;
import org.example.Water.Repository.WaterRepository;

import java.util.Optional;

public class CheckWaterIntakeCommand implements Command {
    private final WaterRepository waterRepository;
    private final ID userId;
    private final String date;

    public CheckWaterIntakeCommand(WaterRepository waterRepository, ID userId, String date) {
        this.waterRepository = waterRepository;
        this.userId = userId;
        this.date = date;
    }

    public boolean execute() {
        Optional<String> waterData = waterRepository.getByBothUserAndDate(userId, date);
        if(waterData.isPresent()) {
            System.out.println(waterData.get());
            return true;
        }
        return false;
    }
}
