package com.food2prototype.restservice.controller;

import com.food2prototype.restservice.model.MockDB;
import com.food2prototype.restservice.model.RecipeProvider;
import com.food2prototype.restservice.model.Rezept;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchByIngredientsController {

    @GetMapping("/recipe/search")
    public String[] searchByIngredients(@RequestParam(value = "ingredients") String[] values) {
        List<Rezept> recipes = RecipeProvider.getRecipesForIngredients(Arrays.asList(values));
        List<String> recipeName = recipes.stream().map(Rezept::getName).collect(Collectors.toList());
        String[] ret = new String[recipes.size()];
        return recipeName.toArray(ret);
    }
}
