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

  public static List<String> getRecipesContaining(String[] ingredients) {
    readDB();
    List<String> ingred = Arrays.asList(ingredients);
    Map<String, String[]> props = new HashMap<>();
    for(Object obj : db.keySet()){
      String key = (String)obj;
      String value = (String) db.get(key);
      String[] values = value.split(",");
      props.put(key, values);
    }

    List<Tupel> results = new LinkedList<>();

    for (Map.Entry<String, String[]> kvp : props.entrySet()) {
      int filteredLength = kvp.getValue().length - Arrays.stream(kvp.getValue()).filter(ing -> !ingred.contains(ing)).toArray().length;
      if(filteredLength > 0) {
        Tupel t = new Tupel(filteredLength, kvp.getKey());
        results.add(t);
      }
    }
    List<String> r = results.stream().sorted(Comparator.reverseOrder()).map(t -> t.second).collect(Collectors.toList());
    return r;
  }

  private static class Tupel implements Comparable<Tupel>{
    public Integer first;
    public String second;

    public Tupel(int k, String v){
      this.first = k;
      this.second = v;
    }

    @Override
    public int compareTo(Tupel o) {
      return first.compareTo(o.first);
    }
  }
}
