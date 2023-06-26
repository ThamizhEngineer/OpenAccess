import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EsorderService } from '../../../../ledger/energy-sale-order/components/services/esorder.service';
import { EsOrder } from '../../../../ledger/energy-sale-order/components/services/esorder';
import { CommonService } from './../../../../shared/common/common.service';



@Component({
  selector: 'es-order-list',
  templateUrl: './es-order-list.component.html',
  styleUrls: ['./es-order-list.component.scss'],
  providers: [CommonUtils]
})
export class EsOrderListComponent implements OnInit {

  esorder: EsOrder;

  //for pagination
  count = 0;
  offset = 0;
  limit = 8;
  months = [];
  searchCompanyName: string = "";
  searchsellerCompanyServiceNumber: string = "";
  companyServiceNumber:string="";
  searchMonth: string = "";
  searchYear: string = "";
  searchEdcName: string = "";
  orgList = [];
  filterNCES = [];
  fuelTypes= [];
  fuelTypeName: string ="";
  searchOrgId: string = "";
  disableEdc: boolean = false;
  tempResults: Array<EsOrder>;
  disableCompanyName: boolean = false;

  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  years: any[];
  constructor(
    private commonUtils: CommonUtils,

    private service: EsorderService,
    private commonService: CommonService,
    private router: Router,

  ) {

  }
  buyers: Array<EsOrder>;
  allotOredr: Array<EsOrder>;
  rows: Array<EsOrder>;
  ngOnInit() {
    this.esorder = new EsOrder();
    this.accessAddFlag = this.commonUtils.userHasAccess("ENERGY-SALE-ORDER", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("ENERGY-SALE-ORDER", "VIEW");
    this.months = this.commonService.fetchMonths();
    this.years = this.commonService.fetchYearList();
    this.fetchEDCs();
    this.fetchFuelTypes();
    this.searchMonth = this.commonUtils.getPerviousMonth();
   
    this.esorder.sellerEndOrgId = CommonUtils.getLoginUserEDC();
    if (this.esorder.sellerEndOrgId != "") {
      console.log("in edc select")
      this.searchOrgId = this.esorder.sellerEndOrgId;
      this.disableEdc = true;
    }
    this.rows = [];

    if(CommonUtils.getLoginUserTypeCode()=='GEN')
    {
     console.log(CommonUtils.getLoginUserTypeCode());
     console.log( CommonUtils.getLoginUserCompany());
      this.esorder.companyServiceNumber = CommonUtils.getLoginUserCompany();
      if (this.esorder.companyServiceNumber != "") {
         this.companyServiceNumber = this.esorder.companyServiceNumber;
        // this.disableEdc = true;
        this.disableCompanyName = true;
        console.log(this.companyServiceNumber);
        
      }
    }

    this.page(this.offset, this.limit);
  }
  page(offset, limit) {
    this.offset = offset;
    this.limit = limit;
    //this.fetchResults()


  }
  searchResults() {
    this.rows=[];
    if (this.esorder.sellerEndOrgId != "") {
      this.searchOrgId = this.esorder.sellerEndOrgId;
    }

    // if(CommonUtils.getLoginUserTypeCode()=='EDC'){
    // this.service.getAllEsoBuyers(this.searchOrgId, this.companyServiceNumber,  this.searchMonth, this.searchYear,this.fuelTypeName,).subscribe(
    //   _es => {
    //     this.buyers = _es;
    //     console.log(this.buyers)
    //   });
    // }

    if(CommonUtils.getLoginUserTypeCode()=='GEN'){
      if (this.esorder.companyServiceNumber != "") {
      this.companyServiceNumber = this.esorder.companyServiceNumber;
    }
  }
    this.service.getAllEso(this.searchCompanyName, this.companyServiceNumber, this.searchOrgId, this.searchMonth, this.searchYear,this.fuelTypeName, '').subscribe(
      _es => {
        this.rows = _es;
        // console.log("In search result")
        // console.log(this.rows);
        // if(CommonUtils.getLoginUserTypeCode()=='EDC'){
        //   this.allotOredr = _es;
        //   console.log(this.allotOredr);
        //   this.service.getAllEsoBuyers(this.searchOrgId, this.companyServiceNumber,  this.searchMonth, this.searchYear,this.fuelTypeName,).subscribe(
        //       _es => {
        //         this.buyers = _es;
        //         console.log(this.buyers)
        //         this.rows = this.allotOredr.concat(this.buyers);
        //         console.log(this.rows);
        //       });

        //   // this.rows = this.allotOredr.concat(this.buyers);
          
        //   }
        //   else{
        //     this.rows = _es;
        //     console.log(this.rows);
        //   }

        this.esorder.sellerEndOrgId = CommonUtils.getLoginUserEDC();

      });
  }

  fetchFuelTypes(){
    this.commonService.FetchFuels()
    .subscribe(
      fuelTypes => {this.fuelTypes = fuelTypes;},
      error => {
        console.error("Error loading Fuel Types");
        console.error(error);
      });
  }

  onPage(event) {
    console.log('Page Event', event);
    this.page(event.offset, event.limit);
  }
  editEso(_id: string) {
    try {
      console.log(_id);
      this.router.navigateByUrl('/es-order/es-order-detail/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }

  // fetchEDCs() {
  //   this.commonService.fetchEDCs().subscribe(
  //     result => {
  //       this.orgList = result;
  //     },
  //     error => {
  //       console.error('Error loading orgs!');
  //       console.error(error);
  //     });
  // }

  fetchEDCs() {
    // this.commonService.fetchWindEDCs().subscribe(
      this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
        console.log(result)

        // this.searchOrgid = CommonUtils.getLoginUserEDC();
        // console.log(this.searchOrgid)
       
        if(CommonUtils.getLoginUserTypeCode()=='MRT'){
          console.log(CommonUtils.getLoginUserCompany());
          console.log(this.orgList.filter((item) => item.ncesDivisionCode))
          this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode == CommonUtils.getLoginUserCompany());
          console.log(this.filterNCES)

          this.orgList =  this.filterNCES;
          console.log(this.orgList)
      

        }
        else if(CommonUtils.getLoginUserTypeCode()=='NCES'){
          console.log(CommonUtils.getLoginUserCompany());
          console.log(this.orgList.filter((item) => item.ncesDivisionCode))
          this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode);
          console.log(this.filterNCES)

          this.orgList =  this.filterNCES;
          console.log(this.orgList)
      

        }
       
 
      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }

///To allow number only
numberFormat(event){
  this.commonService.numberOnly(event)
}

}

