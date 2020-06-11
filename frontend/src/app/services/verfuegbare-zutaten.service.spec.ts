import { TestBed } from '@angular/core/testing';

import { VerfuegbareZutatenService } from './verfuegbare-zutaten.service';

describe('VerfuegbareZutatenService', () => {
  let service: VerfuegbareZutatenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VerfuegbareZutatenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
