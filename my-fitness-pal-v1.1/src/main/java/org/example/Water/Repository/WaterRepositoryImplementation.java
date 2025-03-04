package org.example.Water.Repository;

import org.example.ID.ID;
import org.example.Water.Water;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        String userID = water.getUserID().toString();
        String dateOfDrinking = water.getDateOfDrinking();
        String mililitres =  water.getMililitres().toString();
        String fileName = createFileName(userID, dateOfDrinking);
        writeToFile(fileName, mililitres);
    }

    private void writeToFile(String fileName, String mililitres) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(mililitres + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<String> getByBothUserAndDate(ID userId, String date) {
        String fileName = createFileName(userId.toString(), date);
        File file = new File(fileName);
        return returnContentFromFile(file);
    }

    private String createFileName(String id, String date) {
        return STORAGE_DIR + "/" + id + "_" + date + ".txt";
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
