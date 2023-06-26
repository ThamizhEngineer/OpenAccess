import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SesDetailComponent } from './ses-detail.component';

describe('EaDetailComponent', () => {
  let component: SesDetailComponent;
  let fixture: ComponentFixture<SesDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SesDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SesDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
