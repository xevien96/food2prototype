package com.food2prototype.restservice.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class MockDB {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(MockDB.class);

  private static Properties db;

  public MockDB() {
    readDB();
  }

  public static void readDB() {
    db = new Properties();
    try (InputStream i = MockDB.class.getClassLoader().getResourceAsStream("DB.properties")){
      if(i != null) {
        db.load(i);
      }
    }
    catch(IOException e) {
      logger.error(e.getMessage());
    }
  }

  public static List<Recipe> getAllRecipes() {
    readDB();
    List<Recipe> result = new LinkedList<>();
    for(Object obj : db.keySet()){
      String name = (String)obj;
      String ingredients = (String) db.get(name);
      String[] values = ingredients.split(",");
      Set<Ingredient> ingredientsSet = new HashSet<>();
      for(String ingName : values){
        ingredientsSet.add(new Ingredient(ingName, 1));
      }
      Recipe r = new Recipe(name, ingredientsSet);
      result.add(r);
    }
    return result;
  }

  public static List<Recipe> getAllRecipesContainingAtLeastOneIngredient(List<Ingredient> userIngredients){
    List<Recipe> allRecipes = getAllRecipes();
    List<Recipe> result;
    result = allRecipes.stream()
      .filter(rezept -> rezept.getNumberOfUsedIngredients(userIngredients) > 0)
      .collect(Collectors.toList());
    return result;
  }
}
