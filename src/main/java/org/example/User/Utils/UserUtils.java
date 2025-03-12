package org.example.User.Utils;

import org.example.User.User;

public interface UserUtils
{
    User getUserByUsername(String username);
    boolean createUser(String username, String password);
}
