import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Signup } from './../../vo/signup';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';
// import { Options } from 'selenium-webdriver';


@Injectable()
export class SignupService {
    cachedBuyerServices: any;

    constructor(private http: Http) {
       
    }

    getSignupById(signupId: string) {
        return this.http.get(environment.serviceApiUrl + '/master/signup/' + signupId,new CustomRequestOptions()).map(res => res.json());
    }

    getSignups() {
        return this.http.get(environment.serviceApiUrl + '/master/signups',new CustomRequestOptions()).map(res => res.json());
    }


    searchSignups(purposeCode: string, companyName: string, orgId: string, searchHtscNumber: string,searchHtscNumberOld: string, isCaptive: string, isComplete: string, meterNumber: string) {
        var url = environment.serviceApiUrl + '/master/signups';
    
        if (purposeCode != "" || companyName != "" || orgId != "" || searchHtscNumber != "" || searchHtscNumberOld != "" || isCaptive != "" || isComplete != "" || meterNumber != "") {
            url = url + "?dummy=1";
           
            if (purposeCode != "" && purposeCode !== undefined) {
                url = url + "&purpose-code=" + purposeCode;
            }
            if (companyName != "" && companyName !== undefined) {
                url = url + "&company-name=" + companyName;
            }
            if (orgId != "" && orgId !== undefined) {
                url = url + "&org-id=" + orgId;
            }
            if (searchHtscNumber != "" && searchHtscNumber !== undefined) {
                url = url + "&htsc-number=" + searchHtscNumber;
            }
            if (searchHtscNumberOld != "" && searchHtscNumberOld !== undefined) {
                url = url + "&htsc-number-old=" + searchHtscNumberOld;
            }
            if (isCaptive != "" && isCaptive !== undefined) {
                url = url + "&is-captive=" + isCaptive;
            }
            if (isComplete != "" && isComplete !== undefined) {
                url = url + "&is-complete=" + isComplete;
            }
            if (meterNumber != "" && meterNumber !== undefined) {
                url = url + "&meter-number=" + meterNumber;
            }
        }
        var Options=new CustomRequestOptions();
        console.log(Options)
        return this.http.get(url,Options).map((res: Response) => res.json());
    }


    getEDCs() {
        return this.http.get(environment.serviceApiUrl + '/master/signup/type=EDC',new CustomRequestOptions()).map(res => res.json());
    }

    //returns all Buyer services
    getBuyerCompanyServices() {
        if (this.cachedBuyerServices) {
            return Observable.of(this.cachedBuyerServices);
        } else {
            return this.http.get(environment.assetsApiUrl+"/assets/data/buyerservices.json",new CustomRequestOptions())
                .map(res => res.json())
                .do((data) => {
                    this.cachedBuyerServices = data;
                });
        }

    }


    //returns all Buyer services based on Org Id
    getBuyerCompanyServicesByOrgId(orgId: string) {
        // return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=02&edc-id=' + orgId).map(res => res.json());

         //var url =environment.serviceApiUrl+'/master/companyservices?type=02';
         var url = environment.assetsApiUrl+'/assets/data/buyerservices.json';
        //  var url = environment.reportUrl+'report/masterdata/data/COMPSERVICE?serviceTypeCode=02';

        // if(orgId !=""){
        //     url = url + "&dummy=1";
            
        //     if(orgId!="" && orgId!=null){
        //         url = url + "&edc-id="+orgId;
        //     }
        // }
        if (this.cachedBuyerServices) {
            return Observable.of(this.cachedBuyerServices.json().filter((buyer)=> buyer.orgId == orgId));
        } else {
            return this.http.get(environment.assetsApiUrl+"/assets/data/buyerservices.json",new CustomRequestOptions())
            // return this.http.get(environment.reportUrl+"report/masterdata/data/COMPSERVICE?serviceTypeCode=02",new CustomRequestOptions())
                .do((data) => {
                    this.cachedBuyerServices = data;
                })
                .map(res => res.json().filter((buyer)=> buyer.orgId == orgId));
        }
        
        // return this.http.get(url).map((res: Response) => res.json().filter((buyer)=> buyer.orgId == orgId));    
    }

    getSellerCompanyServices() {
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03',new CustomRequestOptions()).map(res => res.json());

    }
    
    getSellerCompanyServicesByOrgId(orgId: string) {
    
        var url =environment.serviceApiUrl+'/master/companyservices?type=03';
        if(orgId !=""){
            url = url + "&dummy=1";
            
            if(orgId!="" && orgId!=null){
                url = url + "&edc-id="+orgId;
            }
        }

        
         return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());    
    }


    addSignup(signup: Signup) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(signup);
        let url = environment.serviceApiUrl + '/master/signup';
   
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    updateSignup(signup: Signup) {
        var key = signup.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(signup);
        let url = environment.serviceApiUrl + '/master/signup/' + key;
     
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
    completeSignup(signup: Signup) {
        var key = signup.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(signup);
        let url = environment.serviceApiUrl + '/master/signup/' + key + '/complete';
 
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

}
