import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
 
import { DatePipe } from '@angular/common';
import { MeterReadingImportService } from './../../services/meter-reading-import.service'; 
import { MeterReadingImport} from './../../../vo/meter-reading-import'; 
import { CommonService } from './../../../../shared/common/common.service'; 
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'meter-reading-import-list',
  templateUrl: './meter-reading-import-list.component.html',
  //styleUrls: ['./-list.component.scss']
  providers : [MeterReadingImportService]
})
export class MeterReadingImportListComponent implements OnInit {

  searchBatchCode:string;
  searchImportDate:string;
  rows: Array<MeterReadingImport>;
  tempResults: Array<MeterReadingImport>;

  searchMrSourceCode:string;
  searchStatus:string;
  SourceCodeTypes = [
    { "key": "01", "name": "MDMS" },
    { "key": "02", "name": "UI" }
  ]
  statues=[
    {"key":"CREATED","name":"CREATED"},
  {"key":"COMPLETED","name":"COMPLETED"},
  {"key":"IMPORT-ERROR","name":"IMPORT-ERROR"}]
  imported:any;
  procResult:string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private datePipe: DatePipe,
    private commonService: CommonService,
    private service: MeterReadingImportService
  ) {
    console.log('router path'+this.router.url);
  }

  ngOnInit() {
   this.rows = [];
  
  }
 
  searchResults(){

    console.log(this.searchImportDate);
    this.searchImportDate = this.datePipe.transform(this.searchImportDate, 'yyyy-MM-dd');
    console.log(this.searchImportDate);
    if(this.searchBatchCode===undefined){
      this.searchBatchCode = null;
    }
    this.service.getMeterReadingImports(this.searchBatchCode,this.searchImportDate,this.searchStatus,this.searchMrSourceCode).subscribe(
      _mri=> {  
        this.rows = _mri
        console.log(this.rows)
      }
    )

  }
 
  
  newMeterReadingImport() {
	   try{
       this.router.navigateByUrl('/meter-reading-import/meter-reading-import-new');
  
       }
     catch(e){
         console.log(e);
           }
    
        }

  editMeterReadingImport(_id: string) {
	  try{
	    console.log("Edit id value"+_id);
      this.router.navigateByUrl('/meter-reading-import/meter-reading-import-edit/'+ _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
    }
  }

  importMeterReadingImport(_id:string){
    alert(_id);
    console.log('import init for '+ _id);
    try{
      if(_id != "undefined" || _id != null) {
      this.service.processMeterReadingImport(_id)
      .subscribe(readings => this.imported = readings);
      console.log(this.imported);
      alert(this.imported);
      }
    }
    catch(e){
      console.log(e);
    }
  }
   
}
