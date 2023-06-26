import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
// import { MeterReadingImport} from './../../vo/meter-reading-import';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class MeterReadingService {
    constructor(private http:Http){
    }


    //      getMeterReading(){
    //     return this.http.get(environment.serviceApiUrl+'/transaction/meterreadings/').map(res => res.json());      
    // }
        getMrById(_id: string){
        return this.http.get(environment.serviceApiUrl+'/transaction/meterreading/'+_id,new CustomRequestOptions()).map(res => res.json());      
    }
     getMeterReading(companyServiceNumber:string,modemNumber:string,meterNumber:string,month:string,year:string,orgId:string,companyName:string,fuelTypes:string,flowTypeCode:string){
        
        var url = environment.serviceApiUrl+'/transaction/meterreadings';
        if(companyServiceNumber!= "" || modemNumber != "" || meterNumber != "" || month != "" || year!="" || orgId!="" || companyName!="" || fuelTypes!="" ) {
            url = url + "?dummy=1";  
            if(companyServiceNumber != ""){
                url = url + "&company-service-number="+companyServiceNumber ;
            }
            if(modemNumber != ""){
                url = url + "&modem-number="+modemNumber ;
            }
            if(meterNumber != ""){
                url = url + "&meter-number="+meterNumber ;
            } 
            if(companyName != ""){
                url = url + "&company-name="+companyName ;
            }
            if(orgId != ""){
                url = url + "&org-id="+orgId ;
            } 
            if(month != ""){ 
                url = url + "&month="+month ; 
            } 
            if(year != ""){
                url = url + "&year="+year ;
            }  
            if(fuelTypes != ""){
                url = url + "&fuel-code="+fuelTypes ;
            }    
            if(flowTypeCode != ""){
                url = url + "&flowType-code="+flowTypeCode ;
            }    

 
          }
        // let userToken =this.commonUtils.getUserTokens();
        // //console.log(userToken);
        //  console.log(userToken.userToken);
        // let headers = new Headers({ 'Content-Type': 'application/json'});
        // headers.append('token',userToken.userToken);
        // let options = new RequestOptions({ headers: headers });
     
        // console.log(options);
        console.log('url-'+url);
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

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