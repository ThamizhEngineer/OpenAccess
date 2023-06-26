import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DatePipe, DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { CommonUtils } from '../../../shared/common/common-utils';
import { DateAdapter } from '@angular/material/core';

@Component({
  selector: 'tnerc-banking-report09-2022-component',
  templateUrl: './tnerc-banking-report09-2022.component.html'
//   styleUrls: ['./consolidate-energy-adjustment-report.component.scss']
})
export class TnercBankingReport092022 implements OnInit {
    edcList: any;
    Usertype:string;
    disableEdc:boolean;
    OrgName:string;
    readYear:string;
    readMonth:string;
    dispOrgCode:string;
    Year= [
      { "id": "2018", "year": "2018" },
     { "id": "2019", "year": "2019" },
      { "id": "2020", "year": "2020" },
     { "id": "2021", "year": "2021" },
      { "id": "2022", "year": "2022" },
      { "id": "2023", "year": "2023" }
   ]
   months: { value: string; name: string; }[];
 
    uer: any;
    constructor(private datePipe: DatePipe,private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService,
      private dateAdapter: DateAdapter<Date>) {
        this.Usertype = CommonUtils.getLoginUserTypeName();
        this.dateAdapter.setLocale('en-GB');
    }
  
  
      ngOnInit(): void {
        this.fetchOrgList();
        if (this.Usertype == "EDC User" || this.Usertype == "DFC User") {
            this.disableEdc = true;
            this.OrgName = CommonUtils.getLoginUserEDC();
          }
          this.fetchMonths();
      }
      fetchOrgList() {
        this.commonService.fetchEDCs().subscribe(
            result => {
              this.edcList = result;
              console.log(this.edcList.length)
              for (let i = 0; i < this.edcList.length; i++) {
               
        
                if (this.edcList[i].code == this.OrgName) {
              
                  this.dispOrgCode = this.edcList[i].code + "-" + this.edcList[i].name;
                  this.edcList = this.edcList[i].id;
                  
                }
        
              }
            },
            error => {
              console.error('Error loading states!');
            }
          );
          return this.edcList;
        }
        fetchMonths() {
          this.months = this.commonService.fetchMonths();
        }

        searchResults(){
            let genRptInput:GenericReportInput = new GenericReportInput();
            genRptInput.reportName = 'TNERC-BANKING-REPORT09-2022';     
      
      
          if (this.dispOrgCode != "" &&
            this.dispOrgCode != null)
            genRptInput.ip1 = this.dispOrgCode

          if (this.readYear != "" &&
            this.readYear != null)
            genRptInput.ip2 = this.readYear


          if (this.readMonth != "" &&
            this.readMonth != null)
            genRptInput.ip3 = this.readMonth 
                  
      
                this.service.printtnercreport092022(genRptInput).subscribe(x=>{
                  this.uer=x;
                 
              })
            }
        
            openDownload(data: Response) {
                let content_type = data.headers.get('Content-type');
                let x_filename = "Banking Tod Report.pdf";
                console.log(content_type)
                saveAs(data.blob(), x_filename);
              }
        
        
        
              printReport(){ 
                
                this.service.printtnercbanking092022Report(this.dispOrgCode).subscribe(xs=>{
                  this.openDownload(xs);
                });
            
              }
         
              exportAsXLSX():any {
                let str = JSON.stringify(this.uer);
                str = str.replace(/\"op1\":/g, "\"SERVICE NO\":");
                str = str.replace(/\"op2\":/g, "\"COMPANY NAME\":");
                str = str.replace(/\"op3\":/g, "\"COMMISSIONED DATE\":");
                str = str.replace(/\"op4\":/g, "\"FLOW TYPE\":");
                str = str.replace(/\"op5\":/g, "\"BANKING-310822-C1\":");
                str = str.replace(/\"op6\":/g, "\"BANKING-310822-C2\":");
                str = str.replace(/\"op7\":/g, "\"BANKING-310822-C3\":");
                str = str.replace(/\"op8\":/g, "\"BANKING-310822-C4\":");
                str = str.replace(/\"op9\":/g, "\"BANKING-310822-C5\":");
                str = str.replace(/\"op10\":/g, "\"BANKING-310822-TOTAL\":");
                str = str.replace(/\"op11\":/g, "\"BANKINGREARRANGE-310822-C1\":");
                str = str.replace(/\"op12\":/g, "\"BANKINGREARRANGE-310822-C2\":");
                str = str.replace(/\"op13\":/g, "\"BANKINGREARRANGE-310822-C3\":");
                str = str.replace(/\"op14\":/g, "\"BANKINGREARRANGE-310822-C4\":");
                str = str.replace(/\"op15\":/g, "\"BANKINGREARRANGE-310822-C5\":");
                str = str.replace(/\"op16\":/g, "\"BANKINGREARRANGE-310822-TOTAL\":");
                str = str.replace(/\"op17\":/g, "\"BANKING REMARKS\":");
                
                
                this.uer = JSON.parse(str);
              
                this.uer.forEach(x =>{
                  delete x.$$index;
                  delete x.op18;delete x.op19;delete x.op20;
                  delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
                  delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
                  delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
                });
              
              this.excelService.exportAsExcelFile(this.uer, 'BANKING-TOD-REPORT');
              this.searchResults();
              }






}