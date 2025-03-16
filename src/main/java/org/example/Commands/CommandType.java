package org.example.Commands;

public enum CommandType {
    REGISTER(1, "Register"),
    LOGIN(2, "Login"),
    LOGOUT(3, "Logout"),
    EXIT(4, "Exit"),
    DRINK_WATER(5, "Drink Water"),
    CHECK_WATER_INTAKE(6, "Check Water Intake"),
    CREATE_FOOD(7, "Create Food"),
    VIEW_ALL_FOODS(8, "View All Foods"),
    LOG_FOOD(9, "Log Food"),
    VIEW_LOGGED_FOODS(10, "View Logged Foods");

    private final int id;
    private final String description;

    CommandType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static CommandType fromId(int id) {
        for (CommandType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}

