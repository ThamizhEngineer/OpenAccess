export class EsOrder {
    id?: string;
    energySaleId?: string;
    sellerCompanyServiceId?: string;
    sellerEndOrgId?: string;
    year?: string;
    month?: string;
    injectingVoltageCode?: string;
    injectingVoltageName?: string;
    fromDate?: string;
    toDate?: string;
    loss?: string;
    multipleBuyers?: string;
    usageDetailAvail?: string;
    simpleEnergySale?: string;
    totalC1?: string;
    totalC2?: string;
    totalC3?: string;
    totalC4?: string;
    totalC5?: string;
    totalGenUnitsSold?: string;
    totalBc1?: string;
    totalBc2?: string;
    totalBc3?: string;
    totalBc4?: string;
    totalBc5?: string;
    totalBankingUnitsSold?: string;
    totalUnitsSold?: string;
    totalGc1?: string;
    totalGc2?: string;
    totalGc3?: string;
    totalGc4?: string;
    totalGc5?: string;
    companyName?: string;
    companyCode?: string;
    companyServiceNumber?: string;
    orgName?: string;
    orgCode?: string;
    createdDate?: string;
    fuelTypeCode?: string;
    fuelTypeName?: string;
    energySaleOrderLines?: Array<EnergySaleOrderLines>;
    energyCharge?: Array<EnergyCharge>;
  

}
export class EnergySaleOrderLines {

    id?: string;
    energySaleOrderId?: string;
    energySaleId?: string;
    buyerEndOrgId?: string;
    buyerEndOrgName?: string;
    buyerEndOrgCode?: string;
    buyerEndSsId?: string;
    buyerEndSsName?: string;
    buyerCompanyServiceId?: string;
    buyerCompanyServiceNumber?: string;
    c1?: string;
    c2?: string;
    c3?: string;
    c4?: string;
    c5?: string;
    bc1?: string;
    bc2?: string;
    bc3?: string;
    bc4?: string;
    bc5?: string;
    bankingUnitsSold?: string;
    totalUnitsSold?: string;
    genUnitsSold?: string;
    buyerCompanyName?: string;
    buyerCompanyCode?: string;
    unitCost?: string;
    totalAmountPayable?: string;
    totalAmountChargable?: string;
    netAmountPayable?: string;
   
}

export class EnergyCharge {
    id?: string;
    energySaleOrderId?: string;
    remarks?: string;
    chargeCode?: string;
    chargeDesc?: string;
    chargeTypeCode?: string;
    unitCharge?: string;
    totalCharges?: string;
    companyServiceNumber?:string;
    companyName?: string;

}
export class EsOrderForPrint {
    heading?: string;
    c1?: string;
    c2?: string;
    c3?: string;
    c4?: string;
    c5?: string;
    total?: string;
    totalC1?: string;
    totalC2?: string;
    totalC3?: string;
    totalC4?: string;
    totalC5?: string;
    totalGenUnits?: string;
    totalGc1?: string;
    totalGc2?: string;
    totalGc3?: string;
    totalGc4?: string;
    totalGc5?: string;
}

export class EsOrderChargeForPrint{
    C001TotalCharge?: string;
    C002TotalCharge?: string;
    C003TotalCharge?: string;
    C004TotalCharge?: string;
    C005TotalCharge?: string;
    C006TotalCharge?: string;
    C007TotalCharge?: string;
    C008TotalCharge?: string;
    C009TotalCharge?: string;
    companyServiceNumber?: string;
    companyName?: string;
}
