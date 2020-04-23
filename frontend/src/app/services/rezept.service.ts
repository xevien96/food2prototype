import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RezeptService {

  readonly rezeptUrl: string = '';

  constructor(
    private client: HttpClient
  ) { }

  getRezept(zutaten: string[]): Observable<string[]> {
    console.log(this.rezeptUrl + '/zutaten=' + encodeURIComponent(JSON.stringify(zutaten)));
    // return this.client.get<string[]>(this.rezeptUrl + '/zutaten=' + encodeURIComponent(JSON.stringify(zutaten)));
    return of([]);
  }
}
