import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { saveAs } from 'file-saver';

@Component({
    selector: 'srcp-report',
    templateUrl: './srcp-report.component.html'
  })

  export class SrcpReportComponent implements OnInit{
    rows=[];
    searchncesDivisionCode: string ="";
    searchMonth: string ="";
    searchYear: string ="";
    months=[];
    years:any[];
    totalop4: any;
  totalop5: any;
  totalop6: any;

    constructor(private service:ReportService, private commonService:CommonService) { }

    ngOnInit() {
        this.months = this.commonService.fetchMonths();
        this.years = this.commonService.fetchYearList();
      }

      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "SrcpReport.pdf";
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }
      printReport(){
        this.service.printSrcpReport(this.searchncesDivisionCode,this.searchMonth,this.searchYear).subscribe(xs=>{
          this.openDownload(xs);
        });
      }
        searchResults() {

          this.service.getAllsrcp(this.searchMonth,this.searchYear).subscribe(
            _srcp => {
              this.rows = _srcp;
              console.log("In search result")
              console.log(this.rows);
              const Op4 = this.rows.reduce((sum, item) => sum + Number(item.noOfWeg), 0);
         this.totalop4=Op4.toFixed(2)
         const Op5 = this.rows.reduce((sum, item) => sum + Number(item.totalCapacitySum), 0);
         this.totalop5=Op5.toFixed(2)
         const Op6 = this.rows.reduce((sum, item) => sum + Number(item.totalGenerationSum), 0);
         this.totalop6=Op6.toFixed(2)
            });
    }
  }