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
	fuelTypeCode?:string;
	fuelTypeName?:string;
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
	iecRate?:string;
	etaxRate?:string;
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



export class MeterChangeMc {
    
	diffExpC1: string; 
	diffExpC2: string;
	diffExpC3: string; 
	diffExpC4: string; 
	diffExpC5: string; 
	diffImpC1: string; 
	diffImpC2: string; 
	diffImpC3: string; 
	diffImpC4: string; 
	diffImpC5: string; 
	expFinC1: string; 
	expFinC2: string; 
	expFinC3: string; 
	expFinC4: string; 
	expFinC5: string; 
	expInitC1: string; 
	expInitC2: string; 
	expInitC3: string; 
	expInitC4: string;
	expInitC5: string;
	id: number; 
	impFinC1: string;
	impFinC2: string; 
	impFinC3: string; 
	impFinC4: string; 
	impFinC5: string; 
	impInitC1: string; 
	impInitC2: string; 
	impInitC3: string; 
	impInitC4: string; 
	impInitC5: string; 
	netExpC1: string; 
	netExpC2: string; 
	netExpC3: string; 
	netExpC4: string; 
	netExpC5: string; 
	netImpC1: string; 
	netImpC2: string; 
	netImpC3: string; 
	netImpC4: string; 
	netImpC5: string; 
	readingMonth: string; 
	readingType: string; 
	readingYear: string; 
	remarks: string;
	serviceNo: string; 
	netc1:string;
	netc2:string;
	netc3:string;
	netc4:string;
	netc5:string;
	meterno:string;



}

