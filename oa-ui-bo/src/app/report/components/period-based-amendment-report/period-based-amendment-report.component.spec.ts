import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PeriodBasedAmendmentReportComponent } from './period-based-amendment-report.component';

describe('PeriodBasedAmendmentReportComponent', () => {
  let component: PeriodBasedAmendmentReportComponent;
  let fixture: ComponentFixture<PeriodBasedAmendmentReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PeriodBasedAmendmentReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PeriodBasedAmendmentReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
