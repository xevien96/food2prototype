import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, ParamMap} from '@angular/router';
import {RezeptService} from '../services/rezept.service';

@Component({
  selector: 'app-rezept-view',
  templateUrl: './rezepte-list-view.component.html',
  styleUrls: ['./rezepte-list-view.component.css']
})
export class RezepteListViewComponent implements OnInit {

  rezepte: string[];
  currentPosition: number;

  constructor(
    private route: ActivatedRoute,
    private rezepteService: RezeptService
  ) {
    this.currentPosition = 0;
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(map => {
      const zutaten = map.getAll('ingredients');
      this.rezepteService.getRezept(zutaten).subscribe(rez => this.rezepte = rez);
    });
  }

  onRecipeDiscarded(): void {
    this.currentPosition++;
  }

}
