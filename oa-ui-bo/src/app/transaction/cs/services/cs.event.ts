import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Cs } from '../../../transaction/vo/cs';

export class CsEvent {
  public cs$: Observable<Cs>;
  private _csObserver: any;
  private _cs: Cs;

  constructor() {
    this._cs = this.setEmpty();

    this.cs$ = new Observable(observer => {
      this._csObserver = observer;
    });
  }
  
  set(value: Cs) {
    this._cs = value;
    this._csObserver.next(this._cs);
  }
  
  load() {
    this._csObserver.next(this._cs);
  }

  setEmpty(){
	var _csLoans = Object.assign({}, {
		loanOrigin: '', editable: false
    });

    return Object.assign({},{
		id: '', sellerOrgCode: '', sellerEDCName: '', effectiveDate: '', idLoans: []
    });
  }

}
