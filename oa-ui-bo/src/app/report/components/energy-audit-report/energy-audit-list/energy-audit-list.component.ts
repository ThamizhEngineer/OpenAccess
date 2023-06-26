import { Component,OnInit} from '@angular/core';
import { Router,ActivatedRoute,Params } from '@angular/router';
import { CommonService } from '../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { saveAs } from 'file-saver';
import { ReportService } from '../../../../report/services/report.service';
import { ExcelExportService } from '../../../../shared/services/excelExport';
import { EnergyAuditForGenUnitsCsv } from '../../../vo/AuditReport';
import { MonthAndYear } from '../../../vo/EnergyAdjustedOrder';
import { DatePipe } from '@angular/common';
import { ColumnMode } from '@swimlane/ngx-datatable';
import { GenericReportInput } from '../../../vo/GenericReportInput';
import { GenericReportOutput } from '../../../vo/GenericReportOutput';

@Component({
    selector:'energy-audit-list',
    templateUrl:'./energy-audit-list.component.html',
    providers: [CommonUtils,ReportService,DatePipe]
})

export class EnergyAuditReportListComponent implements OnInit{

    ColumnMode = ColumnMode;
    searchGenServNumber: string="";
    searchfromMonth: string="";
    searchtoMonth: string="";
    searchFromYear:string="";
    searchToYear:string="";
    months=[];
    resultSet:boolean=false;
    eaListForCsv:Array<EnergyAuditForGenUnitsCsv>;
    message:string;
    fromDate:string;
    toDate:string;
    monthAndYear:Array<MonthAndYear>;
    tempMonthAndYear:any=[];
    rows:Array<GenericReportOutput>;
    tempObj:GenericReportOutput;
    sellerCompany:string;
    sellerEdc:string;
    fuelType:string;
    years=[];
    totalop3: any;
    totalop4: any;
    totalop5: any;
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
    totalop17: any;
    totalop18: any;
    totalop19: any;
    totalop20: any;
    totalop21: any;
    totalop22: any;
    totalop23: any;
    totalop24: any;
    totalop25: any;
    totalop26: any;
    totalop27: any;
    totalop28: any;
    totalop29: any;
    totalop30: any;
    totalop31: any;
    totalop32: any;
    totalop33: any;
    totalop34: any;
    totalop35: any;
    totalop36: any;
    totalop37: any;
    totalop38: any;
    totalop39: any;
    totalop40: any;
    totalop41: any;
    totalop42: any;
    totalop43: any;
    totalop44: any;
    ShowOpenBalance:Boolean=false;
   
    constructor( private router: Router,
        private route: ActivatedRoute,
        private commonService:CommonService, 
        private reportService:ReportService,
        private excelService: ExcelExportService,
        private datePipe: DatePipe){}

    ngOnInit(){
        this.rows=[];
        // this.eaListForCsv=[];
        this.months = this.commonService.fetchMonths();
        this.years = this.commonService.fetchYearList();
        this.route.params
            .subscribe((_params: Params)=>{
                this.searchGenServNumber =_params['genServNumber'];
                this.searchfromMonth =_params['searchfromMonth'];
                this.searchFromYear = _params['searchFromYear'];
                this.searchtoMonth = _params['searchtoMonth'];
                this.searchToYear = _params['searchToYear'];
            });
            if(this.searchfromMonth!=null && this.searchfromMonth!=""){
                this.searchResults();
            }
    }

    showopenbalance(){

        this.ShowOpenBalance=true;
    }

