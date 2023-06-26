import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { CommonUtils } from '../../../shared/common/common-utils';


@Component({
  selector: 'sale-to-board-ledger',
  templateUrl: './sale-to-board-ledger.component.html',
//   styleUrls: ['./consolidate-energy-adjustment-report.component.scss']
})
export class SaleToBoardLedgerComponent implements OnInit {
  stb: any;
  stmtMonth: string;
  stmtYear: string;
  dispOrgCode: string;
  fuelTypeCode: string;
  fuelTypes: any;
  edcList: any;
  pr: any;
  months: {

    value: string; name: string;
  }[];
  edc: any;
  disableEdc: boolean = false;
  years=[];

  
  totalop4: any;
  totalop5: any;
  totalop6: any;
  totalop7: any;
  totalop8: any;
  totalop9: any;

  totalop10: any;
  totalop11: any;
  totalop12: any;
  totalop13: any;

  totalop14: any;
  totalop15: any;
  totalop16: any;
  totalop17: any;
  totalop18: any;
  totalop19: any;

  totalop20: any;
  totalop21: any;
  totalop22: any;
  totalop23: any;
  totalop24: any;
  totalop25: any;

  totalop26: any;
  totalop27: any;
  uer: any;
  datePipe: any;
  


    constructor(private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) { 

    }

    ngOnInit() {
      console.log(CommonUtils.getLoginUserEDC());
      this.edc = CommonUtils.getLoginUserEDC();
      this.years = this.commonService.fetchYearList();
  
    if (this.edc != "") {
      console.log("in edc select")
      this.dispOrgCode = this.edc;
      this.disableEdc = true;
    }
      this.fetchFuelTypes();
      this.fetchOrgList();
      this.fetchMonths();
      }



     searchResults(){
      let genRptInput:GenericReportInput = new GenericReportInput();
      genRptInput.reportName = 'SALE-TO-BOARD-LEDGER';     

        if(this.stmtMonth != "" && 
      this.stmtMonth != null)
        genRptInput.ip1 = this.stmtMonth

        if(this.stmtYear != "" && 
      this.stmtYear != null)
        genRptInput.ip2 = this.stmtYear

        if(this.dispOrgCode != "" && 
      this.dispOrgCode != null)
        genRptInput.ip3 = this.dispOrgCode

        if(this.fuelTypeCode != "" && 
      this.fuelTypeCode != null)
        genRptInput.ip4 = this.fuelTypeCode
      
        console.log(genRptInput)

        this.service.getAllTnerc(genRptInput).subscribe(x=>{
          this.stb=x;
          console.log(genRptInput)
          console.log(this.stb)
         
         const Op4 = this.stb.reduce((sum, item) => sum + Number(item.op4), 0);
         this.totalop4=Op4.toFixed(2)
         const Op5 = this.stb.reduce((sum, item) => sum + Number(item.op5), 0);
         this.totalop5=Op5.toFixed(2)
         const Op10 = this.stb.reduce((sum, item) => sum + Number(item.op10), 0);
         this.totalop10=Op10.toFixed(2)
         const Op11 = this.stb.reduce((sum, item) => sum + Number(item.op11), 0);
         this.totalop11=Op11.toFixed(2)
         const Op12 = this.stb.reduce((sum, item) => sum + Number(item.op12), 0);
         this.totalop12=Op12.toFixed(2)
         const Op13 = this.stb.reduce((sum, item) => sum + Number(item.op13), 0);
         this.totalop13=Op13.toFixed(2)

         const Op14 = this.stb.reduce((sum, item) => sum + Number(item.op14), 0);
         this.totalop14=Op14.toFixed(2)
         const Op15 = this.stb.reduce((sum, item) => sum + Number(item.op15), 0);
         this.totalop15=Op15.toFixed(2)
         const Op16 = this.stb.reduce((sum, item) => sum + Number(item.op16), 0);
         this.totalop16=Op16.toFixed(2)
         const Op17 = this.stb.reduce((sum, item) => sum + Number(item.op17), 0);
         this.totalop17=Op17.toFixed(2)
         const Op18 = this.stb.reduce((sum, item) => sum + Number(item.op18), 0);
         this.totalop18=Op18.toFixed(2)
         const Op19 = this.stb.reduce((sum, item) => sum + Number(item.op19), 0);
         this.totalop19=Op19.toFixed(2)
         const Op20 = this.stb.reduce((sum, item) => sum + Number(item.op20), 0);
         this.totalop20=Op20.toFixed(2)
         const Op21 = this.stb.reduce((sum, item) => sum + Number(item.op21), 0);
         this.totalop21=Op21.toFixed(2)
         const Op22 = this.stb.reduce((sum, item) => sum + Number(item.op22), 0);
         this.totalop22=Op22.toFixed(2)
         const Op23 = this.stb.reduce((sum, item) => sum + Number(item.op23), 0);
         this.totalop23=Op23.toFixed(2)
         const Op24 = this.stb.reduce((sum, item) => sum + Number(item.op24), 0);
         this.totalop24=Op24.toFixed(2)
         const Op25 = this.stb.reduce((sum, item) => sum + Number(item.op25), 0);
         this.totalop25=Op25.toFixed(2)
         const Op26 = this.stb.reduce((sum, item) => sum + Number(item.op26), 0);
         this.totalop26=Op26.toFixed(2)
         const Op27 = this.stb.reduce((sum, item) => sum + Number(item.op27), 0);
         this.totalop27=Op27.toFixed(2)
      })

      console.log(genRptInput.reportName);
      console.log(genRptInput.ip1);
      console.log(genRptInput.ip2);
      console.log(genRptInput.ip3);
      console.log(genRptInput.ip4);
     
    }

