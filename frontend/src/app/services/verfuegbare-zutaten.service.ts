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
    this.zutaten = ['Eier', 'Mehl', 'Milch', 'Pommes', 'Schnitzel', 'Semmelbrösel', 'Sahne', 'Kartoffel', 'Spinat', 'Nudeln', 'Parmesan', 'Schinken', 'Knoblauch', 'Olivenöl', 'Petersilie', 'Chilischote', 'Hackfleisch', 'Tomate', 'Zwiebel', 'Tomatenmark', 'Linsen', 'Chilipulver', 'Salat', 'Hühnerbrust', 'Senf', 'Erbsen', 'Mais', 'Joghurt', 'Mayonnaise', 'Gemüse', 'Fisch'];
    this.zutaten.sort();
  }

  /**
   * Gibt eine Liste der erlaubten Zutaten zurück
   */
  getZutaten(): string[] {
    return this.zutaten;
  }
}
