import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Feeder } from './../../vo/feeder';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class FeederService {
    constructor(private http:Http){
        console.log('Feeder Service initialised');
    }
    removeUnwantedTags(feeder: Feeder) {
        delete feeder.id;
        
        return feeder;
    }
    getFeederById(id: string){
        return this.http.get(environment.serviceApiUrl+'/master/feeder/'+id,new CustomRequestOptions()).map(res => res.json());      
    }
  
    // getFeeders(){
    //     return this.http.get(environment.serviceApiUrl+'/master/feeders').map(res => res.json());      
    // }
    getFeeders(ssId:string,feederName:string,voltage:string){
        var url = environment.serviceApiUrl+'/master/feeders';
        if(ssId != "" || feederName != "" || voltage != "" ) {
            url = url + "?dummy=1";  
            if(ssId != ""){
                url = url + "&substationId="+ssId ;
            }
            if(feederName != ""){
                url = url + "&name="+feederName;
            }
            if(voltage != ""){
                url = url + "&voltage="+voltage;
            }          

        }
        console.log(url);
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());       
    }
  
   

    addFeeder(feeder: Feeder){ 
      feeder=this.removeUnwantedTags(feeder);
      console.log("in add feeder");
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(feeder);
      let url = environment.serviceApiUrl+'/master/feeder';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
    updateFeeder(feeder: Feeder){
      var key = feeder.id;
      feeder=this.removeUnwantedTags(feeder);
      console.log("In update feeder"+key);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(feeder);
      let url = environment.serviceApiUrl+'/master/feeder/'+ key;
      console.log('Feeder for update-'+JSON.stringify(feeder));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
}
