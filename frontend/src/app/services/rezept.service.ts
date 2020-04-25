import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RezeptService {

  private readonly rezeptUrl: string = 'recipe';

  constructor(
    private client: HttpClient
  ) { }

  getRezept(zutaten: string[]): Observable<string[]> {
    // return this.client.get<string[]>(this.rezeptUrl + '/search', {params: {ingredients: zutaten}});
    return of(['Kuchen', 'Pfannkuchen']);
  }
}
