package com.food2prototype.restservice.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
      Rezept r = new Rezept(name, Arrays.asList(values));
      result.add(r);
    }
    return result;
  }
}
