import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Payment } from './payment';

export class PaymentEvent {
  public payment$: Observable<Payment>;
  private _paymentObserver: any;
  private _payment: Payment;

  constructor() {
    
    this._payment = this.setEmpty();

    this.payment$ = new Observable(observer => {
      this._paymentObserver = observer;
    });
  }
  
  set(value: Payment) {
    this._payment = value;
    this._paymentObserver.next(this._payment);
  }
  
  load() {
    this._paymentObserver.next(this._payment);
  }

  
  setEmpty(){
    var _consumerDetails = Object.assign({}, {
      number: '', orgName: ''
    });
    var _generatorDetails = Object.assign({}, {
      number: '', orgName: ''
    });
    return Object.assign({},{
		_id:'', 
		header : {paymentType: '', applicationNumber: '', appliedDate: '', approvedDate: '', activatedDate: '', duration: '', status: 'Initiated', remarks: '', disapprovedDate: '' },
		consumerDetails: _consumerDetails,
    generatorDetails: _generatorDetails
    }); 
  }
}

