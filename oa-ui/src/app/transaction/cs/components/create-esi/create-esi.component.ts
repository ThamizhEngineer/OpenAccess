import {Component,OnInit, Input} from '@angular/core'
import { Cs} from './../../../vo/cs'; 
import { EsIntent,EsIntentLine} from './../../../vo/es-intent'; 
import { CsService } from './../../services/cs.service';
import { MatDatepickerModule } from '@angular/material';
import { DatePipe } from '@angular/common';
import { MatNativeDateModule } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EsIntentService}from './../../../energy-sale-intent/services/es-intent.service';
@Component({
  selector: 'create-esi',
  templateUrl: './create-esi.component.html',
  providers:[CsService,CommonUtils, MatDatepickerModule, EsIntentService, MatNativeDateModule,DatePipe]
})

export class CreateEsiAgreements implements OnInit{
  @Input() cs: Cs;
  @Input() stepDisable: boolean;

  fromDate:any;
  toDate:any;
  esIntent:EsIntent;
  esIntentLine:EsIntentLine;
  agmtPeriodCodes=[
    {"key": "STOA", "name": "STOA" },
    {"key": "MTOA", "name": "MTOA" },
    {"key": "LTOA", "name": "LTOA" }
  ]
  mpFromConfig = {'placeHolder': 'Duration-From', 'readonly':false};
  mpFromModel = {'month':'', 'year':''};
  mpToConfig = {'placeHolder': 'Duration-To', 'readonly':false};
  mpToModel = {'month':'', 'year':''};
  constructor(  
     private service:CsService,  
     private commonUtils: CommonUtils,
     private datePipe: DatePipe,
     private esIntentService:EsIntentService) 
     {  }
  ngOnInit(){
  this.esIntent =new EsIntent();
  this.esIntentLine = new EsIntentLine();
  this.mpFromModel = null;
  this.mpToModel = null;

    this.service.csEvent.subscribe(_cs => {

      this.cs = _cs;
      console.log(this.cs);

    })
  }

  monthConvertor() {
    
        var fromMonthStr = this.cs.fromMonth;
        var fromYearStr = this.cs.fromYear;
        var fromMonth = +fromMonthStr;
          fromMonth = fromMonth - 1;
        var fromYear = +fromYearStr;
        this.fromDate = new Date(fromYear,fromMonth, 1);

        var toMonthStr = this.cs.toMonth;
        var toYearStr = this.cs.toYear;
        var toMonth = +toMonthStr;
        toMonth = toMonth - 1;
        var toYear = +toYearStr;
        this.toDate = new Date(toYear,toMonth + 1, 0);
        this.cs.fromDate = this.fromDate;
        this.cs.toDate = this.toDate;   
    
      }
  mpOnFromModelChange(e){
	  this.cs.fromMonth = e.month;
	  this.cs.fromYear= e.year;

  }
  mpOnToModelChange(e){
	  this.cs.toMonth = e.month;
	  this.cs.toYear= e.year;

  }
  createEsi(){
    this.monthConvertor();
   
    this.esIntent.sellerCompanyServiceId = this.cs.sellerCompanyServiceId;
    this.esIntent.agmtPeriodCode = this.cs.agreementPeriodCode;
    this.esIntent.fromDate =this.cs.fromDate;
    this.esIntent.toDate =this.cs.toDate;
    this.esIntent.fromMonth = this.cs.fromMonth;
    this.esIntent.toMonth = this.cs.toMonth;
    this.esIntent.fromYear = this.cs.fromYear;
    this.esIntent.toYear = this.cs.toMonth;
    this.esIntent.flowTypeCode="CAPTIVE";
    this.cs.csQuantumAllocations.forEach(element=>{
      this.esIntentLine.buyerCompanyServiceId = element.buyerCompServiceId;
      this.esIntentLine.proposedQuantum = element.quantum;
    });
    this.esIntent.energySaleIntentLines=[];
    this.esIntent.energySaleIntentLines.push(Object.assign({},this.esIntentLine));
    this.formatChangesforDB();
    this.addEsIntent();
    this.service.setCs(this.cs);
  
  }

  addEsIntent() {
    this.esIntent.statusCode="CREATED";

    this.esIntentService.addEsIntent(this.esIntent).subscribe(
      result => {
         console.log(result);
         this.cs.esIntentId = result.id;
         this.cs.esIntentCode = result.code;
         alert(result.message);
     
      },
      error => {
        console.error('Error adding EsIntent!');
        console.error(error);
      
      }
    );
  }

  formatChangesforDB() {
    this.cs.fromDate = this.datePipe.transform(this.cs.fromDate, 'yyyy-MM-dd');
    this.cs.toDate = this.datePipe.transform(this.cs.toDate, 'yyyy-MM-dd');
        this.esIntent.fromDate = this.datePipe.transform(this.esIntent.fromDate, 'yyyy-MM-dd');
        this.esIntent.toDate = this.datePipe.transform(this.esIntent.toDate, 'yyyy-MM-dd');
    
      }

}