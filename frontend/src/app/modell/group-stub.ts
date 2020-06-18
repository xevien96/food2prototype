import {RecipeStub} from './recipe-stub';

export class GroupStub extends RecipeStub {
  public groupID: number;

  constructor(recipeID: number, groupID: number){
    super(recipeID);
    this.groupID = groupID;
  }
}
