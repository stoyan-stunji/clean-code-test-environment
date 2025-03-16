package org.example.Item.Service.Food;

import org.example.Item.Items.Food;

public interface FoodService {
    boolean saveFoodDetailsToAllFoodsFile(Food food);
    String getAllFoodsFromRepo();
    Food findFoodById(int id);
    boolean saveFoodDetailsToRepo(Food food, String dateOfEating);
    String getFoodForUserByDate(String username, String date);
}
