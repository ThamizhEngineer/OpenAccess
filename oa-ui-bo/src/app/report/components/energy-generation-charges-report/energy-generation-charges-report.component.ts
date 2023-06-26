import { Component, OnInit } from '@angular/core';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { forEach } from '@angular/router/src/utils/collection';
import { saveAs } from 'file-saver';
import { Criteria } from '../../vo/report';
import { Router } from '@angular/router';
import { ExcelExportService } from '../../../shared/services/excelExport';



@Component({
  selector: 'energy-generation-charges-report',
  templateUrl: './energy-generation-charges-report.component.html',
  providers: [CommonUtils,ExcelExportService]
})
export class EnergyGenerationChargesReportComponent implements OnInit {
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
  searchip5='';
  searchip6='';
  searchip7='';
 

  searchGenServiceNo="";
  searchOrgName="";
  selOrgName="";
  selOrgId:String;

    searchMonth:string = "";
    searchYear:string = "";
    searchFlowTypeCode:string ="";
    searchFuelTypeCode:string="";
    searchIsRec:string="";
    searchOrgId:string="";


    totalop7: any;
    totalop8: any;
    totalop11: any;
    totalop12: any;
    totalop13: any;
    totalop14: any;
    totalop15: any;
    totalop16: any;
    totalop17: any;
    totalop18: any;
    totalop19: any;
    totalop20: any;
    totalop21: any;
   
   
    
    isRecs = [
        { "key": "Y", "name": "REC" },
        { "key": "N", "name": "N0N-REC" }
      ]

