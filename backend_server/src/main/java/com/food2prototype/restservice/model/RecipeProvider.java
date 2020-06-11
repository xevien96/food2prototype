package com.food2prototype.restservice.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeProvider {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(RecipeProvider.class);

  public static List<Rezept> getRecipesForIngredients(List<String> userIngredients){
    List<Rezept> sensibleRecipes = MockDB.getAllRecipesContainingAtLeastOneIngredient(userIngredients);

    List<ScoredRecipe> scoredRecipes = new LinkedList<>();
    for(Rezept r : sensibleRecipes){
      int score = RecipeAlgorithm.getRating(r, userIngredients);
      ScoredRecipe sr = new ScoredRecipe(score, r);
      scoredRecipes.add(sr);
    }

    List<Rezept> result = scoredRecipes.stream()
      .sorted(Comparator.comparing(scoredRecipe -> scoredRecipe.score))
      .map(scoredRecipe -> scoredRecipe.recipe)
      .collect(Collectors.toList());

    return result;
  }

  private static class ScoredRecipe {
    public int score;
    public Rezept recipe;

    public ScoredRecipe(int score, Rezept recipe) {
      this.score = score;
      this.recipe = recipe;
    }
  }
}
