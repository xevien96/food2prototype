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

  @PutMapping("/group/recipe/{recipeID}")
  public int putGroup(@PathVariable(value = "recipeID") int ID, @RequestBody String[] userIngredientsNames) {
    Recipe recipe = Recipe.get(ID);
    Set<Ingredient> userIngredients = new HashSet<>();
    for (String ingString : userIngredientsNames) {
      userIngredients.add(Ingredient.getIngredient(ingString));
    }
    List<Group> allGroupsForRecipe = Group.getAllGroupsforRecipe(recipe);
    if (allGroupsForRecipe.size() > 0) {
      allGroupsForRecipe.get(0).addUserToGroup("", userIngredients);
      return allGroupsForRecipe.get(0).ID;
    } else {
      Group newGroup = new Group(recipe);
      newGroup.addUserToGroup("", userIngredients);
      return newGroup.ID;
    }
  }

  @GetMapping("/group/{groupID}")
  public Group getGroup(@PathVariable(value = "groupID") int ID){
    return Group.get(ID);
  }
}
