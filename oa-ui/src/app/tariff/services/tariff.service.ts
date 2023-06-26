import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Tariff } from './tariff';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import {CustomRequestOptions} from '../../shared/common/common.service';

@Injectable()
export class TariffService {
    baseUrl: string = environment.serviceApiUrl+'/tariff';
  
    constructor(private http:Http){
        
    }
	getTariffById(_id: string){
       // return this.http.get(this.baseUrl+'/tariff/'+_id).map(res => res.json());      
    }
  
    getTariffs(){
        return this.http.get(this.baseUrl+'/tariffs',new CustomRequestOptions()).map(res => res.json());      
    }

    removeUnwantedTags(tariff: Tariff){
        delete tariff._id;
		delete tariff['$$index'];   
        return tariff;    
    }

    addTariff(tariff){
      tariff = this.removeUnwantedTags(tariff);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(tariff);
	  
	  let url = this.baseUrl+'/tariff';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
    updateTariff(tariff: Tariff){
      var key = tariff._id;
      tariff = this.removeUnwantedTags(tariff);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(tariff);
      let url = this.baseUrl+'/tariff/' + key;
      
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
}
