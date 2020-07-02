import {Component, EventEmitter, Input, OnInit, Output, OnChanges, SimpleChanges} from '@angular/core';
import {TouchDeviceDetectService} from '../../services/touch-device-detect.service';
import {Recipe} from '../../modell/recipe';
import {RecipeStub} from '../../modell/recipe-stub';
import {RezeptService} from '../../services/rezept.service';
import {GroupService} from '../../services/group.service';

@Component({
  selector: 'app-rezept-overview',
  templateUrl: './rezept-overview.component.html',
  styleUrls: ['./rezept-overview.component.scss']
})
/**
 * Komponente für einen Kurzüberblick über ein Rezept
 */
export class RezeptOverviewComponent implements OnInit, OnChanges {

  /**
   * Stub des anzuzeigenden Rezepts
   */
  @Input() rezeptStub: RecipeStub;

  rezept: Recipe;

  /**
   * @event Event, dass bei Ablehnung eines Rezeptes ausgelöst wird
   */
  @Output() recipeDiscarded = new EventEmitter();

  /**
   * @event Event, dass bei Annahme eines Rezeptes ausgelöst wird
   */
  @Output() recipeApproved = new EventEmitter<RecipeStub>();

  isTouchDevice: boolean;

  SWIPE_ACTION = {LEFT: 'swipeleft', RIGHT: 'swiperight'};

  constructor(
    private touchDeviceService: TouchDeviceDetectService,
    private recipeService: RezeptService,
    public groupService: GroupService
  ) { }

  ngOnInit(): void {
    this.isTouchDevice = this.touchDeviceService.isTouchDevice();
  }

  /**
   * Holt neues Rezept bei Änderung des Rezept-Stubs
   */
  ngOnChanges(changes: SimpleChanges): void {
    if (this.rezeptStub !== undefined) {
      this.recipeService.getRezept(this.rezeptStub.recipeID).subscribe(rezept => this.rezept = rezept);
    } else {
      this.rezept = undefined;
    }
  }

  /**
   * Annahme eines Rezeptes
   */
  onRecipeApproved(): void{
    this.recipeApproved.emit(this.rezeptStub);
  }

  /**
   * Ablehnen eines Rezeptes
   */
  onRecipeDiscarded(): void {
    this.recipeDiscarded.emit();
  }
}
