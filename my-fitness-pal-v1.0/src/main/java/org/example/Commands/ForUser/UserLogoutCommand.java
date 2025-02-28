package org.example.Commands.ForUser;

import org.example.Commands.Command;
import org.example.ID.ID;

public class UserLogoutCommand implements Command {
    private final ID userId;

    public UserLogoutCommand(ID userId) {
        this.userId = userId;
    }

    public boolean execute() {
        return true;
    }
}
