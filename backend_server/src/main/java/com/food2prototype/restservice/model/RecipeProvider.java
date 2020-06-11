package com.food2prototype.restservice.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeProvider {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(RecipeProvider.class);

  public static List<Recipe> getRecipesForIngredients(List<String> userIngredients){
    List<Recipe> sensibleRecipes = MockDB.getAllRecipesContainingAtLeastOneIngredient(userIngredients);

    List<ScoredRecipe> scoredRecipes = new LinkedList<>();
    for(Recipe r : sensibleRecipes){
      List<Group> groupsWithRecipe = Group.getAllGroupsforRecipe(r);
      int score = RecipeAlgorithm.getRating(r, userIngredients, groupsWithRecipe);
      ScoredRecipe sr = new ScoredRecipe(score, r);
      scoredRecipes.add(sr);
    }

    List<Recipe> result = scoredRecipes.stream()
      .sorted(Comparator.comparing(scoredRecipe -> scoredRecipe.score))
      .map(scoredRecipe -> scoredRecipe.recipe)
      .collect(Collectors.toList());

    return result;
  }

  private static class ScoredRecipe {
    public int score;
    public Recipe recipe;

    public ScoredRecipe(int score, Recipe recipe) {
      this.score = score;
      this.recipe = recipe;
    }
  }
}
