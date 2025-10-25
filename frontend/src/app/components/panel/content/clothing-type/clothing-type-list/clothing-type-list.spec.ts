import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClothingTypeList } from './clothing-type-list';

describe('ClothingTypeList', () => {
  let component: ClothingTypeList;
  let fixture: ComponentFixture<ClothingTypeList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClothingTypeList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClothingTypeList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
