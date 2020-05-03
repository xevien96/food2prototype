package com.food2prototype.restservice.controller;

import com.food2prototype.restservice.model.MockDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SearchByIngredientsController {

    @GetMapping("/recipe/search")
    public String[] searchByIngredients(@RequestParam(value = "ingredients") String[] values) {
        List<String> recipes = MockDB.getRecipesContaining(values);
        String[] ret = new String[recipes.size()];
        return recipes.toArray(ret);
    }
}
