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
  selector: 'remarks-based-banking-report',
  templateUrl: './remarks-based-banking-report.component.html'
})
export class RemarksBasedBankingReportComponent implements OnInit {
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
  searchGenServiceNo="";
  searchMonth="";
  searchYear="";
  searchOrgName="";
  selOrgName="";
  selOrgId:String;

  constructor(private service:ReportService,
  private commonService: CommonService) {

    this.fetchEDC();
   }

  ngOnInit() {
    this.criteria = new Criteria();
    this.rows = [];
    this.sections = [];
    this.months = this.commonService.fetchMonths();
    // this.searchReport();
     this.fetchCodes();

    this.criteria.orgId = CommonUtils.getLoginUserEDC();
    if (this.criteria.orgId != "") {
      console.log("in edc select")
      this.selOrgName = this.criteria.orgId;
      console.log("this.selOrgName--"+this.selOrgName);
      this.disableEdc = true;
    }
    // this.service.getSections().subscribe(x=>{
    //   this.sections=x;
    // })

   this.selOrgId = CommonUtils.getLoginUserEDC();
    if( this.criteria.orgId!=null){

   this.commonService.fetchEDCs()
   .subscribe(
    edcs => {
      this.orgList = edcs;
      this.selOrgName= this.orgList.filter((x)=>x.id==this.selOrgId)[0].name;
      console.log("this.selOrgName 1--"+this.selOrgName);
     },
    error => {
      console.log("Error loading EDCs");
      console.log(error);
    });
   }
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
    console.log('Search REMARKS-BASED-BANKING-REPORT');

    // if (this.criteria.orgId != "") {
    //   this.selOrgName = this.criteria.orgId;
     
    // }
    
    let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'REMARKS-BASED-BANKING-REPORT';
    if(this.searchMonth != "" && 
    this.searchMonth != null)
      genericRptInput.ip1 = this.searchMonth;
    
      if(this.searchYear != "" && 
      this.searchYear != null)
      genericRptInput.ip2 = this.searchYear;

      if(this.selOrgName != "" && 
       this.selOrgName != null)
       genericRptInput.ip4 = this.selOrgName;

       if(this.searchGenServiceNo != "" && 
       this.searchGenServiceNo != null)
       genericRptInput.ip3 = this.searchGenServiceNo;

    console.log(genericRptInput.ip1);
    console.log(genericRptInput.ip2);
    console.log(genericRptInput.ip3);
    console.log(genericRptInput.ip4);
    console.log(genericRptInput.ip5);

    this.service.getGenericReport(genericRptInput)
    .subscribe(
      items => {this.rows = items ;

      this.criteria.orgId = CommonUtils.getLoginUserEDC();

      });
  }
 
  openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "nil-generation-report.pdf";
		console.log(content_type)
		saveAs(data.blob(), x_filename);
  }

  printReport(){ 
    this.service.nilGensmtsReport(this.searchMonth,this.searchYear,this.searchOrgName).subscribe(xs=>{
      console.log(this.searchMonth,this.searchYear,this.searchOrgName)
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
