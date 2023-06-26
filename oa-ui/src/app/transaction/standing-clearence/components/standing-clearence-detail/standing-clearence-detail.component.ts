import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
//import { SessionStorage} from "angular2-localstorage/WebStorage";
import { CommonUtils } from '../../../../shared/common/common-utils';
//import { ConsentEvent } from './../../services/consent.event';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { StandingClearenceService } from './../../services/standing-clearence.service';
import { CommonService } from './../../../../shared/common/common.service';
import { StandingClearence } from './../../../vo/standing-clearence';
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
    selector: 'standing-clearence-detail',
    templateUrl: './standing-clearence-detail.component.html',
    providers: [CommonService, StandingClearenceService, MatDatepickerModule, MatNativeDateModule, DatePipe ,CommonUtils,
        {
       provide: DateAdapter, useClass: AppDateAdapter
    },
    {
       provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
    })


export class StandingClearenceDetailComponent  implements OnInit{
    addScreen: boolean = true;
    standingClearence: StandingClearence;
    Voltages = [];
    statetransloss:any;
   // substationId = [];
   // feederId = [];
    disableControls: boolean = true;
    buyerCompanyServices = [];
    edcList = [];
    nextAction: string = 'Apply';
    
    isTransmissionLossess = [
        { "key": "Y", "name": "Y" },
        { "key": "N", "name": "N" }
    ]

    isTransmissionCharg = [
        { "key": "Y", "name": "Y" },
        { "key": "N", "name": "N" }
    ]

    mpFromConfig = { 'placeHolder': 'Duration-From', 'readonly': false };
    mpFromModel = { 'month': '', 'year': '' };
    mpToConfig = { 'placeHolder': 'Duration-To', 'readonly': false };
    mpToModel = { 'month': '', 'year': '' };

    accessUpdateFlag:boolean = false;
    accessDeleteFlag:boolean = false;
    accessApproveFlag:boolean = false;
    accessCompleteFlag:boolean = false;
    accessProcessFlag:boolean = false;
    constructor(
      private commonUtils: CommonUtils,
      private route: ActivatedRoute,
        private router: Router,
        private commonService: CommonService,
        private datePipe: DatePipe,
        private service: StandingClearenceService


    ) {

        this.fetchVoltages();

    }

