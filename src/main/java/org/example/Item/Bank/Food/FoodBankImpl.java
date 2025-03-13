package org.example.Item.Bank.Food;

import org.example.Item.Items.Food;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FoodBankImpl implements FoodBank {

    private static final String STORAGE_DIR = "user_food_data";

    public FoodBankImpl() {
        File directory = new File(STORAGE_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public boolean save(Food item) {
        String filename = STORAGE_DIR + "/" + item.getUsername() + "_" + item.getServingDetails().numberOfServings() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(String.format("%s (%dg; %.1f kcal; %.1fg, %.1fg, %.1fg)",
                    item.getName(),
                    item.getServingDetails().servingSize(),
                    item.getMacros().calories(),
                    item.getMacros().carbs(),
                    item.getMacros().fats(),
                    item.getMacros().protein()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<String> returnContentFromFile(String username) {
        String filename = STORAGE_DIR + "/" + username + ".txt";
        Path filePath = Paths.get(filename);

        if (Files.exists(filePath)) {
            try {
                String content = Files.readString(filePath);
                return Optional.of(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Optional.empty();
    }
}
