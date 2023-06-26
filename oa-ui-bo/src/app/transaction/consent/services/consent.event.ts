import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import { Consent } from '../../../transaction/vo/consent';

export class ConsentEvent {
  public consent$: Observable<Consent>;
  private _consentObserver: any;
  private _consent: Consent;

  constructor() {
    this._consent = this.setEmpty();

    this.consent$ = new Observable(observer => {
      this._consentObserver = observer;
    });
  }

  set(value: Consent) {
    this._consent = value;
    this._consentObserver.next(this._consent);
  }

  load() {
    this._consentObserver.next(this._consent);
  }


  setEmpty() {
    // var _consumerDetails = Object.assign({}, {
      // service: [], ssId: '', feeder: '', dedicatedSsId: '', dedicatedFeeder: '', abtMeterInstalled: '', realTimeDataTransfer: '', remarksOnAbt: '', hasGenerators: ''
    // });
    // var _generatorDetails = Object.assign({}, {
      // service: [], ssId: '', feeder: '', genUnits: [], traderName: '', captiveStatus: ''
    // })
    // var _oaDetails = Object.assign({}, {
      // proposedQuantum: '', periodType: '', period: '', fromDate: '', toDate: '', oas: { serviceNumber: '', companyId: '', companyName: '', quantumMappings: [] }
    // })
    return Object.assign({}, {
		id: '', buyerOrgId: '', buyerOrgName: '', buyerCompServiceId: '', buyerCompServiceNumber: '', buyerCompanyName: '', buyerCompanyId: '', drawalSubstationId: '', drawalSubstationName: '', drawalFeederId: '', drawalFeederName: '', drawalVoltageCode: "", drawalVoltageDesc: '', agreementPeriodCode: '',agreementDate:'', agreementPeriodDesc: '', periodDuration: '', tNocId: '', isNocEnclosed: '', proposedDemand: '', approvedDemand: '', isAbtInstalled: '', noAbtReason: '', hasRealTimeCon: '', sellerOrgId: '', sellerOrgName: '', sellerCompServiceId: '', sellerCompServiceNumber: '', sellerCompanyName: '', sellerCompanyId: '', sellerIsCaptive: '', injectionVoltageCode: '', injectionVoltageDesc: '', injectionSubstationId: '', injectionSubstationName: '', injectionFeederId: '', injectionFeederName: '', appliedDate: '', approvedDate: '', statusCode: '', statusDesc: '', consentNumber: '', remarks: '', createdBy: '', createdDate: '', modifiedBy: '', modifiedDate: '', tEsIntentId: '', existingOAAs: '', fromDate: '', toDate: ''
    });
  }

}

