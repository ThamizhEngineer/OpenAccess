// import {Observable} from 'rxjs/Observable';
// import 'rxjs/add/operator/share';
// import { Gc,InvestmentDetail, PlantDetails } from './gc';

// export class GcEvent {
//   public gc$: Observable<Gc>;
//   private _gcObserver: any;
//   private _gc: Gc;

//   constructor() {

//     this._gc = this.setEmpty();

//     this.gc$ = new Observable(observer => {
//       this._gcObserver = observer;
//     });
//   }
  
//   set(value: Gc) {
//     this._gc = value;
//     this._gcObserver.next(this._gc);
//   }
  
//   load() {
//     this._gcObserver.next(this._gc);
//   }


//   setEmpty(){
    
//     var _investmentDetail = Object.assign({},{
//       totalProject:{
//         cost: '', currency: '', exchangeRate: ''},
//       loans:[],
//       proposedDebtEquityRatio: '',
//       equityBreakup:{ prefShareCaptial:{ amount: '',isPercentage: ''}, equityShareCaptial:{ amount: '',isPercentage: '' },promotersEquity:{ amount: '',isPercentage: ''}},
//       promotersContribution:{ prefShareCaptial:{ amount: '',isPercentage: ''},equityShareCaptial:{amount: '',isPercentage: ''}},
//       captiveUsersContribution:{ prefShareCaptial:{ amount: '', isPercentage: '' }, equityShareCaptial:{ amount: '', isPercentage: '' }},
//       ownershipPercentage:{ promotersPercentage: '', captivePercentage: '',ownershipRule: 'Not Applicable' }
//       });
//     var _plantDetails = Object.assign({},{
//         postalAddress:{ line1: '', city: '', state: '', pinCode: '', village: '', town: '', taluk: '', district: ''},
//         physicalLocation:{ sfNo: '', village: '', town: '', taluk: '', district: ''},
//         clearances: [],
//         fuel:{ generatingPlanType: '', fuel: '', fuelLinkageArranged: '', fuelLinkageDetails: '' },
//         cogenPlant:{ industryType: '', proposedSupportFuel: '', supportFuelLinkageDetails: [], isParallelRun: '', isStandBy: '', cogenCycle: '', hasCogenProof: '', cogenProofUrl: ''},
//         misc:{ interfaceVoltagePhase: '', interfaceVoltageFrequency: '', subStation: '', subStationVoltage: '', alreadyHtSupply: '', proposedCommissionDate: '' },
//         quantum:{ annualExpectedQuantum: '', auxiliaryConsumption: '', industrialConsumption: '', proposedPower:{ captive: '', thirdParty: '', powerTrader: '' }, powerToBoard: '', firmPower: '', infirmPower: '' }
//        });
    
//     var gc = Object.assign({},{
//       _id:'', 
//       header : { applicationNumber : '', plantName: '', plantType: '', fuelType: '', companyCode: '', appliedDate: '', approvedDate: '', activatedDate: '', status: 'Initiated' },
//       investmentDetail: _investmentDetail,
//       plantDetails: _plantDetails,
//       genUnits: [],
//       transformers: [],
//       captiveEnergyNeeds: [],
//       checkList: []
//     });
//     return gc;
//   }

// }