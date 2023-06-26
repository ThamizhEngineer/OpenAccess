import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { DatePipe } from '@angular/common';
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { MatDatepickerModule, MatNativeDateModule } from '@angular/material';

import { AppDateAdapter, APP_DATE_FORMATS } from '../../../ledger/energy-ledger/components/energy-ledger-list/energy-ledger-list.component';


@Component({
  selector: 'total-transaction-details',
  templateUrl: './total-transaction-details.component.html',
  styleUrls: ['./total-transaction-details.component.scss'],
  providers: [CommonService, MatDatepickerModule, MatNativeDateModule, DatePipe,
    { 
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})

export class TotalTransactionDetailsComponent implements OnInit {
  selectedTrnFromDate:string = "";
  selectedTrnToDate:string = "";
  selectedAgreePeriod:string = "";

  rows : Array<GenericReportOutput>;
  periodCodes = [];

  constructor(private service:ReportService,
    private commonService:CommonService,
    private datePipe:DatePipe) { }

  ngOnInit() {
    this.fetchPeriodCodes();
}

  fetchPeriodCodes(){
    this.commonService.fetchCodes("AGMT_TYPE_CODE")
    .subscribe(
      result => {
        this.periodCodes = result;
      },
      error => {
        console.error("Error upon loading period codes");
        console.error(error);
      }
    );
  }

  searchReport() {
    let genericRptInput: GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = "TOTAL-TRANSACTION-DETAILS";
    if(this.selectedTrnFromDate != "" && 
    this.selectedTrnFromDate != null){
      
        console.log(this.selectedTrnFromDate);
        this.selectedTrnFromDate = this.datePipe.transform(this.selectedTrnFromDate,
          'yyyy-MM-dd');
          console.log(this.selectedTrnFromDate);
        genericRptInput.ip1 = this.selectedTrnFromDate;
        //let dt:Date = new Date(
    }

    if(this.selectedTrnToDate != "" && 
        this.selectedTrnToDate != null){
          console.log(this.selectedTrnToDate);
          this.selectedTrnToDate = this.datePipe.transform(this.selectedTrnToDate,
          'yyyy-MM-dd');
          console.log(this.selectedTrnToDate);
        genericRptInput.ip2 = this.selectedTrnToDate;
    }
    if(this.selectedAgreePeriod != "" && 
    this.selectedAgreePeriod != null)
        genericRptInput.ip3 = this.selectedAgreePeriod;
    
    console.log(genericRptInput.ip1);
    console.log(genericRptInput.ip2);
    console.log(genericRptInput.ip3);

    this.service.getGenericReport(genericRptInput)
    .subscribe(items => this.rows = items);

  }
}
