package org.example.User.Repository;

import org.example.User.User;

public interface UserFileRepo {
    User getByUsername(String username);
    boolean add(User user);
}