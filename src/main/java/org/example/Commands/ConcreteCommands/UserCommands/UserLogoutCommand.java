package org.example.Commands.ConcreteCommands.UserCommands;

import org.example.Commands.Command;

public class UserLogoutCommand implements Command {
    private final String username;

    public UserLogoutCommand(String username) {
        this.username = username;
    }

    public boolean execute() {
        return true;
    }
}
