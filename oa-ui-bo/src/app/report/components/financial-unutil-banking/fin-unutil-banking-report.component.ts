import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { CommonUtils } from '../../../shared/common/common-utils';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { saveAs } from 'file-saver';
import { Criteria } from '../../vo/report';


@Component({
    selector: 'unutil-banking-report',
    templateUrl: './fin-unutil-banking-report.component.html'
  })

  export class FinUnutilBankReportComponent implements OnInit{
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


  isCaptives = [
    { "key": "Y", "name": "WHEELING" },
    { "key": "N", "name": "SALE-TO-BOARD" }
  ]
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
      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "FinancialUnutilReport.pdf";
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }
      printReport(){
        this.service.printUnutilBankingReport(this.selOrgName,this.searchMonth,this.searchYear).subscribe(xs=>{
          this.openDownload(xs);
        });
      }
        searchResults() {

          this.service.getAllUnutilBanking(this.selOrgName,this.searchMonth,this.searchYear).subscribe(
            _prog => {
              this.rows = _prog;
              console.log("In search result")
              console.log(this.rows);
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