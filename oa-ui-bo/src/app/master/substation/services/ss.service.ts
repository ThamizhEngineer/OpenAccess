import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Ss } from './../../vo/ss';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class SsService {
    constructor(private http:Http){
        console.log('Substation Service initialised');
    }

    getSsById(id: string){
        return this.http.get(environment.serviceApiUrl+'/master/substation/'+id,new CustomRequestOptions()).map(res => res.json());      
    }
  
   // getSss( ){
   //     return this.http.get(environment.serviceApiUrl+'/master/substations').map(res => res.json());      
   // }
    
    getSss(sscode,ssname){
     let substationenter : any
    if(sscode&&ssname){
       console.log("in id , name and code both");
      substationenter = '/master/substations'+sscode+'&code='+ssname+'&name' ;

          }
        else if(sscode){
          console.log("in id , code alone");
         substationenter = '/master/substation?code'+sscode;
          }
        else if(ssname){
           console.log("in id , name alone");
          substationenter = '/master/substation?name';
          }
    return this.http.get(environment.serviceApiUrl+'/master/substations',new CustomRequestOptions()).map(res => res.json());    
  
      }

         

     removeUnwantedTags(ss: Ss) {
     delete ss.id;
    
      return ss;
      }
   
  
   

    addSs(ss: Ss){ 
      ss=this.removeUnwantedTags(ss);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(ss);
      let url = environment.serviceApiUrl+'/master/substation';
      console.log('Ss for add-'+JSON.stringify(ss));
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
    updateSs(ss: Ss){
      var key = ss.id; 
      ss=this.removeUnwantedTags(ss);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(ss);
      let url = environment.serviceApiUrl+'/master/substation'+ key;
      console.log('Ss for update-'+JSON.stringify(ss));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
}
