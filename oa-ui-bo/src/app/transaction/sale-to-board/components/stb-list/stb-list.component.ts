import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
 
import { CommonUtils } from '../../../../shared/common/common-utils';
import { StbService } from './../../services/stb.service'; 

import { CommonService } from './../../../../shared/common/common.service';
import { Es,EsUsageSummary } from '../../../../transaction/vo/es';


@Component({
  selector: 'stb-list',
  templateUrl: './stb-list.component.html',
  providers: [CommonUtils]
  //styleUrls: ['./gs-list.component.scss']
})
export class StbListComponent implements OnInit {

  rows: Array<Es>;
  years:any[];
  orgList = [];
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private service: StbService
  ) {
    console.log('router path'+this.router.url);
  }

  ngOnInit() {
    this.accessAddFlag = this.commonUtils.userHasAccess("SALE-TO-BOARD", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("SALE-TO-BOARD", "VIEW");
   this.rows = [];
   this.fetchResults();
   this.years = this.commonService.fetchYearList();
  }
 

 
  fetchEDCs() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }

  fetchResults(){
    console.log("In FetchResult");
    this.service.getStb().subscribe(
      _stb => {  
     this.rows = _stb;
      });
  }

  



  editStb(_id: string) {
	  try{
	  console.log(_id);
      this.router.navigateByUrl('/stb/stb-edit/'+ _id);
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
    // editStb(){
    //     try{
    //     this.router.navigateByUrl('/stb/stb-edit');
    //     }
    // catch(e){
    //     console.log(e);
    // }
    // }
}
