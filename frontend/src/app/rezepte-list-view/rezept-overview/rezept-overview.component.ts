import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-rezept-overview',
  templateUrl: './rezept-overview.component.html',
  styleUrls: ['./rezept-overview.component.css']
})
/**
 * Komponente für einen Kurzüberblick über ein Rezept
 */
export class RezeptOverviewComponent implements OnInit {

  /**
   * Name des anzuzeigenden Rezepts
   */
  @Input('rezept') rezeptName: string;

  /**
   * @event Event das bei Ablehnung eines Rezeptes ausgelöst wird
   */
  @Output() recipeDiscarded = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  /**
   * Ablehnen eines Rezeptes
   */
  onRecipeDiscarded(): void {
    this.recipeDiscarded.emit();
  }
}