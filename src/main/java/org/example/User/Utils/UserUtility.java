package org.example.User.Utils;

import org.example.User.User;

public interface UserUtility
{
    User getUserByUsername(String username);
    boolean createUser(String username, String password);
}
