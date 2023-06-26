import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
//import { SessionStorage} from "angular2-localstorage/WebStorage";
import { CommonUtils } from '../../../../shared/common/common-utils';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from "@angular/material";
import { DatePipe } from '@angular/common';
import { CompanyMeterChangeService } from './../../services/company-meter-change.service';
import { CommonService } from './../../../../shared/common/common.service';
import { OaaService } from '../../../oaa/services/oaa.service'; 
import { CompanyMeterChange } from './../../../vo/company-meter-change'; 

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
  selector: 'company-meter-change-detail',
  templateUrl: './company-meter-change-detail.component.html',
  //styleUrls: [],
  providers: [CompanyMeterChangeService,CommonUtils,OaaService,DatePipe, {
    provide: DateAdapter, useClass: AppDateAdapter
},
  {
    provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}]
})
export class CompanyMeterChangeDetailComponent implements OnInit {

    companyMeterChange: CompanyMeterChange;


  disableControls: boolean = true;
  orgList=[];
  companyServices=[];
  addScreen: boolean = true;
  rows = [];
  meterMake=[];
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
    private service: CompanyMeterChangeService,
	  private commonService: CommonService,
    private datePipe: DatePipe,
    private oaaService: OaaService
  ) { }

  ngOnInit() {
    this.companyMeterChange = new CompanyMeterChange();
    this.accessUpdateFlag=this.commonUtils.userHasAccess("AMENDMENT","UPDATE");
    this.accessDeleteFlag=this.commonUtils.userHasAccess("AMENDMENT","DELETE");
    this.accessApproveFlag=this.commonUtils.userHasAccess("AMENDMENT","APPROVE");
    this.accessCompleteFlag=this.commonUtils.userHasAccess("AMENDMENT","COMPLETE");
    this.accessProcessFlag=this.commonUtils.userHasAccess("AMENDMENT","PROCESS");

    this.fetchOrgList();
    this.fetchMeterMake()
    if (this.route.params['_value']['_id']) {
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getCompanyMeterChangeById(params['_id']))
        .subscribe((_companyMeterChange: CompanyMeterChange) => {
          this.companyMeterChange = _companyMeterChange;
          this.addScreen = false;
          this.onEdcChange();
          this.formatChangesforUI();
         });
    }

  }

  accuracyclass = [
    { "key": "0.2", "name": "0.2" },
    { "key": "0.5", "name": "0.5" }
  ]

  isCaptives = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]

  isABTMeter = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]
  isMeterNumberChanges=
  [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ];
  isMeterSetChanges=
  [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ];
  isModemNumberChanges=
  [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ];

 

  saveCompanyMeterChange(status: string) {
    //save can be add or update
    this.formatChangesforDB();

    if (this.addScreen) {
      this.addCompanyMeterChange();
    }
    else {
      this.updateCompanyMeterChange();
    }
  }

  addCompanyMeterChange() {
    this.companyMeterChange.oldMeterNumber= this.companyMeterChange.meterNumber ;
    this.companyMeterChange.oldMeterMakeCode=this.companyMeterChange.meterMakeCode;
    this.companyMeterChange.oldModemNumber=  this.companyMeterChange.modemNumber;
    this.companyMeterChange.oldAccuracyClassCode= this.companyMeterChange.accuracyClassCode;
    this.companyMeterChange.oldIsAbtMeter= this.companyMeterChange.isAbtMeter;
    this.companyMeterChange.oldMf= this.companyMeterChange.multiplicationFactor;
    this.companyMeterChange.oldMeterCt1= this.companyMeterChange.meterCt1;
    this.companyMeterChange.oldMeterCt2=this.companyMeterChange.meterCt2;
    this.companyMeterChange.oldMeterCt3= this.companyMeterChange.meterCt3;
    this.companyMeterChange.oldMeterPt1= this.companyMeterChange.meterPt1;
    this.companyMeterChange.oldMeterPt2=this.companyMeterChange.meterPt2 ;
    this.companyMeterChange.oldMeterPt3=this.companyMeterChange.meterPt3;
    console.log(this.companyMeterChange);
    this.service.addCompanyMeterChange(this.companyMeterChange).subscribe(
      result => {
     
        this.listCompanyMeterChange();
      },
      error => {
        console.error('Error adding CompanyMeterChange Application!');
        console.error(error);
      }
    );
  }
  formatChangesforUI() {
    this.companyMeterChange.meterChangeDate = (this.companyMeterChange.meterChangeDate) ? this.companyMeterChange.meterChangeDate.substr(0, 10) : "";
    
  }

  formatChangesforDB() {
   
    this.companyMeterChange.meterChangeDate = this.datePipe.transform(this.companyMeterChange.meterChangeDate, 'yyyy-MM-dd');
  }

  updateCompanyMeterChange() {
    this.service.updateCompanyMeterChange(this.companyMeterChange).subscribe(
      result => {
        this.listCompanyMeterChange();
      },
      error => {
        console.error('Error updating CompanyMeterChange!');
        console.error(error);
      }
    );
  }

  approveCompanyMeterChange(){
    this.service.approveCompanyMeterChange(this.companyMeterChange).subscribe(
      result => {
        this.listCompanyMeterChange();
      },
      error => {
        console.error('Error approving CompanyMeterChange!');
        console.error(error);
      }
    );
  }
  listCompanyMeterChange() {
    this.router.navigateByUrl('/company-meter-change/company-meter-change-list');
  }
  
