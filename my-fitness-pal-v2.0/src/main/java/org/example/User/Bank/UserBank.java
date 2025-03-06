package org.example.User.Bank;

import org.example.User.User;

public interface UserBank {
    User getByUsername(String username);
    boolean add(User user);
}