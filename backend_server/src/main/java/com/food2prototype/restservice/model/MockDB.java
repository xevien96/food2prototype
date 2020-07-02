package com.food2prototype.restservice.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Klasse für das Erstellen der Mock-DB des Prototypen
 */
public class MockDB {
    public static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(MockDB.class);

    private static Properties recipes;
    private static Properties ingredients;

    public MockDB() {
        readDB();
    }

    /**
     * Liest die in der Mock-DB vorhandenen Zutaten und Rezepte aus den jeweiligen Properties-Dateien ein.
     */
    public static void readDB() {
        recipes = new Properties();
        ingredients = new Properties();
        try (InputStream recipeStream = MockDB.class.getClassLoader().getResourceAsStream("Recipes.properties");
             InputStream ingredientsStream = MockDB.class.getClassLoader().getResourceAsStream("Ingredients.properties")) {
            if (recipeStream != null) {
                recipes.load(new InputStreamReader(recipeStream, StandardCharsets.UTF_8));
            }
            if (ingredientsStream != null) {
                ingredients.load(new InputStreamReader(ingredientsStream, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static List<Recipe> getAllRecipes() {
        List<Recipe> result = new LinkedList<>();
        for (Object obj : recipes.keySet()) {
            String name = (String) obj;
            String ingredients = (String) recipes.get(name);
            String[] values = ingredients.split(",");
            Set<Ingredient> ingredientsSet = new HashSet<>();
            for (String ingName : values) {
                ingredientsSet.add(Ingredient.getIngredient(ingName));
            }
            Recipe r = new Recipe(name, ingredientsSet);
            result.add(r);
        }
        return result;
    }

    /**
     * Initiiert die Zutaten-Objekte
     */
    public static void initIngredients() {
        for (Object obj : ingredients.keySet()) {
            String name = obj.toString();
            double rarity = Double.parseDouble(ingredients.get(name).toString());
            new Ingredient(name, rarity);
        }
    }
}
