import { Injectable } from '@angular/core';
import { Http, Response, Headers,ResponseContentType, RequestOptions} from '@angular/http'; 

import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx';
import { environment } from '../../environments/environment';
import { GenericReportInput } from '../report/vo/GenericReportInput';
import { CommonUtils } from '../shared/common/common-utils';
import {CustomRequestOptions} from '../shared/common/common.service';
import { searchSldc, searchSldcLine } from './components/vo/search-sldc';
@Injectable()
export class SdlcNocService {
    constructor(private http:Http,private commonUtils: CommonUtils){
    }

    samastNocImport(){
        return this.http.get(environment.integrationUrl+'/samast-appln-import',new CustomRequestOptions()).map((res: Response) => res.json());        
    }

    samastNocApprovalImport(){
        return this.http.get(environment.integrationUrl+'/samast-approval-import',new CustomRequestOptions()).map(res => res.json());              
    }
  
    getSldcNocByPurpose(purpose: string){
        return this.http.get(environment.serviceApiUrl+'/transaction/sdlc-noc/purpose/'+purpose,new CustomRequestOptions()).map(res => res.json());              
    }

    getSldcNocById(id: string) {
        return this.http.get(environment.serviceApiUrl+'/transaction/sdlc-noc/'+id,new CustomRequestOptions()).map(res => res.json());              
    }
    
    saveSldcNocById(sldcNoc,id) {
        return this.http.patch(environment.serviceApiUrl+'/transaction/sdlc-noc/'+id,sldcNoc,new CustomRequestOptions()).map(res => res.json());              
    }

   sellerWithdraw(eosGcApprovalNumber: String,appType:String,approvalNo: String){
    var url = environment.serviceApiUrl+'/transaction/sdlc-noc/revised?';
    url = url + "dummy=1";

    if(eosGcApprovalNumber!= "" && eosGcApprovalNumber!=undefined){
        url = url + "&eosGcApprovalNumber="+eosGcApprovalNumber ; 
    }
    if(appType != ""&& appType!=undefined){
        url = url + "&appType="+appType ; 
    }  
    if(approvalNo != ""&& approvalNo!=undefined){
        url = url + "&approvalNo="+approvalNo ; 
    }  
   
    return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
   }

