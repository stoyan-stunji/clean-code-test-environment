package org.example.Commands.UserCommands;

import org.example.Commands.Command;
import org.example.User.Service.UserService;

public class UserRegisterCommand implements Command {
    private final UserService userService;
    private final String username;
    private final String password;

    public UserRegisterCommand(UserService userService, String username, String password) {
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

    public String getCommandName() {
        return "01. Register";
    }


}
