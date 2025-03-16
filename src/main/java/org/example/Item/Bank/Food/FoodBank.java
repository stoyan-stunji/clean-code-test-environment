package org.example.Item.Bank.Food;

import org.example.Item.Items.Food;
import org.example.Item.Items.Water;

import java.util.Optional;

public interface FoodBank
{
    boolean saveForUser(Food food);
    boolean saveToAllFoodsFile(Food food);
    String returnContentFromFileForUser(String username, String date);
}
