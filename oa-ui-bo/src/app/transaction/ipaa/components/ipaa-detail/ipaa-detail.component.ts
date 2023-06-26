import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/startWith';

import { Component, OnInit, HostBinding } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';
import { FormsModule, FormControl } from '@angular/forms';
import { CommonService } from '../../../../shared/common/common.service';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
import {  IpaaService } from './../../services/ipaa.service';
import {  IpaScComponent } from './../ipaa-sc/ipaa-sc.component';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';

import { Ipaa,IpaaLine} from './../../../vo/ipaa';
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
  selector: 'ipaa-detail',
  templateUrl: './ipaa-detail.component.html',
  providers: [IpaaService, MatDatepickerModule,  MatNativeDateModule, DatePipe
    ,CommonService,CommonUtils,
    {
   provide: DateAdapter, useClass: AppDateAdapter
},
{
   provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}
]
})


export class IpaaDetailComponent {
  ipaa:Ipaa;
ipaaLine:IpaaLine;
  ipaaLineRowIndex:number;
  addScreen: boolean = true;
  disableControls: boolean = false;
  dialogRef: MatDialogRef<IpaScComponent>;
  config: MatDialogConfig = {
    
    disableClose: false,
    width: '100%',
    height: '70%',
    position: {
      top: '',
      bottom: '',
      left: '10%',
      right: ''
    }
  };
  months= [];
  isCaptives = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ];
  agmtPeriodCodes=[
    {"key": "STOA", "name": "STOA" },
    {"key": "MTOA", "name": "MTOA" },
    {"key": "LTOA", "name": "LTOA" }
  ]
  companyServices=[];
  buyerCompanyServices=[];
  edcList=[];
  fromDate:any;
  toDate:any;
  nocStatus:any;
  ewaStatus:string;
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
    private _location: Location,
    private service: IpaaService,
    private datePipe: DatePipe,
    private commonService :CommonService,
    public dialog: MatDialog

  ) {
    
    
  }

 

 

  ngOnInit() {
    this.ipaa = new Ipaa();
    this.ipaaLine = new IpaaLine();
    this.accessUpdateFlag=this.commonUtils.userHasAccess("IPAA","UPDATE");
    this.accessDeleteFlag=this.commonUtils.userHasAccess("IPAA","DELETE");
    this.accessApproveFlag=this.commonUtils.userHasAccess("IPAA","APPROVE");
    this.accessCompleteFlag=this.commonUtils.userHasAccess("IPAA","COMPLETE");
    this.accessProcessFlag=this.commonUtils.userHasAccess("IPAA","PROCESS");
    this.commonService.fetchEDCs().subscribe(
      edc=>{
        console.log("In page load edc")
        this.edcList = edc;
        console.log(this.edcList)
      }
    )
    this.months = this.commonService.fetchMonths();
    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;

      this.route.params
        .switchMap((params: Params) => this.service.getIpaaById(params['_id']))
        .subscribe((_ipaa: Ipaa) => {
          this.ipaa = _ipaa;
          // this.onEdcChange();
          this.ipaa.sellerCompanyServiceId = _ipaa.sellerCompanyServiceId;          
          console.log("ipaa");
          console.log(this.ipaa);
       
        });
    }

  }


  mpOnFromModelChange(e){
	  this.ipaa.fromMonth = e.month;
	  this.ipaa.fromYear= e.year;

  }
  mpOnToModelChange(e){
	  this.ipaa.toMonth = e.month;
	  this.ipaa.toYear= e.year;

  }



  onBuyerEdcChange(){
    console.log("In edc change");
    console.log(this.ipaaLine.buyerOrgId);
var org =this.edcList.filter((item)=>item.id==this.ipaaLine.buyerOrgId)[0];
this.ipaaLine.buyerOrgId = org.id;
this.ipaaLine.buyerOrgName= org.name;
    this.service.getBuyers(this.ipaaLine.buyerOrgId).subscribe(
      _buyerCompanyServices=>{
        this.buyerCompanyServices = _buyerCompanyServices;
        console.log(this.buyerCompanyServices);
      }
    )
  }

  onBuyerServiceChange(){   
    console.log(this.buyerCompanyServices);
    console.log("In service change");
    
      var buyerCompanyService = this.buyerCompanyServices.filter((item)=> item.id == this.ipaaLine.buyerCompServiceId)[0];
      this.ipaaLine.buyerCompanyId= buyerCompanyService.companyId;
      this.ipaaLine.buyerCompanyName= buyerCompanyService.companyName;
      this.ipaaLine.buyerCompServiceNumber= buyerCompanyService.number;

   

    //this.esIntent.sellerCompanyName= this.edcs.filter((item)=> item.id == this.esIntent.sellerCompanyServiceId)[0].companyName;
    console.log(this.ipaaLine);
   
   
  }
  onServiceChange(){   
    console.log(this.companyServices);
    console.log("In service change");
    var sellerCompanyService = this.companyServices.filter((item)=> item.id == this.ipaa.sellerCompanyServiceId)[0];
    this.ipaa.sellerCompanyId= sellerCompanyService.companyId;
    this.ipaa.sellerCompanyName= sellerCompanyService.companyName;
    //this.ipaa.sellerCompanyName= this.edcs.filter((item)=> item.id == this.ipaa.sellerCompanyServiceId)[0].companyName;
    console.log(this.ipaa);
   
   
  }

 


 

  monthConvertor() {
    
        var fromMonthStr = this.ipaa.fromMonth;
        var fromYearStr = this.ipaa.fromYear;
        var fromMonth = +fromMonthStr;
          fromMonth = fromMonth - 1;
        var fromYear = +fromYearStr;
        this.fromDate = new Date(fromYear,fromMonth, 1);

        var toMonthStr = this.ipaa.toMonth;
        var toYearStr = this.ipaa.toYear;
        var toMonth = +toMonthStr;
        toMonth = toMonth - 1;
        var toYear = +toYearStr;
        this.toDate = new Date(toYear,toMonth + 1, 0);
        this.ipaa.fromDate = this.fromDate;
        this.ipaa.toDate = this.toDate;   
    
      }
      checkApprovedUnits:boolean=false;
      calculateApprovedUnits(){
        var total=0;
    
        total = parseInt(this.ipaaLine.drawalPeakUnits)+parseInt(this.ipaaLine.drawalOffPeakUnits);
        if(total>parseInt(this.ipaaLine.proposedUnits)){
          this.checkApprovedUnits = true;
        }
        this.ipaaLine.approvedUnits = total.toString();
      }
      initIpaaLines(){
        // if(!this.generator.genUnits){
        //     this.generator.genUnits=[];
        //   }
        this.ipaaLine = new IpaaLine();
       this.ipaaLineRowIndex = -1;
      }
      addIpaaLine(){
        if(!this.ipaa.ipaaLines){
          this.ipaa.ipaaLines=[];
          } this.checkApprovedUnits=false;
          this.calculateApprovedUnits();
          if( this.ipaaLine.approvedUnits){
    
          }else{
            this.ipaa.ipaaLines.push(Object.assign({}, this.ipaaLine));   
          }
        this.initIpaaLines();
      } 
      
      
      updateIpaaLine(){
        
        //this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
        // the above code wasnt updating a row in the table due to somebug
        // as a workaround, creating a new array with same rows except the edited row.
        var tempArray =[];
        this.calculateApprovedUnits();
        for (var index = 0; index < this.ipaa.ipaaLines.length; index++) {
          if(this.ipaaLineRowIndex == index){
            tempArray.push(Object.assign({},this.ipaaLine));
          }
          else{
            tempArray.push(this.ipaa.ipaaLines[index]);
          }
          
        }
        this.ipaa.ipaaLines = tempArray;
        this.initIpaaLines();
      }
      
      
      editIpaaLine(_index: number){
       this.ipaaLineRowIndex =_index; 
       var _ipaaLine=Object.assign({},this.ipaa.ipaaLines[_index]); 
      
       this.ipaaLine =_ipaaLine;
     this.onBuyerEdcChange();
       console.log(this.ipaaLine)
       
      }
      
      deleteIpaaLine(_index: number){ 
       this.ipaa.ipaaLines.splice(_index,1);
      }

  saveIpaa() {
    this.monthConvertor() ;
    this.formatChangesforDB();
    console.log('In save' + this.addScreen);
    if (this.addScreen) {
      this.addIpaa();
    }
    else {
      this.updateIpaa();
    }
  }

  formatChangesforDB() {

    this.ipaa.fromDate = this.datePipe.transform(this.ipaa.fromDate, 'yyyy-MM-dd');
    this.ipaa.toDate = this.datePipe.transform(this.ipaa.toDate, 'yyyy-MM-dd');

  }

  

  addIpaa() {
    console.log(this.ipaa);
    this.service.addIpaa(this.ipaa).subscribe(
      result => {
         console.log(result);
         alert(result.message);
        this.listIpaa();
      },
      error => {
        console.error('Error adding Ipaa!');
        console.error(error);
        this.listIpaa();
      }
    );
  }

  updateIpaa() {


    this.service.updateIpaa(this.ipaa).subscribe(
      result => {
        this.listIpaa();
      },
      error => {
        console.error('Error updating Ipaa!');
        console.error(error);
        this.listIpaa();
      }
    );
  }
  approveIpaa(){
    this.formatChangesforDB();
    this.service.approveIpaa(this.ipaa).subscribe(
      result => {
        this.listIpaa();
      },
      error => {
        console.error('Error approving Ipaa!');
        console.error(error);
      }
    );
  }

  completeIpaa(){
    this.formatChangesforDB();
    this.service.completeIpaa(this.ipaa).subscribe(
      result => {
        this.listIpaa();
      },
      error => {
        console.error('Error completing ipaa!');
        console.error(error);
      }
    );
  }


  listIpaa() {
    this.router.navigateByUrl('/ipaa/ipaa-list');
  }

  back(){
	  this._location.back();
  }

  // newIpaSc(_id: string) {

  //   _id;
   
  //   try {

  //     this.dialogRef = this.dialog.open(IpaScComponent, this.config);
  //     this.dialogRef.afterClosed().subscribe(result => {
  //     if(result!=null && result!=undefined){
  //     }
  //       this.dialogRef = null;
  //     });

  //   }
  //   catch (e) {
  //     console.log(e);
  //   }

    
  // }
  newIpaSc(_id: string){
    console.log(_id)
    this.dialogRef = this.dialog.open(IpaScComponent , {
        disableClose: false,
        width: '100%',
        height: '70%',
        data: { _id }
        });
        this.dialogRef.updatePosition({ top: '',
        bottom: '',
        left: '10%',
        right: '' });
  }
}