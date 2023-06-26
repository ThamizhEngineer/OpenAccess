import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CommonUtils } from '../../../../shared/common/common-utils';
import {CompanyMeterChangeService} from './../../services/company-meter-change.service';
import { CompanyMeterChange } from './../../../vo/company-meter-change';

import { CommonService } from './../../../../shared/common/common.service';
import { SignupService } from './../../../../master/signup/services/signup.service';


@Component({
  selector: 'company-meter-change-list',
  templateUrl: './company-meter-change-list.component.html',
  providers: [CompanyMeterChangeService, SignupService,CommonUtils]
})
export class CompanyMeterChangeListComponent implements OnInit {

  rows: Array<CompanyMeterChange>;
  tempResults: Array<CompanyMeterChange>;
  companyMeterChange:CompanyMeterChange;

  searchBuyerEndOrgId: string = "";
  searchBuyerCompanyServiceNumber: string = "";
  searchBuyerCompanyName: string = "";
  searchSellerEndOrgId: string = "";
  searchSellerCompanyServiceNumber: string = "";
  searchSellerCompanyName: string = "";
  searchBuyerVoltageCode: string = "";
  searchBuyerCompanyServiceId: string = "";
  searchStatus: string = "";
  searchEsIntentCode: string = "";
  searchConsentCode: string = "";
  companyServices = [];
  searchCompanyServiceId:string="";
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
    private service: CompanyMeterChangeService,
    private commonService: CommonService,
    private signupService: SignupService
  ) {

  }

  ngOnInit() {
    this.companyMeterChange =  new CompanyMeterChange();
    this.accessAddFlag = this.commonUtils.userHasAccess("AMENDMENT", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("AMENDMENT", "VIEW");
    this.rows = [];
    this.companyMeterChange.orgId = CommonUtils.getLoginUserDetails().substr(3,3); 

    if (( this.companyMeterChange.orgId  != "") && isFinite(parseInt( this.companyMeterChange.orgId )) ) {
      this.searchSellerEndOrgId =  this.companyMeterChange.orgId;
     
      this.disableEdc = true;
      this.onEdcChange();
    }
      
    this.fetchOrgList();
    this.fetchVoltageList();
  }






  newCompanyMeterChange() {
    try {
      this.router.navigateByUrl('/company-meter-change/company-meter-change-new');
    }
    catch (e) {
      console.log(e);
    }

  }

  editCompanyMeterChange(_id: string) {
    try {
      this.router.navigateByUrl('/company-meter-change/company-meter-change-edit/' + _id);
    }
    catch (e) {
      console.log(e);
    }
  }


  searchResults() {

    console.log(this.searchBuyerCompanyServiceId)
    this.service.getAllCompanyMeterChanges( this.searchSellerEndOrgId,this.searchCompanyServiceId).subscribe(
      _cmc => {
        this.rows = _cmc;
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
    this.service.getCompanyMeterChangeByEdc(this.searchSellerEndOrgId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }
}
