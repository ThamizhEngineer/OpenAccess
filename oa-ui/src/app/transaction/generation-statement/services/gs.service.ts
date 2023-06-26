import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType} from '@angular/http'; 
import { Gs } from '../../vo/gs';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CommonUtils} from '../../../shared/common/common-utils';
import { saveAs } from 'file-saver';
import { Observable } from 'rxjs/Rx'
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class GsService {
    constructor(private http:Http,
    private commonUtils:CommonUtils){
        console.log('Generator Statement Service initialised');
    }

    getGsById(_id: string){
        //return this.http.get(environment.apiUrl+'/gs/gs/'+_id).map(res => res.json());   
        return this.http.get(environment.serviceApiUrl+'/api/gs/generationstatement/'+_id,new CustomRequestOptions()).map(res => res.json());    
    }
  
    getGss(){
        //return this.http.get(environment.apiUrl+'/gs/gss').map(res => res.json());  
        return this.http.get(environment.serviceApiUrl+'/api/gs/generationstatements',new CustomRequestOptions()).map(res => res.json());     
    }
    
        searchCompanies(dispCompanyName:string,orgId:string,companyServiceNumber:string,statementMonth:string,statementYear:string,companyServiceId:string){
        var url = environment.serviceApiUrl+'/api/gs/generationstatements';
        if(dispCompanyName != ""||orgId!="" ||  companyServiceNumber != ""||statementMonth!=""||statementYear!=""||companyServiceId!="") {
            url = url + "?dummy=1";  
            if(dispCompanyName != "" && dispCompanyName != undefined){
                url = url + "&company-name="+dispCompanyName ;
            }
            if(orgId!= ""){
                url = url + "&edc-id="+orgId;
            }
            if(companyServiceNumber != ""){
                url = url + "&service-number="+companyServiceNumber ;
            }
            if(statementMonth != ""){
                url = url + "&statement-month="+statementMonth;
            }
            if(statementYear != ""){
                url = url + "&statement-year="+statementYear;
            }

            if(companyServiceId!= ""){
                url = url + "&companyService-id="+companyServiceId;
            }
           
        }
       

    
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
     
        console.log(options);
        console.log('url-'+url);
       
        return this.http.get(url,  new CustomRequestOptions()).map((res: Response) => res.json());

    }

    // Fetch companyname
//   fetchCompanyname() {
//       console.log("In gs service")
      
//     return this.http.get(environment.serviceApiUrl+'/master/companies').map(res =>res.json());      
//        }
  
    //TO-DO - NEED-TO-BE-FIXED
    getNotAllocatedGss(){
        return this.http.get(environment.serviceApiUrl+'/gs/gss/not-allocated',new CustomRequestOptions()).map(res => res.json());      
    }

    //   searchEDCs(orgId:string){
    //     var url = environment.serviceApiUrl+'/master/companies';
    //     if(orgId != "" ) {
    //         url = url + "?dummy=1";  
    //         if(orgId != ""){
    //             url = url + "&EDCs-code="+orgId ;
    //         }
    //         // if(companyName != ""){
    //         //     url = url + "&company-name="+companyName ;
    //         // }
    //         // if(serviceAssociated != ""){
    //         //     url = url + "&service-associated="+serviceAssociated ;
    //         // }
    //         // if(enabled != ""){
    //         //     url = url + "&enabled="+enabled ;
    //         // } 
    //     }
       

    // //     console.log(url);
    // //    return this.http.get(url).map(res => res.json());
    //     let userToken =this.commonUtils.getUserTokens();
    //     //console.log(userToken);
    //      console.log(userToken.userToken);
    //     let headers = new Headers({ 'Content-Type': 'application/json'});
    //     headers.append('token',userToken.userToken);
    //     let options = new RequestOptions({ headers: headers });
     
    //     console.log(options);
    //     console.log('url-'+url);
       
    //     return this.http.get(url,  options).map((res: Response) => res.json());

    // }

    
  
    removeUnwantedTags(gs: Gs){
        // delete gs._id;
        // for (var index = 0; index < gs.bankingUnits.length; index++) {
        //         delete gs.bankingUnits[index]['$$index'];   
        // }
        // for (var index = 0; index < gs.charges.length; index++) {
        //         delete gs.charges[index]['$$index'];   
        // }
        // for (var index = 0; index < gs.generationSummaries.length; index++) {
        //         delete gs.generationSummaries[index]['$$index'];   
        // }
        // for (var index = 0; index < gs.readings.length; index++) {
        //         delete gs.readings[index]['$$index'];   
        // }
        // for (var index = 0; index < gs.slotSummaries.length; index++) {
        //         delete gs.slotSummaries[index]['$$index'];   
        // }
        // for (var index = 0; index < gs.blockSummaries.length; index++) {
        //         delete gs.blockSummaries[index]['$$index'];   
        // }
        return gs;    
    }

        // addGs(gs: Gs){
        // gs = this.removeUnwantedTags(gs);
        // let headers = new Headers({ 'Content-Type': 'application/json'});
        // let options = new RequestOptions({ headers: headers });
        // let body = JSON.stringify(gs);
        // let url = environment.apiUrl+'/gs/gs';
        // return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
        // }
    
        // updateGs(gs: Gs){
        // var key = gs.id;
        // gs = this.removeUnwantedTags(gs);
        // let headers = new Headers({ 'Content-Type': 'application/json'});
        // let options = new RequestOptions({ headers: headers });
        // let body = JSON.stringify(gs);
        // let url = environment.apiUrl+'/gs/gs/'+ key;
        // console.log('Generator Statement for update-'+JSON.stringify(gs));
        // return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
        // }

    printGs(gs: Gs){
        
    	let options = [{responseType: ResponseContentType.ArrayBuffer }];
        let url =environment.serviceApiUrl+'/transaction/generationstatement/'+gs.id+'/print';
		let CRO = new CustomRequestOptions();
        CRO.setHeaders(options);
        console.log(CRO)
        return this.http.get(url, CRO).catch((err: Response) => Observable.throw(err.json()));
      }

    printGen(gs: Gs){
        
        // let options = [{responseType: ResponseContentType.ArrayBuffer}];
        // let url =environment.serviceApiUrl+'/transaction/generationstatement/'+gs.id+'/print';
		// let CRO = new CustomRequestOptions();
        // CRO.setHeaders(options);
        // console.log(CRO)
        // return this.http.get(url, CRO).catch((err: Response) => Observable.throw(err.json()));


    var token = ""+sessionStorage.getItem('token');
    let headers = new Headers();
    headers.append('Authorization', token);
    headers.append('Response-Type', 'ArrayBuffer' );
        // let options = {responseType: ResponseContentType.ArrayBuffer , 'Authorization': sessionStorage.getItem('token') };

        let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.docApiUrl+'/api/print/GenerationStatement/'+gs.id;
        // let url =environment.serviceApiUrl+'/transaction/generationstatement/'+gs.id+'/print';
          return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));

    }  
  

}
