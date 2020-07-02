import {Ingredient} from './ingredient';

/**
 * Ein Rezept
 */
export class Recipe {
  public name: string;
  public ingredients: Ingredient[];

  /**
   * Konstruktor
   * @param name Name des Rezeptes
   * @param ingredients Zutaten im Rezept
   */
  constructor(name: string, ingredients: Ingredient[]){
    this.name = name;
    this.ingredients = ingredients;
  }
}
