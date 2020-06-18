package com.food2prototype.restservice.controller;

import com.food2prototype.restservice.model.Group;
import com.food2prototype.restservice.model.Ingredient;
import com.food2prototype.restservice.model.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class GroupController {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(GroupController.class);

  @PostMapping("/group/{groupID}")
  public int addUser(@PathVariable(value = "groupID") int ID, @RequestBody String[] userIngredientsNames) {
    Recipe recipe = Recipe.get(ID);
    Set<Ingredient> userIngredients = new HashSet<>();
    for (String ingString : userIngredientsNames) {
      userIngredients.add(Ingredient.getIngredient(ingString));
    }
    Group group = Group.get(ID);
    group.addUserToGroup("", userIngredients);
    return group.ID;
  }

  @PostMapping("/group/recipe/{recipeID}")
  public int createGroup(@PathVariable(value = "recipeID") int ID, @RequestBody String[] userIngredientsNames){
    Recipe recipe = Recipe.get(ID);
    Group newGroup = new Group(recipe);
    Set<Ingredient> userIngredients = new HashSet<>();
    for (String ingString : userIngredientsNames) {
      userIngredients.add(Ingredient.getIngredient(ingString));
    }
    newGroup.addUserToGroup("", userIngredients);
    return newGroup.ID;
  }

  @GetMapping("/group/{groupID}")
  public Group getGroup(@PathVariable(value = "groupID") int ID) {
    return Group.get(ID);
  }
}
