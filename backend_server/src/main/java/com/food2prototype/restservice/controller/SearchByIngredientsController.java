package com.food2prototype.restservice.controller;

import com.food2prototype.restservice.model.Ingredient;
import com.food2prototype.restservice.model.RecipeProvider;
import com.food2prototype.restservice.model.Recipe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchByIngredientsController {

    @GetMapping("/recipe/search")
    public Integer[] searchByIngredients(@RequestParam(value = "ingredients") String[] values) {
        List<Ingredient> userIngredients = new LinkedList<>();
        for (String ingString : values){
            userIngredients.add(Ingredient.getIngredient(ingString));
        }
        List<Recipe> recipes = RecipeProvider.getRecipesForIngredients(userIngredients);
        Integer[] ret = new Integer[recipes.size()];
        return recipes.stream().map(recipe -> recipe.ID).collect(Collectors.toList()).toArray(ret);
    }

    @GetMapping("/recipe/{recipeID}")
    public Recipe getRecipe(@PathVariable(value = "recipeID") int ID){
        return Recipe.get(ID);
    }
}
