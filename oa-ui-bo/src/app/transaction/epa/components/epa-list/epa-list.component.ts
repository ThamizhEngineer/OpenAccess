import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CommonUtils } from '../../../../shared/common/common-utils';
import { EpaService } from './../../services/epa.service';
import { Epa } from './../../../vo/epa';

import { CommonService } from './../../../../shared/common/common.service'; 
import { SignupService } from './../../../../master/signup/services/signup.service';

@Component({
  selector: 'epa-list',
  templateUrl: './epa-list.component.html',
  providers: [EpaService, SignupService,CommonUtils]
})
export class EpaListComponent implements OnInit {
  epa:Epa;
  rows: Array<Epa>;
  tempResults: Array<Epa>;
  searchEsIntentCode: string = "";
  searchEpaCode: string = "";
  searchSellerEdcId: string = "";
  searchSellerCompanyId: string = "";
  searchVoltageCode: string = "";
  searchSellerCompanyServiceId: string = "";
  disableEdc: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
	private signupService:SignupService,
	private service: EpaService
  ) { 
	this.fetchOrgList();
	this.fetchVoltageList();
  }

  ngOnInit() {
    this.rows = [];
    this.epa= new Epa();
    this.epa.sellerOrgId = CommonUtils.getLoginUserDetails().substr(3,3); 
   
    if ((this.epa.sellerOrgId  != "") && isFinite(parseInt(this.epa.sellerOrgId ))) {
      this.searchSellerEdcId = this.epa.sellerOrgId;
      
      this.disableEdc = true;
      this.onEdcChange();
    }
  }

 

  fetchResults() {

    this.service.getEpas().subscribe(
      _ewas => {
  
        this.rows = _ewas;

      });
  }


  newEwa() {
    try {
      this.router.navigateByUrl('/epa/epa-new');
    }
    catch (e) {
      console.log(e);
    }

  }

  editEpa(_id: string) {
    try {
      this.router.navigateByUrl('/epa/epa-edit/' + _id);
    }
    catch (e) {
      console.log(e);
    }
  }
  searchResults(){

	this.service.getAllEpas(this.searchSellerEdcId,"",this.searchSellerCompanyServiceId,this.searchVoltageCode,this.searchEpaCode,this.searchEsIntentCode).subscribe(
      _ewas=>{
        this.rows = _ewas;
      });
  }
  
  orgList = [];
  voltageList = [];
  buyerCompanyServices = [];
  sellerCompanyServices=[];
  filteredOrgList = [];
  filteredVoltageList = [];
  filterOrgList(val: string) {
    return val ? this.orgList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.orgList;
  }
  
  fetchOrgList(){
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
	if(this.filteredOrgList.length == 1){
    this.commonService.getBuyerCompanyServicesByOrgId(this.filteredOrgList[0]['id']).subscribe(
      result => {
        this.buyerCompanyServices = result;
        console.log("fetchBuyerCompanyServices")
        console.log(result)

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
  
  acChangeFunc(event, item, modelName:string){
	if (event.source.selected) {
       this[modelName] = item;
    }
  }

  onEdcChange() {
    console.log("In edc change");
    this.service.getEpaCompanyByEdc(this.searchSellerEdcId,"").subscribe(
      _companyServices => {
        this.sellerCompanyServices = _companyServices;
     
      }
    )
  }
}
