import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Ewa } from '../../../transaction/vo/ewa';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class EwaService {

    constructor(private http: Http) {

    }

    getEwaById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'/transaction/ewa/' + _id,new CustomRequestOptions()).map(res => res.json());
    }

    getEwas() {
        return this.http.get(environment.serviceApiUrl+'/transaction/ewas',new CustomRequestOptions()).map(res => res.json());
    }

    getEwaCompanyByEdc(sellerEndOrgId: string, sellerCompanyId: string) {
        
                var url = environment.serviceApiUrl + '/transaction/ewas';
                if (sellerEndOrgId != "" || sellerCompanyId != "") {
                    url = url + "?dummy=1";
        
                    if (sellerEndOrgId != "") {
                        url = url + "&seller-edc-id=" + sellerEndOrgId;
                    }

                    if(sellerCompanyId != ""){
                        url = url + "&seller-company-id="+sellerCompanyId ;
                    }
                
                }
                console.log(url);
                return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
        
            }

	// getAllEwas(edcId:string, buyerId:string, voltage:string , fromDate:string, toDate:string) {
        getAllEwas(sellerEdcId: string, sellerCompanyServiceId: string, sellerCompanyId: string, status: string, esIntentCode: string,ewaCode:string) {
        var url = environment.serviceApiUrl+'/transaction/ewas';
		
		// if(voltage!="" || edcId != "" || buyerId != "") {
        //     url = url + "?dummy=1";  
            
        //     if(voltage != ""){
        //         url = url + "&injection-voltage-code="+voltage ;
        //     }
		// 	if(edcId != ""){
        //         url = url + "&seller-org-id="+edcId ;
        //     }
		// 	if(buyerId != ""){
        //         url = url + "&seller-company-id="+buyerId ;
        //     }
		// 	if(fromDate != ""){
        //         url = url + "&from-date="+fromDate ;
        //     }
		// 	if(toDate != ""){
        //         url = url + "&to-date="+toDate ;
        //     }
        // }	
        
        if (sellerEdcId != "" || sellerCompanyServiceId != "" || sellerCompanyId != "" || status != "" || esIntentCode != ""||ewaCode!="") {
            url = url + "?dummy=1";
            if (sellerEdcId != "") {
                url = url + "&seller-edc-id=" + sellerEdcId;
            }
            if (sellerCompanyServiceId != "") {
                url = url + "&seller-company-service-id=" + sellerCompanyServiceId;
            }
            if (sellerCompanyId !="") {
            url = url + "&seller-company-id=" + sellerCompanyId;
            }
            if (status != "") {
                url = url + "&status=" + status;
            }
            if (esIntentCode != "") {
                url = url + "&es-intent-code=" + esIntentCode;
            }
            if (ewaCode != "") {
                url = url + "&ewa-code=" + ewaCode;
            }

        }
		console.log(url);
		return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }

    removeUnwantedTags(ewa: Ewa) {
        delete ewa.id;
        
        return ewa;
    }

    addEwa(ewa: Ewa) {
        ewa = this.removeUnwantedTags(ewa);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(ewa);
        let url = environment.serviceApiUrl+'/transaction/ewa';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    updateEwa(ewa: Ewa) {
        var key = ewa.id;
        ewa = this.removeUnwantedTags(ewa);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(ewa);
        let url = environment.serviceApiUrl+'/transaction/ewa/' + key;

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }


    approveEwa(ewa: Ewa) {
        var key = ewa.id;
   
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(ewa);
        let url = environment.serviceApiUrl+'/transaction/ewa/' + key+ '/approve';

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    completeEwa(ewa: Ewa) {
        var key = ewa.id;
 
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(ewa);
        let url = environment.serviceApiUrl+'/transaction/ewa/' + key+ '/complete';

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
    

}
