package org.example.Executer;

import org.example.Commands.CommandInvoker;
import org.example.ID.ID;
import org.example.Languages.LanguageContext;
import org.example.Languages.Strategies.EnglishStrategy;
import org.example.User.Repository.UserRepositoryImplementation;
import org.example.User.Service.UserService;
import org.example.User.Service.UserServiceImplementation;
import org.example.Water.Repository.WaterRepositoryImplementation;
import org.example.Water.Service.WaterService;
import org.example.Water.Service.WaterServiceImplementation;

import java.util.Scanner;

public class CommandRegistryExecuter
{
    private final Scanner scanner = new Scanner(System.in);
    private final LanguageContext languageContext;

    CommandRegistry commandRegistry;
    boolean running = true;
    private final int EXIT_CHOICE = 6;

    public CommandRegistryExecuter()
    {
        UserRepositoryImplementation userRepository = new UserRepositoryImplementation();
        WaterRepositoryImplementation waterRepository = new WaterRepositoryImplementation();
        UserService userService = new UserServiceImplementation(userRepository);
        WaterService waterService = new WaterServiceImplementation(waterRepository);
        ID userId = new ID();
        CommandInvoker invoker = new CommandInvoker();
        languageContext = new LanguageContext(new EnglishStrategy());

        commandRegistry = new CommandRegistry(
                false, scanner, invoker, userService,
                waterService, waterRepository, userId, languageContext
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
                System.out.println(languageContext.getInvalidInput());
                continue;
            }
            if (choice == EXIT_CHOICE) {
                running = false;
                System.out.println(languageContext.getExitApplication());
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
