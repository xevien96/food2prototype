package com.food2prototype.restservice.model;

import java.util.List;
import java.util.stream.Collectors;

public class Rezept {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(Rezept.class);

  private String name;
  private List<String> ingredients;

  public Rezept(String name, List<String> ingredients) {
    this.name = name;
    this.ingredients = ingredients;
  }

  public String getName() {
    return name;
  }

  public List<String> getIngredients() {
    return ingredients;
  }

  public int containsIngredients(List<String> userIngredients) {
    List<String> usedUserIngredients = userIngredients.stream().filter(ing -> ingredients.contains(ing)).collect(Collectors.toList());
    return usedUserIngredients.size();
  }
}
