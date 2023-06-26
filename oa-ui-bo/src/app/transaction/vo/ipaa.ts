export class Ipaa{
    id?:string;
    sellerCompanyServiceId?:string;
    sellerCompanyServiceNumber?:string;
    sellerCompanyId?:string;
    sellerCompanyName?:string;
    sellerCompanyCode?:string;
    sellerOrgId?:string;
    sellerOrgName?:string;
    sellerOrgCode?:string;
    agmtPeriodCode?:string;
    fromDate?:string;
    toDate?:string;
    fromMonth?:string;
    toMonth?:string;
    fromYear?:string;
    toYear?:string;   
    isCaptive?:string;
    statusCode?:string;
    statusName?:string;
    esIntentId?:string;
	esIntentCode?:string;
	flowTypeCode?:string;
	proposedCapacity?:string;
    ipaaLines:Array<IpaaLine>;
}

export class IpaaLine{
	id: string;
	ipaaId: string;
	
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