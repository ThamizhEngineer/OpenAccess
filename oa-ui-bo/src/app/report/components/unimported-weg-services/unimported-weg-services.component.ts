import { Component, OnInit } from '@angular/core';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { forEach } from '@angular/router/src/utils/collection';
import { saveAs } from 'file-saver';
import { Criteria } from '../../vo/report';
import { ExcelExportService } from '../../../shared/services/excelExport';



@Component({
  selector: 'unimported-weg-services',
  templateUrl: './unimported-weg-services.component.html'
})
export class UnimportedWegServicesComponent implements OnInit {
  criteria : Criteria ;
  rows: any;

  filterNCES = [];
  selectedSellerOrg:string = "";
  selectedSellerCapacity:string = "";
  selectedIsCaptive:string = "";
  selectedBuyerOrg:string = "";
  selectedMakeCode:string="";
  orgList = [];
  powerPlantClassTypes = [];
  generatorModelTypes = [];
  disableRate: boolean = false;
  disableEdc: boolean = false;
  months=[];
  sections=[];
  fuelTypes = [];
  years:any[];
  searchip1='';
  searchip2='';
  searchip3='';
  searchip4='';
  sectionName="";
   searchMonth="";
   searchYear="";
   serachOrgName="";
   selOrgName="";
   selOrgId:String;
   selFuel: string;
   Typeofss=[
    {"name":"SECTION 10(1)SS"},
    {"name": "TANGEDCO OWN SS"}
  ];


  isCaptives = [
    { "key": "Y", "name": "WHEELING" },
    { "key": "N", "name": "SALE-TO-BOARD" }
  ]
  TypeOfSS: any;
  constructor(private service:ReportService,
  private commonService: CommonService,private excelService: ExcelExportService) {
    this.fetchEDC();
   }

  ngOnInit() {
    this.criteria = new Criteria();
    this.rows = [];
    this.sections = [];
    this.months = this.commonService.fetchMonths();
    this.years = this.commonService.fetchYearList();
    // this.searchReport();
     this.fetchCodes();
     this.fetchFuelTypes();
     

    this.criteria.orgId = CommonUtils.getLoginUserEDC();
    if (this.criteria.orgId != "") {
      console.log("in edc select")
      this.selOrgName = this.criteria.orgId;
      this.onChange(this.selOrgName);
      this.disableEdc = true;
    }
    // this.service.getSections().subscribe(x=>{
    //   this.sections=x;
    // })

  //  this.selOrgId = CommonUtils.getLoginUserEDC();
  //   if( this.criteria.orgId!=null){

  // // this.commonService.fetchEDCs()
  // // .subscribe(
  // //   edcs => {
  // //     this.orgList = edcs;
  // //   //  this.selOrgName= this.orgList.filter((x)=>x.id==this.selOrgId)[0].name;
  
  // //   },
  // //   error => {
  // //     console.log("Error loading EDCs");
  // //     console.log(error);
  // //   });


  // }
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

  fetchEDC(){
    this.commonService.fetchEDCs()
    .subscribe(
      edcs => {this.orgList = edcs;
      if(CommonUtils.getLoginUserTypeCode()=='MRT'){
        console.log(CommonUtils.getLoginUserCompany());
        console.log(this.orgList.filter((item) => item.ncesDivisionCode))
        this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode == CommonUtils.getLoginUserCompany());
        
        console.log(this.filterNCES)
        this.orgList =  this.filterNCES;

        console.log(this.orgList)
    

      }
      else if(CommonUtils.getLoginUserTypeCode()=='NCES'){
        console.log(CommonUtils.getLoginUserCompany());
        console.log(this.orgList.filter((item) => item.ncesDivisionCode))
        this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode);
        console.log(this.filterNCES)

        this.orgList =  this.filterNCES;
        console.log(this.orgList)
    
      }
     
    },
    error => {
      console.error('Error loading orgs!');
      console.error(error);
    });
}
onChange(selOrgName){
  console.log(selOrgName);
  this.service.getSectionByOrgId(selOrgName).subscribe(x=>{
    console.log(selOrgName);
    this.sections=x;
  })
}
  searchReport(){
    console.log('search Weg Generator');

    if (this.criteria.orgId != "") {
      this.selOrgName = this.criteria.orgId;
    }
    
    let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'UNIMPORTED-WEG-SERVICES';
    if(this.searchMonth != "" && 
    this.searchMonth != null)
      genericRptInput.ip1 = this.searchMonth;
    
      if(this.searchYear != "" && 
    this.searchYear != null)
      genericRptInput.ip2 = this.searchYear;

      if(this.selOrgName != "" && 
       this.selOrgName != null)
       genericRptInput.ip3 = this.selOrgName;

       if(this.sectionName != "" && 
       this.sectionName != null)
       genericRptInput.ip4 = this.sectionName;

       if(this.selFuel != "" && 
       this.selFuel != null)
       genericRptInput.ip5 = this.selFuel;

       if(this.TypeOfSS != "" &&
       this.TypeOfSS != null)
       genericRptInput.ip6 = this.TypeOfSS;

    console.log(genericRptInput.ip1);
    console.log(genericRptInput.ip2);
    console.log(genericRptInput.ip3);
    console.log(genericRptInput.ip4);
    console.log(genericRptInput.ip5);

    this.service.getUnimportedWegServicesReport(genericRptInput)
    .subscribe(
      items => {this.rows = items ;

      this.criteria.orgId = CommonUtils.getLoginUserEDC();

      });
  }
 
  openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "unimported-weg-report.pdf";
		console.log(content_type)
		saveAs(data.blob(), x_filename);
  }

  printReport(){ 
    this.service.printUnimportedReport(this.searchMonth,this.searchYear,this.selOrgName,this.sectionName,this.selFuel,this.TypeOfSS).subscribe(xs=>{
			this.openDownload(xs);
		});

  }

  fetchCodes(){
	  this.commonService.fetchCodes().subscribe(
      result => {
        this.generatorModelTypes = result.filter(x=>x.listCode == "GENERATOR_MAKE_CODE");

        this.powerPlantClassTypes = result.filter(x=>x.listCode == "PLANT_CLASS_TYPE_CODE");
      })
  }
  exportAsXLSX():any {  
    let str = JSON.stringify(this.rows);
        str = str.replace(/\"op1\":/g, "\"Sl.No\":");
        str = str.replace(/\"op5\":/g, "\"Section Name\":");
        str = str.replace(/\"op2\":/g, "\"Generator Name\":");
        str = str.replace(/\"op3\":/g, "\"Generator Service No\":");
        str = str.replace(/\"op6\":/g, "\"Meter Number\":");
        str = str.replace(/\"op8\":/g, "\"Fuel Type\":");
        str = str.replace(/\"op9\":/g, "\"SS Name\":");
        str = str.replace(/\"op10\":/g, "\"Type Of SS\":")
        str = str.replace(/\"op11\":/g, "\"Feeder Name\":");
        


        this.rows = JSON.parse(str);
  this.rows.forEach(x =>{
    delete x.$$index;
    delete x.op4
    delete x.op7

    delete x.op12
    delete x.op13
    delete x.op14
    delete x.op15
    delete x.op16
    delete x.op17
    delete x.op18
    delete x.op19
    delete x.op20
    delete x.op21
    delete x.op22
    delete x.op23
    delete x.op24
    delete x.op25
    delete x.op26
    delete x.op27
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
       
  this.excelService.exportAsExcelFile(this.rows, 'UNIMPORTED-WEG-SERVICE-REPORT');
     this.searchReport();
    }
  

}
