import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Stb } from './stb';
import { Payment} from './../../vo/payment';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class StbService {
    constructor(private http:Http){
        console.log('Sale to Board Service initialised');
    }

    getStbById(_id: string){
        // return this.http.get(environment.apiUrl+'/gs/gs/'+_id).map(res => res.json());      
        return this.http.get(environment.serviceApiUrl+'/transaction/energysale/'+_id,new CustomRequestOptions()).map(res => res.json());      
    }
  
    getStb(){
        // console.log("In getstb");
        // return this.http.get(environment.apiUrl+'/gs/gss').map(res => res.json());      
        console.log("In getstb");
        return this.http.get(environment.serviceApiUrl+'/transaction/energysales?is-stb=Y',new CustomRequestOptions()).map(res => res.json());      
    }

    getAllPayments(val:string){
        // console.log("In getstb");
        // return this.http.get(environment.apiUrl+'/gs/gss').map(res => res.json());      

        return this.http.get(environment.serviceApiUrl+'/transaction/payments?payment-context-code=ENERGY-SALE&context-ref-num='+val,new CustomRequestOptions()).map(res => res.json());      
    }
  
    //TO-DO - NEED-TO-BE-FIXED
    getNotAllocatedStb(){
        return this.http.get(environment.serviceApiUrl+'/gs/gss/not-allocated',new CustomRequestOptions()).map(res => res.json());      
    }
  
    removeUnwantedTags(stb: Stb){
        delete stb._id;
        return stb;    
    }

    addStb(payment: Payment){
        console.log(payment);
   
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(payment);
        let url = environment.serviceApiUrl+'/transaction/payment';
        console.log(url)
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
  
    updateStb(payment: Payment){
        console.log(payment);
       
        var key = payment.id;
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(payment);
        let url = environment.serviceApiUrl+'/transaction/payment/'+ key;
  
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
  
}
