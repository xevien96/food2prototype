import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Recipe} from '../modell/recipe';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {RecipeStub} from '../modell/recipe-stub';
import {GroupStub} from '../modell/group-stub';
import {Group} from '../modell/group';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  public readonly groupURL = 'http://localhost:8080/group';

  private readonly httpOptions = {headers: new HttpHeaders({ 'Content-Type': 'application/json'})};

  constructor(
    private client: HttpClient
  ) { }

  public addUserToGroup(recipe: RecipeStub | GroupStub, userZutaten: string[]): Observable<number>{
    if ((recipe as GroupStub).groupID !== undefined){
      const group = recipe as GroupStub;
      return this.addToExistingGroup(group, userZutaten);
    }
    else {
      return this.createNewGroup(recipe, userZutaten);
    }
  }

  public createNewGroup(rezept: RecipeStub, userZutaten: string[]){
    return this.client.post<number>(this.groupURL + `/recipe/${rezept.recipeID}`, userZutaten, this.httpOptions);
  }

  public addToExistingGroup(group: GroupStub, userZutaten: string[]){
    return this.client.post<number>(this.groupURL + `/${group.groupID}`, userZutaten, this.httpOptions);
  }

  public getGroup(id: number): Observable<Group>{
    return this.client.get(this.groupURL + `/${id}`);
  }

  private handleError<T>(operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
