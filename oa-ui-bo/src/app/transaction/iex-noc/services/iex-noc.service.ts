import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { IexNoc} from '../../vo/iex-noc'; 
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class IexNocService {
    constructor(private http:Http){
    }

    getIexNocById(id: string){
        console.log("in getbyid "+id);
        return this.http.get(environment.serviceApiUrl+'/transaction/iex-noc/'+id,new CustomRequestOptions()).map(res => res.json());      
    }

    getAllIexNocs(pageNumber?: any, pageSize?: any){
        var url = environment.serviceApiUrl+'/transaction/iex-noc';
        console.log('url-'+url+'PageNumber-'+pageNumber+'PageSize-'+pageSize);
        return this.http.get(url+'?&page='+ pageNumber+'&size='+pageSize,new CustomRequestOptions()).map((res: Response) => res.json());

    }

    approveIexNoc(iexnoc: IexNoc) {
        var key =  iexnoc.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(iexnoc);
        let url = environment.serviceApiUrl+'/transaction/iex-noc/approve/' + key;
        console.log('Approve Iex-Noc-' + JSON.stringify(iexnoc));
        console.log(url);
        return this.http.patch(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

}