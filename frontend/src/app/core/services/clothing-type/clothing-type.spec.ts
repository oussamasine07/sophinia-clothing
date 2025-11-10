import { TestBed } from '@angular/core/testing';

import { ClothingTypeService } from './clothing-type-service';

describe('ClothingType', () => {
  let service: ClothingTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClothingTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
