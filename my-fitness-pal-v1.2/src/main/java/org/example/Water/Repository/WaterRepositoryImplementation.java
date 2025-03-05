package org.example.Water.Repository;

import org.example.Water.Water;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class WaterRepositoryImplementation implements WaterRepository {
    private static final String STORAGE_DIR = "water_data";

    public WaterRepositoryImplementation() {
        File directory = new File(STORAGE_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void save(Water water) {
        String username = water.getUsername();
        String dateOfDrinking = water.getDateOfDrinking();
        String mililitres =  water.getMililitres().toString();
        String fileName = createFileName(username, dateOfDrinking);
        writeToFile(fileName, mililitres);
    }

    public Optional<String> getByBothUsernameAndDate(String username, String date) {
        String fileName = createFileName(username, date);
        File file = new File(fileName);
        return returnContentFromFile(file);
    }

    private String createFileName(String username, String date) {
        return STORAGE_DIR + "/" + username + "_" + date + ".txt";
    }

    private void writeToFile(String fileName, String mililitres) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(mililitres + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
