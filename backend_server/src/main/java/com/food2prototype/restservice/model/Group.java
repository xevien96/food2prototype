package com.food2prototype.restservice.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Group {
  private static Map<Integer, Group> allGroups = new HashMap<>();
  private static AtomicInteger idCounter = new AtomicInteger(0);

  public final int ID;

  private Recipe gruppenRezept;
  private Set<Ingredient> vorhandeneZutaten;
  private Set<String> user;

  public Group(Recipe rezept) {
    gruppenRezept = rezept;
    vorhandeneZutaten = new HashSet<>();
    user = new HashSet<>();
    ID = idCounter.incrementAndGet();
    allGroups.put(ID, this);
  }

  public static Group get(int ID) {
    return allGroups.get(ID);
  }

  public static List<Group> getAllGroupsforRecipe(Recipe rezept) {
    List<Group> groupsWithRecipe = new ArrayList<>();
    for (Group group : allGroups.values()) {
      if (group.gruppenRezept.equals(rezept)) {
        groupsWithRecipe.add(group);
      }
    }
    return groupsWithRecipe;
  }

  public Recipe getGruppenRezept() {
    return gruppenRezept;
  }

  public Set<Ingredient> getVorhandeneZutaten() {
    return vorhandeneZutaten;
  }

  public Set<String> getUser() {
    return user;
  }

  public void addUserToGroup(String user, Set<Ingredient> userZutaten) {
    this.user.add(user);
    Set<Ingredient> nutzbareZutaten = new HashSet<>();
    for (Ingredient zutat : userZutaten) {
      if (gruppenRezept.getIngredients().contains(zutat)) {
        nutzbareZutaten.add(zutat);
      }
    }
    vorhandeneZutaten.addAll(nutzbareZutaten);
  }

  public Set<Ingredient> getNichtVorhandeneZutaten() {
    Set<Ingredient> nichtVorhandeneZutaten = new HashSet<>();
    nichtVorhandeneZutaten = gruppenRezept.getIngredients().stream().filter(ing -> ! vorhandeneZutaten.contains(ing))
      .collect(Collectors.toSet());
    return nichtVorhandeneZutaten;
  }
}
