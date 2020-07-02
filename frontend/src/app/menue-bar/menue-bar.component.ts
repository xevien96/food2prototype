import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'app-menue-bar',
  templateUrl: './menue-bar.component.html',
  styleUrls: ['./menue-bar.component.scss']
})
/**
 * Komponente für eine Menübar
 */
export class MenueBarComponent implements OnInit {

  constructor(
    private location: Location
  ) { }

  ngOnInit(): void {
  }

  /**
   * Geht eine Seite zurück
   */
  goBack() {
    this.location.back();
  }
}
