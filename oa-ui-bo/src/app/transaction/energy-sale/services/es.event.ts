import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Es } from '../../vo/es';

export class EsEvent {
  public es$: Observable<Es>;
  private _esObserver: any;
  private _es: Es;

  constructor() {

    this._es = this.setEmpty();

    this.es$ = new Observable(observer => {
      this._esObserver = observer;
    });
  }
  
  set(value: Es) {
    this._es = value;
    this._esObserver.next(this._es);
  }
  
  load() {
    this._esObserver.next(this._es);
  }

  setEmpty(){
    var _energySaleUsageDetails = Object.assign({}, {
      buyerCompanyServiceId: '', c1: "0", c2: '0',c3: '0', c4: '0', c5:'0',total:'0', usageDate: ''
    });
	var _energySaleUsageSummaries = Object.assign({}, {
      buyerCompanyServiceId: '', c1: "0", c2: '0',c3: '0', c4: '0', c5:'0',total:'0', drawalVoltageCode: '', tradeRelationshipId:''
    });
	
    var es = Object.assign({},{
      id:'', 
      statusCode: "CREATED",
      gs: null,
      allocations: [],
		c1: "0", c2: '0',c3: '0', c4: '0', c5:'0',netGeneration:'0', netAllocation: '0', 
		gc1: "0", gc2: '0',gc3: '0', gc4: '0', gc5:'0',availc1: "0", availc2: '0',availc3: '0', availc4: '0', availc5:'0',availGc1: "0", availGc2: '0',availGc3: '0', availGc4: '0', availGc5:'0', month: "", year: "", injectingVoltageCode: "", fromDate: "", toDate: "", loss: null, multipleBuyers: "N", usageDetailAvail: "Y", simpleEnergySale: "N",
	  energySaleUsageDetails:[_energySaleUsageDetails],
	  energySaleUsageSummaries:[_energySaleUsageSummaries],
      totalAllocationPerSlotList: [
        {
            "code" : "SL-01",
            "desc" : "Peak Hour - 1",
            "units" : "0"
        }, 
        {
            "code" : "SL-02",
            "desc" : "Peak Hour - 2",
            "units" : "0"
        }, 
        {
            "code" : "SL-03",
            "desc" : "Peak Hour - 3",
            "units" : "0"
        }, 
        {
            "code" : "SL-04",
            "desc" : "NG Hour",
            "units" : "0"
        }, 
        {
            "code" : "SL-05",
            "desc" : "POK Hour",
            "units" : "0"
        }],
      totalChargesPerCode: []
    });
    return es;
  }
}