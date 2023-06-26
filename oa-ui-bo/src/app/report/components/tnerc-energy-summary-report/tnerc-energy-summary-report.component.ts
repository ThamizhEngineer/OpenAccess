import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';


@Component({
  selector: 'tnerc-energy-summary-report',
  templateUrl: './tnerc-energy-summary-report.component.html',
  styleUrls: ['./tnerc-energy-summary-report.component.scss']
})
export class TnercEnergySummaryReportComponent implements OnInit {
    tnerc: any;
  year: string;
  Year = [
    { "key": "2018", "name": "2018-2019" },
    { "key": "2019", "name": "2019-2020" },
    { "key": "2020", "name": "2020-2021" },
    { "key": "2021", "name": "2021-2022" },
    { "key": "2022", "name": "2022-2023" },
    { "key": "2023", "name": "2023-2024" },
    { "key": "2024", "name": "2024-2025" }
    
  ]
 
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

    constructor(private service:ReportService, private commonService:CommonService) { }

  
    ngOnInit() {
      }

     searchResults(){
      let genRptInput:GenericReportInput = new GenericReportInput();
      genRptInput.reportName = 'TNERC-ENERGY-SUMMARY-REPORT';
  
      if(this.year != "" && 
      this.year != null)
        genRptInput.ip1 = this.year
      
        this.service.getAllTnerc(genRptInput).subscribe(x=>{
          this.tnerc=x;
          console.log(this.tnerc)
          const Op3 = this.tnerc.reduce((sum, item) => sum + Number(item.op3), 0);
          this.totalop3=Op3.toFixed(2)
         const Op4 = this.tnerc.reduce((sum, item) => sum + Number(item.op4), 0);
         this.totalop4=Op4.toFixed(2)
         const Op5 = this.tnerc.reduce((sum, item) => sum + Number(item.op5), 0);
         this.totalop5=Op5.toFixed(2)
         const Op6 = this.tnerc.reduce((sum, item) => sum + Number(item.op6), 0);
         this.totalop6=Op6.toFixed(2)
         const Op7 = this.tnerc.reduce((sum, item) => sum + Number(item.op7), 0);
         this.totalop7=Op7.toFixed(2)
         const Op8 = this.tnerc.reduce((sum, item) => sum + Number(item.op8), 0);
         this.totalop8=Op8.toFixed(2)
         const Op9 = this.tnerc.reduce((sum, item) => sum + Number(item.op9), 0);
         this.totalop9=Op9.toFixed(2)
         const Op10 = this.tnerc.reduce((sum, item) => sum + Number(item.op10), 0);
         this.totalop10=Op10.toFixed(2)
         const Op11 = this.tnerc.reduce((sum, item) => sum + Number(item.op11), 0);
         this.totalop11=Op11.toFixed(2)
         const Op12 = this.tnerc.reduce((sum, item) => sum + Number(item.op12), 0);
         this.totalop12=Op12.toFixed(2)
         const Op13 = this.tnerc.reduce((sum, item) => sum + Number(item.op13), 0);
         this.totalop13=Op13.toFixed(2)
       
      })

      console.log(genRptInput.reportName);
      console.log(genRptInput.ip1);
     
    }

    openDownload(data: Response) {
      let content_type = data.headers.get('Content-type');
      let x_filename = "tnerc-energy-summary-report.pdf";
      console.log(content_type)
      saveAs(data.blob(), x_filename);
    }

    printReport(){ 
      this.service.printTnercEnergySummary(this.year).subscribe(xs=>{
        this.openDownload(xs);
      });
  
    }
}