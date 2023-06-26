import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { CommonUtils } from '../../../shared/common/common-utils';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';import { DatePipe } from '@angular/common';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from "@angular/material";

export class AppDateAdapter extends NativeDateAdapter {
  parse(value: any): Date | null {
   if ((typeof value === 'string') && (value.indexOf('/') > -1)) {
     const str = value.split('/');
     const year = Number(str[2]);
     const month = Number(str[1]) - 1;
     const date = Number(str[0]);
     return new Date(year, month, date);
   }
   const timestamp = typeof value === 'number' ? value : Date.parse(value);
   return isNaN(timestamp) ? null : new Date(timestamp);
 }

  format(date: Date, displayFormat: Object): string {
      if (displayFormat == "input") {
          let day = date.getDate();
          let month = date.getMonth() + 1;
          let year = date.getFullYear();
          return this._to2digit(day) + '/' + this._to2digit(month) + '/' + year;
      } else {
          return date.toDateString();
      }
  }

  private _to2digit(n: number) {
      return ('00' + n).slice(-2);
  } 
}

export const APP_DATE_FORMATS =
{
  parse: {
      dateInput: {month: 'short', year: 'numeric', day: 'numeric'}
  },
  display: {
      // dateInput: { month: 'short', year: 'numeric', day: 'numeric' },
      dateInput: 'input',
      monthYearLabel: { month: 'short', year: 'numeric', day: 'numeric' },
      dateA11yLabel: {year: 'numeric', month: 'long', day: 'numeric'},
      monthYearA11yLabel: {year: 'numeric', month: 'long'},
  }
}



@Component({
  selector: 'consumer-report',
  templateUrl: './consumer-report.component.html',
  providers: [CommonUtils,CommonService,MatDatepickerModule, MatNativeDateModule, DatePipe ,CommonUtils,
    {
   provide: DateAdapter, useClass: AppDateAdapter
},
{
   provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}
]
})

export class ConsumerReportComponent implements OnInit {
  rows: Array<GenericReportOutput>;
  months = [];
  orgList = [];
  fuelTypes = [];
  
  selStatus: string ;
  selOrgName: string ;
  selFlowType: string ;
  selIsCaptive: string ;
  isCaptives =[
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }  
  ] 
  flowTypeCode = [
    { "key": "CAPTIVE", "name": "CAPTIVE" },
    { "key": "THIRD-PARTY", "name": "THIRD-PARTY" },
    { "key": "IEX", "name": "IEX" }
  ]
  statuses = [
    { "key": "CREATED", "name": "CREATED" },
    { "key": "APPROVED", "name": "APPROVED" },
    { "key": "COMPLETED", "name": "COMPLETED" }
  ];


  constructor(private service:ReportService, private commonService:CommonService) { }

  ngOnInit() {
    console.log("Im in init");
    this.rows = [];
    this.months = this.commonService.fetchMonths();
    this.fetchEDC();

  }

  fetchEDC(){
    this.commonService.fetchEDCs()
    .subscribe(
      edcs => {this.orgList = edcs;},
      error => {
        console.log("Error loading EDCs");
        console.log(error);
      }
    );
  }


  searchResults(){
    let genRptInput:GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'CONSUMER-REPORT';
    
    if(this.selStatus != "" && 
    this.selStatus != null)
      genRptInput.ip1 = this.selStatus;

    if(this.selFlowType != "" && 
    this.selFlowType != null)
      genRptInput.ip3 = this.selFlowType;

    // if(this.selOrgName != "" && 
    // this.selOrgName != null)
    //   genRptInput.ip3 = this.selOrgName;

    // if(this.selVoltage != "" && 
    // this.selVoltage != null)
    //   genRptInput.ip4 = this.selVoltage;

    if(this.selIsCaptive != "" && 
    this.selIsCaptive != null)
      genRptInput.ip2 = this.selIsCaptive;

    this.service.getGenericReport(genRptInput)
    .subscribe(items => this.rows = items);
    
    console.log(genRptInput.reportName);
    console.log(genRptInput.ip1);
    console.log(genRptInput.ip2);
    console.log(genRptInput.ip3);
    console.log(genRptInput.ip4);
    console.log(genRptInput.ip5);
  }

}
         