import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
 
 
import { PaymentService } from './../../services/payment.service'; 
import { Payment} from './../../services/payment'; 

@Component({
  selector: 'payment-list',
  templateUrl: './payment-list.component.html',
 // styleUrls: [],
  providers: [PaymentService]
})
export class PaymentListComponent implements OnInit{ 

  rows: Array<Payment>;
  tempResults: Array<Payment>;

  // for pagination
  count = 0;
  offset = 0;
  limit = 8;
  paymentType: string = 'sale';
  paymentTypes=['sale', 'made'];

  titles={
    'sale':'Payment made against invoices (Sale to Board)',
    'made':'Payment received'};

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: PaymentService
  ) {}

  ngOnInit() {
    const t= this.route.params['_value']['type'];
    if(this.paymentTypes.indexOf(t) === -1){
      this.router.navigateByUrl('/payment/payment-list/sale');
      return;
    }
    this.paymentType = t;
    this.rows = [];
    
    this.router.events.subscribe(event => {
      if (event.constructor.name === 'NavigationEnd') {
        const t= this.route.params['_value']['type'];
        this.paymentType = this.paymentTypes.indexOf(t) ? t : "made";
        this.page(this.offset,this.limit)
      }
    });
  }

  page(offset, limit) {
    this.offset = offset;
    this.limit = limit;
    this.fetchResults()
  }

  fetchResults(){
    const t= this.route.params['_value']['type'];
    this.service.getPayments().subscribe(
        _payments => {
        this.tempResults = _payments;
        const filtered = _payments.filter(function (payment) {   
        return payment.header.paymentType === t;
    });

        this.count = filtered.length;
        const start = this.offset * this.limit;
        const end = start + this.limit;
        const rows = [...this.rows];

        for (let i = start; i < end; i++) {
          rows[i] = filtered[i];
        }

        this.rows = rows;

      });
  }

  onPage(event) {
    this.page(event.offset, event.limit);
  }

  newPayment() {
    try {
      this.router.navigateByUrl('/payment/payment-new/'+this.paymentType);
    }
    catch(e) {
      console.log(e);
    }
  }

  editPayment(_id: string) {
    try {
      this.router.navigateByUrl('/payment/payment-edit/'+ _id+'/'+this.paymentType);
    }
    catch(e) {
      console.log(e);
    }
  }

}
