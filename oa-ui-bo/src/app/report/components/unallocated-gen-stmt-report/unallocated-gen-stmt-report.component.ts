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
  selector: 'unallocated-gen-stmt-report',
  templateUrl: './unallocated-gen-stmt-report-list.component.html'
})
export class UnAllocatedGenStmtReportComponent implements OnInit {
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
  fuelCodes = [];
  disableRate: boolean = false;
  disableEdc: boolean = false;
  months=[];
  years=[];
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
   selOrgId="";
   selFuelCode="";
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
   totalop16: any;

  isCaptives = [
    { "key": "Y", "name": "WHEELING" },
    { "key": "N", "name": "SALE-TO-BOARD" }
  ]
  constructor(private service:ReportService,
  private commonService: CommonService,private excelService: ExcelExportService) {

   }

  ngOnInit() {
    this.criteria = new Criteria();
    this.rows = [];
    this.sections = [];
    this.months = this.commonService.fetchMonths();
    this.years = this.commonService.fetchYearList();
   
    // this.searchReport();
     this.fetchCodes("FUEL_TYPE_CODE");

    this.criteria.orgId = CommonUtils.getLoginUserEDC();
   
    this.fetchEDCs();
    // this.service.getSections().subscribe(x=>{
    //   this.sections=x;
    // })

  //  this.selOrgId = CommonUtils.getLoginUserEDC();
  //   if( this.criteria.orgId!=null){

  // this.commonService.fetchEDCs()
  // .subscribe(
  //   edcs => {
  //     this.orgList = edcs;
  //     this.selOrgId= this.orgList[0].id;
  
  //   },
  //   error => {
  //     console.log("Error loading EDCs");
  //     console.log(error);
  //   });
  // }
}

  fetchEDCs(){
    this.commonService.fetchEDCs()
    .subscribe(
      edcs => {this.orgList = edcs;
      if(CommonUtils.getLoginUserTypeCode()=='MRT'){
        this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode == CommonUtils.getLoginUserCompany());
        this.orgList =  this.filterNCES;

        // console.log(this.orgList)
      }
      else if(CommonUtils.getLoginUserTypeCode()=='NCES'){
        this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode);
        this.orgList =  this.filterNCES;
    
      }
      if (CommonUtils.getLoginUserEDC() != "") {
        this.selOrgId = CommonUtils.getLoginUserEDC();
        this.disableEdc = true;
      }
     
    },
    error => {
      console.error('Error loading orgs!');
      console.error(error);
    });
}

onChange(selOrgId){
  this.service.getSectionByOrgId(selOrgId).subscribe(x=>{
    // console.log(selOrgId);
    this.sections=x;
  })
}
  searchReport(){
    // console.log('Search UNALLOCATED-GEN-STMT-REPORT');

    // if (this.criteria.orgId != "") {
    //   this.selOrgName = this.criteria.orgId;
    // }
    
    let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'UNALLOCATED-GEN-STMT-REPORT';
    if(this.searchMonth != "" && 
    this.searchMonth != null)
      genericRptInput.ip1 = this.searchMonth;
    
      if(this.searchYear != "" && 
    this.searchYear != null)
      genericRptInput.ip2 = this.searchYear;

      if(this.selOrgId != "" && 
       this.selOrgId != null)
       genericRptInput.ip3 = this.selOrgId;

       if(this.selFuelCode != "" && 
       this.selFuelCode != null)
       genericRptInput.ip4 = this.selFuelCode;

       
    //    if(this.sectionName != "" && 
    //    this.sectionName != null)
    //    genericRptInput.ip4 = this.sectionName;

    // console.log(genericRptInput.ip1);
    // console.log(genericRptInput.ip2);
    // console.log(genericRptInput.ip3);
    // console.log(genericRptInput.ip4);
    // console.log(genericRptInput.ip5);

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
      const Op16 = this.rows.reduce((sum, item) => sum + Number(item.op16), 0);
      this.totalop16=Op16.toFixed(2)



      });
  }
 
  openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "unallocated-gen-report.pdf";
		// console.log(content_type)
		saveAs(data.blob(), x_filename);
  }

  printReport(){ 
    this.service.UnallocatedGenReport(this.searchMonth,this.searchYear,this.selOrgId).subscribe(xs=>{
			this.openDownload(xs);
		});

  }

  fetchCodes(listCode){
	  this.commonService.fetchCodes(listCode).subscribe(
      result => {
        this.fuelCodes = result;
      })
  }
  exportAsXLSX():any {

    this.rows.push({op2:"",op3:"TOTAL",op5:"",op6:this.totalop6,op7:this.totalop7,op8:this.totalop8,op9:this.totalop9,
    op10:this.totalop10,op11:this.totalop11,op12:this.totalop12,op13:this.totalop13,op14:this.totalop14,op15:this.totalop15,op16:this.totalop16})

    let str = JSON.stringify(this.rows);
    str = str.replace(/\"op2\":/g, "\"Generator Service No\":");
    str = str.replace(/\"op3\":/g, "\"Name of Generator\":");
    str = str.replace(/\"op5\":/g, "\"Stmt Date\":");
    str = str.replace(/\"op6\":/g, "\"Total Export\":");
    str = str.replace(/\"op7\":/g, "\"Total Import\":");
    str = str.replace(/\"op8\":/g, "\"Net Generation\":");
    str = str.replace(/\"op9\":/g, "\"M.R.C\":");
    str = str.replace(/\"op10\":/g, "\"O&M.C\":");
    str = str.replace(/\"op11\":/g, "\"T.R.C\":");
    str = str.replace(/\"op12\":/g, "\"S.O.C\":");
    str = str.replace(/\"op13\":/g, "\"Rkvah Penalty\":");
    str = str.replace(/\"op14\":/g, "\"N.E.C\":");
    str = str.replace(/\"op15\":/g, "\"S.C\":");
    str = str.replace(/\"op16\":/g, "\"Total Charges\":");

    this.rows = JSON.parse(str);
  
    this.rows.forEach(x =>{
      delete x.$$index;delete x.op1;delete x.op4;
      delete x.op9;delete x.op10;delete x.op11;delete x.op2;
      delete x.op12;delete x.op13;delete x.op14;delete x.op15;delete x.op16;delete x.op17;
      delete x.op18;delete x.op19;delete x.op20;
      delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
      delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
      delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
    });
  
  this.excelService.exportAsExcelFile(this.rows, 'UNALLOCATED GEN STMT REPORT');
  this.searchReport();
  }

}
