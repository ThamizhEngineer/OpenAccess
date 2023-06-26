import {Gs} from  '../../transaction/vo/gs';


export class Es{
    id?:string;
    generationStatementId?:string;
    sellerCompanyServiceId?:string;
    sellerCompanyServiceNumber?:string;
    sellerEndOrgId?:string;
    sellerEndOrgName?:string;
    sellerEndOrgCode?:string;
    sellerCompanyId?:string;
    sellerCompanyName?:string;
    sellerCompanyCode?:string;
    allowLowerSlotAdmt?:string;
    month?:string;
    year?:string;
    injectingVoltageCode?:string;
    injectingVoltageName?:string;
    fromDate?:string;
    toDate?:string;
    loss?:string;
    multipleBuyers?:string;
    usageDetailAvail?:string;
    simpleEnergySale?:string;
    c1?:string;
	c2?:string;
	c3?:string;
	c4?:string;
    c5?:string;
    bc1?:string;
	bc2?:string;
	bc3?:string;
	bc4?:string;
    bc5?:string;
    gc1?:string;
	gc2?:string;
	gc3?:string;
	gc4?:string;
    gc5?:string;
    availc1?:string;
    availc2?:string;
    availc3?:string;
    availc4?:string;
    availc5?:string;
    availGc1?:string;
    availGc2?:string;
    availGc3?:string;
    availGc4?:string;
    availGc5?:string;
    availBc1?:string;
    availBc2?:string;
    availBc3?:string;
    availBc4?:string;
    availBc5?:string;
    total?:string;
    netGeneration?:string;
    netAllocation?:string;
    totalBankUnitsUsed?:string;
    statusCode?:string;
    isStb?:string;
    allowLowerSlotAdjustment ?:string;
    gs:Gs;
    sanctionedCapacity?:string;
    fuelTypeCode?:string;
    fuelTypeName?:string;
  
    energySaleUsageDetails?:Array<EsUsageDetail>;
    energySaleUsageSummaries?:Array<EsUsageSummary>;
	fuelGroupe?: string;
    constructor(){
        this.c1="0";
        this.c2="0";
        this.c3="0";
        this.c4="0";
        this.c5="0";
        this.bc1="0";
        this.bc2="0";
        this.bc3="0";
        this.bc4="0";
        this.bc5="0";
        this.gc1="0";
        this.gc2="0";
        this.gc3="0";
        this.gc4="0";
        this.gc5="0";
        this.total="0";
    }
}
export class EsUsageDetail{
    id?:string;
    energySaleId?:string;
    buyerCompanyServiceId?:string;
    buyerCompanyServiceNumber?:string;
    buyerCompanyId?:string;
    buyerCompanyName?:string;
    buyerCompanyCode?:string;
    usageDate?:string;
    c1?:string;
    c2?:string;
    c3?:string;
    c4?:string;
    c5?:string;
    total?:string;
}
export class EsUsageSummary{
    id?:string;
    quantum?:string;
    tradeRelationshipId?:string;
    energySaleId?:string;
    buyerEndOrgId?:string;
    buyerEndOrgName?:string;
    buyerEndOrgCode?:string;
    buyerCompanyServiceId?:string;
    buyerCompanyServiceNumber?:string;
    buyerCompanyId?:string;
    buyerCompanyName?:string;
    buyerCompanyCode?:string;
    buyerEndSsid?:string;
    buyerEndSsName?:string;
    buyerEndSsCode?:string;
    c1?:string;
    c2?:string;
    c3?:string;
    c4?:string;
    c5?:string;
    total?:string;
    drawalVoltageCode?: string;
    sharePercent?: string;
    intervalTypeCode?:string;
    c001Id?: string;c001ChargeCode?: string;c001TotalCharge?: string;
    c002Id?: string;c002ChargeCode?: string;c002TotalCharge?: string;
	c003Id?: string;c003ChargeCode?: string;c003TotalCharge?: string;
	c004Id?: string;c004ChargeCode?: string;c004TotalCharge?: string;
	c005Id?: string;c005ChargeCode?: string;c005TotalCharge?: string;
    c006Id?: string;c006ChargeCode?: string;c006TotalCharge?: string;
    c007Id?: string;c007ChargeCode?: string;c007TotalCharge?: string;
    c008Id?: string;c008ChargeCode?: string;c008TotalCharge?: string;
    c009Id?: string;c009ChargeCode?: string;c009TotalCharge?: string;
    unitCost?: string;
    totalAmountPayable?: string;
    totalAmountChargable?: string;
    netAmountPayable?: string;
    agreementType?: string;
    constructor(){
        this.c1 = '0';
        this.c2 = '0';
        this.c3 = '0';
        this.c4 = '0';
        this.c5 = '0';
        this.c001TotalCharge='0';
        this.c002TotalCharge='0';
        this.c003TotalCharge='0';
        this.c004TotalCharge='0';
        this.c005TotalCharge='0';
        this.c006TotalCharge='0';
        this.c007TotalCharge='0';
       
    }
}

export class generationStatementForPrint{
    heading?:string;
    c1?:string;
    c2?:string;
    c3?:string;
    c4?:string;
    c5?:string;
    c1Name?:string;
    c2Name?:string;
    c3Name?:string;
    c4Name?:string;
    c5Name?:string;
    total?:string;
}
export class AvailableGsUnits{
    heading?:string;
    c1?:string;
    c2?:string;
    c3?:string;
    c4?:string;
    c5?:string;
    total?:string;
}

export class AllocatedGsUnits{
    heading?:string;
    c1?:string;
    c2?:string;
    c3?:string;
    c4?:string;
    c5?:string;
    total?:string;
}


