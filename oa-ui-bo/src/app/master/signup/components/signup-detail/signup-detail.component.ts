import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/startWith';

import { Component, OnInit, HostBinding } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule, FormControl } from '@angular/forms';
import { CommonService } from '../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';
//import { DatePickerModule } from 'angular-material-datepicker';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { TradeRelationship } from './../../../vo/trade-relationship';
import { Generator } from './../../../vo/powerplant';
import { Org } from './../../../../master/vo/org';
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from "@angular/material";


import { SignupService } from './../../services/signup.service';
import { Signup } from './../../../vo/signup';

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
      dateInput: { month: 'short', year: 'numeric', day: 'numeric' }
    },
    display: {
      // dateInput: { month: 'short', year: 'numeric', day: 'numeric' },
      dateInput: 'input',
      monthYearLabel: { month: 'short', year: 'numeric', day: 'numeric' },
      dateA11yLabel: { year: 'numeric', month: 'long', day: 'numeric' },
      monthYearA11yLabel: { year: 'numeric', month: 'long' },
    }
  }


@Component({
  selector: 'signup-detail',
  templateUrl: './signup-detail.component.html',
  //styleUrls: [],
  providers: [ MatDatepickerModule, MatNativeDateModule, DatePipe, CommonUtils,
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})

export class SignupDetailComponent {

  signup: Signup;
  addScreen: boolean = true;

  // orgId = [];
  substationList = [];
  feederList = [];
  checkTradeRelationship: boolean = false;

  maxDate: Date = new Date();
  tradeRelationship: TradeRelationship = new TradeRelationship(); //TradeRelationship

  genUnit: Generator = new Generator();
  //this.signup.signupTradeRelationships: Array<TradeRelationship>= []; //TradeRelationship 
  accessUpdateFlag:boolean = false;
	accessDeleteFlag:boolean = false;
	accessCompleteFlag:boolean = false;

  
  tradeRelationshipRowIndex: number;
  genUnitRowIndex: number;

  ValidateQuantum: number;
  compCtrl: FormControl;
  buyerCompanyCode: String;
  buyerCompanyName: String;
  reactiveComp: any;
  disableControls: boolean = false;
  edc: Org;
  Purposes = [];
  mrtUserLogin: boolean = false;
  intervalTypeCodeFlag: boolean = true;
  tradeRelationshipFlag: boolean = false;
  intervalTypeCodes = []
  accuracyclass = [
    { "key": "0.2", "name": "0.2" },
    { "key": "0.5", "name": "0.5" }
  ]

  isCaptives = [
    { "key": "Y", "name": "IS-CAPTIVE" },
    { "key": "N", "name": "SELL-TO-BOARD" }
  ]

  surplusEnergys = [
    { "key": "BANKING", "name": "BANKING" },
    { "key": "SALETOBOARD", "name": "SELL-TO-BOARD" }
  ]
  acceleratedDepreciations = [
    { "key": "Y", "name": "YES" },
    { "key": "N", "name": "NO" }
  ]

  isABTMeter = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]

  isDLMSMeter = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]

  isRECs = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ];

  orgList = [];
  org = [];
  Voltages = [];
  State = [];
  District = [];
  Taluk = [];
  TalukList = [];
  edcPassTypes = [];
  generatorModelTypes = [];
  meterMake = [];
  fuelPlantTypes = [];
  powerPlantTypes = [];
  powerPlantClassTypes = [];
  // filteredEDCs = [];
  filteredSubstations = [];
  filterFeeder = [];
  substation = [];
  buyerTypes = ['Mere Parallel', 'Sale to Board', 'Captive Use', 'Third Party'];
  buyerCompanyServices = [];
  filteredServices = [];
  filteredEdcList = [];
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: SignupService,
    //private companyService: CompanyService,
    //private consentEvent: ConsentEvent
    private commonService: CommonService,
    private datePipe: DatePipe
    // public registrationDate:Date
  ) {


    this.compCtrl = new FormControl({ companyCode: this.buyerCompanyCode, companyName: this.buyerCompanyName });

    this.fetchBuyerCompanyServices("");

  }

  ngOnInit() {
  this.fetchCodes();
  this.fetchEDCs();
    this.signup = new Signup();
    this.signup.signupTradeRelationships = [];
    this.tradeRelationship = new TradeRelationship();

      
			this.accessUpdateFlag=this.commonUtils.userHasAccess("ENERGY-SALE","UPDATE");
			this.accessDeleteFlag=this.commonUtils.userHasAccess("ENERGY-SALE","DELETE");
			this.accessCompleteFlag=this.commonUtils.userHasAccess("ENERGY-SALE","COMPLETE");
			

    this.genUnit = new Generator();
    this.signup.purposeCode = '02';
    this.signup.orgId = CommonUtils.getLoginUserEDC();
    this.fetchSubstations(this.signup.orgId + "");
    if (this.route.params['value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params.switchMap((params: Params) => this.service.getSignupById(this.route.params['value']['_id']))
        .subscribe((_signup: Signup) => {
          this.signup = _signup;
   
          this.fetchSubstations(this.signup.orgId + "");
          this.fetchFeeders(this.signup.substationId);
          this.formatChangesforUI();
        });

    }
  if(CommonUtils.getLoginUserDetails() == 'NCESMRTTRVL' || CommonUtils.getLoginUserDetails() == 'NCESMRTUDML' ||CommonUtils.getLoginUserDetails() == 'NCESMRTMUPN')
  {
     this.mrtUserLogin = true ;
  } 

  }


  // filterEDCs(val: string) {

  //  return val ? this.orgId.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.orgId;
  // }

  fetchEDCs() {

    this.orgList = [];
    this.commonService.fetchEDCs().subscribe(
      result => {

        this.orgList = result;
        var org = new Org();
        org.id = "TNEB";
        org.code = 'STB';
        org.name = 'Sell To Board ';
        // this.orgList.includes((Object.assign({},{'TNEB' : org.id, 'STB': org.code, : }))) ;
        this.orgList.push(org);
        //   this.org.push((Object.assign({},{'TNEB' : org.id, 'STB': org.code, 'Sell To Board ': org.name}))) ;

       
      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }

  filterEDCList(val: string) {

    return val ? this.orgList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.orgList;
  }







  filterSubstations(val: string) {

    return val ? this.substationList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.substationList;
  }

  fetchSubstations(orgId: string) {
    if (this.filteredEdcList.length > 0) {
    
      this.signup.orgName = this.filteredEdcList
        .filter((item) => item.name)[0].name;
      this.signup.orgId = this.filteredEdcList
        .filter((item) => item.id)[0].id;
      orgId = this.signup.orgId;
    }
    if (orgId == "") {
      return false;
    }


    this.commonService.fetchSubstationsByOrgId(orgId).subscribe(
      result => {
        this.substationList = result;
   
      },
      error => {
        console.error('Error loading substaion!');
        console.error(error);
      });
  }



  filterFeeders(val: string) {

    return val ? this.feederList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.feederList;
  }

  fetchFeeders(substationId: string) {
    if (substationId == "") {
      return false;
    }
    this.commonService.fetchFeeders(substationId).subscribe(
      result => {
        this.feederList = result;
      },
      error => {
        console.error('Error loading feeders!');
        console.error(error);
      });
  }

  fetchCodes(){
	  this.commonService.fetchCodes().subscribe(
      result => {
        this.intervalTypeCodes = result.filter(x=>x.listCode == "INTERVAL_TYPE_CODE");
        this.TalukList = result.filter(x=>x.listCode == "TALUK_CODE");
        this.Purposes = result.filter(x=>x.listCode == "PURPOSE_CODE");
        this.Voltages = result.filter(x=>x.listCode == "VOLTAGE_CODE");
        this.State = result.filter(x=>x.listCode == "STATE_CODE");
        this.District = result.filter(x=>x.listCode == "DISTRICT_CODE");
        this.buyerTypes = result.filter(x=>x.listCode == "BUYER_TYPE_CODE");
        this.edcPassTypes = result.filter(x=>x.listCode == "WIND_PASS_CODE");
        this.meterMake = result.filter(x=>x.listCode == "METER_MAKE_CODE");
        this.generatorModelTypes = result.filter(x=>x.listCode == "GENERATOR_MAKE_CODE");
        this.fuelPlantTypes = result.filter(x=>x.listCode == "FUEL_TYPE_CODE");
        this.powerPlantClassTypes = result.filter(x=>x.listCode == "PLANT_CLASS_TYPE_CODE");
        this.powerPlantTypes = result.filter(x=>x.listCode == "PLANT_TYPE_CODE");
      })
  }

  onIntervalTypeChange() {
    if (this.tradeRelationship.intervalTypeCode == '04') {
      this.intervalTypeCodeFlag = false;
    } else {
      this.intervalTypeCodeFlag = true;
    }
  }
  saveSignup() {

    this.formatChangesforDB();

    if (this.addScreen) {
      this.addSignup();

    }
    else {
      this.updateSignup();

    }
    this.listSignup();

  }

  formatChangesforUI() {
    this.signup.registrationDate = (this.signup.registrationDate) ? this.signup.registrationDate.substr(0, 10) : "";
    this.signup.dateOfCommission = (this.signup.dateOfCommission) ? this.signup.dateOfCommission.substr(0, 10) : "";
    this.signup.applicationDate = (this.signup.applicationDate) ? this.signup.applicationDate.substr(0, 10) : "";
    this.signup.dateOfApproval = (this.signup.dateOfApproval) ? this.signup.dateOfApproval.substr(0, 10) : "";
    this.signup.createdDate = (this.signup.createdDate) ? this.signup.createdDate.substr(0, 10) : "";
    this.signup.modifiedDate = (this.signup.modifiedDate) ? this.signup.modifiedDate.substr(0, 10) : null;
  }

  formatChangesforDB() {
    this.signup.buyerTypeCode = '';
    this.signup.registrationDate = this.datePipe.transform(this.signup.registrationDate, 'yyyy-MM-dd');
    this.signup.dateOfCommission = this.datePipe.transform(this.signup.dateOfCommission, 'yyyy-MM-dd');
    this.signup.applicationDate = this.datePipe.transform(this.signup.applicationDate, 'yyyy-MM-dd');
    this.signup.dateOfApproval = this.datePipe.transform(this.signup.dateOfApproval, 'yyyy-MM-dd');
    this.tradeRelationship.fromDate = this.datePipe.transform(this.tradeRelationship.fromDate, 'yyyy-MM-dd');
    this.tradeRelationship.toDate = this.datePipe.transform(this.tradeRelationship.toDate, 'yyyy-MM-dd');
    this.signup.createdDate = this.datePipe.transform(this.signup.createdDate, 'yyyy-MM-dd');
    this.signup.modifiedDate = this.datePipe.transform(this.signup.modifiedDate, 'yyyy-MM-dd');
  }


  displayFn(value: any): string {
    return value && typeof value === 'object' ? value.name : value;
  }
  filterBuyerCompanyServices(val: string) {

    return val ? this.buyerCompanyServices.filter((s) => s.companyName.match(new RegExp(val, 'gi'))) : this.buyerCompanyServices;
  }

  fetchBuyerCompanyServices(orgId: string) {

    this.service.getBuyerCompanyServicesByOrgId(orgId).subscribe(
      result => {
        this.buyerCompanyServices = result;
        this.compCtrl = new FormControl({ companyCode: this.buyerCompanyCode, companyName: this.buyerCompanyName });
        this.reactiveComp = this.compCtrl.valueChanges.startWith(this.compCtrl.value)
          .map(val => this.displayFn(val))
          .map(companyName => this.filterBuyerCompanyServices(companyName));
        // if(this.signup.signupTradeRelationships!=null){
        //   this.onConsumerChange();
        // }
      },
      error => {
        console.error('Error loading company!');
        console.error(error);
      });
  }

  onBuyerChangeAsStb() {
    if (this.signup.isCaptive == "N") {
      this.tradeRelationship.buyerOrgId = "TNEB";
      this.onBuyerOrgChange();
    }

  }


  onBuyerOrgChange() {

    if (this.tradeRelationship.buyerOrgId == "TNEB") {

      this.buyerCompanyServices = [];
      this.buyerCompanyServices.push({ "companyName": "TANGEDCO", "id": "TNEB-" + this.signup.orgId });
      this.tradeRelationship.buyerCompServiceId = "TNEB-" + this.signup.orgId;
      this.tradeRelationship.buyerCompanyName = "TANGEDCO";
      this.tradeRelationship.buyerOrgName = "STB";
      this.tradeRelationship.buyerCompanyId = "TNEB" ;


    }
    else {

      this.fetchBuyerCompanyServices(this.tradeRelationship.buyerOrgId);

      if (this.tradeRelationship.buyerOrgId != "") {
        var localOrg = this.orgList.filter((_org) => _org.id == this.tradeRelationship.buyerOrgId)[0];

        this.tradeRelationship.buyerOrgName = localOrg.name;
        this.tradeRelationship.buyerOrgCode = localOrg.code;


      }
      else {
        this.tradeRelationship = new TradeRelationship();
      }

    }

  }
  onAdd() {

    var total = 0;
    if (this.tradeRelationship.intervalTypeCode == "01") {
      if (this.tradeRelationship.peakUnits == "") {
        this.tradeRelationship.peakUnits = "0";
      }
      if (this.tradeRelationship.offPeakUnits == "") {
        this.tradeRelationship.offPeakUnits = "0";
      }
      total = (parseFloat((this.tradeRelationship.peakUnits)) + parseFloat(this.tradeRelationship.offPeakUnits));
    } else if (this.tradeRelationship.intervalTypeCode == "02") {
      if (this.tradeRelationship.c1 == "") {
        this.tradeRelationship.c1 = "0";
      }
      if (this.tradeRelationship.c2 == "") {
        this.tradeRelationship.c2 = "0";
      }
      if (this.tradeRelationship.c3 == "") {
        this.tradeRelationship.c3 = "0";
      }
      if (this.tradeRelationship.c4 == "") {
        this.tradeRelationship.c4 = "0";
      }
      if (this.tradeRelationship.c5 == "") {
        this.tradeRelationship.c5 = "0";
      }
      total = (parseFloat(this.tradeRelationship.c1) + parseFloat(this.tradeRelationship.c2) + parseFloat(this.tradeRelationship.c3) + parseFloat(this.tradeRelationship.c4) + parseFloat(this.tradeRelationship.c5));
      if (parseInt(this.tradeRelationship.sanctionedCapacity) < (total * 1000)) {
        this.tradeRelationshipFlag = true;
      }
    } else if (this.tradeRelationship.intervalTypeCode == "04") {
      if (this.tradeRelationship.sharePercent == "") {
        this.tradeRelationship.sharePercent = "0";
      }
      total = parseFloat(this.signup.totalCapacity) * parseFloat(this.tradeRelationship.sharePercent) / 100;
    }

    this.tradeRelationship.quantum = total.toString();


  }

  onConsumerChange() {

    this.filteredServices = this.buyerCompanyServices
      .filter((item) => item.id == this.tradeRelationship.buyerCompServiceId);
    this.tradeRelationship.sanctionedCapacity = this.filteredServices[0].capacity;
    this.ValidateQuantum = parseInt(this.tradeRelationship.sanctionedCapacity);
    this.tradeRelationship.buyerCompServiceNumber = this.filteredServices[0].number;
    this.tradeRelationship.buyerCompanyCode = this.filteredServices[0].companyCode;
    this.tradeRelationship.buyerCompanyName = this.filteredServices[0].companyName;
    this.tradeRelationship.buyerCompanyId = this.filteredServices[0].companyId;
    this.tradeRelationship.buyerCompServiceId = this.filteredServices[0].id;
    this.tradeRelationship.buyerCompanyId = this.filteredServices[0].companyId;
    // this.tradeRelationship.signupId = this.filteredServices[0].signupId;
  }


  onUpdateConsumerChange() {


    this.filteredServices = this.buyerCompanyServices
      .filter((item) => item.id == this.tradeRelationship.buyerCompServiceId);
    this.tradeRelationship.sanctionedCapacity = this.filteredServices[0].capacity;
    this.ValidateQuantum = parseInt(this.tradeRelationship.sanctionedCapacity);

  }

  addSignup() {
    this.formatChangesforDB();

    this.service.addSignup(this.signup).subscribe(
      result => {
        console.log(result);
        // this.signup.signupId = result;
        // this.listSignup();
      },
      error => {
        console.error('Error adding signup!');
        console.error(error);
      }
    );
  }


  updateSignup() {
    this.formatChangesforDB();
    this.service.updateSignup(this.signup).subscribe(
      result => {
        this.listSignup();
      },
      error => {
        console.error('Error updating signup!');
        console.error(error);
      }
    );
  }



  listSignup() {
    this.router.navigateByUrl('/signup/signup-list');
  }

  confirmComplete() {
    if (this.signup.signupTradeRelationships.length > 0) {
      this.completeSignup();
    } else {
      var checkComplete = confirm("You have not added any consumer!!! Do you wish to complete signup");
      if (checkComplete) {
        this.completeSignup();
      }
    }

  }
  completeSignup() {
    this.formatChangesforDB();


    this.service.completeSignup(this.signup).subscribe(
      result => {
        this.listSignup();
      },
      error => {
        console.error('Error updating signup!');
        console.error(error);
      }
    );
  }

  initBuyerCompany() {

    this.tradeRelationship = new TradeRelationship();
    this.tradeRelationshipRowIndex = -1;

  }
  checkDuplicateTradeRelationship() {
    this.signup.signupTradeRelationships.forEach(element => {
      if (this.tradeRelationship.buyerCompServiceId == element.buyerCompServiceId) {
        this.checkTradeRelationship = true;
      }

    })

  }
  addBuyerCompany() {
    this.formatChangesforDB();
    this.checkDuplicateTradeRelationship();
    if (this.checkTradeRelationship) {
      alert("Cannot add same consumer again !!!");
      this.checkTradeRelationship = false;

    } else if (this.tradeRelationshipFlag) {
      alert("Trade Relationship cannot be added !!!");
      this.tradeRelationshipFlag = false;
    } else {
      if (this.ValidateQuantum < parseInt(this.tradeRelationship.quantum)) {
        alert("Quantum cannot be more than Sanctioned Capacity");
      
      
      } else {
        if (!this.signup.signupTradeRelationships) {
          this.signup.signupTradeRelationships = [];
        }
 
        this.signup.signupTradeRelationships.push(Object.assign({}, this.tradeRelationship));

        this.initBuyerCompany();
      }
    }



  }


  updateBuyerCompany() {
    this.formatChangesforDB();
    //this.company.shareHolders[this.shareHolderRowIndex] = Object.assign({},this.shareHolder);
    if (this.tradeRelationshipFlag) {
      alert("Trade Relationship cannot be added !!!");
      this.tradeRelationshipFlag = false;
    } else if (parseInt(this.tradeRelationship.quantum) > this.ValidateQuantum) {
      alert("Quantum cannot be more than Sanctioned Capacity");
  

    } else {
      var tempArray = [];
      for (var index = 0; index < this.signup.signupTradeRelationships.length; index++) {


        if (this.tradeRelationshipRowIndex == index) {

     
     //     this.tradeRelationship.isCaptive = (this.tradeRelationship.isCaptive) ? "Y" : "N";
          tempArray.push(Object.assign({}, this.tradeRelationship));


        } else {
          tempArray.push(this.signup.signupTradeRelationships[index]);
        }
      }
      this.signup.signupTradeRelationships = tempArray;
      this.initBuyerCompany();
    }
  }


  editBuyerCompany(_index: number) {
    this.tradeRelationshipRowIndex = _index;
    this.tradeRelationship = Object.assign({}, this.signup.signupTradeRelationships[_index]);
    this.fetchBuyerCompanyServices(this.tradeRelationship.buyerOrgId);
    this.onUpdateConsumerChange();


  }

  deleteBuyerCompany(_index: number) {
    this.signup.signupTradeRelationships.splice(_index, 1);
  }

  initGenUnits() {
    if (!this.signup.genUnits) {
      this.signup.genUnits = [];
    }

    this.genUnit = new Generator();
    this.genUnitRowIndex = -1;
  }


  addGenUnit() {
    if (!this.signup.genUnits) {
      this.signup.genUnits = [];
    }

    this.signup.genUnits.push(Object.assign({}, this.genUnit));
 
    this.initGenUnits();
  }

  updateGenUnit() {

    //this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
    // the above code wasnt updating a row in the table due to somebug
    // as a workaround, creating a new array with same rows except the edited row.
    var tempArray = [];
    for (var index = 0; index < this.signup.genUnits.length; index++) {
      if (this.genUnitRowIndex == index) {
        tempArray.push(Object.assign({}, this.genUnit));

      }
      else {
        tempArray.push(this.signup.genUnits[index]);
      }

    }
    this.signup.genUnits = tempArray;
  
    this.initGenUnits();
  }


  editGenUnit(_index: number) {
    this.genUnitRowIndex = _index;
    this.genUnit = Object.assign({}, this.signup.genUnits[_index]);
  }

  deleteGenUnit(_index: number) {
    this.signup.genUnits.splice(_index, 1);
  }

}