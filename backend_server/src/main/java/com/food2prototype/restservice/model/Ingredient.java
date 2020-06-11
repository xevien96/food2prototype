package com.food2prototype.restservice.model;

public class Ingredient {
  public static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(Ingredient.class);

  private String name;
  private double rarity;

  public Ingredient(String name, double rarity) {
    this.name = name;
    this.rarity = rarity;
  }

  public String getName() {
    return name;
  }

  public double getRarity() {
    return rarity;
  }
}
