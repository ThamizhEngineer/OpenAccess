import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Epa } from '../../../transaction/vo/epa';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class EpaService {

    constructor(private http: Http) {

    }

    getEpaById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'/transaction/epa/' + _id,new CustomRequestOptions()).map(res => res.json());
    }

    getEpas() {
        return this.http.get(environment.serviceApiUrl+'/transaction/epas',new CustomRequestOptions()).map(res => res.json());
    }

    getEpaCompanyByEdc(buyerOrgId:string,sellerOrgId:string){
        
            var url = environment.serviceApiUrl+'/transaction/epas';
            if(buyerOrgId != "" || sellerOrgId !="" ) {
                url = url + "?dummy=1";  
                if(buyerOrgId != ""){
                    url = url + "&buyer-edc-id="+buyerOrgId ;
                }
                if(sellerOrgId != ""){
                    url = url + "&seller-org-id="+sellerOrgId ;
                } 
            }
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    
        }

	getAllEpas(sellerEdcId:string, sellerCompanyId:string,sellerCompanyServiceId:string, voltage:string , epaCode:string, esIntentCode:string) {
        var url = environment.serviceApiUrl+'/transaction/epas';
		
		if(voltage!="" || sellerEdcId != "" || sellerCompanyId != "" || epaCode != "" || esIntentCode != ""|| sellerCompanyServiceId != "") {
            url = url + "?dummy=1";  
            
            if(voltage != ""){
                url = url + "&injection-voltage-code="+voltage ;
            }
			if(sellerEdcId != ""){
                url = url + "&seller-org-id="+sellerEdcId ;
            }
			if(sellerCompanyId != ""){
                url = url + "&seller-company-id="+sellerCompanyId ;
            }
			if(epaCode != ""){
                url = url + "&code="+epaCode ;
            }
			if(esIntentCode != ""){
                url = url + "&es-intent-code="+esIntentCode ;
            }
            if(sellerCompanyServiceId != ""){
                url = url + "&seller-company-service-id="+sellerCompanyServiceId ;
            }
        }		
        console.log(url)
		
		return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }

    removeUnwantedTags(epa: Epa) {
        delete epa.id;
        
        return epa;
    }

    addEpa(epa: Epa) {
        epa = this.removeUnwantedTags(epa);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(epa);
        let url = environment.serviceApiUrl+'/transaction/epa';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    updateEpa(epa: Epa) {
        var key = epa.id;
        epa = this.removeUnwantedTags(epa);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(epa);
        let url = environment.serviceApiUrl+'/transaction/epa/' + key;

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }


    approveEpa(epa: Epa) {
        var key = epa.id;
   
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(epa);
        let url = environment.serviceApiUrl+'/transaction/epa/' + key+ '/approve';

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    completeEpa(epa: Epa) {
        var key = epa.id;
 
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(epa);
        let url = environment.serviceApiUrl+'/transaction/epa/' + key+ '/complete';

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

}
