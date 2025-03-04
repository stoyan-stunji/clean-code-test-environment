package org.example.User.Service;

import org.example.ID.ID;
import org.example.User.Repository.UserRepository;
import org.example.User.User;
import org.example.User.UserAccountDetails;

import java.util.List;

public class UserServiceImplementation implements UserService
{
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    public void createUser(int id, UserAccountDetails userAccountDetails) {
        User user = new User(id, userAccountDetails);
        userRepository.add(user);
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}

