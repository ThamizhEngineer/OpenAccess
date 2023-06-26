import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Unitwise } from './unitwise';

export class UnitwiseEvent {
  public unitwise$: Observable<Unitwise>;
  private _unitwiseObserver: any;
  private _unitwise: Unitwise;

  constructor() {
    
    this._unitwise = this.setEmpty();
    
    this.unitwise$ = new Observable(observer => {
      this._unitwiseObserver = observer;
    });
  }
  
  set(value: Unitwise) {
    this._unitwise = value;
    this._unitwiseObserver.next(this._unitwise);
  }
  
  load() {
    this._unitwiseObserver.next(this._unitwise);
  }


  setEmpty(){
    return Object.assign({},{
		_id:''
    });
  }

}

