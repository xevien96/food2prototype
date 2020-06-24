package com.food2prototype.restservice;

import com.food2prototype.restservice.model.Group;
import com.food2prototype.restservice.model.Ingredient;
import com.food2prototype.restservice.model.MockDB;
import com.food2prototype.restservice.model.Recipe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RestServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(RestServiceApplication.class, args);
    MockDB.readDB();
    MockDB.initIngredients();
    MockDB.getAllRecipes();
    //buildGroups();
  }

  private static void buildGroups(){
    Recipe fisch = Recipe.get(0);
    Recipe spiegelei = Recipe.get(1);
    Recipe pfannkuchen = Recipe.get(2);
    Recipe schnitzel = Recipe.get(3);

    Group fischGroup = new Group(fisch);
    Set<Ingredient> fischIng1 = new HashSet<>();
    fischIng1.add(Ingredient.getIngredient("Fisch"));
    fischGroup.addUserToGroup("Martin", fischIng1);

    Group fischGroup2 = new Group(fisch);
    Set<Ingredient> fischIng2 = new HashSet<>();
    fischIng2.add(Ingredient.getIngredient("Eier"));
    fischGroup2.addUserToGroup("Heike", fischIng2);
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:4200");
      }
    };
  }
}
