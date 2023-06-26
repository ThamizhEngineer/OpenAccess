import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
//import { SessionStorage} from "angular2-localstorage/WebStorage";

import { PaymentEvent } from './../../services/payment.event';
import { PaymentService } from './../../services/payment.service';
import { CompanyService } from './../../../master/company/services/company.service';
import { Payment } from './../../services/payment';

@Component({
  selector: 'payment-detail',
  templateUrl: './payment-detail.component.html',
  //styleUrls: ['./payment-detail.component.scss'],
  providers: [PaymentService, CompanyService]
})
export class PaymentDetailComponent implements OnInit {

  payment: Payment;

  companyNames = [];
  consumerServices = [];
  serviceNames = [];
  csDisplayUnits = ['edcName'];

  nextPaymentStatus: string = 'Applied';
  nextAction: string = 'Apply';
  disableControls: boolean = true;

  titles = {
    'sale': 'Payment made against invoices (Sale to Board)',
    'made': 'Payment received'
  };

  modes = [
    { "key": "Draft", "name": "Draft" },
    { "key": "Cheque", "name": "Cheque" },
    { "key": "Cash", "name": "Cash" },
    { "key": "Others", "name": "Others" }
  ];
  accHeads = [
    { "key": "61.522", "name": "ETAX RECOVERY FOR SELF GENERATION" },
    { "key": "61.800", "name": "WHEELING CHARGES RECOVERIES" },
    { "key": "61.801", "name": "LTOA TRANSMISSION CHARGES" },
    { "key": "61.802", "name": "SCHEDU. AND SYSTEM OPER. CHRGS" },
    { "key": "61.803", "name": "REACTIVE ENERGY CHARGES" },
    { "key": "61.804", "name": "STOA INTRA STATE REACTIVE EGY CHRGS" },
    { "key": "61.805", "name": "WHEELING CHGS LOSS COMP CHGS" },
    { "key": "61.806", "name": "STOA INTER STATE EX. TRN CHRGS" }
  ];

  rowIndex: number;
  addScreen: boolean = true;
  paymentType: string = 'sale';
  paymentTypes = ['sale', 'made'];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: PaymentService,
    private companyService: CompanyService,
    private paymentEvent: PaymentEvent
  ) { }

  ngOnInit() {
    this.setCompanyNames();
    const t = this.route.params['_value']['type'];
    if (this.paymentTypes.indexOf(t) === -1) {
      this.router.navigateByUrl('/payment/payment-list/sale');
      return;
    }
    this.paymentType = t;

    this.paymentEvent.payment$.subscribe(latestValue => {
      this.payment = latestValue;
      this.setNextStatus(this.payment.header.status);
      this.payment.header.paymentType = this.paymentType;
    });
    this.paymentEvent.load();

    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getPaymentById(params['_id']))
        .subscribe((_payment: Payment) => {
          this.payment = _payment;
          this.paymentEvent.set(_payment);
          this.setNextStatus(this.payment.header.status);
          //sessionStorage.setItem('company', JSON.stringify(this.company));
        });
    }

    this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
        const t = this.route.params['_value']['type'];
        this.paymentType = this.paymentTypes.indexOf(t) ? t : "sale";
      }
    });

  }

  setNextStatus(status: string) {

    switch (status) {
      case 'Initiated':
        this.nextPaymentStatus = 'Applied';
        this.nextAction = 'Apply';
        break;
      case 'Applied':
        this.nextPaymentStatus = 'Approved';
        this.nextAction = 'Approve';
        break;
      case 'Approved':
        this.nextPaymentStatus = '';
        this.nextAction = '';
        break;
      case 'Disapproved':
        this.nextPaymentStatus = '';
        this.nextAction = '';
        break;

      default:
        break;
    }
    this.disableControls = (this.nextPaymentStatus === '') ? true : false;

  }

  savePayment(status: string) {
    //save can be add or update
    if (status != '') {
      this.payment.header.status = status;
      var date = new Date();
      var dateString = date.getDate() + '-' + date.getMonth() + '-' + date.getFullYear();
      switch (status) {
        case 'Applied':
          this.payment.header.appliedDate = dateString;
          break;
        case 'Approved':
          this.payment.header.approvedDate = dateString;
          break;
        case 'Disapproved':
          this.payment.header.disapprovedDate = dateString;
          break;

        default:
          break;
      }
    }

    if (this.payment._id == '') {
      this.addPayment();
    }
    else {
      this.updatePayment();
    }
  }

  addPayment() {
    this.service.addPayment(this.payment).subscribe(
      result => {
        this.payment._id = result;
        this.listPayments();
      },
      error => {
        console.error('Error adding GC Application!');
        console.error(error);
      }
    );
  }

  updatePayment() {
    this.service.updatePayment(this.payment).subscribe(
      result => {
        this.listPayments();
      },
      error => {
        console.error('Error updating Payment!');
        console.error(error);
      }
    );
  }
  listPayments() {
    this.router.navigateByUrl('/payment/payment-list/' + this.paymentType);
    // to-do
    // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

  }

  setCompanyNames() {
    this.companyService.getCompanies().subscribe(
      _companies => {
        _companies.forEach(company => {
          this.companyNames.push({ 'key': company.companyName, 'name': company.companyName });
          if (company.services) {
            company.services.forEach(service => {
              service.companyId = company.companyName;
              this.consumerServices.push(service);
            });
          }
        });
      }
    );
  }

}
