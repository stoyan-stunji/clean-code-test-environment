package org.example.Item.Bank;

import org.example.Item.Items.Food;
import org.example.Item.Item;
import org.example.Item.Items.Water;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ItemFileBankImpl<T extends Item> implements ItemBank<T> {
    // These are the directories to keep all the food/water data;
    private static final String STORAGE_DIR_WATER = "users_water_data";
    private static final String STORAGE_DIR_FOOD = "users_food_data";

    public ItemFileBankImpl(T instance) {
        if (instance instanceof Water) {
            if (!createDirectory(STORAGE_DIR_WATER)) {
                throw new RuntimeException("Failed to create directory: " + STORAGE_DIR_WATER);
            }
        } else if (instance instanceof Food) {
            if (!createDirectory(STORAGE_DIR_FOOD)) {
                throw new RuntimeException("Failed to create directory: " + STORAGE_DIR_FOOD);
            }
        }
    }

    private boolean createDirectory(String directoryName) {
        File directory = new File(directoryName);
        return directory.exists() || directory.mkdirs();
    }

    public boolean save(T item) {
        String username = item.getUsername();
        String fileName = createFileName(item, username, null);
        System.out.println("Saving " + fileName);
        return writeToFile(item, fileName);
    }

    public Optional<String> returnContentFromFile(T item, String username, String date) {
        String fileName = createFileName(item, username, date);
        System.out.println("Reading " + fileName);
        File file = new File(fileName);
        return returnContentFromSpecificFile(file);
    }

    private String createFileName(Item item, String username, String date) {
        if (item instanceof Water water) {
            if(date == null) {
                return STORAGE_DIR_WATER + "/" + username + "_" + water.getDateOfDrinking() + ".txt";
            }
            return STORAGE_DIR_WATER + "/" + username + "_" + date + ".txt";
        } else if (item instanceof Food food) {
            return STORAGE_DIR_FOOD + "/" + username + ".txt";
        }
        return "hui";
    }

    private boolean writeToFile(T item, String fileName) {
        if (item instanceof Water water) {
            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.write((water.getMililitres().toString() + System.lineSeparator()));
            } catch (IOException e) {
                return false;
            }
        }
        else if (item instanceof Food food) {
            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.write(food.toString() + System.lineSeparator());
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public Optional<String> returnContentFromSpecificFile(File file) {
        if (!file.exists()) {
            return Optional.empty();
        }
        try {
            Path path = file.toPath();
            String content = Files.readString(path);
            return Optional.of(content);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
