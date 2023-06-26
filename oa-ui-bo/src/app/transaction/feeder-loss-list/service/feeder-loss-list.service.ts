import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {SubstationLoss} from '../../vo/SubstationLoss'; 
import {ImportSubstationLoss} from '../../vo/ImportSubstationLoss'; 
import {CustomRequestOptions} from '../../../shared/common/common.service';
import { ImpFeederLoss } from '../../vo/imp-feeder-loss';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class FeederLossListService {

    constructor(private http:Http){
    }
    
    savesolar_data(data:any){
        return this.http.post(environment.serviceApiUrl+'/transaction/import-substation-loss',data,new CustomRequestOptions()).map(res=>res);
    }

    getDataVcompany(orgId:string,month:string,year:string){

        var url = environment.serviceApiUrl+'/transaction/getallFeeder?'
        if(orgId != "" &&orgId!=undefined &&orgId!="undefined"){
          url = url + "orgId="+orgId 
      } 
      if(month != "" &&month!=undefined){
          url = url + "&month="+month 
      } 
      if(year != "" &&year!=undefined){
        url = url + "&year="+year 
    } 
            return this.http.get(url,new CustomRequestOptions()).map(res => res.json());
    }

    getPrintData(orgId:string,month:string,year:string){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        var url = environment.serviceApiUrl+'/transaction/print/feederlineloss';
        url = url + "?dummy=1";

        if (orgId != ""||month !=""||year !="") {
            if (orgId != "") {
                url = url + "&orgId=" + orgId;
            }
            if (month != "") {
                url = url + "&month=" + month;
            }
            if (year != "") {
                url = url + "&year=" + year;
            }
            
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }
}
