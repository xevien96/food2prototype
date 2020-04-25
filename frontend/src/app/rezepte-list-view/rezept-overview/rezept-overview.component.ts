import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-rezept-overview',
  templateUrl: './rezept-overview.component.html',
  styleUrls: ['./rezept-overview.component.css']
})
export class RezeptOverviewComponent implements OnInit {

  @Input('rezept') rezeptName: string;
  @Output() recipeDiscarded = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  onRecipeDiscarded(): void {
    this.recipeDiscarded.emit();
  }
}
