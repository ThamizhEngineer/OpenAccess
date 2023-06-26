import { filter } from 'rxjs/operator/filter';
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
  selector: 'generationstmt-report',
  templateUrl: './generationstmt-report.component.html',
})
export class GemerationstmtReportComponent implements OnInit {
    months: { value: string; name: string; }[];

    rows: any;
    Orgid: any;
    years=[];
    Readmonth: any;
    Readyear: any;
    edcList: any;
    printdata: any;
    edcid: any;
    fuelType:any;
    disableEdc:boolean;
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

    constructor(private service:ReportService,private route: ActivatedRoute, private commonService:CommonService,private excelService: ExcelExportService) {
    }

    ngOnInit() {
      this.fetchOrgList();
      this.years = this.commonService.fetchYearList();
      
      this.fetchMonths();
      this.edcid = CommonUtils.getLoginUserEDC();
      if (this.edcid != "") {
        console.log("in edc select")
        this.Orgid = this.edcid;
        this.disableEdc = true; 
      }
           
    }

    fetchMonths(){
      this.months = this.commonService.fetchMonths();
    }

  searchResults(){
    let genRptInput:GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'GENERATION REPORT';

    if(this.Orgid != "" &&
    this.Orgid != null)
      genRptInput.ip1 = this.Orgid

      if(this.Readmonth != "" &&
    this.Readmonth != null)
      genRptInput.ip2 = this.Readmonth

      if(this.Readyear != "" &&
      this.Readyear != null)
        genRptInput.ip3 = this.Readyear

        if(this.fuelType != "" &&
        this.fuelType != null)
          genRptInput.ip4 = this.fuelType
      
      this.service.getGenerationstmt(genRptInput).subscribe(x=>{
        this.rows=x;

    })

  }

  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
       const fuels= result.filter(item => item.fuelTypeCode=="WIND");
       this.edcList=fuels;
      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }


  openDownload(data: Response) {
    let content_type = data.headers.get('Content-type');
    let x_filename = "generation-report.pdf";
    console.log(content_type)
    saveAs(data.blob(), x_filename);
  }

  printReport(){
    this.service.printGenerationstmtReport(this.Orgid,this.Readmonth,this.Readyear,this.fuelType).subscribe(xs=>{
      this.printdata=xs;
      this.openDownload(xs);
    });
  }


  exportAsXLSX():any {

    let str = JSON.stringify(this.rows);

    str = str.replace(/\"op1\":/g, "\"EDC ID\":");
    str = str.replace(/\"op2\":/g, "\"EDC NAME\":");
    str = str.replace(/\"op3\":/g, "\"THIRDPARTY COUNT\":");
    str = str.replace(/\"op4\":/g, "\"THIRDPARTY\":");
    str = str.replace(/\"op5\":/g, "\"CAPTIVE COUNT\":");
    str = str.replace(/\"op6\":/g, "\"CAPTIVE\":");
    str = str.replace(/\"op7\":/g, "\"STB COUNT\":");
    str = str.replace(/\"op8\":/g, "\"STB\":");
    str = str.replace(/\"op9\":/g, "\"TOTAL COUNT\":");
    str = str.replace(/\"op10\":/g, "\"TOTAL CAPACITY\":");
    str = str.replace(/\"op11\":/g, "\"THIRDPARTY IMPORT\":");
    str = str.replace(/\"op12\":/g, "\"THIRDPARTY EXPORT\":");
    str = str.replace(/\"op13\":/g, "\"THIRDPARTY NET\":");
    str = str.replace(/\"op14\":/g, "\"CAPTIVE IMPORT\":");
    str = str.replace(/\"op15\":/g, "\"CAPTIVE EXPORT\":");
    str = str.replace(/\"op16\":/g, "\"CAPTIVE NET\":");
    str = str.replace(/\"op17\":/g, "\"STB IMPORT\":");
    str = str.replace(/\"op18\":/g, "\"STB EXPORT\":");
    str = str.replace(/\"op19\":/g, "\"STB NET\":");
   
    this.rows = JSON.parse(str);
    this.rows.forEach(x =>{
      delete x.$$index;
      delete x.op7;delete x.op8;delete x.op9;delete x.op10;delete x.op11;delete x.op12
      delete x.op13;delete x.op14;delete x.op15;delete x.op16;delete x.op17;delete x.op18;delete x.op19;delete x.op20;
      delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
      delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
      delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
    });

    this.excelService.exportAsExcelFile(this.rows,'generation-report');
    this.searchResults();
}

}

