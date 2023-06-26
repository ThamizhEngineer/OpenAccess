import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WegGeneratorReportComponent } from './weg-generator-report.component';

describe('WegGeneratorReportComponent', () => {
  let component: WegGeneratorReportComponent;
  let fixture: ComponentFixture<WegGeneratorReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WegGeneratorReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WegGeneratorReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
