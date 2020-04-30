import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
/**
 * Service für Operationen im Zusammenhang mit Rezepten
 */
export class RezeptService {

  private readonly rezeptUrl: string = 'http://localhost:8080/recipe';

  /**
   * Konstruktor
   * @param client HTTP-Client für die Kommunikation mit dem Server
   */
  constructor(
    private client: HttpClient
  ) { }

  /**
   * Getter für Rezepte, die anhand einer Liste von Zutaten ermittelt werden soll
   * @param zutaten Liste der Zutaten die enthalten sein sollen
   */
  getRezept(zutaten: string[]): Observable<string[]> {
    return this.client.get<string[]>(this.rezeptUrl + '/search', {params: {ingredients: zutaten}});
    //return of(['Kuchen', 'Pfannkuchen']);
  }
}
