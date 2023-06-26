
export class WegWithBuyerReport{
 id?: string;
 powerplantName?: string;
 orgId?: string;
 orgName?: string;
 companyId?: string;
 companyName?: string;
 serviceId?: string;
 serviceNumber?: string;
 voltageCode?: string;
 voltageName?: string;
 windPassCode?: string;
 windPassName?: string;
 makeCode?: string;
 makeName?: string;
 capacity?: string;
 noOfUnits?: string;
 rate?: string;
 commissionDate?: string;
 buyerAgreements: Array<BuyerAgreement>;

}
export interface  BuyerAgreement {

	 id?: string;
	 sellerServiceId?: string;
	 buyerOrgName?: string;
	 buyerCompanyName?: string;
	 buyerServiceNumber?: string;
	 quantumAgreed?: string;
  }