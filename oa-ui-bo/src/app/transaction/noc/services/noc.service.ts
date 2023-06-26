import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { Noc} from '../../vo/noc'; 
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class NocService {
    constructor(private http:Http){
    }

        getNocById(id: string){
        console.log("in getbyid "+id);
        return this.http.get(environment.serviceApiUrl+'/transaction/noc/'+id,new CustomRequestOptions()).map(res => res.json());      
    }

    getNocCompanyByEdc(buyerEndOrgId:string,sellerEndOrgId:string,sellerCompanyId:string){
        
            var url = environment.serviceApiUrl+'/transaction/nocs';
            if(buyerEndOrgId != "" || sellerEndOrgId !="" || sellerCompanyId !="" ) {
                url = url + "?dummy=1";  
                if(buyerEndOrgId != ""){
                    url = url + "&buyer-edc-id="+buyerEndOrgId ;
                }
                if(sellerEndOrgId != ""){
                    url = url + "&seller-edc-id="+sellerEndOrgId ;
                } 
                if(sellerCompanyId != ""){
                    url = url + "&seller-company-id="+sellerCompanyId ;
                }
            }
            console.log(url)
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    
        }

         getAllNocs(buyerEndOrgId:string,buyerCompanyServiceNumber:string,buyerCompanyName:string,sellerCompanyServiceNumber:string,sellerCompanyId:string,sellerCompanyName:string,buyerVoltageCode:string,status:string,esIntentCode:string,buyerCompanyServiceId:string,nocCode:string){
        
        var url = environment.serviceApiUrl+'/transaction/nocs';
        if(buyerEndOrgId != "" || buyerCompanyServiceNumber != "" || buyerCompanyName!="" || sellerCompanyServiceNumber!="" || sellerCompanyId!="" || sellerCompanyName!="" || buyerVoltageCode!=""|| status!=""||esIntentCode!=""||buyerCompanyServiceId !=""||nocCode!="" ) {
            url = url + "?dummy=1";  
            if(buyerEndOrgId != ""){
                url = url + "&buyer-edc-id="+buyerEndOrgId ;
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
            if(sellerCompanyId != ""){
                url = url + "&seller-company-id="+sellerCompanyId;
            } 
            if(sellerCompanyName != ""){
                url = url + "&seller-company-name="+sellerCompanyName ;
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

            if(nocCode != ""){
                url = url + "&noc-code="+nocCode;
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

        addNoc(noc: Noc){ 
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(noc);
      let url = environment.serviceApiUrl+'/transaction/noc/';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
  
    updateNoc(noc: Noc){
      var key = noc.id;
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(noc);
      let url = environment.serviceApiUrl+'/transaction/noc/'+ key;
      console.log('Meter reading Import for update-'+JSON.stringify(noc));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
            completeNoc(noc: Noc) {
        var key =  noc.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(noc);
        let url = environment.serviceApiUrl+'/transaction/noc/' + key + '/complete';
        console.log('update Noc-' + JSON.stringify(noc));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }

            approveNoc(noc: Noc) {
        var key =  noc.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(noc);
        let url = environment.serviceApiUrl+'/transaction/noc/' + key + '/approve';
        console.log('update Noc-' + JSON.stringify(noc));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }
}