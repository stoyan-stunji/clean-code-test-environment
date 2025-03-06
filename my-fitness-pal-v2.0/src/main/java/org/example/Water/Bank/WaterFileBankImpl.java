package org.example.Water.Bank;

import org.example.Water.Water;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class WaterFileBankImpl implements WaterBank {
    // this directory is specified to keep all the water data;
    private static final String STORAGE_DIR = "water_data";

    public WaterFileBankImpl() {
        File directory = new File(STORAGE_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public boolean save(Water water) {
        String username = water.getUsername();
        String dateOfDrinking = water.getDateOfDrinking();
        String mililitres =  water.getMililitres().toString();
        String fileName = createFileName(username, dateOfDrinking);
        return writeToFile(fileName, mililitres);
    }

    public Optional<String> getByBothUsernameAndDate(String username, String date) {
        String fileName = createFileName(username, date);
        File file = new File(fileName);
        return returnContentFromFile(file);
    }

    private String createFileName(String username, String date) {
        return STORAGE_DIR + "/" + username + "_" + date + ".txt";
    }

    private boolean writeToFile(String fileName, String mililitres) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(mililitres + System.lineSeparator());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private Optional<String> returnContentFromFile(File file)
    {
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
