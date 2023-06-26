import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Tariff } from './tariff';

export class TariffEvent {
  public tariff$: Observable<Tariff>;
  private _tariffObserver: any;
  private _tariff: Tariff;

  constructor() {
    
    this._tariff = this.setEmpty();
    
    this.tariff$ = new Observable(observer => {
      this._tariffObserver = observer;
    });
  }
  
  set(value: Tariff) {
    this._tariff = value;
    this._tariffObserver.next(this._tariff);
  }
  
  load() {
    this._tariffObserver.next(this._tariff);
  }


  setEmpty(){
    return Object.assign({},{
		_id:'',type: '', fromDate:'', toDate: '', wegGroup: '', rate: '', reference: ''
    });
  }

}

