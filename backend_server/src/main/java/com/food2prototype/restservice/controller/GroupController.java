package com.food2prototype.restservice.controller;

import com.food2prototype.restservice.model.Group;
import com.food2prototype.restservice.model.Ingredient;
import com.food2prototype.restservice.model.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class GroupController {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(GroupController.class);

  @PutMapping("/group")
  public ResponseEntity putGroup(@RequestBody Map<String, Object> body){
    Recipe recipe = buildRecipe((Map<String, Object>)body.get("recipe"));
    Set<String> userIngredientsNames = getUserIngredientNames((List<String>) body.get("userIngredients"));
    Set<Ingredient> userIngredients = new HashSet<>();
    for (String ingString : userIngredientsNames){
      userIngredients.add(Ingredient.getIngredient(ingString));
    }
    List<Group> allGroupsForRecipe = Group.getAllGroupsforRecipe(recipe);
    if(allGroupsForRecipe.size() > 0){
      allGroupsForRecipe.get(0).addUserToGroup("", userIngredients);
    }
    else {
      Group newGroup = new Group(recipe);
      newGroup.addUserToGroup("", userIngredients);
    }
    return ResponseEntity.ok().build();
  }

  private Recipe buildRecipe(Map<String, Object> recipeObj){
    String name = recipeObj.get("name").toString();
    List<Object> ingredients = (List<Object>) recipeObj.get("ingredients");
    Set<Ingredient> ingredientsList = new HashSet<>();
    for(Object ing : ingredients){
      ingredientsList.add(buildIngredient((Map<String, Object>) ing));
    }
    return new Recipe(name, ingredientsList);
  }

  private Ingredient buildIngredient(Map<String,Object> ingredientObject){
    String name = ingredientObject.get("name").toString();
    return Ingredient.getIngredient(name);
  }

  private Set<String> getUserIngredientNames(List<String> ingObj){
    return new HashSet<>(ingObj);
  }
}
