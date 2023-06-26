import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {SubstationLoss} from '../../vo/SubstationLoss'; 
import {ImportSubstationLoss} from '../../vo/ImportSubstationLoss'; 
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class TenOneSSLossService {

    constructor(private http:Http){
    }
    getAllSubstationLoss(orgId:string,month:string,year:string){
        var url = environment.serviceApiUrl+'transaction/substation-loss';
        url = url + "?dummy=1"; 
        if(orgId != ""||month!=""||year!="") {
     
            if(orgId != "" && orgId != undefined) {
                url = url + "&org-id="+orgId;  
              
                 
            }
            if(month != "" && month != undefined) {
                url = url + "&month="+month;  
              
                 
            }
            if(year != "" && year != undefined) {
                url = url + "&year="+year;  
              
                 
            }
             
        }
       
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());
    }
    
    getAllSubstationLossById(id:string){
        return this.http.get(environment.serviceApiUrl + '/transaction/substation-loss/'+id,new CustomRequestOptions()).map(res => res.json());
    }

    addImportSubstationLoss(imssLossList:any){
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(imssLossList);
        let url = environment.serviceApiUrl+'/transaction/import-substation-loss';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

   
}
