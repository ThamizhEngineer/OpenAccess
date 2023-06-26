import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CommonUtils } from '../../../../shared/common/common-utils';
import { ConsentService } from './../../services/consent.service';
import { Consent } from './../../../vo/consent';

import { CommonService } from './../../../../shared/common/common.service';
import { SignupService } from './../../../../master/signup/services/signup.service';


@Component({
  selector: 'consent-list',
  templateUrl: './consent-list.component.html',
  providers: [ConsentService, SignupService,CommonUtils]
})
export class ConsentListComponent implements OnInit {

  rows: Array<Consent>;
  tempResults: Array<Consent>;
  consent:Consent;

  searchBuyerEndOrgId: string = "";
  searchBuyerCompanyServiceNumber: string = "";
  searchBuyerCompanyName: string = "";
  searchSellerEndOrgId: string = "";
  searchSellerCompanyServiceNumber: string = "";
  searchSellerCompanyName: string = "";
  searchSellerCompanyId: string = "";
  searchBuyerVoltageCode: string = "";
  searchBuyerCompanyServiceId: string = "";
  searchStatus: string = "";
  searchEsIntentCode: string = "";
  searchConsentCode: string = "";
  companyServices = [];
  edcList = [];
  voltages = [];
  //for pagination
  count = 0;
  offset = 0;
  limit = 8;

  statuses = [
    { "key": "CREATED", "name": "CREATED" },
    { "key": "APPROVED", "name": "APPROVED" },
    { "key": "COMPLETED", "name": "COMPLETED" }
  ];
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  disableEdc: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: ConsentService,
    private commonService: CommonService,
    private signupService: SignupService
  ) {

  }

  ngOnInit() {
    this.consent =  new Consent();
    this.accessAddFlag = this.commonUtils.userHasAccess("CONSENT", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("CONSENT", "VIEW");
    this.rows = [];
    this.consent.sellerOrgId = CommonUtils.getLoginUserEDC(); 
    this.consent.sellerCompanyId = CommonUtils.getLoginUserCompanyId();

    console.log(this.consent.sellerOrgId);
    console.log(this.consent.sellerCompanyId);
   // this.consent.buyerOrgId = CommonUtils.getLoginUserDetails().substr(3,3); 
    if ((this.consent.sellerOrgId  != "") && isFinite(parseInt(this.consent.sellerOrgId )) &&  (this.consent.sellerCompanyId != "")) {
      this.searchSellerEndOrgId = this.consent.sellerOrgId;
      //this.searchBuyerEndOrgId = this.consent.buyerOrgId;
      this.searchSellerCompanyId = this.consent.sellerCompanyId;
       
      console.log(this.searchSellerEndOrgId);
      console.log(this.searchSellerCompanyServiceNumber);
      this.disableEdc = true;
      this.onEdcChange();
    }
      
    this.fetchOrgList();
    this.fetchVoltageList(); 

  }






  newConsent() {
    try {
      this.router.navigateByUrl('/consent/consent-new');
    }
    catch (e) {
      console.log(e);
    }

  }

  editConsent(_id: string) {
    try {
      this.router.navigateByUrl('/consent/consent-edit/' + _id);
    }
    catch (e) {
      console.log(e);
    }
  }


  searchResults() { 

     
    this.service.getAllConsents(this.searchBuyerEndOrgId, this.searchBuyerCompanyServiceNumber, this.searchBuyerCompanyName, this.searchSellerCompanyServiceNumber, this.searchSellerCompanyName,this.searchSellerCompanyId,this.searchBuyerVoltageCode, this.searchStatus, this.searchEsIntentCode, this.searchBuyerCompanyServiceId, this.searchConsentCode).subscribe(
      _consents => {
        this.rows = _consents;
          console.log("In search result")
          console.log(this.rows);
      });
  }




  fetchOrgList() {
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




  fetchVoltageList() {
    this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
      result => {
        this.voltages = result;
      },

      error => {
        console.error('Error loading Voltages');
        console.error(error);
      });
  }

  onEdcChange() {
    console.log("In edc change");
    this.service.getConsentCompanyByEdc("", this.searchSellerEndOrgId, this.searchSellerCompanyId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }
}
