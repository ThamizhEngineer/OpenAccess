import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 

import { Oaa } from '../../../transaction/vo/oaa';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class OaaService {
    baseUrl: string = environment.serviceApiUrl+'/oaa';
  
    constructor(private http:Http){
    }

    getOaaById(_id: string){
        return this.http.get(environment.serviceApiUrl+'/transaction/oaa/' + _id,new CustomRequestOptions()).map(res => res.json());      
    }
  
    getOaas(){
        return this.http.get(environment.serviceApiUrl+'/transaction/oaas',new CustomRequestOptions()).map(res => res.json());      
    }

    getSellers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }
	
	getAllOaas(edcId:string, buyerId:string,fromDate:string, toDate:string,oaaCode:string,esIntentCode:string,	buyerCompServiceId: string,status:string,sellerId:string,sellerCompServiceId:string,sellerCompanyName:string ) {
        var url = environment.serviceApiUrl+'/transaction/oaas';
		if( edcId != "" || buyerId != "" || fromDate!="" || toDate != ""  || oaaCode !=""|| esIntentCode !="" || buyerCompServiceId !=""|| status !="" || sellerId !="" || sellerCompServiceId !=""  ||sellerCompanyName!="" ) {
            url = url + "?dummy=1";  
            
			if(edcId != "" && edcId != undefined){
                url = url + "&buyer-org-id="+edcId ;
            }
			if(buyerId != "" && buyerId != undefined){
                url = url + "&buyer-company-id="+buyerId ;
            }
            if(fromDate != "" && fromDate != undefined){
                url = url + "&from-date="+fromDate ;
            }
			if(toDate != "" && toDate != undefined){
                url = url + "&to-date="+toDate ;
            }
            
            if(oaaCode != "" && oaaCode != undefined){
                url = url + "&code="+oaaCode ;
            }
			if(esIntentCode != "" && esIntentCode != undefined ){
                url = url + "&es-intent-code="+esIntentCode ;
            }
            if(buyerCompServiceId != "" && buyerCompServiceId != undefined){
                url = url + "&buyer-company-service-id="+buyerCompServiceId ;
            }
            if(sellerCompanyName != "" && sellerCompanyName != undefined){
                url = url + "&seller-company-name="+sellerCompanyName ;
            }
            if(status != "" && status != undefined){
                url = url + "&status="+status ;
            }
            if(sellerId != "" && sellerId != undefined){
                url = url + "&seller-company-id="+sellerId ;
            }
            if(sellerCompServiceId != "" && sellerCompServiceId != undefined){
                url = url + "&seller-company-service-id="+sellerCompServiceId ;
            }


        }		
		console.log(url)
		
		return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }

    removeUnwantedTags(oaa: Oaa){
        delete oaa.id;
        return oaa;    
    }

    addOaa(oaa: Oaa){
      oaa = this.removeUnwantedTags(oaa);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(oaa);
      let url = environment.serviceApiUrl+'/transaction/oaa';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
    }
  
    updateOaa(oaa: Oaa){
      var key = oaa.id;
  
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(oaa);
      let url = environment.serviceApiUrl+'/transaction/oaa/' + key;
	  
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
            completeOaa(oaa: Oaa) {
        var key =  oaa.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(oaa);
        let url = environment.serviceApiUrl+'/transaction/oaa/' + key + '/complete';
        console.log('update oaa-' + JSON.stringify(oaa));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }

            approveOaa(oaa: Oaa) {
        var key =  oaa.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(oaa);
        let url = environment.serviceApiUrl+'/transaction/oaa/' + key + '/approve';
        console.log('update Oaa-' + JSON.stringify(oaa));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }
  
}
