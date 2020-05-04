import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TouchDeviceDetectService} from '../../services/touch-device-detect.service';

@Component({
  selector: 'app-rezept-overview',
  templateUrl: './rezept-overview.component.html',
  styleUrls: ['./rezept-overview.component.scss']
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

  isTouchDevice: boolean;

  SWIPE_ACTION = {LEFT: 'swipeleft', RIGHT: 'swiperight'};

  constructor(
    private touchDeviceService: TouchDeviceDetectService
  ) { }

  ngOnInit(): void {
    this.isTouchDevice = this.touchDeviceService.isTouchDevice();
  }

  /**
   * Ablehnen eines Rezeptes
   */
  onRecipeDiscarded(): void {
    this.recipeDiscarded.emit();
  }
}
