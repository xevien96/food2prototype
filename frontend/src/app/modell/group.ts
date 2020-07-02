import {Recipe} from './recipe';
import {Ingredient} from './ingredient';

/**
 * Eine Gruppe, die ein Rezept zusammen kocht
 */
export class Group {

  public gruppenRezept: Recipe;
  public user: string[];
  public vorhandeneZutaten: Ingredient[];

  /**
   * Konstruktor
   * @param gruppenRezept Rezept, dass von der Gruppe gekocht wird
   * @param user Namen der Nutzer in der Gruppe
   * @param vorhandeneZutaten Zutaten, die bereits in der Gruppe vorhanden sind
   */
  constructor(gruppenRezept: Recipe, user: string[], vorhandeneZutaten: Ingredient[]){
    this.gruppenRezept = gruppenRezept;
    this.user = user;
    this.vorhandeneZutaten = vorhandeneZutaten;
  }

}
