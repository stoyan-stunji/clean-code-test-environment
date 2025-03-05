package org.example.User.Repository;

import org.example.User.User;

public interface UserRepository {
    User getByUsername(String username);
    void add(User user);
    void deleteByUsername(String username);
}