import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { OaaEvent } from './../../services/oaa.event'; 
import { OaaService } from './../../services/oaa.service'; 
import { Oaa} from './../../../vo/oaa'; 
import { CommonService } from './../../../../shared/common/common.service'; 
import { DatePipe } from '@angular/common';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from "@angular/material";

export class AppDateAdapter extends NativeDateAdapter {
  parse(value: any): Date | null {
   if ((typeof value === 'string') && (value.indexOf('/') > -1)) {
     const str = value.split('/');
     const year = Number(str[2]);
     const month = Number(str[1]) - 1;
     const date = Number(str[0]);
     return new Date(year, month, date);
   }
   const timestamp = typeof value === 'number' ? value : Date.parse(value);
   return isNaN(timestamp) ? null : new Date(timestamp);
 }

  format(date: Date, displayFormat: Object): string {
      if (displayFormat == "input") {
          let day = date.getDate();
          let month = date.getMonth() + 1;
          let year = date.getFullYear();
          return this._to2digit(day) + '/' + this._to2digit(month) + '/' + year;
      } else {
          return date.toDateString();
      }
  }

  private _to2digit(n: number) {
      return ('00' + n).slice(-2);
  } 
}

export const APP_DATE_FORMATS =
{
  parse: {
      dateInput: {month: 'short', year: 'numeric', day: 'numeric'}
  },
  display: {
      // dateInput: { month: 'short', year: 'numeric', day: 'numeric' },
      dateInput: 'input',
      monthYearLabel: { month: 'short', year: 'numeric', day: 'numeric' },
      dateA11yLabel: {year: 'numeric', month: 'long', day: 'numeric'},
      monthYearA11yLabel: {year: 'numeric', month: 'long'},
  }
}



@Component({
  selector: 'oaa-detail',
  templateUrl: './oaa-detail.component.html',
 // styleUrls: [],
  providers: [OaaService,MatDatepickerModule, MatNativeDateModule, DatePipe ,CommonUtils,
    {
   provide: DateAdapter, useClass: AppDateAdapter
},
{
   provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}
]
})

export class OaaDetailComponent implements OnInit{ 

