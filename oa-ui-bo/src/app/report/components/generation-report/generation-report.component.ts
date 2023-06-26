import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { Criteria } from '../../vo/report';

@Component({
  selector: 'generation-report',
  templateUrl: './generation-report.component.html',
  styleUrls: ['./generation-report.component.scss'],
  providers: [CommonUtils]
})
export class GenerationReportComponent implements OnInit {
  criteria : Criteria ;
  rows: Array<GenericReportOutput>;
  reports: Array<GenericReportOutput>;
  months = [];
  orgList = [];
  years:any[];
  filterNCES = [];
  selAgreementMonth: String;
  selAgreementYear: String;
  selOrgName: String;
  selOrgId:String;
  disableEdc: boolean = false;

  constructor(private service:ReportService, private commonService:CommonService,private commonUtils: CommonUtils,) {
  
   }

  ngOnInit() {
    console.log("Im in init");
    this.criteria = new Criteria();
    this.rows = [];
    this.reports =[];
    //this.searchResults();
    // this.availableUnits=[];
    this.fetchEDC();
    this.months = this.commonService.fetchMonths();
    this.years = this.commonService.fetchYearList();
    this.selAgreementMonth = this.commonUtils.getCurrentMonth();
    this.selAgreementYear = this.commonUtils.getCurrentYear();
    
    
  // this.selOrgId = CommonUtils.getLoginUserEDC();
  //   if(this.selOrgName!=null){

  // this.commonService.fetchEDCs()
  // .subscribe(
  //   edcs => {this.orgList = edcs;
  //    this.selOrgName= this.orgList.filter((x)=>x.id==this.selOrgId)[0].name;
  
  //   },
  //   error => {
  //     console.log("Error loading EDCs");
  //     console.log(error);
  //   });
 // }
 

}


  fetchEDC(){
    this.commonService.fetchEDCs()
    .subscribe(
      edcs => {this.orgList = edcs;
        this.setOrgName();
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

 setOrgName(){
    this.criteria.orgId = CommonUtils.getLoginUserEDC();
    if (this.criteria.orgId != "") { 
      this.selOrgName= this.orgList.filter(x=>(x.code==this.criteria.orgId))[0].name; 
      this.disableEdc = true;
    }

 }
  searchResults(){
    
    if (this.criteria.orgId != "") {
      this.selOrgName = this.criteria.orgId;
    }
    
    let genRptInput:GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'GENERATION-REPORT';

    if(this.selAgreementMonth != "" && 
    this.selAgreementMonth != null)
      genRptInput.ip1 = this.selAgreementMonth;

    if(this.selAgreementYear != "" && 
    this.selAgreementYear != null)
      genRptInput.ip2 = this.selAgreementYear;

    if(this.selOrgName != "" && 
    this.selOrgName != null)
      genRptInput.ip3 = this.selOrgName;
 

      this.service.getGenericReport(genRptInput)
      .subscribe(items => {this.rows = items
      
        this.criteria.orgId = CommonUtils.getLoginUserEDC();
      
   });
      
      console.log(genRptInput.reportName);
      console.log(genRptInput.ip1);
      console.log(genRptInput.ip2);
      console.log(genRptInput.ip3);
      console.log(genRptInput.ip4);
      console.log(genRptInput.ip5);
    }
  
  }
