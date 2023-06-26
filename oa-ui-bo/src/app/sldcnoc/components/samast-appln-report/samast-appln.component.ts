import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { CommonService } from '../../../shared/common/common.service';
import { DatePipe, DOCUMENT } from '@angular/common';
import { saveAs } from 'file-saver';
import { ExcelExportService } from '../../../shared/services/excelExport';
import { CommonUtils } from '../../../shared/common/common-utils';
import { SdlcNocService } from '../../sldc-noc.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Http } from '@angular/http';
import { GenericReportInput } from '../vo/GenericReport';


@Component({
  selector: 'samast-appln',
  templateUrl: './samast-appln.component.html',
})
export class SamastApplnComponent implements OnInit {
    row: any;
  appnlNo: string;
  rows: any;
  extSamast: any;
  appnlDt: String;
  // setPage: any;



    constructor(private router: Router, private route: ActivatedRoute, 
      private service:SdlcNocService, private commonService:CommonService, private http: Http) { 
    }

    ngOnInit() {
      this.getExtSamast();
      // this.searchReport();
    }

    searchReport(){
      let genericRptInput:GenericReportInput = new GenericReportInput();
      genericRptInput.reportName = 'Samast-Appln-Report';
      var datePipe = new DatePipe('en-US');
      var newAppnlNo = datePipe.transform(this.appnlDt, 'dd-MM-yy');
      if(newAppnlNo != "" && 
      newAppnlNo!= null)
     
      genericRptInput.ip1 = newAppnlNo;  

      if(this.appnlNo != "" && 
      this.appnlNo != null)
      genericRptInput.ip2 = this.appnlNo; 
      // console.log(genericRptInput);
      this.service.getGenericReport(genericRptInput).subscribe(
        x => {
          this.rows=x;
          console.log(this.rows)
        });
        console.log(this.rows)
      }


    getExtSamast(){
        this.service.getExtSamstAppln().subscribe(x=>{
          this.extSamast=x;
           console.log(this.extSamast)
      });
    }
}