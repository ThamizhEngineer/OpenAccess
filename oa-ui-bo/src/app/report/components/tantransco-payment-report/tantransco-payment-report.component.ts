import { Component, OnInit } from "@angular/core";
import { Console } from "console";
import { CommonUtils } from "../../../shared/common/common-utils";
import { CommonService } from "../../../shared/common/common.service";
import { ExcelExportService } from "../../../shared/services/excelExport";
import { ReportService } from "../../services/report.service";
import { GenericReportInput } from "../../vo/GenericReportInput";
import { GenericReportOutput } from "../../vo/GenericReportOutput";
import { Criteria } from "../../vo/report";
import { DateAdapter } from '@angular/material/core';


@Component({
  selector: 'tantransco-payment-report',
  templateUrl: './tantransco-payment-report.component.html',
  providers: [CommonUtils]

})
export class TantranscoPaymentReport implements OnInit {
  criteria: Criteria;
  serviceNo:string;
  rows: Array<GenericReportOutput>;
  fuelTypeCode: string;
  OrgName: string;
  
  PaymentMonthYear:Date;
  fuelTypes: any;
  orgList = [];
  paymentStatus:string;
  paymentStatuslist = [
    { "key": "Y", "name": "PAID" },
    { "key": "N", "name": "NOT-PAID" }

  ]

  
  xls: any;
  flowTypes = [
    { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
    { "key": "THIRD-PARTY", "name": "THIRD-PARTY" },
    { "key": "STB", "name":"STB"}

  ]
  flowtype:string;
  Usertype: any;
  HasEdc: boolean=false;
  OrgName1: any;
    Month:  number;




  constructor(private service: ReportService, private excelService: ExcelExportService,
    private commonService: CommonService, private commonUtils: CommonUtils,private dateAdapter: DateAdapter<Date>) {
      this.Usertype = CommonUtils.getLoginUserTypeName();
      this.dateAdapter.setLocale('en-GB');


  }

  ngOnInit() {
    this.criteria = new Criteria();
    this.rows = [];
    
    this.fetchOrgList();
    
    this.fetchFuelTypes();
    if (this.Usertype == "EDC User" || this.Usertype == "DFC User") {
      this.HasEdc = true;
      this.OrgName = CommonUtils.getLoginUserEDC();
    }




  }
  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
        console.log(this.orgList.length)
        for (let i = 0; i < this.orgList.length; i++) {
         
  
          if (this.orgList[i].code == this.OrgName) {
        
            this.OrgName = this.orgList[i].code + "-" + this.orgList[i].name
            this.OrgName1 = this.orgList[i].id;
            
          }
  
        }
      },
      error => {
        console.error('Error loading states!');
      }
    );
    return this.orgList;
  }
  fetchFuelTypes() {
    this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x => {
      this.fuelTypes = x;
      console.log(this.fuelTypes)
    })
  }

 

  searchResults() {
    let genericRptInput: GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'TANTRANSCO-PAYMENT-REPORT';
    

    if (this.OrgName1 != "" &&
      this.OrgName1 != null) {
      genericRptInput.ip1 = this.OrgName1;
    }

    if (this.PaymentMonthYear != undefined &&
      this.PaymentMonthYear != null){
          this.Month = this.PaymentMonthYear.getMonth()+1;
        genericRptInput.ip2 = this.Month.toString();

        if(genericRptInput.ip2 != "10" && genericRptInput.ip2 != "11" && genericRptInput.ip2 != "12" ){

        genericRptInput.ip2 = "0"+genericRptInput.ip2;

        }
       genericRptInput.ip5 = this.PaymentMonthYear.getFullYear().toString();
       
      
    }

    if (this.serviceNo != "" &&
      this.serviceNo != null) {
      genericRptInput.ip3 = this.serviceNo;
    }

    
    if (this.paymentStatus != "" &&
    this.paymentStatus != null) {
    genericRptInput.ip4 = this.paymentStatus;
  }


    this.service.gettantranscoPaymentInfoReport(genericRptInput)
      .subscribe(
        items => {
          this.rows = items;

        this.xls=this.rows
        });
  }


  exportAsXLSX():any {
    let str = JSON.stringify(this.xls);
    
    str = str.replace(/\"op1\":/g, "\"SERVICE NO\":");
    str = str.replace(/\"op2\":/g, "\"VIRITUAL ACCOUNT NO\":");
    str = str.replace(/\"op3\":/g, "\"COMPANY NAME\":");
    str = str.replace(/\"op4\":/g, "\"BILL YEAR\":");
    str = str.replace(/\"op5\":/g, "\"BILL MONTH\":");
    str = str.replace(/\"op6\":/g, "\"TOTAL AMOUNT\":");
    
    
    
    this.xls = JSON.parse(str);
  
    this.xls.forEach(x =>{
      delete x.$$index;
      delete x.op7;delete x.op8;delete x.op9;delete x.op10;delete x.op11;delete x.op12;delete x.op13;
      delete x.op14;delete x.op15;delete x.op16;delete x.op17;delete x.op18;delete x.op19;delete x.op20;
      delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
      delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
      delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
    });
  
  this.excelService.exportAsExcelFile(this.xls, 'TanTransco Payment Info Report');
  this.searchResults();
  }

///To allow number only
numberFormat(event){
  this.commonService.numberOnly(event)
}

}
