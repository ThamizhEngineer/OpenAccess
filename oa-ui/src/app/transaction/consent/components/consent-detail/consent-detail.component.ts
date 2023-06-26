import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
//import { SessionStorage} from "angular2-localstorage/WebStorage";
import { CommonUtils } from '../../../../shared/common/common-utils';
import { ConsentEvent } from './../../services/consent.event';
import { ConsentService } from './../../services/consent.service';
import { CommonService } from './../../../../shared/common/common.service';
import { OaaService } from '../../../oaa/services/oaa.service'; 
import { Consent } from './../../../vo/consent'; 

@Component({
  selector: 'consent-detail',
  templateUrl: './consent-detail.component.html',
  //styleUrls: [],
  providers: [ConsentService,CommonUtils,OaaService]
})
export class ConsentDetailComponent implements OnInit {

  consent: Consent;

  nextConsentStatus: string = 'Applied';
  nextAction: string = 'Apply';
  disableControls: boolean = true;

  addScreen: boolean = true;
  rows = [];

  exemptRc = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]
      hasDues = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]
 // rows = [{'serviceNumber':'', 'name':'', 'quantum':'', 'period':'', 'fromDate':'', 'toDate':''}]
  accessUpdateFlag:boolean = false;
  accessDeleteFlag:boolean = false;
  accessApproveFlag:boolean = false;
  accessCompleteFlag:boolean = false;
  accessProcessFlag:boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: ConsentService,
	  private commonService: CommonService,
    private consentEvent: ConsentEvent,
    private oaaService: OaaService
  ) { }

  ngOnInit() {
    this.accessUpdateFlag=this.commonUtils.userHasAccess("CONSENT","UPDATE");
    this.accessDeleteFlag=this.commonUtils.userHasAccess("CONSENT","DELETE");
    this.accessApproveFlag=this.commonUtils.userHasAccess("CONSENT","APPROVE");
    this.accessCompleteFlag=this.commonUtils.userHasAccess("CONSENT","COMPLETE");
    this.accessProcessFlag=this.commonUtils.userHasAccess("CONSENT","PROCESS");
    this.consentEvent.consent$.subscribe(latestValue => {
      this.consent = latestValue;
      this.setNextStatus(this.consent.statusDesc);
    });
    this.consentEvent.load();

    if (this.route.params['_value']['_id']) {
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getConsentById(params['_id']))
        .subscribe((_consent: Consent) => {
          this.consent = _consent;
          this.consentEvent.set(_consent);
          this.setNextStatus(this.consent.statusDesc);
		      this.fetchSubstations();
          this.fetchOaa();
        });
    }

  }

  fetchOaa(){
    console.log(this.consent.buyerCompServiceId);
    this.oaaService.getAllOaas("","","","","","",this.consent.buyerCompServiceId,"COMPLETED","","","").subscribe(
      result=>{
        this.rows = result});
        console.log(this.rows)  ; 
  }

  setNextStatus(status: string) {

    switch (status) {
      case 'Initiated':
        this.nextConsentStatus = 'Applied';
        this.nextAction = 'Apply';
        break;
      case 'Applied':
        this.nextConsentStatus = 'Approved';
        this.nextAction = 'Approve';
        break;
      case 'Approved':
        this.nextConsentStatus = '';
        this.nextAction = '';
        break;
      case 'Disapproved':
        this.nextConsentStatus = '';
        this.nextAction = '';
        break;

      default:
        break;
    }
    this.disableControls = (this.nextConsentStatus === '') ? true : false;

  }

  saveConsent(status: string) {
    //save can be add or update
    if (status != '') {
      this.consent.statusDesc = status;
      var date = new Date();
      var dateString = date.getDate() + '-' + date.getMonth() + '-' + date.getFullYear();
      switch (status) {
        case 'Applied':
          this.consent.appliedDate = dateString;
          break;
        case 'Approved':
          this.consent.approvedDate = dateString;
          break;
        case 'Disapproved':
          //this.consent.disapprovedDate = dateString;
          break;

        default:
          break;
      }
    }

    if (this.consent.id == '') {
      this.addConsent();
    }
    else {
      this.updateConsent();
    }
  }

  addConsent() {
    this.service.addConsent(this.consent).subscribe(
      result => {
        this.consent.id = result;
        this.listConsents();
      },
      error => {
        console.error('Error adding NOC Application!');
        console.error(error);
      }
    );
  }

  updateConsent() {
    this.service.updateConsent(this.consent).subscribe(
      result => {
        this.listConsents();
      },
      error => {
        console.error('Error updating Consent!');
        console.error(error);
      }
    );
  }
  listConsents() {
    // this.router.navigateByUrl('/consent/consent-list');
    this.router.navigateByUrl('/sale/list/CONSENT');
  }
  
      approveConsent() {
    this.service.approveConsent(this.consent).subscribe(
      result => {
        this.listConsents();
      },
      error => {
        console.error('Error approving consents!');
        console.error(error);
      }
    );
  }

  completeConsent() {
    this.service.completeConsent(this.consent).subscribe(
      result => {
        this.listConsents();
      },
      error => {
        console.error('Error approving consents!');
        console.error(error);
      }
    );
  }
	
  acChangeFunc(event, item, modelName:string){
	if (event.source.selected) {
       this[modelName] = item;
    }
  }
  
  subStation:any;
  substationList= [];
  filterSubstations(val: string) {
    return val ? this.substationList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.substationList;
  }
  fetchSubstations() {
    if(this.consent.buyerOrgId){
      this.commonService.fetchSubstationsByOrgId(this.consent.buyerOrgId).subscribe(
      result => {
        this.substationList = result;
      },
      error => {
        console.error('Error loading substaion!');
        console.error(error);
      });
	}
  }
  
  feeder:any;
  feederList= [];
  filterFeeders(val: string) {
    return val ? this.feederList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.feederList;
  }
  fetchFeeders() {
    if(this.subStation.id){
      this.commonService.fetchFeeders(this.subStation.id).subscribe(
      result => {
        this.feederList = result;
      },
      error => {
        console.error('Error loading substaion!');
        console.error(error);
      });
	}
  }
}
