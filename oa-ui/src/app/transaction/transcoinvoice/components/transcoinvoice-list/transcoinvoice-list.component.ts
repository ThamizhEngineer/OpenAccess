import { Component, ViewChild } from "@angular/core";
import { MAT_DATE_LOCALE } from "@angular/material";
import { ActivatedRoute, Router } from "@angular/router";
import { DatatableComponent } from "@swimlane/ngx-datatable";
import { Console } from "console";
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from "../../../../shared/common/common.service";
import { TranscoInvoice } from "../../../vo/transcoinvoice";
import { TranscoService } from "../../services/transco.service";

@Component({
  selector: 'transcoinvoices-list',
  templateUrl: './transcoinvoice-list.component.html',

})

export class TranscoinvoiceListComponent {

  rows: Array<TranscoInvoice>;
  transco: TranscoInvoice;
  years = [
    { "value": "2018", "name": "2018" },
    { "value": "2019", "name": "2019" },
    { "value": "2020", "name": "2020" },
    { "value": "2021", "name": "2021" },
    { "value": "2022", "name": "2022" },
    { "value": "2023", "name": "2023" }

  ];

  invoicestatus = [
    { "value": "CREATED", "name": "CREATED" },
   // { "value": "FORWARDED-TO-EDC", "name": "FORWARDED-TO-EDC" },
   { "value": "TO BE CONFIRMED", "name": "TO BE CONFIRMED" },
    { "value": "CONFIRMED", "name": "CONFIRMED" },
    // { "value": "INVOICE-IN-TRANSIT", "name": "INVOICE-IN-TRANSIT" },
    { "value": "FINAL", "name": "FINAL" }];



  months: { value: string; name: string; }[];
  mCompServId: any;
  lineMonth: any;
  lineYear: any;
  fuelTypeCode: any;
  mOrgId: string;
  invStatus: any;
  orgList = [];
  Usertype: any;
  message: any
  IdasEdc: string;
  IsGenrator: boolean;
  edcname: any;
  HasEEoperation: boolean;
  selectAllrow: boolean = false;

  bulkselect: Array<TranscoInvoice> = [];
  sample: Array<TranscoInvoice>;
  selectAllList: Boolean
  sample1: boolean;
  selectafterdelete: boolean;
  UserName: any;
  masterSelected: boolean = true;
  newbulkee: TranscoInvoice[];
  length: number;
  addedonce: boolean;
  selectAllrowas: boolean;
  tester: Array<string> = [];
  IsSuperUser: boolean;
  statefinal: boolean;
  stateconfirmed: boolean;
  statetobeconfirmed: boolean;
  lineMonthYear: string;
  Final: boolean;
 



  constructor(
    //private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: TranscoService,
    private commonService: CommonService,
    private commonUtils: CommonUtils,
  ) {
    this.Usertype = CommonUtils.getLoginUserTypeName();
    this.UserName = CommonUtils.getLoginUserDetails();
  
  }

