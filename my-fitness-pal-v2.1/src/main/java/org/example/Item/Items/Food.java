package org.example.Item.Items;

import org.example.Item.Item;

public class Food extends Item {
    private final String name;
    private final String description;
    private final Integer servingSize;
    private final Integer numberOfServings;
    private final Double calories;
    private final Double carbs;
    private final Double fats;
    private final Double protein;

    public Food(String username, String name, String description,
                Integer servingSize, Integer numberOfServings,
                Double calories, Double carbs, Double fats, Double protein) {
        super(username);
        this.name = name;
        this.description = description;
        this.servingSize = servingSize;
        this.numberOfServings = numberOfServings;

        this.calories = calories;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getServingSize() {
        return servingSize;
    }

    public Integer getNumberOfServings() {
        return numberOfServings;
    }

    public Double getCalories() {
        return calories * numberOfServings;
    }

    public Double getCarbs() {
        return carbs * numberOfServings;
    }

    public Double getFats() {
        return fats * numberOfServings;
    }

    public Double getProtein() {
        return protein * numberOfServings;
    }

    public String toString() {
        return String.format(
                "%s (%dg; %.1fg kcal; %.1fg, %.1fg, %.1fg)",
                name, servingSize, calories * numberOfServings, carbs * numberOfServings,
                fats * numberOfServings, protein * numberOfServings);
    }

}
