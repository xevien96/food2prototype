package com.food2prototype.restservice.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Group {
    private static List<Group> allGroups = new ArrayList<>();

    private Recipe gruppenRezept;
    private Set<Ingredient> vorhandeneZutaten;
    private Set<String> user;

    public Group(Recipe rezept) {
        gruppenRezept = rezept;
        vorhandeneZutaten = new HashSet<>();
        user = new HashSet<>();
        allGroups.add(this);
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

  public static List<Group> getAllGroupsforRecipe(Recipe rezept) {
        List<Group> groupsWithRecipe = new ArrayList<>();
        for(Group group : allGroups) {
            if (group.gruppenRezept.equals(rezept)) {
                groupsWithRecipe.add(group);
            }
        }
        return groupsWithRecipe;
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
        nichtVorhandeneZutaten = gruppenRezept.getIngredients().stream().filter(ing -> !vorhandeneZutaten.contains(ing))
                .collect(Collectors.toSet());
        return nichtVorhandeneZutaten;
    }
}
