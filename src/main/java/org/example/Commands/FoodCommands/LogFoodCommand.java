package org.example.Commands.FoodCommands;

import org.example.Commands.Command;
import org.example.Item.Repository.Food.FoodRepo;
import org.example.Item.Items.Food;
import org.example.Item.Service.Food.FoodService;

public class LogFoodCommand implements Command {
    private final FoodService foodService;
    private final int id;
    private final String username;
    private final String dateOfEating;

    public LogFoodCommand(FoodService foodService, int id, String username, String dateOfEating) {
        this.foodService = foodService;
        this.id = id;
        this.username = username;
        this.dateOfEating = dateOfEating;
    }

    public boolean execute() {
        Food food = foodService.findFoodById(id);
        food.setUsername(username);
        foodService.saveFoodDetailsToRepo(food, dateOfEating);
        return true;
    }

    public String getCommandName() {
        return "09. Log Food";
    }
}
