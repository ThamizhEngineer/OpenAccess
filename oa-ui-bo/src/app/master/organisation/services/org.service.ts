import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Org } from './../../vo/org';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class OrgService {
    constructor(private http:Http){
        console.log('Org Service initialised');
    }

    getOrgById(id: string){
        return this.http.get(environment.serviceApiUrl+'/master/org/'+id,new CustomRequestOptions()).map(res => res.json());      
    }
  
    getOrgs(){
        return this.http.get(environment.serviceApiUrl+'/master/orgs',new CustomRequestOptions()).map(res => res.json());      
    }
  
    getEDCs(){
        return this.http.get(environment.serviceApiUrl+'/master/orgs?type_code=EDC',new CustomRequestOptions()).map(res => res.json());      
    }
   

    addOrg(org: Org){ 
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(org);
      let url = environment.serviceApiUrl+'/master/org';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
    updateOrg(org: Org){
      var key = org.id;
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(org);
      let url = environment.serviceApiUrl+'/master/org/'+ key;
      console.log('Energy Allocation for update-'+JSON.stringify(org));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
}
