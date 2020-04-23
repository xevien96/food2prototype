import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, ParamMap} from '@angular/router';
import {RezeptService} from '../services/rezept.service';

@Component({
  selector: 'app-rezept-view',
  templateUrl: './rezept-view.component.html',
  styleUrls: ['./rezept-view.component.css']
})
export class RezeptViewComponent implements OnInit {

  public static readonly ri: string = 'rezepte/';

  private rezepte: string[]

  constructor(
    private route: ActivatedRoute,
    private rezepteService: RezeptService
  ) { }

  ngOnInit(): void {
    const zutaten: string[] = JSON.parse(this.route.snapshot.paramMap.get('zutaten'));
    this.rezepteService.getRezept(zutaten).subscribe(rezepte => this.rezepte = rezepte);
  }

}
