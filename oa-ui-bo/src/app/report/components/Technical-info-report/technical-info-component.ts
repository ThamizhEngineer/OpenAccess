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
  selector: 'technical-info-report',
  templateUrl: './technical-info-component.html'
//   styleUrls: ['./consolidate-energy-adjustment-report.component.scss']
})
export class TechnicalInfo implements OnInit {
  GenServiceNumber:any="";
  edcList: any;
  FlowTypes: any="";
  uer: any;
  
  

  FlowType=[
        
    { "value": "IS-CAPTIVE", "name": "CAPTIVE" },
    { "value": "THIRD-PARTY", "name":"THIRD-PARTY"},
    { "value": "STB", "name":"STB"}
  

];


  
  dispOrgCode: string="";
 
  fuelTypes: any;
  constructor(private datePipe: DatePipe,private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) {
  }


    ngOnInit(): void {
      this. fetchOrgList();
     
      
       
    }


    fetchOrgList() {
      this.commonService.fetchEDCs().subscribe(
        result => {
          this.edcList = result;
        },
        error => {
          console.error('Error loading states!');
        }
      );
    }
    
    searchResults(){
      let genRptInput:GenericReportInput = new GenericReportInput();
      genRptInput.reportName = 'TECHNICAL INFO REPORT';     

        
          if(this.dispOrgCode != "" && 
          this.dispOrgCode != null)
            genRptInput.ip1 = this.dispOrgCode 
            if(this.GenServiceNumber != "" && 
            this.GenServiceNumber != null)
              genRptInput.ip2 = this.GenServiceNumber
              if(this.FlowTypes != "" && 
          this.FlowTypes != null)
            genRptInput.ip3 = this.FlowTypes  
          
          

          this.service.getAmrdownlode(genRptInput).subscribe(x=>{
            this.uer=x;
            console.log(this.uer)
        })
      }

      
      
      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "technical info report.pdf";
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }

      printReport(){ 
        
        this.service.TechnicalinfoReport(this.dispOrgCode,this.GenServiceNumber,this.FlowTypes).subscribe(xs=>{
          this.openDownload(xs);
        });
    
      }





       

      exportAsXLSX():any {
        let str = JSON.stringify(this.uer);
        
        str = str.replace(/\"op1\":/g, "\"M ORG ID\":");
        str = str.replace(/\"op2\":/g, "\"M ORG NAME\":");
        str = str.replace(/\"op3\":/g, "\"M COMP SERV NUMBER\":");
        str = str.replace(/\"op4\":/g, "\"SELLER COMP NAME\":");
        str = str.replace(/\"op5\":/g, "\"METER NUMBER\":");
        str = str.replace(/\"op6\":/g, "\"FUEL TYPE CODE\":");
        
        
        this.uer = JSON.parse(str);
      
        this.uer.forEach(x =>{
          delete x.$$index;
          delete x.op7;delete x.op8;delete x.op9;delete x.op10;delete x.op11;delete x.op12
          delete x.op13;delete x.op14;delete x.op15;delete x.op16;delete x.op17;delete x.op18;delete x.op19;delete x.op20;
          delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
          delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
          delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
        });
      
      this.excelService.exportAsExcelFile(this.uer, 'Technical Info Report');
      this.searchResults();
      }

///To allow number only
numberFormat(event){
  this.commonService.numberOnly(event)
}
  
  }