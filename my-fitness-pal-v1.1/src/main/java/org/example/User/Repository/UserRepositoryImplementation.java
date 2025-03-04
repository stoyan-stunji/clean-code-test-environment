package org.example.User.Repository;

import org.example.User.User;
import org.example.User.UserAccountDetails;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImplementation implements UserRepository {
    private final String fileName = "users.txt";
    private List<User> users;

    public UserRepositoryImplementation() {
        this.users = loadUsersFromFile();
    }

    private List<User> loadUsersFromFile() {
        List<User> loadedUsers = new ArrayList<>();
        List<String> lines = readLinesFromFile();
        for (String line : lines) {
            User user = parseUserFromLine(line);
            if (user != null) {
                loadedUsers.add(user);
            }
        }
        return loadedUsers;
    }

    private List<String> readLinesFromFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private User parseUserFromLine(String line) {
        String[] parts = line.split(" ", 3);
        if (parts.length == 3) {
            try {
                int id = Integer.parseInt(parts[0]);
                String username = parts[1];
                String password = parts[2];
                UserAccountDetails userAccountDetails = new UserAccountDetails(username, password);
                return new User(id, userAccountDetails);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void saveUsersToFile() {
        try (FileWriter writer = new FileWriter(fileName)) {
            writeUsersToFile(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeUsersToFile(FileWriter writer) {
        try {
            for (User user : users) {
                int userID = user.getId();
                String username = user.getUsername();
                String password = user.getPassword();
                String separator = System.lineSeparator();
                String userLine = userID + " " + username + " " + password + separator;
                writer.write(userLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public void add(User user) {
        if (getById(user.getId()) == null) {
            users.add(user);
            saveUsersToFile();
        }
    }

    public void delete(int id) {
        if (users.removeIf(u -> u.getId() == id)) {
            saveUsersToFile();
        }
    }
}