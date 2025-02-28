package org.example.Executer;

import org.example.Commands.Command;
import org.example.Commands.CommandInvoker;
import org.example.Commands.ForUser.UserLoginCommand;
import org.example.Commands.ForUser.UserLogoutCommand;
import org.example.Commands.ForUser.UserRegisterCommand;
import org.example.Commands.ForWater.AddWaterCommand;
import org.example.Commands.ForWater.CheckWaterIntakeCommand;
import org.example.ID.ID;
import org.example.Languages.LanguageContext;
import org.example.User.Service.UserService;
import org.example.User.UserAccountDetails;
import org.example.Water.Repository.WaterRepository;
import org.example.Water.Service.WaterService;
import org.example.Water.WaterDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandRegistry {
    private final Map<Integer, Runnable> commands = new HashMap<>();
    private boolean userLoggedIn;
    private final Scanner scanner;
    private final CommandInvoker invoker;
    private final UserService userService;
    private final WaterService waterService;
    private final WaterRepository waterRepository;
    private final ID userId;
    private final LanguageContext languageContext;

    public CommandRegistry(boolean userLoggedIn, Scanner scanner, CommandInvoker invoker,
                           UserService userService, WaterService waterService,
                           WaterRepository waterRepository, ID userId, LanguageContext languageContext) {
        this.userLoggedIn = userLoggedIn;
        this.scanner = scanner;
        this.invoker = invoker;
        this.userService = userService;
        this.waterService = waterService;
        this.waterRepository = waterRepository;
        this.userId = userId;
        this.languageContext = languageContext;
        initializeCommands();
    }

    private void initializeCommands() {
        commands.put(1, this::registerUser);
        commands.put(2, this::loginUser);
        commands.put(3, this::addWater);
        commands.put(4, this::checkWaterIntake);
        commands.put(5, this::logoutUser);
        commands.put(6, () -> System.out.println(languageContext.getExiting()));
    }

    public void executeCommand(int commandId) {
        Runnable command = commands.get(commandId);
        runCommand(command);
    }

    private void runCommand(Runnable command) {
        if (command != null) {
            command.run();
        } else {
            System.out.println(languageContext.getInvalidCommand());
        }
    }

    private void registerUser() {
        String username = inputUsername();
        String password = inputPassword();
        UserAccountDetails userDetails = new UserAccountDetails(username, password);
        setCommandAndExecute(new UserRegisterCommand(userService, userId.generateId(), userDetails));
    }

    private void loginUser() {
        String username = inputUsername();
        String password = inputPassword();
        UserAccountDetails userDetails = new UserAccountDetails(username, password);
        invoker.setCommand(new UserLoginCommand(userService, userId.getCurrentId(), userDetails));
        if (checkIfEntryDataIsCorrect()) {
            userLoggedIn = true;
        }
    }

    private boolean checkIfEntryDataIsCorrect()
    {
        if (!invoker.executeCommand()) {
            System.out.println(languageContext.getInvalidEntryData());
            return false;
        }
        return true;
    }

    private String inputUsername()
    {
        System.out.print(languageContext.getEnterUsername());
        return scanner.nextLine();
    }

    private String inputPassword()
    {
        System.out.print(languageContext.getEnterPassword());
        return scanner.nextLine();
    }

    private void addWater() {
        if (!checkIfUserIsLoggedIn()) {
            return;
        }
        int amount = inputAmountOfWater();
        String date = inputDate();
        WaterDetails waterDetails = new WaterDetails(amount, date);
        setCommandAndExecute(new AddWaterCommand(waterService, userId, waterDetails));
    }

    private int inputAmountOfWater() {
        System.out.print(languageContext.getEnterWaterAmount());
        return Integer.parseInt(scanner.nextLine());
    }

    private void checkWaterIntake() {
        if (!checkIfUserIsLoggedIn()) {
            return;
        }
        String date = inputDate();
        setCommandAndExecute(new CheckWaterIntakeCommand(waterRepository, userId, date));
    }

    private boolean checkIfUserIsLoggedIn() {
        if (userLoggedIn) {
            return true;
        }
        else {
            System.out.println(languageContext.getPleaseLoginToContinue());
            return false;
        }
    }

    private String inputDate() {
        System.out.print(languageContext.getEnterDate());
        return scanner.nextLine();
    }

    private void logoutUser() {
        setCommandAndExecute(new UserLogoutCommand(userId));
        userLoggedIn = false;
        System.out.println(languageContext.getLogoutSuccessfully());
    }

    private void setCommandAndExecute(Command command)
    {
        invoker.setCommand(command);
        invoker.executeCommand();
    }
}
