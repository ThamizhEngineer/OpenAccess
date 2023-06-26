import { Component, OnInit } from '@angular/core';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { forEach } from '@angular/router/src/utils/collection';
import { saveAs } from 'file-saver';
import { Criteria } from '../../vo/report';
import { MonthAndYear } from '../../../report/vo/EnergyAdjustedOrder';
import { DatePipe } from '@angular/common';
import { ExcelExportService } from '../../../shared/services/excelExport';



@Component({
  selector: 'energy-ledger-report',
  templateUrl: './energy-ledger-report.component.html',
  providers: [DatePipe]
})
export class EnergyLedgerReportComponent implements OnInit {
  criteria : Criteria ;
  rows:Array<GenericReportOutput>;

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
  disableEdc: boolean;
  months=[];
  sections=[];

  searchip1='';
  searchip2='';
  searchip3='';
  searchip4='';
  searchGenServiceNo="";
  searchMonth="";
  searchfromMonth="";
  searchtoMonth="";
  searchYear="";
  searchFromYear
  searchToYear="";
  searchOrgName="";
  selOrgName="";
  selOrgId:String;
  monthAndYear:Array<MonthAndYear>;
  fromDate:string;
  toDate:string;
  tempMonthAndYear:any=[];
  fMonth: string;
  tMonth: string;
  fYear: string;
  tYear: string;
  dispOrgCode: string;
  edc: any;
  edcList: any;
  years=[];
  

  constructor(private service:ReportService,private excelService: ExcelExportService,
  private commonService: CommonService,
  private datePipe: DatePipe) {

    // this.fetchEDC();
   }

