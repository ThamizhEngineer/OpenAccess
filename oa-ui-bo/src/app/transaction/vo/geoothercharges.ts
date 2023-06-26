export class GenOtherCharges {
	id: string;
	sellerCompanyId: string;
	sellerCompanyCode: string;
	sellerCompanyName: string;
	sellerCompanyServiceId: string;
	sellerCompanyServiceNumber: string;
	sellerOrgId: string;
	sellerOrgCode: string;
	sellerOrgName: string;
	sellerSubstationId: string;
	sellerSubstationCode: string;
	sellerSubstationName: string;
	month: string;
	year: string;
	chargeCode: string;
	chargeDesc: string;
	chargeTypeCode: string;
	unitCharge: string;
	totalCharges: string;
	remarks: string;
	enabled: string;
	createdBy: string;
	createdDt: string;
	modifiedBy: string;
	modifiedDt: string;
	genOtherSubCharges?:Array<GenOtherSubCharge>;
}
export class GenOtherSubCharge{
	id:string;
	tGenOtherChargeId:string;
	chargeCode:string;
	chargeDesc:string;
	chargeTypeCode:string;
	unitCharge:string;
	totalCharge:string;
	remarks:string;
	enabled:string;
	createdBy: string;
	createdDt: string;
	modifiedBy: string;
	

}