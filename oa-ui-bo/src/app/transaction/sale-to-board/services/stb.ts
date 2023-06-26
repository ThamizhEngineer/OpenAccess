export class Stb {
  _id: string;
    header?: StbHeader;
	generationSummaries?: Array<GenerationSummary>;
	generationSummaryForPrint?:Array<GenerationSummaryForPrint>;
	charges?: Array<Charge>;

}

	/*seqBasedOnService?: string;
		 new sequence is max(seq in db)+1. 
		 elso ensure the current initalReadingDate should be right next to  finalReadingDate (of the last seq).    
		 this can have decimals to accomodate shorter periods left out in the middle.
		 	lets say due to technical difficulties; GenStatement for feb didnt arrive. but Mar arrived.
			 	 in that case; seq for Jan will be 1
				  				seq for Mar will be 2
								  seq for Feb (that arrived late) will be 1.1
	*/

export class StbHeader {
	companyId?: string;
	serviceNumber?: string;
	seqBasedOnService?: string; 
	generatorPlantName?: string;
	orgCode?: string;
	orgName?: string;
	multiplicationFactor?: string;
	load?: string;
	injectingVoltage?: string;
	initialReadingDate?: string; // will be hidden
	finalReadingDate?: string; //will be hidden
	month?: string;
	year? : string;
	netGeneration?: string;
	powerFactor?: string
}

export class GenerationSummary {
	unitDesc?: string;
	initial?: string;
	final?: string;
	difference?: string;
	generation?: string
}

export class GenerationSummaryForPrint {
	heading?: string;
	export?: string;
	import?: string;
	rKVAH?: string;
	iKVAH?: string;
	inital?:string;
	final?:string;
	difference?:string;
	generation?:string;
}

export class Charge {
	code?: string;
	desc?: string;
	isLumpSum?: string;
	unitCharge?: string;
	totalCharges?: string
}

