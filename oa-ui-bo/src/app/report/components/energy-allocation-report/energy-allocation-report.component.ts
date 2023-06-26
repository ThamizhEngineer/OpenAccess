// import { Component, OnInit } from '@angular/core';
// import { Observable } from 'rxjs/Rx';
// import { GenericReportOutput } from '../../vo/GenericReportOutput';
// import { GenericReportInput } from '../../vo/GenericReportInput';
// import { ReportService } from '../../services/report.service';
// import { CommonService } from '../../../shared/common/common.service';
// import { saveAs } from 'file-saver';


// @Component({
//     selector: 'energy-allocation-report',
//     templateUrl: 'energy-allocation-report.component.html'
// })

// export class EnergyAllocationReportComponent implements OnInit {
//     rows=[];
//     searchServiceNo: string ="";
//     searchMonth: string ="";
//     searchYear: string ="";
//     months=[];
//     fuelTypes = [
//       { "key": "02", "name": "Wind" },
//       { "key": "18", "name": "Solar" }

//     ]
//     constructor(private service:ReportService, private commonService:CommonService) { }

//     ngOnInit() {
//         this.months = this.commonService.fetchMonths();
       
//     }
//     openDownload(data: Response) {
//       let content_type = data.headers.get('Content-type');
//       let x_filename = "EnergyAllocatedReport.pdf";
//       console.log(content_type)
//       saveAs(data.blob(), x_filename);
//     }
//     printReport(){
//       this.service.printEnergyAllocationReport(this.searchServiceNo,this.searchMonth,this.searchYear).subscribe(xs=>{
//         this.openDownload(xs);
//       });
//   }
//   searchResults() {

//     this.service.getAllAllocations(this.searchServiceNo,this.searchMonth,this.searchYear).subscribe(
//       _alloc => {
//         this.rows = _alloc;
//         console.log("In search result")
//         console.log(this.rows);
//       });
//    }
// }