import { TestBed } from '@angular/core/testing';

import { TouchDeviceDetectService } from './touch-device-detect.service';

describe('TouchDeviceDetectService', () => {
  let service: TouchDeviceDetectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TouchDeviceDetectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
