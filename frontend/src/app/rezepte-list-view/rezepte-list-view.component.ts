import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, ParamMap} from '@angular/router';
import {RezeptService} from '../services/rezept.service';

@Component({
  selector: 'app-rezept-view',
  templateUrl: './rezepte-list-view.component.html',
  styleUrls: ['./rezepte-list-view.component.css']
})
/**
 * Komponente zur Anzeige einer Menge von Rezepten
 */
export class RezepteListViewComponent implements OnInit {

  rezepte: string[];
  currentPosition: number;

  /**
   * Konstruktor
   * @param route Routekomponente zur Extraction von URI-Parametern
   * @param rezepteService Service zur Ermittlung passender Rezepte
   */
  constructor(
    private route: ActivatedRoute,
    private rezepteService: RezeptService
  ) {
    this.currentPosition = 0;
    this.rezepte = [];
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(map => {
      const zutaten = map.getAll('ingredients');
      this.rezepteService.getRezept(zutaten).subscribe(rez => this.rezepte = rez);
    });
  }

  /**
   * Reaktion auf die Ablehnung eines Rezeptes
   */
  onRecipeDiscarded(): void {
    this.currentPosition++;
  }

}
