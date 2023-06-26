import { Component, OnInit } from '@angular/core';
import { saveAs } from 'file-saver';
import { DatePipe } from '@angular/common';
import { DateAdapter } from '@angular/material';
import { ReportService } from '../../../services/report.service';
import { CommonService } from '../../../../shared/common/common.service';
import { ExcelExportService } from '../../../../shared/services/excelExport';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { GenericReportInput } from '../../../vo/GenericReportInput';
import { Router } from '@angular/router';

@Component({
  selector: 'meter-readingcalc-component',
  templateUrl: './meter-readingcalc-report.html'
//   styleUrls: ['./consolidate-energy-adjustment-report.component.scss']
})
export class MeterReadingcalcComponent implements OnInit {
  
  tabs:boolean;
  meteropt: any;
  edcList: any;
  fuelTypeCode:any;
  dispOrgCode: any;
  readYear: any;
  readMonth: string;
  fuelTypes: any;
  sumTotalService:any;
  sumAMRServices:any;
  sumManualService:any;
  sumNotrunService:any;
  sumCaptive:any;
  sumThird:any;
  sumSTB:any;
  edcid:any;
  disableEdc:boolean;
  Usertype: any;
  months: { value: string; name: string; }[];
  years=[];
  fuelTypeList = [

    { "key": "WIND", "name": "Wind" },
    { "key": "COAL", "name": "Coal" },
    { "key": "SOLAR", "name": "Solar" },
    { "key": "BIOGASS", "name": "Bio-Gas" },
    { "key": "NATURAL GAS", "name": "NaturalGas" },
    { "key": "WASTE HEAT", "name": "Waste Heat " },
    { "key": "BIO-MASS", "name": "Bio-Mass" },
    { "key": "BAGASSE", "name": "Bagasse " },
    { "key": "DIESEL", "name": "Diesel" }

  ];
  sumCAPAMR: any;
  sumTHIRDAMR: any;
  sumSTBAMR: any;
  sumManualCAP: any;
  sumManualTHIRD: any;
  sumManualSTB: any;
  sumNotrunCAP: any;
  sumNotrunTHIRD: any;
  sumNotrunSTB: any;

  constructor(private service:ReportService, private commonService:CommonService, private excelService: ExcelExportService,private router: Router) {
  }


