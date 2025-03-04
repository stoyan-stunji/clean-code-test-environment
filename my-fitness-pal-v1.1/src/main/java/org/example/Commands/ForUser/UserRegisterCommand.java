package org.example.Commands.ForUser;

import org.example.Commands.Command;
import org.example.User.Service.UserService;
import org.example.User.UserAccountDetails;

public class UserRegisterCommand implements Command {
    private final UserService userService;
    private final int userId;
    private final UserAccountDetails userAccountDetails;

    public UserRegisterCommand(UserService userService, int userId, UserAccountDetails userAccountDetails) {
        this.userService = userService;
        this.userId = userId;
        this.userAccountDetails = userAccountDetails;
    }

    public boolean execute() {
        userService.createUser(userId, userAccountDetails);
        return true;
    }
}
