import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { CommonUtils } from '../../../shared/common/common-utils';


@Component({
  selector: 'gen-wise-charge-alloc-to-ht',
  templateUrl: './gen-wise-charge-alloc-to-ht.component.html',
//   styleUrls: ['./consolidate-energy-adjustment-report.component.scss']
})
export class GenWiseChargeAllocToHtComponent implements OnInit {
    stmtMonth: string;
    stmtYear: string;
    fuelTypeCode: string;
    dispOrgCode: string;
    flowTypeCode: string;
    serviceNo: string;
    gen: any;
    totalop3: any;
  totalop12: any;
    months: { value: string; name: string; }[];
    edcList: any;
    fuelTypes: any;
    pr: any;
    edc: any;
    disableEdc: boolean;
    flowTypes = [
      { "key": "STB", "name": "SALE-TO-BOARD" },
      { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
      { "key": "THIRD-PARTY", "name": "THIRD-PARTY" }
    ]
  years=[];


    constructor(private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) { 

    }

    ngOnInit() {
        console.log(CommonUtils.getLoginUserEDC());
        this.edc = CommonUtils.getLoginUserEDC();
        this.years = this.commonService.fetchYearList();
      if (this.edc != "") {
        console.log("in edc select")
        this.dispOrgCode = this.edc;
        this.disableEdc = true;
      }
        this.fetchMonths();
        this.fetchOrgList();
        this.fetchFuelTypes();
        
    }

    searchResults(){
        let genRptInput:GenericReportInput = new GenericReportInput();
        genRptInput.reportName = 'GEN-CHARGES-ALLOC-TO-HT';     
  
          if(this.stmtMonth != "" && 
        this.stmtMonth != null)
          genRptInput.ip1 = this.stmtMonth
  
          if(this.stmtYear != "" && 
        this.stmtYear != null)
          genRptInput.ip2 = this.stmtYear

          if(this.flowTypeCode != "" && 
          this.flowTypeCode != null)
            genRptInput.ip3 = this.flowTypeCode

          if(this.fuelTypeCode != "" && 
        this.fuelTypeCode != null)
          genRptInput.ip4 = this.fuelTypeCode
  
          if(this.dispOrgCode != "" && 
          this.dispOrgCode != null)
            genRptInput.ip5 = this.dispOrgCode        

            if(this.serviceNo != "" && 
          this.serviceNo != null)
            genRptInput.ip6 = this.serviceNo   
        
          console.log(genRptInput)
  
          this.service.getAllTnerc(genRptInput).subscribe(x=>{
            this.gen=x;

            console.log(genRptInput)
            console.log(this.gen)

            const Op3 = this.gen.reduce((sum, item) => sum + Number(item.op3), 0);
            this.totalop3=Op3.toFixed(2)
            const Op12 = this.gen.reduce((sum, item) => sum + Number(item.op12), 0);
            this.totalop12=Op12.toFixed(2)




        })
  
        console.log(genRptInput.reportName);
        console.log(genRptInput.ip1);
        console.log(genRptInput.ip2);
        console.log(genRptInput.ip3);
        console.log(genRptInput.ip4);
        console.log(genRptInput.ip5);
        console.log(genRptInput.ip6);
      }

      fetchMonths(){
        this.months = this.commonService.fetchMonths();
        console.log(this.months)
      }

      fetchOrgList() {
        this.commonService.fetchEDCs().subscribe(
          result => {
            this.edcList = result;
            console.log(this.edcList)
          },
          error => {
            console.error('Error loading states!');
            console.error(error);
          }
        );
      }

      fetchFuelTypes(){
        this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x=>{
        this.fuelTypes=x;
        console.log(this.fuelTypes)
    })
  }

  openDownload(data: Response) {
    let content_type = data.headers.get('Content-type');
    let x_filename = "gen-charges-alloc-to-ht.pdf";
    console.log(content_type)
    saveAs(data.blob(), x_filename);
  }
  
  printReport(){ 
    this.service.printGenWiseAllocToHt(this.stmtMonth,this.stmtYear,this.flowTypeCode,this.fuelTypeCode,this.dispOrgCode,this.serviceNo).subscribe(xs=>{
      this.pr=xs;
      this.openDownload(xs);
    });
  }

  
exportAsXLSX():any {
  this.gen.push({op1:"TOTAL",op2:"",op3:this.totalop3,op4:"",op5:"",op6:"",op7:"",op8:"",op9:"",op10:"",op11:"",op12:this.totalop12,op13:"",op14:""})
    let str = JSON.stringify(this.gen);
    // str = str.replace(/\"op1\":/g, "\"SiNo\":");
    str = str.replace(/\"op1\":/g, "\"GEN NAME\":");
    str = str.replace(/\"op2\":/g, "\"GEN SERVICE NUMBER\":");
    str = str.replace(/\"op3\":/g, "\"MACHINE CAPACITY\":");
    str = str.replace(/\"op4\":/g, "\"FLOW TYPE CODE\":");
    str = str.replace(/\"op5\":/g, "\"TYPE OF SS\":");
    str = str.replace(/\"op6\":/g, "\"IS REC\":");
    str = str.replace(/\"op7\":/g, "\"EDC\":");
    str = str.replace(/\"op8\":/g, "\"HT SC NO\":");
    str = str.replace(/\"op9\":/g, "\"HT SER NAME\":");
    str = str.replace(/\"op10\":/g, "\"CHARGE DESC\":");
    str = str.replace(/\"op11\":/g, "\"CHARGE CODE\":");
    str = str.replace(/\"op12\":/g, "\"TOTAL CHARGE\":");
    str = str.replace(/\"op13\":/g, "\"STMT MONTH\":");
    str = str.replace(/\"op14\":/g, "\"STMT YEAR\":");
  
    this.gen = JSON.parse(str);
    this.gen.forEach(x =>{
      delete x.$$index;
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
  
  
  this.excelService.exportAsExcelFile(this.gen, 'GEN WISE CHARGES ALLOCATION TO HT CONSUMER');
  this.searchResults();
  }

///To allow number only
numberFormat(event){
  this.commonService.numberOnly(event)
}

}