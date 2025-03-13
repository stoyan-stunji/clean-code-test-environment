package org.example.Item.Bank.Water;

import org.example.Item.Items.Water;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class WaterBankImpl implements WaterBank {
    private static final String STORAGE_DIR = "user_water_data";

    public WaterBankImpl() {
        File directory = new File(STORAGE_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public boolean save(Water water) {
        String filename = STORAGE_DIR + "/" + water.getUsername() + "_" + water.getDateOfDrinking() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(water.getMililitres() + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<String> returnContentFromFile(String username, String date) {
        String filename = STORAGE_DIR + "/" + username + "_" + date + ".txt";
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
