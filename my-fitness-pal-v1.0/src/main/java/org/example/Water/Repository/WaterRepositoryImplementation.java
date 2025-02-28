package org.example.Water.Repository;

import org.example.ID.ID;
import org.example.Water.Water;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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
        String fileName = createFileName(water.getUserID().toString(), water.getDateOfDrinking());
        writeToFile(fileName, water.getMililitres().toString());
    }

    public void writeToFile(String fileName, String mililitres) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(mililitres + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<String> getByUserAndDate(ID userId, String date) {
        String fileName = createFileName(userId.toString(), date);
        File file = new File(fileName);
        return returnContentFromFile(file);
    }

    private Optional<String> returnContentFromFile(File file)
    {
        if (!file.exists()) {
            return Optional.empty();
        }
        try {
            String content = Files.readString(file.toPath());
            return Optional.of(content);
        } catch (IOException e) {
            return Optional.empty();
        }
    }


    private String createFileName(String id, String date) {
        return STORAGE_DIR + "/" + id + "_" + date + ".txt";
    }
}
