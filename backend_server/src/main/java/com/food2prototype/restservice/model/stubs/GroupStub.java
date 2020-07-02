package com.food2prototype.restservice.model.stubs;

public class GroupStub extends RecipeStub {
    public static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(GroupStub.class);

    public final int groupID;

    public GroupStub(int recipeID, int groupID) {
        super(recipeID);
        this.groupID = groupID;
    }
}
