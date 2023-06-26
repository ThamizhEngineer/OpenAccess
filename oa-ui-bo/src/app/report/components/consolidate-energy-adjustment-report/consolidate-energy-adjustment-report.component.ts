import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { GenericReportOutput } from '../../vo/GenericReportOutput';


@Component({
  selector: 'consolidate-energy-adjustment-report',
  templateUrl: './consolidate-energy-adjustment-report.component.html',
  styleUrls: ['./consolidate-energy-adjustment-report.component.scss']
})
export class ConsEnergyAdjustedReportComponent implements OnInit {
  cons: any;
  rows: any;
  sample:GenericReportOutput;
  ServiceNo: string;
  year: string;
  totalop3: any;
  totalop4: any;
  totalop5: any;
  totalop6: any;
  totalop7: any;
  totalop8: any;
  totalop9: any;
  totalop10: any;
  totalop11: any;
  totalop12: any;
  totalop13: any;
  totalop14: any;
  totalop15: any;
  totalop16: any;
  totalop17: any;
  totalop18: any;
  totalop19: any;
  totalop20: any;
  totalop21: any;
  totalop22: any;
  totalop23: any;
  totalop24: any;
  totalop25: any;
  totalop26: any;
  totalop27: any;
  totalop28: any;
  totalop29: any;
  totalop30: any;
  totalop31: any;
  totalop32: any;
  totalop33: any;
  totalop34: any;
  



  Year:any= [
    { "id": "2019", "year": "2019-2020" },
    { "id": "2020", "year": "2020-2021" },
    { "id": "2021", "year": "2021-2022" }
  ]
  stmtYear: string;
 

    constructor(private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) { }

    ngOnInit() {
      }

