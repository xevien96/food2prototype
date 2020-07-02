package com.food2prototype.restservice.model;

import com.food2prototype.restservice.model.stubs.GroupStub;
import com.food2prototype.restservice.model.stubs.RecipeStub;
import com.food2prototype.restservice.model.stubs.ScoredRecipeStub;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Algorthmus für die Priorisierung von Rezepten
 */
public class RecipeAlgorithm {
    public static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(RecipeAlgorithm.class);

    /**
     * Ermittelt ein Rating für ein Rezept anhand der Nutzerzutaten und vorhandener Gruppen für das Rezept
     * @param recipe Rezept, für das ein Rating ermittelt werden soll
     * @param userIngredients Liste von Zutaten des Nutzers
     * @param groupsWithRecipe Liste von Gruppen, welche dieses Rezept kochen, denen der Nutzer beitreten könnte
     * @return Rating für das Rezept, nutzbar für die Priorisierung von Rezepten
     */
    public static ScoredRecipeStub getRating(Recipe recipe, List<Ingredient> userIngredients, List<Group> groupsWithRecipe) {
        int score = 0;
        RecipeStub stub = new RecipeStub(recipe.ID);
        if (groupsWithRecipe.size() > 0) {
            int maxGroupScore = Integer.MIN_VALUE;
            Group selectedGroup = null;
            for (Group group : groupsWithRecipe) {
                int tempGroupScore = 5;
                tempGroupScore += getNumberOfUsedGroupIngredientsAfterJoin(userIngredients, group);
                tempGroupScore -= getNumberOfMissingGroupIngredientsAfterJoin(userIngredients, group);
                if (tempGroupScore > maxGroupScore) {
                    maxGroupScore = tempGroupScore;
                    selectedGroup = group;
                }
            }
            score = maxGroupScore;
            stub = new GroupStub(recipe.ID, selectedGroup.ID);
        } else {
            score -= getNumberOfNotUsedIngredientsInRecipe(recipe, userIngredients);
        }
        score += getUsedIngredientsScore(recipe, userIngredients);
        score -= getNotUsedIngredientsScore(recipe, userIngredients);
        return new ScoredRecipeStub(score, stub);
    }

    /**
     * Ermittel die Anzahl der genutzten Zutaten, wenn der User der Gruppe beitreten würde
     * @param userIngredients Zutaten des Nutzers
     * @param group Gruppe, die der Nutzer beitreten würde
     * @return Anzahl der verwendeten Zutaten nach Gruppenbeitritt
     */
    private static int getNumberOfUsedGroupIngredientsAfterJoin(List<Ingredient> userIngredients, Group group) {
        return group.getGruppenRezept().getIngredients().size() - getNumberOfMissingGroupIngredientsAfterJoin(userIngredients, group);
    }

    /**
     * Ermittel die Anzahl der fehlenden Zutaten, wenn der User der Gruppe beitreten würde
     * @param userIngredients Zutaten des Nutzers
     * @param group Gruppe, die der Nutzer beitreten würde
     * @return Anzahl der fehlenden Zutaten nach Gruppenbeitritt
     */
    private static int getNumberOfMissingGroupIngredientsAfterJoin(List<Ingredient> userIngredients, Group group) {
        Set<Ingredient> missingIngredients = group.getNichtVorhandeneZutaten();
        Set<Ingredient> missingIngredientsAfterJoin = missingIngredients.stream().filter(ing -> !userIngredients.contains(ing)).collect(Collectors.toSet());
        return missingIngredientsAfterJoin.size();
    }

    /**
     * Ermittelt den Score für die verwendeten/ nicht verwendeten Zutaten
     * @param recipe Rezept für das der Score ermittelt wird
     * @param userIngredients Zutaten des Nutzers
     * @return Score basierend auf den Zutaten
     */
    private static int getNotUsedIngredientsScore(Recipe recipe, List<Ingredient> userIngredients) {
        return userIngredients.size() - recipe.getNumberOfUsedIngredients(userIngredients);
    }

    private static int getUsedIngredientsScore(Recipe recipe, List<Ingredient> userIngredients) {
        return recipe.getNumberOfUsedIngredients(userIngredients);
    }

    private static int getNumberOfNotUsedIngredientsInRecipe(Recipe recipe, List<Ingredient> userIngredients) {
        Set<Ingredient> missingIngredients = recipe.getIngredients().stream().filter(ing -> !userIngredients.contains(ing))
                .collect(Collectors.toSet());
        return missingIngredients.size();
    }
}
