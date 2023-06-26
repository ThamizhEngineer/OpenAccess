import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/startWith';

import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule, FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';
//import { SessionStorage} from "angular2-localstorage/WebStorage";
import { CommonUtils } from '../../../../shared/common/common-utils';
//import { ConsentEvent } from './../../services/consent.event';
import { MeterReadingImportService } from './../../services/meter-reading-import.service';
import { CommonService } from './../../../../shared/common/common.service';
import { MeterReadingImport, MeterReadingImportLine } from './../../../vo/meter-reading-import';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { SignupService } from './../../../../master/signup/services/signup.service';

@Component({
  selector: 'meter-reading-import-detail',
  templateUrl: './meter-reading-import-detail.component.html',
  providers: [CommonService,CommonUtils, MeterReadingImportService, SignupService, MatDatepickerModule, MatNativeDateModule, DatePipe]
})

export class MeterReadingImportDetailComponent {

  meterReadingImport: MeterReadingImport;
  meterReadingImportLine: MeterReadingImportLine;

  meterReadingImportLineRowIndex: number; 
  sellerhtscNumber: any;
  sellerCompanyName: String; 
  lineValidationErrors: String="";

  addScreen: boolean = true;
  disableControls: boolean = true;
  firstDay: any;
  lastDay: any;
  importDate: any;

  edcId:string;
  // months=[];
  orgId = [];
  plantTypes = [];
  substationId = [];
  filteredEDCs = [];
  filteredCompanies = [];
  filteredSellerCompanyServices = [];
  sellerCompanyServices = [];
months=[];
  SourceCodeTypes = [
    { "key": "01", "name": "MDMS" },
    { "key": "02", "name": "UI" }
  ]

  monthsreading = [
    { "key": "01", "name": "January" },
    { "key": "02", "name": "February" },
    { "key": "03", "name": "March" },
    { "key": "04", "name": "April" },
    { "key": "05", "name": "May" },
    { "key": "06", "name": "June" },
    { "key": "07", "name": "July" },
    { "key": "08", "name": "August" },
    { "key": "09", "name": "September" },
    { "key": "10", "name": "October" },
    { "key": "11", "name": "November" },
    { "key": "12", "name": "December" }
  ]

