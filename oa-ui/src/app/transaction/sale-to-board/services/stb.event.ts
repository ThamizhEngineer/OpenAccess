import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Stb, StbHeader } from './stb';

export class StbEvent {
  public stb$: Observable<Stb>;
  private _stbObserver: any;
  private _stb: Stb;

  constructor() {

    this._stb = this.setEmpty();

    this.stb$ = new Observable(observer => {
      this._stbObserver = observer;
    });
  }
  
  set(value: Stb) {
    this._stb = value;
    this._stbObserver.next(this._stb);
  }
  
  load() {
    this._stbObserver.next(this._stb);
  }


  setEmpty(){
    
    var _header = Object.assign({},{
        companyId:"", serviceNumber: "",	generatorPlantName: "", edcId: "", 	edcName: "", 	multiplicationFactor: "", load: "",
	      injectingVoltage: "",initialReadingDate: "",finalReadingDate: "", netGeneration: "",powerFactor: ""
      });
      

  
    var stb = Object.assign({},{
      _id:'', 
      header: _header,
      bankingUnits: [],
      charges: [],
      generationSummaries: [],
      readings: [],
      slotSummaries: [],
      blockSummaries: []
    });
    
    return stb;
  }
}