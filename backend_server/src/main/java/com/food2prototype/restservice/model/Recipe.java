package com.food2prototype.restservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Recipe {

  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(Recipe.class);

  private String name;
  private Set<Ingredient> ingredients;

  public Recipe(String name, Set<Ingredient> ingredients) {
    this.name = name;
    this.ingredients = ingredients;
  }

  public String getName() {
    return name;
  }

  public Set<Ingredient> getIngredients() {
    return ingredients;
  }

  public int getNumberOfUsedIngredients(List<Ingredient> userIngredients) {
    List<Ingredient> usedUserIngredients = userIngredients.stream().filter(ing -> ingredients.contains(ing)).collect(Collectors.toList());
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
