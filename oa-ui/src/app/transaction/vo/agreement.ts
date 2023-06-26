
export class Agreement {
  id?: string;
  buyerCompanyName?: string;
  buyerCompanyServiceNumber?: string;
  buyerOrgName?: string;
  buyerOrgCode?: string;
  sellerCompanyName?: string;
  sellerCompanyServiceNumber?: string;
  sellerOrgName?: string;
  sellerOrgCode?: string;
  agreementPeriodCode?: string;
  agreementPeriodDesc?: string;
  fromDate?: string;
  toDate?: string;
  agreementDate?: string;
  type?: string;

  code?: string;
  sellerCompanyServiceId?: string;

  sellerCompanyId?: string;

  sellerCompanyCode?: string;
  sellerOrgId?: string;

  sellerSubstationid?: string;
  sellerSubstationCode?: string;
  sellerSubstationName?: string;
  sellerFeederId?: string;
  sellerFeederCode?: string;
  sellerFeederName?: string;
  version?: string;

  isActive?: string;


  fromMonth?: string;
  toMonth?: string;
  fromYear?: string;
  toYear?: string;
  totalUnits?: string;
  c1?: string;
  c2?: string;
  c3?: string;
  c4?: string;
  c5?: string;
  peakUnits?: string;
  offPeakUnits?: string;
  signatoryParty1?: string;
  signatoryParty2?: string;
  signatoryParty3?: string;
  hasLines?: string;
  buyerCompanyServiceId?: string;

  buyerCompanyId?: string;

  buyerCompanyCode?: string;
  buyerOrgId?: string;

  buyerSubstationid?: string;
  buyerSubstationCode?: string;
  buyerSubstationName?: string;
  buyerFeederId?: string;
  buyerFeederCode?: string;
  buyerFeederName?: string;
  versionChangeReasonCode?: string;
  versionChangeReasonName?: string;
  statusCode?: string;
  statusName?: string;

  agreementPeriodName?: string;
  flowTypeCode?: string;
  flowTypeName?: string;
  isLatest?: string;
  intervalTypeCode?: string;
  intervalTypeName?: string;
  sharePercent?: string;
  isCaptive?: string;
  esIntentId?: string;
  ewaId?: string;
  oaaId?: string; nocGeneratorId?: string;
  ipaaId?: string;
  enabled?: string;
  createdDate?: string;
  createdBy?: string;
  modifiedDate?: string;
  modifiedBy?: string;
  epaId?: string;
  csId?:string;
  agreementTypeName?:string;
  agreementLines?: Array<AgreementLine>;

}

export class AgreementLine {
  id?: string;
  code?: string;
  buyerCompanyServiceId?: string;
  buyerCompanyServiceNumber?: string;
  buyerCompanyId?: string;
  buyerCompanyName?: string;
  buyerCompanyCode?: string;
  buyerOrgId?: string;
  buyerOrgName?: string;
  buyerOrgCode?: string;
  buyerSubstationid?: string;
  buyerSubstationCode?: string;
  buyerSubstationName?: string;
  buyerFeederId?: string;
  buyerFeederCode?: string;
  buyerFeederName?: string;
  agreementId?: string;
  drawalVoltageCode?: string;
  drawalVoltageName?: string;
  proposedCapacity?: string;
  approvedCapacity?: string;
  agreementPeriodCode?: string;
  agreementPeriodName?: string;
  flowTypeCode?: string;
  flowTypeName?: string;
  fromDate?: string;
  toDate?: string; fromMonth?: string;
  toMonth?: string; fromYear?: string;
  toYear?: string;
  appliedDate?: string;
  approvedDate?: string;
  agreementDate?: string;
  isCaptive?: string;
  c1?: string;
  c2?: string;
  c3?: string;
  c4?: string;
  c5?: string;
  peakUnits?: string;
  offPeakUnits?: string;
  intervalTypeCode?: string;
  intervalTypeName?: string;
  sharePercent?: string;
  paymentTypeCode?: string;
  paymentTypeDesc?: string;
  paymentBankDetails?: string;
  paymentTxnNo?: string;
  paymentDate?: string;
  paymentAmount?: string;
  licensee?: string;
  stateTransLoss?: string;
  stateTransLossPercent?: string;
  stateTransCharges?: string;
  stateTransChargesPercent?: string;
  schedulingCharges?: string;
  schedulingChargesPercent?: string;
  systemOprCharges?: string;
  systemoprChargesPercent?: string;
  statusCode?: string; statusName?: string;
  esIntentLineId?: string;
  nocId?: string;
  consentId?: string;
  ewaLineId?: string;
  ipaaLineId?: string;
  nocGeneratorLineId?: string;
  standingClearenceId?: string;
  isTransmissionCharges?: string;
  maxDrawalCeiling?: string;
  isTransmissionLoss?: string;
  constructor(){
    this.maxDrawalCeiling="1";
    this.stateTransLossPercent="0.0307";
    this.stateTransChargesPercent="126.55";
    this.schedulingChargesPercent="160";
    this.systemoprChargesPercent="33.84";
}
}