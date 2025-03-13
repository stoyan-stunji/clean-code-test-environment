package org.example.Commands.ConcreteCommands.ItemCommands;

import org.example.Commands.Command;

import org.example.Item.Bank.Food.FoodBank;

import java.util.Optional;

public class ViewAllFoodsCommand implements Command {
    private final FoodBank foodBank;
    private final String username;

    public ViewAllFoodsCommand(FoodBank foodBank, String username) {
        this.foodBank = foodBank;
        this.username = username;
    }

    public boolean execute() {
        Optional<String> itemData = foodBank.returnContentFromFile(username);
        if (itemData.isEmpty()) {
            return false;
        }
        System.out.println(itemData.get());
        return true;
    }
}