    searchResults(){
        this.eaListForCsv=[];
            let genRptInput:GenericReportInput = new GenericReportInput();
            genRptInput.reportName = 'ENERGY-AUDIT-REPORT';

            if(this.searchGenServNumber != "" && this.searchGenServNumber != null)
            genRptInput.ip1 = this.searchGenServNumber

            if(this.searchfromMonth != "" && this.searchfromMonth != null)
            genRptInput.ip2 = this.searchfromMonth

            if(this.searchFromYear != "" && this.searchFromYear != null)
            genRptInput.ip3 = this.searchFromYear

            if(this.searchtoMonth != "" && this.searchtoMonth != null)
            genRptInput.ip4 = this.searchtoMonth

            if(this.searchToYear != "" && this.searchToYear != null)
            genRptInput.ip5 = this.searchToYear

            this.reportService.getAllTnerc(genRptInput).subscribe(x=>{
                this.rows=x;
                this.formatData(this.rows);
         const Op3 = this.rows.reduce((sum, item) => sum + Number(item.op12), 0);
         this.totalop3=Op3.toFixed(2)
         const Op4 = this.rows.reduce((sum, item) => sum + Number(item.op13), 0);
         this.totalop4=Op4.toFixed(2)
         const Op5 = this.rows.reduce((sum, item) => sum + Number(item.op14), 0);
         this.totalop5=Op5.toFixed(2)
         const Op6 = this.rows.reduce((sum, item) => sum + Number(item.op15), 0);
         this.totalop6=Op6.toFixed(2)
         const Op7 = this.rows.reduce((sum, item) => sum + Number(item.op16), 0);
         this.totalop7=Op7.toFixed(2)

         const Op9 = this.rows.reduce((sum, item) => sum + Number(item.op7), 0);
         this.totalop9=Op9.toFixed(2)
         const Op10 = this.rows.reduce((sum, item) => sum + Number(item.op8), 0);
         this.totalop10=Op10.toFixed(2)
         const Op11 = this.rows.reduce((sum, item) => sum + Number(item.op9), 0);
         this.totalop11=Op11.toFixed(2)
         const Op12 = this.rows.reduce((sum, item) => sum + Number(item.op10), 0);
         this.totalop12=Op12.toFixed(2)
         const Op13 = this.rows.reduce((sum, item) => sum + Number(item.op11), 0);
         this.totalop13=Op13.toFixed(2)

         
         const Op15 = this.rows.reduce((sum, item) => sum + Number(item.op17), 0);
         this.totalop15=Op15.toFixed(2)
         const Op16 = this.rows.reduce((sum, item) => sum + Number(item.op18), 0);
         this.totalop16=Op16.toFixed(2)
         const Op17 = this.rows.reduce((sum, item) => sum + Number(item.op19), 0);
         this.totalop17=Op17.toFixed(2)
         const Op18 = this.rows.reduce((sum, item) => sum + Number(item.op20), 0);
         this.totalop18=Op18.toFixed(2)
         const Op19 = this.rows.reduce((sum, item) => sum + Number(item.op21), 0);
         this.totalop19=Op19.toFixed(2)
         

         const Op21 = this.rows.reduce((sum, item) => sum + Number(item.op22), 0);
         this.totalop21=Op21.toFixed(2)
         const Op22 = this.rows.reduce((sum, item) => sum + Number(item.op23), 0);
         this.totalop22=Op22.toFixed(2)
         const Op23 = this.rows.reduce((sum, item) => sum + Number(item.op24), 0);
         this.totalop23=Op23.toFixed(2)
         const Op24 = this.rows.reduce((sum, item) => sum + Number(item.op25), 0);
         this.totalop24=Op24.toFixed(2)
         const Op25 = this.rows.reduce((sum, item) => sum + Number(item.op26), 0);
         this.totalop25=Op25.toFixed(2)


         
         const Op27 = this.rows.reduce((sum, item) => sum + Number(item.op27), 0);
         this.totalop27=Op27.toFixed(2)
         const Op28 = this.rows.reduce((sum, item) => sum + Number(item.op28), 0);
         this.totalop28=Op28.toFixed(2)
         const Op29 = this.rows.reduce((sum, item) => sum + Number(item.op29), 0);
         this.totalop29=Op29.toFixed(2)
         const Op30 = this.rows.reduce((sum, item) => sum + Number(item.op30), 0);
         this.totalop30=Op30.toFixed(2)
         const Op31 = this.rows.reduce((sum, item) => sum + Number(item.op31), 0);
         this.totalop31=Op31.toFixed(2)

        
         const Op33 = this.rows.reduce((sum, item) => sum + Number(item.op32), 0);
         this.totalop33=Op33.toFixed(2)
         const Op34 = this.rows.reduce((sum, item) => sum + Number(item.op33), 0);
         this.totalop34=Op34.toFixed(2)
         const Op35 = this.rows.reduce((sum, item) => sum + Number(item.op34), 0);
         this.totalop35=Op35.toFixed(2)
         const Op36 = this.rows.reduce((sum, item) => sum + Number(item.op35), 0);
         this.totalop36=Op36.toFixed(2)
         const Op37 = this.rows.reduce((sum, item) => sum + Number(item.op36), 0);
         this.totalop37=Op37.toFixed(2)


         
         const Op39 = this.rows.reduce((sum, item) => sum + Number(item.op37), 0);
         this.totalop39=Op39.toFixed(2)
         const Op40 = this.rows.reduce((sum, item) => sum + Number(item.op38), 0);
         this.totalop40=Op40.toFixed(2)
         const Op41 = this.rows.reduce((sum, item) => sum + Number(item.op39), 0);
         this.totalop41=Op41.toFixed(2)
         const Op42 = this.rows.reduce((sum, item) => sum + Number(item.op40), 0);
         this.totalop42=Op42.toFixed(2)
         const Op43 = this.rows.reduce((sum, item) => sum + Number(item.op41), 0);
         this.totalop43=Op43.toFixed(2)
         
         


            })
        this.resultSet=true;
        console.log(this.eaListForCsv)
    }