    searchSldcNoc(appliedDt:string,appliedNo:string,cntrctDemand:string,compServNo:string,edcId:string,nocCode:string,nocPurpose:string){
        var url = environment.serviceApiUrl+'/transaction/sdlc-noc/search';
        if( searchSldc !=null ) {
            url = url + "?dummy=1";  
            if(appliedDt != undefined){
                url = url + "&appliedDt="+appliedDt ;
            } 
            if(appliedNo!= undefined){
                url = url + "&appliedNo="+appliedNo ;
            } 
            if(cntrctDemand != undefined){
                url = url + "&cntrctDemand="+cntrctDemand ;
            } 
            if(compServNo != undefined){
                url = url + "&compServNo="+compServNo ;
            } 
            if(edcId != undefined){
                url = url + "&edcId="+edcId ;
            } 
            if(nocCode != undefined){
                url = url + "&nocCode="+nocCode ;
            } 
            if(nocPurpose != undefined){
                url = url + "&nocPurpose="+nocPurpose ;
            } 
            // if(status != undefined){
            //     url = url + "&status="+status ;
            // } 

        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }

    searchApprovalNoc(quantum:string,tSldcNocId:string,commitType:string,flowType:string,isExisting:string){
        
        var url = environment.serviceApiUrl+'/transaction/sdlc-noc/report';
        if( searchSldcLine !=null ) {
            url = url + "?dummy=1";  
            if(quantum != undefined){
                url = url + "&quantum="+quantum ;
            } 
            if(tSldcNocId!= undefined){
                url = url + "&tSldcNocId="+tSldcNocId ;
            } 
            if(commitType != undefined){
                url = url + "&commitType="+commitType ;
            } 
            if(flowType != undefined){
                url = url + "&flowType="+flowType ;
            } 
            // if(fuelGroup != undefined){
            //     url = url + "&compServNo="+fuelGroup ;
            // } 
            if(isExisting != undefined){
                url = url + "&isExisting="+isExisting ;
            }
        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }

    printSldcNoc(id){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.documentUrl+'/api/print/SldcNocPrint/'+id;
          return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
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

    
    getGenericReport(genReportInput: GenericReportInput){
        var url = environment.serviceApiUrl + '/report/generic-report';
        console.log(genReportInput);
        let params = {
			name: genReportInput.reportName,
			ip1: genReportInput.ip1,
			ip2: genReportInput.ip2,
			ip3: genReportInput.ip3,
			ip4: genReportInput.ip4,
			ip5: genReportInput.ip5,
			ip6: genReportInput.ip6,
			ip7: genReportInput.ip7,
			ip8: genReportInput.ip8,
			ip9: genReportInput.ip9,
			ip10: genReportInput.ip10
		};
		let CRO = new CustomRequestOptions();
		CRO.setParams(params);
        console.log(CRO);
        
        return this.http.get(url, CRO).map((res:Response) => res.json());
        
    }

    getReport(genReportInput: GenericReportInput){
        if (genReportInput.ip1 !=undefined && genReportInput.ip2 !=undefined && genReportInput.ip3 !=undefined){
        var url = environment.serviceApiUrl + '/report/generic-report';
        console.log(url)
        let params = {
            name: genReportInput.reportName,
            ip1: genReportInput.ip1,
			ip2: genReportInput.ip2,
			ip3: genReportInput.ip3,
			ip4: genReportInput.ip4,
			ip5: genReportInput.ip5,
			ip6: genReportInput.ip6,
			ip7: genReportInput.ip7,
			ip8: genReportInput.ip8,
			ip9: genReportInput.ip9,
			ip10: genReportInput.ip10
		}; 
		let CRO = new CustomRequestOptions();
		CRO.setParams(params);
        return this.http.get(url, CRO).map((res:Response) => res.json());
    }
}

getExtSamstAppln(){
    var url = environment.serviceApiUrl + '/report/generic-report';
    let params = {
        name: 'Ext-Samast-Appln-Table',
    }; 
    let CRO = new CustomRequestOptions();
    CRO.setParams(params);
    return this.http.get(url, CRO).map((res:Response) => res.json());

}
getNocapproval(){
    return this.http.get(environment.serviceApiUrl+'/Sldc/Noc/approvalrecords',new CustomRequestOptions()).map(res => res.json());              
}
getApprovalNocById(id:string) {
    console.log(id);
    
    return this.http.get(environment.serviceApiUrl+'/Sldc/Noc/'+id,new CustomRequestOptions()).map(res => res.json());              
}
printCusNoc(id){
    let options = {responseType: ResponseContentType.ArrayBuffer };
    let url =environment.documentUrl+'/api/print/ConsumerNocPrint/'+id;
      return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
}
searchDate(date:string){
    var url= environment.serviceApiUrl+'/transaction/sdlc-noc/getSldc?';
  
    if(date!=""&&date!= undefined){
          url=url+"date="+date;
    }
    return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
}
getcomservno(EosGcApprovalNumber:string){
    return this.http.get(environment.serviceApiUrl+'/Sldc/Noc/?EosGcApprovalNumber='+EosGcApprovalNumber,new CustomRequestOptions()).map(res => res.json());
}
getexistingbuyer(compServNo: String){

    return this.http.get(environment.serviceApiUrl+'/transaction/sdlc-noc/getHt/'+compServNo,new CustomRequestOptions()).map(res => res.json());

}

getexistingseller(compServNo: String){

    return this.http.get(environment.serviceApiUrl+'/transaction/sdlc-noc/getGc/'+compServNo,new CustomRequestOptions()).map(res => res.json());

}

getbuyercomservno(EobHtConsumerNumber:string){
    return this.http.get(environment.serviceApiUrl+'/Sldc/Noc/pick?EobHtConsumerNumber='+EobHtConsumerNumber,new CustomRequestOptions()).map(res => res.json());
}
getcompDetail(compServNo: String){
    return this.http.get(environment.serviceApiUrl+'/transaction/sdlc-noc/compname/'+compServNo,new CustomRequestOptions()).map(res => res.json());
}
}