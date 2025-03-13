package org.example.User.Utils;

import org.example.User.Bank.UserBank;
import org.example.User.User;

public class UserUtilityImpl implements UserUtility {
    private final UserBank userRepository;

    public UserUtilityImpl(UserBank userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public boolean createUser(String username, String password) {
        User user = new User(username, password);
        return userRepository.add(user);
    }
}