import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesignUpdate } from './design-update';

describe('DesignUpdate', () => {
  let component: DesignUpdate;
  let fixture: ComponentFixture<DesignUpdate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DesignUpdate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DesignUpdate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
