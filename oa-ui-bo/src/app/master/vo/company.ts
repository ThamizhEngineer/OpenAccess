import { Org } from './../../master/vo/org';

export interface Item1 {
  _id: string;
  itemName: string;
  itemQuantity: number;
}

export interface Company { 
  _id: string;
  companyId: string;//code in db
  code?: string;
  companyName: string; 
  companySetup: string;  //person, co-op, associate-of-person, others
  registrationNumber: string;
  pan?: string;
  tan?: string;
  cst?: string;
  gst?: string;
  registrationDate: string;
  cobDate: string;
  placeOfIncorp: string;
  isCaptive:string;
  captivePurpose?: string;
  addresses: Array<Address>;
  contacts: Array<Contact>;
  employees: Array<Employee>;
  shareHolders: Array<ShareHolder>;
  services: Array<Service>;
  meters: Array<Meter>;
}

export interface KeyNamePair {
  key: string;
  name: string;
}

export interface Address {
  addressNo: string;
//  type: {key:string, name:string};//HeadOffice, RegisteredOffice, SiteOffice
//  type: KeyNamePair;//HeadOffice, RegisteredOffice, SiteOffice
  type: string
  line1: string;
  city: string;
  district: string;
  state: string;
  pinCode: string;
  enabled: boolean;
}

export interface Contact {
  contactNo: string;
  purpose: string;//Correspondance, Others
  name: string;
  designation: string;
  landline: string;
  mobile: string;
  email: string;
  enabled: boolean;
}

export interface Employee {
  employeeNo: string;
  designation: string;
  name: string;
  landline: string;
  mobile: string;
  email: string;
  fax: string;
  line1: string;
  city: string;
  district: string;
  state: string;
  pinCode: string;
  enabled: boolean;
}

export interface  Meter{      
  id?: string;
  serviceId?: string;
  meterNumber?: string;
  meterMakeCode?: string; 
  meterMakeName?: string;
  accuracyClassCode?: string;
  accuracyClassName?: string;
  isAbtMeter?: string;
  multiplicationFactor?: string;
  companyId?: string;
  companyName?: string;
  serviceNumber?: string;
  serviceTypeCode?: string;
  serviceTypeName?: string;
  modemNumber?: string;
  meterCt1?: string;
  meterCt2?: string;
  meterCt3?: string;
  meterPt1?: string;
  meterPt2?: string;
  meterPt3?: string;
  ctRatio?: string;
  ptRatio?:string;


}


export interface Service {
  type: string; //Generator, Consumer   /*Now changed to buyer,banking,seller from list-code*/
  typeCode?:string;
  typeName?:string;
  companyId?: string;
  companyCode?: string; //used for view only
  companyName?: string; //used for view only
  number: string;
  org?: Org;  //used for view only -  check if this is used
  orgId?: string;
  orgCode?: string; //used for view only
  orgName?: string; //used for view only
  sanctionedQuantum: string;
  tariff: string;
  proposedSupply?: string; //used for view only
  installedCapacity?: string; //used for view only
  voltage: string; // applicable for Consumer only
  refNumber: string; // Generator-->GridAppln#, Consumer-->None
  ssId?: string;
  feeder?: string;
  enabled: boolean;
  isRec?:boolean;
  meters: Array<Meter>;

}

export interface  ShareHolder {

  id?: string;
  share?: string;
  companyId?: string;
  companyCode?: string;
  companyName?: string; 
  shareHolderCompanyId?: string;
  shareHolderCompanyCode?: string;
  shareHolderCompanyName?: string;
  measure?: string;
  enabled?: string;

}