    ngOnInit() {
        this.standingClearence = new StandingClearence();
        this.accessUpdateFlag=this.commonUtils.userHasAccess("CONSENT","UPDATE");
        this.accessDeleteFlag=this.commonUtils.userHasAccess("CONSENT","DELETE");
        this.accessApproveFlag=this.commonUtils.userHasAccess("CONSENT","APPROVE");
        this.accessCompleteFlag=this.commonUtils.userHasAccess("CONSENT","COMPLETE");
        this.accessProcessFlag=this.commonUtils.userHasAccess("CONSENT","PROCESS");
        this.commonService.fetchEDCs().subscribe(
            edc => {
                console.log("In page load edc")
                this.edcList = edc;
                console.log(this.edcList)
            });
        console.log(this.route.params['_value']);
        if (this.route.params['_value']['_id']) {
            //route.params['value'] will have the '_id' in it, during edit 
            this.addScreen = false;
            this.route.params
                .switchMap((params: Params) => this.service.getStandingClearenceById(this.route.params['_value']['_id']))
                .subscribe((_standingClearence: StandingClearence) => {
                    console.log(_standingClearence);
                    this.standingClearence = _standingClearence;
               //     this.fetchSubstations();
                      this.formatChangesforUI();
                    // this.mpFromModel = {'month':this.noc.fromMonth, 'year':this.noc.fromYear};
                    // this.mpToModel = {'month':this.noc.toMonth, 'year':this.noc.toYear};
                });
        }

    }
     formatChangesforUI() {
        this.standingClearence.fromDate= ( this.standingClearence.fromDate) ?  this.standingClearence.fromDate.substr(0, 10) : "";
         this.standingClearence.toDate= ( this.standingClearence.toDate) ? this.standingClearence.toDate.substr(0, 10) : "";
         this.standingClearence.approvalDate= ( this.standingClearence.approvalDate) ? this.standingClearence.approvalDate.substr(0, 10) : "";
         this.standingClearence.createdDate= ( this.standingClearence.createdDate) ? this.standingClearence.createdDate.substr(0, 10) : "";
       }
    fetchVoltages() {
        this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
            result => {
                this.Voltages = result;
            },

            error => {
                console.error('Error loading Voltages');
                console.error(error);
            });
    }

    initStandingClearence() {
        // if(!this.generator.genUnits){
        //     this.generator.genUnits=[];
        //   }
        this.standingClearence = new StandingClearence();
    }
     
    onTransmissionLoss()
    {
        var statetransloss=0;
        console.log('on trans loss');
        if(this.standingClearence.maxDrawalCeiling==null){
            this.standingClearence.maxDrawalCeiling="0";
         }
      if(this.standingClearence.isTransmissionLoss == 'Y'){
          console.log(this.standingClearence.maxDrawalCeiling)
          console.log(this.standingClearence.stateTransLossPercent) 
          if(this.standingClearence.stateTransLossPercent==null){
            this.standingClearence.stateTransLossPercent="0.0307";
          }
          if(this.standingClearence.stateTransChargesPercent==null){
            this.standingClearence.stateTransChargesPercent="126.55";
          }
          if(this.standingClearence.schedulingChargesPercent==null){
            this.standingClearence.schedulingChargesPercent="160";
          }
          if(this.standingClearence.systemoprChargesPercent==null){
            this.standingClearence.systemoprChargesPercent="33.84";
          }
         
      statetransloss= parseFloat(this.standingClearence.maxDrawalCeiling)*parseFloat(this.standingClearence.stateTransLossPercent);

      this.standingClearence.stateTransLoss= statetransloss.toString();
      console.log(this.standingClearence.maxDrawalCeiling);
    
    }else{
        if(this.standingClearence.maxDrawalCeiling==null){
            this.standingClearence.systemoprChargesPercent="0";
        }
    }
    }
    onTransmissionCharges()
    {
        var statetranscharges=0;
        var schedulingcharges=0;
        var systemoprcharges=0;
        console.log('on trans charges');
        if(this.standingClearence.isTransmissionCharges == 'Y' )
        {  
            console.log('yse inside');
            statetranscharges = parseFloat(this.standingClearence.maxDrawalCeiling)*parseFloat(this.standingClearence.stateTransChargesPercent );
            console.log(statetranscharges);
            this.standingClearence.stateTransCharges = statetranscharges.toString();
            schedulingcharges = parseFloat(this.standingClearence.maxDrawalCeiling)*parseFloat(this.standingClearence.schedulingChargesPercent );
            console.log(schedulingcharges);
            this.standingClearence.schedulingCharges = schedulingcharges.toString();
            systemoprcharges = parseFloat(this.standingClearence.maxDrawalCeiling)*parseFloat(this.standingClearence.systemoprChargesPercent );
            console.log(systemoprcharges);
            this.standingClearence.systemOprCharges = systemoprcharges.toString();
        }
        
    }

    onBuyerEdcChange() {
        console.log("In edc change");
        console.log(this.standingClearence.orgId);
        var org = this.edcList.filter((item) => item.id == this.standingClearence.orgId)[0];
        this.standingClearence.orgId = org.id;
        this.standingClearence.orgName = org.name;
        this.service.getBuyers(this.standingClearence.orgId).subscribe(
            _buyerCompanyServices => {
                this.buyerCompanyServices = _buyerCompanyServices;
                console.log(this.buyerCompanyServices);
            }
        )
    }

    onBuyerServiceChange() {
        console.log(this.buyerCompanyServices);
        console.log("In service change");
        var buyerCompanyService = this.buyerCompanyServices.filter((item) => item.id == this.standingClearence.buyerCompanyServiceId)[0];
        this.standingClearence.buyerCompanyId = buyerCompanyService.companyId;
        this.standingClearence.buyerCompanyName = buyerCompanyService.companyName;
        this.standingClearence.buyerCompanyServiceNumber = buyerCompanyService.number;

        //this.esIntent.sellerCompanyName= this.edcs.filter((item)=> item.id == this.esIntent.sellerCompanyServiceId)[0].companyName;
        console.log(this.standingClearence);


    }

    saveStandingClearence() {
        console.log("this.standingClearence")
        console.log(this.standingClearence)
        console.log('In save' + this.addScreen);
         this.formatChangesforDB();
        if (this.addScreen) {
            this.addStandingClearence();
        }
        else {
            this.updateStandingClearence();
        }
    }
    
    formatChangesforDB() {

        this.standingClearence.fromDate = this.datePipe.transform(this.standingClearence.fromDate, 'yyyy-MM-dd');
        this.standingClearence.createdDate = this.datePipe.transform(this.standingClearence.createdDate, 'yyyy-MM-dd');
        this.standingClearence.toDate = this.datePipe.transform(this.standingClearence.toDate, 'yyyy-MM-dd');
        this.standingClearence.approvalDate = this.datePipe.transform(this.standingClearence.approvalDate, 'yyyy-MM-dd');
    }
    
    addStandingClearence() {
        this.service.addStandingClearence(this.standingClearence).subscribe(
            result => {
                console.log(result)

                this.listStandingClearence();
            },
            error => {
                console.error('Error adding Standing Clearence!');
                console.error(error);
            }
        );
    }

    updateStandingClearence() {
        this.service.updateStandingClearence(this.standingClearence).subscribe(
            result => {
                this.listStandingClearence();
            },
            error => {
                console.error('Error updating Standing Clearence!');
                console.error(error);
            }
        );
    }

    listStandingClearence() {
        this.router.navigateByUrl('/standing-clearence/standing-clearence-list');
    }

    completeStandingClearence() {
        this.formatChangesforDB();
        this.service.completeStandingClearence(this.standingClearence).subscribe(
            result => {
                this.listStandingClearence();
            },
            error => {
                console.error('Error completing noc Generator!');
                console.error(error);
            }
        );
    }

    approveStandingClearence() {
        this.formatChangesforDB();
        this.service.approveStandingClearence(this.standingClearence).subscribe(
            result => {
                this.listStandingClearence();
            },
            error => {
                console.error('Error approving Standing Clearence!');
                console.error(error);
            }
        );

//   subStation:any;
//   substationList= [];
//   filterSubstations(val: string) {
//     return val ? this.substationList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.substationList;
//   }
//   fetchSubstations() {
//     if(this.standingClearence.buyerOrgId){
//       this.commonService.fetchSubstationsByOrgId(this.standingClearence.buyerOrgId).subscribe(
//       result => {
//         this.substationList = result;
//       },
//       error => {
//         console.error('Error loading substaion!');
//         console.error(error);
//       });
// 	}
//   }
  
//   feeder:any;
//   feederList= [];
//   filterFeeders(val: string) {
//     return val ? this.feederList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.feederList;
//   }
//   fetchFeeders() {
//     if(this.subStation.id){
//       this.commonService.fetchFeeders(this.subStation.id).subscribe(
//       result => {
//         this.feederList = result;
//       },
//       error => {
//         console.error('Error loading substaion!');
//         console.error(error);
//       });
	}
    }
