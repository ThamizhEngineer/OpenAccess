import { Component, OnInit } from '@angular/core';
import { CommonService } from '../../../shared/common/common.service';
import { ReportService } from '../../services/report.service';
// import { error } from 'selenium-webdriver';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { GenericReportOutput } from '../../vo/GenericReportOutput';

@Component({
  selector: 'period-based-approval-report',
  templateUrl: './period-based-approval-report.component.html',
  styleUrls: ['./period-based-approval-report.component.scss']
})
export class PeriodBasedApprovalReportComponent implements OnInit {
  selectedAgreePeriod: string = "";
  selectedMonth:string = "";
  selectedYear:string= "";
  months = [];
  periodCodes = [];
  rows: Array<GenericReportOutput>;

  constructor(private service:ReportService,
     private commonService:CommonService) { }

  ngOnInit() {

    this.fetchMonths();
    this.fetchPeriodCodes();
  }

  fetchMonths(){
    this.months = this.commonService.fetchMonths();
  }

  fetchPeriodCodes(){
      this.commonService.fetchCodes('AGMT_TYPE_CODE').subscribe(
        result => {
          this.periodCodes = result;
        },
        error => {
            console.error('Error while loading Period codes');
            console.error(error);
        }
      );
  }

  searchReport(){
    let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'PERIOD-BASED-APPROVAL-REPORT';
    if(this.selectedAgreePeriod != "" && 
    this.selectedAgreePeriod != null)
      genericRptInput.ip1 = this.selectedAgreePeriod;
    if (this.selectedMonth != "" && 
    this.selectedMonth != null) 
      genericRptInput.ip2 = this.selectedMonth;
    if(this.selectedYear != "" && 
    this.selectedYear != null)
      genericRptInput.ip3 = this.selectedYear;

    this.service.getGenericReport(genericRptInput)
    .subscribe(items => this.rows = items);
  }
}
