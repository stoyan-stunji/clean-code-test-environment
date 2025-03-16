package org.example.Commands.FoodCommands;

import org.example.Commands.Command;
import org.example.Item.Service.Food.FoodService;

public class ViewAllFoodsCommand implements Command {
    private final FoodService foodService;

    public ViewAllFoodsCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    public boolean execute() {
        String fileContent = foodService.getAllFoodsFromRepo();
        System.out.println(fileContent);
        return true;
    }

    public String getCommandName() {
        return "08. View All Foods";
    }
}
