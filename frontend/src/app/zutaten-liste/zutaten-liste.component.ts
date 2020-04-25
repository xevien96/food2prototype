import {Component, ElementRef, OnInit, ViewChild, AfterViewChecked} from '@angular/core';
import { VerfuegbareZutatenService } from '../services/verfuegbare-zutaten.service';
import {Router} from '@angular/router';
import {RezeptViewComponent} from '../rezept-view/rezept-view.component';
import {FormBuilder, FormGroup, FormArray, Validators} from '@angular/forms';
import {query} from '@angular/animations';

@Component({
  selector: 'app-zutaten-liste',
  templateUrl: './zutaten-liste.component.html',
  styleUrls: ['./zutaten-liste.component.css']
})
export class ZutatenListeComponent implements OnInit, AfterViewChecked {

  public static readonly ri: string = 'zutaten';

  @ViewChild('list') private list: ElementRef;

  selectedZutaten: string[];
  zutaten: string[];

  zutatenForm = this.fb.group({
    zutaten: this.fb.array([
      this.fb.control('', Validators.required),
      this.fb.control('', Validators.required)
    ])
  });

  constructor(
    private zutatenService: VerfuegbareZutatenService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.zutaten = zutatenService.getZutaten();
    this.selectedZutaten = ['0', '1'];
  }

  ngOnInit(): void {
  }

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  scrollToBottom(): void {
    try {
      window.scrollTo(0, this.list.nativeElement.offsetHeight);
    }
    catch (err) {
    }
  }

  addZutat(): void {
    this.selectedZutaten.push('' + this.selectedZutaten.length);
    this.zutatenControls.push(this.fb.control('', Validators.required));
  }

  removeZutat(): void {
    if (this.selectedZutaten.length > 2) {
      this.selectedZutaten.pop();
      this.zutatenControls.removeAt(this.zutatenControls.length - 1);
    }
  }

  setZutat(pos: number, zutat: string): void {
    if (!this.selectedZutaten.includes(zutat)) {
      this.selectedZutaten[pos] = zutat;
    }
  }

  toRezept(){
    if (this.selectedZutaten.map<boolean>(zutat => this.zutaten.includes(zutat)).reduce((first, second) => first && second)){
      this.router.navigate(['recipe/search'], {queryParams: {ingredients: this.selectedZutaten}});
    }
    else {
      console.log('Es wurd eine nicht existente Zutat ausgewählt');
    }
  }

  get zutatenControls(): FormArray {
    return this.zutatenForm.get('zutaten') as FormArray;
  }
}
