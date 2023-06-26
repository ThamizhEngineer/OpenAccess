import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CommonUtils } from '../../../../shared/common/common-utils';
import { EwaService } from './../../services/ewa.service';
import { Ewa } from './../../../vo/ewa';

import { CommonService } from './../../../../shared/common/common.service';
import { SignupService } from './../../../../master/signup/services/signup.service';

@Component({
  selector: 'ewa-list',
  templateUrl: './ewa-list.component.html',
  providers: [EwaService, SignupService, CommonUtils]
})
export class EwaListComponent implements OnInit {
  ewa:Ewa;
  rows: Array<Ewa>;
  tempResults: Array<Ewa>;
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;

  disableEdc: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private signupService: SignupService,
    private service: EwaService
  ) {
    this.fetchOrgList();
    this.fetchVoltageList();
  }

  ngOnInit() {
    this.ewa = new Ewa();
    this.rows = [];
    this.accessAddFlag = this.commonUtils.userHasAccess("EWA", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("EWA", "VIEW");
    this.ewa.sellerOrgId =  CommonUtils.getLoginUserEDC(); 
    this.ewa.sellerCompanyId = CommonUtils.getLoginUserCompanyId();
 
    if ((this.ewa.sellerOrgId  != "") && isFinite(parseInt(this.ewa.sellerOrgId ))  && (this.ewa.sellerCompanyId != "")) {
      this.searchSellerEndOrgId = this.ewa.sellerOrgId;
      this.searchSellerCompanyId = this.ewa.sellerCompanyId;
      

    console.log(this.searchSellerEndOrgId);
    console.log(this.searchSellerCompanyId);
      this.disableEdc = true;
      this.onEdcChange();
    }
  }




  newEwa() {
    try {
      this.router.navigateByUrl('/ewa/ewa-new');
    }
    catch (e) {
      console.log(e);
    }

  }

  editEwa(_id: string) {
    try {
      this.router.navigateByUrl('/ewa/ewa-edit/' + _id);
    }
    catch (e) {
      console.log(e);
    }
  }
  searchSellerCompanyServiceId: string = "";
  searchSellerCompanyId: string = "";
  searchSellerEndOrgId: string = "";
  searchStatus: string = "";
  searchEsIntentCode: string = "";
  searchEwaCode: string = "";
  companyServices = [];

  searchVoltage: any;
  searchBuyer: any;
  searchEDC: any;
  searchFromDate: any;
  searchToDate: any;
  // searchResults(){
  // var voltage =  this.searchVoltage && this.searchVoltage.valueCode ? this.searchVoltage.valueCode :'';
  // var edc = this.searchEDC && this.searchEDC.code ? this.searchEDC.code :'';
  // var buyer = this.searchBuyer && this.searchBuyer.id ? this.searchBuyer.id :'';
  // var fromDate = this.searchFromDate? this.searchFromDate :'';
  // var toDate = this.searchToDate? this.searchToDate :'';
  // this.service.getAllEwas(edc, buyer, voltage, fromDate, toDate).subscribe(
  //     _ewas=>{
  //       this.rows = _ewas;
  //     });
  // }

  orgList = [];
  voltageList = [];
  buyerCompanyServices = [];
  filteredOrgList = [];
  filteredVoltageList = [];
  filterOrgList(val: string) {
    return val ? this.orgList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.orgList;
  }

  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }

  filterBuyerList(val: string) {
    return val ? this.buyerCompanyServices.filter((s) => s.companyName.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.buyerCompanyServices;
  }
  fetchBuyerCompanyServices() {
    if (this.filteredOrgList.length == 1) {
      this.signupService.getBuyerCompanyServicesByOrgId(this.filteredOrgList[0]['id']).subscribe(
        result => {
          this.buyerCompanyServices = result;
          console.log("buyerCompanyServices")
          console.log(this.buyerCompanyServices)
        },
        error => {
          console.error('Error loading company!');
          console.error(error);
        });
    }
  }

  filterVoltageList(val: string) {
    return val ? this.voltageList.filter((s) => s.valueDesc.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.voltageList;
  }
  fetchVoltageList() {
    this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
      result => {
        this.voltageList = result;
      },

      error => {
        console.error('Error loading Voltages');
        console.error(error);
      });
  }

  // acChangeFunc(event, item, modelName:string){
  // if (event.source.selected) {
  //      this[modelName] = item;
  //   }
  // }

  onEdcChange() {
    console.log("In edc change");
    this.service.getEwaCompanyByEdc(this.searchSellerEndOrgId,this.searchSellerCompanyId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }

  searchResults() {

    this.service.getAllEwas(this.searchSellerEndOrgId, this.searchSellerCompanyServiceId,this.searchSellerCompanyId, this.searchStatus, this.searchEsIntentCode, this.searchEwaCode).subscribe(
      _ewas => {
        this.rows = _ewas;
        console.log(this.rows)
      });
  }



}
