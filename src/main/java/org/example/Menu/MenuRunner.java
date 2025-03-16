package org.example.Menu;

import org.example.Commands.CommandInvoker;
import org.example.Commands.CommandType;
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

    public MenuRunner() {
        if (!initializeMemberData()) {
            throw new RuntimeException("MenuRunner::initializeMemberData()_failed!");
        }
    }

    public void run() {
        while (running) {
            printMenu();
            int choice = getUserChoice();
            if (choice == CommandType.EXIT.getId()) {
                if (!menu.executeCommand(CommandType.EXIT.getId())) {
                    throw new RuntimeException("MenuRunner::run_failed_to_execute_exit_command!");
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

    private void printMenu() {
        System.out.println("Please select a command:");
        for (CommandType type : CommandType.values()) {
            System.out.printf("%02d. %s%n", type.getId(), type.getDescription());
        }
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            return -1;
        }
    }

    private boolean initializeMemberData() {
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
