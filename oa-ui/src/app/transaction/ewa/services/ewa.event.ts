import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Ewa } from '../../../transaction/vo/ewa';

export class EwaEvent {
  public ewa$: Observable<Ewa>;
  private _ewaObserver: any;
  private _ewa: Ewa;

  constructor() {
    this._ewa = this.setEmpty();

    this.ewa$ = new Observable(observer => {
      this._ewaObserver = observer;
    });
  }

  set(value: Ewa) {
    this._ewa = value;
    this._ewaObserver.next(this._ewa);
  }

  load() {
    this._ewaObserver.next(this._ewa);
  }


  setEmpty() {

    return Object.assign({}, {
		id: '', version:'', ewaNumber:'', ewaApprovalNumber:'', sellerOrgId:'', sellerOrgName:'', sellerCompServiceId:'', sellerCompServiceNumber:'', sellerCompanyName:'', sellerCompanyId:'', sellerCapacity:'', sellerPowerPlantFuelCode:'', sellerPowerPlantFuelDesc:'', sellerInjectionVoltageCode:'', sellerInjectionVoltageDesc:'', sellerIsCaptive:'', agreementPeriodCode:'', agreementPeriodDesc:'', fromDate:'', toDate:'', periodDuration:'', totalProposedUnits:'', totalApprovedUnits:'', totalInjectionPeakUnits:'', totalInjectionOffPeakUnits:'', totalDrawalPeakUnits:'', totalDrawalOffPeakUnits:'', appliedDate:'', approvedDate:'', statusCode:'', statusDesc:'', remarks:'', createdBy:'', createdDate:'', modifiedBy:'', modifiedDate:'', t_es_intent_id:'', ewaLines: '',
    });
  }

}

