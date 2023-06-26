export class Gc {
  id?: string;
  _id?: string;
  generatingTypeIsCaptive?: string;
  generatingTypeName?: string;
  applnNumber?: string;
  plantName?: string;
  plantTypeCode?: string;
  plantTypeName?: string;
  fuelTypecode?: string;
  fuelTypeName?: string;
  //  companyCode?:string; // remove companyCode
  companyName?: string;
  appliedDate?: string;
  approvedDate?: string;
  activatedDate?: string;
  orgId?:string;
  orgName?:string;
  statusCode?: string;
  statusName?: string;
  line1?: string;
  city?: string;
  stateCode?: string;
  stateName?: string;
  pinCode?: string;
  village?: string;
  town?: string;
  talukCode?: string;
  talukName?: string;
  districtCode?: string;
  districtName?: string;
  plSfNo?: string;
  plVillage?: string;
  plPinCode?: string;
  plTown?: string;
  plTalukCode?: string;
  plTalukName?: string;
  plDistrictCode?: string;
  plDistrictName?: string;
  plWindPassCode?: string;
  plWindPassName?: string;
  plWindZoneArea?: string
  clearances?: Array<Clearance>;
  fuelCode?: string;
  fuelLinkageArranged?: string;
  fuelLinkageDetails?: string;
  // cg - cogen info
  parallelOperation?: string;

  cgIndustryType?: string;
  cgSupportFuel?: string;
  cgIsParallelRun?: string;
  cgIsStandBy?: string;
  cgCycle?: string;
  cgHasProof?: string;
  cgProofUrl?: string;
  //sf - Support Fuel Linkage
  sfLinkageFuelSource?: string;
  sfLinkageFrom?: string;
  sfLinkageTo?: string;
  sfRate?: string;
  sfDocUrl?: string;
  classVoltagePhase?: string;
  classVoltageFrequency?: string;
  ssCode?: string;
  ssName?: string;
  ssId?:String;
  ssVoltageCode?: string;
  ssVoltageName?: string;
  tempHtSupplyNumber?: string; // it was alreadyHTSupply
  proposedCommissionDate?: string;
  availedHtSupply?:string;
  availedHtSupplyNo?: string;
  availedSanctionedDemand?: string;
  availedVoltageLevel?: string;
  plantCapacity?: string;
  annualExpectedQuantum?: string;
  expectedCuf?: string;
  auxiliaryConsumption?: string;
  industrialConsumption?: string;
  perUnitCost?: string;
  proposedPowerCaptive?: string;
  proposedPower3PT?: string;
  proposedPowerSTB?: string;
  firmPower?: string;
  infirmPower?: string;
  scoTamilNadu?: string;
  scoMinistry?: string;
  scoCivil?: string;
  idTotalCost?: string;
  idTotalCurrency?: string;
  idTotalExchangeRate?: string;
  idLoans: Array<Loan>;
  idproposedDebtEquityRatio?: string;
  idEBPrefShareCapAmt?: string;
  idEBPrefShareCapPer?: string;
  idEBEquityShareCapAmt?: string;
  idEBEquityShareCapPer?: string;
  idEBPromEquityAmt?: string;
  idEBPromEquityPer?: string;

  idPCPrefShareCapAmt?: string;
  idPCPrefShareCapPer?: string;
  idPCEquityShareCapAmt?: string;
  idPCEquityShareCapPer?: string;

  idCUCPrefShareCapAmt?: string;
  idCUCPrefShareCapPer?: string;
  idCUCEquityShareCapAmt?: string;
  idCUCEquityShareCapPer?: string;

  idOwnPercPromoter?: string;
  idOwnPercCaptive?: string;
  idOwnPercOwnRule?: string;
  idEquityShareVotingRights?: Array<EquityShareVotingRights>;
  idCaptiveUserContributions?: Array<CaptiveUserContribution>;
  genServiceNumber?: string;
  genServiceApprovalNumber?: string ;
  genServiceDate?: string ;
  finalOrgCode?: string;
  finalOrgName?: string;
  finalOrgId?: string;
  finalSsTypeCode?: string;
  finalSsTypeName?: string;
  finalSsCode?: string;
  finalSsName?: string;
  finalSsId?: string;
  finalFeederTypeCode?: string;
  finalFeederTypeName?: string;
  finalFeederCode?: string;
  finalFeederName?: string;
  finalFeederId?: string;
  finalIsStb?: string;
  finalIsWheeling?: string;
  finalPpRate?: string;
  finalStbTariffOrder?: string;
  finalStbTenderNumber?: string;
  finalStbTenderDate?: string;
  finalWheelingFromDate?: string;
  finalWheelingToDate?: string;
  genUnits?: Array<GeneratorUnit>;
  transformers?: Array<Transformer>;
  captiveEnergyNeeds?: Array<CaptiveEnergyNeed>;
  checkList?: Array<DocCheckListItem>;
  genServiceDetails?: Array<any>;
  infrastructure?: Array<any>;
  captiveQuantumAllocation?: Array<QuantumAllocation>;
  applicationStatus?: Array<ApplicationStatus>;
  checkOwnership: boolean;
  remarks?:string;
	createdBy?:string;
	createdDate?:string;
	modifiedBy?:string;
	modifiedDate?:string;
  enabled?:string;
  meterNumber?:string;                                  /*Meter Number*/
  meterMakeCode?:string;                                /*Meter Make*/
  meterMakeName?:string;                                /*Meter Make*/
  modemNumber?:string;                                     /*Modem Number*/
  meterCt1?:string;                                     /*Meter ct 1*/
  meterCt2?:string;                                     /*Meter ct 2*/
  meterCt3?:string;                                     /*Meter ct 3*/
  meterPt1:string;                                      /*Meter Bt 1 */
  meterPt2:string;                                      /*Meter Bt 2 */
  meterPt3:string;                                      /*Meter Bt 3 */
  accuracyClassCode?:string;                            /*Accuracy Class*/
  accuracyClassName?:string;                            /*Accuracy Class*/
  isABTMeter?:string;                                   /* isABTMeter*/
  multiplicationFactor?:string;  
  finalCod?: string;
  finalCopd?: string; 
}

