import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
//import { LocalStorageService } from 'angular-2-local-storage';
import { Observable } from 'rxjs/Rx'


import { CustomRequestOptions } from '../../shared/common/common.service';
import { User } from './../../user/services/user';
import { AuthUser } from './../../user/services/authuser';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';

@Injectable()
export class LoginService {
  
  baseUrl: string = environment.authApiUrl;
  clientId: string = environment.clientId;

  constructor(private http: Http) { }

  login(user: User) {
    let body = JSON.stringify({ 'userName': user.userName, 'password': user.password, appClientId: this.clientId,'uniqueId' : user.uniqueId });
    let url = this.baseUrl + '/tokens/login';
    
    if(environment.dataSource == "api"){
    
      return this.http.post(url, body, this.jwt()).map((res: Response) => res.json());
    }
    else{
      url =   environment.assetsApiUrl + '/assets/data/login.json'; 
      return this.http.get(url, body).map((res: Response) => res.json());
    }
    
  }

  setToken(user: AuthUser) {
    sessionStorage.clear;
    sessionStorage.setItem('user', JSON.stringify(user));
    sessionStorage.setItem('token', user.token);
    return true;
  }

  private jwt() {
    let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
    return new RequestOptions({ headers: headers });
  }
  setIsloggedin(user: AuthUser) {

    let body = JSON.stringify(user);
    let url = this.baseUrl + '/tokens/setloggedin';
    return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
  }



  // Logout ----------------------------------------

  logout(){
    let authUser = JSON.parse(sessionStorage.getItem('user'));
    if (authUser) {
        let obj = JSON.stringify({ userName: authUser.userName, token: authUser.token });
        console.log(obj)
        return this.http.post(this.baseUrl + 'tokens/logout', obj, this.setCustomHeader(authUser.token)).map(res => { });
    }
    else return Observable.of(null);
  }
  

  private setCustomHeader(token) {
    let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json','authorization': token });
    return new RequestOptions({ headers: headers });
  }


}
