import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, ParamMap} from '@angular/router';
import {RezeptService} from '../services/rezept.service';
import {Recipe} from '../modell/recipe';
import {GroupService} from '../services/group.service';

@Component({
  selector: 'app-rezept-view',
  templateUrl: './rezepte-list-view.component.html',
  styleUrls: ['./rezepte-list-view.component.scss']
})
/**
 * Komponente zur Anzeige einer Menge von Rezepten
 */
export class RezepteListViewComponent implements OnInit {

  zutaten: string[];
  rezepte: Recipe[];
  currentPosition: number;

  /**
   * Konstruktor
   * @param route Routekomponente zur Extraction von URI-Parametern
   * @param rezepteService Service zur Ermittlung passender Rezepte
   */
  constructor(
    private route: ActivatedRoute,
    private rezepteService: RezeptService,
    private groupService: GroupService,
    private router: Router
  ) {
    this.currentPosition = 0;
    this.rezepte = [];
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(map => {
      this.zutaten = map.getAll('ingredients');
      this.rezepteService.getRezept(this.zutaten).subscribe(rez => this.rezepte = rez);
    });
  }

  /**
   * Reaktion auf die Ablehnung eines Rezeptes
   */
  onRecipeDiscarded(): void {
    this.currentPosition++;
  }

  onRecipeApproved(rezept: Recipe): void {
    this.groupService.putGroup(rezept, this.zutaten).subscribe(() => {
      this.router.navigate(['zutaten']);
    });
  }

}