export class Loan {
  id?:string;
  gcId?:string;
  loanOrigin?: string;
  loanSourceName?: string;
  loanSourceAddress?: string;
  loanAmount?: string;
  loanCurrency?: string;
  loanExchangeRate?: string;
  remarks?:string;
	createdBy?:string;
	createdDate?:string;
	modifiedBy?:string;
	modifiedDate?:string;
	Enabled?:string;
}

export class Clearance {   //temporarily not in use 
  id?: string;
  plantGenId?: string;
  clearanceCode?: string;
  clearanceDesc?: string; // display only
  isCleared?: string;
  docUrl?: string
}
export class SupportFuelLinkageDetail {  //temporarily not in use 
  fuelSource?: string;
  linkageFrom?: string;
  linkageTo?: string;
  rate?: string;
  docUrl?: string
}

export class GeneratorUnit {
  id?: string;
  gcId?: string;
  name?: string;
  capacity?: string;
  makeCode?: string;
  makeName?: string;
  serialNumber?: string;
  rotorDia?: string;
  hubHeight?: string;
  mwRating?: string;
  mvRating?: string;
  kvRating?: string;
  dampingFactor?: string;
  amateurResistance?: string;
  directAssistanceReactance?: string;
  negativeSequenceReactance?: string;
  zeroSequenceReactance?: string;
  directAxisTransientReactance?: string;
  quadratureAxisTransientReactance  ?: string;
  directAxisSubTransientReactance?: string;
  inertia?: string;
  windingConnection?: string;
  massNumber?: string;
  stiffnessCoefficient?: string;
  isCaptive?: string;
  finalCod?: string;
  finalCopd?: string;
  remarks?:string;
	createdBy?:string;
	createdDate?:string;
	modifiedBy?:string;
	modifiedDate?:string;
	Enabled?:string;
}
export class Transformer {
  id?: string;
  gcId?: string;
  name?: string;
  mvaRating?: string;
  primaryVoltageCode?: string;
  secondaryVoltageCode?: string;
  primaryVoltageName?: string;
  secondaryVoltageName?: string;
  coolingType?: string;
  windingConfig?: string;
  breakerRating?: string;
  tapSetting?: string;
  tapStep?: string;
  tapStepOffLoad?: string;
  tapStepOnLoad?: string;
  tapRatio?: string;
  tapNumberMax?: string;
  tapNumberMin?: string;
  tapVoltageMax?: string;
  tapVoltageMin?: string;
  phaseDisplacement?: string;
  impedencePercentage?: string;
  leakReact?: string;
  resistance?: string;
  react?: string;
  remarks?:string;
	createdBy?:string;
	createdDate?:string;
	modifiedBy?:string;
	modifiedDate?:string;
  Enabled?:string;

}
export class CaptiveEnergyNeed {   //temporarily not in use 
  id?: string;
  gcId?: string;
  serviceNumber?: string;
  proposedQuantum?: string;
  genUnitId?: string;
}
export class DocCheckListItem {
  id?: string;
  gcId?: string;
  remarks?:string;
	createdBy?:string;
	createdDate?:string;
	modifiedBy?:string;
	modifiedDate?:string;
	Enabled?:string;
  documentCode?: string;
  documentDesc?: string;
  checkListCode?: string;
  checkListDesc?: string;
  docSubmitted?: string;
  docUrl?: string;
  constructor() {
    this.id = "";
    this.gcId = "";
    this.checkListCode = "";
    this.checkListDesc = "";
    this.docSubmitted = "";
    this.documentCode = "";
    this.documentDesc = "";
  }
}
export class ApplicationStatus {
  id?: string;
  gcStatusTypeCode?: string;
  gcStatusTypeName?: string;
  gcStatusUpdateDate?: string;
  gcStatusUpdateBy?: string;
  gcStatusRemarks?: string;
	createdBy?:string;
	createdDate?:string;
	modifiedBy?:string;
	modifiedDate?:string;
	Enabled?:string;
}
export class CaptiveUserContribution {
  id?: string;
  classOfShareHolder?: string;    // Previously companyName
  noOfEquityShares?:string;
  valueOfEquityShares?:string;
  amountOfEquityShares?:String;
  //prefShare?: string;
  noOfVotingRights?:string; 
  percentageHoldingInEquityShares?:string; //previously equityShare
  percentageHoldingInVotingRights?:string; 
  percentageHoldingInVotingWithEquity?:string ; 
  remarks?:string;
	createdBy?:string;
	createdDate?:string;
	modifiedBy?:string;
	modifiedDate?:string;
	Enabled?:string;
}

