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
        userRepository.add(new User(id, userAccountDetails));
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}