    getMonth(){
        this.fromDate = this.datePipe.transform(this.searchFromYear+'-'+this.searchfromMonth+'-'+'01', 'yyyy-MM-dd');
        this.toDate = this.datePipe.transform(this.searchToYear+'-'+this.searchtoMonth+'-'+'28', 'yyyy-MM-dd')
        this.dateRange(this.fromDate,this.toDate);
        this.tempMonthAndYear.forEach(mo=>{
            mo.forEach(element => {
                this.formatedDate(element);
            });
        })
    }

    dateRange(startDate, endDate) {
        var start      = startDate.split('-');
        var end        = endDate.split('-');
        var startYear  = parseInt(start[0]);
        var endYear    = parseInt(end[0]);
        var dates      = [];
      
        for(var i = startYear; i <= endYear; i++) {
          var endMonth = i != endYear ? 11 : parseInt(end[1]) - 1;
          var startMon = i === startYear ? parseInt(start[1])-1 : 0;
          for(var j = startMon; j <= endMonth; j = j > 12 ? j % 12 || 11 : j+1) {
            var month = j+1;
            var displayMonth = month < 10 ? '0'+month : month;
            dates.push([i, displayMonth, '01'].join('-'));
          }
        }
        this.tempMonthAndYear.push(dates);
        return dates;
      }

    formatedDate(fromList){
        this.monthAndYear.push({
            "month":this.datePipe.transform(fromList,'MM'),
            "year":this.datePipe.transform(fromList,'yyyy')
        });
    }

