package org.example.Commands.ConcreteCommands.UserCommands;

import org.example.Commands.Command;
import org.example.User.Utils.UserUtility;

public class UserRegisterCommand implements Command {
    private final UserUtility userService;
    private final String username;
    private final String password;

    public UserRegisterCommand(UserUtility userService, String username, String password) {
        this.userService = userService;
        this.username = username;
        this.password = password;
    }

    public boolean execute() {
        if(userService.getUserByUsername(username) != null) {
            return false;
        }
        return userService.createUser(username, password);
    }
}
