package com.food2prototype.restservice.model;

import java.util.List;

public class RecipeAlgorithm {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(RecipeAlgorithm.class);

  public static int getRating(Rezept recipe, List<String> userIngredients, List<Group> groupsWithRecipe){
    int score = groupsWithRecipe.size() > 0 ? 5 : 0;
    score += getUsedIngredientsScore(recipe, userIngredients);
    score -= getNotUsedIngredientsScore(recipe, userIngredients);
    return score;
  }

  private static int getNotUsedIngredientsScore(Rezept recipe, List<String> userIngredients) {
    return userIngredients.size() - recipe.getNumberOfUsedIngredients(userIngredients);
  }

  private static int getUsedIngredientsScore(Rezept recipe, List<String> userIngredients) {
    return recipe.getNumberOfUsedIngredients(userIngredients);
  }
}
