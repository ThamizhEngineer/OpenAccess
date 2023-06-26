import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { environment } from "../../../../environments/environment";
import { CustomRequestOptions } from "../../../shared/common/common.service";

@Injectable()
export class importsellerService {

    constructor(private http: Http) {
    }
    
    saveSeller(importsellers){
        return this.http.post(environment.serviceApiUrl+'/transaction/import-seller/add',importsellers,new CustomRequestOptions()).map(res => res.json());
    }
    fetchServNo(number:string){
        return this.http.get(environment.serviceApiUrl+'/transaction/import-seller/getserviceno?number='+number,new CustomRequestOptions()).map(res => res.text());
    }
}