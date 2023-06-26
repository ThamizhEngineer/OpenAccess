import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Consent } from '../../../transaction/vo/consent';
import { Ewa } from '../../../transaction/vo/ewa';
import { Epa } from '../../../transaction/vo/epa';
import { Oaa } from '../../../transaction/vo/oaa';
import { Agreement } from '../../../transaction/vo/agreement';
import { Noc} from '../../vo/noc'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class AgreementService {

    constructor(private http: Http) {

    }


    getAllConsents(status: string) {
        var url = environment.serviceApiUrl + '/transaction/consents';
        if (status != "") {
            url = url + "?dummy=1";
            if (status != "") {
                url = url + "&status=" + status;
            }
        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }  

    getAllNocs(status: string) {
        console.log("In service get all noc");
        
        var url = environment.serviceApiUrl + '/transaction/nocs';
        if (status != "") {
            url = url + "?dummy=1";
            if (status != "") {
                url = url + "&status=" + status;
            }
        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    } 

    getAllEwas(status: string) {
        console.log("In service get all ewa");
        
        var url = environment.serviceApiUrl + '/transaction/ewas';
        if (status != "") {
            url = url + "?dummy=1";
            if (status != "") {
                url = url + "&status=" + status;
            }
        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    } 

    getAllEpas(status: string) {
        console.log("In service get all epa");
        
        var url = environment.serviceApiUrl + '/transaction/epas';
        if (status != "") {
            url = url + "?dummy=1";
            if (status != "") {
                url = url + "&status=" + status;
            }
        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }
    getAllOaas(status: string) {
        console.log("In service get all oaa");
        
        var url = environment.serviceApiUrl + '/transaction/oaas';
        if (status != "") {
            url = url + "?dummy=1";
            if (status != "") {
                url = url + "&status=" + status;
            }
        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    } 

    completeConsent(id: string, consent: Consent) {
        var key = id;       
        console.log(consent)
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(consent);
        let url = environment.serviceApiUrl + '/transaction/consent/' + key + '/complete';
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());

    }


    completeNoc(id: string,noc: Noc) {
        var key = id;       
        console.log(noc)
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(noc);
        let url = environment.serviceApiUrl + '/transaction/noc/' + key + '/complete';
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());

    }



    completeEwa(id: string,ewa: Ewa) {
        var key = id;       
        console.log(ewa)
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(ewa);
        let url = environment.serviceApiUrl + '/transaction/ewa/' + key + '/complete';
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());

    }

    completeEpa(id: string,epa: Epa) {
        var key = id;       
        console.log(epa)
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(epa);
        let url = environment.serviceApiUrl + '/transaction/epa/' + key + '/complete';
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());

    }



    completeOaa(id: string,oaa: Oaa) {
        var key = id;       
        console.log(oaa)
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(oaa);
        let url = environment.serviceApiUrl + '/transaction/oaa/' + key + '/complete';
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());

    }
    getSellers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }

    getBuyers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=02&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }

    getAllAgreements(sellerEdcId:string,sellerCompanyServiceId:string, agreementCode:string, esIntentCode:string,status:string) {
        var url = environment.serviceApiUrl+'/transaction/agreements';
		
		if( sellerEdcId != "" || agreementCode != "" || esIntentCode != ""|| sellerCompanyServiceId != ""||status!="") {
            url = url + "?dummy=1";  
            
           
			if(sellerEdcId != "" && sellerEdcId!=undefined){
                url = url + "&seller-edc-id="+sellerEdcId ;
            }

			if(agreementCode != ""  && agreementCode!=undefined){
                url = url + "&code="+agreementCode ;
            }
			if(esIntentCode != ""  && esIntentCode!=undefined){
                url = url + "&es-intent-code="+esIntentCode ;
            }
            if(sellerCompanyServiceId != "" && sellerCompanyServiceId!=undefined){
                url = url + "&seller-company-service-id="+sellerCompanyServiceId ;
            }
            if(status != "" && status!=undefined){
                url = url + "&status="+status ;
            }
        }		
        console.log(url)
		
		return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }

    addAgreement(agreement: Agreement) {
    
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(agreement);
        let url = environment.serviceApiUrl+'/transaction/agreement';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    updateAgreement(agreement: Agreement) {
        var key = agreement.id;
       
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(agreement);
        let url = environment.serviceApiUrl+'/transaction/agreement/' + key;

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    getAgreementById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'/transaction/agreement/' + _id,new CustomRequestOptions()).map(res => res.json());
    }
   
    agreementSigned(agreement: Agreement) {
        var key = agreement.id;
       
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(agreement);
        let url = environment.serviceApiUrl+'/transaction/agreement/' + key+'/signed';

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
    agreementCancelled(agreement: Agreement) {
        var key = agreement.id;
       
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(agreement);
        let url = environment.serviceApiUrl+'/transaction/agreement/' + key+'/cancelled';

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    getAgreementCompanyByEdc(buyerOrgId:string,sellerOrgId:string){
        
            var url = environment.serviceApiUrl+'/transaction/agreements';
            if(buyerOrgId != "" || sellerOrgId !="" ) {
                url = url + "?dummy=1";  
                if(buyerOrgId != ""){
                    url = url + "&buyer-edc-id="+buyerOrgId ;
                }
                if(sellerOrgId != ""){
                    url = url + "&seller-edc-id="+sellerOrgId ;
                } 
            }
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    
        }


}
