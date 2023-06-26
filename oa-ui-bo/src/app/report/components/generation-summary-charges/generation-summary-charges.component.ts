import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { CommonUtils } from '../../../shared/common/common-utils';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { filter } from 'rxjs/operator/filter';
import { OutputEmitter } from '@angular/compiler/src/output/abstract_emitter';
// import { ConsoleReporter } from 'jasmine';


@Component({
  selector: 'generation-summary-charges',
  templateUrl: './generation-summary-charges.component.html',
})
export class GenSummaryChargesComponent implements OnInit {

  rows: any = [];

  flowTypes = [
    { "key": "STB", "name": "SALE-TO-BOARD" },
    { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
    { "key": "THIRD-PARTY", "name": "THIRD-PARTY" }
  ]

  fuelGroup = [
    { "key": "RE", "name": "RE" },
    { "key": "FF", "name": "FF" }
  ]

  isRecs = [
    { "key": "Y", "name": "REC" },
    { "key": "N", "name": "NON-REC" }
  ]

  months: { value: string; name: string; }[];
  stmtMonth: string;
  stmtYear: string;
  dispOrgCode: string;
  serviceNo: string;
  edc: any;
  disableEdc: boolean;
  edcList: any;
  fuelTypes: any;
  fuelTypeCode: string;
  fetchFuels: any;
  searchFlowTypeCode: string;
  dispFuelTypeCode: string;
  IsRec: string;
  dispFuelTypeGroup: string;
  searchIsRec: string;
  fuelName: { key: string; name: string; };
  filteredFuel: any;
  isOpen:boolean = true;
  years = [];
  


  constructor(private router: Router, private service: ReportService, private route: ActivatedRoute,
    private commonService: CommonService, private excelService: ExcelExportService) {
  }

  ngOnInit() {
    this.fetchMonths();
      this.fetchOrgList();

      this.edc = CommonUtils.getLoginUserEDC();
      this.years = this.commonService.fetchYearList();
     
      if (this.edc != "") {
        console.log("in edc select")
        this.dispOrgCode = this.edc;
        this.disableEdc = true;
    //     console.log(typeof(this.disableEdc));
    // console.log(this.disableEdc);
      }

      
  }

  restSearch(){
    this.stmtMonth="",this.stmtYear="",this.searchFlowTypeCode="",this.searchFlowTypeCode="",
    this.dispFuelTypeCode="",this.searchIsRec="",this.dispFuelTypeGroup="";
    // console.log(typeof(this.disableEdc));
    // console.log(this.disableEdc);
    if(this.edc != this.disableEdc){
      this.dispOrgCode=""
    }
   }
  searchResults(){
    let genReportInput:GenericReportInput = new GenericReportInput();
    genReportInput.reportName = 'Gen-summary-charges';

    if(this.stmtMonth != "" && 
      this.stmtMonth != null)
      genReportInput.ip1 = this.stmtMonth

        if(this.stmtYear != "" && 
      this.stmtYear != null)
      genReportInput.ip2 = this.stmtYear

        if(this.dispOrgCode != "" && 
      this.dispOrgCode != null)
      genReportInput.ip3 = this.dispOrgCode 
    
      if(this.searchFlowTypeCode != "" && 
      this.searchFlowTypeCode != null)
      genReportInput.ip4 = this.searchFlowTypeCode

      if(this.dispFuelTypeCode != "" && 
      this.dispFuelTypeCode != null)
      genReportInput.ip5 = this.dispFuelTypeCode

      if(this.searchIsRec != "" && 
      this.searchIsRec != null)
      genReportInput.ip6 = this.searchIsRec

      if(this.dispFuelTypeGroup != "" && 
      this.dispFuelTypeGroup != null)
      genReportInput.ip7 = this.dispFuelTypeGroup

      console.log(genReportInput)
      this.service.getGenericReport(genReportInput).subscribe(x=>{
        this.rows=x;
    })     
  }

  displayFuel(event){
    this.fuelName = event;
    this.commonService.FetchFuels().subscribe(x=>{
      this.fuelTypes = x
      this.filteredFuel = this.fuelTypes.filter((item)=> item.fuelGroup==this.fuelName);
      this.isOpen=false;
    });
  }

  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.edcList = result;
      },
      error => {
        console.error('Error loading states!');
      }
    );
  }

  fetchMonths(){
    this.months = this.commonService.fetchMonths();
  }

  exportAsXLSX():any {
    let str = JSON.stringify(this.rows);
    str = str.replace(/\"op1\":/g, "\"EDC NAME\":");
    str = str.replace(/\"op2\":/g, "\"SERVICE COUNT\":");
    str = str.replace(/\"op3\":/g, "\"FLOW TYPE\":");
    str = str.replace(/\"op4\":/g, "\"TOTAL CAPACITY\":");
    str = str.replace(/\"op5\":/g, "\"AMR METER READING CHARGES\":");
    str = str.replace(/\"op6\":/g, "\"O&M CHANGES\":");
    str = str.replace(/\"op7\":/g, "\"TRANSMISSION CHANGES\":");
    str = str.replace(/\"op8\":/g, "\"SYSTEM OPERATING CHARGES\":");
    str = str.replace(/\"op9\":/g, "\"RKVAH PENALTY\":");
    str = str.replace(/\"op10\":/g, "\"IMPORT ENERGY CHARGES\":");
    str = str.replace(/\"op11\":/g, "\"SCHEDULING\":");
    str = str.replace(/\"op12\":/g, "\"OTHER CHARGES\":");
    this.rows = JSON.parse(str);
  
    this.rows.forEach(x =>{
      delete x.$$index;
      delete x.op13;delete x.op14;delete x.op15;delete x.op16;delete x.op17;delete x.op18;delete x.op19;delete x.op20;
      delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
      delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
      delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
    });
  
  this.excelService.exportAsExcelFile(this.rows, 'GENERATOR SUMMARY CHARGES REPORT');
  this.searchResults();
  }
}