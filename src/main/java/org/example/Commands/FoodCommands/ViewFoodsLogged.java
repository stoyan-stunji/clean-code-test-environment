package org.example.Commands.FoodCommands;

import org.example.Commands.Command;
import org.example.Item.Repository.Food.FoodRepo;
import org.example.Item.Service.Food.FoodService;

public class ViewFoodsLogged implements Command {
    private final FoodService foodService;
    private final String username;
    private final String dateOfEating;

    public ViewFoodsLogged(FoodService foodService, String username, String dateOfEating) {
        this.foodService = foodService;
        this.username = username;
        this.dateOfEating = dateOfEating;
    }

    public boolean execute() {
        String fileContent = foodService.getFoodForUserByDate(username, dateOfEating);
        System.out.println(fileContent);
        return true;
    }

    public String getCommandName() {
        return "10. View Foods Logged";
    }
}