  ngOnInit() {
    this.fetchEDC();
    // console.log(CommonUtils.getLoginUserTypeCode())
    // console.log(CommonUtils.getLoginUserEDC());
    this.edc = CommonUtils.getLoginUserEDC();
  if (this.edc != "") {
    console.log("in edc select")
    this.dispOrgCode = this.edc;
    this.disableEdc = true;
  }
    this.criteria = new Criteria();
    this.rows = [];
    this.sections = [];
    this.months = this.commonService.fetchMonths();
    this.years = this.commonService.fetchYearList();
    // this.searchReport();
    //  this.fetchCodes();
     

    // this.criteria.orgId = CommonUtils.getLoginUserEDC();
    // if (this.criteria.orgId != "") {
    //   console.log("in edc select")
    //   this.selOrgName = this.criteria.orgId;
    //   this.disableEdc = true;
    // }
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

// fetchEDC(){
//   this.commonService.fetchEDCs()
//   .subscribe(
//     edcs => {this.orgList = edcs;},
//     error => {
//       console.log("Error loading EDCs");
//       console.log(error);
//     }
//   );
// }


searchResults(){
  let genericRptInput:GenericReportInput = new GenericReportInput();
  genericRptInput.reportName = 'ENERGY-LEDGER-REPORT';
  // genericRptInput.reportName = 'ENERGY-LEDGER-REPORT';

  if(this.fMonth != "" && this.fMonth != null)
      genericRptInput.ip1 = this.fMonth;
      // console.log(this.fMonth)

      if(this.tMonth != "" && this.tMonth != null)
      genericRptInput.ip2 = this.tMonth;
      // console.log(this.tMonth)
    
    if(this.fYear != "" && this.fYear!= null)
      genericRptInput.ip3 = this.fYear;
      // console.log(this.fYear)

      if(this.tYear != "" && this.tYear!= null)
      genericRptInput.ip4 = this.tYear;
      // console.log(this.tYear)

    if(this.searchGenServiceNo != "" && this.searchGenServiceNo != null)
       genericRptInput.ip5 = this.searchGenServiceNo;
      //  console.log(this.searchGenServiceNo)

       if(this.dispOrgCode != "" && this.dispOrgCode != null)
       genericRptInput.ip6 = this.dispOrgCode;
  // console.log(this.dispOrgCode)
    this.service.getGenericReport(genericRptInput).subscribe(x=>{
      this.rows=x;
      console.log(this.rows)
  })     
}

exportAsXLSX():any {     

  let str = JSON.stringify(this.rows);
  str = str.replace(/\"op13\":/g, "\"COMPANY NAME\":");
  str = str.replace(/\"op1\":/g, "\"SERVICE NUMBER  \":");
  str = str.replace(/\"op2\":/g, "\"MONTH\":");
  str = str.replace(/\"op3\":/g, "\"YEAR\":");
  str = str.replace(/\"op4\":/g, "\"EDC\":");
  str = str.replace(/\"op5\":/g, "\"OPEN BANKING\":");
  str = str.replace(/\"op6\":/g, "\"GENERATION UNITS\":");
  str = str.replace(/\"op7\":/g, "\"ALLOTED GROSS\":");
  str = str.replace(/\"op8\":/g, "\"ALLOTED NET\":");
  str = str.replace(/\"op9\":/g, "\"ADJUSTED NET\":");
  str = str.replace(/\"op10\":/g, "\"HT BANKING\":");
  str = str.replace(/\"op11\":/g, "\"UNALLOTED UNITS\":");
  str = str.replace(/\"op12\":/g, "\"CLOSING BALANCE\":");
  this.rows = JSON.parse(str);
  
  console.log(this.rows);
  this.rows.forEach(x =>{
    delete x.op14
    delete x.op15
    delete x.op16
    delete x.op17
    delete x.op18
    delete x.op19
    delete x.op20
    delete x.op21
    delete x.op22
    delete x.op23
    delete x.op24
    delete x.op25
    delete x.op26
    delete x.op27
    delete x.op28
    delete x.op29
    delete x.op30
    delete x.op31
    delete x.op32
    delete x.op33
    delete x.op34
    delete x.op35
    delete x.op36
    delete x.op37
    delete x.op38
    delete x.op39
    delete x.op40
    delete x.op41
  });
  console.log(this.rows);
this.excelService.exportAsExcelFile(this.rows, 'Energy-ledger-report');
this.searchResults();
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


  // searchReport(){
  //   console.log('Search ENERGY-LEDGER-REPORT');

  //   // if (this.criteria.orgId != "") {
  //   //   this.selOrgName = this.criteria.orgId;
     
  //   // }
  //   this.monthAndYear=[];
  //   this.rows=[];
  //   this.getMonth();
  //   this.monthAndYear.forEach(x=>{
      
  //   let genericRptInput:GenericReportInput = new GenericReportInput();
  //   genericRptInput.reportName = 'ENERGY-LEDGER-REPORT';
    
  //   if(x.month != "" && x.month != null)
  //     genericRptInput.ip1 = x.month;
    
  //   if(x.year != "" && x.year != null)
  //     genericRptInput.ip2 = x.year;

  //   if(this.searchOrgName != "" && this.searchOrgName != null)
  //      genericRptInput.ip4 = this.searchOrgName;

  //   if(this.searchGenServiceNo != "" && this.searchGenServiceNo != null)
  //      genericRptInput.ip3 = this.searchGenServiceNo;

  //   // console.log(genericRptInput.ip1);
  //   // console.log(genericRptInput.ip2);
  //   // console.log(genericRptInput.ip3);
  //   // console.log(genericRptInput.ip4);
  //   // console.log(genericRptInput.ip5);

  //   this.service.getGenericReport(genericRptInput)
  //   .subscribe(
  //     items => {
  //       // console.log(this.rows)
  //       items.forEach(element => {
  //         this.rows.push(element);
  //       });
  //     this.criteria.orgId = CommonUtils.getLoginUserEDC();
  //     });
  //   })    
  // }

  // getMonth(){
  //   this.fromDate = this.datePipe.transform(this.searchFromYear+'-'+this.searchfromMonth+'-'+'01', 'yyyy-MM-dd');
  //   this.toDate = this.datePipe.transform(this.searchToYear+'-'+this.searchtoMonth+'-'+'28', 'yyyy-MM-dd')
  //   this.dateRange(this.fromDate,this.toDate);
  //   this.tempMonthAndYear.forEach(mo=>{
  //       mo.forEach(element => {
  //           this.formatedDate(element);
  //       });
  //   })
  // }

  // dateRange(startDate, endDate) {
  //   var start      = startDate.split('-');
  //   var end        = endDate.split('-');
  //   var startYear  = parseInt(start[0]);
  //   var endYear    = parseInt(end[0]);
  //   var dates      = [];
  
  //   for(var i = startYear; i <= endYear; i++) {
  //     var endMonth = i != endYear ? 11 : parseInt(end[1]) - 1;
  //     var startMon = i === startYear ? parseInt(start[1])-1 : 0;
  //     for(var j = startMon; j <= endMonth; j = j > 12 ? j % 12 || 11 : j+1) {
  //       var month = j+1;
  //       var displayMonth = month < 10 ? '0'+month : month;
  //       dates.push([i, displayMonth, '01'].join('-'));
  //     }
  //   }
  //   this.tempMonthAndYear.push(dates);
  //   return dates;
  // }

//   formatedDate(fromList){
//     this.monthAndYear.push({
//         "month":this.datePipe.transform(fromList,'MM'),
//         "year":this.datePipe.transform(fromList,'yyyy')
//     });
// }
 
  // openDownload(data: Response) {
	// 	let content_type = data.headers.get('Content-type');
	// 	let x_filename = "nil-generation-report.pdf";
	// 	console.log(content_type)
	// 	saveAs(data.blob(), x_filename);
  // }

  // printReport(){ 
  //   this.service.nilGensmtsReport(this.searchMonth,this.searchYear,this.searchOrgName).subscribe(xs=>{
  //     console.log(this.searchMonth,this.searchYear,this.searchOrgName)
	// 		this.openDownload(xs);
	// 	});

  // }

  // fetchCodes(){
	//   this.commonService.fetchCodes().subscribe(
  //     result => {
  //       this.generatorModelTypes = result.filter(x=>x.listCode == "GENERATOR_MAKE_CODE");

  //       this.powerPlantClassTypes = result.filter(x=>x.listCode == "PLANT_CLASS_TYPE_CODE");
  //     })
  // }
  
  ///To allow number only
  numberFormat(event){
    this.commonService.numberOnly(event)
}

}
