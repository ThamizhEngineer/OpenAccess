export class NocGenerator{
    id ?:string;
    code ?:string;
    esIntentId ?:string;
    ipaaId ?:string;
    ipaaQuantum ?:string;
    ipaaApprovedDate ?:string;
    orgId ?:string;
    orgCode ?:string;
    orgName ?:string;
    substationName ?:string;
    substationId ?:string;
    substationCode ?:string;
    feederName ?:string;
    feederId ?:string;
    feederCode ?:string;
    fuelTypeCode ?:string;
    fuelTypeName ?:string;
    capacity ?:string;
    auxiliaryConsumption ?:string;
    inHouseConsumption ?:string;
    exBus ?:string;
    approvedPowerCapacity ?:string;
    isOnlineDataMonitoring ?:string;
    isTangedco ?:string;
    tangedcoRefNumber ?:string;
    tangedcoApprovedQuantum ?:string;
    tangedcoDated ?:string;
    tangedcoTillDate ?:string;
    captiveQuantum ?:string;
    thirdPartyQuantum ?:string;
    traderQuantum ?:string;
    otherQuantum ?:string;
    totalPowerSaleCommitments ?:string;
    maximumSurplusQuantum ?:string;
    sellerCompanyServiceId?:string;
    sellerCompanyServiceNumber?:string;
    sellerCompanyId?:string;
    sellerCompanyName?:string;
    sellerCompanyCode?:string;
    sellerInjectingVoltageCode?:string;
    sellerInjectingVoltageName?:string;
    nocGeneratorLines:Array<NocGeneratorLine>;

}

export class NocGeneratorLine{
	id: string;
	nocGeneratorId: string;

	buyerOrgId: string;
	buyerOrgName: string;
	buyerCompServiceId: string;
	buyerCompServiceNumber: string;
	buyerCompanyName: string;
	buyerCompanyId: string;
	buyerCapacity: string;
	drawalVoltageCode: string;
	drawalVoltageDesc: string;
	injectionPeakUnits: string;
	injectionOffPeakUnits: string;
	loss: string;
	drawalPeakUnits: string;
	drawalOffPeakUnits: string;
	appliedDate: string;
	approvedDate: string;
	statusCode: string;
	statusDesc: string;

	remarks: string;
	createdBy: string;
	createdDate: string;
	modifiedBy: string;
	modifiedDate: string;
	proposedUnits:string;
	approvedUnits:string;
	isCaptive:string;
}