package com.food2prototype.restservice.controller;

import com.food2prototype.restservice.model.Group;
import com.food2prototype.restservice.model.Ingredient;
import com.food2prototype.restservice.model.Recipe;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class GroupController {
    public static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(GroupController.class);

    @PostMapping("/group/{groupID}")
    public int addUser(@PathVariable(value = "groupID") int ID, @RequestBody String[] userIngredientsNames) {
        Group group = Group.get(ID);
        return addUserToGroup("", userIngredientsNames, group);
    }

    @PostMapping("/group/recipe/{recipeID}")
    public int createGroup(@PathVariable(value = "recipeID") int ID, @RequestBody String[] userIngredientsNames) {
        Recipe recipe = Recipe.get(ID);
        Group newGroup = new Group(recipe);
        return addUserToGroup("", userIngredientsNames, newGroup);
    }

    @GetMapping("/group/{groupID}")
    public Group getGroup(@PathVariable(value = "groupID") int ID) {
        return Group.get(ID);
    }

    private int addUserToGroup(String userName, String[] userIngredientsNames, Group group) {
        Set<Ingredient> userIngredients = new HashSet<>();
        for (String ingString : userIngredientsNames) {
            userIngredients.add(Ingredient.getIngredient(ingString));
        }
        group.addUserToGroup(userName, userIngredients);
        return group.ID;
    }
}
