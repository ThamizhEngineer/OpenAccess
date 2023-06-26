import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';

import { CommonService } from './../../../../shared/common/common.service';
import { OperationService } from '../../../../transaction/operations/components/services/operation.service'; 
import { Operation } from '../../../../transaction/operations/components/services/operation'; 



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

  result:string="";

  importMdmsMonth:string="";
  importMdmsYear:string="";
  importMdmsResult:string="";
    constructor(
            private service: OperationService,

        private commonService: CommonService,
        private router: Router,

    ) {

    }
  rows: Array<Operation>;


    ngOnInit() {
         this.months = this.commonService.fetchMonths();
   this.rows = [];
             this.operation= new Operation();


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

}