//       approveConsent() {
//     this.service.approveConsent(this.companyMeterChange).subscribe(
//       result => {
//         this.listConsents();
//       },
//       error => {
//         console.error('Error approving consents!');
//         console.error(error);
//       }
//     );
//   }

//   completeConsent() {
//     this.service.completeConsent(this.companyMeterChange).subscribe(
//       result => {
//         this.listConsents();
//       },
//       error => {
//         console.error('Error approving consents!');
//         console.error(error);
//       }
//     );
//   }
	

filterOrgList(val: string) {
  return val ? this.orgList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.orgList;
}

onEdcChange(){
  console.log("In edc change");
  console.log(this.companyMeterChange.orgId)
  this.service.getSellers(this.companyMeterChange.orgId).subscribe(
    _companyServices=>{
      this.companyServices = _companyServices;
      console.log(this.companyServices);
    }
  )
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

fetchMeterMake() {
  this.commonService.fetchCodes('METER_MAKE_CODE').subscribe(
    result => {
      this.meterMake = result;
    },

    error => {
      console.error('Error loading MeterMake');
      console.error(error);
    });
}

  onServiceChange(){   
    console.log(this.companyServices);
    console.log("In service change");
    var companyService = this.companyServices.filter((item)=> item.id == this.companyMeterChange.serviceId)[0];
    this.companyMeterChange.companyId= companyService.companyId;
    this.companyMeterChange.companyName= companyService.companyName;
    this.companyMeterChange.substationName= companyService.substationName;
    this.companyMeterChange.feederName= companyService.feederName;
    this.companyMeterChange.meterNumber= companyService.meterNumber;
    this.companyMeterChange.meterMakeCode= companyService.meterMakeCode;
    this.companyMeterChange.modemNumber= companyService.modemNumber;
    this.companyMeterChange.accuracyClassCode= companyService.accuracyClassCode;
    this.companyMeterChange.isAbtMeter= companyService.isAbtMeter;
    this.companyMeterChange.multiplicationFactor= companyService.multiplicationFactor;
    this.companyMeterChange.meterCt1= companyService.meterCt1;
    this.companyMeterChange.meterCt2= companyService.meterCt2;
    this.companyMeterChange.meterCt3= companyService.meterCt3;
    this.companyMeterChange.meterPt1= companyService.meterPt1;
    this.companyMeterChange.meterPt2= companyService.meterPt2;
    this.companyMeterChange.meterPt3= companyService.meterPt3;
    //this.esIntent.sellerCompanyName= this.edcs.filter((item)=> item.id == this.esIntent.sellerCompanyServiceId)[0].companyName;
    console.log(this.companyMeterChange);
   
   
  }
  
}
