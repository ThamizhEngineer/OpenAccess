import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Gs } from './../../vo/gs';

export class GsEvent {
  public gs$: Observable<Gs>;
  private _gsObserver: any;
  private _gs: Gs;

  constructor() {

    // this._gs = this.setEmpty();

    this.gs$ = new Observable(observer => {
      this._gsObserver = observer;
    });
  }
  
  set(value: Gs) {
    this._gs = value;
    this._gsObserver.next(this._gs);
  }
  
  load() {
    this._gsObserver.next(this._gs);
  }


  setEmpty(){
    
    var _header = Object.assign({},{
        companyId:"", serviceNumber: "",	generatorPlantName: "", edcId: "", 	edcName: "", 	multiplicationFactor: "", load: "",
	      injectingVoltage: "",initialReadingDate: "",finalReadingDate: "", netGeneration: "",powerFactor: ""
      });
      

  
    var gs = Object.assign({},{
      _id:'', 
      header: _header,
      bankingUnits: [],
      charges: [],
      generationSummaries: [],
      readings: [],
      slotSummaries: [],
      blockSummaries: []
    });
    
    return gs;
  }
}