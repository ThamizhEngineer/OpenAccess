import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Oaa } from '../../../transaction/vo/oaa';

export class OaaEvent {
  public oaa$: Observable<Oaa>;
  private _oaaObserver: any;
  private _oaa: Oaa;

  constructor() {
    this._oaa = this.setEmpty();

    this.oaa$ = new Observable(observer => {
      this._oaaObserver = observer;
    });
  }
  
  set(value: Oaa) {
    this._oaa = value;
    this._oaaObserver.next(this._oaa);
  }
  
  load() {
    this._oaaObserver.next(this._oaa);
  }

  setEmpty(){

    return Object.assign({},{
		id: '', buyerOrgId: '', buyerOrgName: '', buyerEndUtility: '', buyerCompServiceId: '', buyerCompServiceNumber: '', buyerCompanyName: '', buyerCompanyId: '', drawalTransSubstationId: '', drawalTransSubstationName: '', drawalTransVoltageCode: '', drawalTransVoltageDesc: '', drawalDistSubstationId: '', drawalDistSubstationName: '', drawalDistVoltageCode: '', drawalDistVoltageDesc: '', drawalFeederId: '', drawalFeederName: '', agreementPeriodCode: '', agreementPeriodDesc: '', periodDuration: '', proposedTotalUnits: '', approvedTotalUnits: '', c1Units: '', c2Units: '', c3Units: '', c4Units: '', c5Units: '', sellerOrgId: '', sellerOrgName: '', sellerEndUtility: '', sellerCompServiceId: '', sellerCompServiceNumber: '', sellerCompanyName: '', sellerCompanyId: '', sellerIsCaptive: '', injectionTransSubstationId: '', injectionTransSubstationName: '', injectionTransVoltageCode: '', injectionTransVoltageDesc: '', injectionDistSubstationId: '', injectionDistSubstationName: '', injectionDistVoltageCode: '', injectionDistVoltageDesc: '', injectionFeederId: '', injectionFeederName: '', paymentTypeCode: '', paymentTypeDesc: '', paymentBankDetails: '', paymentTxnNo: '', paymentDate: '', paymentAmount: '', appliedDate: '', approvedDate: '', statusCode: '', statusDesc: '', oaaNumber: '', oaaAppNumber: '', remarks: '', createdBy: '', createdDate: '', modifiedBy: '', modifiedDate: '', tEsIntentId: '', licensee: '', fromDate: '', toDate: ''
    });
  }

}
