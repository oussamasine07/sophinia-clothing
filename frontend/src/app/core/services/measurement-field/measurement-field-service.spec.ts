import { TestBed } from '@angular/core/testing';

import { MeasurementFieldService } from './measurement-field-service';

describe('MeasurementFieldService', () => {
  let service: MeasurementFieldService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MeasurementFieldService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
