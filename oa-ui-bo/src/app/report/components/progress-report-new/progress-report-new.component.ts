import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { CommonUtils } from '../../../shared/common/common-utils';
import { ActivatedRoute, Params } from '@angular/router';


@Component({
  selector: 'progress-report-new',
  templateUrl: './progress-report-new.component.html',
})
export class ProgressReportNewComponent implements OnInit {
    months: { value: string; name: string; }[];
    fuelTypes: any;
    rows: any;
    stmtMonth: string;
    stmtYear: string;
    fuelTypeCode: string;
    tMeterReading: any;
    totalMeterReading: any;
    totalThirdParty: any;
    totalCaptive: any;
    totalTotalAllot: any;
    totalManualReading: any;
    pr: any;
    resultSet:boolean=false;
    fuelTypeList = [
   
      { "key": "WIND", "name": "Wind" },
       {"key": "COAL", "name": "Coal" },
       { "key": "SOLAR", "name": "Solar" },
      { "key": "BIOGASS", "name": "Bio-Gas" },
      { "key": "NATURAL GAS", "name": "NaturalGas" },
      { "key": "WASTE HEAT", "name": "Waste Heat " },
      { "key": "BIO-MASS", "name": "Bio-Mass" },
      { "key": "BAGASSE", "name": "Bagasse " },
      { "key": "DIESEL", "name": "Diesel" }
          
     ];
    constructor(private service:ReportService,private route: ActivatedRoute, private commonService:CommonService,private excelService: ExcelExportService) { 
    }

    ngOnInit() {
        this.fetchMonths();
        this.fetchFuelTypes();

        this.route.params
            .subscribe((_params: Params)=>{
                this.stmtMonth =_params['stmtMonth'];
                this.stmtYear =_params['stmtYear'];
                this.fuelTypeCode = _params['fuelTypeCode'];
            });
    }

    fetchMonths(){
        this.months = this.commonService.fetchMonths();
        console.log(this.months)
      }

      fetchFuelTypes(){
        this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x=>{
        this.fuelTypes=x;
        // console.log(this.fuelTypes)
    })
  }

  searchResults(){
    let genRptInput:GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'PROGRESS-REPORT-NEW';

    if(this.stmtMonth != "" && 
    this.stmtMonth != null)
      genRptInput.ip1 = this.stmtMonth

      if(this.stmtYear != "" && 
    this.stmtYear != null)
      genRptInput.ip2 = this.stmtYear

      if(this.fuelTypeCode != "" && 
    this.fuelTypeCode != null)
      genRptInput.ip3 = this.fuelTypeCode
      // console.log(genRptInput);
      this.service.getAllTnerc(genRptInput).subscribe(x=>{
        this.rows=x;

        // console.log(this.rows);

        const mr = this.rows.reduce((sum, item) => sum + Number(item.op4), 0);
        this.totalMeterReading=mr;

        const ttp = this.rows.reduce((sum, item) => sum + Number(item.op8), 0);
        this.totalThirdParty=ttp

        const tca = this.rows.reduce((sum, item) => sum + Number(item.op9), 0);
        this.totalCaptive=tca

        const tta = this.rows.reduce((sum, item) => sum + Number(item.op10), 0);
        this.totalTotalAllot=tta

        const tmr = this.rows.reduce((sum, item) => sum + Number(item.op11), 0);
        this.totalManualReading=tmr
    })     
    this.resultSet=true;
  }

  openDownload(data: Response) {
    let content_type = data.headers.get('Content-type');
    let x_filename = "progress-report-new.pdf";
    console.log(content_type)
    saveAs(data.blob(), x_filename);
  }
  
  printReport(){ 
    this.service.printProgress(this.stmtMonth,this.stmtYear,this.fuelTypeCode).subscribe(xs=>{
      this.pr=xs;
      this.openDownload(xs);
    });
  }

  exportAsXLSX():any {     
    // console.log(this.rows)
    this.rows.push({op1:"TOTAL",op2:"",op3:"",op4:this.totalMeterReading,op5:"",op6:"",op7:"",op8:this.totalThirdParty,
  op9:this.totalCaptive,op10:this.totalTotalAllot,op11:this.totalManualReading})
    // this.rows.push( "op4",this.totalMeterReading);
    // console.log(this.rows)
//         this.rows.push(this.totalAdj);
    let str = JSON.stringify(this.rows);
    str = str.replace(/\"op1\":/g, "\"ID\":");
    str = str.replace(/\"op2\":/g, "\"ORG ID\":");
    str = str.replace(/\"op3\":/g, "\"EDC\":");
    str = str.replace(/\"op4\":/g, "\"METER READING\":");
    str = str.replace(/\"op5\":/g, "\"MONTH\":");
    str = str.replace(/\"op6\":/g, "\"YEAR\":");
    str = str.replace(/\"op7\":/g, "\"FUEL TYPE\":");
    str = str.replace(/\"op8\":/g, "\"THIRD PARTY\":");
    str = str.replace(/\"op9\":/g, "\"CAPTIVE ALLOTMENT\":");
    str = str.replace(/\"op10\":/g, "\"TOTAL ALLOTMENT\":");
    str = str.replace(/\"op11\":/g, "\"MANUAL READING\":");
    this.rows = JSON.parse(str);
    
    // this.rows.push(this.totalMeterReading);
    // console.log(this.rows)
 this.excelService.exportAsExcelFile(this.rows, 'PROGRESS-REPORT');
 this.searchResults();
}
}