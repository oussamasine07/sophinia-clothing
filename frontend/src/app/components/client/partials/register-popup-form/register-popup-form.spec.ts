import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterPopupForm } from './register-popup-form';

describe('RegisterPopupForm', () => {
  let component: RegisterPopupForm;
  let fixture: ComponentFixture<RegisterPopupForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterPopupForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterPopupForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
