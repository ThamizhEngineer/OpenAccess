import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'tnerc-net-generation-report',
  templateUrl: './tnerc-net-generation-report.component.html',
  styleUrls: ['./tnerc-net-generation-report.component.scss']
})
export class TnercNetGenerationReportComponent implements OnInit {
  tnerc: any;

    constructor(private service:ReportService, private commonService:CommonService) { }

  
    ngOnInit() {
        console.log("Hi");
        this.getAll();
      }

      getAll(){
        let genRptInput:GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'TNERC-NET-GENERATION-REPORT';
        this.service.getAllTnerc(genRptInput).subscribe(x=>{
            this.tnerc=x;
        })
     }
}