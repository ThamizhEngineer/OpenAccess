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
  selector: 'utility-report',
  templateUrl: './utility-report.component.html'
})
export class UtilityReportComponent implements OnInit {
  criteria : Criteria ;
  rows: Array<GenericReportOutput>;
   months=[];
   searchMonth="";
   searchYear="";
   selOrgId="";
   edcList: any;
   typeofChanges ="";
   Year= [
    { "id": "2018", "year": "2018" },
   { "id": "2019", "year": "2019" },
    { "id": "2020", "year": "2020" },
   { "id": "2021", "year": "2021" },
    { "id": "2022", "year": "2022" },
    { "id": "2023", "year": "2023" }
 ]
 
   typeofChange = [
    { "key": "UC", "name": "UTILITYCHANGE" },
    { "key": "NAME & UC", "name": "NAME & UTILITYCHANGE" }
  ]
  constructor(private service:ReportService,
  private commonService: CommonService) {

   }

  ngOnInit(): void {
    this.criteria = new Criteria();
    
    this.months = this.commonService.fetchMonths();
    
    
       this.fetchOrgList();
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


  searchReport(){
     
    let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'UTILITY-REPORT';
    if(this.searchMonth != "" && 
    this.searchMonth != null)
      genericRptInput.ip1 = this.searchMonth;
    
      if(this.searchYear != "" && 
    this.searchYear != null)
      genericRptInput.ip2 = this.searchYear;

      if(this.selOrgId != "" && 
       this.selOrgId != null)
       genericRptInput.ip3 = this.selOrgId;

       if(this.typeofChanges != "" && 
       this.typeofChanges != null)
       genericRptInput.ip4 = this.typeofChanges;

   
       
      this.service.getUtilityReport(genericRptInput)
    .subscribe(
      items => {this.rows = items ;

      this.criteria.orgId = CommonUtils.getLoginUserEDC();

      });
  

  
  }
   
  
  
}
