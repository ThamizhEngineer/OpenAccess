import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { OaaService } from './../../services/oaa.service';
import { OaaEvent } from './../../services/oaa.event';
import { Oaa } from './../../../vo/oaa';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from './../../../../shared/common/common.service';
import { SignupService } from './../../../../master/signup/services/signup.service';
import { id } from '@swimlane/ngx-datatable/release/utils';
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
  selector: 'oaa-list',
  templateUrl: './oaa-list.component.html',
  //styleUrls: [],
  providers: [OaaService, SignupService, MatDatepickerModule, MatNativeDateModule, DatePipe ,CommonUtils,
    {
   provide: DateAdapter, useClass: AppDateAdapter
},
{
   provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}
]
})

export class OaaListComponent implements OnInit {

  rows: Array<Oaa>;
  tempResults: Array<Oaa>;
  oaa: Oaa;
  //for pagination
  count = 0;
  offset = 0;
  limit = 8;

  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  accessApproveFlag:boolean = false;
  accessCompleteFlag:boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: OaaService,
    private commonService: CommonService,
    private signupService: SignupService,
    private oaEvent: OaaEvent,
    private datePipe: DatePipe
  ) {
    this.fetchOrgList();
  }

  ngOnInit() {
    this.accessAddFlag = this.commonUtils.userHasAccess("OAA", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("OAA", "VIEW");
    this.accessApproveFlag=this.commonUtils.userHasAccess("OAA","APPROVE");
    this.accessCompleteFlag=this.commonUtils.userHasAccess("OAA","COMPLETE");
    this.rows = [];
    this.oaa = new Oaa();

  }

  fetchResults() {
    this.service.getOaas().subscribe(
      _oaas => {
        this.rows = _oaas;
      });
  }

  newOaa() {
    try {
      this.router.navigateByUrl('/oaa/oaa-new');
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }

  editOaa(_id: string) {
    try {
      console.log(_id);
      this.router.navigateByUrl('/oaa/oaa-edit/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }

  onClick(event) {
    console.log(event.currentTarget.attributes.id);
    var target = event.currentTarget;
    var idAttr = target.attributes.id;
    var value = idAttr.nodeValue;
    console.log(value);
    console.log(document.getElementById(value))
    document.getElementById(value).style.visibility = 'hidden';
  }

  updateOaa() {

    console.log(this.oaa)
    this.service.updateOaa(this.oaa).subscribe(
      result => {
        console.log('Oaa(_id:' + result + ') updated');
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

  approve(id: string) {
    console.log(id);
    this.service.getOaaById(id).subscribe(
      result => {
        this.oaa = result;
        console.log(this.oaa);

        if (this.oaa != null) {
          this.service.approveOaa(this.oaa).subscribe(
            oaa => {
              this.listOaas();
            },
            error => {
              console.error('Error approving oaas!');
              console.error(error);
            }
          );
        }
      });

  }


  complete(id: string) {

    console.log(id);
    this.service.getOaaById(id).subscribe(
      result => {
        this.oaa = result
        console.log(this.oaa);

        if (this.oaa != null) {
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
      });

  }
  searchSeller:any;
  searchSellerName:string;
  searchBuyer: any;
  searchEDC: any;
  searchFromDate: any;
  searchToDate: any;
  searchOaaCode: string;
  searchEsIntentCode: string;
  searchResults() {
    var edc = this.searchEDC && this.searchEDC.code ? this.searchEDC.code : '';
    // var buyer = this.searchBuyer && this.searchBuyer.id ? this.searchBuyer.id : '';
    var seller = this.searchSeller && this.searchSeller.id ? this.searchSeller.id : '';
    var fromDate = this.searchFromDate ? this.searchFromDate : '';
    var toDate = this.searchToDate ? this.searchToDate : '';
    this.service.getAllOaas("", "", fromDate, toDate, this.searchOaaCode, this.searchEsIntentCode, "", "", "", "",this.searchSellerName).subscribe(
      _oaas => {
        this.rows = _oaas;
      });
  }

  orgList = [];
  voltageList = [];
  buyerCompanyServices = [];
  sellerCompanyServices = [];
  filteredOrgList = [];
  filteredVoltageList = [];
  filterOrgList(val: string) {
    return val ? this.orgList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.orgList;
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

  filterBuyerList(val: string) {
    return val ? this.buyerCompanyServices.filter((s) => s.companyName.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.buyerCompanyServices;
  }
  fetchBuyerCompanyServices() {
    if (this.filteredOrgList.length == 1) {
      this.commonService.getBuyerCompanyServicesByOrgId(this.filteredOrgList[0]['id']).subscribe(
        result => {
          this.buyerCompanyServices = result;
          console.log("buyerCompanyServices")
          console.log(this.buyerCompanyServices)
        },
        error => {
          console.error('Error loading company!');
          console.error(error);
        });
    }
  }
 
  filterSellerList(val: string) {
    return val ? this.sellerCompanyServices.filter((s) => s.companyName.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.sellerCompanyServices;
  }
  fetchSellerCompanyServices() {
    if (this.filteredOrgList.length == 1) {
      this.commonService.getSellerCompanyServicesByOrgId(this.filteredOrgList[0]['id']).subscribe(
        result => {
          this.sellerCompanyServices = result;
          console.log("sellerCompanyServices")
          console.log(this.sellerCompanyServices)
        },
        error => {
          console.error('Error loading company!');
          console.error(error);
        });
    }
  }

  

  acChangeFunc(event, item, modelName: string) {
    if (event.source.selected) {
      this[modelName] = item;
    }
  }
}
