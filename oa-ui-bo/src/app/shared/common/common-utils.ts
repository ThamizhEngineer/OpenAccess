import {labels} from '../../../assets/data/labels'; 
import { Headers } from '@angular/http';
import { AuthUser } from '../../user/services/authuser';
export class CommonUtils {
  floatRegex = new RegExp("[0-9]+(\.[0-9]{1,4}?)?");
    constructor(){
      //this.http._defaultOptions.headers.set('Authorization', 'token');

    }

    public replaceIfInvalid( checkString: string){ 
      checkString = checkString==null?"0.0": checkString.toString().replace("[0-9]+(\.[0-9]{1,4}?)?", "");
      checkString = checkString==""?  "0.0": checkString;
      // if invalid data replace with 0.0
      if(!this.floatRegex.test(checkString)){ 
        checkString = "0.0";
      }
      return checkString;
    }
  /**
   * 
   * @param dateUIFormat - Input Date format - dd/mm/yyyyTHH24:mi:ss or dd/mm/yyyy
   * output - date in the format-->yyyy-mm-dd
   */
  static formatDateForDB( dateUIFormat: String){
  
    var arr = dateUIFormat.substring(0,10).split("/");
    return arr[2] + "-" + arr[1] + "-" + arr[0];
  }

  /**
   * 
   * @param dateDBFormat - Input Date format - yyyy-mm-ddHH24:mi:ss or yyyy-mm-dd
   * output - date in the format-->dd/mm/yyyy
   */

  static formatDateForUI(dateDBFormat: String){
    var arr = dateDBFormat.substring(0,10).split("-");
    return arr[2] + "/" + arr[1] + "/" + arr[0];
  }

  getHeaders(){
    let headers = new Headers({ 'Content-Type': 'application/json'});
    let user : AuthUser = JSON.parse(sessionStorage.getItem('user'));
    headers.append('username', user.userName);
    headers.append('token', user.token);
    headers.append('systemkeycode', user.systemKeyCode);
    return headers;
  }

  static getLoginUserTypeName(){
    var user = JSON.parse(sessionStorage.getItem('user'));
    if(user.isSuperUser=="Y"){
      return "Super User";
    }
    else
      {
        return user.userTypeName;
      }
    // return user.systemRefKey;
  }

  static getLoginUserDetails(){
    var user = JSON.parse(sessionStorage.getItem('user'));
    if(user.userName==null){
      return "";
    }
    else
      {
        return user.userName;
      }
    // return user.systemRefKey;
  }

 static getLoginUserEDC(){
    var user = JSON.parse(sessionStorage.getItem('user'));
  
    if(user.edcCode==null){
      return "";
    }
    else
      {
        return user.edcCode.trim();
      }
    // return user.systemRefKey;
  }  

  static getLoginUserCompany(){
    var user = JSON.parse(sessionStorage.getItem('user'));
   
    if(user.firstName==null){
      return "";
    }
    else
      {
        return user.firstName.trim();
      }
    // return user.systemRefKey;
  }

  static getLoginFuelTypeName(){
    var user = JSON.parse(sessionStorage.getItem('user'));
   
    if(user.fueltypename==null){
      return "";
    }
    else
      {
        return user.fueltypename.trim();
      }
    // return user.systemRefKey;
  }


  static getLoginUserServiceNumber(){
    var user = JSON.parse(sessionStorage.getItem('user'));
   
    if(user.companyServiceId==null){
      return "";
    }
    else
      {
        return user.companyServiceId.trim();
      }
    // return user.systemRefKey;
  }

  static getLoginUserCompanyId(){
    var user = JSON.parse(sessionStorage.getItem('user'));
    console.log("user");
    console.log(user);
   
    if(user.companyId==null){
      console.log("user company id"+user.companyId);
      return "";
    }
    else
      {
        console.log("user company id"+user.companyId);
        return user.companyId.trim();
      }
    // return user.systemRefKey;
   }

  static getLoginUserTypeCode(){
    var user = JSON.parse(sessionStorage.getItem('user'));
   
    if(user.userTypeCode==null){
      return "";
    }else 
      {
        return user.userTypeCode.trim();
      }
    // return user.systemRefKey;
  }


  /**
   * 
   * @param quantum - get quantum as input and multiply it by 720 and return
   * 
   */

  convertQuantumToUnits(quantum:string){

    var result = parseInt(quantum) * 720;
    return result.toString();
  }

  getProp(propKey: string){
    var result ;
    labels.forEach(element=>{
      if(propKey==element.key){     
        result = element.name;       
      }
    })
    return result;    
  }

  userHasAccess(functionality : string, feature: string){
    var result :boolean = false;
    var user = JSON.parse(sessionStorage.getItem('user'));
    if(user.isSuperUser=="Y"){
      return true;
    }
    user.accessList.forEach(element=>{
      if(element.functionality == functionality && element.feature==feature ){
        result = true;
      }      
    })
    return result;
  }

  getDaysInMonth(month:number,year:number){
    return new Date(year, month, 0).getDate();
  }

  calculateProposedQuantum(quantum:number,days:number){
    return quantum*1000*24*days;
  }
  calculateC1Units(quantum:number,days:number){
    return quantum*1000*3*days;
  }
  calculateC2Units(quantum:number,days:number){
    return quantum*1000*3*days;
  }
  calculateC3Units(quantum:number,days:number){
    return quantum*1000*1*days;
  }
  calculateC4Units(quantum:number,days:number){
    return quantum*1000*10*days;
  }
  calculateC5Units(quantum:number,days:number){
    return quantum*1000*7*days;
  }

  getCurrentMonth(){
    var utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');
    var currentMonth = utc.substr(5,2);
    return currentMonth ;
  }

  getCurrentYear(){
    var utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');  
    var currentYear = utc.substr(0,4);  
    return currentYear;
  }

  fetchYearList(){
    var currentYear: number = +(this.getCurrentYear());  
    var yearList = [];
    yearList.push(currentYear-1);
    yearList.push(currentYear);
    yearList.push(currentYear+1);
    return yearList;
  }
  getPerviousMonth(){
    var myDate = new Date();
    var previousMonth = new Date(myDate);
    previousMonth.setMonth(myDate.getMonth()-1);
    var utc = previousMonth.toJSON().slice(0,10).replace(/-/g,'/');
    var previousMonthValue = utc.substr(5,2);
    return previousMonthValue ;
  }

  getPreviousYear(){
    var myDate = new Date();
    var previousMonth = new Date(myDate);
    previousMonth.setMonth(myDate.getMonth()-1);
    var utc = previousMonth.toJSON().slice(0,10).replace(/-/g,'/');
    var previousYearValue = utc.substr(0,4);  
    return previousYearValue;
  }
  
  getEaYear(){
    var utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');
    var currentMonth = utc.substr(5,2);
    var year;
if(currentMonth=='01'){
    var myDate = new Date();
    var previousMonth = new Date(myDate);
    previousMonth.setMonth(myDate.getMonth()-1);
    var utc = previousMonth.toJSON().slice(0,10).replace(/-/g,'/');
    var previousYearValue = utc.substr(0,4);
    year = previousYearValue;
    console.log(year);
}else{
    var utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');  
    var currentYear = utc.substr(0,4);  
    year = currentYear;
    console.log(year);
}
  return year
  }
}
