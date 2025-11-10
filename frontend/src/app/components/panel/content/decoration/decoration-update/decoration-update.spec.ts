import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DecorationUpdate } from './decoration-update';

describe('DecorationUpdate', () => {
  let component: DecorationUpdate;
  let fixture: ComponentFixture<DecorationUpdate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DecorationUpdate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DecorationUpdate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
