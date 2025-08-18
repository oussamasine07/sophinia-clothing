import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesignList } from './design-list';

describe('DesignList', () => {
  let component: DesignList;
  let fixture: ComponentFixture<DesignList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DesignList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DesignList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
