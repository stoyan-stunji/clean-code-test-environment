package org.example.Menu;

import org.example.Commands.CommandInvoker;
import org.example.Item.Repository.Food.FoodRepoFileImpl;
import org.example.Item.Repository.Water.WaterRepoFileImpl;
import org.example.Item.Service.Food.FoodService;
import org.example.Item.Service.Food.FoodServiceImpl;
import org.example.Item.Service.Water.WaterService;
import org.example.Item.Service.Water.WaterServiceImpl;
import org.example.User.Repository.UserFileRepoImpl;
import org.example.User.Service.UserService;
import org.example.User.Service.UserServiceImpl;

import java.util.Scanner;

public class MenuRunner {
    private final Scanner scanner = new Scanner(System.in);
    private Menu menu;
    private boolean running = true;
    private static final int EXIT_CHOICE = 4;

    public MenuRunner() {
        if(!initializeMemberData()) {
            throw new RuntimeException("MenuRunner::initializeMemberData()_failed!");
        }
    }

    public void run() {
        while (running) {
            printMenu();
            int choice = getUserChoice();
            if (choice == EXIT_CHOICE) {
                if (!menu.executeCommand(EXIT_CHOICE)) {
                    throw new RuntimeException("MenuRunner::run()_failed_to_execute_exit_command!");
                }
                running = false;
            } else {
                if (!menu.executeCommand(choice)) {
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
        System.out.println("01. Register");
        System.out.println("02. Login");
        System.out.println("03. Logout");
        System.out.println("04. Exit");
        System.out.println("05. Drink Water");
        System.out.println("06. Check Water Intake");
        System.out.println("07. Create Food");
        System.out.println("08. View All Foods");
        System.out.println("09. Log Food");
        System.out.println("10. View Logged Foods");
        System.out.print("Enter your choice: ");
    }

    private boolean initializeMemberData()
    {
        CommandInvoker invoker = new CommandInvoker();
        UserFileRepoImpl userRepository = new UserFileRepoImpl();
        UserService userService = new UserServiceImpl(userRepository);
        WaterRepoFileImpl waterBank = new WaterRepoFileImpl();
        WaterService waterUtility = new WaterServiceImpl(waterBank);
        FoodRepoFileImpl foodBank = new FoodRepoFileImpl();
        FoodService foodUtility = new FoodServiceImpl(foodBank);

        menu = new Menu(scanner, invoker, userService, waterUtility, foodUtility);
        return true;
    }
}