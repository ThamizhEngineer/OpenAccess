import { Component, HostListener, ViewChild } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { saveAs } from 'file-saver';
import { Directive, ElementRef, AfterViewInit  } from '@angular/core';
import { FeederLossListService } from "../service/feeder-loss-list.service";
import { CommonUtils } from "../../../shared/common/common-utils";
import { CommonService } from "../../../shared/common/common.service";
import { ExcelExportService } from "../../../shared/services/excelExport";


@Component({
  selector: 'feeder-loss-list',
  templateUrl: './feeder-loss-list.component.html',
  providers: [FeederLossListService,ExcelExportService]
})


export class FeederLossListComponent { 

  datasource:any;
  dispOrgCode:any;
  substationName:any;
  bulkMeterReading:string="";
  orgList:any;
  subsationById:any;
  rows:any= [];
  months = [];
  statementMonth: string = "";
  statementYear: string = "";
  orgName: string = "";
  ssName: any = "";
  feederName: string = "";
  searchssId: string = "";
  searchfeederId: string = "";
  message: string = "";
  totalexportunits: any = "";
  totalfeederexportunits: any = "";
  lospercentage: any;
  feedermeterno: String = "";
  ExportunitDifference: any = "";
  fuelType: String = "SOLAR";
  success: any;
  error: boolean = false;
  showEditmode: boolean = false;
  hidetotal: boolean = true;
  show_edit_btn: boolean = true;
  show_edit_btn1: boolean = true;
  show_save_btn: boolean = false;
  enableIp: boolean=false;
  selectedValue: any;
  searchTxt: any;
  error_msg: boolean = false;
  constructor(private feederlinelossservice: FeederLossListService,
    private route: ActivatedRoute,
    private router: Router,private excelService: ExcelExportService,
    private commonUtils: CommonUtils,
    private commonService: CommonService) {

  }
 
  ngOnInit() {

    this.fetchOrgList();
    this.months = this.commonService.fetchMonths();
    this.statementMonth = this.commonUtils.getPerviousMonth();
    this.statementYear = (this.statementMonth == "12") ? this.commonUtils.getPreviousYear() : this.commonUtils.getCurrentYear();
   
  }

  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
        console.log(this.orgList)
      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }

    generateResult(){
      
        this.feederlinelossservice.getDataVcompany(this.dispOrgCode,this.statementMonth,this.statementYear).subscribe(x=>{
          this.rows=x;
         console.log(this.rows);    

         for(let i=0;i<this.rows.length;i++){
          if(this.rows[i].subbulkreading!=null){
            this.rows[i].exportDifference=this.rows[i].totalExportUnits-this.rows[i].subbulkreading;
            const loss =(this.rows[i].exportDifference/this.rows[i].totalExportUnits)*100;
            this.rows[i].lossUnits=loss.toFixed(3);      
          }
          else{
          this.rows[i].exportDifference=this.rows[i].totalExportUnits-this.rows[i].bulkMeterReading;
          if(this.rows[i].exportDifference!="0"){
          const loss =(this.rows[i].exportDifference/this.rows[i].totalExportUnits)*100;
         this.rows[i].lossUnits=loss.toFixed(3);
        }
        else{
          this.rows[i].lossUnits="0";
        }
      }
         }
        })
      
    }
 
    save(feederId:string,feederName:string,substationName:string, orgId:string,
      orgName:string,substationId:string,totalExportUnits:string,bulkentry:string,lossUnits:string){
   
    var send_data = [
      {
          "orgId": orgId,
          "orgName": orgName,
          "substationId": substationId,
          "substationName": substationName,
          "feederId": feederId,
          "feederName": feederName,
          "lossPercent": lossUnits,
          "month": this.statementMonth,
          "year": this.statementYear,
          "bulkMeterReading": bulkentry,
          "totalAllWegs": totalExportUnits,
          "fuelType": this.fuelType
      }
  ]
if(bulkentry){
  this.feederlinelossservice.savesolar_data(send_data).subscribe(
      res => {
          console.log(res);
          if (res.status == 200) {
              alert("Line loss saved sucessfully")
          }
      }
  )
}
    }


    calculateSum(row: any) {
      const sub = row.totalExportUnits - row.bulkentry;
      row.exportDifference=sub;
      
      const loss=(row.exportDifference/ row.totalExportUnits)*100;
      row.lossUnits = loss.toFixed(3);
    }
    enableBulk(row:any){
      console.log("edit in Input");
      row.enableIp="EnableSave";
      console.log(row.enableIp);
      
    }
    reload(){
      this.generateResult();
    }
      ///To allow number only
  numberFormat(event){
    this.commonService.numberOnly(event)
}


exportAsXLSX():any {

  let str = JSON.stringify(this.rows);

  str = str.replace(/\"orgId\":/g, "\"EDC ID\":");
  str = str.replace(/\"substationName\":/g, "\"SUBSTATION NAME\":");
  str = str.replace(/\"feederName\":/g, "\"FEEDER NAME\":");
  str = str.replace(/\"totalServices\":/g, "\"TOTAL SERVICES\":");
  str = str.replace(/\"billedService\":/g, "\"BILLED SERVICES\":");  
  str = str.replace(/\"totalExportUnits\":/g, "\"TOTAL EXPORT UNITS\":");
  str = str.replace(/\"subbulkreading\":/g, "\"BULK METER READING\":");
  str = str.replace(/\"exportDifference\":/g, "\"EXPORT DIFFERENCE\":");
  str = str.replace(/\"lossUnits\":/g, "\"LOSS PERCENT\":");
  str = str.replace(/\"meterNo\":/g, "\"FEEDER END METER NO\":");

  this.rows = JSON.parse(str);
  this.rows.forEach(x =>{
    delete x.$$index;
    delete x.orgName;delete x.feederId;delete x.subbulkreading;delete x.substationId;delete x.bulkMeterReading;
  });

  this.excelService.exportAsExcelFile(this.rows,'Solar Feeder Line Loss');
  this.generateResult();
}


openDownload(data: Response) {
  let content_type = data.headers.get('Content-type');
  let x_filename = "SOLAR FEEDER LINE LOSS.pdf";
  console.log(content_type)
  saveAs(data.blob(), x_filename);
}

printSolarReport() {

  this.feederlinelossservice.getPrintData(this.dispOrgCode,this.statementMonth,this.statementYear).subscribe(xs => {
    this.openDownload(xs);
  });

}

}