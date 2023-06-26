import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PeriodBasedApprovalReportComponent } from './period-based-approval-report.component';

describe('PeriodBasedApprovalReportComponent', () => {
  let component: PeriodBasedApprovalReportComponent;
  let fixture: ComponentFixture<PeriodBasedApprovalReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PeriodBasedApprovalReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PeriodBasedApprovalReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
