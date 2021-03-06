package com.food2prototype.restservice.controller;

import com.food2prototype.restservice.model.Ingredient;
import com.food2prototype.restservice.model.Recipe;
import com.food2prototype.restservice.model.RecipeProvider;
import com.food2prototype.restservice.model.stubs.RecipeStub;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class SearchByIngredientsController {

    @GetMapping("/recipe/search")
    public List<RecipeStub> searchByIngredients(@RequestParam(value = "ingredients") String[] values) {
        List<Ingredient> userIngredients = new LinkedList<>();
        for (String ingString : values) {
            userIngredients.add(Ingredient.getIngredient(ingString));
        }
        return RecipeProvider.getRecipesForIngredients(userIngredients);
    }

    @GetMapping("/recipe/{recipeID}")
    public Recipe getRecipe(@PathVariable(value = "recipeID") int ID) {
        return Recipe.get(ID);
    }
}
