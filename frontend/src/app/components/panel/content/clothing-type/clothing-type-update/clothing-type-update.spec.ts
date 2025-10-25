import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClothingTypeUpdate } from './clothing-type-update';

describe('ClothingTypeUpdate', () => {
  let component: ClothingTypeUpdate;
  let fixture: ComponentFixture<ClothingTypeUpdate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClothingTypeUpdate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClothingTypeUpdate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
