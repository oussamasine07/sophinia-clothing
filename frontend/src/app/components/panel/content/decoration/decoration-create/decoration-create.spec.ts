import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DecorationCreate } from './decoration-create';

describe('DecorationCreate', () => {
  let component: DecorationCreate;
  let fixture: ComponentFixture<DecorationCreate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DecorationCreate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DecorationCreate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
