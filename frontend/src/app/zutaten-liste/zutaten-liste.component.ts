import { Component, OnInit } from '@angular/core';
import { VerfuegbareZutatenService } from '../services/verfuegbare-zutaten.service';
import { RezeptService } from '../services/rezept.service';

@Component({
  selector: 'app-zutaten-liste',
  templateUrl: './zutaten-liste.component.html',
  styleUrls: ['./zutaten-liste.component.css']
})
export class ZutatenListeComponent implements OnInit {

  selectedZutaten: string[];
  zutaten: string[];

  constructor(
    private zutatenService: VerfuegbareZutatenService,
    private rezeptService: RezeptService
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

  getClick(){

  }
}
