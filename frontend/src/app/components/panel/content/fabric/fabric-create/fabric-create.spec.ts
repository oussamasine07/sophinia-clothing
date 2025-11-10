import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FabricCreate } from './fabric-create';

describe('FabricCreate', () => {
  let component: FabricCreate;
  let fixture: ComponentFixture<FabricCreate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FabricCreate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FabricCreate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
