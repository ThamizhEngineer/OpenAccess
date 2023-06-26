import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';

import { CommonService } from './../../../../shared/common/common.service';
import { OperationService } from '../../../../transaction/operations/components/services/operation.service'; 
import { Operation } from '../../../../transaction/operations/components/services/operation'; 
import { GsService } from '../../../../transaction/generation-statement/services/gs.service';
import { Gs } from '../../../vo/gs';
import { MeterChangeList } from '../../../vo/MeterchangeList';
import { Observable } from 'rxjs';



@Component({
    selector: 'operation-list',
    templateUrl: './operation-list.component.html'

})
export class OperationListComponent implements OnInit {
operation:Operation;
  months= [];
  searchBatchCode:string="";
    searchyear:string="";
searchmonth:string;
  results:string="";
 years:any[];
  result:string="";
  gs:Gs;
  importMdmsMonth:string="";
  importMdmsYear:string="";
  importMeterChangeMdmsMonth:string="";
  importMeterChangeMdmsYear:string="";
  importMdmsResult:string="";
  genStmtId:string="";
  gsMonth:string="";
  gsYear:string="";
  importMeterChangeMdmsResult: MeterChangeList;
  currentstatus: any;
    constructor(
            private service: OperationService,
        private gsService: GsService,
        private commonService: CommonService,
        private router: Router,

    ) {

    }
  rows: Array<Operation>;


    ngOnInit() {
      this.gs = new Gs();
         this.months = this.commonService.fetchMonths();
   this.rows = [];
             this.operation= new Operation();
        this.years=this.commonService.fetchYearList();     
            
          

    }

      searchResults(){
 
   this.service.getMeterReading(this.searchBatchCode).subscribe(
      _es=>{
        this.result = _es;
        console.log("In search result")
        console.log(this.result);
        
      });
  }

  fetchResults(){
 
   this.service.getGenerationStatement(this.searchyear,this.searchmonth).subscribe(
      _gs=>{
        this.results = _gs;
        console.log("In search result")
        console.log(this.results);
        
      });
  }

  importMeterReadingFromMdms(){
    this.service.getMeterReadingFromMdms(this.importMdmsMonth,this.importMdmsYear).subscribe(
      _import=>{
        this.importMdmsResult = _import;
        console.log("In search result")
        console.log(this.importMdmsResult);
        
      });
  }

  importMeterChangeMeterReadingFromMdms(){
    this.service.getMeterChangeMeterReadingFromMdms(this.importMeterChangeMdmsMonth,this.importMeterChangeMdmsYear).subscribe(
      _import=>{
        this.importMeterChangeMdmsResult = _import;
        console.log("In search result")
        console.log(this.importMeterChangeMdmsResult);
        
      });
  //     new Observable(item=>{

  //       setTimeout(()=>{
  // item.next(this.importMeterChangeMeterReadingFromMdms())
  //       },2000);
  
  //       setTimeout(()=>{
  //         item.next('Pending')
  //               },4000);
  
  //               setTimeout(()=>{
  //                 item.next('Completed')
  //                       },6000);
  
  //     }).subscribe(result=>{
  //       this.currentstatus=result;
  //     });

      return 'done';
  }

  refreshmeterchange(){
    this.service.refreshmeterchange(this.importMeterChangeMdmsMonth,this.importMeterChangeMdmsYear).subscribe(
      _import=>{
        this.importMeterChangeMdmsResult = _import;
        console.log("In search result")
        console.log(this.importMeterChangeMdmsResult);
        
      });
  }

  genratePdf(){
    console.log("month-->",this.gsMonth);
    console.log("year-->",this.gsYear)
    this.gsService.generatePdf(this.gsMonth,this.gsYear).subscribe(x=>{
      
    })
  }

  regenerate(){
    console.log("gen-id",this.genStmtId)
    this.gs.id=this.genStmtId;
    console.log(this.gs)
    this.gsService.regeneratePdf(this.gs).subscribe(x=>{
      
    })
  }

}
