package org.example.Item.Utility.Food;

import org.example.Item.Bank.Food.FoodBank;
import org.example.Item.Items.Food;

public class FoodUtilityImpl implements FoodUtility {
    private final FoodBank foodBank;

    public FoodUtilityImpl(FoodBank foodBank) {
        this.foodBank = foodBank;
    }

    public boolean saveItemDetailsToFile(String username, Food food) {
        return foodBank.save(food);
    }
}
