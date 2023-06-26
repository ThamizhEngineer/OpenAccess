import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class LogService {
	assetUrl = environment.assetsApiUrl;
	serviceUrl = environment.serviceApiUrl;

    constructor(private http: Http) {}
	
	getLog(){

		return this.http.get(this.assetUrl+'/assets/data/progress-log.json',new CustomRequestOptions()).map(res => res.json()); 
	}

	getEsForLog(_id:string){
		return this.http.get(this.serviceUrl+'/transaction/processlog/ps?tEnergySaleId='+_id,new CustomRequestOptions()).map(res => res.json()); 
	}


	getEsStatus(_id:string){
		return this.http.get(this.serviceUrl+'/transaction/processlog/ps/status/'+_id,new CustomRequestOptions()).map(res => res.json()); 
	}

}