export class Epa {

	id?: string;
	code?: string;
	agreementPeriodCode?: string;
	agreementPeriodDesc?: string; //(display)
	agreementDate?: string;
	agreementNumber?: string;
	fromDate?: string;
	toDate?: string;
	periodDuration?: string; //(calculation)
	sellerOrgId?: string;
	sellerOrgCode?: string;
	sellerOrgName?: string;
	sellerCompServiceId?: string;
	sellerCompServiceNumber?: string; //(display based   sellerCompServiceId)
	sellerCompanyName?: string; //(display based on sellerCompServiceId)
	sellerCompanyId?: string; //(display based on sellerCompServiceId)
	sellerIsCaptive?: string; //(Y/N)
	sellerSubstationId?: string;
	sellerSubstationName?: string; //(display)
	sellerSubstationCode?: string;
	voltageCode?: string;
	voltageDesc?: string; //(display)
	sellerFeederId?: string;
	sellerFeederCode?: string;
	sellerFeederName?: string; // (display)
	appliedDate?: string;
	approvedDate?: string;
	statusCode?: string;
	statusDesc?: string; // (display)
	epaNumber?: string;
	epaAppNumber?: string;
	proposedTotalUnits?: string;
	approvedTotalUnits?: string;
	totalGenCapacity?: string;
	remarks?: string;
	createdBy?: string;
	createdDate?: string;
	modifiedBy?: string;
	modifiedDate?: string;
	tEsIntentId?: string;
	districtCode?: string;
	districtName?: string;
	fuelTypeCode?: string;
	fuelTypeName?: string;
	c1?: string;
	c2?: string;
	c3?: string;
	c4?: string;
	c5?: string;
	peakUnits?: string;
	offPeakUnits?: string;
	fromMonth?: string;
	toMonth?: string;
	fromYear?: string;
	toYear?: string;
	buyerCompanyServiceId?: string;
	buyerCompanyServiceNumber?: string;
	buyerCompanyId?: string;
	buyerCompanyName?: string;
	buyerCompanyCode?: string;
	buyerOrgId?: string;
	buyerOrgName?: string;
	buyerOrgCode?: string;
	buyerSubstationId?: string;
	buyerSubstationCode?: string;
	buyerSubstationName?: string;
	buyerFeederId?: string;
	buyerFeederCode?: string;
	buyerFeederName?: string;
	intervalTypeCode?: string;
	intervalTypeName?: string;
	sharePercent?: string;
	flowTypeCode?: string;
	flowTypeName?: string;
	esIntentId?:string;
	esIntentCode?:string;
	epaLines: Array<Epa>;
}

export class EpaLine {

	id: string;
	tEpaId: string;
	proposedTotalUnits: string;
	approvedTotalUnits: string;
	createdBy: string;
	createdDate: string;
	modifiedBy: string;
	modifiedDate: string;
	mGeneratorId: string;
	noOfUnits: string;
	genCapacity: string;
	generatorName: string;
	powerplantId: string;
	commsionDate: string;
	districtCode: string;
	districtName: string;
	remarks: string;

}