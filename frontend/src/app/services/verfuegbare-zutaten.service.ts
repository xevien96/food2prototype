import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VerfuegbareZutatenService {

  private zutaten: string[];

  constructor() {
    this.zutaten = ['Ei', 'Mehl', 'Zucker', 'Karotte', 'Kartoffel'];
  }

  getZutaten(): string[] {
    return this.zutaten;
  }
}
