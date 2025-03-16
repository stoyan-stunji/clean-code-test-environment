package org.example.Item.Items;

import org.example.Item.Item;
import org.example.Item.Items.FoodDetails.FoodMacros;
import org.example.Item.Items.FoodDetails.FoodServingDetails;
import org.example.Item.Items.FoodDetails.TimeOfDay;

public class Food extends Item {
    private final String name;
    private final String description;
    private final FoodServingDetails servingDetails;
    private final FoodMacros macros;

    private TimeOfDay timeOfDay = TimeOfDay.UNDEFINED;

    public Food(String username, String name, String description, FoodServingDetails servingDetails, FoodMacros macros) {
        super(username, "");
        this.name = name;
        this.description = description;
        this.servingDetails = servingDetails;
        this.macros = macros;
    }

    // TODO Fix the parsing;
    public static Food parseFromFileLine(String username, String line) {
        try {
            String[] parts = line.split(" ", 30);
            String name = parts[0];
            String numberOfServings = parts[1];
            String xSymbol = parts[2];
            String foodName = parts[3];
            String servingSize = parts[4];
            String calories = parts[5];
            String kcal = parts[6];
            String carbs = parts[7];
            String g1 = parts[8];
            String fats = parts[9];
            String g2 = parts[10];
            String protein = parts[11];
            String g3 = parts[12];

            FoodServingDetails servingDetails = new FoodServingDetails(servingSize, numberOfServings);
            FoodMacros macros = new FoodMacros(calories, carbs, fats, protein);

            return new Food(username, name, "", servingDetails, macros);
        } catch (Exception e) {
            throw new RuntimeException("Food::parseFromFileLine::parsing_error", e);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public FoodServingDetails getServingDetails() {
        return servingDetails;
    }

    public FoodMacros getMacros() {
        return macros;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public boolean setTimeOfDay(String timeOfDay) {
        switch (timeOfDay) {
            case "breakfast": this.timeOfDay = TimeOfDay.BREAKFAST; break;
            case "lunch": this.timeOfDay = TimeOfDay.LUNCH; break;
            case "snacks":  this.timeOfDay = TimeOfDay.SNACKS; break;
            case "dinner": this.timeOfDay = TimeOfDay.DINNER; break;
            default: this.timeOfDay = TimeOfDay.UNDEFINED;
        }
        return true;
    }

    public String formatForFileSaving() {
        String servingSize = servingDetails.servingSize();
        String calories = macros.calories();
        String carbs = macros.carbs();
        String fats = macros.fats();
        String protein = macros.protein();

        return String.format(
                "%s %s %s kcal %s g %s g %s g %s",
                name,
                servingSize,
                calories,
                carbs,
                fats,
                protein,
                description
        );
    }
}
