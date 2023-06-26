import { Component, OnInit } from '@angular/core';
import { CommonUtils } from '../../../shared/common/common-utils';
import { CommonService } from '../../../shared/common/common.service';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { ReportService } from '../../services/report.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { Criteria } from '../../vo/report';

@Component({
  selector: 'app-master-report',
  templateUrl: './master-report.component.html',
  
})
export class MasterReportComponent implements OnInit {

  criteria : Criteria ;
  rows: any;
   orgList = [];
  powerPlantClassTypes = [];
  generatorModelTypes = [];
  disableRate: boolean = false;
  disableEdc: boolean = false;
  months=[];
  sections=[];
  dispOrgCode: string;
  fuelTypes = [];
  sectionName="";
   searchMonth="";
   searchYear="";
   year=[
    { "id": "2019", "year": "2019" },
    { "id": "2020", "year": "2020" },
    { "id": "2021", "year": "2021" },
    { "id": "2022", "year": "2022" },
    { "id": "2023", "year": "2023" },
  ]
   serachOrgName="";
   selOrgName="";
   selOrgId:String;
   selFuel: String;
  datePipe: any;

  totalMeterReading: string;
  totalThirdParty: string;
  totalCaptive: string;
  totalTotalAllot: string;
  totalManualReading: string;
  uer: any;
  edc: any;
  constructor(private service:ReportService,private excelService: ExcelExportService,
  private commonService: CommonService) {

   }

  ngOnInit() {
    this.criteria = new Criteria();
    this.rows = [];
    this.sections = [];
    this.months = this.commonService.fetchMonths();
     this.fetchCodes();
     this.fetchFuelTypes();
     this.fetchEDC();


    this.criteria.orgId = CommonUtils.getLoginUserEDC();
    this.edc = CommonUtils.getLoginUserEDC();
      if (this.edc != "") {
      console.log("in edc select")
      this.dispOrgCode = this.edc;
      this.disableEdc = true;
    }

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

onChange(selOrgName){
  console.log(selOrgName);
  this.service.getSectionByOrgId(selOrgName).subscribe(x=>{
    console.log(selOrgName);
    this.sections=x;
    this.fetchEDC();
  })
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
  let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'Master-Report';
    console.log('search Weg Generator');
    if(this.dispOrgCode != "" &&
    this.dispOrgCode != null)
    genericRptInput.ip1 = this.dispOrgCode

    if(this.searchMonth != "" &&
    this.searchMonth != null)
      genericRptInput.ip3 = this.searchMonth;

      if(this.searchYear != "" &&
    this.searchYear != null)
      genericRptInput.ip4 = this.searchYear;

       if(this.selFuel != "" &&
       this.selFuel != null)
       genericRptInput.ip2 = this.selFuel;


    console.log(genericRptInput.ip1);
    console.log(genericRptInput.ip3);
    console.log(genericRptInput.ip4);
    console.log(genericRptInput.ip2);

    this.service.getMasterReport(genericRptInput)
    .subscribe(
      items => {this.rows = items ;
        console.log(genericRptInput)
          console.log(this.rows)

      this.criteria.orgId = CommonUtils.getLoginUserEDC();

      });
  }

ExcelExportService():any{
  let str = JSON.stringify(this.rows);
    str = str.replace(/\"op1\":/g, "\"SELLER ORG ID\":");
    str = str.replace(/\"op2\":/g, "\"SELLER NAME\":");
    str = str.replace(/\"op3\":/g, "\"THIRD PARTY COUNT\":");
    str = str.replace(/\"op4\":/g, "\"THIRD PARTY CAPACITY\":");
    str = str.replace(/\"op5\":/g, "\"CAPTIVE COUNT\":");
    str = str.replace(/\"op6\":/g, "\"CAPTIVE CAPACITY\":");
    str = str.replace(/\"op7\":/g, "\"STB COUNT\":");
    str = str.replace(/\"op8\":/g, "\"STB CAPACITY\":");
    str = str.replace(/\"op9\":/g, "\"TOTAL COUNT\":");
    str = str.replace(/\"op10\":/g, "\"TOTAL CAPACITY\":");
    this.rows = JSON.parse(str);

this.rows.forEach(x =>{
  delete x.$$index;
delete x.op11;delete x.op12;
delete x.op13;delete x.op14;delete x.op15;delete x.op16;delete x.op17;delete x.op18;delete x.op19;delete x.op20;
delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
 delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
});
    this.excelService.exportAsExcelFile(this.rows, 'Abstract-Master-Report');
    this.searchResults();
    }

  fetchCodes(){
	  this.commonService.fetchCodes().subscribe(
      result => {
        this.generatorModelTypes = result.filter(x=>x.listCode == "GENERATOR_MAKE_CODE");

        this.powerPlantClassTypes = result.filter(x=>x.listCode == "PLANT_CLASS_TYPE_CODE");
      })
  }

}




