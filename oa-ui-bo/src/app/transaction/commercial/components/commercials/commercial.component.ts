import { CommonService } from './../../../../shared/common/common.service';
import { Component, SystemJsNgModuleLoader } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { stringify } from 'querystring';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { UnAllocatedRemarks } from '../../../vo/unallocatedRemarks';
import { commercialService } from '../../services/commercial.service';
import { DateAdapter, throwMatDialogContentAlreadyAttachedError } from '@angular/material';
import { Console } from 'console';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'commercial',
  templateUrl: './commercial.component.html',
  styleUrls: ['./commercial.component.scss'],
  providers: [commercialService, DatePipe]
})

export class CommercialComponent {

  commissionDate: string="";

  sellerServiceNo: string = "";

  sellerEndEdc: string = "";

  sellerCompanyName: string = "";

  flowTypeCode: string = "";

  fuelType: string = "";

  message: string = "";

  searchServiceNo: string = "";

  error: boolean = false;

  fromDateSTR: any;

  toDateSTR: any;

  toDATE: Date;

  dateOfAggrement: any;

  dynamicArray = [];

  buyerServiceNo: string = "";

  data = [];

  value: string = "";

  // saveButton: boolean = false;

  buyerEntry: boolean = false;
  year:number;
  fromdate:Date;

  constructor(
    private commercialService: commercialService, private commonService:CommonService, private dateAdapter: DateAdapter<Date>, private datepipe: DatePipe, private router: Router) {
    this.dateAdapter.setLocale('en-GB');
  }

  ngOnInit() {
    this.dynamicArray.push({ buyerServiceNo: '', buyerCompanyName: '', buyerEndEDC: '' });
  }

  addRow() {
    this.dynamicArray.push({ buyerServiceNo: '', buyerCompanyName: '', buyerEndEDC: '' });
  }

  commercialEntry() {
    if (this.sellerServiceNo != null && this.sellerCompanyName != null && this.toDateSTR != null && this.dateOfAggrement != null) {
      this.buyerEntry = true;
    } else {
      this.buyerEntry = false;
    }
  }

  deleteRow(index) {
    this.dynamicArray.splice(index, 1);
  }

  ValidateItem() {

    if (this.searchServiceNo == "") {
      // this.error = true;
      this.message = "Service No cannot be null"
      alert(this.message);
      // return false;
    } else {
      this.FetchDetail(this.searchServiceNo)
    }

  }

  FetchDetail(serviceNo: string) {
    this.sellerCompanyName = "";
    this.sellerEndEdc = "";
    this.flowTypeCode = "";

    this.commercialService.getByServiceNumber(serviceNo).subscribe(
      result => {
        if (result.status == "OK") {
          console.log(result);
          this.sellerEndEdc = result.sellerEndEdc;
          this.sellerCompanyName = result.companyName;
          this.fuelType = result.fuelType;
          this.flowTypeCode = result.flowTypeCode;
          this.commissionDate=this.datepipe.transform(result.commissionDate, 'dd/MM/yyyy');
             console.log(Date.parse(this.datepipe.transform(result.commissionDate, 'yyyy-MM-dd')));
             var d1 = Date.parse(result.commissionDate);
        
             if (d1 <= Date.parse("2016-03-31")&& this.fuelType == 'WIND') {
         let newDate = new Date(this.datepipe.transform(result.commissionDate, 'yyyy-MM-dd'));
         var year = newDate.getFullYear();
         var month = newDate.getMonth();
         var day = newDate.getDate();
         var c = new Date(year + 20, month, day-1);
         
         this.toDateSTR=c;
         }
          if(d1 >= Date.parse("2016-04-01")  && this.fuelType == 'WIND'){
          let newDate = new Date(this.datepipe.transform(result.commissionDate, 'yyyy-MM-dd'));
          var year = newDate.getFullYear();
          var month = newDate.getMonth();
          var day = newDate.getDate();
          var c = new Date(year + 25, month, day-1);
         
          this.toDateSTR=c;
          }
          if(this.fuelType == 'SOLAR'){
            let newDate = new Date(this.datepipe.transform(result.commissionDate, 'yyyy-MM-dd'));
            var year = newDate.getFullYear();
            var month = newDate.getMonth();
            var day = newDate.getDate();
            var c = new Date(year + 25, month, day-1);
            
            this.toDateSTR=c;
            }
          //this.toDATE = this.datepipe.transform(result.commissionDate, 'dd/MM/yyyy') 
        } else {
          this.message = result.message;
          alert(this.message);
        }
      })
  }

  fetchBuyer(index: any) {

    for(let i=0; i<this.dynamicArray.length; i++) {
        if(this.dynamicArray[i].buyerServiceNo == this.dynamicArray[index].buyerServiceNo && index != i) {
          alert("Duplicate buyer cannot be added");
          return;
        }else {
        }
    }
      if (this.searchServiceNo && this.sellerCompanyName && this.flowTypeCode && this.dateOfAggrement && this.fromDateSTR && this.toDateSTR) {
        this.buyerServiceNo = this.dynamicArray[index].buyerServiceNo;
        this.commercialService.getBuyerDetails(this.buyerServiceNo).subscribe(
          result => {
            if (result.status = "OK") {
              this.dynamicArray[index].buyerCompanyName = result.companyName;
              this.dynamicArray[index].buyerEndEDC = result.buyerEndEdc;
            } else {
              alert(result.message);
            }
          });
      } else {
        alert(this.message = "Fill the above details to add commercials");
      }

  }

  saveCommercials() {
    this.data = [];
    if (this.dynamicArray[this.dynamicArray.length - 1].buyerCompanyName && this.dynamicArray[this.dynamicArray.length - 1].buyerEndEDC) {

      for (let i = 0; i < this.dynamicArray.length; i++) {
        this.data.push({
          sellerCompanyName: this.sellerCompanyName, sellerCompanyServiceNo: this.searchServiceNo, sellerEndEdc: this.sellerEndEdc,
          flowTypeCode: this.flowTypeCode, fromDateStr: this.datepipe.transform(this.fromDateSTR, 'dd/MM/yyyy'),
          agmtDt: this.datepipe.transform(this.dateOfAggrement, 'yyyy-MM-dd'), toDateStr: this.datepipe.transform(this.toDateSTR, 'dd/MM/yyyy'), buyerCompanyServiceNo: this.dynamicArray[i].buyerServiceNo,
          buyerEndEdc: this.dynamicArray[i].buyerEndEDC, buyerCompanyName: this.dynamicArray[i].buyerCompanyName
        });
      }
      console.log(this.data);
      this.commercialService.saveConsumers(this.data).subscribe(
        result => {
          console.log(result)
          const currentRoute = this.router.url;

          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate([currentRoute]);  //  refresh the current page
          });
          this.addRow();
        });

    } else {
      alert("Table cannot be saved with empty row data ");
    }
  }
  numberFormat(event){
     this.commonService.numberOnly(event);
  }

}