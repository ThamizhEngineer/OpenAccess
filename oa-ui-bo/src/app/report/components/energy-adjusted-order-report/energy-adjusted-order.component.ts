import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { GenericReportOutput } from '../../vo/GenericReportOutput';
import { GenericReportInput } from '../../vo/GenericReportInput';
import { ReportService } from '../../services/report.service';
import { CommonService } from '../../../shared/common/common.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'energy-adjusted-order',
  templateUrl: './energy-adjusted-order.component.html'
})
export class EnergyAdjustedOrderReportComponent implements OnInit {
  rows=[];
  searchSellerServiceNumber: string = "";
  searchYear: string = "";
  searchMonth: string = "";
  months= [];
  message: string;
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
  this.service.printEnergyAdjustedOrder(this.searchMonth,this.searchYear,this.searchSellerServiceNumber).subscribe(xs=>{
    this.openDownload(xs);
    this.message = "Downloaded Successfully"
  },
  error=>{
    if (JSON.parse(error._body).message) {
      this.message = JSON.parse(error._body).message;
    } else {
      this.message = "Too many request please try again later"
    }
  });

}

///To allow number only
numberFormat(event){
  this.commonService.numberOnly(event)
}

}
