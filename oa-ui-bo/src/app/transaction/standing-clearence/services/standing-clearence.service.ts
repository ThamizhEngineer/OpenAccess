import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { StandingClearence} from '../../vo/standing-clearence'; 
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class StandingClearenceService {
    constructor(private http:Http){
    }

        getStandingClearenceById(id: string){
        console.log("in getbyid "+id);
        return this.http.get(environment.serviceApiUrl+'/transaction/standingclearence/'+id,new CustomRequestOptions()).map(res => res.json());      
    }

    getBuyers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=02&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }

    getNocCompanyByEdc(buyerEndOrgId:string){
        
            var url = environment.serviceApiUrl+'/transaction/nocs';
            if(buyerEndOrgId != "") {
                url = url + "?dummy=1";  
                if(buyerEndOrgId != ""){
                    url = url + "&buyer-edc-id="+buyerEndOrgId ;
                }
                 
            }
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    
        }

         getAllStandingClearences(orgId:string,buyerCompanyServiceId:string,esIntentCode:string,code:string){
        
        var url = environment.serviceApiUrl+'/transaction/standingclearences';
        if(orgId != ""||buyerCompanyServiceId!=""||esIntentCode!=""||code!="" ) {
            url = url + "?dummy=1";  
            if(orgId != ""){
                url = url + "&edc-id="+orgId ;
            }

            if(buyerCompanyServiceId != ""){
                url = url + "&buyer-company-service-id="+buyerCompanyServiceId  ;
            }
            
            if(esIntentCode != ""){
                url = url + "&es-intent-code="+esIntentCode ;
            } 

            if(code != ""){
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

     addStandingClearence(standingClearence: StandingClearence){ 
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(standingClearence);
      let url = environment.serviceApiUrl+'/transaction/standingclearence/';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
  
    updateStandingClearence(standingClearence:  StandingClearence){
      var key =  standingClearence.id;
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(standingClearence);
      let url = environment.serviceApiUrl+'/transaction/standingclearence/'+ key;
      console.log('standing clearence for update-'+JSON.stringify(standingClearence));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
            
        completeStandingClearence(standingClearence:  StandingClearence) {
        var key =  standingClearence.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(standingClearence);
        let url = environment.serviceApiUrl+'/transaction/standingclearence/' + key + '/complete';
        console.log('update standingClearence-' + JSON.stringify(standingClearence));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }

        approveStandingClearence(standingClearence:  StandingClearence) {
        var key =  standingClearence.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(standingClearence);
        let url = environment.serviceApiUrl+'/transaction/standingclearence/' + key + '/approveipasc';
        console.log('update standingClearence' + JSON.stringify(standingClearence));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }
}