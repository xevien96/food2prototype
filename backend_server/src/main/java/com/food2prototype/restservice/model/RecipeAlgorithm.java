package com.food2prototype.restservice.model;

import java.util.List;

public class RecipeAlgorithm {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(RecipeAlgorithm.class);

  public static int getRating(Rezept recipe, List<String> userIngredients){
    return getUsedIngredientsScore(recipe, userIngredients) + getNotUsedIngredientsScore(recipe, userIngredients);
  }

  private static int getNotUsedIngredientsScore(Rezept recipe, List<String> userIngredients) {
    return userIngredients.size() - recipe.getNumberOfUsedIngredients(userIngredients);
  }

  private static int getUsedIngredientsScore(Rezept recipe, List<String> userIngredients) {
    return recipe.getNumberOfUsedIngredients(userIngredients);
  }
}
