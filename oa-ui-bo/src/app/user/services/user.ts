
// import { Gs } from './../../generator-statement/services/gs';
export interface User{
  //this _id will be dropped at a later point
  _id?: string;
  userName?: string;
  password?: string;
  firstName?: string;
  lastName?: string;
  userType?: string; //A(admin) or I(Internal) or E(external)
  systemRefKey?:string; //orgCode or companyCode
  //this systemKey will be dropped at a later point
  systemKey?: string; //OA or any other system name
  orgCode?: string;  // used by OA-UI and OA-services
  companyCode?: string; // used by OA-UI and OA-services
  //this userToken will be dropped at a later point
  userToken?: string;
  //this unique id is generated for secrity purpose
  uniqueId?:string;
  //this userAccess will be dropped at a later point
  userAccess?:{
    systemKey?: string;
    functionality?: string;
    feature?: string;
  }
  accessList?:{
    functionality:string,
	  feature:string,
	  systemKey:string,
	  orgType:string
  }
  systemKeyCode?:string;
  id?: string;
  token?: string;
  //gs? : Gs;
}
