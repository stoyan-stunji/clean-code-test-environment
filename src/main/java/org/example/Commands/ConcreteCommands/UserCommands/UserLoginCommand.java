package org.example.Commands.ConcreteCommands.UserCommands;

import org.example.Commands.Command;
import org.example.User.Utils.UserUtils;
import org.example.User.User;

public class UserLoginCommand implements Command {
    private final UserUtils userService;
    private final String username;
    private final String password;

    public UserLoginCommand(UserUtils userService, String username, String password) {
        this.userService = userService;
        this.username = username;
        this.password = password;
    }

    public boolean execute() {
        User user = userService.getUserByUsername(username);
        return checkIfUserIsCorrect(user);
    }

    private boolean checkIfUserIsCorrect(User user) {
        return user == null || !username.equals(user.getUsername()) || !password.equals(user.getPassword());
    }
}
