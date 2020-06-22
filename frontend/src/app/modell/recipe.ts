import {Ingredient} from './ingredient';

export class Recipe {
  public name: string;
  public ingredients: Ingredient[];

  constructor(name: string, ingredients: Ingredient[]){
    this.name = name;
    this.ingredients = ingredients;
  }
}
