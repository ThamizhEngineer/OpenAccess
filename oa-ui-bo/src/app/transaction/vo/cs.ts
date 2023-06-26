export class Cs {
    id?: string;
    code?: string;
    sellerCompanyServiceId?: string;
    sellerCompanyServiceNumber?: string;
    sellerCompanyId?: string;
    sellerCompanyName?: string;
    sellerCompanyCode?: string;
    sellerOrgId?: string;
    sellerOrgName?: string;
    sellerOrgCode?: string;
    sellerSubstationId?: string;
    sellerSubstationCode?: string;
    sellerSubstationName?: string;
    sellerFeederId?: string;
    sellerFeederCode?: string;
    sellerFeederName?: string;
    agreementDate?: string;
    effectiveDate?: string;
    fromDate?: string;
    toDate?: string;
    fromMonth?: string;
    toMonth?: string;
    fromYear?: string;
    toYear?: string;
    approvedCapacity?: string;
    voltageCode?: string;
    voltageName?: string;
    statusCode?: string;
    statusName?: string;
    agreementPeriodCode?: string;
    agreementPeriodName?: string;
    flowTypeCode?: string;
    flowTypeName?: string;
    idTotalCost?: string;
    idTotalCurrency?: string;
    idTotalExchangeRate?: string;
    fuelTypeName?:string;
    csOwnPercCaptive?:string;
    esIntentId?:string;
    esIntentCode?:string;
    csCaptiveUserContributions?:Array<CsCaptiveUserContribution>;
    csQuantumAllocations?:Array<CsQuantumAllocation>;
    csEquityShareVotingRights?:Array<CsEquityShareVotingRights>;
    csLoans?:Array<CsLoan>;
}

export class CsLoan {
    id?:string;
    csId?: string
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

  export class CsCaptiveUserContribution {
    id?: string;
    csId?: string
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
  
  export class CsEquityShareVotingRights{
    id?: string;
    csId?: string
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
  
  export class CsQuantumAllocation{
    id?: string;
    csId?: string;
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