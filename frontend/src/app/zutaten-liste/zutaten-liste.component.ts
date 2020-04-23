import { Component, OnInit } from '@angular/core';
import { VerfuegbareZutatenService } from '../services/verfuegbare-zutaten.service';
import {Router} from '@angular/router';
import {RezeptViewComponent} from '../rezept-view/rezept-view.component';

@Component({
  selector: 'app-zutaten-liste',
  templateUrl: './zutaten-liste.component.html',
  styleUrls: ['./zutaten-liste.component.css']
})
export class ZutatenListeComponent implements OnInit {

  public static readonly ri: string = 'zutaten';

  selectedZutaten: string[];
  zutaten: string[];

  constructor(
    private zutatenService: VerfuegbareZutatenService,
    private router: Router
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

  toRezept(){
    this.router.navigate([RezeptViewComponent.ri + JSON.stringify(this.selectedZutaten)]);
  }
}
