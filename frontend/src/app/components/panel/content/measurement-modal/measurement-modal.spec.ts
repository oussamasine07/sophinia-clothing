import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeasurementModal } from './measurement-modal';

describe('MeasurementModal', () => {
  let component: MeasurementModal;
  let fixture: ComponentFixture<MeasurementModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MeasurementModal]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MeasurementModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
