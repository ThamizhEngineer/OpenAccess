import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Epa } from '../../../transaction/vo/epa';

export class EpaEvent {
  public epa$: Observable<Epa>;
  private _epaObserver: any;
  private _epa: Epa;

  constructor() {
    // this._epa = this.setEmpty();

    this.epa$ = new Observable(observer => {
      this._epaObserver = observer;
    });
  }

  set(value: Epa) {
    this._epa = value;
    this._epaObserver.next(this._epa);
  }

  load() {
    this._epaObserver.next(this._epa);
  }


  setEmpty() {

    return Object.assign({}, {
		id: '', version:'', epaNumber:'', epaApprovalNumber:'', sellerOrgId:'', sellerOrgName:'', sellerCompServiceId:'', sellerCompServiceNumber:'', sellerCompanyName:'', sellerCompanyId:'', sellerCapacity:'', sellerPowerPlantFuelCode:'', sellerPowerPlantFuelDesc:'', sellerInjectionVoltageCode:'', sellerInjectionVoltageDesc:'', sellerIsCaptive:'', agreementPeriodCode:'', agreementPeriodDesc:'', fromDate:'', toDate:'', periodDuration:'', totalProposedUnits:'', totalApprovedUnits:'', totalInjectionPeakUnits:'', totalInjectionOffPeakUnits:'', totalDrawalPeakUnits:'', totalDrawalOffPeakUnits:'', appliedDate:'', approvedDate:'', statusCode:'', statusDesc:'', remarks:'', createdBy:'', createdDate:'', modifiedBy:'', modifiedDate:'', t_es_intent_id:'', epaLines: '',
    });
  }

}

