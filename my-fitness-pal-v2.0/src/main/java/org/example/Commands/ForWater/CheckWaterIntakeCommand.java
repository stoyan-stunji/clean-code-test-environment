package org.example.Commands.ForWater;

import org.example.Commands.Command;
import org.example.Water.Bank.WaterBank;

import java.util.Optional;

public class CheckWaterIntakeCommand implements Command {
    private final WaterBank waterRepository;
    private final String username;
    private final String date;

    public CheckWaterIntakeCommand(WaterBank waterRepository, String username, String date) {
        this.waterRepository = waterRepository;
        this.username = username;
        this.date = date;
    }

    public boolean execute() {
        Optional<String> waterData = waterRepository.getByBothUsernameAndDate(username, date);
        if(waterData.isEmpty()) {
            return false;
        }
        System.out.println(waterData.get());
        return true;
    }
}
