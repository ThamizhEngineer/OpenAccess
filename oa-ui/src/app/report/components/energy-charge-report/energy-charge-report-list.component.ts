import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'energy-charge-report',
  templateUrl: './energy-charge-report-list.component.html'
})
export class EnergyChargeReportComponent implements OnInit {
  rows=[];
  searchSellerServiceNumber: string = "";
  searchYear: string = "";
  searchMonth: string = "";
  months= [];
  years=[];

  constructor(private service:ReportService, private commonService:CommonService) { }

  ngOnInit() {
    this.months = this.commonService.fetchMonths();
    this.years = this.commonService.fetchYearList();


  }
	
openDownload(data: Response) {
  let content_type = data.headers.get('Content-type');
  let x_filename = "energy-adjusted-order.pdf";
  console.log(content_type)
  saveAs(data.blob(), x_filename);
}
printReport(){
  this.service.printEnergyChargeOrder(this.searchMonth,this.searchYear,this.searchSellerServiceNumber).subscribe(xs=>{
    this.openDownload(xs);
  });

}
}
