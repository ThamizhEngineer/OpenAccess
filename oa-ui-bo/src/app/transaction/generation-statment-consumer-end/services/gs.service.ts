import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType} from '@angular/http'; 
import { Gs } from '../../vo/gs';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CommonUtils} from '../../../shared/common/common-utils';
import { saveAs } from 'file-saver';
import { Observable } from 'rxjs/Rx'
import {CustomRequestOptions} from '../../../shared/common/common.service';
import { GenericReportInput } from '../../../report/vo/GenericReportInput';


@Injectable()
export class GsService {
    constructor(private http:Http,
    private commonUtils:CommonUtils){
        console.log('Generator Statement Service initialised');
    }

    getGsById(_id: string){
        //return this.http.get(environment.apiUrl+'/gs/gs/'+_id).map(res => res.json());   
        return this.http.get(environment.serviceApiUrl+'/transaction/generationstatement/'+_id,new CustomRequestOptions()).map(res => res.json());    
    }
  
    getGss(){
        //return this.http.get(environment.apiUrl+'/gs/gss').map(res => res.json());  
        return this.http.get(environment.serviceApiUrl+'/transaction/generationstatements',new CustomRequestOptions()).map(res => res.json());     
    }


    searchBuyerCompanies(orgId:string,companyServiceNumber:string,statementMonth:string,statementYear:string,fuelTypeCode:string){
        console.log(companyServiceNumber)
        var url = environment.serviceApiUrl+'/transaction/buyergenerationstatements';
        if(orgId!="" || companyServiceNumber!="" || statementMonth!=""||statementYear!="" || fuelTypeCode+"") {
            url = url + "?dummy=1";  
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
            if(fuelTypeCode!= ""){
                url = url + "&fuelTypeCode="+fuelTypeCode;
            }
        }
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
     
        console.log(options);
        console.log('url-'+url);
       
        return this.http.get(url,  new CustomRequestOptions()).map((res: Response) => res.json());

    }

    
        searchCompanies(dispCompanyName:string,orgId:string,companyServiceNumber:string,statementMonth:string,statementYear:string,companyServiceId:string,fuelTypeCode:string,fuelTypeName:string){
        var url = environment.serviceApiUrl+'/transaction/generationstatements';
        if(dispCompanyName != ""||orgId!="" ||  companyServiceNumber != ""||statementMonth!=""||statementYear!=""||companyServiceId!=""||fuelTypeCode!=""||fuelTypeName!="") {
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
           
            if(fuelTypeCode!= ""){
                url = url + "&fuelTypeCode="+fuelTypeCode;
            }

            if(fuelTypeName!= ""){
                url = url + "&fuelTypeName="+fuelTypeName;
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
        
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.documentUrl+'/api/print/GenerationStatement/'+gs.id;
          return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    }  
    getTmpGss(dispServiceNumber:any,stmtMonth:any,stmtYear:any){
        //return this.http.get(environment.apiUrl+'/gs/gss').map(res => res.json());  
        return this.http.get(environment.serviceApiUrl+'/transaction/temp-gen-stmts/new-meter-reading?dispServiceNumber='+dispServiceNumber+'&stmtMonth='+stmtMonth+'&stmtYear='+stmtYear,new CustomRequestOptions()).map(res => res.json());     
    }

    getPdf(docId){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        let url = environment.documentUrl+'doc-print/getpdf/'+docId;
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    }

    //  generate pdf

    generatePdf(stmtMonth:any,stmtYear:any){
        return this.http.get(environment.documentUrl+'doc-print/genPdf?month='+stmtMonth+'&year='+stmtYear,new CustomRequestOptions()).map(res => res.text());
    }

    //regenerate pdf
    regeneratePdf(gs: Gs){
        gs = this.removeUnwantedTags(gs);
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(gs);
        let url = environment.documentUrl+'doc-print/regen-pdf';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
      }

}
