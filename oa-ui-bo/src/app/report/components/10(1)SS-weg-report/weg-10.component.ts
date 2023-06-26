import { Component, OnInit } from "@angular/core";
import { CommonUtils } from "../../../shared/common/common-utils";
import { CommonService } from "../../../shared/common/common.service";
import { ExcelExportService } from "../../../shared/services/excelExport";
import { ReportService } from "../../services/report.service";
import { GenericReportInput } from "../../vo/GenericReportInput";
import { GenericReportOutput } from "../../vo/GenericReportOutput";
import { Criteria } from "../../vo/report";


@Component({
  selector: 'weg-10(1)ss-report',
  templateUrl: './weg-10.component.html',
  providers:[CommonUtils]
  
})
export class Weg10Component implements OnInit {
  criteria : Criteria ;
  rows: Array<GenericReportOutput>;

  
  orgList = [];
  data= [];
  edc:any;
  disableEdc:boolean;
  months=[];
   searchMonth="";
   searchYear="";
   year=[
    { "id": "2019", "year": "2019" },
    { "id": "2020", "year": "2020" },
    { "id": "2021", "year": "2021" },
    { "id": "2022", "year": "2022" },
    { "id": "2023", "year": "2023" },
  ]
   
   selOrgName="";
  constructor(private service:ReportService,private excelService: ExcelExportService,
  private commonService: CommonService,private commonUtils:CommonUtils) {
    
    
   }

  ngOnInit() {
    this.criteria = new Criteria();
    this.edc = CommonUtils.getLoginUserEDC();
      if (this.edc != "") {
        console.log("in edc select")
        this.selOrgName = this.edc;
        this.disableEdc = true;
      }
    this.rows = [];
    this.months = this.commonService.fetchMonths();
    this.fetchOrgList();
    this.searchMonth=this.commonUtils.getCurrentMonth();
    this.searchYear=this.commonUtils.getCurrentYear();
     
    

}
fetchOrgList() {
  this.commonService.fetchEDCs().subscribe(
    result => {
      this.data = result;
      this.orgList=this.data.filter(item => item.fuelTypeCode=='WIND')
      console.log(this.orgList);
      
    },
    error => {
      console.error('Error loading states!');
    }
  );
}

  

searchResults(){
  let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'WEG-10(1)SS LOSS REPORT';
    console.log('search Weg Generator');

   


    if(this.searchMonth != "" &&
    this.searchMonth != null)
      genericRptInput.ip1 = this.searchMonth;

      if(this.searchYear != "" &&
    this.searchYear != null)
      genericRptInput.ip2 = this.searchYear;

      if(this.selOrgName != "" &&
       this.selOrgName != null)
       genericRptInput.ip3 = this.selOrgName;

    this.service.getWEG10SSlossReport(genericRptInput)
    .subscribe(
      items => {this.rows = items ;
        
          

      

      });
  }

 }
