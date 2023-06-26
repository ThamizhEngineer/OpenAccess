export interface Tariff { 
	_id: string;
	type: string;
	fromDate:string;
	toDate: string;
	wegGroup: string;
	rate:string;
	reference: string;
}