package com.food2prototype.restservice.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeAlgorithm {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(RecipeAlgorithm.class);

  public static int getRating(Rezept recipe, List<String> userIngredients, List<Group> groupsWithRecipe){
    int score = 0;
    if(groupsWithRecipe.size() > 0){
      score = 5;
      score += getNumberOfUsedGroupIngredientsAfterJoin(userIngredients, groupsWithRecipe.get(0));
      score -= getNumberOfMissingGroupIngredientsAfterJoin(userIngredients, groupsWithRecipe.get(0));
    }
    score += getUsedIngredientsScore(recipe, userIngredients);
    score -= getNotUsedIngredientsScore(recipe, userIngredients);
    return score;
  }

  private static int getNumberOfUsedGroupIngredientsAfterJoin(List<String> userIngredients, Group group){
    return group.getGruppenRezept().getIngredients().size() - getNumberOfMissingGroupIngredientsAfterJoin(userIngredients, group);
  }

  private static int getNumberOfMissingGroupIngredientsAfterJoin(List<String> userIngredients, Group group){
    Set<String> missingIngredients = group.getNichtVorhandeneZutaten();
    Set<String> missingIngredientsAfterJoin = missingIngredients.stream().filter(ing -> userIngredients.contains(ing)).collect(Collectors.toSet());
    return missingIngredientsAfterJoin.size();
  }

  private static int getNotUsedIngredientsScore(Rezept recipe, List<String> userIngredients) {
    return userIngredients.size() - recipe.getNumberOfUsedIngredients(userIngredients);
  }

  private static int getUsedIngredientsScore(Rezept recipe, List<String> userIngredients) {
    return recipe.getNumberOfUsedIngredients(userIngredients);
  }
}
