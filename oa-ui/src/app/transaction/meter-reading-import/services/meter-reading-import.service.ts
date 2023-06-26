import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { MeterReadingImport} from './../../vo/meter-reading-import';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class MeterReadingImportService {
    constructor(private http:Http){
        console.log('Meter reading Import Service initialised');
    }

    getMeterReadingImportById(id: string){
        console.log("in getbyid "+id);
        return this.http.get(environment.serviceApiUrl+'/transaction/meterreadingimport/'+id,new CustomRequestOptions()).map(res => res.json());      
    }
  
    // getMeterReadingImports(){
    //     return this.http.get(environment.serviceApiUrl+'/transaction/meterreadingimports').map(res => res.json());      
    // }
    getMeterReadingImports(batchCode:string,importDate:string,status:string,source:string){

        var url =environment.serviceApiUrl+'/transaction/meterreadingimports';
        if(batchCode!=""||importDate!="" ||status !="" ||source !=""){
            url = url + "?dummy=1";
            
            if(batchCode!="" && batchCode!=null){
                url = url + "&code="+batchCode;
            }
            if(importDate!="" && importDate!=null){
                url = url + "&import-date="+importDate;
            }
            if(status!="" && status!=null){
                url = url + "&status="+status;
            }
            if(source!="" && source!=null){
                url = url + "&source="+source;
            }


        }
        console.log('url-'+url);
        
         return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());    
    }
  
    //returns all Buyer services
    getSellerCompanyServices(edcId:string) {
        // return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&is-internal=N').map(res => res.json());


        var url =environment.serviceApiUrl+'/master/companyservices?type=03&is-internal=N';
        if(edcId !=""){
            url = url + "&dummy=1";
            
            if(edcId!="" && edcId!=null){
                url = url + "&edc-id="+edcId;
            }
          


        }
        console.log('url-'+url);
        
         return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());    

    }
   
    

    addAndImportMeterReadingImport(meterReadingImport: MeterReadingImport){ 
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(meterReadingImport);
        let url = environment.serviceApiUrl+'/transaction/meterreadingimport/process';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    addMeterReadingImport(meterReadingImport: MeterReadingImport){ 
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(meterReadingImport);
      let url = environment.serviceApiUrl+'/transaction/meterreadingimport/';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
    updateMeterReadingImport(meterReadingImport: MeterReadingImport){
      var key = meterReadingImport.id;
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(meterReadingImport);
      let url = environment.serviceApiUrl+'/transaction/meterreadingimport/'+ key;
      console.log('Meter reading Import for update-'+JSON.stringify(meterReadingImport));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    
    processMeterReadingImport(batchid:string){
        var url = environment.serviceApiUrl+'/transaction/import-meter-readings';
     
          
            if(batchid != ""){
                url = url + "?dummy=1";  
                url = url + "&batch-id="+batchid ;
            }
            console.log(url)
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.text());
    }
  
}
