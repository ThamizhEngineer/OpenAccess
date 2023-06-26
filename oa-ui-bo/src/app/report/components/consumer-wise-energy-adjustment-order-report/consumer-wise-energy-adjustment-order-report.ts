import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { CompanyService } from '../../../master/company/services/company.service';
import { TradeRelationshipService } from '../../../master/trade-relationship/services/trade-relationship.service';
// import { CompanyService } from '../../../master/company/services/company.service';


@Component({
  selector: 'consumer-wise-energy-adjustment-order-report',
  templateUrl: './consumer-wise-energy-adjustment-order-report.html',
//   styleUrls: ['./generator-wise-generation-report.component.scss']
})
export class ConsumerWiseEnergyAdjustmentOrderReportComponent implements OnInit {
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
    orgName: string;
    serviceNo: string;
  ServiceNumber: any;
  bank: any;
  test: any=[];
  totalGen: any;
  totalAdj: any;
  totalNet: any;
  years=[];

    constructor(private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) { }

    ngOnInit() {
        this.months = this.commonService.fetchMonths();
        this.years = this.commonService.fetchYearList();
        this.fetchEDC();
        // this.ServiceNo();

    }

    fetchEDC(){
        this.commonService.fetchEDCs().subscribe(x=>{
        this.orgList=x;
        console.log(this.orgList)
    })
    }

    // onEdcChange() {
    //   console.log("In edc change");
    //   this.services.getCompanyMeterChangeByEdc(this.searchSellerEndOrgId).subscribe(
    //     _companyServices => {
    //       this.companyServices = _companyServices;
    //       console.log(this.companyServices);
    //     }
    //   )
    // }

  //   ServiceNo(){
  //     this.companyService.getCompanies().subscribe(x=>{
  //     this.ServiceNumber=x;
  //     console.log(this.ServiceNumber)
  // })
  // }

    searchResults(){
        let genRptInput:GenericReportInput = new GenericReportInput();
        genRptInput.reportName = 'CONSUMER-WISE-ENERGY-ADJUSTMENT-ORDER-REPORT';
    
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

          if(this.orgName != "" && 
        this.orgName != null)
          genRptInput.ip5 = this.orgName
        
          if(this.serviceNo != "" && 
          this.serviceNo != null)
            genRptInput.ip6 = this.serviceNo

          console.log(genRptInput)
          this.service.getAllTnerc(genRptInput).subscribe(x=>{
            this.rows=x;

            const tGen = this.rows.reduce((sum, item) => sum + Number(item.op6), 0);
            this.totalGen=tGen;

            const netGen = this.rows.reduce((sum, item) => sum + Number(item.op7), 0);
            this.totalNet=netGen;

            const tAdj = this.rows.reduce((sum, item) => sum + Number(item.op11), 0);
            this.totalAdj=tAdj

        })     
      }


      openDownload(data: Response, fileType: string) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "consumer-wise-energy-adjustment-order-report."+fileType;
        saveAs(data.blob(), x_filename);
      }

      printReport(fileType){ 
        this.service.printConsumerWiseEnergyAdjustmentOrderReport(this.fromMonth,this.toMonth,this.fromYear,this.toYear,this.orgName,this.serviceNo,fileType).subscribe(xs=>{
          this.openDownload(xs,fileType);
        });
    
      }


      exportAsXLSX():any {     

        let str = JSON.stringify(this.rows);
        str = str.replace(/\"op1\":/g, "\"MONTH\":");
        str = str.replace(/\"op2\":/g, "\"YEAR\":");
        str = str.replace(/\"op3\":/g, "\"EDC Name\":");
        str = str.replace(/\"op4\":/g, "\"GENERATOR\":");
        str = str.replace(/\"op5\":/g, "\"COMPANY NAME\":");
        str = str.replace(/\"op6\":/g, "\"TOTAL GENERATION\":");
        str = str.replace(/\"op7\":/g, "\"EDC\":");
        str = str.replace(/\"op8\":/g, "\"SERVICE NUMBER\":");
        str = str.replace(/\"op9\":/g, "\"CONSUMER NAME\":");
        str = str.replace(/\"op10\":/g, "\"ADJUSTED UNITS\":");
        this.rows = JSON.parse(str);
//         console.log(this.rows)
//         this.rows.push(this.totalGen);
//         this.rows.push(this.totalAdj);
// console.log(this.rows)
        
     this.excelService.exportAsExcelFile(this.rows, 'CONSUMER-WISE-ENERGY-ADJUSTMENT-ORDER-REPORT');
     this.searchResults();
    }

///To allow number only
numberFormat(event){
  this.commonService.numberOnly(event)
}

}