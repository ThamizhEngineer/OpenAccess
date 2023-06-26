import { Injectable,EventEmitter } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { CompanyNameChange } from '../../../transaction/vo/company-name-change';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class CompanyNameChangeService {
    constructor(private http: Http) {
    }

    companyNameChangeEvent: EventEmitter<CompanyNameChange> = new EventEmitter();
    setCompanyNameChange(companyNameChange: CompanyNameChange) {
        this.companyNameChangeEvent.emit(companyNameChange);

    }
    getCompanyNameChangeById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'/transaction/companynamechange/' + _id,new CustomRequestOptions()).map(res => res.json());
    }
    getSellers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }
    getCompanyNameChanges() {
        return this.http.get(environment.serviceApiUrl+'/transaction/companynamechanges',new CustomRequestOptions()).map(res => res.json());
    }

    getCompanyNameChangeByEdc(orgId:string){
        
            var url = environment.serviceApiUrl+'/transaction/companynamechanges';
            if(orgId != ""  ) {
                url = url + "?dummy=1";  
               
                if(orgId != ""){
                    url = url + "&edc-id="+orgId ;
                } 
            }
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    
        }
	

        getAllCompanyNameChanges(orgId:string,companyServiceId:string){
        
        var url = environment.serviceApiUrl+'/transaction/companynamechanges';
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
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }


    // removeUnwantedTags(companyMeterChange: CompanyMeterChange) {
    //     delete consent.id;
       
    //     return consent;
    // }

    addCompanyNameChange(companyNameChange: CompanyNameChange) {
  
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(companyNameChange);
        let url = environment.serviceApiUrl+'/transaction/companynamechange';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
    }

    updateCompanyNameChange(companyNameChange: CompanyNameChange) {
        var key = companyNameChange.id;       
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(companyNameChange);
        let url = environment.serviceApiUrl+'/transaction/companynamechange/' + key;

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
    approveCompanyNameChange(companyNameChange: CompanyNameChange) {
        var key = companyNameChange.id;       
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(companyNameChange);
        let url = environment.serviceApiUrl+'/transaction/companynamechange/' + key+'/approve';

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }


}
