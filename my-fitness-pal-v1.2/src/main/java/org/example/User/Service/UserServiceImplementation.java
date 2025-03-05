package org.example.User.Service;

import org.example.User.Repository.UserRepository;
import org.example.User.User;

public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public void createUser(String username, String password) {
        User user = new User(username, password);
        userRepository.add(user);
    }

    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}