package org.example.Commands.UserCommands;

import org.example.Commands.Command;

public class ExitCommand implements Command {
    public boolean execute() {
        return true;
    }

    public String getCommandName() {
        return "04. Exit";
    }
}
