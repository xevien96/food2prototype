import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, ParamMap} from '@angular/router';
import {RezeptService} from '../services/rezept.service';

@Component({
  selector: 'app-rezept-view',
  templateUrl: './rezept-view.component.html',
  styleUrls: ['./rezept-view.component.css']
})
export class RezeptViewComponent implements OnInit {

  rezepte: string[]

  constructor(
    private route: ActivatedRoute,
    private rezepteService: RezeptService
  ) { }

  ngOnInit(): void {

  }

}
