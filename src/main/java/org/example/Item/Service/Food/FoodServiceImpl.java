package org.example.Item.Service.Food;

import org.example.Item.Repository.Food.FoodRepo;
import org.example.Item.Items.Food;

public class FoodServiceImpl implements FoodService {
    private final FoodRepo foodRepo;

    public FoodServiceImpl(FoodRepo foodRepo) {
        this.foodRepo = foodRepo;
    }

    public boolean saveFoodDetailsToAllFoodsFile(Food food) {
        return foodRepo.saveToAllFoodsFile(food);
    }

    public String getAllFoodsFromRepo() {
        return foodRepo.returnAllFoods();
    }

    public Food findFoodById(int id) {
        return foodRepo.findById(id);
    }

    public boolean saveFoodDetailsToRepo(Food food, String dateOfEating) {
        return foodRepo.saveForUser(food, dateOfEating);
    }

    public String getFoodForUserByDate(String username, String date) {
        return foodRepo.returnContentFromFileForUser(username, date);
    }

}
