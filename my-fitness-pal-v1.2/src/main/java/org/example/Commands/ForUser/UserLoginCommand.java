package org.example.Commands.ForUser;

import org.example.Commands.Command;
import org.example.Languages.LanguageContext;
import org.example.LogHandler.LogHandler;
import org.example.User.Service.UserService;
import org.example.User.User;

import java.util.logging.Logger;

public class UserLoginCommand implements Command {
    private final UserService userService;
    private final String username;
    private final String password;
    private final LogHandler logger;

    public UserLoginCommand(UserService userService, String username, String password, LogHandler logger) {
        this.userService = userService;
        this.username = username;
        this.password = password;
        this.logger = logger;
    }

    public void execute() {
        User user = userService.getUserByUsername(username);

        if (!checkIfUserIsCorrect(user)) {
            throw new IllegalArgumentException("UserLoginCommand: execute() failed");
        }

        String logMessage = username + " logged in successfully.";
        logger.publish(logMessage);
    }

    private boolean checkIfUserIsCorrect(User user) {
        return user == null || !username.equals(user.getUsername()) || !password.equals(user.getPassword());
    }
}
