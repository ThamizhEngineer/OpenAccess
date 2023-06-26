import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { saveAs } from 'file-saver';
import { ReportService } from '../../../services/report.service';
import { CommonService } from '../../../../shared/common/common.service';
import { ExcelExportService } from '../../../../shared/services/excelExport';
import { ActivatedRoute, Params } from '@angular/router';
import { GenericReportOutput } from '../../../vo/GenericReportOutput';
import { GenericReportInput } from '../../../vo/GenericReportInput';

@Component({
  selector: 'meter-readingcalc-report-detail',
  templateUrl: './meter-readingcalc-report-detail.html',
  providers: [ ReportService]
})
export class MeterReadingcalcdetailComponent implements OnInit {
  data:any;
  months:any;
  years:any;
  fueltypecode: any;
  meterdetail: any;
  type: any;
  sourceCode: any;
  flowtype: any;
  constructor(private service:ReportService,private excelService: ExcelExportService, private commonService:CommonService,private route: ActivatedRoute,private router:Router) {
  }


    ngOnInit(): void {
      this.months = this.commonService.fetchMonths();
      this.years = this.commonService.fetchYearList();
      this.route.paramMap
      .subscribe((_params: Params) => {
        console.log(_params)
        this.data = _params.get('dispOrgCode');
        this.months = _params.get('readMonth');
        this.years = _params.get('readYear');
        this.fueltypecode = _params.get('fuelTypeCode');
         this.type = _params.get('type')

        this.getdetaildata();
  
      });

    }
   
    getdetaildata(){

      if(this.type=='AMR'){
        console.log("AMR");
        this.sourceCode='01';
      }
      
      if(this.type=='MANUAL'){
        console.log("MANUAL");
        this.sourceCode='02';
      }

      if(this.type=='TOTALCAPTIVE'|| this.type=='AMRCAPTIVE'|| this.type=='MANUALCAPTIVE'|| this.type=='NOTRUNNINGCAPTIVE'){
        console.log(this.type);   
        this.flowtype='IS-CAPTIVE';
      }

      if(this.type=='TOTALTHIRD-PARTY'|| this.type=='AMRTHIRD-PARTY'|| this.type=='MANUALTHIRD-PARTY'|| this.type=='NOTRUNNINGTHIRD-PARTY'){
        console.log(this.type);
        this.flowtype='THIRD-PARTY';
      }

      if(this.type=='TOTALSTB'|| this.type=='AMRSTB'|| this.type=='MANUALSTB'|| this.type=='NOTRUNNINGSTB'){
        console.log(this.type);
        this.flowtype='STB';
      }

      let genRptInput:GenericReportInput = new GenericReportInput();
      genRptInput.reportName = 'METER READING SERVICE DETAIL REPORT';     
      
           if(this.data != "" && 
               this.data != null){
               genRptInput.ip1 = this.data
              }

              if(this.months != "" && 
              this.months != null){
                genRptInput.ip2 = this.months
              }
                if(this.years != "" && 
              this.years != null){
                genRptInput.ip3 = this.years
              }
             if(this.fueltypecode != "" && 
             this.fueltypecode != null&& this.fueltypecode != "undefined"){
             genRptInput.ip4 = this.fueltypecode
            }

            if(this.sourceCode != "" && 
            this.sourceCode != null){
            genRptInput.ip5 = this.sourceCode
           }

            if(this.type != "" && 
            this.type != null){
            genRptInput.ip6 = this.type
           }

           if(this.flowtype != "" && 
           this.flowtype != null){
           genRptInput.ip7 = this.flowtype
          }

          this.service.getMonthlyProgressService(genRptInput).subscribe(x=>{
            this.meterdetail=x;    
            console.log(this.meterdetail);
            
        })  
      }

Cancel(){
  this.router.navigateByUrl('/reports/meter-readingcalc-component');
}

  exportAsXLSX(): any {
    let str = JSON.stringify(this.meterdetail);
    str = str.replace(/\"op1\":/g, "\"EDC CODE\":");
    str = str.replace(/\"op3\":/g, "\"SERVICE NO\":");
    str = str.replace(/\"op4\":/g, "\"COMPANY NAME\":");
    str = str.replace(/\"op5\":/g, "\"FLOW TYPE\":");
    str = str.replace(/\"op6\":/g, "\"METER NO\":");
    if(this.type=='MANUAL'){
      str = str.replace(/\"op7\":/g, "\"CREATED DATE\":");
    }
    this.meterdetail = JSON.parse(str);

    this.meterdetail.forEach(x =>{
      delete x.$$index;
      delete x.op2;
      delete x.op7;delete x.op8;delete x.op9;delete x.op10;delete x.op11;delete x.op12
      delete x.op13;delete x.op14;delete x.op15;delete x.op16;delete x.op17;delete x.op18;delete x.op19;delete x.op20;
      delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
      delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
      delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
    });
  
   
    this.excelService.exportAsExcelFile(this.meterdetail, 'Monthly Progess Report For '+this.type);
    this.getdetaildata();

  }

  openDownload(data: Response) {
    let content_type = data.headers.get('Content-type');
  
    let x_filename = "Monthly Progress Report For "+this.type+".pdf";
    console.log(content_type)
    saveAs(data.blob(), x_filename);
  }

  printReport(){ 

    this.service.printReportTotalService(this.data,this.months,this.years,this.fueltypecode,this.sourceCode,this.type,this.flowtype).subscribe(xs=>{
      this.openDownload(xs);
    });

  }


  }
