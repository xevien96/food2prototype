package com.food2prototype.restservice;

import com.food2prototype.restservice.model.Group;
import com.food2prototype.restservice.model.Ingredient;
import com.food2prototype.restservice.model.MockDB;
import com.food2prototype.restservice.model.Recipe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

/**
 * Startklasse für die REST-Application
 */
@SpringBootApplication
public class RestServiceApplication {

    /**
     * Initialisiert die Mock-DB und startet alle Schnittstellen
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
        MockDB.readDB();
        MockDB.initIngredients();
        MockDB.getAllRecipes();
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
