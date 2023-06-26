import {Component,OnInit, Input} from '@angular/core'
import {CompanyNameChange} from './../../../vo/company-name-change'; 
import { EsIntent,EsIntentLine} from './../../../vo/es-intent'; 
import { CompanyNameChangeService } from './../../services/company-name-change.service';
import { MatDatepickerModule } from '@angular/material';
import { DatePipe } from '@angular/common';
import { MatNativeDateModule } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EsIntentService}from './../../../energy-sale-intent/services/es-intent.service';
@Component({
  selector: 'approve-captive-change',
  templateUrl: './approve-captive-change.component.html',
  providers:[CompanyNameChangeService,CommonUtils, MatDatepickerModule, EsIntentService, MatNativeDateModule,DatePipe]
})

export class ApproveCaptiveChange implements OnInit{
  @Input() companyNameChange: CompanyNameChange;
  @Input() stepDisable: boolean;

  constructor(  
     private service:CompanyNameChangeService,  
     private commonUtils: CommonUtils,
     ) 
     {  }
  ngOnInit(){

    this.service.companyNameChangeEvent.subscribe(_companyNameChange => {

      this.companyNameChange = _companyNameChange;
      console.log(this.companyNameChange);

    })
  }

 

}