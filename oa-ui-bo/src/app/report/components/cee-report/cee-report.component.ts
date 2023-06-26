import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { saveAs } from 'file-saver';

@Component({
    selector: 'cee-report',
    templateUrl: './cee-report.component.html'
  })

  export class CeeReportComponent implements OnInit{
      rows=[];
      searchMonth: string ="";
      searchYear: string ="";
      searchtypeOfShare: string ="";
      searchfuelType: string ="";
      months=[];
      fuelTypes = [
        { "key": "WIND", "name": "Wind" },
        { "key": "SOLAR", "name": "Solar" }

      ]
      totalop4: any;
      totalop5: any;
      years:any[];


      constructor(private service:ReportService, private commonService:CommonService) { }

      ngOnInit() {
        this.months = this.commonService.fetchMonths();
        this.years = this.commonService.fetchYearList();
   
      }
      openDownload(data: Response) {
        let content_type = data.headers.get('Content-type');
        let x_filename = "CeeReport.pdf";
        console.log(content_type)
        saveAs(data.blob(), x_filename);
      }
      printReport(){
        this.service.printCeeReport(this.searchMonth,this.searchYear,this.searchtypeOfShare,this.searchfuelType).subscribe(xs=>{
          this.openDownload(xs);
        });
    }
    searchResults() {

      this.service.getAllCee(this.searchMonth,this.searchYear,this.searchtypeOfShare,this.searchfuelType).subscribe(
        _cee => {
          this.rows = _cee;
          const Op4 = this.rows.reduce((sum, item) => sum + Number(item.totalCapacitySum), 0);
         this.totalop4=Op4.toFixed(2)
         const Op5 = this.rows.reduce((sum, item) => sum + Number(item.netGenerationSum), 0);
         this.totalop5=Op5.toFixed(2)

          console.log("In search result")
          console.log(this.rows);
        });
    }
  }