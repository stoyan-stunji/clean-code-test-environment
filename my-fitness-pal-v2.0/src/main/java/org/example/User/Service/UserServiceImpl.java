package org.example.User.Service;

import org.example.User.Bank.UserBank;
import org.example.User.User;

public class UserServiceImpl implements UserService {
    private final UserBank userRepository;

    public UserServiceImpl(UserBank userRepository) {
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