export class EquityShareVotingRights{
  id?: string;
  classOfEquityShares?: string;    
  noOfEquityShares?:string;
  valueOfEquityShares?:string;
  amountOfEquityShares?:String;
  //prefShare?: string;
  noOfVotingRights?:string; 
  percentageHoldingInEquityShares?:string; //previously equityShare
  percentageHoldingInVotingRights?:string; 
  percentageHoldingInVotingWithEquity?:string ;
  remarks?:string;
	createdBy?:string;
	createdDate?:string;
	modifiedBy?:string;
	modifiedDate?:string;
	Enabled?:string; 
}

export class QuantumAllocation{
  id?: string;
  gcId?: string;
  buyerCompServiceId?: string;
  buyerCompServiceName?: string;
  buyerCompServiceNumber?: string;
  buyerOrgId?: string;  
  buyerOrgCode?: string; 	// for display purpose - not stored
  buyerOrgName?: string;   // for display purpose - not stored
  captiveCompanyName?:string;
  quantum?:string;
  injectionVoltageCode?:string;
  injectionVoltageName?:string;
  drawalVoltageCode?:string;
  drawalVoltageName?:string;
  remarks?:string;
	createdBy?:string;
	createdDate?:string;
	modifiedBy?:string;
	modifiedDate?:string;
	Enabled?:string;
}

 