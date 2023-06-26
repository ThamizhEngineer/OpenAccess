import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class AuthenticationService {
    baseUrl: string = environment.authApiUrl;
    clientId: string = environment.clientId;

    constructor(private http: Http) { }


    refresh() {
        let authUser = JSON.parse(sessionStorage.getItem('user'));
        if (authUser) {
            let obj = JSON.stringify({ userName: authUser.userName, token: authUser.token });
            return this.http.post(this.baseUrl + 'tokens/refresh', obj, this.jwt(authUser.token)).map((response: Response) => response.json());
        } else return Observable.of(null);
    }

    private jwt(token) {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json', 'authorization': token });
        return new RequestOptions({ headers: headers });
    }


    // Logout ----------------------------------------

    logout() {
        let authUser = JSON.parse(sessionStorage.getItem('user'));
        if (authUser) {
            let obj = JSON.stringify({ userName: authUser.userName, token: authUser.token });
            console.log(obj)
            return this.http.post(this.baseUrl + 'tokens/logout', obj, this.setCustomHeader(authUser.token)).map(res => { });
        }
        else return Observable.of(null);
    }


    private setCustomHeader(token) {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json', 'authorization': token });
        return new RequestOptions({ headers: headers });
    }
}