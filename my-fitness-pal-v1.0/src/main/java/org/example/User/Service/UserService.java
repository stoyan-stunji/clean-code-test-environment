package org.example.User.Service;

import org.example.User.User;
import org.example.User.UserAccountDetails;

public interface UserService
{
    User getUserById(int id);
    void createUser(int id, UserAccountDetails userAccountDetails);
    void deleteUser(int id);
}