  yearsreading = [
    { "key": "2016", "name": "2016" },
    { "key": "2017", "name": "2017" },
    { "key": "2018", "name": "2018" },
  ];

  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: MeterReadingImportService,
    private commonService: CommonService,
    private datePipe: DatePipe

  ) {

    this.fetchEDCs();
    //  this.fetchMonths()
    this.fetchSellerCompanyServices();
    this.months = this.commonService.fetchMonths();
  }

  ngOnInit() {
    this.meterReadingImport = new MeterReadingImport();
  
    this.meterReadingImportLine = new MeterReadingImportLine();

    this.initMeterReadingImportLines();
  
    // if (this.gs.orgId != "") {
    //   console.log("in edc select")
    //   this.searchOrgId = this.gs.orgId;
    //   this.disableEdc = true;
    // }
    console.log(this.route.params['_value']);
    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getMeterReadingImportById(this.route.params['_value']['_id']))
        .subscribe((_meterReadingImport: MeterReadingImport) => {
          //console.log(_meterReadingImport);
          this.meterReadingImport = _meterReadingImport;
          this.formatChangesforUI();
        });
    }

  }

  onMonthSelect()
  {
    
    this.meterReadingImportLine.readingMonthCode = this.meterReadingImport.readingMonthCode ;
  } 

  onYearSelect()
  {
    this.meterReadingImportLine.readingYear = this.meterReadingImport.readingYear ; 

  }


  //  filterMonths(val: string) {
  //     return val ? this.months.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.months;
  //   }

  filterEDCs(val: string) {

    return val ? this.orgId.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.orgId;
  }


  fetchEDCs() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgId = result;
      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }


  //  fetchMonths(){
  //   this.commonService.fetchMonths().subscribe(
  //     result => {this.months = result;
  //     },
  //     error => {
  //        console.error('Error loading months!');
  //        console.error(error);
  //     }
  //     );
  // }

  monthConvertor() {

    var monthStr = this.meterReadingImportLine.readingMonthCode;
    var yearStr = this.meterReadingImportLine.readingYear;
    var month = +monthStr;
    month = month - 1;
    var year = +yearStr;
    this.firstDay = new Date(year, month, 1);
    this.lastDay = new Date(year, month + 1, 0);
    this.meterReadingImportLine.initReadingDate = this.firstDay;
    this.meterReadingImportLine.finalReadingDate = this.lastDay;


  }

  saveMeterReadingImport() {
 
    console.log("this.meterReadingImport")
    console.log(this.meterReadingImport)
    console.log('In save' + this.addScreen);
   
      this.formatChangesforDB();
      if (this.addScreen) {
        this.addMeterReadingImport();
      }
      else {
        this.updateMeterReadingImport();
      }
    
    
  }


  formatChangesforLinesDB() {


    this.meterReadingImportLine.initReadingDate = this.datePipe.transform(this.meterReadingImportLine.initReadingDate, 'yyyy-MM-dd');
    this.meterReadingImportLine.finalReadingDate = this.datePipe.transform(this.meterReadingImportLine.finalReadingDate, 'yyyy-MM-dd');

  }

  formatChangesforUI() {
    this.meterReadingImport.importDate = (this.meterReadingImport.importDate) ? this.meterReadingImport.importDate.substr(0, 10) : "";

  }
  formatChangesforDB() {

    this.meterReadingImport.importDate = this.datePipe.transform(this.meterReadingImport.importDate, 'yyyy-MM-dd');
  }
  displayFn(value: any): string {
    return value && typeof value === 'object' ? value.name : value;
  }

  filterSellerCompanyServices(val: string) { 
    // this.sellerCompanyServices.forEach(s => {
    //   console.log(s.number)
    // });
    
    // as services strangely has null-serviceNumbers this is needed --> (s.number != null)
    return val ? this.sellerCompanyServices.filter((s) => ((s.number != null) && s.number.match(new RegExp(val, 'gi')))) : this.sellerCompanyServices;
  }

  fetchSellerCompanyServices() {
    this.edcId = CommonUtils.getLoginUserEDC();
    console.log( this.edcId)
    this.service.getSellerCompanyServices(this.edcId).subscribe(
      result => {
        this.sellerCompanyServices = result; 
        console.log(this.sellerCompanyServices)
      },
      error => {
        console.error('Error loading company!');
        console.error(error);
      });
  }

  onSellerChange() {
    //console.log("In onSellerChange") 
    var _sellerCompanyService = this.sellerCompanyServices 
       .filter((item) => ((item.number !=null )&& item.number == this.meterReadingImportLine.serviceNumber))[0];
    //console.log(_sellerCompanyService);
    if(_sellerCompanyService){
      this.meterReadingImportLine.meterNumber = _sellerCompanyService.meterNumber;
      this.meterReadingImportLine.mf = _sellerCompanyService.multiplicationFactor;
    }

  }


  addMeterReadingImport() { 
      this.importDate = new Date();
      this.meterReadingImport.importDate = this.importDate;
      this.meterReadingImport.mrSourceCode = "02";
      this.formatChangesforDB();
      this.service.addMeterReadingImport(this.meterReadingImport).subscribe(
        result => {
          // this.meterReadingImport.id = result;
          console.log(result);
          alert(result.message);
          this.listMeterReadingImport();
        },
        error => {
          console.error('Error adding Meter Reading Import!');
          console.error(error);
        }
      );
  }

  updateMeterReadingImport() {
    this.service.updateMeterReadingImport(this.meterReadingImport).subscribe(
      result => {
        this.listMeterReadingImport();
      },
      error => {
        console.error('Error updating meter reading import!');
        console.error(error);
      }
    );
    
  }

  processMeterReadingImport() {

    console.log("In Process Meter Reading")
    this.service.processMeterReadingImport(this.meterReadingImport.id).subscribe(
      result => {
        console.log(result);
        alert(result);
        this.listMeterReadingImport();
      },
      error => {
        console.error('Error in Process meter reading imports!');
        console.error(error);
      }
    );
  }


  listMeterReadingImport() {
    this.router.navigateByUrl('/meter-reading-import/meter-reading-import-list');
  }


  initMeterReadingImportLines() {
    this.meterReadingImportLine = new MeterReadingImportLine();
    this.meterReadingImportLine.readingMonthCode = this.meterReadingImport.readingMonthCode;
    this.meterReadingImportLine.readingYear = this.meterReadingImport.readingYear;
    this.meterReadingImportLineRowIndex = -1;
  }

  isLineDataValid(){
    this.lineValidationErrors = "";
   if(this.meterReadingImportLine.serviceNumber == null || this.meterReadingImportLine.serviceNumber ==""){
     this.lineValidationErrors +="Please choose a valid Service Number";
   }
   else if(this.meterReadingImportLine.meterNumber == null || this.meterReadingImportLine.meterNumber ==""){
     this.lineValidationErrors +="Please select a Service Number with valid Meter Number";
   }
   else if(this.meterReadingImportLine.mf == null || this.meterReadingImportLine.mf ==""){
     this.lineValidationErrors +="Please select a Service Number with valid mf";
  }
   else if(this.meterReadingImportLine.readingMonthCode == null || this.meterReadingImportLine.readingMonthCode ==""){
     this.lineValidationErrors +="Please select a valid Reading Month";
   }
   else if(this.meterReadingImportLine.readingYear == null || this.meterReadingImportLine.readingYear ==""){
     this.lineValidationErrors +="Please select a valid Reading Year";
   }
   if (this.lineValidationErrors==""){
     return true;
   }
   else
     return false;
 }

  addMeterReadingImportLines() {
    if(this.isLineDataValid()){
      if (!this.meterReadingImport.meterReadingImportLines) {
        this.meterReadingImport.meterReadingImportLines = [];
      }
      this.monthConvertor();
      this.formatChangesforLinesDB();
      this.meterReadingImport.meterReadingImportLines.push(Object.assign({}, this.meterReadingImportLine));

      this.initMeterReadingImportLines();

    }else{
      alert(this.lineValidationErrors)
    }
  }

  updateMeterReadingImportLines() {
    if(this.isLineDataValid()){
      var tempArray = [];
      this.monthConvertor();
      this.formatChangesforLinesDB();
      for (var index = 0; index < this.meterReadingImport.meterReadingImportLines.length; index++) {
        if (this.meterReadingImportLineRowIndex == index) {
          tempArray.push(Object.assign({}, this.meterReadingImportLine));
        }
        else {
          tempArray.push(this.meterReadingImport.meterReadingImportLines[index]);
        }

      }
      this.meterReadingImport.meterReadingImportLines = tempArray;
      this.initMeterReadingImportLines();

    }else{
      alert(this.lineValidationErrors)
    }
  }


  editMeterReadingImportLines(_index: number) {
    this.meterReadingImportLineRowIndex = _index;
    this.meterReadingImportLine = Object.assign({}, this.meterReadingImport.meterReadingImportLines[_index]);
  }

  deleteMeterReadingImportLines(_index: number) {
    this.meterReadingImport.meterReadingImportLines.splice(_index, 1);
  }

}