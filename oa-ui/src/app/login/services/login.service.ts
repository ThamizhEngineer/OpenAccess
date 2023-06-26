import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
//import { LocalStorageService } from 'angular-2-local-storage';


import { User } from './../../user/services/user';
import { AuthUser } from './../../user/services/authuser';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';

@Injectable()
export class LoginService {
    baseUrl: string = environment.authApiUrl;
	clientId: string = environment.clientId;  
  
    constructor(private http:Http){}  

    login(user: User){
		let body = JSON.stringify({'userName': user.userName, 'password': user.password, appClientId: this.clientId,'uniqueId' : user.uniqueId});
		let url = this.baseUrl+'/tokens/login';

		return this.http.post(url, body, this.jwt()).map((res: Response) => res.json());
    }

    setToken(user: AuthUser){
      sessionStorage.clear;
      sessionStorage.setItem('user', JSON.stringify(user));
      sessionStorage.setItem('token', user.token);
      return true;
    }
	
	private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
        return new RequestOptions({ headers: headers });
    }

    getTotalGenerator(){
		return this.http.get( 'assets/data/login-generator-total-chart.json', this.jwt()).map((response: Response) => response.json());
    }
    
    getEdcGenerator(){
      return this.http.get( 'assets/data/login-generator-edc-chart.json', this.jwt()).map((response: Response) => response.json());
    }

    getAllGsRepSummary(){
      var url = environment.reportUrl+'/report/login-page-data';
      return this.http.get(url,this.jwt()).map((response: Response) => response.json());
    }
}
