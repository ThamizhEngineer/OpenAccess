import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../services/report.service';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { CommonService } from '../../../shared/common/common.service';
import { GenericReportInput } from '../../vo/GenericReportInput';

@Component({
  selector: 'org-report',
  templateUrl: './org-report.component.html',
  styleUrls: ['./org-report.component.scss']
})
export class OrgReportComponent implements OnInit {
  rows: Array<GenericReportOutput>;
  searchOrgId: string = "";
  searchOrgName: string="";
  orgList = [];

  constructor(private service:ReportService, 
    private commonService:CommonService) { }

  ngOnInit() {
    this.rows = [];
    this.commonService.fetchEDCs()
    .subscribe(edcs => this.orgList = edcs);
    console.log(this.orgList);
  }

  searchResults(){
    console.log(this.searchOrgId);
    let genRptInput: GenericReportInput = new GenericReportInput();
    genRptInput.reportName = 'ORGSELECT';
    genRptInput.ip1 = this.searchOrgId;
    genRptInput.ip2 = 'REGION';
    this.service.getGenericReport(genRptInput)
    .subscribe(items => this.rows = items);
    console.log(this.rows);
  }
}
