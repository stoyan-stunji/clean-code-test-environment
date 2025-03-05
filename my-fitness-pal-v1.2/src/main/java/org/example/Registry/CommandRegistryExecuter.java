package org.example.Registry;

import org.example.Commands.CommandInvoker;
import org.example.Languages.LanguageContext;
import org.example.Languages.Strategies.EnglishStrategy;
import org.example.LogHandler.LogHandler;
import org.example.User.Repository.UserRepositoryImplementation;
import org.example.User.Service.UserService;
import org.example.User.Service.UserServiceImplementation;
import org.example.Water.Repository.WaterRepositoryImplementation;
import org.example.Water.Service.WaterService;
import org.example.Water.Service.WaterServiceImplementation;

import java.util.Scanner;
import java.util.logging.Logger;

public class CommandRegistryExecuter
{
    private final Scanner scanner = new Scanner(System.in);
    private final LanguageContext languageContext;
    private final CommandRegistry commandRegistry;
    private boolean running = true;
    private final int EXIT_CHOICE = 6;

    public CommandRegistryExecuter()
    {
        UserRepositoryImplementation userRepository = new UserRepositoryImplementation();
        WaterRepositoryImplementation waterRepository = new WaterRepositoryImplementation();
        UserService userService = new UserServiceImplementation(userRepository);
        WaterService waterService = new WaterServiceImplementation(waterRepository);
        CommandInvoker invoker = new CommandInvoker();
        languageContext = new LanguageContext(new EnglishStrategy());
        LogHandler logger = new LogHandler();

        commandRegistry = new CommandRegistry(scanner, invoker, userService,
                waterService, waterRepository, null, languageContext, logger
        );
    }

    public void run()
    {
        while (running) {
            printMenu();
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                String invalidInputMessage = languageContext.getInvalidInput();
                System.out.println(invalidInputMessage);
                continue;
            }
            if (choice == EXIT_CHOICE) {
                running = false;
                String exitAppMessage = languageContext.getExitApplication();
                System.out.println(exitAppMessage);
            } else {
                commandRegistry.executeCommand(choice);
            }
        }
        scanner.close();
    }

    private void printMenu()
    {
        System.out.println(languageContext.getSelectCommand());
        System.out.println(languageContext.getRegisterUser());
        System.out.println(languageContext.getLoginUser());
        System.out.println(languageContext.getAddWater());
        System.out.println(languageContext.getCheckWaterIntake());
        System.out.println(languageContext.getLogoutUser());
        System.out.println(EXIT_CHOICE + languageContext.getExit());
        System.out.print(languageContext.getEnterYourChoice());
    }
}