    fetchFuelTypes(){
      this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x=>{
      this.fuelTypes=x;
      console.log(this.fuelTypes)
  })
}

fetchOrgList() {
  this.commonService.fetchEDCs().subscribe(
    result => {
      this.edcList = result;
      console.log(this.edcList)
    },
    error => {
      console.error('Error loading states!');
      console.error(error);
    }
  );
}

fetchMonths(){
  this.months = this.commonService.fetchMonths();
  console.log(this.months)
}

openDownload(data: Response) {
  let content_type = data.headers.get('Content-type');
  let x_filename = "sale-to-board-ledger-report.pdf";
  console.log(content_type)
  saveAs(data.blob(), x_filename);
}

printReport(){ 
  this.service.printStbLedger(this.stmtMonth,this.stmtYear,this.dispOrgCode,this.fuelTypeCode).subscribe(xs=>{
    this.pr=xs;
    this.openDownload(xs);

  });
}

exportAsXLSX():any {
  

  this.stb.push({op1:"TOTAL",op2:"",op3:"",op4:this.totalop4,op5:this.totalop5,op6:"",op7:"",op8:"",op9:"",op10:this.totalop10,op11:this.totalop11,op12:this.totalop12,op13:this.totalop13,op14:this.totalop14,op15:this.totalop15,op16:this.totalop16,op17:"",op18:"",
  op19:"",op20:"",op21:this.totalop21,op22:this.totalop22,op23:this.totalop23,op24:this.totalop24,op25:this.totalop25,op26:this.totalop26,op27:this.totalop27})
  let str = JSON.stringify(this.stb);
  str = str.replace(/\"op1\":/g, "\"SiNo\":");
  str = str.replace(/\"op2\":/g, "\"EDC\":");
  str = str.replace(/\"op3\":/g, "\"SERVICE NO\":");
  str = str.replace(/\"op4\":/g, "\"COMPANY\":");
  str = str.replace(/\"op5\":/g, "\"MC\":");
  str = str.replace(/\"op6\":/g, "\"MF\":");
  str = str.replace(/\"op7\":/g, "\"FUEL\":");
  str = str.replace(/\"op8\":/g, "\"SUB-STAT\":");
  str = str.replace(/\"op9\":/g, "\"SS Type\":");
  str = str.replace(/\"op10\":/g, "\"FLOW TYPE\":");
  str = str.replace(/\"op11\":/g, "\"TOTAL EXP\":");
  str = str.replace(/\"op12\":/g, "\"TOTAL IMP\":");
  str = str.replace(/\"op13\":/g, "\"NET GEN\":");
  str = str.replace(/\"op14\":/g, "\"TRF RATE\":");
  str = str.replace(/\"op15\":/g, "\"TRF NET AMOUNT\":");
  str = str.replace(/\"op16\":/g, "\"NET PAYABLE\":");
  str = str.replace(/\"op17\":/g, "\"STMT GEN DATE\":");
  str = str.replace(/\"op18\":/g, "\"STMT MONTH\":");
  str = str.replace(/\"op19\":/g, "\"STMT YEAR\":");
  str = str.replace(/\"op20\":/g, "\"INIT STMT DATE\":");
  str = str.replace(/\"op21\":/g, "\"FINAL STMT DATE\":");
  str = str.replace(/\"op22\":/g, "\"METER RD CHARGES\":");
  str = str.replace(/\"op23\":/g, "\"OM CHARGES\":");
  str = str.replace(/\"op24\":/g, "\"TRANS CHARGES\":");
  str = str.replace(/\"op25\":/g, "\"SYS OP CHARGES\":");
  str = str.replace(/\"op26\":/g, "\"RKVAH PENALTY\":");
  str = str.replace(/\"op27\":/g, "\"IMP ENG CHARGES\":");
  str = str.replace(/\"op27\":/g, "\"SCHE CHARGES\":");

  this.stb = JSON.parse(str);
  this.stb.forEach(x =>{
    delete x.$$index;
    delete x.op28
    delete x.op29
    delete x.op30
    delete x.op31
    delete x.op32
    delete x.op33
    delete x.op34
    delete x.op35
    delete x.op36
    delete x.op37
    delete x.op38
    delete x.op39
    delete x.op40
    delete x.op41
  });


this.excelService.exportAsExcelFile(this.stb, 'sale-to-board-ledger-report');
this.searchResults();
}
    }