    ngOnInit(): void {
     this.Usertype= CommonUtils.getLoginUserTypeName();
      this.edcid = CommonUtils.getLoginUserEDC();
      if (this.edcid != "") {
        console.log("in edc select")
        this.dispOrgCode = this.edcid;
        this.disableEdc = true;
      }
      this.years = this.commonService.fetchYearList();
      this.fetchMonths();
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

    fetchMonths(){
      this.months = this.commonService.fetchMonths();
      console.log(this.months)
    }

    searchResults(){
      let genRptInput:GenericReportInput = new GenericReportInput();
      genRptInput.reportName = 'METER READING SERVICE STATUS REPORT';     
      
           if(this.dispOrgCode != "" && 
               this.dispOrgCode != null){
               genRptInput.ip1 = this.dispOrgCode
              }

              if(this.readMonth != "" && 
              this.readMonth != null){
                genRptInput.ip2 = this.readMonth
              }
                if(this.readYear != "" && 
              this.readYear != null){
                genRptInput.ip3 = this.readYear
              }
             if(this.fuelTypeCode != "" && 
             this.fuelTypeCode != null){
             genRptInput.ip4 = this.fuelTypeCode
            }

          this.service.getMeterDownloadCalc(genRptInput).subscribe(x=>{
            this.meteropt=x;    
            console.log(this.meteropt);

            const sumTotal = this.meteropt.filter(item => item.op3)
                        .reduce((sum, current) => sum + Number(current.op3), 0);
            console.log(sumTotal);
            this.sumTotalService=sumTotal;

            const sumCaptive = this.meteropt.filter(item => item.op4)
            .reduce((sum, current) => sum + Number(current.op4), 0);
            console.log(sumCaptive);
            this.sumCaptive=sumCaptive;

            const sumThird = this.meteropt.filter(item => item.op5)
            .reduce((sum, current) => sum + Number(current.op5), 0);
            console.log(sumThird);
            this.sumThird=sumThird;

            const sumSTB = this.meteropt.filter(item => item.op6)
            .reduce((sum, current) => sum + Number(current.op6), 0);
            console.log(sumSTB);
            this.sumSTB=sumSTB;

            const sumAMR = this.meteropt.filter(item => item.op7)
            .reduce((sum, current) => sum + Number(current.op7), 0);
            console.log(sumAMR);
            this.sumAMRServices=sumAMR;

            const sumCAPAMR = this.meteropt.filter(item => item.op8)
            .reduce((sum, current) => sum + Number(current.op8), 0);
            console.log(sumCAPAMR);
            this.sumCAPAMR=sumCAPAMR;

            const sumTHIRDAMR = this.meteropt.filter(item => item.op9)
            .reduce((sum, current) => sum + Number(current.op9), 0);
            console.log(sumTHIRDAMR);
            this.sumTHIRDAMR=sumTHIRDAMR;

            const sumSTBAMR = this.meteropt.filter(item => item.op10)
            .reduce((sum, current) => sum + Number(current.op10), 0);
            console.log(sumSTBAMR);
            this.sumSTBAMR=sumSTBAMR;


            const sumManual = this.meteropt.filter(item => item.op11)
            .reduce((sum, current) => sum + Number(current.op11), 0);
            console.log(sumManual);
            this.sumManualService=sumManual;

            const sumManualCAP = this.meteropt.filter(item => item.op12)
            .reduce((sum, current) => sum + Number(current.op12), 0);
            console.log(sumManualCAP);
            this.sumManualCAP=sumManualCAP;

            const sumManualTHIRD = this.meteropt.filter(item => item.op13)
            .reduce((sum, current) => sum + Number(current.op13), 0);
            console.log(sumManualTHIRD);
            this.sumManualTHIRD=sumManualTHIRD;

            const sumManualSTB = this.meteropt.filter(item => item.op14)
            .reduce((sum, current) => sum + Number(current.op14), 0);
            console.log(sumManualSTB);
            this.sumManualSTB=sumManualSTB;

            const sumNotrun = this.meteropt.filter(item => item.op15)
            .reduce((sum, current) => sum + Number(current.op15), 0);
            console.log(sumNotrun);
            this.sumNotrunService=sumNotrun;

            const sumNotrunCAP = this.meteropt.filter(item => item.op16)
            .reduce((sum, current) => sum + Number(current.op16), 0);
            console.log(sumNotrunCAP);
            this.sumNotrunCAP=sumNotrunCAP;

            const sumNotrunTHIRD = this.meteropt.filter(item => item.op17)
            .reduce((sum, current) => sum + Number(current.op17), 0);
            console.log(sumNotrunTHIRD);
            this.sumNotrunTHIRD=sumNotrunTHIRD;

            const sumNotrunSTB = this.meteropt.filter(item => item.op18)
            .reduce((sum, current) => sum + Number(current.op18), 0);
            console.log(sumNotrunSTB);
            this.sumNotrunSTB=sumNotrunSTB;
            
        })  
      }

      exportAsXLSX(): any {
    if(this.Usertype!='EDC User'){
         this.meteropt.push({op1:"",op2:"TOTAL",op3:this.sumTotalService,op4:this.sumCaptive,op5:this.sumThird,op6:this.sumSTB,op7:this.sumAMRServices,
         op8:this.sumAMRServices,op9:this.sumTHIRDAMR,op10:this.sumSTBAMR,op11:this.sumManualService,op12:this.sumManualCAP,op13:this.sumManualTHIRD,op14:this.sumManualSTB
         ,op15:this.sumNotrunService,op16:this.sumNotrunCAP,op17:this.sumNotrunTHIRD,op18:this.sumNotrunSTB})
        }
        let str = JSON.stringify(this.meteropt);
        str = str.replace(/\"op1\":/g, "\"EDC CODE\":");
        str = str.replace(/\"op2\":/g, "\"EDC NAME\":");
        str = str.replace(/\"op3\":/g, "\"TOTAL SERVICES\":");
        str = str.replace(/\"op4\":/g, "\"CAPTIVE\":");
        str = str.replace(/\"op5\":/g, "\"THIRD PARTY\":");
        str = str.replace(/\"op6\":/g, "\"STB\":");
        str = str.replace(/\"op7\":/g, "\"AMR\":");
        str = str.replace(/\"op8\":/g, "\"CAPTIVE IN AMR\":");
        str = str.replace(/\"op9\":/g, "\"THIRD PARTY IN AMR\":");
        str = str.replace(/\"op10\":/g, "\"STB IN AMR\":");
        str = str.replace(/\"op11\":/g, "\"MANUAL\":");
        str = str.replace(/\"op12\":/g, "\"CAPTIVE IN MANUAL\":");
        str = str.replace(/\"op13\":/g, "\"THIRD PARTY IN MANUAL\":");
        str = str.replace(/\"op14\":/g, "\"STB IN MANUAL\":");
        str = str.replace(/\"op15\":/g, "\"MACHINE NOT RUNNING/DC SERVICES\":");
        str = str.replace(/\"op16\":/g, "\"CAPTIVE IN MNR\":");
        str = str.replace(/\"op17\":/g, "\"THIRD PARTY IN MNR\":");
        str = str.replace(/\"op18\":/g, "\"STB IN MNR\":");
        this.meteropt = JSON.parse(str);

        this.meteropt.forEach(x =>{
          delete x.$$index;
         delete x.op19;delete x.op20;
          delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
          delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
          delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
        });
      
        this.excelService.exportAsExcelFile(this.meteropt, 'Meter-reading-import-status-report');
        this.searchResults();
      }

      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "Monthly Progress report.pdf";
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }

      printReport(){ 
        this.service.printMeterStatusReport(this.dispOrgCode,this.readMonth,this.readYear,this.fuelTypeCode).subscribe(xs=>{
          this.openDownload(xs);
        });
    
      }

      getspeificvalue(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'Total');
      }

      getAmrvalue(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'AMR');
      }

      getManualvalue(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'MANUAL');
      }

      getNotReading(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'NotReading');
      }

      getTotaCap(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'TOTALCAPTIVE');
      }
      getTotalThird(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'TOTALTHIRD-PARTY');
      }
      getTotalSTB(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'TOTALSTB');
      }
      getAmrCap(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'AMRCAPTIVE');
      }
      getAmrThird(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'AMRTHIRD-PARTY');
      }
      getAmrSTB(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'AMRSTB');
      }
      getManualCap(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'MANUALCAPTIVE');
      }
      getManualThird(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'MANUALTHIRD-PARTY');
      }
      getManualSTB(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'MANUALSTB');
      }
      getMNRCap(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'NOTRUNNINGCAPTIVE');
      }
      getMNRThird(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'NOTRUNNINGTHIRD-PARTY');
      }
      getMNRSTB(dispOrgCode,readMonth,readYear,fuelTypeCode){
        this.router.navigateByUrl('/reports/meter-readingcalc-report-detail/'+dispOrgCode+'/'+readMonth+'/'+readYear+'/'+fuelTypeCode+'/'+'NOTRUNNINGSTB');
      }

  }


