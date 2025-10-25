import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClothingModelCreate } from './clothing-model-create';

describe('ClothingModelCreate', () => {
  let component: ClothingModelCreate;
  let fixture: ComponentFixture<ClothingModelCreate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClothingModelCreate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClothingModelCreate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
