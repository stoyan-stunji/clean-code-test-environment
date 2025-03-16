package org.example.Item.Repository.Food;

import org.example.Item.Items.Food;

public interface FoodRepo
{
    boolean saveForUser(Food food, String dateOfEating);
    boolean saveToAllFoodsFile(Food food);
    String returnContentFromFileForUser(String username, String date);
    String returnAllFoods();
    Food findById(int id);
}
