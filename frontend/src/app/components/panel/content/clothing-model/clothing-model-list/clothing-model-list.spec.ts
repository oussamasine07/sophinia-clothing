import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClothingModelList } from './clothing-model-list';

describe('ClothingModelList', () => {
  let component: ClothingModelList;
  let fixture: ComponentFixture<ClothingModelList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClothingModelList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClothingModelList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
