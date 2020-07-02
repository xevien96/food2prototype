package com.food2prototype.restservice.model.stubs;

/**
 * Kapstelt die ID eines Rezeptes
 */
public class RecipeStub {
    public static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(RecipeStub.class);

    public final int recipeID;

    public RecipeStub(int recipeID) {
        this.recipeID = recipeID;
    }
}
