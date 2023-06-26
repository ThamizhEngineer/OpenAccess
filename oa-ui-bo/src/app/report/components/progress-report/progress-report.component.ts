import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { saveAs } from 'file-saver';

@Component({
    selector: 'progress-report',
    templateUrl: './progress-report.component.html'
  })

  export class ProgressReportComponent implements OnInit{
    rows=[];
    searchncesDivisionCode: string ="";
    searchMonth: string ="";
    searchYear: string ="";
    months=[];

    constructor(private service:ReportService, private commonService:CommonService) { }

    ngOnInit() {
        this.months = this.commonService.fetchMonths();
    
      }

      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "ProgressReport.pdf";
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }
      printReport(){
        this.service.printProgressReport(this.searchMonth,this.searchYear).subscribe(xs=>{
          this.openDownload(xs);
        });
      }
        searchResults() {

          this.service.getAllprogress(this.searchMonth,this.searchYear).subscribe(
            _prog => {
              this.rows = _prog;
              console.log("In search result")
              console.log(this.rows);
            });
    }
  }