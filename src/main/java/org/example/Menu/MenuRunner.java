package org.example.Menu;

import org.example.Commands.CommandInvoker;
import org.example.Item.Bank.ItemFileBankImpl;
import org.example.Item.Items.Food;
import org.example.Item.Items.Water;
import org.example.Item.Utility.ItemUtilityImpl;
import org.example.User.Bank.UserFileBankImpl;
import org.example.User.Utils.UserUtils;
import org.example.User.Utils.UserUtilsImpl;

import java.util.Scanner;

public class MenuRunner {
    private final Scanner scanner = new Scanner(System.in);
    private Menu menu;
    private boolean running = true;
    private static final int EXIT_CHOICE = 8;

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
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Add Water");
        System.out.println("4. Check Water Intake");
        System.out.println("5. Create Food");
        System.out.println("6. View All Food");
        System.out.println("7. Logout");
        System.out.println(EXIT_CHOICE + ". Exit");
        System.out.print("Enter your choice: ");
    }

    private boolean initializeMemberData()
    {
        CommandInvoker invoker = new CommandInvoker();
        UserFileBankImpl userRepository = new UserFileBankImpl();
        UserUtils userService = new UserUtilsImpl(userRepository);
        Water water = new Water(null, null, null);
        Food food = new Food(null, null, null, null, null, null, null, null, null);
        ItemFileBankImpl<Water> waterBank = new ItemFileBankImpl<Water>(water);
        ItemUtilityImpl<Water> waterUtility = new ItemUtilityImpl<>(waterBank);
        ItemFileBankImpl<Food> foodBank = new ItemFileBankImpl<Food>(food);
        ItemUtilityImpl<Food> foodUtility = new ItemUtilityImpl<>(foodBank);

        menu = new Menu(scanner, invoker, userService, waterUtility, waterBank, foodUtility, foodBank);
        return true;
    }
}
