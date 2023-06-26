import { Component, OnInit, HostBinding, Injectable } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subject } from 'rxjs/Subject';
import { FormsModule } from '@angular/forms';
import { CommonService } from '../../../shared/common/common.service';
import { ReportService } from './../../services/report.service';
import { Criteria } from './../../vo/report';
import { OrgWiseGen } from '../../vo/OrgWiseGeneration';
import { CommonUtils } from '../../../shared/common/common-utils';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';


@Component({
    selector: 'generation-summary-report',
    templateUrl: './generation-summary-report.component.html',
    //styleUrls: [],
    providers: [CommonUtils]
})


export class GenerationSummaryReportComponent implements OnInit {
    searchMonth:string = "";
    searchYear:string = "";
    searchFlowTypeCode:string ="";
    searchFuelTypeCode:string="";
    searchIsRec:string="";
    searchOrgId:string="";
    sumtotalunits:any;
    sumTotalCap:any;
    sumTotalimp:number=0;
    sumTotalExp :number=0;
    sumServCount :number=0;
    orgWiseGen:OrgWiseGen;
    rows: Array<OrgWiseGen>;
    orgList = [];
    years:any[];
    months = [];
    isRecs = [
        { "key": "Y", "name": "REC" },
        { "key": "N", "name": "NON-REC" }
      ]

      flowTypes = [
        { "key": "STB", "name": "SALE-TO-BOARD" },
        { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
        { "key": "THIRD-PARTY", "name": "THIRD-PARTY" }

      ]

      fuelGroup = [
        { "key": "RE", "name": "RE" },
        { "key": "FF", "name": "FF" }
        // { "key": "WIND", "name": "WIND" },
        // { "key": "SOLAR", "name": "SOLAR" },
        // { "key": "COAL", "name": "COAL" },
        // { "key": "BIO-MASS", "name": "BIO-MASS" },
        // { "key": "BAGASSE", "name": "BAGASSE" }
      ]
  edc: any;
  disableEdc: boolean;
  dispFuelTypeGroup: string;
  test: OrgWiseGen[];
    
    constructor(
        private commonService: CommonService,
        private commonUtils: CommonUtils,
        private service: ReportService,
        private router: Router,
        private excelService: ExcelExportService
    ) { 
        this.fetchEDCs();

    }
    ngOnInit() {
      this.orgWiseGen = new OrgWiseGen();
      this.edc = CommonUtils.getLoginUserEDC();
      console.log(this.edc)
      if (this.edc != "") {
        this.searchOrgId = this.edc;
        console.log(this.searchOrgId);
        this.disableEdc = true;
      }
        this.months = this.commonService.fetchMonths();
        this.years = this.commonService.fetchYearList();
        this.searchMonth = this.commonUtils.getPerviousMonth();
        this.searchYear = this.commonUtils.getCurrentYear();
        
    }

    restSearch(){
     this.searchFlowTypeCode="",this.searchFuelTypeCode="",this.searchIsRec="",this.dispFuelTypeGroup="";
    }

    orgWiseGeneration(){
console.log(this.searchOrgId)
            this.service.getOrgWiseGneration(this.searchMonth,this.searchYear,this.searchFlowTypeCode,this.dispFuelTypeGroup,this.searchIsRec,this.searchOrgId).subscribe(
                res => {
                    this.rows=res;
                    if(this.rows.length>0){
                   this.sumTotalCap = this.rows[0].sumMachineCapacity;
                   this.sumTotalimp = this.rows[0].sumTotalImportUnits;
                   this.sumTotalExp = this.rows[0].sumTotalExportUnits;
                   this.sumtotalunits = this.rows[0].sumTotalUnits; 
                   this.sumServCount = this.rows[0].sumserviceCount; 

                   this.orgWiseGen.orgName="TOTAL";
                   this.orgWiseGen.flowTypeCode="-";
                   this.orgWiseGen.dispFuelTypeGroup="-";
                   this.orgWiseGen.isRec="-";
                   this.orgWiseGen.totalMachineCapacity=this.sumTotalCap;
                   this.orgWiseGen.totalImportUnits=this.sumTotalimp;
                   this.orgWiseGen.totalExportUnits=this.sumTotalExp;
                   this.orgWiseGen.totalUnits=this.sumtotalunits;
                   this.orgWiseGen.recCount=this.sumServCount;
                   this.rows.push(Object.assign({},this.orgWiseGen));
                  }  
                },

                error => {
                    console.error('Error loading generation');
                    console.error(error);
                });
        }


openDownload(data: Response) {
  let content_type = data.headers.get('Content-type');
  let x_filename = "org-wise-generation-summary.pdf";
  console.log(content_type)
  saveAs(data.blob(), x_filename);
}
printReport(){
  this.service.printOrgWiseGneration(this.searchMonth,this.searchYear,this.searchFlowTypeCode,this.dispFuelTypeGroup,this.searchIsRec,this.searchOrgId).subscribe(xs=>{
    this.openDownload(xs);
  });
}


        fetchEDCs() {
            this.commonService.fetchEDCs().subscribe(
              result => {
                this.orgList = result;
                console.log(result)
              },
              error => {
                console.error('Error loading orgs!');
                console.error(error);
              });
          }

          exportAsXLSX(): any {

            this.rows.forEach(x =>{
              delete x.$$index;
              delete x.id;
              delete x.companyId;
              delete x.companyName;
              delete x.dispFuelTypeGroup;
              delete x.fuelName;
              delete x.genReportDate;
              delete x.machineCapacity;
              delete x.netGeneration;
              delete x.orgCode;
              delete x.plantClassTypeCode;
              delete x.plantClassTypeDesc;
              delete x.sellerServiceId;
              delete x.sellerServiceId;
              delete x.sellerServiceNumber;
              delete x.voltageCode;
              delete x.voltageName;
              delete x.sumMachineCapacity;
              delete x.sumTotalExportUnits;
              delete x.sumTotalImportUnits;
              delete x.sumTotalUnits;
              delete x.sumserviceCount;
              delete x.totalExportGeneration;
              delete x.totalImportGeneration;
            })
        
            this.excelService.exportAsExcelFile(this.rows, 'Generation Summary Report');
            this.orgWiseGeneration();
          }

}

