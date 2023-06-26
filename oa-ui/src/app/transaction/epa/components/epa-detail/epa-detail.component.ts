import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
//import { SessionStorage} from "angular2-localstorage/WebStorage";
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EpaEvent } from './../../services/epa.event';
import { EpaService } from './../../services/epa.service';
import { Epa,EpaLine} from './../../../vo/epa';
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
  selector: 'epa-detail',
  templateUrl: './epa-detail.component.html',
  //styleUrls: [],
  providers: [EpaService,CommonUtils,DatePipe ,MatDatepickerModule, MatNativeDateModule,
    {
   provide: DateAdapter, useClass: AppDateAdapter
},
{
   provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}
]
})
export class EpaDetailComponent implements OnInit {

  epa: Epa;
  epaLine:EpaLine;
  agmtPeriodCodes=[
    {"key": "STOA", "name": "STOA" },
    {"key": "MTOA", "name": "MTOA" },
    {"key": "LTOA", "name": "LTOA" }
  ]
  nextepaStatus: string = 'Applied';
  nextAction: string = 'Apply';
  disableControls: boolean = true;
  substationList = [];
  filteredSubstationList = [];
  filteredFeederList = [];
  feederList=[];
  filterFeeder = [];
  voltages = [];
  voltageList = [];
  filteredVoltageList = [];
  editing = {};
  rows = [];
  addScreen: boolean = true;
  // waType: string = "consumer";
  isCaptives = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ];
  epaLineRowIndex:number;
  intervalTypeCodes = [];
  accessUpdateFlag:boolean = false;
  accessDeleteFlag:boolean = false;
  accessApproveFlag:boolean = false;
  accessCompleteFlag:boolean = false;
  accessProcessFlag:boolean = false;

  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: EpaService,
  private commonService: CommonService,
  private datePipe: DatePipe,
    private epaEvent: EpaEvent
  ) { 
this.fetchIntervalTypeCode();

  }

  ngOnInit() {
    this.epa= new Epa();
    this.epaLine = new EpaLine();

    this.accessUpdateFlag=this.commonUtils.userHasAccess("EPA","UPDATE");
    this.accessDeleteFlag=this.commonUtils.userHasAccess("EPA","DELETE");
    this.accessApproveFlag=this.commonUtils.userHasAccess("EPA","APPROVE");
    this.accessCompleteFlag=this.commonUtils.userHasAccess("EPA","COMPLETE");
    this.accessProcessFlag=this.commonUtils.userHasAccess("EPA","PROCESS");
    this.fetchVoltages();
    if (this.route.params['_value']['_id']) {
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getEpaById(params['_id']))
        .subscribe((_epa: Epa) => {
          this.epa = _epa;
          console.log(this.epa)
          this.fetchFeeders(this.epa.sellerSubstationId);
          this.formatChangesforUI();
        
          // this.setNextStatus(this.epa.statusDesc);
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

  formatChangesforDB() {
    
        this.epa.fromDate = this.datePipe.transform(this.epa.fromDate, 'yyyy-MM-dd');
        this.epa.toDate = this.datePipe.transform(this.epa.toDate, 'yyyy-MM-dd');
    
    this.epa.agreementDate = this.datePipe.transform(this.epa.agreementDate, 'yyyy-MM-dd');
        
      }
    
      formatChangesforUI() {
        
        this.epa.fromDate = (this.epa.fromDate) ? this.epa.fromDate.substr(0, 10) : "";
        this.epa.toDate = (this.epa.toDate) ? this.epa.toDate.substr(0, 10) : "";
    this.epa.agreementDate = (this.epa.agreementDate) ? this.epa.agreementDate.substr(0, 10) : "";
      }

 

  saveEpa(status: string) {
    //save can be add or update
    if (status != '') {
      this.epa.statusDesc = status;
      var date = new Date();
      var dateString = date.getDate() + '-' + date.getMonth() + '-' + date.getFullYear();
      switch (status) {
        case 'Applied':
          this.epa.appliedDate = dateString;
          break;
        case 'Approved':
          this.epa.approvedDate = dateString;
          break;
        case 'Disapproved':
          //this.ewa.disapprovedDate = dateString;
          break;

        default:
          break;
      }
    }

    if (this.epa.id == '') {
      this.addEpa();
    }
    else {
      this.updateEpa();
    }
  }

  addEpa() {
    console.log("In add epa");
    console.log(this.epa);
    this.service.addEpa(this.epa).subscribe(
      result => {
   
        this.listEpas();
      },
      error => {
        console.error('Error adding Epa Application!');
        console.error(error);
      }
    );
  }
  updateEpa() {
    console.log("In update epa");
    console.log(this.epa);
    this.service.updateEpa(this.epa).subscribe(
      result => {
        this.listEpas();
      },
      error => {
        console.error('Error updating Ewa!');
        console.error(error);
      }
    );
  }
  filterFeeders(val: string) {
    
        return val ? this.feederList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.feederList;
      }
    
      fetchFeeders(substationId: string) {
        console.log("fetch feeder");
   
        if (substationId ==""){
          return false;
        }
        this.commonService.fetchFeeders(substationId).subscribe(
          result => {
            this.feederList = result;
            console.log(this.feederList)
          },
          error => {
            console.error('Error loading feeders!');
            console.error(error);
          });
      }
  filterVoltages(val: string) {
    
        return val ? this.voltageList.filter((s) => s.valueDesc.match(new RegExp(val, 'gi'))) : this.voltageList;
      }
      fetchVoltages() {
        this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
          result => {
    
            this.voltages = result;
          },
    
          error => {
            console.error('Error loading Voltages');
            console.error(error);
          });
      }

  filterSubstations(val: string) {
    
        return val ? this.substationList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.substationList;
      }
    
      fetchSubstations() {
    
        this.commonService.fetchSubstations().subscribe(
          result => {
            this.substationList= result;
            console.log(this.substationList)
          },
          error => {
            console.error('Error loading substaion!');
            console.error(error);
          });
      }

  listEpas() {
    this.router.navigateByUrl('/epa/epa-list');
  }
  
  epaLines: any;
  isCaptive:boolean = false;
  isAdd:boolean = false;
  

  approveEpa() {
    this.service.approveEpa(this.epa).subscribe(
      result => {
        this.listEpas();
      },
      error => {
        console.error('Error approving Epa!');
        console.error(error);
      }
    );
  }

  completeEpa() {
    this.service.completeEpa(this.epa).subscribe(
      result => {
        this.listEpas();
      },
      error => {
        console.error('Error complete Epa!');
        console.error(error);
      }
    );
  }
  // addEpaLines(){
  //   if(!this.epa.epaLines){
  //       this.epa.epaLines=[];
  //     }
  //   this.epa.epaLines.push(Object.assign({}, this.epaLines));   
  //   // this.initEpaLine();
  // } 

  // updateEpaLine(){
    
 
  //   var tempArray =[];
  //   for (var index = 0; index < this.epa.epaLines.length; index++) {
  //     if(this.epaLineRowIndex == index){
  //       tempArray.push(Object.assign({},this.epaLines));
  //     }
  //     else{
  //       tempArray.push(this.epa.epaLines[index]);
  //     }
      
  //   }
  //   this.epa.epaLines = tempArray;
  //   // this.initEpaLine();
  // }


  // editEpaLine(_index: number){
  //  this.epaLineRowIndex =_index; 
  //  this.epaLines = Object.assign({},this.epa.epaLines[_index]);
  // }

  // deleteEpaLine(_index: number){ 
  //  this.epa.epaLines.splice(_index,1);
  // }

}
