import {Recipe} from './recipe';

export class Group {

  public gruppenRezept: Recipe;
  public user: string[];
  public vorhandeneZutaten: string[];

  constructor(gruppenRezept: Recipe, user: string[], vorhandeneZutaten: string[]){
    this.gruppenRezept = gruppenRezept;
    this.user = user;
    this.vorhandeneZutaten = vorhandeneZutaten;
  }

}
