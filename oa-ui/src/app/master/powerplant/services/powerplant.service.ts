import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType} from '@angular/http'; 
import { CommonUtils } from '../../../shared/common/common-utils';

import { Powerplant } from './../../vo/powerplant';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class PowerplantService {
    constructor(private http: Http,private commonUtils: CommonUtils) { }

    getPowerplantByServiceId(serviceId: string) {
        return this.http.get(environment.serviceApiUrl + 'api/my-powerplants/' + serviceId,new CustomRequestOptions()).map(res => res.json());
    }

    printPPGenMaster(powerplant:Powerplant){
        
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/api/my-powerplants/'+powerplant.id+'/print';
          return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    }  

    // confirmMaster(userName:string){
    //     console.log(userName)
    //     let url=environment.serviceApiUrl+'master/Companiesnew/confirmMaster/'+userName;
    //     console.log(url)
    //     return this.http.patch(url,new CustomRequestOptions()).map(res => res.json());    

    // }

    confirmMaster(userName:string) {
        // let headers = this.commonUtils.getHeaders();
        // let options = new RequestOptions({ headers: headers });
        let url = environment.serviceApiUrl+'users/confirmMaster/'+userName;
        return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res);
    }
}