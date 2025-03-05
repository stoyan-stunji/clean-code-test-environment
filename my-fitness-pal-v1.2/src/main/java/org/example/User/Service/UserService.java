package org.example.User.Service;

import org.example.User.User;

public interface UserService
{
    User getUserByUsername(String username);
    void createUser(String username, String password);
    void deleteUserByUsername(String username);
}
