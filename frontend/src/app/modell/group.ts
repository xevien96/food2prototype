import {Recipe} from './recipe';

export class Group {

  public rezept: Recipe;
  public users: string[];
  public groupIngredients: string[];

  constructor(rezept: Recipe, users: string[], groupIngredients: string[]){
    this.rezept = rezept;
    this.users = users;
    this.groupIngredients = groupIngredients;
  }

}
