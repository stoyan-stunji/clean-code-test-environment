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

    private final TimeOfDay timeOfDay = TimeOfDay.UNDEFINED;
    private final String getDateOfEating;

    public Food(String username, String name, String description, FoodServingDetails servingDetails, FoodMacros macros, String getDateOfEating) {
        super(username);
        this.name = name;
        this.description = description;
        this.servingDetails = servingDetails;
        this.macros = macros;
        this.getDateOfEating = getDateOfEating;
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

    public String getDateOfEating() {
        return getDateOfEating;
    }

    public String formatForFileSaving() {
        int servingSize = servingDetails.servingSize();
        int numberOfServings = servingDetails.numberOfServings();
        double calories = macros.calories();
        double carbs = macros.carbs();
        double fats = macros.fats();
        double protein = macros.protein();

        return String.format(
                "%s (%dg; %.1f kcal; %.1fg, %.1fg, %.1fg) %s",
                name,
                servingSize,
                calories * numberOfServings,
                carbs * numberOfServings,
                fats * numberOfServings,
                protein * numberOfServings,
                description
        );
    }
}
