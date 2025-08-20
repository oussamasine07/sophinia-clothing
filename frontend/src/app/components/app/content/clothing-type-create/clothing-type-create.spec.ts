import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClothingTypeCreate } from './clothing-type-create';

describe('ClothingTypeCreate', () => {
  let component: ClothingTypeCreate;
  let fixture: ComponentFixture<ClothingTypeCreate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClothingTypeCreate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClothingTypeCreate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
