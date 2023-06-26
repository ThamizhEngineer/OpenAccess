import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/startWith';

import { Component, OnInit, HostBinding } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule, FormControl } from '@angular/forms';
//import { CommonService } from '../../../../shared/common/common.service';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { CommonService } from './../../../../shared/common/common.service';

import { TradeRelationshipService } from './../../services/trade-relationship.service';
import { TradeRelationship } from './../../../vo/trade-relationship';


@Component({
  selector: 'trade-relationship-detail',
  templateUrl: './trade-relationship-detail.component.html',
  providers: [TradeRelationshipService, MatDatepickerModule,  MatNativeDateModule, DatePipe
    //,CommonService
  ]

})

export class TradeRelationshipDetailComponent {
  tradeRelationship: TradeRelationship;
  addScreen: boolean = true;
  disableControls: boolean = false;
  companies = [];
  validateQuantum:number;
  filteredCompanies = [];
  filteredServices = [];
  filteredSellerServices = [];

  buyerCompanyServices = [];
  sellerCompanyServices = [];
  intervalTypeCodeFlag:boolean=true;
  tradeRelationshipFlag:boolean=false;
  searchBuyerEndOrgId: string = "";
  searchBuyerCompanyServiceNumber: string = "";
  searchBuyerCompanyName: string = "";
  searchSellerEndOrgId: string = "";
  searchSellerCompanyServiceNumber: string = "";
  searchSellerCompanyName: string = "";

  statues = [
    { "key": "Active", "name": "Active" },
    { "key": "Established", "name": "Established" }

  ];
  buyerCompanyCode: String;
  buyerCompanyName: String;

