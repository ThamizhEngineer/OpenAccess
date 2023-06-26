import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
// import { MeterReadingImport} from './../../vo/meter-reading-import';
import 'rxjs/add/operator/map';
import { environment } from '../../../../../environments/environment';
import {CustomRequestOptions} from '../../../../shared/common/common.service';

@Injectable()
export class OperationService {
    constructor(private http:Http){
    }

    getMeterReadingFromMdms(month:string,year:string){

        var url = environment.serviceApiUrl+'/transaction/importmeterreadingfrommdms';
        if(month != "" || year != "") {
            url = url + "?dummy=1";  
            if(month != ""){
                url = url + "&month="+month ;
            }
            if(year != ""){
                url = url + "&year="+year ;
            }


   
        }
        // let userToken =this.commonUtils.getUserTokens();
        // //console.log(userToken);
        //  console.log(userToken.userToken);
        // let headers = new Headers({ 'Content-Type': 'application/json'});
        // headers.append('token',userToken.userToken);
        // let options = new RequestOptions({ headers: headers });
     
        // console.log(options);
        // console.log('url-'+url);
       
             

        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.text());
    }
        getMeterReading(batchCode:string){
        
        var url = environment.serviceApiUrl+'/transaction/import-meter-readings';
        if(batchCode != "" ) {
            url = url + "?dummy=1";  
            if(batchCode != ""){
                url = url + "&batch-code="+batchCode ;
            }

   
        }
        // let userToken =this.commonUtils.getUserTokens();
        // //console.log(userToken);
        //  console.log(userToken.userToken);
        // let headers = new Headers({ 'Content-Type': 'application/json'});
        // headers.append('token',userToken.userToken);
        // let options = new RequestOptions({ headers: headers });
     
        // console.log(options);
        // console.log('url-'+url);
       
             

        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.text());
    }


     getGenerationStatement(year:string,month:string){
        
        var url = environment.serviceApiUrl+'/transaction/process-generation-statements';
        if(year != "" || month !="") {
            url = url + "?dummy=1";  
            if(year != ""){
                url = url + "&year="+year ;
            }  
            if(month != ""){
                url = url + "&month="+month ;
            }

   
        }
        // let userToken =this.commonUtils.getUserTokens();
        // //console.log(userToken);
        //  console.log(userToken.userToken);
        // let headers = new Headers({ 'Content-Type': 'application/json'});
        // headers.append('token',userToken.userToken);
        // let options = new RequestOptions({ headers: headers });
     
        // console.log(options);
        // console.log('url-'+url);
       
             

        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.text());
    }

}