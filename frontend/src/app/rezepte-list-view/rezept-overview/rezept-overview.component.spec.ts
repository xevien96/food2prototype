import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezeptOverviewComponent } from './rezept-overview.component';

describe('RezeptOverviewComponent', () => {
  let component: RezeptOverviewComponent;
  let fixture: ComponentFixture<RezeptOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezeptOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezeptOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
