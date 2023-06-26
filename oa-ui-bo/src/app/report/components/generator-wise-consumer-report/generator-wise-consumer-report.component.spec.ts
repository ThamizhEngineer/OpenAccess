import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneratorWiseConsumerReportComponent } from './generator-wise-consumer-report.component';

describe('GeneratorWiseConsumerReportComponent', () => {
  let component: GeneratorWiseConsumerReportComponent;
  let fixture: ComponentFixture<GeneratorWiseConsumerReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GeneratorWiseConsumerReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneratorWiseConsumerReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
