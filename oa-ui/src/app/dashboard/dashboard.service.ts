import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 

import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';

@Injectable()
export class DashboardService {
  
    constructor(private http:Http){
       
    }

    getDataSetById(_id: string){
        return this.http.get(environment.serviceApiUrl+'/fetch-data-sets/'+_id).map(res => res.json());      
    }
	
	getUserDetail(){
		return JSON.parse(sessionStorage.getItem('user'));
    }

  
}
