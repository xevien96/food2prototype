import {RecipeStub} from './recipe-stub';

/**
 * Stub für eine Gruppe
 */
export class GroupStub extends RecipeStub {
  public groupID: number;

  /**
   * Konstruktor
   * @param recipeID ID des Rezepts, dass zur Gruppe gehört
   * @param groupID ID der Gruppe
   */
  constructor(recipeID: number, groupID: number){
    super(recipeID);
    this.groupID = groupID;
  }
}
