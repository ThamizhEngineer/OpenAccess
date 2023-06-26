import { Component, OnInit } from '@angular/core';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { forEach } from '@angular/router/src/utils/collection';
import { saveAs } from 'file-saver';
import { Criteria } from '../../vo/report';



@Component({
  selector: 'nil-generation-report',
  templateUrl: './nil-generation-report-list.component.html'
})
export class NilGenerationReportComponent implements OnInit {
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


  isCaptives = [
    { "key": "Y", "name": "WHEELING" },
    { "key": "N", "name": "SALE-TO-BOARD" }
  ]
  years=[];
  constructor(private service:ReportService,
  private commonService: CommonService) {

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

  searchReport(){
    console.log('Search NIL-GENERATION-REPORT');

    // if (this.criteria.orgId != "") {
    //   this.selOrgName = this.criteria.orgId;
     
    // }
    
    let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'NIL-GENERATION-REPORT';
    if(this.searchMonth != "" && 
    this.searchMonth != null)
      genericRptInput.ip1 = this.searchMonth;
    
      if(this.searchYear != "" && 
    this.searchYear != null)
      genericRptInput.ip2 = this.searchYear;

      if(this.selOrgName != "" && 
       this.selOrgName != null)
       genericRptInput.ip3 = this.selOrgName;

    //    if(this.sectionName != "" && 
    //    this.sectionName != null)
    //    genericRptInput.ip4 = this.sectionName;

    console.log(genericRptInput.ip1);
    console.log(genericRptInput.ip2);
    console.log(genericRptInput.ip3);
    console.log(genericRptInput.ip4);
    console.log(genericRptInput.ip5);

    this.service.getUnimportedWegServicesReport(genericRptInput)
    .subscribe(
      items => {this.rows = items ;

      this.criteria.orgId = CommonUtils.getLoginUserEDC();

      const Op6 = this.rows.reduce((sum, item) => sum + Number(item.op6), 0);
         this.totalop6=Op6.toFixed(2)
         const Op7 = this.rows.reduce((sum, item) => sum + Number(item.op7), 0);
         this.totalop7=Op7.toFixed(2)
         const Op8 = this.rows.reduce((sum, item) => sum + Number(item.op8), 0);
         this.totalop8=Op8.toFixed(2)
         const Op9 = this.rows.reduce((sum, item) => sum + Number(item.op9), 0);
         this.totalop9=Op9.toFixed(2)
         const Op10 = this.rows.reduce((sum, item) => sum + Number(item.op10), 0);
         this.totalop10=Op10.toFixed(2)
         const Op11 = this.rows.reduce((sum, item) => sum + Number(item.op11), 0);
         this.totalop11=Op11.toFixed(2)
         const Op12 = this.rows.reduce((sum, item) => sum + Number(item.op12), 0);
         this.totalop12=Op12.toFixed(2)
         const Op13 = this.rows.reduce((sum, item) => sum + Number(item.op13), 0);
         this.totalop13=Op13.toFixed(2)
         const Op14 = this.rows.reduce((sum, item) => sum + Number(item.op14), 0);
         this.totalop14=Op14.toFixed(2)
         const Op15 = this.rows.reduce((sum, item) => sum + Number(item.op15), 0);
         this.totalop15=Op15.toFixed(2)

      });
  }
 
  openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "nil-generation-report.pdf";
		console.log(content_type)
		saveAs(data.blob(), x_filename);
  }

  printReport(){ 
    this.service.nilGensmtsReport(this.searchMonth,this.searchYear,this.selOrgName).subscribe(xs=>{
      console.log(this.searchMonth,this.searchYear,this.selOrgName)
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

}
