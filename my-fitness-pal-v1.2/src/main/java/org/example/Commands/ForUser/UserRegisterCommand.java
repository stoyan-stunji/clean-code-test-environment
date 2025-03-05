package org.example.Commands.ForUser;

import org.example.Commands.Command;
import org.example.LogHandler.LogHandler;
import org.example.User.Service.UserService;

import java.util.logging.Logger;

public class UserRegisterCommand implements Command {
    private final UserService userService;
    private final String username;
    private final String password;
    private final LogHandler logger;

    public UserRegisterCommand(UserService userService, String username, String password, LogHandler logger) {
        this.userService = userService;
        this.username = username;
        this.password = password;
        this.logger = logger;
    }

    public void execute() {
        if(userService.getUserByUsername(username) != null) {
            throw new IllegalArgumentException("UserRegister: execute() failed");
        }
        userService.createUser(username, password);
        String logMessage = username + " registered successfully.";
        logger.publish(logMessage);
    }
}
