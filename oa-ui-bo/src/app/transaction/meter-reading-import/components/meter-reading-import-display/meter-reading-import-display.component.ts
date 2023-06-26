import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/startWith';

import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule, FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';
//import { SessionStorage} from "angular2-localstorage/WebStorage";
import { CommonUtils } from '../../../../shared/common/common-utils';
//import { ConsentEvent } from './../../services/consent.event';
import { MeterReadingImportService } from './../../services/meter-reading-import.service';
import { CommonService } from './../../../../shared/common/common.service';
import { MeterReadingImport, MeterReadingImportLine } from './../../../vo/meter-reading-import';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { SignupService } from './../../../../master/signup/services/signup.service';

@Component({
  selector: 'meter-reading-import-display',
  templateUrl: './meter-reading-import-display.component.html',
  providers: [CommonService,CommonUtils, MeterReadingImportService, SignupService, MatDatepickerModule, MatNativeDateModule, DatePipe]
})

export class MeterReadingImportDisplayComponent {

  

  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: MeterReadingImportService,
    private commonService: CommonService,
    private datePipe: DatePipe

  ) {


  }

  ngOnInit() {
    
  
  
   
    this.router.navigateByUrl('/meter-reading-import/meter-reading-import-process');
  }

  

}