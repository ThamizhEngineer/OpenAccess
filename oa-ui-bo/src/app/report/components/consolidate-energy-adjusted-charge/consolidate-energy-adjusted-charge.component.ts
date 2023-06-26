import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { CommonUtils } from '../../../shared/common/common-utils';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';


@Component({
  selector: 'consolidate-energy-adjusted-charge',
  templateUrl: './consolidate-energy-adjusted-charge.component.html',
  styleUrls: ['./consolidate-energy-adjusted-charge.component.scss']
})
export class ConsEnergyAdjustedChargeComponent implements OnInit {
  cons: any=[];
  orgList = [];
  disableEdc: boolean = false;
  edc: any;
  months=[];
  years=[];
  dispOrgCode: string;
  stmtMonth: string;
  stmtYear: string;
  dispServiceNumber: string;
  slNo: any;
  newOb: any;
  pr: any;

    constructor(private service:ReportService, private commonService:CommonService, private excelService: ExcelExportService) { }

    ngOnInit() {

      this.fetchEDC();
      this.months = this.commonService.fetchMonths();
      this.years = this.commonService.fetchYearList();
      this.edc = CommonUtils.getLoginUserEDC();
      if (this.edc != "") {
      console.log("in edc select")
      this.dispOrgCode = this.edc;
      this.disableEdc = true;
    }
      }

      searchResults(){
        let genRptInput:GenericReportInput = new GenericReportInput();
        genRptInput.reportName = 'CONSOLIDATE-ENERGY-ADJUSTED-CHARGES-REPORT';
    
        if(this.dispOrgCode != "" && 
        this.dispOrgCode != null)
          genRptInput.ip1 = this.dispOrgCode

          if(this.stmtMonth != "" && 
        this.stmtMonth != null)
          genRptInput.ip2 = this.stmtMonth

          if(this.stmtYear != "" && 
        this.stmtYear != null)
          genRptInput.ip3 = this.stmtYear

          if(this.dispServiceNumber != "" && 
        this.dispServiceNumber != null)
          genRptInput.ip4 = this.dispServiceNumber
        
          console.log(genRptInput)

          this.service.getAllTnerc(genRptInput).subscribe(x=>{
            this.cons=x;
            console.log(genRptInput)
            console.log(this.cons)
        })
  
        console.log(genRptInput.reportName);
        console.log(genRptInput.ip1);
       
      }
  
      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "consolidate-energy-adjustment-report.pdf";
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }
  
      printReport(){ 
        this.service.printConsEnergyCharge(this.dispOrgCode,this.stmtMonth,this.stmtYear,this.dispServiceNumber).subscribe(xs=>{
          this.pr=xs;
          this.openDownload(xs);
        });
      }

      exportAsXLSX():any {
          let str = JSON.stringify(this.cons);
          str = str.replace(/\"op2\":/g, "\"Month\":");
          str = str.replace(/\"op3\":/g, "\"Year\":");
          str = str.replace(/\"op4\":/g, "\"Edc Name\":");
          str = str.replace(/\"op5\":/g, "\"Service No\":");
          str = str.replace(/\"op6\":/g, "\"Generator Name\":");
          str = str.replace(/\"op7\":/g, "\"Fuel Type\":");
          str = str.replace(/\"op8\":/g, "\"Flow Type\":");
          str = str.replace(/\"op9\":/g, "\"REC\":");
          str = str.replace(/\"op10\":/g, "\"M.F\":");
          str = str.replace(/\"op11\":/g, "\"Tariff\":");
          str = str.replace(/\"op12\":/g, "\"SC No\":");
          str = str.replace(/\"op13\":/g, "\"Name\":");
          str = str.replace(/\"op14\":/g, "\"O And M Charges\":");
          str = str.replace(/\"op15\":/g, "\"Transmission Charges\":");
          str = str.replace(/\"op16\":/g, "\"System Operation Charges\":");
          str = str.replace(/\"op17\":/g, "\"RKVAH Penalty\":");
          str = str.replace(/\"op18\":/g, "\"Negative Energy Charges\":");
          str = str.replace(/\"op19\":/g, "\"Scheduling Charges\":");
          str = str.replace(/\"op20\":/g, "\"Meter Reading Charges\":");
          str = str.replace(/\"op21\":/g, "\"Other Charges\":");
          str = str.replace(/\"op22\":/g, "\"Total Charges\":");

          this.cons = JSON.parse(str);
    
          this.cons.forEach(x =>{
            delete x.$$index;
            delete x.op1;
            delete x.op9;delete x.op10;delete x.op11;delete x.op2;
            delete x.op12;delete x.op13;delete x.op14;delete x.op15;delete x.op16;delete x.op17;
            delete x.op18;delete x.op19;delete x.op20;
            delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
            delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
            delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
          });
        
      
       this.excelService.exportAsExcelFile(this.cons, 'Cons-Energy-Adjusted-Charges-report');
       this.searchResults();
      }

      fetchEDC(){
        this.commonService.fetchEDCs()
        .subscribe(
          edcs => {this.orgList = edcs;},
          error => {
            console.log("Error loading EDCs");
            console.log(error);
          }
        );
      }
      numberFormat(event){
      this.commonService.numberOnly(event);
      }
    }