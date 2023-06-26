import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { deleteTransaction  } from '../../../transaction/vo/delete-transaction';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class DeleteTransactionService {

    constructor(private http: Http) {

    }

    getDelTxnById(_id: string,readingMonth:string,readingYear:string) {
        return this.http.get(environment.serviceApiUrl+'/transaction/delete-transaction/get-by-gen-serv-no/' + _id+"/"+readingMonth+"/"+readingYear,new CustomRequestOptions()).map(res => res.json());
    }

    
    getDelTxns(orgId) {
       // return this.http.get(environment.serviceApiUrl+'/transaction/delete-transactions',new CustomRequestOptions()).map(res => res.json());
        console.log(orgId);
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&edc-id='+ orgId,new CustomRequestOptions()).map(res => res.json());
    }

    getDelTxnCompanyByEdc(buyerEndOrgId:string,sellerEndOrgId:string,sellerCompanyId:string){
        
            var url = environment.serviceApiUrl+'/transaction/delete-transaction';
            if( sellerEndOrgId !=""  ||  buyerEndOrgId != "" || sellerCompanyId != "" ) {
                url = url + "?dummy=1";  
                if(sellerEndOrgId != ""){
                    url = url + "&seller-edc-id="+sellerEndOrgId ;
                } 
                 if(buyerEndOrgId != ""){
                    url = url + "&buyer-org-id="+buyerEndOrgId ;
                }
                if(sellerCompanyId != ""){
                    url = url + "&seller-company-id="+sellerCompanyId ;
                }
            }
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    
        }
	

     getAllDelTxn(orgId?: string,genServiceNumber?: string,readingMonth?: string,readingYear?: string){
        
        var url = environment.serviceApiUrl+'/transaction/delete-transaction/getAllLog';
        if(orgId != "" || genServiceNumber != "" || readingMonth!="" || readingYear!="" ) {
            url = url + "?dummy=1";  
            if(orgId != ""){
                url = url + "&org-id="+orgId ;
            }
             if(genServiceNumber != ""){
                url = url + "&gen-service-number="+genServiceNumber ;
            } 
            if(readingMonth != ""){
                url = url + "&month="+readingMonth ;
            } 
            if(readingYear != "" && readingYear!=undefined){
                url = url + "&year="+readingYear ;
                console.log(readingYear,"readingYear");
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


    removeUnwantedTags(DeleteTransaction:deleteTransaction) {
        delete DeleteTransaction.id;
        return DeleteTransaction;
    }

    
    addDelTxn(DeleteTransactionList:any,transactionTypeCode:string) {
    console.log(DeleteTransactionList);
    DeleteTransactionList = this.removeUnwantedTags(DeleteTransactionList);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(DeleteTransactionList);
        let url = environment.serviceApiUrl+'/transaction/delete-transaction/post?transactionTypeCode='+transactionTypeCode;
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    updateDelTxn(DeleteTransaction:deleteTransaction) {
        var key = DeleteTransaction.id;
        DeleteTransaction = this.removeUnwantedTags(DeleteTransaction);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(DeleteTransaction);
        let url = environment.serviceApiUrl+'/transaction/delete-transaction/' + key;

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

}
