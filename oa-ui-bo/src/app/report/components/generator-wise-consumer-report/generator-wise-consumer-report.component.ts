import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { ExcelExportService } from '../../../shared/services/excelExport';

@Component({
  selector: 'generator-wise-consumer-report',
  templateUrl: './generator-wise-consumer-report.component.html',
  styleUrls: ['./generator-wise-consumer-report.component.scss']
})
export class GeneratorWiseConsumerReportComponent implements OnInit {
  rows: Array<GenericReportOutput>;
  months = [];
  orgList = [];
  fuelTypes = [];
  voltages =[];
  years=[];
  
  selVoltage: String;
  selFuel: String;
  selAgreementMonth: String;
  selAgreementYear: String;
  selOrgName: String;
  dispOrgCode: string;
  serviceNo: string;
  fromMonth: string;
  year: string;
  edc: any;
  disableEdc: boolean;
  fuelTypeCode: string;

  flowTypes = [
    { "key": "STB", "name": "SALE-TO-BOARD" },
    { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
    { "key": "THIRD-PARTY", "name": "THIRD-PARTY" }

  ]

  fuelTypeCodes =
  [
    { "key": "COAL", "name": "COAL" },
    { "key": "WIND", "name": "WIND" },
    { "key": "SOLAR", "name": "SOLAR" },
    { "key": "NATURAL GAS", "name": "NATURAL GAS" },
    { "key": "BIOGASS", "name": "BIOGASS" },
    { "key": "WASTE HEAT", "name": "WASTE HEAT" },
    { "key": "BIO-MASS", "name": "BIO-MASS" },
    { "key": "DIESEL", "name": "DIESEL" },
    { "key": "BAGASSE", "name": "BAGASSE" }
  ]
  flowTypeCode: string;

  constructor(private service:ReportService, private commonService:CommonService, private excelService: ExcelExportService) { }

  ngOnInit() {
    console.log(CommonUtils.getLoginUserEDC());
        this.edc = CommonUtils.getLoginUserEDC();
        this.years = this.commonService.fetchYearList();
    
      if (this.edc != "") {
        console.log("in edc select")
        this.dispOrgCode = this.edc;
        this.disableEdc = true;
      }
    console.log("Im in init");
    this.rows = [];
    this.months = this.commonService.fetchMonths();
    this.fetchEDC();
    this.fetchFuelTypes();
    this.fetchVoltages();
    console.log(this.voltages);
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

  
  fetchFuelTypes(){
    this.commonService.fetchCodes("FUEL_TYPE_CODE")
    .subscribe(
      fuelTypes => {this.fuelTypes = fuelTypes;},
      error => {
        console.error("Error loading Fuel Types");
        console.error(error);
      });
  }

  fetchVoltages() {
    this.commonService.fetchCodes("VOLTAGE_CODE")
    .subscribe(
      result => {
        this.voltages = result;
      },
 
      error => {
        console.error("Error loading Voltages");
        console.error(error);
      });
  }

  searchResults(){
    let genRptInput:GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'GENERATOR-WISE-CONSUMER-REPORT';

    if(this.dispOrgCode != "" && 
    this.dispOrgCode != null)
      genRptInput.ip1 = this.dispOrgCode
    
    if(this.serviceNo != "" && 
    this.serviceNo != null)
      genRptInput.ip2 = this.serviceNo;

    if(this.fromMonth != "" && 
    this.fromMonth != null)
      genRptInput.ip3 = this.fromMonth;

    if(this.year != "" && 
    this.year != null)
      genRptInput.ip4 = this.year;

      if(this.flowTypeCode != "" && 
        this.flowTypeCode != null)
          genRptInput.ip5 = this.flowTypeCode

      if(this.fuelTypeCode != "" && 
        this.fuelTypeCode != null)
          genRptInput.ip6 = this.fuelTypeCode
          console.log(genRptInput.ip6);

    this.service.getGenericReport(genRptInput)
    .subscribe(items => this.rows = items);
    console.log(genRptInput)
  }

  exportAsXLSX():any {     

    let str = JSON.stringify(this.rows);
    str = str.replace(/\"op1\":/g, "\"SELLER NAME\":");
    str = str.replace(/\"op2\":/g, "\"SELLER COMP NAME\":");
    str = str.replace(/\"op3\":/g, "\"GENERATOR SERVICE NO\":");
    str = str.replace(/\"op14\":/g, "\"COMISSION DATE\":");
    str = str.replace(/\"op4\":/g, "\"SELLER ORG ID\":");
    str = str.replace(/\"op5\":/g, "\"FLOW TYPE\":");
    str = str.replace(/\"op6\":/g, "\"FUEL TYPE\":");
    str = str.replace(/\"op7\":/g, "\"BUYER NAME\":");
    str = str.replace(/\"op9\":/g, "\"BUYER COMPANY NAME\":");
    str = str.replace(/\"op10\":/g, "\"HT SDERVICE NO\":");
    str = str.replace(/\"op12\":/g, "\"FROM DATE\":");
    str = str.replace(/\"op13\":/g, "\"TO DATE\":");
    str = str.replace(/\"op15\":/g, "\"AGREEMENT DATE\":");
    this.rows = JSON.parse(str);
    
 this.excelService.exportAsExcelFile(this.rows, 'GENERATOR-WISE-CONSUMER-REPORT');
 this.searchResults();
}

///To allow number only
numberFormat(event){
  this.commonService.numberOnly(event)
}

}
