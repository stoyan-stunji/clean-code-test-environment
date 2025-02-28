package org.example.User.Repository;

import org.example.User.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImplementation implements UserRepository
{
    private final List<User> users = new ArrayList<>();

    public User getById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public void add(User user) {
        if (getById(user.getId()) == null) {
            users.add(user);
        }
    }

    public void delete(int id) {
        users.removeIf(u -> u.getId() == id);
    }
}

