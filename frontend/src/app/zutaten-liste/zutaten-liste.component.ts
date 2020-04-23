import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { VerfuegbareZutatenService } from '../services/verfuegbare-zutaten.service';

@Component({
  selector: 'app-zutaten-liste',
  templateUrl: './zutaten-liste.component.html',
  styleUrls: ['./zutaten-liste.component.css']
})
export class ZutatenListeComponent implements OnInit {

  zutaten: string[];

  constructor(
    zutatenService: VerfuegbareZutatenService
  ) {
    this.zutaten = zutatenService.getZutaten();
  }

  ngOnInit(): void {
  }

  addZutat(i: number): void {
    this.zutaten.push('' + i);
  }

  removeZutat(): void {
    this.zutaten.pop();
  }

}
