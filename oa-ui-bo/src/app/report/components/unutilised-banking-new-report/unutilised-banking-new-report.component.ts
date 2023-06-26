import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DatePipe, DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { CommonUtils } from '../../../shared/common/common-utils';


@Component({
  selector: 'unutilised-banking-new-report',
  templateUrl: './unutilised-banking-new-report.component.html',
})
export class UnutilisedBankingNewReportComponent implements OnInit {
    edcList: any;
    dispOrgCode: string;
    stmtYear: string;
    unutilised: any;
    pr: any;
    Year = [
      { "key": "2019", "name": "2018-2019" },
      { "key": "2020", "name": "2019-2020" },
      { "key": "2021", "name": "2020-2021" },
      { "key": "2022", "name": "2021-2022" },
      { "key": "2023", "name": "2022-2023" }
    ]
  edc: any;
  totalop5: any;
  totalop6: any;
  totalop7: any;
  totalop8: any;
  totalop9: any;
  totalop10: any;
  totalop11: any;
  totalop12: any;
  totalop13: any;
disableEdc: boolean = false;
    constructor(private datePipe: DatePipe,private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) { 

    }

    ngOnInit() {
      this.edc = CommonUtils.getLoginUserEDC();
    if (this.edc != "") {
      this.dispOrgCode = this.edc;
      this.disableEdc = true;
    }
        this.fetchOrgList();
    }

    fetchOrgList() {
        this.commonService.fetchEDCs().subscribe(
          result => {
            this.edcList   = result;
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
        if(this.stmtYear=="2020"){
        genRptInput.reportName = 'UNUTILISED-BANKING-MAR-2020';     
        }else{
            genRptInput.reportName = 'UNUTILISED-BANKING-MAR-NEW';
        }
        if(this.dispOrgCode != "" && 
        this.dispOrgCode != null)
          genRptInput.ip1 = this.dispOrgCode  
  
          if(this.stmtYear != "" && 
        this.stmtYear != null)
          genRptInput.ip2 = this.stmtYear      
        
          console.log(genRptInput)
  
          this.service.getAllTnerc(genRptInput).subscribe(x=>{
            this.unutilised=x;
            console.log(genRptInput)
            console.log(this.unutilised)
            const Op5 = this.unutilised.reduce((sum, item) => sum + Number(item.op5), 0);
         this.totalop5=Op5.toFixed(2)
         const Op6 = this.unutilised.reduce((sum, item) => sum + Number(item.op6), 0);
         this.totalop6=Op6.toFixed(2)
         const Op7 = this.unutilised.reduce((sum, item) => sum + Number(item.op7), 0);
         this.totalop7=Op7.toFixed(2)
         const Op8 = this.unutilised.reduce((sum, item) => sum + Number(item.op8), 0);
         this.totalop8=Op8.toFixed(2)
         const Op9 = this.unutilised.reduce((sum, item) => sum + Number(item.op9), 0);
         this.totalop9=Op9.toFixed(2)
         const Op10 = this.unutilised.reduce((sum, item) => sum + Number(item.op10), 0);
         this.totalop10=Op10.toFixed(2)
         const Op11 = this.unutilised.reduce((sum, item) => sum + Number(item.op11), 0);
         this.totalop11=Op11.toFixed(2)
         const Op12 = this.unutilised.reduce((sum, item) => sum + Number(item.op12), 0);
         this.totalop12=Op12.toFixed(2)
         const Op13 = this.unutilised.reduce((sum, item) => sum + Number(item.op13), 0);
         this.totalop13=Op13.toFixed(2)

        })
      }

      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename
        if(this.stmtYear=="2020"){
        x_filename = "unutilised-banking-mar-2020.pdf";
        }else{
        x_filename = "unutilised-banking-mar-new.pdf";
        }
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }
      
      printReport(){ 
        if(this.stmtYear=="2020"){
        this.service.UnutilisedBankingMar2020(this.dispOrgCode,this.stmtYear).subscribe(xs=>{
          this.pr=xs;
          this.openDownload(xs);
        });
      }else{
        this.service.unutilisedBankingMarNew(this.dispOrgCode,this.stmtYear).subscribe(xs=>{
          this.pr=xs;
          this.openDownload(xs);
        });
      }
      }

      exportAsXLSX():any {
        this.unutilised.forEach(element => {       
          element.op4=this.datePipe.transform(element.op4,'dd-MM-yyyy');
        });
        this.unutilised.push({op1:"TOTAL",op2:"",op3:"",op4:"",op5:this.totalop5,op6:this.totalop6,op7:this.totalop7,op8:this.totalop8,op9:this.totalop9,op10:this.totalop10,op11:this.totalop11,op12:this.totalop12,op13:this.totalop13})

 
        let str = JSON.stringify(this.unutilised);
        str = str.replace(/\"op1\":/g, "\"EDC\":");
        str = str.replace(/\"op2\":/g, "\"WEG SERVICE NUMBER\":");
        str = str.replace(/\"op3\":/g, "\"GENERATOR NAME\":");
        str = str.replace(/\"op4\":/g, "\"COMISSION DATE\":");
        str = str.replace(/\"op5\":/g, "\"UNUTILISED C1\":");
        str = str.replace(/\"op6\":/g, "\"UNUTILISED C2\":");
        str = str.replace(/\"op7\":/g, "\"UNUTILISED C3\":");
        str = str.replace(/\"op8\":/g, "\"UNUTILISED C4\":");  
        str = str.replace(/\"op9\":/g, "\"UNUTILISED C5\":"); 
        str = str.replace(/\"op10\":/g, "\"UNUTILISED BANKING C24\":"); 
        str = str.replace(/\"op11\":/g, "\"RATE\":"); 
        str = str.replace(/\"op12\":/g, "\"75% OF PURCHASE TARIFF\":"); 
        str = str.replace(/\"op13\":/g, "\"AMOUNT IN RUPEES\":");   
        str = str.replace(/\"op14\":/g, "\"FLOW TYPE CODE\":"); 
        this.unutilised = JSON.parse(str);
        this.unutilised.forEach(x =>{
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
    
      
      this.excelService.exportAsExcelFile(this.unutilised, 'UNUTILISED-BANKING');
      this.searchResults();
      }
}