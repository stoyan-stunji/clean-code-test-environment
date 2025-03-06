package org.example.Dispatcher;

import org.example.Commands.Command;
import org.example.Commands.CommandInvoker;
import org.example.Commands.ForUser.UserLoginCommand;
import org.example.Commands.ForUser.UserLogoutCommand;
import org.example.Commands.ForUser.UserRegisterCommand;
import org.example.Commands.ForWater.AddWaterCommand;
import org.example.Commands.ForWater.CheckWaterIntakeCommand;
import org.example.User.Service.UserService;
import org.example.Water.Bank.WaterBank;
import org.example.Water.Service.WaterService;
import org.example.Water.WaterDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandDispatcher {
    private final Map<Integer, Boolean> commands = new HashMap<>();
    private final Scanner scanner;
    private final CommandInvoker invoker;
    private final UserService userService;
    private final WaterService waterService;
    private final WaterBank waterRepository;
    private String username;

    public CommandDispatcher(Scanner scanner,
                             CommandInvoker invoker,
                             UserService userService,
                             WaterService waterService,
                             WaterBank waterRepository,
                             String username) {
        this.scanner = scanner;
        this.invoker = invoker;
        this.userService = userService;
        this.waterService = waterService;
        this.waterRepository = waterRepository;
        this.username = username;
    }

    public boolean executeCommand(int commandId) {
        return switch (commandId) {
            case 1 -> registerUser();
            case 2 -> loginUser();
            case 3 -> addWater();
            case 4 -> checkWaterIntake();
            case 5 -> logoutUser();
            case 6 -> exitApplication();
            default -> {
                System.out.println("Invalid command!");
                yield false;
            }
        };
    }

    private boolean registerUser() {
        String username = inputUsername();
        String password = inputPassword();
        Command command = new UserRegisterCommand(userService, username, password);
        invoker.setCommand(command);
        boolean success = invoker.executeCommand();
        if (success) {
            System.out.println("Registered successfully!");
        }
        return success;
    }

    private boolean loginUser() {
        String username = inputUsername();
        String password = inputPassword();
        Command command = new UserLoginCommand(userService, username, password);
        invoker.setCommand(command);
        boolean success = checkIfEntryDataIsCorrect();
        if (success) {
            this.username = username;
            System.out.println("Logged in successfully!");
        }
        return success;
    }

    private boolean addWater() {
        if (!checkIfUserIsLoggedIn()) return false;
        int amount = inputAmountOfWater();
        String date = inputDate();
        WaterDetails waterDetails = new WaterDetails(amount, date);
        Command command = new AddWaterCommand(waterService, username, waterDetails);
        invoker.setCommand(command);
        return invoker.executeCommand();
    }

    private boolean checkWaterIntake() {
        if (!checkIfUserIsLoggedIn()) return false;
        String date = inputDate();
        Command command = new CheckWaterIntakeCommand(waterRepository, username, date);
        invoker.setCommand(command);
        return invoker.executeCommand();
    }

    private boolean logoutUser() {
        Command command = new UserLogoutCommand(username);
        invoker.setCommand(command);
        boolean success = invoker.executeCommand();
        if (success) {
            System.out.println("Logged out successfully!");
            username = null;
        }
        return success;
    }

    private boolean exitApplication() {
        System.out.println("Exiting application...");
        return true;
    }

    private boolean checkIfEntryDataIsCorrect() {
        try {
            return invoker.executeCommand();
        } catch (Exception e) {
            System.out.println("Invalid entry data!");
            return false;
        }
    }

    private boolean checkIfUserIsLoggedIn() {
        if (username == null) {
            System.out.println("Please log in to continue!");
            return false;
        }
        return true;
    }

    private String inputUsername() {
        System.out.print("Enter username: ");
        return scanner.nextLine();
    }

    private String inputPassword() {
        System.out.print("Enter password: ");
        return scanner.nextLine();
    }

    private int inputAmountOfWater() {
        System.out.print("Enter amount of water (ml.): ");
        return Integer.parseInt(scanner.nextLine());
    }

    private String inputDate() {
        System.out.print("Enter date (YYYY-MM-DD): ");
        return scanner.nextLine();
    }
}
