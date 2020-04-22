import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-zutaten-liste',
  templateUrl: './zutaten-liste.component.html',
  styleUrls: ['./zutaten-liste.component.css']
})
export class ZutatenListeComponent implements OnInit {

  zutaten: string[];

  constructor() {
    this.zutaten = [];
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
