import { Component, OnInit } from "@angular/core";
import { CommonUtils } from "../../../shared/common/common-utils";
import { CommonService } from "../../../shared/common/common.service";
import { ExcelExportService } from "../../../shared/services/excelExport";
import { ReportService } from "../../services/report.service";
import { GenericReportInput } from "../../vo/GenericReportInput";
import { GenericReportOutput } from "../../vo/GenericReportOutput";
import { Criteria } from "../../vo/report";


@Component({
  selector: 'tantransco-generatorInfo-report',
  templateUrl: './tantransco-generatorInfor-report.component.html',
  providers: [CommonUtils]

})
export class TantranscoGeneratorInfo implements OnInit {
  criteria: Criteria;
  serviceNo:string;
  rows: Array<GenericReportOutput>;
  fuelTypeCode: string;
  OrgName: string;
  fuelTypes: any;
  orgList = [];

  months = [];
  xls: any;
  flowTypes = [
    { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
    { "key": "THIRD-PARTY", "name": "THIRD-PARTY" },
    { "key": "STB", "name":"STB"}

  ]
  flowtype:string;
  Usertype: any;
  HasEdc: boolean=false;
  OrgName1: any;




  constructor(private service: ReportService, private excelService: ExcelExportService,
    private commonService: CommonService, private commonUtils: CommonUtils) {
      this.Usertype = CommonUtils.getLoginUserTypeName();


  }

  ngOnInit() {
    this.criteria = new Criteria();
    this.rows = [];
    
    this.fetchOrgList();
    this.fetchFuelTypes();
    if (this.Usertype == "EDC User" || this.Usertype == "DFC User") {
      this.HasEdc = true;
      this.OrgName = CommonUtils.getLoginUserEDC();
    }




  }
  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
        console.log(this.orgList.length)
        for (let i = 0; i < this.orgList.length; i++) {
         
  
          if (this.orgList[i].code == this.OrgName) {
        
            this.OrgName = this.orgList[i].code + "-" + this.orgList[i].name
            this.OrgName1 = this.orgList[i].id;
            
          }
  
        }
      },
      error => {
        console.error('Error loading states!');
      }
    );
    return this.orgList;
  }
  fetchFuelTypes() {
    this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x => {
      this.fuelTypes = x;
      console.log(this.fuelTypes)
    })
  }



  searchResults() {
    let genericRptInput: GenericReportInput = new GenericReportInput();
    genericRptInput.reportName = 'TANTRANSCO-GENERATOR-INFO-REPORT';
    

    if (this.OrgName1 != "" &&
      this.OrgName1 != null) {
      genericRptInput.ip1 = this.OrgName1;
    }

    if (this.fuelTypeCode != "" &&
      this.fuelTypeCode != null){
      genericRptInput.ip2 = this.fuelTypeCode;
    }

    if (this.serviceNo != "" &&
      this.serviceNo != null) {
      genericRptInput.ip3 = this.serviceNo;
    }

    if (this.flowtype != "" &&
      this.flowtype != null) {
      genericRptInput.ip4 = this.flowtype;
    }



    this.service.gettantranscoGeneratorInfoReport(genericRptInput)
      .subscribe(
        items => {
          this.rows = items;

          for (var i =0 ; i<this.rows.length;i++){
            if(this.rows[i].op12 =='IS-CAPTIVE'){
            this.rows[i].op12 = 'CAPTIVE'; 
          }
          if(this.rows[i].op13 =='N'){
            this.rows[i].op13 = 'NON-REC'; 
          }
          if(this.rows[i].op13 =='Y'){
            this.rows[i].op13 = 'REC'; 
          }

        }
        this.xls=this.rows
        });
  }


  exportAsXLSX():any {
    let str = JSON.stringify(this.xls);
    
    str = str.replace(/\"op1\":/g, "\"CIRCLE NAME\":");
    str = str.replace(/\"op2\":/g, "\"SUBSTATION NAME\":");
    str = str.replace(/\"op3\":/g, "\"COMPANY NAME\":");
    str = str.replace(/\"op4\":/g, "\"SERVICE NAME\":");
    str = str.replace(/\"op5\":/g, "\"COMPANY ADDRESS\":");
    str = str.replace(/\"op7\":/g, "\"COMPANY PHONENO\":");
    str = str.replace(/\"op8\":/g, "\"COMPANY EMAILID\":");
    str = str.replace(/\"op9\":/g, "\"GST-NO\":");
    str = str.replace(/\"op10\":/g, "\"PAN-NO\":");
    str = str.replace(/\"op11\":/g, "\"CAPACITY\":");
    str = str.replace(/\"op12\":/g, "\"FLOW TYPE\":");
    str = str.replace(/\"op13\":/g, "\"REC\":");
    str = str.replace(/\"op14\":/g, "\"AGREEMENT DATE\":");
    str = str.replace(/\"op15\":/g, "\"PERIOD OF AGREEMENT IN YEARS\":");
   
    
    
    this.xls = JSON.parse(str);
  
    this.xls.forEach(x =>{
      delete x.$$index;
      delete x.op6;delete x.op15;delete x.op16;delete x.op17;delete x.op18;delete x.op19;delete x.op20;
      delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
      delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
      delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
    });
  
  this.excelService.exportAsExcelFile(this.xls, 'TanTransco Genrator Info Report');
  this.searchResults();
  }

///To allow number only
numberFormat(event){
  this.commonService.numberOnly(event)
}

}
