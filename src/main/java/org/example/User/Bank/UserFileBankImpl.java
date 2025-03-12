package org.example.User.Bank;

import org.example.User.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileBankImpl implements UserBank {
    // "users.txt" is the file where all the pairs (username, password) are saved;
    private final String fileName = "users.txt";
    private final List<User> users;

    public UserFileBankImpl() {
        this.users = loadUsersFromFile();
    }

    private List<User> loadUsersFromFile() {
        List<User> loadedUsers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                User user = parseUserFromLine(line);
                if (user != null) {
                    loadedUsers.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedUsers;
    }

    private User parseUserFromLine(String line) {
        String[] parts = line.split(" ", 3);
        if (parts.length == 3) {
            try {
                String username = parts[0];
                String password = parts[1];
                return new User(username, password);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (User user : users) {
                String line = user.getUsername() + " " + user.getPassword();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public User getByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    public boolean add(User user) {
        if (getByUsername(user.getUsername()) == null) {
            users.add(user);
            return saveUsersToFile();
        }
        return false;
    }
}
