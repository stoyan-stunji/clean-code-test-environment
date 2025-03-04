package org.example.User.Repository;

import org.example.User.User;

public interface UserRepository
{
    User getById(int id);
    void add(User user);
    void delete(int id);
}
