import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { CompanyMeterChange } from '../../../transaction/vo/company-meter-change';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { CommonUtils } from '../../../shared/common/common-utils';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class CompanyMeterChangeService {

    constructor(private http: Http,private commonUtils: CommonUtils) {

    }

    getCompanyMeterChangeById(_id: string) {
        var url = environment.serviceApiUrl+'/transaction/companymeterchange/' + _id;
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
       console.log(options);
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());
    }
    getSellers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }
    getCompanyMeterChanges() {
        return this.http.get(environment.serviceApiUrl+'/transaction/companymeterchanges',new CustomRequestOptions()).map(res => res.json());
    }

    getCompanyMeterChangeByEdc(orgId:string){
        
            var url = environment.serviceApiUrl+'/transaction/companymeterchanges';
            if(orgId != ""  ) {
                url = url + "?dummy=1";  
               
                if(orgId != ""){
                    url = url + "&edc-id="+orgId ;
                } 
            }
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    
        }
	

        getAllCompanyMeterChanges(orgId:string,companyServiceId:string){
        
        var url = environment.serviceApiUrl+'/transaction/companymeterchanges';
        if(orgId != "" ||companyServiceId !="" ) {
            url = url + "?dummy=1";  
            if(orgId != ""){
                url = url + "&edc-id="+orgId ;
            }
           
            if(companyServiceId != ""){
                url = url + "&company-service-id="+companyServiceId ;
            } 
          
 
 
          }
        // let userToken =this.commonUtils.getUserTokens();
        // //console.log(userToken);
        //  console.log(userToken.userToken);
        // let headers = new Headers({ 'Content-Type': 'application/json'});
        // headers.append('token',userToken.userToken);
        // let options = new RequestOptions({ headers: headers });
     
        // console.log(options);
        console.log('url-'+url);
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
        console.log(options);
        return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());

    }


    // removeUnwantedTags(companyMeterChange: CompanyMeterChange) {
    //     delete consent.id;
       
    //     return consent;
    // }

    addCompanyMeterChange(companyMeterChange: CompanyMeterChange) {
  
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(companyMeterChange);
        let url = environment.serviceApiUrl+'/transaction/companymeterchange';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    updateCompanyMeterChange(companyMeterChange: CompanyMeterChange) {
        var key = companyMeterChange.id;       
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(companyMeterChange);
        let url = environment.serviceApiUrl+'/transaction/companymeterchange/' + key;

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
    approveCompanyMeterChange(companyMeterChange: CompanyMeterChange) {
        var key = companyMeterChange.id;       
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(companyMeterChange);
        let url = environment.serviceApiUrl+'/transaction/companymeterchange/' + key+'/approve';

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
    //     approveConsent(consent: Consent) {
    //     var key =  consent.id;
    //     let headers = new Headers({ 'Content-Type': 'application/json' });
    //     let options = new RequestOptions({ headers: headers });
    //     let body = JSON.stringify(consent);
    //     let url = environment.serviceApiUrl+'/transaction/consent/' + key + '/approve';
    //     console.log('update Consent-' + JSON.stringify(consent));
    //     console.log(url);
    //     return this.http.put(url, body, options).map((res: Response) => res);

    // }

    // completeConsent(consent: Consent) {
    //     var key =  consent.id;
    //     let headers = new Headers({ 'Content-Type': 'application/json' });
    //     let options = new RequestOptions({ headers: headers });
    //     let body = JSON.stringify(consent);
    //     let url = environment.serviceApiUrl+'/transaction/consent/' + key + '/complete';
    //     console.log('update Consent-' + JSON.stringify(consent));
    //     console.log(url);
    //     return this.http.put(url, body, options).map((res: Response) => res);

    // }
    

}
