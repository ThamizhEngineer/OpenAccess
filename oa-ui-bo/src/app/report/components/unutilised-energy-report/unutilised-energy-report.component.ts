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
  selector: 'unutilised-energy-report',
  templateUrl: './unutilised-energy-report.component.html',
//   styleUrls: ['./consolidate-energy-adjustment-report.component.scss']
})
export class UnutilisedEnergyReportComponent implements OnInit {
  uer: any;
  userTypeName: any;
  stmtYear: string;
  Year= [
          // { "id": "2018", "year": "2017-2018" },
          // { "id": "2019", "year": "2018-2019" },
          { "id": "2020", "year": "2019-2020" },
          { "id": "2021", "year": "2020-2021" }
          // { "id": "2022", "year": "2021-2022" },
          // { "id": "2023", "year": "2022-2023" }
        ]
  edc: any;
  dispOrgCode: any;
  disableEdc: boolean;
  edcList: any;
  totalUnutilisedEneregy:any;
  totalPowerPurchasecostPayable:any;
  resultSet:boolean=false;
  totalop6: any;
  totalop5: any;


    constructor(private datePipe: DatePipe,private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) { 

      this.userTypeName=  CommonUtils.getLoginUserTypeName();
    }

    ngOnInit() {
      // console.log(CommonUtils.getLoginUserEDC());
      this.edc = CommonUtils.getLoginUserEDC();
    if (this.edc != "") {
      // console.log("in edc select")
      this.dispOrgCode = this.edc;
      this.disableEdc = true;
    }
    this. fetchOrgList();
      // this. getAll();
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
        genRptInput.reportName = 'UNUTILISED-BANKING';     
  
          if(this.stmtYear != "" && 
        this.stmtYear != null)
          genRptInput.ip1 = this.stmtYear        
          if(this.dispOrgCode != "" && 
          this.dispOrgCode != null)
            genRptInput.ip2 = this.dispOrgCode 
          this.service.getAllTnerc(genRptInput).subscribe(x=>{
            this.uer=x;
             //console.log(this.uer)
             const op6 = this.uer.reduce((sum, item) => sum + Number(item.op6), 0);
             this.totalop6=op6.toFixed(2)
            const op5 = this.uer.reduce((sum, item) => sum + Number(item.op5), 0);
            this.totalop5=op5.toFixed(2)
             const mr = this.uer.reduce((sum, item) => sum + Number(item.op7), 0);
             this.totalUnutilisedEneregy=mr.toFixed(2)
            const ttp = this.uer.reduce((sum, item) => sum + Number(item.op8), 0);
            this.totalPowerPurchasecostPayable=ttp.toFixed(2)
 
        })
         this.resultSet=true;
      }

      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "Unutilised-banking-report.pdf";
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }
  
      printReport(){ 
        this.service.printUnutilisedEnergyReport(this.stmtYear,this.dispOrgCode).subscribe(xs=>{
          this.openDownload(xs);
        });
      }
      
     exportAsXLSX():any {   
       this.uer.forEach(element => {       
         element.op4=this.datePipe.transform(element.op4,'dd-MM-yyyy');
       });
       this.uer.push({op1:"TOTAL",op2:"",op3:"",op4:"",op5:this.totalop5,op6:this.totalop6,op7:this.totalUnutilisedEneregy,op8:this.totalPowerPurchasecostPayable})
    

      let str = JSON.stringify(this.uer);
      str = str.replace(/\"op1\":/g, "\"EDC\":");
      str = str.replace(/\"op2\":/g, "\"WEG SERVICE NUMBER\":");
      str = str.replace(/\"op3\":/g, "\"GENERATOR NAME\":");
      str = str.replace(/\"op4\":/g, "\"COMISSION DATE\":");
      str = str.replace(/\"op5\":/g, "\"RATE IN (RS)\":");
      str = str.replace(/\"op6\":/g, "\"75% OF PURCHASE RATE\":");
      str = str.replace(/\"op7\":/g, "\"UNUTILISED ENERGY (KWH)\":");
      str = str.replace(/\"op8\":/g, "\"POWER PURCHASE COST PAYABLE (RS)\":");
    
      this.uer = JSON.parse(str);
      this.uer.forEach(x =>{
        delete x.$$index;
        delete x.op9
        delete x.op10
        delete x.op11
        delete x.op12
        delete x.op13
        delete x.op14
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
    
   this.excelService.exportAsExcelFile(this.uer, 'UNUTILISED-BANKING');
   this.searchResults();
  }

    }
    function roundTo(ttp: any, arg1: number): any {
      throw new Error('Function not implemented.');
    }