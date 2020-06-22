package com.food2prototype.restservice.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class MockDB {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(MockDB.class);

  private static Properties recipes;
  private static Properties ingredients;

  public MockDB() {
    readDB();
  }

  public static void readDB() {
    recipes = new Properties();
    ingredients = new Properties();
    try (InputStream recipeStream = MockDB.class.getClassLoader().getResourceAsStream("Recipes.properties");
         InputStream ingredientsStream = MockDB.class.getClassLoader().getResourceAsStream("Ingredients.properties")){
      if(recipeStream != null) {
        recipes.load(recipeStream);
      }
      if(ingredientsStream != null){
        ingredients.load(ingredientsStream);
      }
    }
    catch(IOException e) {
      logger.error(e.getMessage());
    }
  }

  public static List<Recipe> getAllRecipes() {
    List<Recipe> result = new LinkedList<>();
    for(Object obj : recipes.keySet()){
      String name = (String)obj;
      String ingredients = (String) recipes.get(name);
      String[] values = ingredients.split(",");
      Set<Ingredient> ingredientsSet = new HashSet<>();
      for(String ingName : values){
        ingredientsSet.add(Ingredient.getIngredient(ingName));
      }
      Recipe r = new Recipe(name, ingredientsSet);
      result.add(r);
    }
    return result;
  }

  public static void initIngredients(){
    for(Object obj : ingredients.keySet()){
      String name = obj.toString();
      double rarity = Double.parseDouble(ingredients.get(name).toString());
      new Ingredient(name, rarity);
    }
  }
}
