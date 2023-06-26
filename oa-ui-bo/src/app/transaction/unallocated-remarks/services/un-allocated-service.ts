import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { CustomRequestOptions } from '../../../shared/common/common.service';


@Injectable()
export class UnAllocatedService {
   
   
    

    constructor(private http: Http) {

    }
    fetchrecord(edcname:any,Month:any,Year:any,FlowTypes:any) {
        

        var url = environment.serviceApiUrl+'unallocatedremarks/all';
        url = url + "?dummy=1";
        if(edcname != ""){
            url = url + "&edcId="+edcname ;
        } 
        if(Month != ""){
            url = url + "&month="+Month ;
        } 
        if(Year != "" && Year!=undefined){
            url = url + "&year="+Year ;
            console.log(Year,"readingYear");
        }  
        if(FlowTypes != "" && FlowTypes!=undefined){
            url = url + "&flowtype="+FlowTypes ;
        }    
      
   

    console.log('url-'+url);
   
       
        console.log('url-'+url);
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }
    update(Servicenum:string,Month:string,Year:string,Remarks:string){

        var url = environment.serviceApiUrl+'unallocatedremarks/updatenew';
        url = url + "?dummy=1";
        if(Servicenum!= ""){
            url = url + "&Servicenum="+Servicenum ;
        } 
        if(Month != ""){
            url = url + "&month="+Month ;
        } 
        if(Year != "" && Year!=undefined){
            url = url + "&year="+Year ;
            console.log(Year,"readingYear");
        }  
        if(Remarks != ""){
            url = url + "&remarks="+Remarks ;
        }    
      
   

    console.log('url-'+url);
   
       
        console.log('url-'+url);
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.text());
    }

    gettingdetail(Servicenum:string,Month:string,Year:string){

        var url = environment.serviceApiUrl+'unallocatedremarks/fordetail';
        url = url + "?dummy=1";
        if(Servicenum!= ""){
            url = url + "&Servicenum="+Servicenum ;
        } 
        if(Month != ""){
            url = url + "&month="+Month ;
        } 
        if(Year != "" && Year!=undefined){
            url = url + "&year="+Year ;
            console.log(Year,"readingYear");
        }  

        console.log('url-'+url);
   
       
        console.log('url-'+url);
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

          
    }




}