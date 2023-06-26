import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { OaaService } from '../../../oaa/services/oaa.service';
// import { OaaEvent } from '../../../oaa/services/oaa.event';
import { Oaa } from './../../../vo/oaa';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from './../../../../shared/common/common.service';
import { SignupService } from './../../../../master/signup/services/signup.service';
import { id } from '@swimlane/ngx-datatable/release/utils';
import { DatePipe } from '@angular/common';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from "@angular/material";
import { EsIntentService } from './../../services/es-intent.service';
import { EsIntent ,EsIntentLine,Edc} from './../../../vo/es-intent';
import { element } from 'protractor';



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
  selector: 'es-intent-oaa-list',
  templateUrl: './es-intent-oaa-list.component.html',
  //styleUrls: [],
  providers: [OaaService, SignupService, MatDatepickerModule, MatNativeDateModule, DatePipe ,CommonUtils,EsIntentService,
    {
   provide: DateAdapter, useClass: AppDateAdapter
},
{
   provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}
]
})

export class EsIntentOaaListComponent implements OnInit {

  rows: Array<Oaa>;
  tempResults: Array<Oaa>;
  oaa: Oaa;
  esIntent:EsIntent;
  esIntentLines:Array<EsIntentLine>;
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
    // private oaEvent: OaaEvent,
    private datePipe: DatePipe,
    private esIntentService:  EsIntentService
  ) {
  
  }

  ngOnInit() {
    this.accessAddFlag = this.commonUtils.userHasAccess("OAA", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("OAA", "VIEW");
    this.accessApproveFlag=this.commonUtils.userHasAccess("OAA","APPROVE");
    this.accessCompleteFlag=this.commonUtils.userHasAccess("OAA","COMPLETE");
    this.rows = [];
    this.oaa = new Oaa();
    this.esIntent = new EsIntent();
    this.esIntentLines=[];

    if(this.route.params['_value']['_id']){
      //route.params['value'] will have the '_id' in it, during edit 
 
      this.route.params
            .switchMap((params: Params) => this.esIntentService.getEsIntentById(params['_id']))
            .subscribe((_esIntent: EsIntent) => {
                  this.esIntent = _esIntent;
                
                 this.esIntentLines = this.esIntent.energySaleIntentLines;
                 console.log(this.esIntentLines)
      
		
            });
    }

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
   
      this.router.navigateByUrl('es-intent/es-intent-oaa-detail/' + _id);
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
    console.log(this.esIntent.id);
    this.router.navigateByUrl('/es-intent/es-intent-oaa-list/'+this.esIntent.id);
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
              this.listOaas();
            }
          );
        }
      });

  }

  searchBuyerServiceId:string;


 


  addOaa(){

    this.esIntent.energySaleIntentLines.forEach(element=>{
      if(element.buyerCompanyServiceId == this.searchBuyerServiceId){
     
          this.oaa.sellerCompServiceId =this.esIntent.sellerCompanyServiceId;
          this.oaa.sellerCompServiceNumber=this.esIntent.sellerCompanyServiceNumber;
          this.oaa.sellerCompanyId =this.esIntent.sellerCompanyId;
          this.oaa.sellerCompanyName = this.esIntent.sellerCompanyName;
          this.oaa.sellerOrgId =this.esIntent.sellerEndOrgId;
          this.oaa.sellerOrgName=this.esIntent.sellerEndOrgName;
          this.oaa.fromDate=this.esIntent.fromDate;
          this.oaa.toDate=this.esIntent.toDate;
          this.oaa.sellerIsCaptive =this.esIntent.isCaptive;
          this.oaa.tEsIntentId =this.esIntent.id;
          this.oaa.flowTypeCode = this.esIntent.flowTypeCode;
          this.oaa.agreementPeriodCode =this.esIntent.agmtPeriodCode;
          this.oaa.sellerIsCaptive =this.esIntent.isCaptive;
          this.oaa.statusCode ="CREATED";

          this.oaa.buyerCompanyId =element.buyerCompanyId;
          this.oaa.buyerCompanyName =element.buyerCompanyName;
          this.oaa.buyerCompServiceId =element.buyerCompanyServiceId;
          this.oaa.buyerCompServiceNumber =element.buyerCompanyServiceNumber;
          this.oaa.buyerOrgId =element.buyerEndOrgId;
          this.oaa.buyerOrgName =element.buyerEndOrgName;
          this.oaa.sellerIsCaptive =element.isCaptive;
          if(element.ewaLineApprovedCapacity!=null){
              this.oaa.proposedTotalUnits =element.ewaLineApprovedCapacity;
              this.oaa.approvedTotalUnits =element.ewaLineApprovedCapacity;
          }
          this.formatChangesforDB();
      
          this.service.addOaa(this.oaa).subscribe(res=>{
            
            element.oaAgmtId = res;
            console.log(this.esIntent)
            this.formatChangesforEsintentDB();
            this.esIntentService.updateEsIntent(this.esIntent).subscribe(res=>{
              this.filterOaa(element.buyerCompanyServiceId);
            });
            
          });
       
      }

     
    });   
    

  
  }
  
  filterOaa(buyerCompanyServiceId:string){
    this.service.getAllOaas("", "", "", "","", "", this.searchBuyerServiceId, "", "", this.esIntent.sellerCompanyServiceId,"").subscribe(
      _oaas => {
        this.rows = _oaas;
      });
  }

  searchOaa(){
    this.service.getAllOaas("", "", "", "","", "", "", "", "",this.esIntent.sellerCompanyServiceId,"").subscribe(
      _oaas => {
        this.rows = _oaas;
      });
  }

  listEsIntent() {
    this.router.navigateByUrl('/es-intent/es-intent-list');
  }
  formatChangesforDB() {

    this.oaa.fromDate = this.datePipe.transform(this.oaa.fromDate, 'yyyy-MM-dd');
    this.oaa.toDate = this.datePipe.transform(this.oaa.toDate, 'yyyy-MM-dd');

  }
  formatChangesforEsintentDB() {

    this.esIntent.fromDate = this.datePipe.transform(this.esIntent.fromDate, 'yyyy-MM-dd');
    this.esIntent.toDate = this.datePipe.transform(this.esIntent.toDate, 'yyyy-MM-dd');

  }
  

 
}
