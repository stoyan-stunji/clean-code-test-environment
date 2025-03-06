package org.example.Dispatcher;

import org.example.Commands.CommandInvoker;
import org.example.User.Bank.UserFileBankImpl;
import org.example.User.Service.UserService;
import org.example.User.Service.UserServiceImpl;
import org.example.Water.Bank.WaterFileBankImpl;
import org.example.Water.Service.WaterService;
import org.example.Water.Service.WaterServiceImpl;

import java.util.Scanner;

public class CommandDispatcherRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final CommandDispatcher commandRegistry;
    private boolean running = true;
    private static final int EXIT_CHOICE = 6;

    public CommandDispatcherRunner() {
        UserFileBankImpl userRepository = new UserFileBankImpl();
        WaterFileBankImpl waterRepository = new WaterFileBankImpl();
        UserService userService = new UserServiceImpl(userRepository);
        WaterService waterService = new WaterServiceImpl(waterRepository);
        CommandInvoker invoker = new CommandInvoker();

        commandRegistry = new CommandDispatcher(scanner, invoker, userService, waterService, waterRepository, null);
    }

    public void run() {
        while (running) {
            printMenu();
            int choice = getUserChoice();
            if (choice == EXIT_CHOICE) {
                if (!commandRegistry.executeCommand(EXIT_CHOICE)) {
                    throw new RuntimeException("Failed to execute exit command.");
                }
                running = false;
            } else {
                if (!commandRegistry.executeCommand(choice)) {
                    System.out.println("Invalid command. Please try again.");
                }
            }
        }
        scanner.close();
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number between 1 and " + EXIT_CHOICE + ".");
            return -1;
        }
    }

    private void printMenu() {
        System.out.println("Please select a command:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Add Water");
        System.out.println("4. Check Water Intake");
        System.out.println("5. Logout");
        System.out.println(EXIT_CHOICE + ". Exit");
        System.out.print("Enter your choice: ");
    }
}
