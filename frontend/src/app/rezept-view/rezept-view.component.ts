import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-rezept-view',
  templateUrl: './rezept-view.component.html',
  styleUrls: ['./rezept-view.component.css']
})
export class RezeptViewComponent implements OnInit {

  public static readonly ri: string = 'rezepte/';

  constructor() { }

  ngOnInit(): void {
  }

}
