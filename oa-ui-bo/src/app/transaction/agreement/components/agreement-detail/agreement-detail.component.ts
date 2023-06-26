import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/startWith';

import { Component, OnInit, HostBinding } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule, FormControl } from '@angular/forms';
import { CommonService } from '../../../../shared/common/common.service';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { AgreementService } from './../../services/agreement.service';
import { Agreement, AgreementLine } from './../../../vo/agreement';
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
  selector: 'agreement-detail',
  templateUrl: './agreement-detail.component.html',
  providers: [AgreementService, MatDatepickerModule,  MatNativeDateModule, DatePipe
    ,CommonService,CommonUtils,DatePipe ,CommonUtils,
    {
   provide: DateAdapter, useClass: AppDateAdapter
},
{
   provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}
]
})
  

export class AgreementDetailComponent {
  agreement: Agreement;
  agreementLine: AgreementLine;
  agreementLineRowIndex: number;
  addScreen: boolean = true;
  disableControls: boolean = false;
  enableProcess: boolean = false;
  enableSave: boolean = false;
  diableBuyerList:boolean=false;
  months = [];
  isCaptives = [
    { "key": "Y", "name": "Y - Captive" },
    { "key": "N", "name": "N - Third Party" }
  ];
  agmtPeriodCodes = [
    { "key": "STOA", "name": "STOA" },
    { "key": "MTOA", "name": "MTOA" },
    { "key": "LTOA", "name": "LTOA" }
  ]
  companyServices = [];
  buyerCompanyServices = [];
  intervalTypeCodes = [];
  edcList = [];
  fromDate: any;
  toDate: any;
  nocStatus: any;
  ewaStatus: string;
  mpFromConfig = { 'placeHolder': 'Duration-From', 'readonly': false };
  mpFromModel = { 'month': '', 'year': '' };
  mpToConfig = { 'placeHolder': 'Duration-To', 'readonly': false };
  mpToModel = { 'month': '', 'year': '' };

