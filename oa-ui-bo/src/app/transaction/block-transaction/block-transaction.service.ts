import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import { CustomRequestOptions } from '../../shared/common/common.service';
import { blockTransaction } from '../vo/block-transaction';


@Injectable()
export class BlockTransactionService {
    

    constructor(private http: Http) {

    }

    test(mCompServiceNumber:any,month:any,year:any){
        
        var url = environment.serviceApiUrl+'blocktransaction/get';

        url = url + "?dummy=1";
            if(mCompServiceNumber != ""){
                url = url + "&mCompServiceNumber="+mCompServiceNumber ;
            } 
            if(month != ""){
                url = url + "&month="+month ;
            } 
            if(year != "" && year!=undefined){
                url = url + "&year="+year ;
                console.log(year,"readingYear");
            }     
          
       

        console.log('url-'+url);
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }


    removeUnwantedTags(BlockTransaction:blockTransaction) {
        delete BlockTransaction.id;
        return BlockTransaction;
    }


    post(status:string, mCompServiceNumber:string){
        console.log(status);
        let url = environment.serviceApiUrl+'blocktransaction/updatestate/';
        url = url + "?dummy=1";
        if(mCompServiceNumber != ""){
            url = url + "&mCompServiceNumber="+mCompServiceNumber ;
        } 
        if(status != ""){
            url = url + "&status="+status;
        } 
        console.log('url-'+url);
        return this.http.get(url ,new CustomRequestOptions()).map((res: Response) => res.json());
    }
    

           
          }
    



