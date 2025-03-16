package org.example.Commands.UserCommands;

import org.example.Commands.Command;

public class UserLogoutCommand implements Command {

    public UserLogoutCommand() {
    }

    public boolean execute() {
        return true;
    }

    public String getCommandName() {
        return "03. Logout";
    }
}
