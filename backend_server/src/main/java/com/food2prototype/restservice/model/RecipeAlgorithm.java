package com.food2prototype.restservice.model;

import com.food2prototype.restservice.model.stubs.GroupStub;
import com.food2prototype.restservice.model.stubs.RecipeStub;
import com.food2prototype.restservice.model.stubs.ScoredRecipeStub;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeAlgorithm {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(RecipeAlgorithm.class);

  public static ScoredRecipeStub getRating(Recipe recipe, List<Ingredient> userIngredients, List<Group> groupsWithRecipe){
    int score = 0;
    RecipeStub stub = new RecipeStub(recipe.ID);
    if(groupsWithRecipe.size() > 0){
      int maxGroupScore = Integer.MIN_VALUE;
      Group selectedGroup = null;
      for(Group group : groupsWithRecipe){
        int tempGroupScore = 5;
        tempGroupScore += getNumberOfUsedGroupIngredientsAfterJoin(userIngredients, group);
        tempGroupScore -= getNumberOfMissingGroupIngredientsAfterJoin(userIngredients, group);
        if(tempGroupScore > maxGroupScore){
          maxGroupScore = tempGroupScore;
          selectedGroup = group;
        }
      }
      score = maxGroupScore;
      stub = new GroupStub(recipe.ID, selectedGroup.ID);
    }
    else {
      score -= getNumberOfNotUsedIngredientsInRecipe(recipe, userIngredients);
    }
    score += getUsedIngredientsScore(recipe, userIngredients);
    score -= getNotUsedIngredientsScore(recipe, userIngredients);
    return new ScoredRecipeStub(score, stub);
  }

  private static int getNumberOfUsedGroupIngredientsAfterJoin(List<Ingredient> userIngredients, Group group){
    return group.getGruppenRezept().getIngredients().size() - getNumberOfMissingGroupIngredientsAfterJoin(userIngredients, group);
  }

  private static int getNumberOfMissingGroupIngredientsAfterJoin(List<Ingredient> userIngredients, Group group){
    Set<Ingredient> missingIngredients = group.getNichtVorhandeneZutaten();
    Set<Ingredient> missingIngredientsAfterJoin = missingIngredients.stream().filter(ing -> userIngredients.contains(ing)).collect(Collectors.toSet());
    return missingIngredientsAfterJoin.size();
  }

  private static int getNotUsedIngredientsScore(Recipe recipe, List<Ingredient> userIngredients) {
    return userIngredients.size() - recipe.getNumberOfUsedIngredients(userIngredients);
  }

  private static int getUsedIngredientsScore(Recipe recipe, List<Ingredient> userIngredients) {
    return recipe.getNumberOfUsedIngredients(userIngredients);
  }

  private static int getNumberOfNotUsedIngredientsInRecipe(Recipe recipe, List<Ingredient> userIngredients){
    Set<Ingredient> missingIngredients = recipe.getIngredients().stream().filter(ing -> !userIngredients.contains(ing))
      .collect(Collectors.toSet());
    return missingIngredients.size();
  }
}
