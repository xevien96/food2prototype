import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TouchDeviceDetectService} from '../../services/touch-device-detect.service';
import {Recipe} from '../../modell/recipe';

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
  @Input('rezept') rezept: Recipe;

  /**
   * @event Event das bei Ablehnung eines Rezeptes ausgelöst wird
   */
  @Output() recipeDiscarded = new EventEmitter();

  @Output() recipeApproved = new EventEmitter<Recipe>();

  isTouchDevice: boolean;

  SWIPE_ACTION = {LEFT: 'swipeleft', RIGHT: 'swiperight'};

  constructor(
    private touchDeviceService: TouchDeviceDetectService
  ) { }

  ngOnInit(): void {
    this.isTouchDevice = this.touchDeviceService.isTouchDevice();
  }

  onRecipeApproved(): void{
    this.recipeApproved.emit(this.rezept);
  }

  /**
   * Ablehnen eines Rezeptes
   */
  onRecipeDiscarded(): void {
    this.recipeDiscarded.emit();
  }
}
