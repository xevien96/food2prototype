package com.food2prototype.restservice.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Recipe {

  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(Recipe.class);

  private static final AtomicInteger idCounter = new AtomicInteger(0);
  private static final Map<Integer, Recipe> allRecipes = new HashMap<>();

  private String name;
  private Set<Ingredient> ingredients;
  private final int ID;

  public Recipe(String name, Set<Ingredient> ingredients) {
    this.name = name;
    this.ingredients = ingredients;
    ID = idCounter.incrementAndGet();
    allRecipes.put(ID, this);
  }

  public static Recipe get(int id){
    return allRecipes.get(id);
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
