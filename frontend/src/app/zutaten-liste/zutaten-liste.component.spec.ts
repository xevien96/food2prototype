import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZutatenListeComponent } from './zutaten-liste.component';

describe('ZutatenListeComponent', () => {
  let component: ZutatenListeComponent;
  let fixture: ComponentFixture<ZutatenListeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZutatenListeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZutatenListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
