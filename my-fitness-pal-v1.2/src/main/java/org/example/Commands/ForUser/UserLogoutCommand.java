package org.example.Commands.ForUser;

import org.example.Commands.Command;
import org.example.LogHandler.LogHandler;

import java.util.logging.Logger;

public class UserLogoutCommand implements Command {
    private final String username;
    private final LogHandler logger;

    public UserLogoutCommand(String username, LogHandler logger) {
        this.username = username;
        this.logger = logger;
    }

    public void execute() {
        String logMessage = username + " logged out successfully.";
        logger.publish(logMessage);
    }
}
