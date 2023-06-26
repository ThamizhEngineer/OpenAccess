export class Gs {

	id?:string;
	statusCode?:string;
	statusName?:string;
	remarks?:string;
	companyMeterId?:string;
	companyMeterNumber?:string;
	referenceNumber?:string;
	mrIds?:string;
	mf?:string;
    commissionDate?:string;
	machineCapacity?:string;
	statementGenerationDate?:string;
	statementMonth?:string;
	statementYear?:string;
	initStatementDate?:string;
	finalStatementDate?:string;
	rKvahInitial?:string;
	rKvahFinal?:string;
	rKvahDifference?:string;
	rKvahUnits?:string;
	kVahInitial?:string;
	kVahFinal?:string;
	kVahDifference?:string;
	kVahUnits?:string;
	totalImportGeneration?:string;
	totalExportGeneration?:string;
	orgId?:string;
	dispOrgName?:string;
	orgCode?:string;
	companyId?:string;
	dispCompanyName?:string;
	companyCode?:string;
	companyServiceId?:string;
	dispCompanyServiceNumber?:string;
	dispFuelTypeGroup?:string;
	dispFuelTypeCode?:string;
	dispFuelTypeName?:string;
	injectingVoltageCode?:string;
	injectingVoltageName?:string;
	powerFactor?:string;
	netGeneration?:string;
	totalChargedAmount?:string;
	netPayable?:string;
	tariffRate?:string;
	tariffNetAmount?:string;
	isCaptive?:string;
	isStb?:string;
	typeOfSS?:string;
	c1?:string;
	c2?:string;
	c3?:string;
	c4?:string;
	c5?:string;
	plantClassTypeCode?:string;
	plantClassTypeDesc?:string;
	flowTypeCode?:string;
	isRec?:string;
	totalSsLoss?:string;
	ssLossPercent?:string;
	substationId?:string;
	substationName?:string;
	mrSourceCode?:string;
	docId?:string;
	generationStatementCharges?:Array<GenerationStatementCharge>;
	generationStatementSlots?:Array<GenerationStatementSlot>;
}

export class GenerationStatementCharge{
	id?:string;
	remarks?:string;
	generationStatementId?:string;
	chargeCode?:string;
	chargeDesc?:string;
	isLumpSum?:string;
	unitCharge?:string;
	totalCharges?:string;
	netPayable?:string;
}


export class GenerationStatementSlot{
	id?:string;
	remarks?:string;
	generationStatementId?:string;
	referenceNumber?:string;
	slotCode?:string;
	slotName?:string;
	impInitial?:string;
	impFinal?:string;
	impDifference?:string;
	impUnits?:string;
	expInitial?:string;
	expFinal?:string;
	expDifference?:string;
	expUnits?:string;
	bankedBalance?:string;
	netUnits?:string;

}

export class GenerationStatementImportSlotForPrint{
	heading?:string;
	c1?:string;
	c2?:string;
	c3?:string;
	c4?:string;
	c5?:string;
	impInitial?:string;
	impFinal?:string;
	impDifference?:string;
	impUnits?:string;
	
}

export class GenerationStatementExportSlotForPrint{
	heading?:string;
	c1?:string;
	c2?:string;
	c3?:string;
	c4?:string;
	c5?:string;
	expInitial?:string;
	expFinal?:string;
	expDifference?:string;
	expUnits?:string;
	
}

export class GenerationSummary{
	heading?:string;
	rKvah?:string;	
	kVah?:string;

	kVahUnits?:string;
	importGeneration?:string;
	exportGeneration?:string;
	

}


export class ValidateGs{
	id?:string;
	statusCode?:string;	
	machineCapacity?:string;
	statementMonth?:string;
}
