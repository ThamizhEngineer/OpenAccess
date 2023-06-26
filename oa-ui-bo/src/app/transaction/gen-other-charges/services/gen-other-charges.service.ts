import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { GenOtherCharges} from '../../vo/geoothercharges';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { CommonUtils } from '../../../shared/common/common-utils';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class GenOtherChargesService {
    constructor(private http:Http,private commonUtils: CommonUtils){
    }


  
        getGenOtherChargesById(_id: string){
        return this.http.get(environment.serviceApiUrl+'/transaction/genothercharges/'+_id,new CustomRequestOptions()).map(res => res.json()); 
             
    }

    getSellers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }
    
     getGenOtherCharges(companyServiceId:string,month:string,year:string,orgId:string,){
        
        var url = environment.serviceApiUrl+'transaction/genothercharges';
        if(companyServiceId!= ""  || month != "" || year!="" || orgId!=""  ) {
            url = url + "?dummy=1";  
            if(companyServiceId != "" && companyServiceId != undefined){
                url = url + "&sellerCompanyServiceId="+companyServiceId ;
            }
    
            if(orgId != "" && orgId != undefined){
                url = url + "&sellerOrgId="+orgId ;
            } 
            if(month != ""&& month != undefined){ 
                url = url + "&month="+month ; 
            } 
            if(year != ""&& year != undefined){
                url = url + "&year="+year ;
            }      

 
          }
     
        
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }

    getGenOtherChargesCompanyByEdc(sellerEndOrgId:string){
        
        var url = environment.serviceApiUrl+'/transaction/genothercharges';
        if( sellerEndOrgId !="" ) {
            url = url + "?dummy=1";  
          
            if(sellerEndOrgId != ""){
                url = url + "&sellerOrgId="+sellerEndOrgId ;
            } 
        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }
    addGenOtherCharges(genOtherCharges: any) {
        // console.log(genOtherCharges)
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(genOtherCharges);
        let url = environment.serviceApiUrl+'transaction/genothercharges/add';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    updateGenOtherCharges(genOtherCharges: any) {
        var key = genOtherCharges.id;       
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(genOtherCharges);
        let url = environment.serviceApiUrl+'transaction/genothercharges/update';

        return this.http.patch(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
}