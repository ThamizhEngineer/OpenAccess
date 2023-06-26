import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
//import { SesAddComponent } from './../ses-add/ses-add.component';
import { EsService } from './../../../energy-sale/services/es.service'; 
import { Es} from '../../../vo/es'; 
import { CommonService } from './../../../../shared/common/common.service'; 


@Component({
  selector: 'ses-list',
  templateUrl: './ses-list.component.html',
   providers: [ EsService, Es ,CommonUtils]
})
export class SesListComponent implements OnInit {

  rows: Array<Es>;
  //dialogRef: MatDialogRef<SesAddComponent>;
  config: MatDialogConfig = {
    disableClose: false,
    width: '100%',
    height: '50%',
    position: {
      left: '20%',
      right: '20%'
    }
  };
  lastCloseResult: string;

  tempResults: Array<Es>;

  //for pagination
  count = 0;
  offset = 0;
  limit = 8;
  months= [];
  years:any[];
  searchBuyerCompanyName:string="";
  searchSellerCompanyServiceNumber:string="";
  searchEdcName:string="";
  searchMonth:string="";
  searchYear:string="";
  dispFuelTypeCode:string="";
  searching:boolean = false;
  
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
 
    private route: ActivatedRoute,
    private router: Router,
    private service: EsService,
    private commonService: CommonService,
    public dialog: MatDialog
  ) {
  }

  ngOnInit() {
    this.accessAddFlag = this.commonUtils.userHasAccess("ENERGY-STATEMENT", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("ENERGY-STATEMENT", "VIEW");
    this.months = this.commonService.fetchMonths();
    this.years = this.commonService.fetchYearList();
   this.rows = [];
   this.page(this.offset,this.limit);
  }
 

  page(offset, limit) { 
    this.offset = offset;
    this.limit = limit;
    //this.fetchResults()
  
  
  }

  searchResults(){
	this.searching = true;
    this.service.getAllEss(this.searchBuyerCompanyName,this.searchSellerCompanyServiceNumber,this.searchEdcName,"","","","",this.searchMonth,this.searchYear, 'Y','').subscribe(
      _es=>{
		this.searching = false;
        this.rows = _es;
        console.log(this.rows);
      });
  }
  fetchResults(){
    this.service.getEss().subscribe(
      _eas => {  
        this.tempResults = _eas;
        
        this.count = this.tempResults.length;
        const start = this.offset * this.limit;
        const end = start + this.limit;
        const rows = [...this.rows];

        for (let i = start; i < end; i++) {
          rows[i] = this.tempResults[i];
        }

        this.rows = rows;
        console.log(rows);
        console.log('Page Results', start, end, rows);
      });
  }

  onPage(event) {
    console.log('Page Event', event);
    this.page(event.offset, event.limit);
  }

  
  newSes() {
   try {
      this.router.navigateByUrl('/ses/ses-new');
    }
    catch (e) {
      console.log(e);
    }
 }

  editSes(_id: string) {
	  try{
	  console.log(_id);
      this.router.navigateByUrl('/ses/ses-edit/'+ _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
    
  }

  ///To allow number only
  numberFormat(event){
    this.commonService.numberOnly(event)
}

}
