import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, ParamMap} from '@angular/router';
import {RezeptService} from '../services/rezept.service';
import {Recipe} from '../modell/recipe';
import {GroupService} from '../services/group.service';
import {RecipeStub} from '../modell/recipe-stub';

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
  rezepteStubs: RecipeStub[];
  currentPosition: number;

  constructor(
    private route: ActivatedRoute,
    private rezepteService: RezeptService,
    private gruppenService: GroupService,
    private router: Router
  ) {
    this.currentPosition = 0;
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(map => {
      this.zutaten = map.getAll('ingredients');
      this.rezepteService.getRezepte(this.zutaten).subscribe(ret =>  this.rezepteStubs = ret);
    });
  }

  /**
   * Reaktion auf die Ablehnung eines Rezeptes
   */
  onRecipeDiscarded(): void {
    this.currentPosition++;
  }

  /**
   * Reaktion auf Annahme eines Rezeptes
   * @param recipe Angenommenes Rezept
   */
  onRecipeApproved(recipe: RecipeStub): void {
    this.gruppenService.addUserToGroup(recipe, this.zutaten).subscribe(val => {
      this.router.navigate([`group/${val}`]);
    });
  }

}
