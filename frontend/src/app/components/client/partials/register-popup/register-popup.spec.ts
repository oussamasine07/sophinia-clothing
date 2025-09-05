import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterPopup } from './register-popup';

describe('RegisterPopup', () => {
  let component: RegisterPopup;
  let fixture: ComponentFixture<RegisterPopup>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterPopup]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterPopup);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
