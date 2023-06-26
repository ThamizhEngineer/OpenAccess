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
  selector: 'generator-summary-report',
  templateUrl: './generator-summary-report.component.html',
})
export class GeneratorSummaryComponent implements OnInit {
    fuelTypeCode: string;
    dispOrgCode: string;
    rows: any;
    fuelTypes: any;
    edcList: any;
  captiveRec: any;
  captiveNonrec: any;
  thirdPartyRec: any;
  thirdPartyNon: any;
  stbRec: any;
  stbNon: any;
  edc: any;
  disableEdc: boolean;


    constructor(private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) { 
    }

    ngOnInit() {
      console.log(CommonUtils.getLoginUserEDC());
      this.edc = CommonUtils.getLoginUserEDC();
    if (this.edc != "") {
      console.log("in edc select")
      this.dispOrgCode = this.edc;
      this.disableEdc = true;
    }
this.fetchFuelTypes();
this.fetchOrgList();
    }

    fetchFuelTypes(){
        this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x=>{
        this.fuelTypes=x;
        console.log(this.fuelTypes)
    })
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


    searchResults(){
        let genRptInput:GenericReportInput = new GenericReportInput();
        genRptInput.reportName = 'GENERATOR-SUMMARY';
    
        if(this.fuelTypeCode != "" && 
        this.fuelTypeCode != null)
          genRptInput.ip1 = this.fuelTypeCode
    
          if(this.dispOrgCode != "" && 
        this.dispOrgCode != null)
          genRptInput.ip2 = this.dispOrgCode
    
          console.log(this.fuelTypeCode,this.dispOrgCode)
          this.service.getAllTnerc(genRptInput).subscribe(x=>{
            this.rows=x;
            console.log(this.rows)
            const cr = this.rows.reduce((sum, item) => sum + Number(item.op7), 0);
            this.captiveRec=cr;
            console.log(this.captiveRec)
    
            const cnr = this.rows.reduce((sum, item) => sum + Number(item.op9), 0);
            this.captiveNonrec=cnr
    
            const tr = this.rows.reduce((sum, item) => sum + Number(item.op12), 0);
            this.thirdPartyRec=tr
    
            const tn = this.rows.reduce((sum, item) => sum + Number(item.op14), 0);
            this.thirdPartyNon=tn
    
            const sr = this.rows.reduce((sum, item) => sum + Number(item.op17), 0);
            this.stbRec=sr

            const sn = this.rows.reduce((sum, item) => sum + Number(item.op19), 0);
            this.stbNon=sn
        })     
      }

      exportAsXLSX():any {
        let str = JSON.stringify(this.rows);
        str = str.replace(/\"op1\":/g, "\"EDC NAME\":");
        str = str.replace(/\"op2\":/g, "\"EDC\":");
        str = str.replace(/\"op3\":/g, "\"FUEL TYPE\":");
        str = str.replace(/\"op4\":/g, "\"SERVICE NUMBER\":");
        str = str.replace(/\"op5\":/g, "\"CAPTIVE\":");
        str = str.replace(/\"op6\":/g, "\"CAPTIVE REC\":");
        str = str.replace(/\"op7\":/g, "\"CAPTIVE REC COUNT\":");
        str = str.replace(/\"op8\":/g, "\"CAPTIVE NON REC\":");
        str = str.replace(/\"op9\":/g, "\"CAPTIVE NON REC COUNT\":");
        str = str.replace(/\"op10\":/g, "\"THIRD PARTY\":");
        str = str.replace(/\"op11\":/g, "\"THIRD PARTY REC\":");
        str = str.replace(/\"op12\":/g, "\"THIRD PARTY REC COUNT\":");
        str = str.replace(/\"op13\":/g, "\"THIRD PARTY NON REC\":");
        str = str.replace(/\"op14\":/g, "\"THIRD PARTY NON REC COUNT\":");
        str = str.replace(/\"op15\":/g, "\"STB\":");
        str = str.replace(/\"op16\":/g, "\"STB REC\":");
        str = str.replace(/\"op17\":/g, "\"STB REC COUNT\":");
        str = str.replace(/\"op18\":/g, "\"STB NON REC\":");
        str = str.replace(/\"op19\":/g, "\"STB NON REC COUNT\":");
        str = str.replace(/\"op20\":/g, "\"Status\":");
        this.rows = JSON.parse(str);
      
      
      this.excelService.exportAsExcelFile(this.rows, 'Generator summary report');
      this.searchResults();
      }
}