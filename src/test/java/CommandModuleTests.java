import org.example.Commands.*;
import org.example.Commands.FoodCommands.*;
import org.example.Commands.UserCommands.*;
import org.example.Commands.WaterCommands.*;
import org.example.Item.Items.Food;
import org.example.Item.Items.Water;
import org.example.Item.Service.Food.FoodService;
import org.example.Item.Service.Water.WaterService;
import org.example.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO Make it work

/*
public class CommandTests {
    @Test
    public void testUserRegisterCommand() {
        UserService userService = new UserService();
        Command registerCommand = new UserRegisterCommand(userService, "testUser", "password");
        assertTrue(registerCommand.execute());
    }

    @Test
    public void testUserLoginCommand() {
        UserService userService = new UserService();
        userService.createUser("testUser", "password");
        Command loginCommand = new UserLoginCommand(userService, "testUser", "password");
        assertTrue(loginCommand.execute());
    }

    @Test
    public void testUserLogoutCommand() {
        Command logoutCommand = new UserLogoutCommand();
        assertTrue(logoutCommand.execute());
    }

    @Test
    public void testExitCommand() {
        Command exitCommand = new ExitCommand();
        assertTrue(exitCommand.execute());
    }

    @Test
    public void testDrinkWaterCommand() {
        WaterService waterService = new WaterService();
        Water water = new Water("testUser", 500);
        Command drinkWaterCommand = new DrinkWaterCommand(waterService, water);
        assertTrue(drinkWaterCommand.execute());
    }

    @Test
    public void testCheckWaterCommand() {
        WaterService waterService = new WaterService();
        Command checkWaterCommand = new CheckWaterCommand(waterService, "testUser", "2025-03-16");
        assertTrue(checkWaterCommand.execute());
    }

    @Test
    public void testCreateFoodCommand() {
        FoodService foodService = new FoodService();
        Food food = new Food("Apple", 95);
        Command createFoodCommand = new CreateFoodCommand(foodService, food);
        assertTrue(createFoodCommand.execute());
    }

    @Test
    public void testViewAllFoodsCommand() {
        FoodService foodService = new FoodService();
        Command viewAllFoodsCommand = new ViewAllFoodsCommand(foodService);
        assertTrue(viewAllFoodsCommand.execute());
    }

    @Test
    public void testLogFoodCommand() {
        FoodService foodService = new FoodService();
        Command logFoodCommand = new LogFoodCommand(foodService, 1, "testUser", "2025-03-16");
        assertTrue(logFoodCommand.execute());
    }

    @Test
    public void testViewFoodsLogged() {
        FoodService foodService = new FoodService();
        Command viewFoodsLogged = new ViewFoodsLogged(foodService, "testUser", "2025-03-16");
        assertTrue(viewFoodsLogged.execute());
    }

    @Test
    public void testUserLoginCommandInvalid() {
        UserService userService = new UserService();
        Command loginCommand = new UserLoginCommand(userService, "nonExistentUser", "wrongPassword");
        assertFalse(loginCommand.execute());
    }

    @Test
    public void testCreateFoodCommandFailure() {
        FoodService foodService = new FoodService();
        Food food = new Food(null, 0);
        Command createFoodCommand = new CreateFoodCommand(foodService, food);
        assertThrows(RuntimeException.class, createFoodCommand::execute);
    }

    @Test
    public void testDrinkWaterCommandFailure() {
        WaterService waterService = new WaterService();
        Water water = new Water(null, 0);
        Command drinkWaterCommand = new DrinkWaterCommand(waterService, water);
        assertThrows(RuntimeException.class, drinkWaterCommand::execute);
    }
}
*/