    surplusC1:number=0.0; surplusC2:number=0.0; surplusC3:number=0.0; surplusC4:number=0.0; surplusC5:number=0.0;
    formatData(list){
        list.forEach(element => {
            this.tempObj=element;
            this.sellerCompany=this.tempObj.op2;
        this.sellerEdc=this.tempObj.op3;
        this.fuelType=this.tempObj.op4;
        this.surplusC1=0; this.surplusC2=0; this.surplusC3=0; this.surplusC4=0; this.surplusC5=0;
        this.surplusC1=parseInt(this.tempObj.op27);
        this.surplusC2=parseInt(this.tempObj.op28);
        this.surplusC3=parseInt(this.tempObj.op29);
        this.surplusC4=parseInt(this.tempObj.op30);
        this.surplusC5=parseInt(this.tempObj.op31);
        this.eaListForCsv.push({
            "serviceNo": this.tempObj.op1,
            "companyName":this.tempObj.op2,
            "orgName":this.tempObj.op3,
            "month":this.tempObj.op5,
            "year":this.tempObj.op6,
            "fuelTypeCode":this.tempObj.op4,
            "generatedc1":this.tempObj.op7,
            "generatedc2":this.tempObj.op8,
            "generatedc3":this.tempObj.op9,
            "generatedc4":this.tempObj.op10,
            "generatedc5":this.tempObj.op11,
            "totalgenerated":Number(this.tempObj.op7)+Number(this.tempObj.op8)+Number(this.tempObj.op9)+Number(this.tempObj.op10)+Number(this.tempObj.op11),
            
            "openBc1":this.tempObj.op12,
            "openBc2":this.tempObj.op13,
            "openBc3":this.tempObj.op14,
            "openBc4":this.tempObj.op15,
            "openBc5":this.tempObj.op16,
            "totalopenBc":Number(this.tempObj.op12)+Number(this.tempObj.op13)+Number(this.tempObj.op14)+Number(this.tempObj.op15)+Number(this.tempObj.op16),
            "allotedC1":this.tempObj.op17,
            "allotedC2":this.tempObj.op18,
            "allotedC3":this.tempObj.op19,
            "allotedC4":this.tempObj.op20,
            "allotedC5":this.tempObj.op21,
            "totalalloted":Number(this.tempObj.op17)+Number(this.tempObj.op18)+Number(this.tempObj.op19)+Number(this.tempObj.op20)+Number(this.tempObj.op21),
            "adjustedC1":this.tempObj.op22,
            "adjustedC2":this.tempObj.op23,
            "adjustedC3":this.tempObj.op24,
            "adjustedC4":this.tempObj.op25,
            "adjustedC5":this.tempObj.op26,
            "totaladjusted":Number(this.tempObj.op22)+Number(this.tempObj.op23)+Number(this.tempObj.op24)+Number(this.tempObj.op25)+Number(this.tempObj.op26),
            "surplusC1":(isNaN(this.surplusC1) ? 0: this.surplusC1).toString(),
            "surplusC2":(isNaN(this.surplusC2) ? 0: this.surplusC2).toString(),
            "surplusC3":(isNaN(this.surplusC3) ? 0: this.surplusC3).toString(),
            "surplusC4":(isNaN(this.surplusC4) ? 0: this.surplusC4).toString(),
            "surplusC5":(isNaN(this.surplusC5) ? 0: this.surplusC5).toString(),
            "totalsurplus":Number(isNaN(this.surplusC1) ? 0: this.surplusC1)+Number(isNaN(this.surplusC2) ? 0: this.surplusC2)+Number(isNaN(this.surplusC3) ? 0: this.surplusC3)+Number(isNaN(this.surplusC4) ? 0: this.surplusC4)+Number(isNaN(this.surplusC5) ? 0: this.surplusC5),
            "unAllotedC1":this.tempObj.op32,
            "unAllotedC2":this.tempObj.op33,
            "unAllotedC3":this.tempObj.op34,
            "unAllotedC4":this.tempObj.op35,
            "unAllotedC5":this.tempObj.op36,
            "totalunAlloted":Number(this.tempObj.op32)+Number(this.tempObj.op33)+Number(this.tempObj.op34)+Number(this.tempObj.op35)+Number(this.tempObj.op36),
            "closingC1":this.tempObj.op37,
            "closingC2":this.tempObj.op38,
            "closingC3":this.tempObj.op39,
            "closingC4":this.tempObj.op40,
            "closingC5":this.tempObj.op41,
            "totalclosing":Number(this.tempObj.op37)+Number(this.tempObj.op38)+Number(this.tempObj.op39)+Number(this.tempObj.op40)+Number(this.tempObj.op41),
           
        })
        
        });
        
        const Op8 = this.eaListForCsv.reduce((sum, item) => sum + Number(item.totalopenBc), 0);
           this.totalop8=Op8.toFixed(2)
       const Op14 = this.eaListForCsv.reduce((sum, item) => sum + Number(item.totalgenerated), 0);
         this.totalop14=Op14.toFixed(2)
       const Op20 = this.eaListForCsv.reduce((sum, item) => sum + Number(item.totalalloted), 0);
         this.totalop20=Op20.toFixed(2)
       const Op26 = this.eaListForCsv.reduce((sum, item) => sum + Number(item.totaladjusted), 0);
         this.totalop26=Op26.toFixed(2)
         const Op32 = this.eaListForCsv.reduce((sum, item) => sum + Number(item.totalsurplus), 0);
         this.totalop32=Op32.toFixed(2)
         const Op38 = this.eaListForCsv.reduce((sum, item) => sum + Number(item.totalunAlloted), 0);
         this.totalop38=Op38.toFixed(2)
         const Op44 = this.eaListForCsv.reduce((sum, item) => sum + Number(item.totalclosing), 0);
         this.totalop44=Op44.toFixed(2)
        

       console.log(this.totalop41)
      
    }
    



