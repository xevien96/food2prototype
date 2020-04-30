import {Component, ElementRef, OnInit, ViewChild, AfterViewChecked} from '@angular/core';
import { VerfuegbareZutatenService } from '../services/verfuegbare-zutaten.service';
import {Router} from '@angular/router';
import {RezepteListViewComponent} from '../rezepte-list-view/rezepte-list-view.component';
import {FormBuilder, FormGroup, FormArray, Validators} from '@angular/forms';
import {query} from '@angular/animations';

@Component({
  selector: 'app-zutaten-liste',
  templateUrl: './zutaten-liste.component.html',
  styleUrls: ['./zutaten-liste.component.css']
})
/**
 * Auswahlformular für Zutaten
 */
export class ZutatenListeComponent implements OnInit, AfterViewChecked {

  @ViewChild('list') private list: ElementRef;

  selectedZutaten: string[];
  zutaten: string[];

  zutatenForm = this.fb.group({
    zutaten: this.fb.array([
      this.fb.control('', Validators.required),
      this.fb.control('', Validators.required)
    ])
  });

  private addedZutat: boolean;

  /**
   * Konstructor
   * @param zutatenService Service zur übergabe von erlaubten Zutaten TODO entfernen wenn Freitext erlaubt
   * @param router Router für Navigation
   * @param fb FormBuilder zum Aufbau des Formulars
   */
  constructor(
    private zutatenService: VerfuegbareZutatenService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.zutaten = zutatenService.getZutaten();
    this.selectedZutaten = ['0', '1'];
    this.addedZutat = false;
  }

  ngOnInit(): void {
  }

  /**
   * Scrollt automatisch nach unten wenn eine neue Zutat hinzugefügt wurde
   */
  ngAfterViewChecked(): void {
    if (this.addedZutat) {
      this.scrollToBottom();
      this.addedZutat = false;
    }
  }

  /**
   * Scrollt das aktuelle Fenster nach unten
   */
  scrollToBottom(): void {
    try {
      window.scrollTo(0, this.list.nativeElement.offsetHeight);
    }
    catch (err) {
    }
  }

  /**
   * Fügt Platz für eine neue Zutat hinzu
   */
  addZutat(): void {
    this.selectedZutaten.push('' + this.selectedZutaten.length);
    this.zutatenControls.push(this.fb.control('', Validators.required));
    this.addedZutat = true;
  }

  /**
   * Entfernt die letzte hinzugefügte Zutat.
   * Entfernt nicht wenn nur noch zwei Zutaten vorhanden sind
   */
  removeZutat(): void {
    if (this.selectedZutaten.length > 2) {
      this.selectedZutaten.pop();
      this.zutatenControls.removeAt(this.zutatenControls.length - 1);
    }
  }

  /**
   * Setzt die selektierte Zutat an einer bestimmten Position
   * @param pos Position an der eine Zutat eingefügt werden soll
   * @param zutat Zutat die eingefügt werden soll
   */
  setZutat(pos: number, zutat: string): void {
    if (!this.selectedZutaten.includes(zutat)) {
      this.selectedZutaten[pos] = zutat;
    }
  }

  /**
   * Navigiert zur Rezeptansicht
   */
  toRezept(){
    if (this.selectedZutaten.map<boolean>(zutat => this.zutaten.includes(zutat)).reduce((first, second) => first && second)){
      this.router.navigate(['recipe/search'], {queryParams: {ingredients: this.selectedZutaten}});
    }
    else {
      console.log('Es wurd eine nicht existente Zutat ausgewählt');
    }
  }

  /**
   * Getter für die Controls der Zutatenauswahlboxen
   */
  get zutatenControls(): FormArray {
    return this.zutatenForm.get('zutaten') as FormArray;
  }
}
