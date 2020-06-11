export class Recipe {
  public name: string;
  public ingredients: string[];

  constructor(name: string, ingredients: string[]){
    this.name = name;
    this.ingredients = ingredients;
  }
}