  oaa: Oaa;
  nextOaaStatus: string='Applied'; 
  nextAction: string='Apply'; 
  disableControls: boolean = true;
  isCaptives = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ];
  rows = [];
  intervalTypeCodes=[]
  rowIndex: number;
  addScreen: boolean = true;
  accessUpdateFlag:boolean = false;
  accessDeleteFlag:boolean = false;
  accessApproveFlag:boolean = false;
  accessCompleteFlag:boolean = false;
  accessProcessFlag:boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: OaaService,
    private commonService: CommonService,
	private oaEvent: OaaEvent
  ) {
  this.fetchVoltageList();
  this.fetchIntervalTypeCode();
  }

  ngOnInit() {
    this.accessUpdateFlag=this.commonUtils.userHasAccess("OAA","UPDATE");
    this.accessDeleteFlag=this.commonUtils.userHasAccess("OAA","DELETE");
    this.accessApproveFlag=this.commonUtils.userHasAccess("OAA","APPROVE");
    this.accessCompleteFlag=this.commonUtils.userHasAccess("OAA","COMPLETE");
    this.accessProcessFlag=this.commonUtils.userHasAccess("OAA","PROCESS");
	this.oaEvent.oaa$.subscribe(latestValue =>{ 
     this.oaa =  latestValue;
     this.setNextStatus(this.oaa.statusDesc);
    });
   this.oaEvent.load();

    if(this.route.params['_value']['_id']){
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
            .switchMap((params: Params) => this.service.getOaaById(params['_id']))
            .subscribe((_oaa: Oaa) => {
                  this.oaa = _oaa;
                  this.oaEvent.set(_oaa);
                  this.setNextStatus(this.oaa.statusDesc);
				  if(this.oaa.buyerOrgId)this.fetchBuyerSubstations(this.oaa.buyerOrgId);
				  if(this.oaa.sellerOrgId)this.fetchSellerSubstations(this.oaa.sellerOrgId);
		
            });
    }

  }
  fetchIntervalTypeCode() {
    this.commonService.fetchCodes('INTERVAL_TYPE_CODE').subscribe(
      result => {
        this.intervalTypeCodes = result;

      },

      error => {
        console.error('Error loading INTERVAL_TYPE_CODE');
        console.error(error);
      });
    }
    onAdd() {
      
       
        var total=0;
        if(this.oaa.intervalTypeCode=="01"){
          if(this.oaa.peakUnits=="" || this.oaa.peakUnits==null){
            this.oaa.peakUnits="0";
          }
          if(this.oaa.offPeakUnits=="" || this.oaa.offPeakUnits==null){
            this.oaa.offPeakUnits="0";
          }

          total = (parseInt((this.oaa.peakUnits)) + parseInt(this.oaa.offPeakUnits));
        }else if(this.oaa.intervalTypeCode=="02"){
          if(this.oaa.c1Units==null){
            this.oaa.c1Units="0";
          }
          if(this.oaa.c1Units==null){
            this.oaa.c2Units="0";
          }
          if( this.oaa.c1Units==null){
            this.oaa.c3Units="0";
          }
          if( this.oaa.c1Units==null){
            this.oaa.c4Units="0";
          }
          if( this.oaa.c1Units==null){
            this.oaa.c5Units="0";
          }
          console.log(this.oaa.c1Units+this.oaa.c2Units+this.oaa.c3Units+this.oaa.c4Units+this.oaa.c5Units);
          total = (parseInt(this.oaa.c1Units) + parseInt(this.oaa.c2Units) + parseInt(this.oaa.c3Units) + parseInt(this.oaa.c4Units) + parseInt(this.oaa.c5Units));
          
        }else if(this.oaa.intervalTypeCode=="04" ){
          if(this.oaa.sharePercent==""  || this.oaa.sharePercent==null){
            this.oaa.sharePercent="0";
          }
         total = parseFloat(this.oaa.sellerCapacity)/parseFloat(this.oaa.sharePercent);
         total = isFinite(total)?total:0;
      
        }
      
        this.oaa.approvedTotalUnits =total.toString();
       
      }
 setNextStatus(status: string){
    
    switch (status) {
        case 'Initiated':          
          this.nextOaaStatus = 'Applied';
          this.nextAction = 'Apply';
          break;
        case 'Applied':          
          this.nextOaaStatus = 'Approved';
          this.nextAction = 'Approve';
          break;
        case 'Approved':          
          this.nextOaaStatus = 'Activated';
          this.nextAction = 'Activate';
          break;
        default:
          break;
      }
    this.disableControls = (this.nextOaaStatus === '')?true:false;
 }

  saveOaa(status: string){
    //save can be add or update
    if(status != ''){
      this.oaa.statusDesc = status;
      var date = new Date();
      var dateString = date.getDate()+'-'+date.getMonth()+'-'+date.getFullYear();
      switch (status) {
        case 'Applied':          
			this.oaa.appliedDate = dateString;
			break;
        case 'Approved':          
			this.oaa.approvedDate = dateString;
			break;
      
        default:
          break;
      }
    }
	
	this.setDropdownData();

    if(this.oaa.id==''){
      this.addOaa();
    }
    else{
      this.updateOaa();
    }
  }

  addOaa(){
    
    this.service.addOaa(this.oaa).subscribe(
      result => { 
        
                console.log('Oaa(_id:'+result+') added');
                this.listOaas();
      },
      error => {
         console.error('Error adding OpenAccess Application!');
         console.error(error);
      }
      );
  }
  
  updateOaa(){

    console.log(this.oaa)
    this.service.updateOaa(this.oaa).subscribe(
      result => { 
                console.log('Oaa(_id:'+result+') updated');
                this.listOaas();
      },
      error => {
         console.error('Error updating Oaa!');
         console.error(error);
      }
      );
  }
  listOaas() {
      this.router.navigateByUrl('/oaa/oaa-list');
  }
  	
	      completeOaa() {
    this.service.completeOaa(this.oaa).subscribe(
      result => {
        this.listOaas();
      },
      error => {
        console.error('Error completing Oaa!');
        console.error(error);
      }
    );
  }

        approveOaa() {
    this.service.approveOaa(this.oaa).subscribe(
      result => {
        this.listOaas();
      },
      error => {
        console.error('Error approving oaas!');
        console.error(error);
      }
    );
  }

  acChangeFunc(event, item, modelName:string){
	if (event.source.selected) {
       this[modelName] = item;
    }
  }
  
  buyerSubStation:any;buyerDSS: any;
  buyerSubstationList= [];
  filterBuyerSubstations(val: string) {
    return val ? this.buyerSubstationList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.buyerSubstationList;
  }
  fetchBuyerSubstations(orgId:string) {
      this.commonService.fetchSubstationsByOrgId(orgId).subscribe(
      result => {
        this.buyerSubstationList = result;
      },
      error => {
        console.error('Error loading substaion!');
        console.error(error);
      });
  }
  
  buyerFeeder:any;
  buyerFeederList= [];
  filterBuyerFeeders(val: string) {
    return val ? this.buyerFeederList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.buyerFeederList;
  }
  fetchBuyerFeeders() {
    if(this.buyerSubStation.id){
      this.commonService.fetchFeeders(this.buyerSubStation.id).subscribe(
      result => {
        this.buyerFeederList = result;
      },
      error => {
        console.error('Error loading substaion!');
        console.error(error);
      });
	}
  }
  
  filteredOrgList = [];
  filteredVoltageList = [];
  voltageList = [];
  buyerTV:any; buyerDV:any; sellerTV:any; sellerDV:any;
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

  
  sellerSubStation:any; sellerDSS: any;
  sellerSubstationList= [];
  filterSellerSubstations(val: string) {
    return val ? this.sellerSubstationList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.sellerSubstationList;
  }
  fetchSellerSubstations(orgId:string) {
      this.commonService.fetchSubstationsByOrgId(orgId).subscribe(
      result => {
        this.sellerSubstationList = result;
      },
      error => {
        console.error('Error loading substaion!');
        console.error(error);
      });
  }
  
  sellerFeeder:any;
  sellerFeederList= [];
  filterSellerFeeders(val: string) {
    return val ? this.sellerFeederList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.sellerFeederList;
  }
  fetchSellerFeeders() {
    if(this.sellerSubStation.id){
      this.commonService.fetchFeeders(this.sellerSubStation.id).subscribe(
      result => {
        this.sellerFeederList = result;
      },
      error => {
        console.error('Error loading substaion!');
        console.error(error);
      });
	}
  }
  
  setDropdownData(){
	if(this.buyerSubStation && this.buyerSubStation.id) this.oaa.drawalTransSubstationId = this.buyerSubStation.id;
	if(this.buyerTV && this.buyerTV.valueCode) this.oaa.drawalTransVoltageCode = this.buyerTV.valueCode;
	if(this.buyerFeeder && this.buyerFeeder.code) this.oaa.drawalFeederId = this.buyerFeeder.code;
	if(this.buyerDSS && this.buyerDSS.id) this.oaa.drawalDistSubstationId = this.buyerDSS.id;
	if(this.buyerDV && this.buyerDV.valueCode) this.oaa.drawalDistVoltageCode = this.buyerDV.valueCode;
	
	if(this.sellerSubStation && this.sellerSubStation.id) this.oaa.injectionTransSubstationId = this.sellerSubStation.id;
	if(this.sellerTV && this.sellerTV.valueCode) this.oaa.injectionTransVoltageCode = this.sellerTV.valueCode;
	if(this.sellerFeeder && this.sellerFeeder.code) this.oaa.injectionFeederId = this.sellerFeeder.code;
	if(this.sellerDSS && this.sellerDSS.id) this.oaa.injectionDistSubstationId = this.sellerDSS.id;
	if(this.sellerDV && this.sellerDV.valueCode) this.oaa.injectionDistVoltageCode = this.sellerDV.valueCode;
	  
  }
  
}
