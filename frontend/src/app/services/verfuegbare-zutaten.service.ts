import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

/**
 * Service für aktuell verfügbare Zutaten
 * TODO entfernen wenn Freitextzutaten möglich sind
 */
export class VerfuegbareZutatenService {

  private zutaten: string[];

  constructor() {
    this.zutaten = ['Ei', 'Mehl', 'Milch', 'Paniermehl', 'Fleisch', 'Fisch', 'Spinat'];
  }

  /**
   * Gibt eine Liste der erlaubten Zutaten zurück
   */
  getZutaten(): string[] {
    return this.zutaten;
  }
}
