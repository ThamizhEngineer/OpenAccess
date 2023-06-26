import { Component, Input } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from '../../../../shared/common/common.service';
import { saveAs } from 'file-saver';
import { InvoiceLine, TranscoInvoice } from '../../../vo/transcoinvoice';
import { TranscoService } from '../../services/transco.service';
import { Console } from 'console';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];

@Component({
  selector: 'transcoinvoice-detail',
  templateUrl: './transcoinvoice-detail.component.html',

})

export class TranscoinvoiceDetailComponent {
  dataSource = ELEMENT_DATA;

  orgList = [];
  view: boolean = false;
  viewed: boolean = true;
  Detail: TranscoInvoice;
  ChargesDetail: Array<InvoiceLine>
  ChargesDetailothers: InvoiceLine
  transcoinv: TranscoInvoice;

  years = [
    { "value": "2018", "name": "2018" },
    { "value": "2019", "name": "2019" },
    { "value": "2020", "name": "2020" },
    { "value": "2021", "name": "2021" },
    { "value": "2022", "name": "2022" },
    { "value": "2023", "name": "2023" }

  ];
  months: { value: string; name: string; }[];
  mCompServId: any;
  lineMonth: any;
  lineYear: any;
  fuelTypeCode: any;
  Message: any;
  UserName: any;
  oandmChargesDetail: InvoiceLine;
  ETChargesDetail: InvoiceLine;
  Usertype: any;
  HasEdc: boolean;
  HasEEoperation: boolean;
  IsSuperUser: boolean;
  tester: Array<string> = [];
  message: any;
  CanSendEmail: boolean;
  progress: boolean;
  transmissioncharges: InvoiceLine;
  ScheduleingChrages: InvoiceLine;
  systemoperation: InvoiceLine;
  reactiveEnergyCharges: InvoiceLine;
  totalamount: number;
  total: number;
  confirmedonce: boolean = false;
  totalinwords: string;
  error: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private commonUtils: CommonUtils,
    // private datePipe: DatePipe,
    private service: TranscoService,
  ) {
    this.UserName = CommonUtils.getLoginUserDetails();
    this.Usertype = CommonUtils.getLoginUserTypeName();
  }
  ngOnInit(): void {
    this.months = this.commonService.fetchMonths();
    this.ChargesDetailothers= new InvoiceLine();
    this.transcoinv = new TranscoInvoice();
    this.Detail = new TranscoInvoice();
    //  this.printtranscoinvReport()

    //  this.fetchFuelTypes();

    this.route.paramMap
      .subscribe((_params: Params) => {
        console.log(_params)
        this.transcoinv.id = _params.get('invoiceId')
        this.transcoinv.lineMonth = _params.get('month')
        this.transcoinv.lineYear = _params.get('year')
        this.transcoinv.mCompServId = _params.get('dispservicenumber')
        console.log(this.transcoinv.mCompServId)
        console.log(_params.get('dispservicenumber'))



      });
    if (this.Usertype == "EDC User") {
      this.HasEdc = true;

    }
    if (this.Usertype == "EE Operation user") {
      this.HasEEoperation = true;

    }

    if (this.Usertype == "Super User") {
      this.IsSuperUser = true;
    }

    console.log(this.transcoinv)
  }
  forwardtoEdc() {
    this.service.forwardtoedc(this.transcoinv.id, this.transcoinv.lineMonth, this.transcoinv.lineYear, this.transcoinv.mCompServId, this.UserName).subscribe(
      result => {
        this.Message = result
        alert(this.Message);


      });
  }
  getinvoiceDetails() {

    this.service.getforinvdetail(this.transcoinv.lineMonth, this.transcoinv.lineYear, this.transcoinv.mCompServId, this.transcoinv.id).subscribe(
      data => {
        this.Detail = data;
       // this.Detail.totalInvAmt=this.total
        console.log(this.Detail.totalInvAmt)
        this.totalinwords=this.Detail.invAmtInWords
        console.log(this.Detail)

        if(this.Detail.cusGst=='GST-NOT-REGISTERED'){
         
          this.Detail.transactiontype = 'B2C'
        }

        if (this.Detail.invStatus == 'CONFIRMED') {

          this.CanSendEmail = true;


        }

       
if(this.Detail.invStatus == 'CREATED'){
       this.confirmedonce = true; 

}
        console.log("In search result")

      });
    this.service.getchargesdetail(this.transcoinv.id).subscribe(
      data => {
        this.ChargesDetail = data;

        

        
        //this.ChargesDetail.push(this.ChargesDetailothers)
      
        console.log(this.ChargesDetail[1].itemDesc == 'O&M Charges')

        for (var i = 0; i < this.ChargesDetail.length; i++) {

          if (this.ChargesDetail[i].itemDesc == 'O&M Charges') {
            console.log("came here");

            this.oandmChargesDetail = this.ChargesDetail[i];
            
            console.log(this.oandmChargesDetail)
          }
          if (this.ChargesDetail[i].itemDesc == 'Transmission Charges') {
            this.transmissioncharges = this.ChargesDetail[i];
            
            console.log(this.ETChargesDetail)
          }
          if (this.ChargesDetail[i].itemDesc == 'Scheduling Charges') {
            this.ScheduleingChrages = this.ChargesDetail[i];
           
            console.log(this.oandmChargesDetail)
          }
          if (this.ChargesDetail[i].itemDesc == 'System Operation Charges') {
            this.systemoperation = this.ChargesDetail[i];
           
            console.log(this.oandmChargesDetail)
          }
          if (this.ChargesDetail[i].itemDesc == 'RKvah Penalty') {
            this.reactiveEnergyCharges = this.ChargesDetail[i];
            
            console.log(this.oandmChargesDetail)
          }

        }
        this.total=Number(this.systemoperation.itemTotalAmt)+Number(this.reactiveEnergyCharges.itemTotalAmt)+Number(this.oandmChargesDetail.itemTotalAmt)+Number(this.transmissioncharges.itemTotalAmt)+Number(this.ScheduleingChrages.itemTotalAmt)
      });



  }
  
  ViewInvoice() {
    this.viewed = false;
    this.view = true;
    this.getinvoiceDetails();
  }
  confirm() {
    this.service.confirmById(this.transcoinv.id, this.transcoinv.lineMonth, this.transcoinv.lineYear, this.transcoinv.mCompServId, this.UserName).subscribe(
      result => {
        {
          alert("SuccessFully Confirmed") ;
          this.transcoinvoicelist();
        }

        error => {
          this.error=JSON.parse(error._body);
          console.log(this.error.message);
          alert(this.error.message)
          this.transcoinvoicelist();
          console.error('Error adding DeleteTxn!');
          console.error(error);

      }}
      );
    }

  transcoinvoicelist() {
    this.router.navigateByUrl('/transcoinvoices/transcoinvoice-list/');

  }

  openDownload(data: Response) {
    let content_type = data.headers.get('Content-type');
    let x_filename = "TranscoInvoice.pdf";
    console.log(content_type)
    saveAs(data.blob(), x_filename);
  }

  sendEmail() {
    console.log(this.Detail)

    this.tester.push(this.Detail.id)
    this.progress = true;
    console.log("passed it")
    this.service.SendMail(this.tester, this.UserName).subscribe(
      data => {
        console.log(data);
        alert(data);
        this.transcoinvoicelist();
      },
      error => {
        console.error('not mailed');
        console.error(error);
      }
    );
  }




  printReport() {

    this.service.printTranscoInvoice(this.transcoinv.id).subscribe(xs => {
      this.openDownload(xs);
    });

  }
  printFormulaReport() {

    this.service.printTranscoInvoiceCharges(this.transcoinv.id).subscribe(xs => {
      this.openDownload(xs);
    });

  }
}








