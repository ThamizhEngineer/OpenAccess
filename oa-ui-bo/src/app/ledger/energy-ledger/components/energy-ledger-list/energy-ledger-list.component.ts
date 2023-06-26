import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { Observable } from 'rxjs/Observable';
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from "@angular/material";
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';

import { CommonService } from '../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EnergyLedgerService } from './../../services/energy-ledger.service';
import { EnergyLedger } from './../../../vo/energy-ledger';



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
  selector: 'app-energy-ledger-list',
  templateUrl: './energy-ledger-list.component.html',
  //styleUrls: ['./trade-relationship-list.component.scss'],
  providers: [EnergyLedgerService, CommonService ,MatDatepickerModule, MatNativeDateModule, DatePipe,
    {
      provide: DateAdapter, useClass: AppDateAdapter
  },
  {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
  }
  ]
  })
  

export class EnergyLedgerListComponent implements OnInit {
  energyLedger: EnergyLedger;
  rows: Array<EnergyLedger>;
  searchOrgId: string = "";
  searchOrgCode: string = "";
  searchLedgerDate:string = "";
  searchMonth: string = "";
  searchYear: string = "";
  searchCompanyName: string = "";
  searchSellerCompanyServiceNumber: string = "";
  orgList = [];
  months = [];
  years:any[];
  disableEdc: boolean = false;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private datePipe: DatePipe,
    private service: EnergyLedgerService,
    private commonService: CommonService,
  ) {
    this.months = this.commonService.fetchMonths();
    this.rows = [];
    //  this.fetchResults();
  }

  ngOnInit() {
    this.rows = [];
    this.energyLedger = new EnergyLedger();
    this.fetchEDCs();
    this.years = this.commonService.fetchYearList();
    this.energyLedger.orgId = CommonUtils.getLoginUserEDC();
    if (this.energyLedger.orgId != "") {
      console.log("in edc select")
      this.searchOrgId = this.energyLedger.orgId;
      this.disableEdc = true;
    }
  }

  fetchEDCs() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }

  fetchResults() {

    console.log(this.searchLedgerDate);
    this.searchLedgerDate = this.datePipe.transform(this.searchLedgerDate, 'yyyy-MM-dd');
    console.log(this.searchLedgerDate);

    if (this.energyLedger.orgId != "") {
      this.searchOrgId = this.energyLedger.orgId;
    }
    this.service.getEnergyLedgers(this.searchOrgId, this.searchCompanyName, this.searchSellerCompanyServiceNumber,this.searchLedgerDate, this.searchMonth, this.searchYear).subscribe(
      _energyledgers => {

        this.rows = _energyledgers;
        console.log(this.rows)

      });
  }

  newEnergyLedger() {
    try {
      //this.router.navigate(['/feeder-new','_id', _id]);
      this.router.navigateByUrl('/energy-ledger/energy-ledger-new');
      // to-do
      // this still needs to be fixed as giving '/order' is unecefeederary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }

  editEnergyLedger(_id: string) {
    try {
      console.log("Edit id value" + _id);
      this.router.navigateByUrl('/energy-ledger/energy-ledger-edit/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecefeederary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }

  ///To allow number only
  numberFormat(event){
    this.commonService.numberOnly(event)
}

}
