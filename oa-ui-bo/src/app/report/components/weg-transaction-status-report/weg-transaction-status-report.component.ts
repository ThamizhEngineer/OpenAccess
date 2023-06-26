import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { Criteria } from '../../vo/report';


@Component({
  selector: 'weg-transaction-status-report',
  templateUrl: './weg-transaction-status-report.component.html',
//  styleUrls: ['./weg-transaction-status-report.scss'],
  providers: [CommonUtils]
})

export class WegTransactionStatusReportComponent implements OnInit {
  
  criteria : Criteria ;
  rows: Array<GenericReportOutput>;
  months = [];
  orgList = [];
  years:any[];
  filterNCES = [];
  transMonth: String;
  transYear: String;
  transOrgName = "";
  transOrgId: String;
  disableEdc: boolean = false;
  
 

  constructor(private service:ReportService, private commonService:CommonService,private commonUtils: CommonUtils,) {
    
    this.fetchEDC();
   }

  ngOnInit() {
    console.log("Im in init");
    this.criteria = new Criteria();
    this.rows = [];
    this.months = this.commonService.fetchMonths();
    this.transMonth = this.commonUtils.getCurrentMonth();
    this.transYear = this.commonUtils.getCurrentYear();
    this.years = this.commonService.fetchYearList();

    this.criteria.orgId = CommonUtils.getLoginUserEDC();
    if (this.criteria.orgId != "") {
      console.log("in edc select")
      this.transOrgName = this.criteria.orgId;
      this.disableEdc = true;
    }
  
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


  searchResults(){
    
    if (this.criteria.orgId != "") {
      this.transOrgName = this.criteria.orgId;
    }
    
    let genRptInput:GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'WEG-TRANSACTION-STATUS';

    if(this.transMonth != "" && 
    this.transMonth != null)
      genRptInput.ip1 = this.transMonth;

    if(this.transYear != "" && 
    this.transYear!= null)
      genRptInput.ip2 = this.transYear;

    if(this.transOrgName != "" && 
    this.transOrgName != null)
      genRptInput.ip3 = this.transOrgName;
 

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

