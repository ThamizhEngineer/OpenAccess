import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { NocGenerator} from '../../vo/noc-generator'; 
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class NocGeneratorService {
    constructor(private http:Http){
    }

        getNocGeneratorById(id: string){
        console.log("in getbyid "+id);
        return this.http.get(environment.serviceApiUrl+'/transaction/noc-generator/'+id,new CustomRequestOptions()).map(res => res.json());      
    }

    getBuyers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=02&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }

    getNocGeneratorCompanyByEdc(OrgId:string){
        
            var url = environment.serviceApiUrl+'/transaction/noc-generators';
            if(OrgId != "" || OrgId !="" ) {
                url = url + "?dummy=1";  
                if(OrgId != ""){
                    url = url + "&edc-id="+OrgId ;
                }
                
            }
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    
        }

         getAllNocGenerators(orgId:string,sellerCompanyServiceId:string,esIntentCode:string,code:string){
        
        var url = environment.serviceApiUrl+'/transaction/noc-generators';
        if(orgId != ""||esIntentCode!=""||code!="" ||sellerCompanyServiceId!="" ) {
            url = url + "?dummy=1";  
            if(orgId != "" && orgId !=undefined){
                url = url + "&edc-id="+orgId ;
            }
            if(sellerCompanyServiceId != "" && sellerCompanyServiceId !=undefined){
                url = url + "&seller-company-service-id="+sellerCompanyServiceId ;
            }
            if(esIntentCode != "" && esIntentCode !=undefined){
                url = url + "&es-intent-code="+esIntentCode ;
            } 

          

            if(code != "" && code !=undefined){
                url = url + "&code="+code;
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

        addNocGenerator(nocGenerator: NocGenerator){ 
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(nocGenerator);
      let url = environment.serviceApiUrl+'/transaction/noc-generator/';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
  
    updateNocGenerator(nocGenerator: NocGenerator){
      var key = nocGenerator.id;
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(nocGenerator);
      let url = environment.serviceApiUrl+'/transaction/noc-generator/'+ key;
      console.log('Meter reading Import for update-'+JSON.stringify(nocGenerator));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
            completeNocGenerator(nocGenerator: NocGenerator) {
        var key =  nocGenerator.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(nocGenerator);
        let url = environment.serviceApiUrl+'/transaction/noc-generator/' + key + '/complete';
        console.log('update nocGenerator-' + JSON.stringify(nocGenerator));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }

            approveNocGenerator(nocGenerator: NocGenerator) {
        var key =  nocGenerator.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(nocGenerator);
        let url = environment.serviceApiUrl+'/transaction/noc-generator/' + key + '/approve';
        console.log('update nocGenerator-' + JSON.stringify(nocGenerator));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }
}