      flowTypes = [
        { "key": "STB", "name": "SALE-TO-BOARD" },
        { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
        { "key": "WEG-THIRD-PARTY", "name": "THIRD-PARTY" }

      ]

      fuelTypes = [
        { "key": "WIND", "name": "Wind" },
        { "key": "SOLAR", "name": "Solar" }

      ]


  constructor( private commonService: CommonService,
    private commonUtils: CommonUtils,
    private service: ReportService,
    private router: Router,
    private excelService: ExcelExportService ) {
    this.fetchEDC();
   }

  ngOnInit() {
    this.criteria = new Criteria();
    this.rows = [];
    this.sections = [];
    this.months = this.commonService.fetchMonths();
    this.searchMonth = this.commonUtils.getPerviousMonth();
    this.searchYear = this.commonUtils.getCurrentYear();
    // this.searchReport();
    this.years = this.commonService.fetchYearList();
    this.criteria.orgId = CommonUtils.getLoginUserEDC();
    if (this.criteria.orgId != "") {
      console.log("in edc select")
      this.selOrgName = this.criteria.orgId;
      this.disableEdc = true;
    }

    // restSearch(){
    //   this.searchFlowTypeCode="",this.searchFuelTypeCode="",this.searchIsRec="",this.searchOrgId="";
    //  }
    // this.service.getSections().subscribe(x=>{
    //   this.sections=x;
    // })

  //  this.selOrgId = CommonUtils.getLoginUserEDC();
  //   if( this.criteria.orgId!=null){

  // this.commonService.fetchEDCs()
  // .subscribe(
  //   edcs => {
  //     this.orgList = edcs;
  //     this.selOrgName= this.orgList.filter((x)=>x.id==this.selOrgId)[0].name;
  
  //   },
  //   error => {
  //     console.log("Error loading EDCs");
  //     console.log(error);
  //   });


  //  }
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

    console.log('Search ENERGY-GENERATION-CHARGES-REPORT');

    // if (this.criteria.orgId != "") {
    //   this.selOrgName = this.criteria.orgId;
     
    // }
    
    let genericRptInput:GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'ENERGY-GENERATION-CHARGES-REPORT';
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


       if(this.searchFlowTypeCode != "" && 
       this.searchFlowTypeCode != null)
       genericRptInput.ip5 = this.searchFlowTypeCode;


       if(this.searchFuelTypeCode != "" && 
       this.searchFuelTypeCode != null)
       genericRptInput.ip6 = this.searchFuelTypeCode;

       if(this.searchIsRec != "" && 
       this.searchIsRec != null)
       genericRptInput.ip7 = this.searchIsRec;

    console.log(genericRptInput.ip1);
    console.log(genericRptInput.ip2);
    console.log(genericRptInput.ip3);
    console.log(genericRptInput.ip4);
    console.log(genericRptInput.ip5);
    console.log(genericRptInput.ip6);
    console.log(genericRptInput.ip7);
    console.log(genericRptInput.ip8);


    this.service.getGenericReport(genericRptInput)
    .subscribe(
      items => {
      this.rows = items ;
      this.criteria.orgId = CommonUtils.getLoginUserEDC();

     
      const Op7 = this.rows.reduce((sum, item) => sum + Number(item.op7), 0);
      this.totalop7=Op7.toFixed(2)
      const Op8 = this.rows.reduce((sum, item) => sum + Number(item.op8), 0);
      this.totalop8=Op8.toFixed(2)
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
      const Op17 = this.rows.reduce((sum, item) => sum + Number(item.op17), 0);
      this.totalop17=Op17.toFixed(2)
      const Op18 = this.rows.reduce((sum, item) => sum + Number(item.op18), 0);
      this.totalop18=Op18.toFixed(2)
      const Op19 = this.rows.reduce((sum, item) => sum + Number(item.op19), 0);
      this.totalop19=Op19.toFixed(2)
      const Op20 = this.rows.reduce((sum, item) => sum + Number(item.op20), 0);
      this.totalop20=Op20.toFixed(2)
      const Op21 = this.rows.reduce((sum, item) => sum + Number(item.op21), 0);
      this.totalop21=Op21.toFixed(2)


      });
    }
  
   

    exportAsXLSX():any {
      

      this.rows.push({op1:"TOTAL",op2:"",op3:"",op4:"",op5:"",op6:"",op7:this.totalop7,op8:this.totalop8,op9:"",op10:"",op11:this.totalop11,op12:this.totalop12,op13:this.totalop13,op14:this.totalop14,op15:this.totalop15,op16:this.totalop16,op17:this.totalop17,op18:this.totalop18,
      op19:this.totalop19,op20:this.totalop20,op21:this.totalop21 })

      let str = JSON.stringify(this.rows);
      var re = /IS-CAPTIVE/gi; 
      
      str = str.replace(re, "CAPTIVE"); 
      
      str = str.replace(/\"op1\":/g, "\"Sl.No\":");
      str = str.replace(/\"op3\":/g, "\"GeneratorServiceNo\":");
      str = str.replace(/\"op4\":/g, "\"GenCompanyName\":");
      str = str.replace(/\"op5\":/g, "\"Month\":");
      str = str.replace(/\"op6\":/g, "\"Year\":");
      str = str.replace(/\"op7\":/g, "\"MachineCapacity\":");
      str = str.replace(/\"op8\":/g, "\"M.F\":");
      str = str.replace(/\"op9\":/g, "\"FuelType\":");
      str = str.replace(/\"op10\":/g, "\"FlowType\":");
      str = str.replace(/\"op11\":/g, "\"TotalExport\":");
      str = str.replace(/\"op12\":/g, "\"TotalImport\":");
      str = str.replace(/\"op13\":/g, "\"NetGeneration\":");
      str = str.replace(/\"op14\":/g, "\"M.R.C\":");
      str = str.replace(/\"op15\":/g, "\"O&M.C\":");
      str = str.replace(/\"op16\":/g, "\"T.R.C\":");
      str = str.replace(/\"op17\":/g, "\"S.O.C\":");
      str = str.replace(/\"op18\":/g, "\"Rkvah Penalty\":");
      str = str.replace(/\"op19\":/g, "\"N.E.C\":");
      str = str.replace(/\"op20\":/g, "\"S.C\":");
      str = str.replace(/\"op21\":/g, "\"O.C\":");
     // str = str.replace(/\"op21\":/g, "\"DOWNLOAD STATUS\":");
      
      this.rows = JSON.parse(str);
    
      this.rows.forEach(x =>{
      
        delete x.$$index;delete x.op2;
        delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
        delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
        delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
      });
    
    this.excelService.exportAsExcelFile(this.rows, 'Energy-Generaration-Charges-report');
    this.searchReport();
    }

}