  isTransmissionLossess = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]

  isTransmissionCharg = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: AgreementService,
    private datePipe: DatePipe,
    private commonService: CommonService
  ) {
    this.fetchIntervalTypeCode();

  }

  ngOnInit() {
    this.agreement = new Agreement();
    this.agreementLine = new AgreementLine();



    this.commonService.fetchEDCs().subscribe(
      edc => {
        console.log("In page load edc")
        this.edcList = edc;

      }
    )
    this.months = this.commonService.fetchMonths();
    console.log(this.diableBuyerList)

    if (this.route.params['_value']['_id']) {
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getAgreementById(params['_id']))
        .subscribe((_agreement: Agreement) => {
          this.agreement = _agreement;
          console.log(this.agreement);
          this.onEdcChange();
          this.formatChangesforUI();
          if(this.agreement.agreementLines.length>0){
           
          }else{
            this.diableBuyerList=true;
          }
          console.log(this.diableBuyerList)
          // this.mpFromModel = {'month':this.agreement.fromMonth, 'year':this.agreement.fromYear};
          // this.mpToModel = {'month':this.agreement.toMonth, 'year':this.agreement.toYear};

        });
    }
  }

  onEdcChange() {
    console.log("In edc change");
    console.log(this.agreement.sellerOrgId)
    this.service.getSellers(this.agreement.sellerOrgId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices)

      }
    )
  }
  onServiceChange() {

    console.log("In service change");
    var sellerCompanyService = this.companyServices.filter((item) => item.id == this.agreement.sellerCompanyServiceId)[0];
    this.agreement.sellerCompanyId = sellerCompanyService.companyId;
    this.agreement.sellerCompanyName = sellerCompanyService.companyName;

  }
  onBuyerEdcChange() {
    console.log("In edc change");
    console.log(this.agreementLine.buyerOrgId);
    this.service.getBuyers(this.agreementLine.buyerOrgId).subscribe(
      _buyerCompanyServices => {
        this.buyerCompanyServices = _buyerCompanyServices;
        console.log(this.buyerCompanyServices);
      }
    )
  }

  onBuyerServiceChange() {
    console.log(this.buyerCompanyServices);
    console.log("In service change");
    var buyerCompanyService = this.buyerCompanyServices.filter((item) => item.id == this.agreementLine.buyerCompanyServiceId)[0];
    this.agreementLine.buyerCompanyId = buyerCompanyService.companyId;
    this.agreementLine.buyerCompanyName = buyerCompanyService.companyName;
    this.agreementLine.buyerCompanyServiceNumber = buyerCompanyService.number;

    //this.esIntent.sellerCompanyName= this.edcs.filter((item)=> item.id == this.esIntent.sellerCompanyServiceId)[0].companyName;
    console.log(this.agreementLine);


  }

  mpOnFromModelChange(e) {
    this.agreement.fromMonth = e.month;
    this.agreement.fromYear = e.year;

  }
  mpOnToModelChange(e) {
    this.agreement.toMonth = e.month;
    this.agreement.toYear = e.year;

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



  formatChangesforDB() {

    this.agreement.fromDate = this.datePipe.transform(this.agreement.fromDate, 'yyyy-MM-dd');
    this.agreement.toDate = this.datePipe.transform(this.agreement.toDate, 'yyyy-MM-dd');
    this.agreement.fromMonth = this.datePipe.transform(this.agreement.fromMonth, 'yyyy-MM-dd');
    this.agreement.fromYear = this.datePipe.transform(this.agreement.fromYear, 'yyyy-MM-dd');
    this.agreement.toMonth = this.datePipe.transform(this.agreement.toMonth, 'yyyy-MM-dd');
    this.agreement.toYear = this.datePipe.transform(this.agreement.toYear, 'yyyy-MM-dd');
    this.agreement.agreementDate = this.datePipe.transform(this.agreement.agreementDate, 'yyyy-MM-dd');
  }

  formatChangesforUI() {
    this.agreement.agreementDate = (this.agreement.agreementDate) ? this.agreement.agreementDate.substr(0, 10) : "";
    this.agreement.fromDate = (this.agreement.fromDate) ? this.agreement.fromDate.substr(0, 10) : "";
    this.agreement.toDate = (this.agreement.toDate) ? this.agreement.toDate.substr(0, 10) : "";
  }

  agreementSigned(){
    console.log(this.agreement)
    if(this.agreement.agreementDate!=null && this.agreement.agreementDate!=""){
      this.formatChangesforDB();
      this.service.agreementSigned(this.agreement).subscribe( result => {
        this.listAgreement();
      },
      error => {
        console.error('Error adding signed Agreement!');
        console.error(error);
        this.listAgreement();
      }
    );
    }else{
      alert("Please select Agreement Signed Date!!!")
    }
    
  
  }
  saveAgreement() {
    this.formatChangesforDB();
    console.log('In save' + this.addScreen);
    if (this.addScreen) {
      this.addAgreement();
    }
    else {
      this.updateAgreement();
    }
  }





  addAgreement() {
    this.agreement.statusCode = "CREATED";
    this.service.addAgreement(this.agreement).subscribe(
      result => {
        console.log(result);
        alert(result.message);
        this.listAgreement();
      },
      error => {
        console.error('Error adding Agreement!');
        console.error(error);
        this.listAgreement();
      }
    );
  }

  updateAgreement() {


    this.service.updateAgreement(this.agreement).subscribe(
      result => {
        this.listAgreement();
      },
      error => {
        console.error('Error updating Agreement!');
        console.error(error);
        this.listAgreement();
      }
    );
  }



  initAgreementLines() {
    // if(!this.generator.genUnits){
    //     this.generator.genUnits=[];
    //   }
    this.agreementLine = new AgreementLine();
    this.agreementLineRowIndex = -1;
  }
  addAgreementLine() {
    if (!this.agreement.agreementLines) {
      this.agreement.agreementLines = [];
    }
    this.agreement.agreementLines.push(Object.assign({}, this.agreementLine));
    this.initAgreementLines();
  }


  updateAgreementLine() {

    //this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
    // the above code wasnt updating a row in the table due to somebug
    // as a workaround, creating a new array with same rows except the edited row.
    var tempArray = [];
    for (var index = 0; index < this.agreement.agreementLines.length; index++) {
      if (this.agreementLineRowIndex == index) {
        tempArray.push(Object.assign({}, this.agreementLine));
      }
      else {
        tempArray.push(this.agreement.agreementLines[index]);
      }

    }
    this.agreement.agreementLines = tempArray;
    this.initAgreementLines();
  }


  editAgreementLine(_index: number) {
    this.agreementLineRowIndex = _index;
    var _agreementLine = Object.assign({}, this.agreement.agreementLines[_index]);

    this.agreementLine = _agreementLine;
    this.onBuyerEdcChange();
    console.log(this.agreementLine)

  }

  deleteAgreementLine(_index: number) {
    this.agreement.agreementLines.splice(_index, 1);
  }
  listAgreement() {
    this.router.navigateByUrl('/agreement/agreement-list');
  }


}