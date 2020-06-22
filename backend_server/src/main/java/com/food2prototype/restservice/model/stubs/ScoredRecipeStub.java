package com.food2prototype.restservice.model.stubs;

public class ScoredRecipeStub {
  public int score;
  public RecipeStub recipe;

  public ScoredRecipeStub(int score, RecipeStub recipe) {
    this.score = score;
    this.recipe = recipe;
  }

  public int getScore() {
    return score;
  }
}
