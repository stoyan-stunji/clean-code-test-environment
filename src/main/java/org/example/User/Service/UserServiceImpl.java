package org.example.User.Service;

import org.example.User.Repository.UserFileRepo;
import org.example.User.User;

public class UserServiceImpl implements UserService {
    private final UserFileRepo userRepository;

    public UserServiceImpl(UserFileRepo userRepository) {
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