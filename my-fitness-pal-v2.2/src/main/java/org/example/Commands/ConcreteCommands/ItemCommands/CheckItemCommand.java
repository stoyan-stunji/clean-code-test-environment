package org.example.Commands.ConcreteCommands.ItemCommands;

import org.example.Commands.Command;
import org.example.Item.Item;
import org.example.Item.Bank.ItemBank;
import org.example.Item.Items.Food;
import org.example.Item.Items.Water;

import java.util.Optional;

public class CheckItemCommand<T extends Item> implements Command {
    private final ItemBank<T> itemBank;
    private final String username;
    private final String date;

    public CheckItemCommand(ItemBank<T> itemBank, String username, String date) {
        this.itemBank = itemBank;
        this.username = username;
        this.date = date;
    }

    // TODO: Do not check with NULL, change it;
    public boolean execute() {
        if (date != null) {
            return returnWaterContentFromFile();
        }
        else {
            return returnFoodContentFromFile();
        }
    }

    @SuppressWarnings("unchecked")
    private boolean returnWaterContentFromFile() {
        Water water = new Water(username, null, date);
        return printData((T) water);
    }

    @SuppressWarnings("unchecked")
    private boolean returnFoodContentFromFile() {
        Food food = new Food(username, null, null, null,
                null, null, null, null, null);
        return printData((T) food);
    }

    private boolean printData(T item) {
        Optional<String> itemData = itemBank.returnContentFromFile(item, username, date);
        if (itemData.isEmpty()) {
            return false;
        }
        System.out.println(itemData.get());
        return true;
    }
}
