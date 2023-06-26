import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
//import { SessionStorage} from "angular2-localstorage/WebStorage";
import { MeterReading,MeterReadingSlot,MeterReadingForPrint } from './../../services/meter-reading';

//import { ConsentEvent } from './../../services/consent.event';
import { MeterReadingService } from './../../services/meter-reading.service';
import { CommonService } from './../../../../shared/common/common.service';
import { MeterReadingImport, MeterReadingImportLine } from './../../../vo/meter-reading-import';


@Component({
  selector: 'meter-reading-detail',
  templateUrl: './meter-reading-detail.component.html',
  providers: [CommonService, MeterReadingService, DatePipe]
})

export class MeterReadingDetailComponent {
  meterReading: MeterReading;
  meterReadingSlot:MeterReadingSlot;
  rows: Array<MeterReading>;
  months = [];
  disableControls: boolean = true;
  result:string;


  meterReadingForPrint: Array<MeterReadingForPrint>;

  mrSourceCodes = [
    { "key": "01", "name": "AMR READING" },
    { "key": "02", "name": "MANUAL READING" },
    { "key": "03", "name": "CMRI READING" }
  ]


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private datePipe: DatePipe,
    private service: MeterReadingService,





  ) {

  }
  ngOnInit() {

    this.meterReading = new MeterReading();
    this.months = this.commonService.fetchMonths();
    this.meterReadingSlot = new MeterReadingSlot();

    this.rows = [];
    this.meterReadingForPrint = [];



    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 

      this.route.params
        .subscribe((_params: Params) => {
          this.service.getMrById(_params['_id']).subscribe(_mr => {
            this.meterReading = _mr;
          });
        });
    }

  }
  processGenerationStatement(){
    console.log("processGenerationStatement");
    
      // this.service.getGenerationStatement(this.meterReading.readingYear,this.meterReading.readingMonthCode).subscribe(
      //    _gs=>{
      //      this.result = _gs;
 
      //      console.log(this.result);
           
      //    });
     }
  listMeterReading() {
    this.router.navigateByUrl('/meter-reading/meter-reading-list');
  }


}