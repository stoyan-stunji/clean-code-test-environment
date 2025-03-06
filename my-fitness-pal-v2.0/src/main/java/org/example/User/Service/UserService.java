package org.example.User.Service;

import org.example.User.User;

public interface UserService
{
    User getUserByUsername(String username);
    boolean createUser(String username, String password);
}
