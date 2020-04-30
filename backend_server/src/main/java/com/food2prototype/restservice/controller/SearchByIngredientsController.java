package com.food2prototype.restservice.controller;

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
        List<String> ingredients = Arrays.asList(values);
        if(ingredients.contains("Mehl") && ingredients.contains("Eier")) {
            return new String[]{"Pfannkuchen", "Schnitzel", "Panierter Fisch"};
        }
        return new String[]{};
    }
}
