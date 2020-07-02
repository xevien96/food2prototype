package com.food2prototype.restservice.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Klasse für ein Zutaten-Objekt
 */
public class Ingredient {
    public static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Ingredient.class);

    private static Map<String, Ingredient> allIngredients = new HashMap<>();

    private String name;
    private double rarity;

    /**
     * Konstruktor für eine Zutat
     * @param name Name der Zutat
     * @param rarity Seltenheit der Zutat
     */
    public Ingredient(String name, double rarity) {
        this.name = name;
        this.rarity = rarity;
        allIngredients.put(name, this);
    }

    public static Ingredient getIngredient(String name) {
        return allIngredients.get(name);
    }

    public String getName() {
        return name;
    }

    public double getRarity() {
        return rarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
