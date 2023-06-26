import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Org } from '../../vo/org';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class GeneratorhisService {
    constructor(private http:Http){
    }
    getHistoryByServiceNo(mCompServNumber: string){
        return this.http.get(environment.serviceApiUrl+'/master/generatorhis/'+mCompServNumber,new CustomRequestOptions()).map(res => res.json());
    }

    getGeneratorDetail(mCompServNumber: string){

        return this.http.get(environment.serviceApiUrl+'/master/generatorhis/detail/'+mCompServNumber,new CustomRequestOptions()).map(res => res.json());
    }

    getTradeData(sellerCompServiceNumber:string){
        return this.http.get(environment.serviceApiUrl+'/master/generatorhis/trade/'+sellerCompServiceNumber,new CustomRequestOptions()).map(res => res.json());
    }


}
