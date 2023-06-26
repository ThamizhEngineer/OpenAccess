import { Injectable } from '@angular/core';
import { Http, BaseRequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { Response, Headers, RequestOptions } from '@angular/http';

import { environment } from '../../../environments/environment';
import { ContentType } from '@angular/http/src/enums';

@Injectable()
export class CommonService {

  cachedCodesData: any;
  cachedSubstations: any;
  cachedFeeders: any;
  cachedEdcs: any;
  cachedWindEdcs: any;
  cachedMenuItems: any;
  cachedFuels: any;

  constructor(private http: Http) {
    //this.http._defaultOptions.headers.set('Authorization', 'token');


  }

  displayMonth(month: string) {
    var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
    return months[parseInt(month) - 1];
  }

  fetchMonths() {
    return [
      { "value": "01", "name": "January" },
      { "value": "02", "name": "February" },
      { "value": "03", "name": "March" },
      { "value": "04", "name": "April" },
      { "value": "05", "name": "May" },
      { "value": "06", "name": "June" },
      { "value": "07", "name": "July" },
      { "value": "08", "name": "August" },
      { "value": "09", "name": "September" },
      { "value": "10", "name": "October" },
      { "value": "11", "name": "November" },
      { "value": "12", "name": "December" }
    ];
  }


  fetchYearList(){
    var utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');  
    var currentYear : number = +(utc.substr(0,4));  
    var yearList = [];

    
   // let currentYear: number = new Date().getFullYear();
for(let i = 2018; i < 2040; i++) {
  yearList.push(i);
}
    // yearList.push('2019');
    // yearList.push(currentYear-3);
    // yearList.push(currentYear-2);
    // yearList.push(currentYear-1);
    // yearList.push(currentYear);
    // yearList.push(currentYear+1);
    return yearList;
  }

  
  
  // Fetch States 
  fetchStates() {
    return this.http.get(environment.assetsApiUrl + '/assets/data/states.json')
      .map(res => res.json());

  }


  // Fetch Currencies 
  fetchCountries() {
    return this.http.get(environment.assetsApiUrl + '/assets/data/countries.json')
      .map(res => res.json());

  }

  // Fetch Currencies 
  fetchCurrencies() {
    return this.http.get(environment.assetsApiUrl + '/assets/data/currencies.json')
      .map(res => res.json());

  }

  // Fetch EDC 
  fetchEDCs() {

    if (this.cachedEdcs) {
      return Observable.of(this.cachedEdcs);
    } else {
      // return this.http.get(environment.assetsApiUrl+"/assets/data/edcs.json")
      return this.http.get(environment.reportUrl + "report/org-summaries?typeCode=EDC", new CustomRequestOptions())

        .map(res => res.json())
        .do((data) => {
          this.cachedEdcs = data;
        });
    }
    //return this.http.get(environment.assetsApiUrl+'/assets/data/edcs.json').map(res => res.json()); 
    // return this.http.get(environment.serviceApiUrl+'/master/orgs?type-code=EDC').map(res => res.json());      
  }

  fetchEDCByWind() {

    if (this.cachedWindEdcs) {
      return Observable.of(this.cachedWindEdcs);
    } else {
      // return this.http.get(environment.assetsApiUrl+"/assets/data/edcs.json")
      return this.http.get(environment.reportUrl + "report/org-summaries?typeCode=EDC&fuelTypeCode=WIND", new CustomRequestOptions())

        .map(res => res.json())
        .do((data) => {
          this.cachedWindEdcs = data;
        });
    }
    //return this.http.get(environment.assetsApiUrl+'/assets/data/edcs.json').map(res => res.json()); 
    // return this.http.get(environment.serviceApiUrl+'/master/orgs?type-code=EDC').map(res => res.json());      
  }

  FetchFuels()  {
    if (this.cachedFuels) {
      return Observable.of(this.cachedFuels);
     } else {
        return this.http.get(environment.reportUrl + "report/fuel-summaries", new CustomRequestOptions())
        .map(res => res.json())
        .do((data) => {
          this.cachedFuels = data;
        });
    }
  }

  fetchSubstations() {
    if (this.cachedSubstations) {
      return Observable.of(this.cachedSubstations);
    } else {
      // return this.http.get(environment.assetsApiUrl+"/assets/data/substations.json")
      return this.http.get(environment.reportUrl + "report/substation-summaries", new CustomRequestOptions())

        .map(res => res.json())
        .do((data) => {
          this.cachedSubstations = data;
        });
    }
    // return this.http.get(environment.serviceApiUrl+'/master/substations').map(res => res.json());      
  }

  fetchSubstationsByOrgId(orgId: string) {

    if (this.cachedSubstations) {
      return Observable.of(this.cachedSubstations.json().filter(function (value) { return value.orgId == orgId; }));
    } else {
      return this.http.get(environment.reportUrl + "report/substation-summaries?orgId=" + orgId, new CustomRequestOptions())
        .do((data) => { this.cachedSubstations = data; })
        .map(res => res.json().filter(function (value) { return value.orgId == orgId; }));
    }
    //return this.http.get(environment.assetsApiUrl+'/assets/data/substations.json').map(res => res.json().filter(function(value){ return value.orgId==orgId;}));      
  }

  //  fetchCompanyname(){
  //   return this.http.get(environment.serviceApiUrl+'/master/companies').map(res => res.json());      
  //     }

  fetchFeeders(substationId: string) {
    if (this.cachedFeeders) {
      return Observable.of(this.cachedFeeders.json().filter(function (value) { return value.substationId == substationId; }));
    } else {
      // return this.http.get(environment.assetsApiUrl+"/assets/data/feeders.json")
      return this.http.get(environment.reportUrl + "report/feeder-summaries", new CustomRequestOptions())

        .do((data) => { this.cachedFeeders = data; })
        .map(res => res.json().filter(function (value) { return value.substationId == substationId; }));
    }

    //return this.http.get(environment.assetsApiUrl+'/assets/data/feeders.json').map(res => res.json().filter(function(value){ return value.substationId==substationId;})); 
  }

  // Fetch Menu Items 
  fetchMenuItems() {


    if (this.cachedMenuItems) {
      return Observable.of(this.cachedMenuItems);
    } else {
      return this.http.get(environment.assetsApiUrl + "/assets/data/menu-items.json")
        .map(res => res.json())
        .do((data) => {
          this.cachedMenuItems = data;
        });
    }
    // return this.http.get(environment.assetsApiUrl+'/assets/data/menu-items.json')
    //         .map(res => res.json());    

  }

  //Company and service from report service (masterservice) for buyer
  getBuyerCompanyServicesByOrgId(orgId: string) {
    var url = environment.reportUrl + 'report/masterdata/data/COMPSERVICE?serviceTypeCode=02';

    if (orgId != "") {
      if (orgId != "" && orgId !== undefined) {
        url = url + "&orgId=" + orgId;
      }
      console.log(orgId)
    }
    console.log("buyer url")

    console.log(url)
    return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());
  }

  //Company and service from report service (masterservice) for seller
  getSellerCompanyServicesByOrgId(orgId: string) {
    var url = environment.reportUrl + 'report/masterdata/data/COMPSERVICE?serviceTypeCode=03';

    if (orgId != "") {
      if (orgId != "" && orgId !== undefined) {
        url = url + "&orgId=" + orgId;
      }
      console.log(orgId)
    }
    console.log("seller url")
    console.log(url)
    return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());
  }

  //Company and Meter from report service (masterservice). Used in meter-reading-import-process
  getMeterAndService(orgId: string) {
    var url = environment.reportUrl + 'report/masterdata/data/METER?serviceTypeCode=03';
    if (orgId != "") {
      if (orgId != "" && orgId != null) {
        url = url + "&orgId=" + orgId;
      }
      console.log(orgId)

    }
    console.log('url-' + url);
    return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());

  }

  //Fetch common codes

  fetchCodes(listCode?: string) {
    if (listCode) {
      if (this.cachedCodesData) {
        return Observable.of(this.cachedCodesData.json().filter(function (value) { return value.listCode == listCode; }));
      } else {
        return this.http.get(environment.assetsApiUrl + '/assets/data/codes.json').map(res => res.json().filter(function (value) { return value.listCode == listCode; }));
      }
    }
    else {
      if (this.cachedCodesData) {
        return Observable.of(this.cachedCodesData);
      } else {
        return this.http.get(environment.assetsApiUrl + "/assets/data/codes.json")
          .map(res => res.json())
          .do((data) => {
            this.cachedCodesData = data;
          });
      }
    }
  }

  private regex: RegExp = new RegExp(/^\d*\.?\d{0,2}$/g);
  private specialKeys: Array<string> = ['Backspace', 'Tab', 'End', 'Home', 'ArrowLeft', 'ArrowRight', 'Del', 'Delete'];
  numberOnly(event): boolean {
    let value = event.target.value;
    if (this.specialKeys.indexOf(event.key) !== -1) {
      return;
    }
    if ((event.ctrlKey || event.metaKey) && (event.key === 'c' || event.key === 'v' || event.key === 'a'|| event.key === 'x')) {
      return;
    }
     let current: string = value;
      const position = event.target.selectionStart;
      const next: string = [current.slice(0, position), event.key == 'Decimal' ? '.' : event.key, current.slice(position)].join('');
      if (next && !String(next).match(this.regex)) {
       event.preventDefault();
      }
   
  }

}


/**
 * Extending BaseRequestOptions to inject common headers to all requests.
 */
export class CustomRequestOptions extends BaseRequestOptions {
  constructor() {
    super();
    var token = sessionStorage.getItem('token');
    this.headers.append('Authorization', token);
    this.headers.append('Content-Type', 'application/json');
  }
  setParams(params?: any) {
    this.params = params;
  }
  setBody(body?: any) {
    this.body = body;
  }
  setHeaders(obj?: any) {
    obj.forEach(x => {
      Object.keys(x).forEach(key => {
        this.headers.append(key, x[key]);
      });
    });
  }






}
