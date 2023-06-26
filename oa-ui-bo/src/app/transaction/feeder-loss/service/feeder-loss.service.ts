import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {SubstationLoss} from '../../vo/SubstationLoss'; 
import {ImportSubstationLoss} from '../../vo/ImportSubstationLoss'; 
import {CustomRequestOptions} from '../../../shared/common/common.service';
import { ImpFeederLoss } from '../../vo/imp-feeder-loss';

@Injectable()
export class FeederLossService {

    constructor(private http:Http){
    }
    
    getAllFunctionLoss(orgId:string,month:string,year:string){
        var url = environment.serviceApiUrl+'transaction/feeder-loss';
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

    getAllFeederLossById(id:string){
        return this.http.get(environment.serviceApiUrl + '/transaction/feeder-loss/'+id,new CustomRequestOptions()).map(res => res.json());
    }

    // getAllImpFeederLoss(){
    //     return this.http.get(environment.serviceApiUrl + '/transaction/import-feeder-loss/findAll',new CustomRequestOptions()).map(res => res.json());
    // }

    getAllImpFeederLossById(id:string){
        return this.http.get(environment.serviceApiUrl + '/transaction/import-feeder-loss/'+id,new CustomRequestOptions()).map(res => res.json());
    }

    addImportFeederLoss(impFeederLoss:any){
        console.log(impFeederLoss)
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(impFeederLoss);
        let url = environment.serviceApiUrl+'/transaction/import-feeder-loss/';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    UpdateimportFeeder(_id,impFeederLoss: ImpFeederLoss){
       
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(impFeederLoss);
        let url = environment.serviceApiUrl+'/transaction/import-feeder-loss/'+_id;
        return this.http.patch(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    deleteTradeRelationship(impFeederLoss: ImpFeederLoss) {
        
		var id = impFeederLoss.id;
        // impFeederLoss = this.removeUnwantedTags(impFeederLoss);
        let url = environment.serviceApiUrl + '/transaction/import-feeder-loss/'+id;
		return this.http.delete(url, new CustomRequestOptions()).map((res: Response) => res);
    }
}