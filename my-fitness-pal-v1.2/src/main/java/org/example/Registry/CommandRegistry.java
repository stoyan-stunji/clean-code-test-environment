package org.example.Registry;

import org.example.Commands.Command;
import org.example.Commands.CommandInvoker;
import org.example.Commands.ForLogHandler.GetLogsCommand;
import org.example.Commands.ForUser.UserLoginCommand;
import org.example.Commands.ForUser.UserLogoutCommand;
import org.example.Commands.ForUser.UserRegisterCommand;
import org.example.Commands.ForWater.AddWaterCommand;
import org.example.Commands.ForWater.CheckWaterIntakeCommand;
import org.example.Languages.LanguageContext;
import org.example.LogHandler.LogHandler;
import org.example.User.Service.UserService;
import org.example.Water.Repository.WaterRepository;
import org.example.Water.Service.WaterService;
import org.example.Water.WaterDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class CommandRegistry {
    private final Map<Integer, Runnable> commands = new HashMap<>();
    private final Scanner scanner;
    private final CommandInvoker invoker;
    private final UserService userService;
    private final WaterService waterService;
    private final WaterRepository waterRepository;
    private String username;
    private final LanguageContext languageContext;
    private final LogHandler logger;

    public CommandRegistry(Scanner scanner,
                           CommandInvoker invoker,
                           UserService userService,
                           WaterService waterService,
                           WaterRepository waterRepository,
                           String username,
                           LanguageContext languageContext,
                           LogHandler logger) {
        this.scanner = scanner;
        this.invoker = invoker;
        this.userService = userService;
        this.waterService = waterService;
        this.waterRepository = waterRepository;
        this.username = username;
        this.languageContext = languageContext;
        this.logger = logger;
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
        commands.put(7, this::getLogs);
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

        Command command = new UserRegisterCommand(userService, username, password, logger);
        setCommand(command);
        executeCommand();
    }

    private void loginUser() {
        String username = inputUsername();
        String password = inputPassword();

        Command command = new UserLoginCommand(userService, username, password, logger);
        invoker.setCommand(command);

        if (checkIfEntryDataIsCorrect()) {
            this.username = username;
        }

    }

    private boolean checkIfEntryDataIsCorrect()
    {
        try {
            invoker.executeCommand();
        }
        catch (Exception e) {
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

        Command command = new AddWaterCommand(waterService, username, waterDetails, logger);
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
        Command command = new CheckWaterIntakeCommand(waterRepository, username, date, logger);
        setCommand(command);
        executeCommand();
    }

    private boolean checkIfUserIsLoggedIn() {
        if (username == null) {
            String loginToContinueMessage = languageContext.getPleaseLoginToContinue();
            System.out.println(loginToContinueMessage);
            return false;
        }
        else {
            return true;
        }
    }

    public void getLogs() {
        Command command = new GetLogsCommand(logger);
        setCommand(command);
        executeCommand();
    }

    private String inputDate() {
        String enterDateMessage = languageContext.getEnterDate();
        System.out.print(enterDateMessage);
        return scanner.nextLine();
    }

    private void logoutUser() {
        Command command = new UserLogoutCommand(username, logger);
        setCommand(command);
        executeCommand();
        username = null;

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
