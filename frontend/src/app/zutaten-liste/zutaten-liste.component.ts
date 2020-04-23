import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { VerfuegbareZutatenService } from '../services/verfuegbare-zutaten.service';

@Component({
  selector: 'app-zutaten-liste',
  templateUrl: './zutaten-liste.component.html',
  styleUrls: ['./zutaten-liste.component.css']
})
export class ZutatenListeComponent implements OnInit {

  selectedZutaten: string[];
  zutaten: string[];

  constructor(
    zutatenService: VerfuegbareZutatenService
  ) {
    this.zutaten = zutatenService.getZutaten();
    this.selectedZutaten = ['', ''];
  }

  ngOnInit(): void {
  }

  addZutat(i: number): void {
    this.selectedZutaten.push('');
  }

  removeZutat(): void {
    if (this.selectedZutaten.length > 2) {
      this.selectedZutaten.pop();
    }
  }

  setZutat(pos: number, zutat: string): void {
    if (!this.selectedZutaten.includes(zutat)) {
      this.selectedZutaten[pos] = zutat;
    }
  }

  getSelectableZutaten(selected: string): string[] {
    return this.zutaten.filter(item => item === selected || !this.selectedZutaten.includes(item));
  }
}
