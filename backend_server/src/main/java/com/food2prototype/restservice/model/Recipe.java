package com.food2prototype.restservice.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Recipe {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(Recipe.class);

  private String name;
  private Set<String> ingredients;

  public Recipe(String name, Set<String> ingredients) {
    this.name = name;
    this.ingredients = ingredients;
  }

  public String getName() {
    return name;
  }

  public Set<String> getIngredients() {
    return ingredients;
  }

  public int getNumberOfUsedIngredients(List<String> userIngredients) {
    List<String> usedUserIngredients = userIngredients.stream().filter(ing -> ingredients.contains(ing)).collect(Collectors.toList());
    return usedUserIngredients.size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Recipe rezept = (Recipe) o;
    return Objects.equals(name, rezept.name) &&
      Objects.equals(ingredients, rezept.ingredients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, ingredients);
  }
}
