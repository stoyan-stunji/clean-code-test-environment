package org.example.Commands.ForUser;

import org.example.Commands.Command;
import org.example.User.Service.UserService;
import org.example.User.User;
import org.example.User.UserAccountDetails;

public class UserLoginCommand implements Command {
    private final UserService userService;
    private final int userId;
    private final UserAccountDetails userAccountDetails;

    public UserLoginCommand(UserService userService, int userId, UserAccountDetails userAccountDetails) {
        this.userService = userService;
        this.userId = userId;
        this.userAccountDetails = userAccountDetails;
    }

    public boolean execute() {
        User user = userService.getUserById(userId);
        return user != null
                && user.getUsername().equals(userAccountDetails.username())
                && user.getPassword().equals(userAccountDetails.password());
    }
}
