import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Unitwise } from './unitwise';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import {CustomRequestOptions} from '../../shared/common/common.service';

@Injectable()
export class UnitwiseService {
    baseUrl: string = environment.serviceApiUrl+'/unitwise';
  
    constructor(private http:Http){
        
    }

    getUnitwiseById(_id: string){
       // return this.http.get(this.baseUrl+'/unitwise/'+_id).map(res => res.json());      
    }
  
    getUnitwises(){
       // return this.http.get(this.baseUrl+'/unitwises').map(res => res.json());      
    }
  

    removeUnwantedTags(unitwise: Unitwise){
        delete unitwise._id;
       
        return unitwise;    
    }

    addUnitwise(unitwise: Unitwise){
      unitwise = this.removeUnwantedTags(unitwise);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(unitwise);
	  console.log(unitwise, options)
      let url = this.baseUrl+'/unitwise';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
    updateUnitwise(unitwise: Unitwise){
      var key = unitwise._id;
      unitwise = this.removeUnwantedTags(unitwise);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(unitwise);
      console.log(body)
      let url = this.baseUrl+'/unitwise/'+ key;
      
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
}