  sellerCompanyCode: String;
  sellerCompanyName: String;
  compCtrl: FormControl;
  reactiveComp: any;
  store = [];
  companyServices = [];
  searchBuyerCompanyServiceId: string = "";
  searchSellerCompanyServiceId: string = "";
  edcList = [];
  ValidateQuantum: number;
  orgList = [];
  peakUnits: string;
  offPeakUnits: string;
  intervalTypeCodes=[]
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: TradeRelationshipService,
    private commonService: CommonService,
    private datePipe: DatePipe
    // private commonService :CommonService
  ) {

    //this.fetchServices();
    this.compCtrl = new FormControl({ companyCode: this.buyerCompanyCode, companyName: this.buyerCompanyName });

    this.fetchCompanies();
    this.fetchIntervalTypeCode();




    /*  
    .map(val => this.displayFn(val))
      .map(name => this.filterCompanies(name));
*/
  }

  displayFn(value: any): string {
    return value && typeof value === 'object' ? value.name : value;
  }




  ngOnInit() {
    this.tradeRelationship = new TradeRelationship();
    this.fetchOrgList();


    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      // this.fetchOrgList();
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getTradeRelationshipById(params['_id']))
        .subscribe((_tradeRelationship: TradeRelationship) => {
          //console.log(_tradeRelationship);
          this.tradeRelationship = _tradeRelationship;
          this.onBuyerOrgChange();
          this.onSellerOrgChange();
          
          this.buyerCompanyCode = this.tradeRelationship.buyerCompanyCode;
          this.buyerCompanyName = this.tradeRelationship.buyerCompanyName;
          this.formatChangesforUI();
        

        });
       
    }
  this.tradeRelationship.statusCode="Active";


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
    onIntervalTypeChange(){
      if(this.tradeRelationship.intervalTypeCode=='04'){
        this.intervalTypeCodeFlag=false;
      }else{
        this.intervalTypeCodeFlag=true;
      }
    }
  onAdd() {
  
   
    var total=0;
    if(this.tradeRelationship.intervalTypeCode=="01"){
      if(this.tradeRelationship.peakUnits==""){
        this.tradeRelationship.peakUnits="0";
      }
      if(this.tradeRelationship.offPeakUnits==""){
        this.tradeRelationship.offPeakUnits="0";
      }
      total = (parseFloat((this.tradeRelationship.peakUnits)) + parseFloat(this.tradeRelationship.offPeakUnits));
    }else if(this.tradeRelationship.intervalTypeCode=="02"){
      if(this.tradeRelationship.c1==""){
        this.tradeRelationship.c1="0";
      }
      if(this.tradeRelationship.c2==""){
        this.tradeRelationship.c2="0";
      }
      if(this.tradeRelationship.c3==""){
        this.tradeRelationship.c3="0";
      }
      if(this.tradeRelationship.c4==""){
        this.tradeRelationship.c4="0";
      }
      if(this.tradeRelationship.c5==""){
        this.tradeRelationship.c5="0";
      }
      total = (parseFloat(this.tradeRelationship.c1) + parseFloat(this.tradeRelationship.c2) + parseFloat(this.tradeRelationship.c3) + parseFloat(this.tradeRelationship.c4) + parseFloat(this.tradeRelationship.c5));
      if(parseInt(this.tradeRelationship.sanctionedCapacity)<(total*1000)){
        this.tradeRelationshipFlag=true;
    }
    }else if(this.tradeRelationship.intervalTypeCode=="04"){
      if(this.tradeRelationship.sharePercent==""){
        this.tradeRelationship.sharePercent="0";
      }
      total = parseFloat(this.tradeRelationship.sellerSanctionedCapacity) * parseFloat(this.tradeRelationship.sharePercent)/100;
    }
  
    this.tradeRelationship.quantum =total.toString();
   
  }

  filterBuyerCompanyServices(val: string) {



    return val ? this.buyerCompanyServices.filter((s) => s.companyName.match(new RegExp(val, 'gi'))) : this.buyerCompanyServices;
  }
  filterSellerCompanyServices(val: string) {


    return val ? this.sellerCompanyServices.filter((s) => s.companyName.match(new RegExp(val, 'gi'))) : this.sellerCompanyServices;
  }

  filterCompanies(val: string) {



    return val ? this.companies.filter((s) => s.companyName.match(new RegExp(val, 'gi'))) : this.companies;
  }

  fetchCompanies() {
    this.service.getCompanies().subscribe(
      result => {
        this.companies = result;
        console.log(this.companies);
        this.compCtrl = new FormControl({ companyCode: this.buyerCompanyCode, companyName: this.buyerCompanyName });
        this.reactiveComp = this.compCtrl.valueChanges.startWith(this.compCtrl.value)
          .map(val => this.displayFn(val))
          .map(companyName => this.filterCompanies(companyName));
      },
      error => {
        console.error('Error loading company!');
        console.error(error);
      });
  }


  fetchBuyerCompanyServices(orgId: string) {

    this.commonService.getBuyerCompanyServicesByOrgId(orgId).subscribe(
      result => {
        this.buyerCompanyServices = result;
        console.log("trade get buyer")
        console.log(this.buyerCompanyServices)
        // this.compCtrl = new FormControl({ companyCode: this.buyerCompanyCode, companyName: this.buyerCompanyName });
        // this.reactiveComp = this.compCtrl.valueChanges.startWith(this.compCtrl.value)
        //   .map(val => this.displayFn(val))
        //   .map(companyName => this.filterBuyerCompanyServices(companyName));
      
      if(this.tradeRelationship.id!=null){
        this.onConsumerChange();
      }
      },

      error => {
        console.error('Error loading company!');
        console.error(error);
      });
  }

  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.edcList = result;
        console.log(this.edcList)
      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }


  onBuyerOrgChange() {

    this.fetchBuyerCompanyServices(this.tradeRelationship.buyerOrgId);

    // this.tradeRelationship.buyerOrgId != "";
    console.log("In buyer edc change");
    //this.fetchOrgList();
    // console.log(this.edcList)
    // var localOrg = this.edcList.filter((_org) => _org.id == this.tradeRelationship.buyerOrgId)[0];
    // console.log("localOrg" + localOrg)
    // this.tradeRelationship.buyerOrgName = localOrg.code + localOrg.name;




  }


  onConsumerChange() {
    console.log("On consumer change")
    this.filteredServices = this.buyerCompanyServices
      .filter((item) => item.serviceId == this.tradeRelationship.buyerCompServiceId);
    // this.tradeRelationship.quantum = this.filteredServices[0].capacity;
    // this.ValidateQuantum = parseInt(this.tradeRelationship.quantum);
    this.tradeRelationship.buyerCompServiceNumber = this.filteredServices[0].serviceNumber;
    this.tradeRelationship.buyerCompanyCode = this.filteredServices[0].companyCode;
    this.tradeRelationship.buyerCompanyName = this.filteredServices[0].companyName;
    this.tradeRelationship.buyerCompanyId = this.filteredServices[0].companyId;
    this.tradeRelationship.buyerCompServiceId = this.filteredServices[0].serviceId;
    this.tradeRelationship.buyerCompanyId = this.filteredServices[0].companyId;
    this.tradeRelationship.sanctionedCapacity = this.filteredServices[0].capacity;
    this.validateQuantum = parseInt(this.tradeRelationship.sanctionedCapacity);
    console.log( this.tradeRelationship)
  }


  onSellerOrgChange() {



    this.fetchSellerCompanyServices(this.tradeRelationship.sellerOrgId);

    // this.tradeRelationship.sellerOrgId != "";
    // var localOrg = this.edcList.filter((_org) => _org.id == this.tradeRelationship.sellerOrgId)[0];

    // this.tradeRelationship.sellerOrgName = localOrg.code + localOrg.name;


  }


  onSellerConsumerChange() {
    console.log("On seller consumer change")
    this.filteredSellerServices = this.sellerCompanyServices
      .filter((item_) => item_.serviceId == this.tradeRelationship.sellerCompServiceId);
    // this.tradeRelationship.quantum = this.filteredSellerServices[0].capacity;
    // this.ValidateQuantum = parseInt(this.tradeRelationship.quantum);
    this.tradeRelationship.sellerCompServiceNumber = this.filteredSellerServices[0].serviceNumber;
    this.tradeRelationship.sellerCompanyCode = this.filteredSellerServices[0].companyCode;
    this.tradeRelationship.sellerCompanyName = this.filteredSellerServices[0].companyName;
    this.tradeRelationship.sellerCompanyId = this.filteredSellerServices[0].companyId;
    this.tradeRelationship.sellerCompServiceId = this.filteredSellerServices[0].serviceId;
    this.tradeRelationship.sellerCompanyId = this.filteredSellerServices[0].companyId;
    this.tradeRelationship.sellerSanctionedCapacity = this.filteredSellerServices[0].capacity;
  }

  fetchSellerCompanyServices(orgId: string) {

    this.commonService.getSellerCompanyServicesByOrgId(orgId).subscribe(
      result => {
        this.sellerCompanyServices = result;
        console.log("sellerCompanyServices")

        console.log(this.sellerCompanyServices)
        this.compCtrl = new FormControl({ companyCode: this.sellerCompanyCode, companyName: this.sellerCompanyName });
        this.reactiveComp = this.compCtrl.valueChanges.startWith(this.compCtrl.value)
          .map(val => this.displayFn(val))
          .map(companyName => this.filterBuyerCompanyServices(companyName));
          if(this.tradeRelationship.id!=null){
            this.onSellerConsumerChange();
          }
      },
      error => {
        console.error('Error loading company!');
        console.error(error);
      });
  }


  saveTradeRelationship() {
    this.formatChangesforDB();
    console.log(this.tradeRelationship.quantum);
    console.log(this.validateQuantum)
    if(parseInt(this.tradeRelationship.quantum)>this.validateQuantum){
      alert("Quantum cannot be more than Sanctioned Capacity")
    }else if(this.tradeRelationshipFlag){
      alert("Trade Relationship cannot be added !!!");
      this.tradeRelationshipFlag = false;
    }else{
     
      if (this.addScreen) {
        this.addTradeRelationship();
      }
      else {
        this.updateTradeRelationship();
      }
    }
   
  }

  formatChangesforDB() {

    this.tradeRelationship.fromDate = this.datePipe.transform(this.tradeRelationship.fromDate, 'yyyy-MM-dd');
    this.tradeRelationship.toDate = this.datePipe.transform(this.tradeRelationship.toDate, 'yyyy-MM-dd');
  }

  formatChangesforUI() {

    this.tradeRelationship.fromDate = (this.tradeRelationship.fromDate) ? this.tradeRelationship.fromDate.substr(0, 10) : "";
    this.tradeRelationship.toDate = (this.tradeRelationship.toDate) ? this.tradeRelationship.toDate.substr(0, 10) : "";
  }

  addTradeRelationship() {
    this.service.addTradeRelationship(this.tradeRelationship).subscribe(
      result => {
        // this.tradeRelationship.id = result;
        this.listTradeRelationship();
      },
      error => {
        console.error('Error adding TradeRelationship!');
        console.error(error);
        this.listTradeRelationship();
      }
    );
  }

  updateTradeRelationship() {


    this.service.updateTradeRelationship(this.tradeRelationship).subscribe(
      result => {
        this.listTradeRelationship();
      },
      error => {
        console.error('Error updating TradeRelationship!');
        console.error(error);
        this.listTradeRelationship();
      }
    );
  }


  deleteTradeRelationship() {
    this.service.deleteTradeRelationship(this.tradeRelationship).subscribe(
      result => {
        // this.tradeRelationship.id = result;
        this.listTradeRelationship();
      },
      error => {
        console.error('Error deleting TradeRelationship!');
        console.error(error);
        this.listTradeRelationship();
      }
    );
  }
  listTradeRelationship() {
    this.router.navigateByUrl('/trade-relationship/trade-relationship-list');
  }


}