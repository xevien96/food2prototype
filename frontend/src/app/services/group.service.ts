import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Recipe} from '../modell/recipe';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  public readonly groupURL = 'http://localhost:8080/group';

  private readonly httpOptions = {headers: new HttpHeaders({ 'Content-Type': 'application/json'})};

  constructor(
    private client: HttpClient
  ) { }

  public putGroup(rezept: Recipe, userZutaten: string[]){
    return this.client.put<any>(this.groupURL, {recipe: rezept, userIngredients: userZutaten}, this.httpOptions).pipe(
      catchError(this.handleError(`join group for recipe ${rezept.name}`))
    );
  }

  private handleError<T>(operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
