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

  public static List<Rezept> getAllRecipes() {
    readDB();
    List<Rezept> result = new LinkedList<>();
    for(Object obj : db.keySet()){
      String name = (String)obj;
      String ingredients = (String) db.get(name);
      String[] values = ingredients.split(",");
      Set<String> ingredientsSet = new HashSet<>();
      ingredientsSet.addAll(Arrays.asList(values));
      Rezept r = new Rezept(name, ingredientsSet);
      result.add(r);
    }
    return result;
  }

  public static List<Rezept> getAllRecipesContainingAtLeastOneIngredient(List<String> userIngredients){
    List<Rezept> allRecipes = getAllRecipes();
    List<Rezept> result;
    result = allRecipes.stream()
      .filter(rezept -> rezept.getNumberOfUsedIngredients(userIngredients) > 0)
      .collect(Collectors.toList());
    return result;
  }
}
