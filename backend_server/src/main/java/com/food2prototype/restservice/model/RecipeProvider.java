package com.food2prototype.restservice.model;

import com.food2prototype.restservice.model.stubs.RecipeStub;
import com.food2prototype.restservice.model.stubs.ScoredRecipeStub;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Gibt Rezepte in einer Priorisierten Reihenfolge aus
 */
public class RecipeProvider {
    public static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(RecipeProvider.class);

    public static List<RecipeStub> getRecipesForIngredients(List<Ingredient> userIngredients) {
        List<Recipe> sensibleRecipes = Recipe.getAllRecipesContainingAtLeastOneIngredient(userIngredients);

        List<ScoredRecipeStub> scoredRecipes = new LinkedList<>();
        for (Recipe r : sensibleRecipes) {
            List<Group> groupsWithRecipe = Group.getAllGroupsforRecipe(r);
            ScoredRecipeStub sr = RecipeAlgorithm.getRating(r, userIngredients, groupsWithRecipe);
            scoredRecipes.add(sr);
        }

        List<RecipeStub> result = scoredRecipes.stream()
                .sorted(Comparator.comparing(ScoredRecipeStub::getScore).reversed())
                .map(scoredRecipe -> scoredRecipe.recipe)
                .collect(Collectors.toList());

        return result;
    }
}
