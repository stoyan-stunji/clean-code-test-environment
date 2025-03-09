package org.example.Menu;

import org.example.Commands.Command;
import org.example.Commands.CommandInvoker;
import org.example.Commands.ConcreteCommands.ItemCommands.CheckItemCommand;
import org.example.Commands.ConcreteCommands.ItemCommands.CreateItemCommand;
import org.example.Commands.ConcreteCommands.UserCommands.UserLoginCommand;
import org.example.Commands.ConcreteCommands.UserCommands.UserLogoutCommand;
import org.example.Commands.ConcreteCommands.UserCommands.UserRegisterCommand;
import org.example.Item.Bank.ItemBank;
import org.example.Item.Items.Food;
import org.example.Item.Items.Water;
import org.example.Item.Utility.ItemUtility;
import org.example.User.Utils.UserUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<Integer, Boolean> commands = new HashMap<>();
    private final Scanner scanner;
    private final CommandInvoker invoker;
    private final UserUtils userService;
    private final ItemUtility<Water> waterUtility;
    private final ItemBank<Water> waterBank;
    private final ItemUtility<Food> foodUtility;
    private final ItemBank<Food> foodBank;
    private String username;

    public Menu(Scanner scanner, CommandInvoker invoker, UserUtils userService,
                ItemUtility<Water> waterUtility, ItemBank<Water> waterBank,
                ItemUtility<Food> foodUtility, ItemBank<Food> foodBank) {
        this.scanner = scanner;
        this.invoker = invoker;
        this.userService = userService;
        this.waterUtility = waterUtility;
        this.waterBank = waterBank;
        this.foodUtility = foodUtility;
        this.foodBank = foodBank;
        this.username = null;
    }

    public boolean executeCommand(int commandId) {
        return switch (commandId) {
            case 1 -> registerUser();
            case 2 -> loginUser();
            case 3 -> addWater();
            case 4 -> checkWaterIntake();
            case 5 -> createFood();
            case 6 -> viewAllFood();
            case 7 -> logoutUser();
            case 8 -> exitApplication();
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
        Water water = new Water(username, amount, date);
        Command command = new CreateItemCommand<>(waterUtility, username, water);
        invoker.setCommand(command);
        return invoker.executeCommand();
    }

    private boolean checkWaterIntake() {
        if (!checkIfUserIsLoggedIn()) return false;
        String date = inputDate();
        Command command = new CheckItemCommand<Water>(waterBank, username, date);
        invoker.setCommand(command);
        return invoker.executeCommand();
    }

    private boolean createFood() {
        if (!checkIfUserIsLoggedIn()) return false;
        String name = inputName();
        String description = inputDescription();
        Integer servingsSize = inputServingSize();
        Integer numberOfServings = inputNumberOfServings();
        Double calories = inputCalories();
        Double carbs = inputCarbs();
        Double fats = inputFats();
        Double proteins = inputProteins();
        Food food = new Food(username, name, description, servingsSize, numberOfServings, calories, carbs, fats, proteins);
        Command command = new CreateItemCommand<>(foodUtility, username, food);
        invoker.setCommand(command);
        return invoker.executeCommand();
    }

    private boolean viewAllFood() {
        if (!checkIfUserIsLoggedIn()) return false;
        Command command = new CheckItemCommand<Food>(foodBank, username, null);
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

    private String inputName() {
        System.out.print("Enter name: ");
        return scanner.nextLine();
    }

    private String inputDescription() {
        System.out.print("Enter description: ");
        return scanner.nextLine();
    }

    private Integer inputServingSize() {
        System.out.print("Enter serving size (g.): ");
        return Integer.parseInt(scanner.nextLine());
    }

    private Integer inputNumberOfServings() {
        System.out.print("Enter servings per container: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private Double inputCalories() {
        System.out.print("Enter calories (kcal): ");
        return Double.parseDouble(scanner.nextLine());
    }

    private Double inputCarbs() {
        System.out.print("Enter carbs (g.): ");
        return Double.parseDouble(scanner.nextLine());
    }

    private Double inputFats() {
        System.out.print("Enter fat (g.): ");
        return Double.parseDouble(scanner.nextLine());
    }

    private Double inputProteins() {
        System.out.print("Enter proteins (g.): ");
        return Double.parseDouble(scanner.nextLine());
    }
}
