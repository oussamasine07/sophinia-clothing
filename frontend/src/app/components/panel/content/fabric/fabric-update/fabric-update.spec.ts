import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FabricUpdate } from './fabric-update';

describe('FabricUpdate', () => {
  let component: FabricUpdate;
  let fixture: ComponentFixture<FabricUpdate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FabricUpdate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FabricUpdate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
