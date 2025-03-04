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

    public CommandRegistry(boolean userLoggedIn,
                           Scanner scanner,
                           CommandInvoker invoker,
                           UserService userService,
                           WaterService waterService,
                           WaterRepository waterRepository,
                           ID userId,
                           LanguageContext languageContext) {
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
        String exitingMessage = languageContext.getExiting();

        commands.put(1, this::registerUser);
        commands.put(2, this::loginUser);
        commands.put(3, this::addWater);
        commands.put(4, this::checkWaterIntake);
        commands.put(5, this::logoutUser);
        commands.put(6, () -> System.out.println(exitingMessage));
    }

    public void executeCommand(int commandId) {
        Runnable command = commands.get(commandId);
        runCommand(command);
    }

    private void runCommand(Runnable command) {
        if (command != null) {
            command.run();
        } else {
            String invalidCommandMessage = languageContext.getInvalidCommand();
            System.out.println(invalidCommandMessage);
        }
    }

    private void registerUser() {
        String username = inputUsername();
        String password = inputPassword();
        UserAccountDetails userDetails = new UserAccountDetails(username, password);
        int usedID = userId.generateId();

        Command command = new UserRegisterCommand(userService, usedID, userDetails);
        setCommand(command);
        executeCommand();
    }

    private void loginUser() {
        String username = inputUsername();
        String password = inputPassword();
        UserAccountDetails userDetails = new UserAccountDetails(username, password);
        int userID = userId.generateId();
        System.out.println(userID);
        Command command = new UserLoginCommand(userService, userID, userDetails);
        invoker.setCommand(command);

        if (checkIfEntryDataIsCorrect()) {
            userLoggedIn = true;
        }
    }

    private boolean checkIfEntryDataIsCorrect()
    {
        if (!invoker.executeCommand()) {
            String invalidEntryDataMessage = languageContext.getInvalidEntryData();
            System.out.println(invalidEntryDataMessage);
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

        Command command = new AddWaterCommand(waterService, userId, waterDetails);
        setCommand(command);
        executeCommand();
    }

    private int inputAmountOfWater() {
        String enterWaterAmountMessage = languageContext.getEnterWaterAmount();
        System.out.print(enterWaterAmountMessage);
        return Integer.parseInt(scanner.nextLine());
    }

    private void checkWaterIntake() {
        if (!checkIfUserIsLoggedIn()) {
            return;
        }

        String date = inputDate();
        Command command = new CheckWaterIntakeCommand(waterRepository, userId, date);
        setCommand(command);
        executeCommand();
    }

    private boolean checkIfUserIsLoggedIn() {
        if (userLoggedIn) {
            return true;
        }
        else {
            String loginToContinueMessage = languageContext.getPleaseLoginToContinue();
            System.out.println(loginToContinueMessage);
            return false;
        }
    }

    private String inputDate() {
        String enterDateMessage = languageContext.getEnterDate();
        System.out.print(enterDateMessage);
        return scanner.nextLine();
    }

    private void logoutUser() {
        Command command = new UserLogoutCommand(userId);
        setCommand(command);
        executeCommand();
        userLoggedIn = false;

        String logoutSuccessfulMessage = languageContext.getLogoutSuccessfully();
        System.out.println(logoutSuccessfulMessage);
    }

    private void setCommand(Command command) {
        invoker.setCommand(command);
    }

    private void executeCommand() {
        invoker.executeCommand();
    }
}
