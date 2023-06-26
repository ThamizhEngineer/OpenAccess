import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding  } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { Es, EsUsageDetail, EsUsageSummary, generationStatementForPrint, AvailableGsUnits, AllocatedGsUnits } from './../../../vo/es';
import { Payment} from './../../../vo/payment';
import { StbEvent } from './../../services/stb.event'; 
import { StbService } from './../../services/stb.service'; 
// import { Stb,GenerationSummary,GenerationSummaryForPrint,Charge} from './../../services/stb'; 
import { CommonService } from './../../../../shared/common/common.service'; 
import * as jsPDF from 'jspdf';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from "@angular/material";
import { DatePipe } from '@angular/common';

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
  selector: 'stb-detail',
  templateUrl: './stb-detail.component.html',
  styleUrls: ['./stb-detail.component.scss'],
    providers: [DatePipe, {
    provide: DateAdapter, useClass: AppDateAdapter
},
  {
    provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
}]
})
export class StbDetailComponent implements OnInit {
  stb: Es;
  esUsageSummary: EsUsageSummary;
  payment:Payment;
  payments:Array<Payment>;
  paymentRowIndex: number;
  // generationSummaries:GenerationSummary;
  // generationSummariesForPrint:Array<GenerationSummaryForPrint>;
  // charges:Array<Charge>;
  viewType: string;
  months= [];
  tenOfNet;
  rKVAHGenetation;
  penaltyRate;
  rKVAKPenalty;
  monthlyMeterReading;
  netGenerationUnit;
  netPayable;
  machineCapacity;
  omCharges;
  electricityTax;
  id;
  modes = [
    { "key": "Draft", "name": "Draft" },
    { "key": "Cheque", "name": "Cheque" },
    { "key": "Cash", "name": "Cash" },
    { "key": "Others", "name": "Others" }
  ];

 
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: StbService,
    private commonService: CommonService,
    private stbEvent: StbEvent,
    private datePipe: DatePipe,
  ) {}

  ngOnInit() {
    this.stb = new Es();    
    this.esUsageSummary = new EsUsageSummary();
    this.payment = new Payment();
    this.payments = new Array<Payment>();
    this.months = this.commonService.fetchMonths();

   
 
    if(this.route.params['_value']['_id']){
      //route.params['value'] will have the '_id' in it, during edit 
     
      this.route.params
            .subscribe((_params: Params) => {
              this.service.getStbById(_params['_id']).subscribe(_stb=>{
                this.stb = _stb;
            
                this.esUsageSummary = this.stb.energySaleUsageSummaries[0];
                this.service.getAllPayments(this.stb.id).subscribe(res=>{
                    this.payments = res
                    this.payments.forEach(payment=>{
                      this.formatChangesforUI(payment);
                      console.log(payment)
                    }) 
                })
                
              });
              this.viewType = _params['_viewType'];
            }); 
    }         
          
  }



  initPayment(){

    this.payment = new Payment();
   this.paymentRowIndex = -1;
  }


  addPayment(){
    this.formatChangesforDB();
    if(!this.payments){
        this.payments=[];
      }
    this.payments.push(Object.assign({}, this.payment));   
    this.initPayment();
  } 

  updatePayment(){
    this.formatChangesforDB();
    var tempArray =[];
    for (var index = 0; index < this.payments.length; index++) {
      if(this.paymentRowIndex == index){
        tempArray.push(Object.assign({},this.payment));
      }
      else{
        tempArray.push(this.payments[index]);
      }
      
    }
    this.payments = tempArray;
    this.initPayment();
  }


  editPayment(_index: number){
   this.paymentRowIndex =_index; 
   this.payment = Object.assign({},this.payments[_index]);
  }

  deletePayment(_index: number){ 
   this.payments.splice(_index,1);
  }
 

 printToCart(printSectionId){
   var innerContents = document.getElementById(printSectionId).innerHTML;
        var popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
        popupWinindow.document.open();
        popupWinindow.document.write('<html><head><style>table{ border-collapse: collapse;}td,th{border: 1px solid black;padding: 3px;}</style></head><body onload="window.print()"><img style=" display: block;margin: auto;" src="./../../../assets/images/tneb-tangedco-logo_360.png"><br/><br/>' + innerContents + '</html>');
        popupWinindow.document.close();
      
 }
 formatChangesforUI(payment) {
  payment.paymentDate = (payment.paymentDate) ? payment.paymentDate.substr(0, 10) : "";
  payment.invoiceDate = (payment.invoiceDate) ? payment.invoiceDate.substr(0, 10) : "";
  
}

formatChangesforDB() {
 
  this.payment.paymentDate = this.datePipe.transform(this.payment.paymentDate, 'yyyy-MM-dd');
  this.payment.invoiceDate = this.datePipe.transform(this.payment.invoiceDate, 'yyyy-MM-dd');
}
 saveStb(){

    this.payments.forEach(element=>{
      console.log(element.id);
      if(element.id!=null && element.id!=undefined ){
        console.log("update ");
        console.log(element);
        this.updateStb(element);

      }else if(element.id==undefined){
        console.log("add ");
        console.log(element);
        this.addStb(element);
      }
    })   

 }

 addStb(element){


   
       element.paymentContextCode="ENERGY-SALE";
       element.contextRefNumber = this.stb.id;
       element.fromCompanyId = this.esUsageSummary.buyerCompanyId;
       element.fromCompanyServiceId = this.esUsageSummary.buyerCompanyServiceId;
       element.toCompanyId=this.stb.sellerCompanyId;
       element.toCompanyServiceId = this.stb.sellerCompanyServiceId;
      this.service.addStb(element).subscribe(
        result => {
          this.router.navigateByUrl('/stb/stb-list');
        },
        error => {
          this.router.navigateByUrl('/stb/stb-list');
          console.error('Error adding Energy allocation!');
          console.error(error);
        
        })
       }
 updateStb(element){

   
     this.service.updateStb(element).subscribe(
       result => {
        this.router.navigateByUrl('/stb/stb-list');
       },
       error => {
        this.router.navigateByUrl('/stb/stb-list');
         console.error('Error adding Energy allocation!');
         console.error(error);
       
       }
     );


 }

 print(_id: string){
    try{
	  console.log("In Print "+_id);
      this.router.navigateByUrl('/stb/stb-print/'+ _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
 }

  download(printSectionId) {
        var innerContents = document.getElementById(printSectionId);
              //var doc = new jsPDF(orientation, unit, format, compress);

        var img = new Image();
        img.addEventListener('load', function() {
        var doc = new jsPDF('p','pt','a4');    
        doc.addImage(img, 'png', 230, 10,100,100);
        doc.fromHTML(innerContents,50,100);
        doc.save('Test.pdf');
});
        img.src = './../../../assets/images/tneb-tangedco-logo_360.png';  
       
}
 
   listStb() {
      this.router.navigateByUrl('/stb/stb-list');
//       // to-do
//       // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
 	  }

}
