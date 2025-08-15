import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesignCreate } from './design-create';

describe('DesignCreate', () => {
  let component: DesignCreate;
  let fixture: ComponentFixture<DesignCreate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DesignCreate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DesignCreate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
