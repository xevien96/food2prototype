import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezeptViewComponent } from './rezept-view.component';

describe('RezeptViewComponent', () => {
  let component: RezeptViewComponent;
  let fixture: ComponentFixture<RezeptViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezeptViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezeptViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
