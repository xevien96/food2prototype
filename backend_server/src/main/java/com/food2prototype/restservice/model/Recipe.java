package com.food2prototype.restservice.model;

import com.food2prototype.restservice.model.stubs.RecipeStub;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Objekt-Klasse für Rezepte
 */
public class Recipe {

    public static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Recipe.class);

    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private static final Map<Integer, Recipe> allRecipes = new HashMap<>();
    public final int ID;
    private String name;
    private Set<Ingredient> ingredients;

    /**
     * Konstruktor für Rezept-Objekte
     * @param name Name des Rezeptes
     * @param ingredients Zutaten des Rezeptes
     */
    public Recipe(String name, Set<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        ID = idCounter.getAndIncrement();
        allRecipes.put(ID, this);
    }

    public static Recipe get(int id) {
        return allRecipes.get(id);
    }

    /**
     * Gibt alle Rezepte, in denen mindestens eine der übergebenen Zutaten vorhanden ist, aus
     * @param userIngredients Liste von Rezepten des Nutzers
     * @return Liste von Rezepten, in denen mindestens eine der Nutzerzutaten vorhanden ist.
     */
    public static List<Recipe> getAllRecipesContainingAtLeastOneIngredient(List<Ingredient> userIngredients) {
        List<Recipe> result;
        result = allRecipes.values().stream()
                .filter(rezept -> rezept.getNumberOfUsedIngredients(userIngredients) > 0)
                .collect(Collectors.toList());
        return result;
    }

    public String getName() {
        return name;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public RecipeStub toStub() {
        return new RecipeStub(this.ID);
    }

    /**
     * Gibt die Anzahl der nutzbaren Zutaten aus einer Liste in einem Rezept aus
     * @param userIngredients Liste von Zutaten des Nutzers
     * @return Anzahl der Zutaten, welche für ein Rezept nutzbar sind
     */
    public int getNumberOfUsedIngredients(List<Ingredient> userIngredients) {
        List<Ingredient> usedUserIngredients = userIngredients.stream().filter(ing -> ingredients.contains(ing)).collect(Collectors.toList());
        return usedUserIngredients.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe rezept = (Recipe) o;
        return Objects.equals(name, rezept.name) &&
                Objects.equals(ingredients, rezept.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients);
    }
}
