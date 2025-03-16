package org.example.Menu;

import org.example.Commands.Command;
import org.example.Commands.CommandInvoker;
import org.example.Commands.CommandType;
import org.example.Commands.FoodCommands.CreateFoodCommand;
import org.example.Commands.FoodCommands.LogFoodCommand;
import org.example.Commands.FoodCommands.ViewAllFoodsCommand;
import org.example.Commands.FoodCommands.ViewFoodsLogged;
import org.example.Commands.UserCommands.ExitCommand;
import org.example.Commands.UserCommands.UserLoginCommand;
import org.example.Commands.UserCommands.UserLogoutCommand;
import org.example.Commands.UserCommands.UserRegisterCommand;
import org.example.Commands.WaterCommands.CheckWaterCommand;
import org.example.Commands.WaterCommands.DrinkWaterCommand;
import org.example.Item.Items.Food;
import org.example.Item.Items.FoodDetails.FoodMacros;
import org.example.Item.Items.FoodDetails.FoodServingDetails;
import org.example.Item.Items.Water;
import org.example.Item.Service.Food.FoodService;
import org.example.Item.Service.Water.WaterService;
import org.example.User.Service.UserService;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final CommandInvoker invoker;
    private final UserService userService;
    private final WaterService waterService;
    private final FoodService foodService;
    private String username = "";

    public Menu(Scanner scanner, CommandInvoker invoker, UserService userService, WaterService waterService, FoodService foodService) {
        this.scanner = scanner;
        this.invoker = invoker;
        this.userService = userService;
        this.waterService = waterService;
        this.foodService = foodService;
    }

    public boolean executeCommand(int commandId) {
        CommandType commandType = CommandType.fromId(commandId);
        if (commandType == null) {
            System.out.println("Invalid command!");
            return false;
        }

        return switch (commandType) {
            case REGISTER -> registerUser();
            case LOGIN -> loginUser();
            case LOGOUT -> logoutUser();
            case EXIT -> exitApplication();
            case DRINK_WATER -> drinkWater();
            case CHECK_WATER_INTAKE -> checkWaterIntake();
            case CREATE_FOOD -> createFood();
            case VIEW_ALL_FOODS -> viewAllFoods();
            case LOG_FOOD -> logFood();
            case VIEW_LOGGED_FOODS -> viewLoggedFoods();
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

    private boolean logoutUser() {
        if (!checkIfUserIsLoggedIn()) return false;
        Command command = new UserLogoutCommand();
        invoker.setCommand(command);
        boolean success = invoker.executeCommand();
        if (success) {
            System.out.println("Logged out successfully!");
            username = "";
        }
        return success;
    }

    private boolean exitApplication() {
        Command command = new ExitCommand();
        invoker.setCommand(command);
        System.out.println("Exiting application...");
        return invoker.executeCommand();
    }

    private boolean drinkWater() {
        if (!checkIfUserIsLoggedIn()) return false;
        String amount = inputAmountOfWater();
        String dateOfDrinking = inputDate();
        Water water = new Water(username, amount, dateOfDrinking);
        Command command = new DrinkWaterCommand(waterService, water);
        invoker.setCommand(command);
        boolean success = invoker.executeCommand();
        if (success) {
            System.out.println("Successfully logged water intake on " + dateOfDrinking);
        }
        return success;
    }

    private boolean checkWaterIntake() {
        if (!checkIfUserIsLoggedIn()) return false;
        String date = inputDate();
        Command command = new CheckWaterCommand(waterService, username, date);
        invoker.setCommand(command);
        return invoker.executeCommand();
    }

    private boolean createFood() {
        if (!checkIfUserIsLoggedIn()) return false;
        Food food = gatherFoodDetails();
        Command command = new CreateFoodCommand(foodService, food);
        invoker.setCommand(command);
        return invoker.executeCommand();
    }

    private boolean viewAllFoods() {
        if (!checkIfUserIsLoggedIn()) return false;
        Command command = new ViewAllFoodsCommand(foodService);
        invoker.setCommand(command);
        return invoker.executeCommand();
    }

    private boolean logFood() {
        if (!checkIfUserIsLoggedIn()) return false;
        String dateOfEating = inputDate();
        String timeOfDay = inputTimeOfDay();
        int id = inputId();
        Command command = new LogFoodCommand(foodService, id, username, dateOfEating);
        invoker.setCommand(command);
        boolean success = invoker.executeCommand();
        if (success) {
            System.out.println("Successfully logged food on! " + dateOfEating);
        }
        return success;
    }

    private boolean viewLoggedFoods() {
        if (!checkIfUserIsLoggedIn()) return false;
        String dateOfEating = inputDate();
        Command command = new ViewFoodsLogged(foodService, username, dateOfEating);
        invoker.setCommand(command);
        return invoker.executeCommand();
    }

    private Food gatherFoodDetails() {
        String name = inputName();
        String description = inputDescription();
        FoodServingDetails foodServingDetails = gatherServingDetails();
        FoodMacros foodMacros = gatherFoodMacros();
        return new Food(username, name, description, foodServingDetails, foodMacros);
    }

    private FoodServingDetails gatherServingDetails() {
        String servingsSize = inputServingSize();
        String numberOfServings = inputNumberOfServings();
        return new FoodServingDetails(servingsSize, numberOfServings);
    }

    private FoodMacros gatherFoodMacros() {
        String calories = inputCalories();
        String carbs = inputCarbs();
        String fats = inputFats();
        String proteins = inputProteins();
        return new FoodMacros(calories, carbs, fats, proteins);
    }

    private String inputUsername() {
        System.out.print("Enter username: ");
        return scanner.nextLine();
    }

    private String inputPassword() {
        System.out.print("Enter password: ");
        return scanner.nextLine();
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
        if (username.isEmpty()) {
            System.out.println("Please log in to continue!");
            return false;
        }
        return true;
    }

    private String inputAmountOfWater() {
        System.out.print("Enter amount of water (ml.): ");
        return scanner.nextLine();
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

    private String inputServingSize() {
        System.out.print("Enter serving size (g.): ");
        return scanner.nextLine();
    }

    private String inputNumberOfServings() {
        System.out.print("Enter servings per container: ");
        return scanner.nextLine();
    }

    private String inputCalories() {
        System.out.print("Enter calories (kcal): ");
        return scanner.nextLine();
    }

    private String inputCarbs() {
        System.out.print("Enter carbs (g.): ");
        return scanner.nextLine();
    }

    private String inputFats() {
        System.out.print("Enter fat (g.): ");
        return scanner.nextLine();
    }

    private String inputProteins() {
        System.out.print("Enter proteins (g.): ");
        return scanner.nextLine();
    }

    private int inputId() {
        System.out.print("Enter ID: ");
        return scanner.nextInt();
    }

    private String inputTimeOfDay() {
        System.out.print("Enter [breakfast, lunch, snacks, dinner]: ");
        return scanner.nextLine();
    }
}
