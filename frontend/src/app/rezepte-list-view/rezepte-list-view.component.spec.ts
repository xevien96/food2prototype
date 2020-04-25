import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezepteListViewComponent } from './rezepte-list-view.component';

describe('RezepteListViewComponent', () => {
  let component: RezepteListViewComponent;
  let fixture: ComponentFixture<RezepteListViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezepteListViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezepteListViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
