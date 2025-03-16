package org.example.Commands.FoodCommands;

import org.example.Commands.Command;
import org.example.Item.Repository.Food.FoodRepo;
import org.example.Item.Items.Food;
import org.example.Item.Service.Food.FoodService;

public class CreateFoodCommand implements Command {
    private final FoodService foodService;
    private final Food food;

    public CreateFoodCommand(FoodService foodService, Food food) {
        this.foodService = foodService;
        this.food = food;
    }

    public boolean execute() {
        if (!foodService.saveFoodDetailsToAllFoodsFile(food)) {
            String exceptionMessage = "CreateFoodCommand::execute::fail_to_save::"
                    + food.getName();
            throw new RuntimeException(exceptionMessage);
        }
        return true;
    }

    public String getCommandName() {
        return "07. Create Food";
    }


}
