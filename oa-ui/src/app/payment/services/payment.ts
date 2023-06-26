export interface Payment {
	_id: string;
	header:{
		paymentType: string,
		applicationNumber: string,
		appliedDate: string,
		approvedDate: string,
		activatedDate: string,
		duration: string,
		status: string,
		remarks?: string,
		disapprovedDate: string
	},
  bankDetails?: string,
  type?: string,
  instrumentNumber?: string,
  date?: string,
  amount?: string
	consumerDetails? : Service;
	generatorDetails?: Service;
}

export interface PaymentLine{
  bankDetails: string,
  type: string,
  instrumentNumber: string,
  date: string,
  amount: string
}

export interface Service{
	number?: string;
	orgName?: string;
	edcName?: string;
	injectionVoltage?: string;
	proposedSupply?: string	;
	installedCapacity?: string;
} 