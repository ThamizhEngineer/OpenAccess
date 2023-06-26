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
  selector: 'amr-downlode-component',
  templateUrl: './amr-downlode-status-report.html'
//   styleUrls: ['./consolidate-energy-adjustment-report.component.scss']
})
export class AmrDownlodeComponent implements OnInit {
  Year= [
     { "id": "2018", "year": "2018" },
    { "id": "2019", "year": "2019" },
     { "id": "2020", "year": "2020" },
    { "id": "2021", "year": "2021" },
     { "id": "2022", "year": "2022" },
     { "id": "2023", "year": "2023" }
  ]

  ReadingType=[
    { "id": "01", "readingtype": "AMR" },
    {"id": "02", "readingtype": "MANUAL" } 
  ]
  edcList: any;
  months: { value: string; name: string; }[];
  uer: any;
  fuelTypeCode:any
  readingType:string
  
  readYear: any;
  dispOrgCode: string;
  readMonth: string;
  fuelTypes: any;
  constructor(private datePipe: DatePipe,private service:ReportService, private commonService:CommonService,private excelService: ExcelExportService) {
  }


    ngOnInit(): void {
      this. fetchOrgList();
      this.fetchMonths();
      this.fetchFuelTypes();
       
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
    fetchMonths() {
      this.months = this.commonService.fetchMonths();
    }
    searchResults(){
      let genRptInput:GenericReportInput = new GenericReportInput();
      genRptInput.reportName = 'AMR DOWNLODE STATUS REPORT';     

        if(this.readYear != "" && 
        this.readYear != null)
          genRptInput.ip2 = this.readYear  

          if(this.dispOrgCode != "" && 
          this.dispOrgCode != null)
            genRptInput.ip1 = this.dispOrgCode

            if(this.readMonth != "" && 
          this.readMonth != null)
            genRptInput.ip3 = this.readMonth 

            if(this.fuelTypeCode != "" && 
          this.fuelTypeCode != null)
            genRptInput.ip4 = this.fuelTypeCode 

            if(this.readingType != "" && 
          this.readingType != null)
            genRptInput.ip5 = this.readingType 

          this.service.getAmrdownlode(genRptInput).subscribe(x=>{
            this.uer=x;
            console.log(this.uer)
        })
      }

      fetchFuelTypes(){
        this.commonService.fetchCodes("FUEL_TYPE_CODE").subscribe(x=>{
        this.fuelTypes=x;
        console.log(this.fuelTypes)
      })
      }
      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "Amr Downlode Status report.pdf";
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }



      printReport(){ 
        
        this.service.printAmrReport(this.dispOrgCode,this.readYear,this.readMonth,this.fuelTypeCode,this.readingType).subscribe(xs=>{
          this.openDownload(xs);
        });
    
      }


      exportAsXLSX():any {
        let str = JSON.stringify(this.uer);
        str = str.replace(/\"op1\":/g, "\"M ORG ID\":");
        str = str.replace(/\"op2\":/g, "\"M ORG NAME\":");
        str = str.replace(/\"op3\":/g, "\"M COMP SERV NUMBER\":");
        str = str.replace(/\"op4\":/g, "\"METER NUMBER\":");
        str = str.replace(/\"op5\":/g, "\"FUEL TYPE CODE\":");
        str = str.replace(/\"op6\":/g, "\"READING_MONTH\":");
        str = str.replace(/\"op7\":/g, "\"READING YEAR\":");
        str = str.replace(/\"op8\":/g, "\"SYS DT\":");
        str = str.replace(/\"op9\":/g, "\"DOWNLOAD STATUS\":");
        
        this.uer = JSON.parse(str);
      
        this.uer.forEach(x =>{
          delete x.$$index;
          delete x.op10;delete x.op11;delete x.op12
          delete x.op13;delete x.op14;delete x.op15;delete x.op16;delete x.op17;delete x.op18;delete x.op19;delete x.op20;
          delete x.op21;delete x.op22;delete x.op23;delete x.op24;delete x.op25;delete x.op26;delete x.op27;delete x.op28;
          delete x.op29;delete x.op30;delete x.op31;delete x.op32;delete x.op33;delete x.op34;delete x.op35;delete x.op36;
          delete x.op37;delete x.op38;delete x.op39;delete x.op40;delete x.op41;
        });
      
      this.excelService.exportAsExcelFile(this.uer, 'AMR DOWNLODE STATUS REPORT');
      this.searchResults();
      }



  
  }