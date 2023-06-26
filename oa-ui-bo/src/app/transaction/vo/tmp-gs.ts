export class TmpGs {

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
	tempGenStmtCharges?:Array<TmpGenerationStatementCharge>;
	tempGenStmtSlots?:Array<TmpGenerationStatementSlot>;
}

export class TmpGenerationStatementCharge{
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


export class TmpGenerationStatementSlot{
	id?:string;
	remarks?:string;
	generationStatementId?:string;
	referenceNumber?:string;
	slotCode?:string;
	slotName?:string;
	impInit?:string;
	impFinal?:string;
	impDiff?:string;
	impUnits?:string;
	expInit?:string;
	expFinal?:string;
	expDiff?:string;
	expUnits?:string;
	bankedBalance?:string;
	netUnits?:string;

}

export class TmpGenerationStatementImportSlotForPrint{
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

export class TmpGenerationStatementExportSlotForPrint{
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

export class TmpGenerationSummary{
	heading?:string;
	rKvah?:string;	
	kVah?:string;

	kVahUnits?:string;
	importGeneration?:string;
	exportGeneration?:string;
	

}