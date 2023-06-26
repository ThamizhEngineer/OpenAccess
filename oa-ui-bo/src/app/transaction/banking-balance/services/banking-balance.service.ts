import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { BankingBalance} from './../../vo/banking-balance';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { CommonUtils } from '../../../shared/common/common-utils';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class BankingBalanceService {
    constructor(private http:Http,private commonUtils: CommonUtils){
    }


    //      getMeterReading(){
    //     return this.http.get(environment.serviceApiUrl+'/transaction/meterreadings/').map(res => res.json());      
    // }
        getBankingBalanceById(_id: string){
        return this.http.get(environment.serviceApiUrl+'/transaction/bankingbalance/'+_id,new CustomRequestOptions()).map(res => res.json());      
    }

    // getCompanyService(id: string){
    //     console.log(id)
    //     return this.http.get(environment.serviceApiUrl+'/master/companyservices?comp-service-id='+id,new CustomRequestOptions()).map(res => res.json());      
    // }
    
    getSellers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }
     getBankingBalance(companyServiceId:string,month:string,year:string,orgId:string,){
        
        var url = environment.serviceApiUrl+'/transaction/bankingbalances';
        if(companyServiceId!= ""  || month != "" || year!="" || orgId!=""  ) {
            url = url + "?dummy=1";  
            if(companyServiceId != "" && companyServiceId != undefined){
                url = url + "&seller-company-service-id="+companyServiceId ;
            }
    
            if(orgId != "" && orgId != undefined){
                url = url + "&seller-org-id="+orgId ;
            } 
            if(month != ""&& month != undefined){ 
                url = url + "&month="+month ; 
            } 
            if(year != ""&& year != undefined){
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
        console.log('url-'+url);
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }

    getExcessUnit(companyServiceId:string,month:string,year:string){
        var url = environment.serviceApiUrl+'/transaction/excessunit';
        if(companyServiceId!= ""  || month != "" || year!=""){
            url = url + "?dummy=1";  
            if(companyServiceId != "" && companyServiceId != undefined){
                url = url + "&supplierid="+companyServiceId ;
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

    getBankingBalanceCompanyByEdc(sellerEndOrgId:string){
        
        var url = environment.serviceApiUrl+'/transaction/bankingbalances';
        if( sellerEndOrgId !="" ) {
            url = url + "?dummy=1";  
          
            if(sellerEndOrgId != ""){
                url = url + "&seller-edc-id="+sellerEndOrgId ;
            } 
        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }
    addBankingBalance(bankingBalance: BankingBalance) {
  
        // let headers = new Headers({ 'Content-Type': 'application/json' }); 
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(bankingBalance);
        let url = environment.serviceApiUrl+'/transaction/bankingbalance';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    importBankingBalance(bankingBalance: BankingBalance) {
  
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(bankingBalance);
        let url = environment.serviceApiUrl+'/transaction/impBankingBalance/processImpBankingBalance';
        // return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
        return this.http.post(url,body, new CustomRequestOptions()).map((res: Response) => res.text());

    }

    updateBankingBalance(bankingBalance: BankingBalance) {
        var key = bankingBalance.id;       
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(bankingBalance);
        let url = environment.serviceApiUrl+'/transaction/bankingbalance/' + key;

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
}