      searchResults(){
        let genRptInput:GenericReportInput = new GenericReportInput();
        genRptInput.reportName = 'CONS-ENERGY-ADJUSTED-ORDER-REPORT';
    
        if(this.ServiceNo != "" && 
        this.ServiceNo != null)
          genRptInput.ip1 = this.ServiceNo
        console.log(genRptInput.ip1)
          if(this.stmtYear != "" && 
        this.stmtYear != null)
          genRptInput.ip2 = this.stmtYear
        console.log(genRptInput.ip2)
          this.service.getAllTnerc(genRptInput).subscribe(x=>{
          this.rows=x;

          for(var i = 0 ;i<this.rows.length;i++){
            if(this.rows[i].op4 == "-"){
        console.log("came in");
        this.rows[i].op4 = "0";

      }
    }
    



          const Op4 = this.rows.reduce((sum, item) => sum + Number(item.op4), 0);
          this.totalop4=Op4.toFixed(2)
          const Op5 = this.rows.reduce((sum, item) => sum + Number(item.op5), 0);
          this.totalop5=Op5.toFixed(2)
          const Op6 = this.rows.reduce((sum, item) => sum + Number(item.op6), 0);
          this.totalop6=Op6.toFixed(2)
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
          const Op13 = this.rows.reduce((sum, item) => sum + Number(item.op13), 0);
          this.totalop13=Op13.toFixed(2)
          const Op14 = this.rows.reduce((sum, item) => sum + Number(item.op14), 0);
          this.totalop14=Op14.toFixed(2)
          const Op15 = this.rows.reduce((sum, item) => sum + Number(item.op15), 0);
          this.totalop15=Op15.toFixed(2)
          const Op16 = this.rows.reduce((sum, item) => sum + Number(item.op16), 0);
          this.totalop16=Op16.toFixed(2)
          const Op17 = this.rows.reduce((sum, item) => sum + Number(item.op17), 0);
          this.totalop17=Op17.toFixed(2)
          const Op18 = this.rows.reduce((sum, item) => sum + Number(item.op18), 0);
          this.totalop18=Op18.toFixed(2)
          const Op19 = this.rows.reduce((sum, item) => sum + Number(item.op19), 0);
          this.totalop19=Op19.toFixed(2)
          const Op20 = this.rows.reduce((sum, item) => sum + Number(item.op20), 0);
          this.totalop20=Op20.toFixed(2)
          const Op21 = this.rows.reduce((sum, item) => sum + Number(item.op21), 0);
          this.totalop21=Op21.toFixed(2)
          const Op22 = this.rows.reduce((sum, item) => sum + Number(item.op22), 0);
          this.totalop22=Op22.toFixed(2)
          const Op23 = this.rows.reduce((sum, item) => sum + Number(item.op23), 0);
          this.totalop23=Op23.toFixed(2)
          const Op24 = this.rows.reduce((sum, item) => sum + Number(item.op24), 0);
          this.totalop24=Op24.toFixed(2)
          const Op25 = this.rows.reduce((sum, item) => sum + Number(item.op25), 0);
          this.totalop25=Op25.toFixed(2)
          const Op26 = this.rows.reduce((sum, item) => sum + Number(item.op26), 0);
          this.totalop26=Op26.toFixed(2)
          const Op27 = this.rows.reduce((sum, item) => sum + Number(item.op27), 0);
          this.totalop27=Op27.toFixed(2)
          const Op28 = this.rows.reduce((sum, item) => sum + Number(item.op28), 0);
          this.totalop28=Op28.toFixed(2)
          const Op29 = this.rows.reduce((sum, item) => sum + Number(item.op29), 0);
          this.totalop29=Op29.toFixed(2)
          const Op30 = this.rows.reduce((sum, item) => sum + Number(item.op30), 0);
          this.totalop30=Op30.toFixed(2)
          const Op31 = this.rows.reduce((sum, item) => sum + Number(item.op31), 0);
          this.totalop31=Op31.toFixed(2)
          const Op32 = this.rows.reduce((sum, item) => sum + Number(item.op32), 0);
          this.totalop32=Op32.toFixed(2)
          const Op33 = this.rows.reduce((sum, item) => sum + Number(item.op33), 0);
          this.totalop33=Op33.toFixed(2)
          const Op34 = this.rows.reduce((sum, item) => sum + Number(item.op34), 0);
          this.totalop34=Op34.toFixed(2)
          

            
            
            
          
            
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
        this.service.printConsEnergyReport(this.ServiceNo,this.stmtYear).subscribe(xs=>{
          this.openDownload(xs);
        });
    
      }

      exportAsXLSX():any {  


        this.rows.push({op1:"TOTAL",op2:"",op3:"",op4:this.totalop4,op5:this.totalop5,op6:this.totalop6,op7:this.totalop7,op8:this.totalop8,op9:this.totalop9,op10:this.totalop10,op11:this.totalop11,op12:this.totalop12,op13:this.totalop13,op14:this.totalop14,op15:this.totalop15,op16:this.totalop16,op17:this.totalop17,op18:this.totalop18,
        op19:this.totalop19,op20:this.totalop20,op21:this.totalop21,op22:this.totalop22,op23:this.totalop23,op24:this.totalop24,op25:this.totalop25,op26:this.totalop26,op27:this.totalop27,
        op28:this.totalop28,op29:this.totalop29,op30:this.totalop30,op31:this.totalop31,op32:this.totalop32,op33:this.totalop33,op34:this.totalop34 })



        let str = JSON.stringify(this.rows);
        str = str.replace(/\"op1\":/g, "\"Month\":");
        str = str.replace(/\"op2\":/g, "\"Year\":");
        str = str.replace(/\"op3\":/g, "\"Consumer Service No\":");
        str = str.replace(/\"op4\":/g, "\"Loss %\":");
  str = str.replace(/\"op5\":/g, "\"GROSSC1\":");
  str = str.replace(/\"op6\":/g, "\"GROSSC2\":");
  str = str.replace(/\"op7\":/g, "\"GROSSC3\":");
  str = str.replace(/\"op8\":/g, "\"GROSSC4\":");
  str = str.replace(/\"op9\":/g, "\"GROSSC5\":");
  str = str.replace(/\"op10\":/g, "\"GROSSS TOTAL\":");
  str = str.replace(/\"op11\":/g, "\"NETC1\":");
  str = str.replace(/\"op12\":/g, "\"NETC2\":");
  str = str.replace(/\"op13\":/g, "\"NETC3\":");
  str = str.replace(/\"op14\":/g, "\"NETC4\":");
  str = str.replace(/\"op15\":/g, "\"NETC5\":");
  str = str.replace(/\"op16\":/g, "\"NET TOTAL\":");
  str = str.replace(/\"op17\":/g, "\"ADJ NETC1\":");
  str = str.replace(/\"op18\":/g, "\"ADJ NETC2\":");
  str = str.replace(/\"op19\":/g, "\"ADJ NETC3\":");
  str = str.replace(/\"op20\":/g, "\"ADJ NETC4\":");
  str = str.replace(/\"op21\":/g, "\"ADJ NETC5\":");
  str = str.replace(/\"op22\":/g, "\"ADJ NETTOTAL\":");
  str = str.replace(/\"op23\":/g, "\"SUR GROSSC1\":");
  str = str.replace(/\"op24\":/g, "\"SUR GROSSC2\":");
  str = str.replace(/\"op25\":/g, "\"SUR GROSSC3\":");
  str = str.replace(/\"op26\":/g, "\"SUR GROSSC4\":");
  str = str.replace(/\"op27\":/g, "\"SUR GROSSC5\":");
  str = str.replace(/\"op28\":/g, "\"SUR GROSSTOTAL\":");
  str = str.replace(/\"op29\":/g, "\"SUR NETC1\":");
  str = str.replace(/\"op30\":/g, "\"SUR NETC2\":");
  str = str.replace(/\"op31\":/g, "\"SUR NETC3\":");
  str = str.replace(/\"op32\":/g, "\"SUR NETC4\":");
  str = str.replace(/\"op33\":/g, "\"SUR NETC5\":");
  str = str.replace(/\"op34\":/g, "\"SUR NETTOTAL\":");

  this.rows = JSON.parse(str);
  this.rows.forEach(x =>{
    delete x.$$index;
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

///To allow number only
numberFormat(event){
  this.commonService.numberOnly(event)
}

    }