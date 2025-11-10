import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClothingModelUpdate } from './clothing-model-update';

describe('ClothingModelUpdate', () => {
  let component: ClothingModelUpdate;
  let fixture: ComponentFixture<ClothingModelUpdate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClothingModelUpdate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClothingModelUpdate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
