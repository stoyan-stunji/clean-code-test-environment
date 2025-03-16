package org.example.Item.Repository.Food;

import org.example.Item.Items.Food;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FoodRepoFileImpl implements FoodRepo {
    // This is the directory where every user's info about the water intake is stored;
    // It is temporary;
    private static final String STORAGE_DIR = "user_food_data";
    private static final String allFoodsFile = STORAGE_DIR + "/all_food_data.txt";
    private final File directory;

    public FoodRepoFileImpl() {
        this.directory = new File(STORAGE_DIR);
        if (!createDirectory()) {
            throw new RuntimeException("FoodBankImpl::ctor::unable_to_create_directory");
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

    public boolean saveForUser(Food food, String dateOfEating) {
        String username = food.getUsername();
        String filename = createFileName(username, dateOfEating);

        String line = createFileLineByNumberOfServings(food);
        if (!writeToFile(filename, line)) {
            throw new RuntimeException("FoodBankImpl::save::unable_to_write_file");
        }
        return true;
    }

    private boolean writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private String createFileLineByNumberOfServings(Food food) {
        String numberOfServings = food.getServingDetails().numberOfServings();
        return numberOfServings + " X " + food.formatForFileSaving() + "\n";
    }

    public boolean saveToAllFoodsFile(Food food) {
        String line = createFileLineByOrderOfFoods(food);
        return writeToFile(allFoodsFile, line);
    }

    private String createFileLineByOrderOfFoods(Food food) {
        int nextNumber = getNextNumberFromFile();
        return nextNumber + " " + food.formatForFileSaving() + "\n";
    }

    private int getNextNumberFromFile() {
        String lastLine = readLastLineFromFile();
        return extractNextNumberFromLine(lastLine);
    }

    private String readLastLineFromFile() {
        try {
            Path filePath = Paths.get(allFoodsFile);
            if (Files.exists(filePath)) {
                try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                    String lastLine = null, line;
                    while ((line = reader.readLine()) != null) {
                        lastLine = line;
                    }
                    return lastLine;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("FoodBankImpl::readLastLineFromFile::unable_to_read_file");
        }
        return null;
    }

    private int extractNextNumberFromLine(String line) {
        int nextNumber = 1;
        if (line != null) {
            String[] parts = line.split(" ", 2);
            if (parts.length > 1) {
                try {
                    nextNumber = Integer.parseInt(parts[0]) + 1;
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return nextNumber;
    }

    public String returnContentFromFileForUser(String username, String date) {
        String filename = createFileName(username, date);
        try {
            return extractContent(filename);
        } catch (IOException e) {
            String exceptionMessage = "FoodBankImpl::returnContentFromFile::fail_to_read_file::"
                    + filename;
            throw new RuntimeException(exceptionMessage);
        }
    }

    private String extractContent(String filename) throws IOException {
        Path filePath = Paths.get(filename);
        if (Files.exists(filePath)) {
            return Files.readString(filePath);
        }
        String exceptionMessage = "FoodBankImpl::getContent::fail_to_read_file::"
                + filename;
        throw new FileNotFoundException(exceptionMessage);
    }

    private String createFileName(String username, String dateOfEating) {
        return STORAGE_DIR + "/" + username + "_" + dateOfEating + ".txt";
    }

    public String returnAllFoods() {
        try {
            Path filePath = Paths.get(allFoodsFile);
            if (Files.exists(filePath)) {
                return Files.readString(filePath);
            }
            return "";
        } catch (IOException e) {
            throw new RuntimeException("FoodBankImpl::returnAllFoods::unable_to_read_file");
        }
    }

    public Food findById(int id) {
        Path filePath = Paths.get(allFoodsFile);
        if (Files.exists(filePath)) {
            List<String> lines = readAllLines(filePath);
            for (String line : lines) {
                Food food = parseLine(line, id);
                if (food != null) {
                    return food;
                }
            }
        }
        // TODO Remove null;
        return null;
    }

    private List<String> readAllLines(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            return reader.lines().collect(Collectors.toList());
        }
        catch (IOException e) {
            throw new RuntimeException("FoodBankImpl::readAllLines::unable_to_read_file");
        }
    }

    private Food parseLine(String line, int id) {
        String[] parts = line.split(" ", 2);
        if (parts.length > 1) {
            try {
                int lineId = Integer.parseInt(parts[0]);
                if (lineId == id) {
                    return Food.parseFromFileLine("", parts[1]);
                }
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }
}
