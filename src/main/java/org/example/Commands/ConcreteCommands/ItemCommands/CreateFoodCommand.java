package org.example.Commands.ConcreteCommands.ItemCommands;

import org.example.Commands.Command;
import org.example.Item.Items.Food;
import org.example.Item.Utility.Food.FoodUtility;

public class CreateFoodCommand implements Command
{
    private final FoodUtility itemUtility;
    private final String username;
    private final Food item;

    public CreateFoodCommand(FoodUtility itemUtility, String username, Food item) {
        this.itemUtility = itemUtility;
        this.username = username;
        this.item = item;
    }

    public boolean execute() {
        return itemUtility.saveItemDetailsToFile(username, item);
    }
}
