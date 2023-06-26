import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';


@Component({
  selector: 'generator-wise-generation-report',
  templateUrl: './generator-wise-generation-report.component.html',
//   styleUrls: ['./generator-wise-generation-report.component.scss']
})
export class GeneratorWiseGenerationReportComponent implements OnInit {
    rows: any;
    stmtMonth: string;
    stmtYear: string;
    fuelTypeCode: string;
    flowTypeCode: string;
    orgCode: string;
    orgList: any;
    months: { value: string; name: string; }[];
    fuelTypes: any;

    flowTypes = [
        { "key": "STB", "name": "SALE-TO-BOARD" },
        { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
        { "key": "THIRD-PARTY", "name": "THIRD-PARTY" }

      ]
  fromMonth: string;
  toMonth: string;
  fromYear: string;
  toYear: string;
  years=[];
  totalop7: any;
  totalop8: any;
  totalop9: any;
  totalop10: any;
  totalop11: any;
  totalop12: any;

    constructor(private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) { }

    ngOnInit() {
        this.fetchEDC();
        this.fetchFuelTypes();
        this.months = this.commonService.fetchMonths();
        this.years = this.commonService.fetchYearList();
    
      }

      fetchFuelTypes(){
        this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x=>{
        this.fuelTypes=x;
    })
}


fetchEDC(){
    this.commonService.fetchEDCs().subscribe(x=>{
    this.orgList=x;
})
}



      searchResults(){
        let genRptInput:GenericReportInput = new GenericReportInput();
        genRptInput.reportName = 'GENERATOR-WISE-GENERATION-REPORT';
    
        if(this.fromMonth != "" && 
        this.fromMonth != null)
          genRptInput.ip1 = this.fromMonth

          if(this.toMonth != "" && 
        this.toMonth != null)
          genRptInput.ip2 = this.toMonth

          if(this.fromYear != "" && 
        this.fromYear != null)
          genRptInput.ip3 = this.fromYear

          if(this.toYear != "" && 
        this.toYear != null)
          genRptInput.ip4 = this.toYear

          if(this.fuelTypeCode != "" && 
        this.fuelTypeCode != null)
          genRptInput.ip5 = this.fuelTypeCode

          if(this.flowTypeCode != "" && 
        this.flowTypeCode != null)
          genRptInput.ip6 = this.flowTypeCode

          if(this.orgCode != "" && 
        this.orgCode != null)
          genRptInput.ip7 = this.orgCode
        
          console.log(genRptInput)
          this.service.getAllTnerc(genRptInput).subscribe(x=>{
            this.rows=x;
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
          })
       
      }

      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "generator-wise-generation-report.pdf";
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }

      printReport(){ 
        this.service.printGenWiseGeneration(this.fromMonth,this.toMonth,this.fromYear,this.toYear,this.fuelTypeCode,this.flowTypeCode,this.orgCode).subscribe(xs=>{
          this.openDownload(xs);
        });
    
      }

      exportAsXLSX():any {     
        this.rows.push({op1:"TOTAL",op2:"",op3:"",op4:"",op5:"",op6:"",op7:this.totalop7,op8:this.totalop8,op9:"",op10:this.totalop10,op11:this.totalop11,op12:this.totalop12})
        let str = JSON.stringify(this.rows);
        str = str.replace(/\"op1\":/g, "\"SERVICE NO\":");
        str = str.replace(/\"op2\":/g, "\"METER NUMBER\":");
        str = str.replace(/\"op3\":/g, "\"COMPANY NAME\":");
        str = str.replace(/\"op4\":/g, "\"EDC\":");
        str = str.replace(/\"op5\":/g, "\"MONTH\":");
        str = str.replace(/\"op6\":/g, "\"YEAR\":");
        str = str.replace(/\"op7\":/g, "\"CAPACITY\":");
        str = str.replace(/\"op8\":/g, "\"MF\":");
        str = str.replace(/\"op9\":/g, "\"IS RE\":");
        str = str.replace(/\"op10\":/g, "\"TOTAL EXPORT GEN\":");
        str = str.replace(/\"op11\":/g, "\"TOTAL IMPORT GEN\":");
        str = str.replace(/\"op12\":/g, "\"NET GENERATION\":");
        str = str.replace(/\"op13\":/g, "\"FLOW TYPE CODE\":");
        str = str.replace(/\"op14\":/g, "\"FUEL TYPE NAME\":");
        this.rows = JSON.parse(str);

        this.rows.forEach(x =>{
          delete x.$$index;
          delete x.op13
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
          delete x.Op27
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
        
     this.excelService.exportAsExcelFile(this.rows, 'CONS-ENERGY-ADJUSTED-ORDER-REPORT');
     this.searchResults();
    }


    }