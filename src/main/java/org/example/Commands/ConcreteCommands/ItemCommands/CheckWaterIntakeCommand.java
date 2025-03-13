package org.example.Commands.ConcreteCommands.ItemCommands;

import org.example.Commands.Command;

import org.example.Item.Bank.Water.WaterBank;
import org.example.Item.Items.Water;

import java.util.Optional;

public class CheckWaterIntakeCommand implements Command {
    private final WaterBank itemBank;
    private final String username;
    private final String date;

    public CheckWaterIntakeCommand(WaterBank itemBank, String username, String date) {
        this.itemBank = itemBank;
        this.username = username;
        this.date = date;
    }

    public boolean execute() {
        Optional<String> itemData = itemBank.returnContentFromFile(username, date);
        if (itemData.isEmpty()) {
            return false;
        }
        System.out.println(itemData.get());
        return true;
    }
}

