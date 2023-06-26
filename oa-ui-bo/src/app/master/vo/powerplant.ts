import { Gc } from './../../grid-connectivity/services/gc';
import { Company } from './company';
import { Service } from './company.v1';
import { TradeRelationship } from './trade-relationship';


export class Powerplant{
  id?: string;
  isPrimary ?: string; // Y or N
  version?: string;
  code?: string;
  name?: string;
  plantTypeCode?: string;
  plantTypeName?: string;  // for display purpose - not stored
  fuelTypeCode?: string;
  fuelTypeName?: string;  // for display purpose - not stored
  orgId?: string; // for reference
  orgCode?: string; 	// for display purpose - not stored
  orgName?: string;   // for display purpose - not stored 
  serviceId ?: string; // for reference
  serviceNumber?: string;   // for display purpose - not stored
  gcId?: string;       //for reference
  companyId?: string; // for display purpose - not stored 
  companyCode?: string; 	// for display purpose - not stored
  companyName?: string;   // for display purpose - not stored
  totalCapacity?: string;
  subStationId?: string;
  subStationCode?: string;  // for display purpose - not stored
  subStationName?: string;  // for display purpose - not stored
  subStationVoltage?: string; // for display purpose - not stored
  interfaceVoltagePhase?: string;
  interfaceVoltageFrequency?: string;
  alreadyHtSupply?: string;   //not needed
  proposedCommissionDate?: string  
  status?: string;   // Default - Active
  enabled?: string;   // Y or N
  generators?: Array<Generator>;
  //postalAddress
  line1?: string;
  city?: string;
  stateCode?: string;
  stateName?: string; // for display purpose - not stored
  pinCode?: string; 
  village?: string;
  town?: string;
  talukCode?: string;
  talukName?: string; // for display purpose - not stored
  districtCode?: string;
  districtName?: string; // for display purpose - not stored
  //physicalLocation
  plSfNo?: string;
  plVillage?: string;
  plTown?: string;
  plTalukCode?: string;
  plTalukName?: string; // for display purpose - not stored
  plDistrictCode?: string;
  plDistrictName?: string; // for display purpose - not stored
  windPassCode?: string;
  windPassName?: string; // for display purpose - not stored
  windZoneAreaCode?: string;
  windZoneAreaName?: string; // for display purpose - not stored
  gc?: Gc;
  company?: Company;
  services: Array<Service>;
  commissionDate?:string;
  feederId?:string;
  feederName?:string;
  tradeRelationships?: Array<VtradeRelationship>;
  adBenefits?:string;

}
export class VtradeRelationship{
  id?:string;
quantum?:string;
fromDate?:string;
month?:string;
year?:string;
toDate?:string;
statusCode?:string;
statusName?:string;
sellerCompanyId?:string;
sellerCompanyName?:string;
sellerCompanyCode?:string;
sellerCompServiceId?:string;
sellerCompServiceNumber?:string;
buyerCompanyId?:string;
buyerCompanyName?:string;
buyerCompanyCode?:string;
buyerCompServiceId?:string;
buyerCompServiceNumber?:string;
referenceNumber?:string;
remarks?:string;
c1?:string;
c2?:string;
c3?:string;
c4?:string;
c5?:string;
isCaptive?:string;
peakUnits?:string;
offPeakUnits?:string;
intervalTypeCode?:string;
sharePercent?:string;
intervalTypeName?:string;
sellerOrgId?:string;
buyerOrgId?:string;
traderelationshipSourceCode?:string;
agreementType?:string;
buyerOrgName?:string
}
export class Generator{
    id?: string;
    generatorId?: string;
    name?: string;
    makeCode?: string; 
    makeName?: string; // for display purpose - not stored
    serialNumber?: string; 
    rotorDia?: string;
    hubHeight?: string;
    capacity?: string;
    refId?: string;
    voltageCode?: string; // might be used sooner. no clarity yet
    voltageName?:string;
    enabled?: string ; // Y or N
    noOfUnits?: string 
  }