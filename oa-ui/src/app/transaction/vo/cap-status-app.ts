import { Loan, EquityShareVotingRights, CaptiveUserContribution, QuantumAllocation  } from './../../grid-connectivity/services/gc';
import { Agreement } from   './agreement';
export class CaptiveStatusApp {
	_id?: string;
	id?: string;
	applnNumber?: string; // optionally generated to share as an id with user
	applnStatusCode?: string;
	applnStatusName?: string; //display only
    applnStageCode?: string;
    applnStageName?: string;
	appliedDate?: string;
	approvedDate?: string;
	completedDate?: string;

	// Company details
	orgId?: string;
	orgCode?: string;//display only
	orgName?: string;//display only
	companyId?: string;//display only
	companyCode?: string;//display only
	companyName?: string;	//display only
	companyVersionNo?: string;//display only
	compServiceId?: string;	
	compServiceNumber?: string;	//display only
	totalCapacity?: string;	//display only
	captiveCapacity?:string; //will be updated in comp service table
	
	//Powerplant
	generatingTypeCode?: string;
	generatingTypeName?: string;
    plSfNo?: string;
    plVillage?: string;
    plTown?: string;
    plTalukCode?: string;
    plTalukName?: string; // for display purpose - not stored
    plDistrictCode?: string;
    plDistrictName?: string; // for display purpose - not stored
    
    // Investment Details
    
    idTotalCost?: string;
    idTotalCurrency?: string;
    idTotalExchangeRate?: string;
    idproposedDebtEquityRatio?: string;
    idLoans: Array<Loan>;
    idEquityShareVotingRights?: Array<EquityShareVotingRights>;
    idCaptiveUserContributions?: Array<CaptiveUserContribution>;
    captiveQuantumAllocation?: Array<QuantumAllocation>;
	existingAgreements?:Array<Agreement>;
		
}