
export class Company {
  id: string;
  code?: string;
  companyName?: string;
  companyTypeCode?: string;  //person, co-op, associate-of-person, others
  typeName?: string;
  registrationNo?: string;
  registrationDate?: string;
  cobDate?: string;
  incorpPlace?: string;
  isCaptive?:string;
  captivePurpose?: string;
  pan?: string;
  tan?: string;
  cst?: string;
  gst?: string;
  enabled?: string;
  remarks?:string;
  isInternal?:string;
  bankingServiceId?:string;
  unadjustedServiceId?:string;
  bankingServiceNumber?:string;
  unadjustedServiceNumber?:string;
  isBuyer?:string;
  isSeller?:string;
  employees: Array<Employee>;
  locations: Array<any>;
  addresses: Array<Address>;
  contacts: Array<Contact>;
  shareHolders: Array<ShareHolder>;
  services: Array<Service>;
  meters: Array<Meter>;
  constructor() {
    this.id = "";
    }
}

export class KeyNamePair {
  key: string;
  name: string;
}

export class Address {
  addressNo: string;
//  type: {key:string, name:string};//HeadOffice, RegisteredOffice, SiteOffice
//  type: KeyNamePair;//HeadOffice, RegisteredOffice, SiteOffice
  type: string;
  line1: string;
  city: string;
  district: string;
  state: string;
  pinCode: string;
  enabled: boolean;
}

export class Contact {
  contactNo: string;
  purpose: string;//Correspondance, Others
  name: string;
  designation: string;
  landline: string;
  mobile: string;
  email: string;
  enabled: boolean;
}

export class Employee {
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

export class  Meter{
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
  remarks?: string;
  createdBy?: string;
  createdDate?: string;
  modifiedBy?: string;
  modifiedDate?: string;
  modemNumber?: string;
  enabled?: string;
  meterCt1?: string;
  meterCt2?: string;
  meterCt3?: string;
  meterPt1?: string;
  meterPt2?: string;
  meterPt3?: string;
  importRemarks?: string;
  ctRatio?: string;
  ptRatio?: string;


}


export class Service {
  id?: string;
  type?: string; //Generator, Consumer   /*Now changed to buyer,banking,seller from list-code*/
  code?: string;
  typeCode?:string;
  typeName?:string;
  companyId?: string;
  companyCode?: string; //used for view only
  companyName?: string; //used for view only
  number: string;
  orgId?: string;
  orgCode?: string; //used for view only
  orgName?: string; //used for view only
  capacity?: string;
  feederId?: string;
  feederCode?: string;
  feederName?: string;
  substationId?: string;
  substationCode?: string;
  substationName?: string;
  refNumber: string; // Generator-->GridAppln#, Consumer-->None
  voltageCode: string; // applicable for Consumer only
  voltageName: string; // applicable for Consumer only
  tariff: string;
  enabled: boolean;
  fuelTypeCode?: string;
  fuelTypeName?: string;
  meters: Array<Meter>;
  isRec?: string;
  contactFullName?: string;
  contactDesignation?: string;
  contactEmail?: string;
  contactPhoneNo?: string;
  regOfficeAddr?: string;
  plantAddr?: string;
  isContactVerified?: string;
  contactLastVerifiedDate?: string;
  companyTypeName?: string;
  companyTypeCode?: string;
  pan?: string;
  cin?: string;
  llpin?: string;
  gst?: string;
  tan?: string;
  bankIfscCode?:string
  bankName?:string;
  bankAccountNo?:string;
}

export class  ShareHolder {

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
