import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DecorationList } from './decoration-list';

describe('DecorationList', () => {
  let component: DecorationList;
  let fixture: ComponentFixture<DecorationList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DecorationList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DecorationList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
