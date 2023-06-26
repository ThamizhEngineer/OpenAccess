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
import { NocGeneratorService } from './../../services/noc-generator.service';
import { CommonService } from './../../../../shared/common/common.service';
import { NocGenerator,NocGeneratorLine} from './../../../vo/noc-generator'; 
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
  selector: 'noc-generator-detail',
  templateUrl: './noc-generator-detail.component.html',
  providers: [CommonService, NocGeneratorService, MatDatepickerModule, MatNativeDateModule, DatePipe ,CommonUtils,
    {
   provide: DateAdapter, useClass: AppDateAdapter
},
{
   provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}
]
})

export class NocGeneratorDetailComponent {
  addScreen: boolean = true;
  nocGenerator: NocGenerator;
  nocGeneratorLine:NocGeneratorLine
  nocGeneratorLineRowIndex:number;
  Voltages = [];
  feederId = [];
    disableControls: boolean = true;
    buyerCompanyServices=[];
    edcList=[];
    isOnlineDatas = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]

  isTangedcos = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]
        typeCode = [
    { "key": "BUYER-NOC", "name": "BUYER-NOC" },
    { "key": "SELLER-NOC", "name": "SELLER-NOC" }
  ]
  agmtPeriodCodes=[
    {"key": "STOA", "name": "STOA" },
    {"key": "MTOA", "name": "MTOA" },
    {"key": "LTOA", "name": "LTOA" }
  ];
  mpFromConfig = {'placeHolder': 'Duration-From', 'readonly':false};
  mpFromModel = {'month':'', 'year':''};
  mpToConfig = {'placeHolder': 'Duration-To', 'readonly':false};
  mpToModel = {'month':'', 'year':''};

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
    private service: NocGeneratorService


  ) {

    this.fetchVoltages();

  }

  ngOnInit() {
    this.accessUpdateFlag=this.commonUtils.userHasAccess("CONSENT","UPDATE");
    this.accessDeleteFlag=this.commonUtils.userHasAccess("CONSENT","DELETE");
    this.accessApproveFlag=this.commonUtils.userHasAccess("CONSENT","APPROVE");
    this.accessCompleteFlag=this.commonUtils.userHasAccess("CONSENT","COMPLETE");
    this.accessProcessFlag=this.commonUtils.userHasAccess("CONSENT","PROCESS");
    

    this.nocGenerator = new NocGenerator();
    this.nocGeneratorLine = new NocGeneratorLine();
    this.commonService.fetchEDCs().subscribe(
      edc=>{
        console.log("In page load edc")
        this.edcList = edc;
        console.log(this.edcList)
      });
    console.log(this.route.params['_value']);
    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getNocGeneratorById(this.route.params['_value']['_id']))
        .subscribe((_nocGenerator: NocGenerator) => {
          console.log(_nocGenerator);
          this.nocGenerator = _nocGenerator;
          this.formatChangesforUI();
          // this.mpFromModel = {'month':this.noc.fromMonth, 'year':this.noc.fromYear};
          // this.mpToModel = {'month':this.noc.toMonth, 'year':this.noc.toYear};
        });
    }

  }
  formatChangesforUI() {
    this.nocGenerator.tangedcoDated= ( this.nocGenerator.tangedcoDated) ?  this.nocGenerator.tangedcoDated.substr(0, 10) : "";
    this.nocGenerator.tangedcoTillDate= ( this.nocGenerator.tangedcoTillDate) ?  this.nocGenerator.tangedcoTillDate.substr(0, 10) : "";
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
  checkApprovedUnits:boolean=false;
  calculateApprovedUnits(){
    var total=0;

    total = parseInt(this.nocGeneratorLine.drawalPeakUnits)+parseInt(this.nocGeneratorLine.drawalOffPeakUnits);
    if(total>parseInt(this.nocGeneratorLine.proposedUnits)){
      this.checkApprovedUnits = true;
    }
    this.nocGeneratorLine.approvedUnits = total.toString();
  }

  initNocGeneratorLines(){
    // if(!this.generator.genUnits){
    //     this.generator.genUnits=[];
    //   }
    this.nocGeneratorLine = new NocGeneratorLine();
   this.nocGeneratorLineRowIndex = -1;
  }
  addNocGeneratorLine(){
    if(!this.nocGenerator.nocGeneratorLines){
      this.nocGenerator.nocGeneratorLines=[];
      }
     this.checkApprovedUnits=false;
      
      if( this.nocGeneratorLine.approvedUnits){

      }else{
        this.nocGenerator.nocGeneratorLines.push(Object.assign({}, this.nocGeneratorLine));   
      }

  
    this.initNocGeneratorLines();
  } 
  
  
  updateNocGeneratorLine(){
    
    //this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
    // the above code wasnt updating a row in the table due to somebug
    // as a workaround, creating a new array with same rows except the edited row.
    this.calculateApprovedUnits();
    var tempArray =[];
    for (var index = 0; index < this.nocGenerator.nocGeneratorLines.length; index++) {
      if(this.nocGeneratorLineRowIndex == index){
        tempArray.push(Object.assign({},this.nocGeneratorLine));
      }
      else{
        tempArray.push(this.nocGenerator.nocGeneratorLines[index]);
      }
      
    }
    this.nocGenerator.nocGeneratorLines = tempArray;
    this.initNocGeneratorLines();
  }
  
  
  editNocGeneratorLine(_index: number){
   this.nocGeneratorLineRowIndex =_index; 
   var _nocGeneratorLine=Object.assign({},this.nocGenerator.nocGeneratorLines[_index]); 
  
   this.nocGeneratorLine =_nocGeneratorLine;
   this.onBuyerEdcChange();
   console.log(this.nocGeneratorLine)
   
  }
  
  deleteNocGeneratorLine(_index: number){ 
   this.nocGenerator.nocGeneratorLines.splice(_index,1);
  }


  onBuyerEdcChange(){
    console.log("In edc change");
    console.log(this.nocGeneratorLine.buyerOrgId);
var org =this.edcList.filter((item)=>item.id==this.nocGeneratorLine.buyerOrgId)[0];
this.nocGeneratorLine.buyerOrgId = org.id;
this.nocGeneratorLine.buyerOrgName= org.name;
    this.service.getBuyers(this.nocGeneratorLine.buyerOrgId).subscribe(
      _buyerCompanyServices=>{
        this.buyerCompanyServices = _buyerCompanyServices;
        console.log(this.buyerCompanyServices);
      }
    )
  }

  onBuyerServiceChange(){   
    console.log(this.buyerCompanyServices);
    console.log("In service change");
    var buyerCompanyService = this.buyerCompanyServices.filter((item)=> item.id == this.nocGeneratorLine.buyerCompServiceId)[0];
    this.nocGeneratorLine.buyerCompanyId= buyerCompanyService.companyId;
    this.nocGeneratorLine.buyerCompanyName= buyerCompanyService.companyName;
    this.nocGeneratorLine.buyerCompServiceNumber= buyerCompanyService.number;

    //this.esIntent.sellerCompanyName= this.edcs.filter((item)=> item.id == this.esIntent.sellerCompanyServiceId)[0].companyName;
    console.log(this.nocGeneratorLine);
   
   
  }

  saveNocGenerator() {


    console.log("this.nocGenerator")
    console.log(this.nocGenerator)
    console.log('In save' + this.addScreen);
    this.formatChangesforDB();
    if (this.addScreen) {
      this.addNocGenerator();
    }
    else {
      this.updateNocGenerator();
    }
  }
  formatChangesforDB() {

    this.nocGenerator.tangedcoDated=  this.datePipe.transform( this.nocGenerator.tangedcoDated, 'yyyy-MM-dd');
    this.nocGenerator.tangedcoTillDate=  this.datePipe.transform(this.nocGenerator.tangedcoTillDate, 'yyyy-MM-dd');

  }
  addNocGenerator() {
    this.service.addNocGenerator(this.nocGenerator).subscribe(
      result => {
        console.log(result)
        
        this.listNocGenerator();
      },
      error => {
        console.error('Error adding Noc Generator!');
        console.error(error);
      }
    );
  }

  updateNocGenerator() {
    this.service.updateNocGenerator(this.nocGenerator).subscribe(
      result => {
        this.listNocGenerator();
      },
      error => {
        console.error('Error updating Noc Generator!');
        console.error(error);
      }
    );
  }

  listNocGenerator() {
    this.router.navigateByUrl('/noc-generator/noc-Generator-list');
  }

        completeNocGenerator() {
    this.service.completeNocGenerator(this.nocGenerator).subscribe(
      result => {
        this.listNocGenerator();
      },
      error => {
        console.error('Error completing noc Generator!');
        console.error(error);
      }
    );
  }

        approveNocGenerator() {
    this.service.approveNocGenerator(this.nocGenerator).subscribe(
      result => {
        this.listNocGenerator();
      },
      error => {
        console.error('Error approving Noc Generator!');
        console.error(error);
      }
    );
  }
}