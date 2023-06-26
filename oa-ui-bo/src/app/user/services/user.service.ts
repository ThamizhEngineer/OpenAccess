import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 

import { User } from './user';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import {CustomRequestOptions} from '../../shared/common/common.service';

@Injectable()
export class UserService {
    baseUrl: string = environment.serviceApiUrl+'/user';
  
    constructor(private http:Http){
       // console.log('User Service initialised');
    }

    getUserById(_id: string){
        return this.http.get(this.baseUrl+'/user/'+_id,new CustomRequestOptions()).map(res => res.json());      
    }
  
    getUsers(){
        return this.http.get(this.baseUrl+'/users',new CustomRequestOptions()).map(res => res.json());      
    }
  

    addUser(user: User){
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(user);
      let url = this.baseUrl+'/user';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
    updateUser(user: User){
      var key = user._id;
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(user);
      let url = this.baseUrl+'/user/'+ key;
      console.log('User for update-'+JSON.stringify(user));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
}
