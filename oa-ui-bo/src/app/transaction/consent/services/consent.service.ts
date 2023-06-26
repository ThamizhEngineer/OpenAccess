import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Consent } from '../../../transaction/vo/consent';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class ConsentService {

    constructor(private http: Http) {

    }

    getConsentById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'/transaction/consent/' + _id,new CustomRequestOptions()).map(res => res.json());
    }

    getConsents() {
        return this.http.get(environment.serviceApiUrl+'/transaction/consents',new CustomRequestOptions()).map(res => res.json());
    }

    getConsentCompanyByEdc(buyerEndOrgId:string,sellerEndOrgId:string,sellerCompanyId:string){
        
            var url = environment.serviceApiUrl+'/transaction/consents';
            if( sellerEndOrgId !=""  ||  buyerEndOrgId != "" || sellerCompanyId != "" ) {
                url = url + "?dummy=1";  
                if(sellerEndOrgId != ""){
                    url = url + "&seller-edc-id="+sellerEndOrgId ;
                } 
                 if(buyerEndOrgId != ""){
                    url = url + "&buyer-org-id="+buyerEndOrgId ;
                }
                if(sellerCompanyId != ""){
                    url = url + "&seller-company-id="+sellerCompanyId ;
                }
            }
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    
        }
	

    getAllConsents(buyerEndOrgId:string,buyerCompanyServiceNumber:string,buyerCompanyName:string,sellerCompanyServiceNumber:string,sellerCompanyName:string,sellerCompanyId :string ,buyerVoltageCode:string,status:string,esIntentCode:string,buyerCompanyServiceId:string,consentCode:string){
        
        var url = environment.serviceApiUrl+'/transaction/consents';
        if(buyerEndOrgId != "" || buyerCompanyServiceNumber != "" || buyerCompanyName!="" || sellerCompanyServiceNumber!="" ||sellerCompanyName!=""|| sellerCompanyId  !="" || buyerVoltageCode!=""||status!=""||esIntentCode!=""||buyerCompanyServiceId !="" || consentCode!="" ) {
            url = url + "?dummy=1";  
            if(buyerEndOrgId != ""){
                url = url + "&buyer-org-id="+buyerEndOrgId ;
            }
            if(buyerCompanyServiceNumber != ""){
                url = url + "&buyer-company-service-number="+buyerCompanyServiceNumber ;
            } 
            if(buyerCompanyName != ""){
                url = url + "&buyer-company-name="+buyerCompanyName ;
            } 
            if(sellerCompanyServiceNumber != ""){
                url = url + "&seller-company-service-number="+sellerCompanyServiceNumber ;
            } 
            if(sellerCompanyName != ""){
                url = url + "&seller-company-name="+sellerCompanyName ;
            }  
            if(sellerCompanyId != ""){
                url = url + "&seller-company-id="+sellerCompanyId ;
            } 
            if(buyerVoltageCode != ""){
                url = url + "&drawal-voltage-code="+buyerVoltageCode ;
            }    
            if(status != ""){
                url = url + "&status="+status ;
            }      
            if(esIntentCode != ""){
                url = url + "&es-intent-code="+esIntentCode ;
            } 

            if(buyerCompanyServiceId != ""){
                url = url + "&buyer-company-service-id="+buyerCompanyServiceId ;
            } 
            if(consentCode != ""){
                url = url + "&consent-code="+consentCode;
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
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }


    removeUnwantedTags(consent: Consent) {
        delete consent.id;
       
        return consent;
    }

    addConsent(consent: Consent) {
        consent = this.removeUnwantedTags(consent);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(consent);
        let url = environment.serviceApiUrl+'/transaction/consent';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    updateConsent(consent: Consent) {
        var key = consent.id;
        consent = this.removeUnwantedTags(consent);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(consent);
        let url = environment.serviceApiUrl+'/transaction/consent/' + key;

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
        approveConsent(consent: Consent) {
        var key =  consent.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(consent);
        let url = environment.serviceApiUrl+'/transaction/consent/' + key + '/approve';
        console.log('update Consent-' + JSON.stringify(consent));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }

    completeConsent(consent: Consent) {
        var key =  consent.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(consent);
        let url = environment.serviceApiUrl+'/transaction/consent/' + key + '/complete';
        console.log('update Consent-' + JSON.stringify(consent));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }
    

}