  ngOnInit() {
    this.months = this.commonService.fetchMonths();
    this.lineMonth = this.commonUtils.getCurrentMonth();
    this.lineYear = this.commonUtils.getCurrentYear();
    this.fetchOrgList();
   

    console.log(this.Usertype)
    console.log(this.invoicestatus[1].value)
    if (this.Usertype == "Generator") {
      this.IsGenrator = true;
      this.mCompServId=CommonUtils.getLoginUserCompany()
      this.invStatus="FINAL";
     // this.invStatus = this.invoicestatus[0].value;

    }
    if (this.Usertype == "EE Operation user") {
      this.HasEEoperation = true;
     // this.invStatus = this.invoicestatus[0].value;
    }

    if (this.Usertype == "Super User") {
      this.IsSuperUser = true;
    }
    console.log(this.HasEEoperation)
   
    //this.genrateSample();
  }



  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
        this.mOrgId = CommonUtils.getLoginUserEDC();
        console.log(this.mOrgId);
        for (let i = 0; i < this.orgList.length; i++) {
          if (this.orgList[i].id == this.mOrgId) {
            this.IdasEdc = this.orgList[i].id + "-" + this.orgList[i].name

            this.edcname = this.orgList[i].id
          }

        }

      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }
  onInvoicestatus(event){

    if(event  == "CONFIRMED"){

      this.statefinal = true;

    }
    if(event  != "CONFIRMED"){

      this.statefinal = false;

    }
    
    if(event  == "FINAL"){

      this.Final = false;

    }
    if(event  != "FINAL"){

      this.Final = true;

    }


   if(event == 'CREATED'){

    this.stateconfirmed = true;
   }
   if(event != "CREATED"){

   this.stateconfirmed = false;

   }

   if(event == 'TO BE CONFIRMED'){
    this.Final = false;
    this.statetobeconfirmed = true;
   }
   if(event != "TO BE CONFIRMED"){

   
   this.statetobeconfirmed = false;

   }





  }
  generateResult() {
  //  console.log(this.DatePicker +"  "+this.DatePickers)
    this.service.getallTransco(this.edcname, this.lineMonthYear, this.mCompServId, this.invStatus, "undefined").subscribe(
      data => {
        this.rows = data;
        console.log(this.rows)



        console.log("In search result")

      });
  }

  UpdateInvoice(dispservicenumber: string, month: string, year: string, invoiceId: string) {

    try {


      this.router.navigateByUrl('/transcoinvoices/transcoinvoice-detail/' + month + "/" + year + "/" + invoiceId + "/" + dispservicenumber);


    }
    catch (e) {
      console.log(e);
    }
  }

  bulkForwardtoEdc() {
    console.log(this.rows)
    for (var i = 0; i < this.bulkselect.length; i++) {
      
      this.tester.push(this.bulkselect[i].id)
    }
    console.log(this.tester)
    this.service.bulkinvoicefortoedc(this.tester, this.UserName).subscribe(
      data => {
        this.message = data;

        alert(this.message._body);
        const currentRoute = this.router.url;

        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate([currentRoute]); // navigate to same route
        });

      });
      
    }
  
  newbulkfor() {

    this.service.bulkinvoicenew(this.rows).subscribe(
      data => {
        this.message = data;
      });

  }
  sendEmail() {
    console.log(this.bulkselect)
    for (var i = 0; i < this.bulkselect.length; i++) {
      this.tester.push(this.bulkselect[i].id)
    }
    this.service.SendMail(this.tester, this.UserName).subscribe(
      data => {
        this.message = data;
        alert(this.message._body);
        const currentRoute = this.router.url;

        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate([currentRoute]); // navigate to same route
        });

        console.log(this.message._body)

      });

  }

  bulkinvoiceforConfirm() {
    for (var i = 0; i < this.bulkselect.length; i++) {
      
      if (this.tester.indexOf(this.bulkselect[i].id) === -1)
      this.tester.push(this.bulkselect[i].id)
    }
    console.log(this.tester)
    this.service.bulkinvoiceforConfirm(this.tester, this.UserName).subscribe(
      data => {
        this.message = data;

        alert(this.message._body);
        const currentRoute = this.router.url;

        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate([currentRoute]); // navigate to same route
        });

      });
  }



  updateCheckedList(row: TranscoInvoice, event: any) {

    


    if (event.checked == true) {

      console.log("ooddl");
      console.log(this.bulkselect.indexOf(row) == -1)
      
        console.log("addede")
        this.bulkselect.push(row)
        
      

    }


   


    if (event.checked == false) {

      this.bulkselect.splice(this.bulkselect.indexOf(row), 1)
      
      this.selectAllrowas = false;
    }

console.log(this.bulkselect)
  }

  selectAll(row: Array<TranscoInvoice>, event: any) {
    console.log(row[6])
    this.length = row.length
    if (event.checked == true) {
      this.selectAllrow = true;
      this.selectAllrowas = true;
      if (this.selectAllrowas == true) {
        console.log("add on")
         for (var i = 0; i < this.length; i++) {
        //   if (this.bulkselect.indexOf(this.rows[i]) === -1) {
        //     this.bulkselect.push(this.rows[i])
        //   }
        
        this.updateCheckedList(row[i],event);
        }
        
      }


    }
    


    if (event.checked == false) {
      console.log('ffdfddf')
      this.selectAllrow = false;
      for (var i = 0; i < this.length; i++) {
        this.updateCheckedList(row[1],event);
      }
    }

  }

}