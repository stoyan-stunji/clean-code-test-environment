package org.example.Item.Repository.Water;

import org.example.Item.Items.Water;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WaterRepoFileImpl implements WaterRepo {
    // This is the directory where every user's info about the water intake is stored;
    // It is temporary;
    private static final String STORAGE_DIR = "user_water_data";
    private final File directory;

    public WaterRepoFileImpl() {
        this.directory = new File(STORAGE_DIR);
        if (!createDirectory()) {
            throw new RuntimeException("WaterBankImpl::ctor::unable_to_create_directory");
        }
    }

    private boolean createDirectory() {
        try {
            if (!directory.exists()) {
                return directory.mkdirs();
            }
            return true;
        } catch (SecurityException e) {
            return false;
        }
    }

    public boolean saveForUser(Water water) {
        String username = water.getUsername();
        String date = water.getDateOfDrinking();
        String filename = createFileName(username, date);

        String mililitres = water.getMililitres();
        if(!writeToFile(filename, mililitres)) {
            throw new RuntimeException("WaterBankImpl::save::unable_to_write_file");
        }
        return true;
    }

    private boolean writeToFile(String filename, String mililitres) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String line = mililitres + "\n";
            writer.write(line);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String returnContentFromFileForUser(String username, String date) {
        String filename = createFileName(username, date);
        try {
            return extractContent(filename);
        } catch (IOException e) {
            String exceptionMessage = "WaterBankImpl::returnContentFromFile::fail_to_read_file::"
                    + filename;
            throw new RuntimeException(exceptionMessage);
        }
    }

    private String extractContent(String filename) throws IOException {
        Path filePath = Paths.get(filename);
        if (Files.exists(filePath)) {
            return Files.readString(filePath);
        }
        String exceptionMessage = "WaterBankImpl::getContent::fail_to_read_file::"
                + filename;
        throw new FileNotFoundException(exceptionMessage);
    }

    private String createFileName(String username, String date) {
        return STORAGE_DIR + "/" + username + "_" + date + ".txt";
    }
}
