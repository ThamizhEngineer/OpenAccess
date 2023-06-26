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
  selector: 'contact-of-generator-report',
  templateUrl: './contact-of-generator-report.component.html'
})
export class ContactOfGeneratorReportComponent implements OnInit {
  criteria : Criteria ;
  rows: Array<GenericReportOutput>;

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
  selectedId: any;
  selectedName: any;
  fuelTypes: any;
  fuelTypeCode: string;


  constructor(private service:ReportService,
  private commonService: CommonService,private excelService: ExcelExportService) {

    this.fetchEDC();
   }

  ngOnInit() {
    this.criteria = new Criteria();
    this.rows = [];
    this.sections = [];
    this.months = this.commonService.fetchMonths();
    this.fetchFuelTypes();
    // this.searchReport();
    

    this.criteria.orgId = CommonUtils.getLoginUserEDC();
    if (this.criteria.orgId != "") {
      console.log("in edc select")
      this.selOrgName = this.criteria.orgId;
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

  fetchEDC(){
    this.commonService.fetchEDCs()
    .subscribe(
      edcs => {
        this.orgList = edcs;
        
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

fetchFuelTypes(){
  this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x=>{
  this.fuelTypes=x;
  console.log(this.fuelTypes)
})
}

  searchReport(){
    console.log('Search CONTACT-OF-GENERATOR-REPORT');

    // if (this.criteria.orgId != "") {
    //   this.selOrgName = this.criteria.orgId;
     
    // }
    
    let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'CONTACT-DETAILS-OF-GENERATOR-REPORT';
    // if(this.searchMonth != "" && 
    // this.searchMonth != null)
    //   genericRptInput.ip1 = this.searchMonth;
    
    //   if(this.searchYear != "" && 
    // this.searchYear != null)
    //   genericRptInput.ip2 = this.searchYear;

      if(this.selOrgName != "" && 
       this.selOrgName != null)
       genericRptInput.ip1 = this.selOrgName;

       if(this.fuelTypeCode != "" && 
        this.fuelTypeCode != null)
        genericRptInput.ip2 = this.fuelTypeCode

    //    if(this.sectionName != "" && 
    //    this.sectionName != null)
    //    genericRptInput.ip4 = this.sectionName;
    console.log(genericRptInput.ip2)
    this.service.getUnimportedWegServicesReport(genericRptInput)
    .subscribe(
      items => {this.rows = items ;
console.log(this.rows)
      this.criteria.orgId = CommonUtils.getLoginUserEDC();

      });
  }
 
  openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "contact-details-of-generator.pdf";
		console.log(content_type)
		saveAs(data.blob(), x_filename);
  }

  onSelect(event){
this.selectedId = event.value
  }

  printReport(){ 
    for(let i of this.orgList){
        if(i.id == this.selectedId)
        {
          this.selectedName = i.name;
        }
      }
    this.service.nilGensmtsReport1(this.selectedName).subscribe(xs=>{
			this.openDownload(xs);
		});
  }


  exportAsXLSX():any {     
    let str = JSON.stringify(this.rows);
    str = str.replace(/\"op1\":/g, "\"Sl.No\":");
    str = str.replace(/\"op2\":/g, "\"EDC Code\":");
    str = str.replace(/\"op3\":/g, "\"Generator Service No\":");
    str = str.replace(/\"op4\":/g, "\"Name of Generator\":");
    str = str.replace(/\"op5\":/g, "\"Authorised Contact Person\":");
    str = str.replace(/\"op6\":/g, "\"Designation\":");
    str = str.replace(/\"op7\":/g, "\"Contact Email\":");
    str = str.replace(/\"op8\":/g, "\"Contact Phone No\":");
    str = str.replace(/\"op9\":/g, "\"RegOff Address\":");
    str = str.replace(/\"op10\":/g, "\"Plant Address\":");
    str = str.replace(/\"op11\":/g, "\"Status Of Generator\":");
    this.rows = JSON.parse(str);

 this.excelService.exportAsExcelFile(this.rows, 'contact-details-of-generator');
 this.searchReport();
}
 
}
