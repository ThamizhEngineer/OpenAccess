import { Component, OnInit } from '@angular/core';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { forEach } from '@angular/router/src/utils/collection';

@Component({
  selector: 'period-based-amendment-report',
  templateUrl: './period-based-amendment-report.component.html',
  styleUrls: ['./period-based-amendment-report.component.scss']
})
export class PeriodBasedAmendmentReportComponent implements OnInit {
  rows: Array<GenericReportOutput>;
  months = [];
  periodCodes = [];

  amendmentTypes = [
    { "key": "CAPTIVE-STATUS-CHANGE", "name": "CAPTIVE-STATUS-CHANGE" },
    { "key": "OAA-QUANTUM-REDUCTION", "name": "OAA-QUANTUM-REDUCTION" },
    { "key": "OAA-PERIOD-REDUCTION", "name": "OAA-PERIOD-REDUCTION" },
    { "key": "CANCELLATION", "name": "CANCELLATION" }
  ];
  
  selectedMonth:string = "";
  selectedYear:string = "";
  selectedAgreePeriod:string = "";
  selectedAmendmentType:string = "";

  constructor(private service:ReportService,
  private commonService: CommonService) { }

  ngOnInit() {
    this.months = this.commonService.fetchMonths();
    this.fetchPeriodCodes();
  }

  fetchPeriodCodes(){
    this.commonService.fetchCodes('AGMT_TYPE_CODE')
    .subscribe(
      result => {
        this.periodCodes = result;
      },
      error => {
        console.error('Error loading Period codes');
        console.error(error);
      }
    );
  }

  searchReport(){
    console.log('search in period based');
    let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'PERIOD-BASED-AMENDMENT-REPORT';
    if(this.selectedAgreePeriod != "" && 
    this.selectedAgreePeriod != null)
      genericRptInput.ip1 = this.selectedAgreePeriod;
    
      if(this.selectedAmendmentType != "" && 
    this.selectedAmendmentType != null)
      genericRptInput.ip2 = this.selectedAmendmentType;
    
    if(this.selectedMonth != "" && 
    this.selectedMonth != null) 
      genericRptInput.ip3 = this.selectedMonth;

    if(this.selectedYear != "" && 
    this.selectedYear != null)
      genericRptInput.ip4 = this.selectedYear;

      console.log(genericRptInput.ip1);
    console.log(genericRptInput.ip2);
    console.log(genericRptInput.ip3);
    console.log(genericRptInput.ip4);

    this.service.getGenericReport(genericRptInput)
    .subscribe(
      items => this.rows = items
    );
  }

}
