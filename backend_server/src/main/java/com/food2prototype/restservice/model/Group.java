package com.food2prototype.restservice.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Group {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(Group.class);

  private Rezept groupRecipe;
  private Set<String> groupIngredients;

  public Group(Rezept groupRecipe) {
    this.groupRecipe = groupRecipe;
    groupIngredients = new HashSet<>();
  }

  public Rezept getGroupRecipe() {
    return groupRecipe;
  }

  public Set<String> getGroupIngredients() {
    return groupIngredients;
  }

  public void addToGroup(List<String> userIngredients) {
    for (String ing: userIngredients) {
      if(groupRecipe.getIngredients().contains(ing) && !groupIngredients.contains(ing)) {
        groupIngredients.add(ing);
      }
    }
  }

  public int getNumberOfUsedIngredientsInGroup(List<String> userIngredients) {
    List<String> usableIngredients = userIngredients.stream()
      .filter(ing -> groupRecipe.getIngredients().contains(ing))
      .filter(ing -> !groupIngredients.contains(ing))
      .collect(Collectors.toList());
    return usableIngredients.size();
  }

}
