import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
//import { LocalStorageService } from 'angular-2-local-storage';


import { User } from './../../user/services/user';
import { AuthUser } from './../../user/services/authuser';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import {CustomRequestOptions} from '../../shared/common/common.service';

@Injectable()
export class AccountService {
    baseUrl: string = environment.authApiUrl;
  
  
    constructor(private http:Http//, private localStorageService: LocalStorageService
    ){
       // console.log('User Service initialised');
    }
  
	getUser(){
		let currentUser = JSON.parse(sessionStorage.getItem('user'));
		if (currentUser) return currentUser;
	}
	
	private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json', 'Access-Control-Allow-Methods':'*' });
        return new RequestOptions({ headers: headers });
    }
	
    updatePassword(obj, token){
		return this.http.patch(this.baseUrl+"auth/users/" + token + "/change-password/token", obj, new CustomRequestOptions()).map((response: Response) => response.json());
	}
    validatePassword(obj, token){
		return this.http.patch(this.baseUrl+"auth/users/" + token + "/validate-password/token", obj, new CustomRequestOptions()).map((response: Response) => response.json());
	}
}