    detailPage(genServNumber,month,year,searchfromMonth,searchFromYear,searchtoMonth,searchToYear){
        this.router.navigateByUrl('/reports/energy-audit-detail/'+genServNumber+'/'+month+'/'+year+'/'+searchfromMonth+'/'+searchFromYear+'/'+searchtoMonth+'/'+searchToYear);
    }

    printGenratedUnits(gsList){
        this.reportService.printEnergyAuditReportGenUnits(gsList).subscribe(xs=>{
            this.openDownloadGenUnit(xs);
            this.message = "Downloaded Successfully";
        },error=>{
            if (JSON.parse(error._body).message) {
              this.message = JSON.parse(error._body).message;
            } else {
              this.message = "Too many request please try again later"
            }
          });
        
    }

    openDownloadGenUnit(data:Response){
        let x_filename = "energy-audit-report-genUnits.pdf";
        saveAs(data.blob(), x_filename);
    }
    
    exportAsXLSX():any {     
        console.log("in export",this.eaListForCsv)
        this.eaListForCsv.push({"serviceNo":"TOTAL",
        "companyName":"",
        "orgName":"",
        "month":"",
        "year":"",
        "fuelTypeCode":"",
        "generatedc1":this.totalop9,
        "generatedc2":this.totalop10,
        "generatedc3":this.totalop11,
        "generatedc4":this.totalop12,
        "generatedc5":this.totalop13,
        "totalgenerated":this.totalop14,
        
        "openBc1":this.totalop3,
        "openBc2":this.totalop4,
        "openBc3":this.totalop5,
        "openBc4":this.totalop6,
        "openBc5":this.totalop7,
        "totalopenBc":this.totalop8,
        
        "allotedC1":this.totalop15,
        "allotedC2":this.totalop16,
        "allotedC3":this.totalop17,
        "allotedC4":this.totalop18,
        "allotedC5":this.totalop19,
        "totalalloted":this.totalop20,
        "adjustedC1":this.totalop21,
        "adjustedC2":this.totalop22,
        "adjustedC3":this.totalop23,
        "adjustedC4":this.totalop24,
        "adjustedC5":this.totalop25,
        "totaladjusted":this.totalop26,
        "surplusC1":this.totalop27,
        "surplusC2":this.totalop28,
        "surplusC3":this.totalop29,
        "surplusC4":this.totalop30,
        "surplusC5":this.totalop31,
        "totalsurplus":this.totalop32,
        "unAllotedC1":this.totalop33,
        "unAllotedC2":this.totalop34,
        "unAllotedC3":this.totalop35,
        "unAllotedC4":this.totalop36,
        "unAllotedC5":this.totalop37,
        "totalunAlloted":this.totalop38,
        "closingC1":this.totalop39,
        "closingC2":this.totalop40,
        "closingC3":this.totalop41,
        "closingC4":this.totalop42,
        "closingC5":this.totalop43,
        "totalclosing":this.totalop44,
       })
        let str = JSON.stringify(this.eaListForCsv);
        str = str.replace(/\"op1\":/g, "\"S.NO\":");
        str = str.replace(/\"op2\":/g, "\"ORG ID\":");
        str = str.replace(/\"op3\":/g, "\"EDC\":");
        str = str.replace(/\"op4\":/g, "\"METER READING\":");
        str = str.replace(/\"op5\":/g, "\"MONTH\":");
        str = str.replace(/\"op6\":/g, "\"YEAR\":");
        str = str.replace(/\"op7\":/g, "\"FUEL TYPE\":");
        str = str.replace(/\"op8\":/g, "\"THIRD PARTY\":");
        str = str.replace(/\"op9\":/g, "\"CAPTIVE ALLOTMENT\":");
        str = str.replace(/\"op10\":/g, "\"TOTAL ALLOTMENT\":");
        str = str.replace(/\"op11\":/g, "\"MANULAR READING\":");
        this.eaListForCsv = JSON.parse(str);
        
     this.excelService.exportAsExcelFile(this.eaListForCsv, 'AUDIT-REPORT-GEN-DETAILS');
    }

    ///To allow number only
    numberFormat(event){
        this.commonService.numberOnly(event)
    }

}