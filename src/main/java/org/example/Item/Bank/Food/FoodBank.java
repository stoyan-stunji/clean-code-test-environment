package org.example.Item.Bank.Food;

import org.example.Item.Items.Food;
import org.example.Item.Items.Water;

import java.util.Optional;

public interface FoodBank
{
    boolean save(Food item);
    Optional<String> returnContentFromFile(String username);
}
