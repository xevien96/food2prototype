import {Recipe} from './recipe';
import {Ingredient} from './ingredient';

export class Group {

  public gruppenRezept: Recipe;
  public user: string[];
  public vorhandeneZutaten: Ingredient[];

  constructor(gruppenRezept: Recipe, user: string[], vorhandeneZutaten: Ingredient[]){
    this.gruppenRezept = gruppenRezept;
    this.user = user;
    this.vorhandeneZutaten = vorhandeneZutaten;
  }

}
