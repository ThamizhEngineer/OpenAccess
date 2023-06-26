import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
//import { CommonService } from '../../../../shared/common/common.service';

import { TradeRelationshipService } from './../../services/trade-relationship.service'; 
import { TradeRelationship} from './../../../vo/trade-relationship'; 
import { CommonService } from './../../../../shared/common/common.service'; 

@Component({
  selector: 'app-trade-relationship-list',
  templateUrl: './trade-relationship-list.component.html',
  //styleUrls: ['./trade-relationship-list.component.scss'],
  providers: [TradeRelationshipService]
})
export class TradeRelationshipListComponent implements OnInit{ 
    rows: Array<TradeRelationship>;
    searchSellerCompanyCode:string="";
    searchSellerCompanyName:string="";
    searchSellerCompanyServiceNumber:string="";
    searchBuyerEndOrgId: string = "";
    searchSellerEndOrgId: string = "";
    searchbuyerCompanyServiceId: string="";
    searchsellerCompanyServiceId: string="";
    searchbuyerCompanyCode: string="";
    searchquantum:string="";
    edcList = [];
    companyServices=[];


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    	private commonService: CommonService,

    private service: TradeRelationshipService
  ) {
  //  this.fetchResults();
  }

  ngOnInit() {
    this.rows = [];
        this.fetchOrgList();

      }
 
 

  fetchResults(){
    this.service.getTradeRelationships(this.searchSellerCompanyCode,this.searchSellerCompanyName,this.searchSellerCompanyServiceNumber,this.searchsellerCompanyServiceId,"",this.searchbuyerCompanyServiceId,"","",this.searchquantum,this.searchbuyerCompanyCode).subscribe(
       _tradeRelationships => {  
       
        this.rows = _tradeRelationships;
        console.log(this.rows)
        
     });
  }
      fetchOrgList(){
    this.commonService.fetchEDCs().subscribe(
      result => { 
        this.edcList = result;
      },
      error => {
         console.error('Error loading states!');
         console.error(error);
      }
      );
  }
 
  newTradeRelationship() {
	  try{
	    //this.router.navigate(['/feeder-new','_id', _id]);
      this.router.navigateByUrl('/trade-relationship/trade-relationship-new');
      // to-do
      // this still needs to be fixed as giving '/order' is unecefeederary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
    
  }

  editTradeRelationship(_id: string) {
	  try{
	  console.log("Edit id value"+_id);
     this.router.navigateByUrl('/trade-relationship/trade-relationship-edit/'+ _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecefeederary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
    
  }
    onBuyerEdcChange(){
    console.log("In edc change");
    this.service.getCompanyByEdc(this.searchBuyerEndOrgId,this.searchSellerEndOrgId).subscribe(
      _companyServices=>{
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
}

}
