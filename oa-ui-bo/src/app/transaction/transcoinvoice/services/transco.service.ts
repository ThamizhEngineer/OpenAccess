import { TranscoInvoice } from './../../vo/transcoinvoice';
import { id } from '@swimlane/ngx-datatable/release/utils';
import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';

import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../../environments/environment';
import { CustomRequestOptions } from '../../../shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { empty } from 'rxjs/Observer';
@Injectable()
export class TranscoService {
 
 
  


  
   

    constructor(private http: Http,private commonUtils: CommonUtils) {

    }
    
    getallTransco(mOrgId,lineMonthYear,mCompServId,invStatus,invid){
        console.log('came in')
        // var url =environment.serviceApiUrl+'invoicetransco/getall';
        // return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
        var url = environment.serviceApiUrl+'invoicetransco/all?'
        if(mOrgId != "" &&mOrgId!=undefined &&mOrgId!="undefined"){
          url = url + "mOrgId="+mOrgId 
      } 
      if(lineMonthYear != "" &&lineMonthYear!=undefined){
          url = url + "&lineMonthYear="+lineMonthYear 
      } 
    //   if(lineYear != "" &&lineYear!=undefined){
    //       url = url + "&lineYear="+lineYear 
    //   }  
      if(mCompServId != ""&& mCompServId!=undefined){
          url = url + "&mCompServId="+mCompServId 
          
      } 
      if(invStatus != ""&& invStatus!=undefined){
        url = url + "&invStatus="+invStatus 
        
    }  
    if(invid != ""&& invid!=undefined && invid!="undefined"){
        url = url + "&invid="+invid 
        console.log(invStatus,"invStatus")
    }         
    
  console.log('url-'+url);
 
     
      return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json())
      }
     

      forwardtoedc(invoiceid:string,Month:string,year:string,mComservid:string,UserName:string){
        var url = environment.serviceApiUrl+'invoicetransco/forwardtoEdc';
        url = url + "?dummy=1";
        if(invoiceid!= "" && invoiceid!=undefined){
            url = url + "&Invoicenum="+invoiceid ;
        } 
        if(Month != "" && Month!=undefined){
            url = url + "&month="+Month ;
        } 
        if(year != "" && year!=undefined){
            url = url + "&year="+year ;
            
        }  
        if(mComservid != "" && mComservid!=undefined){
            url = url + "&mComservid="+mComservid ;
        }   
        if(UserName != "" && UserName!=undefined){
            url = url + "&UserName="+UserName ;
        }    
        console.log('url-'+url);
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.text());
    }


    confirmById(invoiceid:string,Month:string,year:string,mComservid:string,UserName:string){
        var url = environment.serviceApiUrl+'invoicetransco/confirm';
        url = url + "?dummy=1";
        if(invoiceid!= "" && invoiceid!=undefined){
            url = url + "&Invoicenum="+invoiceid ;
        } 
        if(Month != "" && Month!=undefined){
            url = url + "&month="+Month ;
        } 
        if(year != "" && year!=undefined){
            url = url + "&year="+year ;
            
        }  
        if(mComservid != "" && mComservid!=undefined){
            url = url + "&mComservid="+mComservid ;
        } 
        
        if(UserName != "" && UserName!=undefined){
            url = url + "&UserName="+UserName ;
        }
        console.log('url-'+url);
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.text());
    }

    removeUnwantedTags(trancscoinvoiclist: TranscoInvoice){
        
		delete trancscoinvoiclist['$$index'];   
        return trancscoinvoiclist;    
    }

    bulkinvoicefortoedc(invid: String[],username:String) {
        console.log("came here")
       // trancscoinvoiclist = this.removeUnwantedTags(trancscoinvoiclist);
     let headers = new Headers({ 'Content-Type': 'application/json' });
     //let headers = this.commonUtils.getHeaders();
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(invid);
      console.log(body)
      let url = environment.serviceApiUrl+'/invoicetransco/buklinvoiceforEdc?username='+username;
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }


    bulkinvoiceforConfirm(invid: String[],username:String){

      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(invid);
      let url = environment.serviceApiUrl+'invoicetransco/buklinvoiceconfirm?username='+username;
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
    
    getforinvdetail(lineMonth: string, lineYear: string, mCompServId: string, id: string) {
        console.log('came in')
        
        var url = environment.serviceApiUrl+'invoicetransco/forDetail?'
        
      if(lineMonth != "" &&lineMonth!=undefined){
          url = url + "&lineMonth="+lineMonth 
      } 
      if(lineYear != "" &&lineYear!=undefined){
          url = url + "&lineYear="+lineYear 
      }  
      if(mCompServId != ""&& mCompServId!=undefined){
          url = url + "&mCompServId="+mCompServId 
          
      } 
      if(id != ""&& id!=undefined){
        url = url + "&invid="+id 
        
    } 
    
       
    
  console.log('url-'+url);
 
     
      return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json())
    }

    gettotalinwords(total: number){


        var url = environment.serviceApiUrl+'invoicetransco/fortotalinwords?'
        url = url + "?dummy=1";
       
        if(total!=undefined){
          url = url + "&total="+total 
          
      }  
            
      
    console.log('url-'+url);
   
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.text())

        
    }
       
  
    getchargesdetail(id: string) {
        var url = environment.serviceApiUrl+'invoicetransco/forChargesDetail?'
        url = url + "?dummy=1";
       
        if(id != ""&& id!=undefined){
          url = url + "&invid="+id 
          
      }  
            
      
    console.log('url-'+url);
   
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json())
      }
      
      SendMail(invid: String[], UserName: any) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(invid);
        let url = environment.serviceApiUrl+'invoicetransco/sendmail?username='+UserName;
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res)
      }
      printTranscoInvoice(invid: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'invoicetransco/print/transcoInvoice';
        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        url = url + "?dummy=1";  

        if (invid != "") {
            if (invid != "") {
                url = url + "&id=" + invid;
            }
            
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }
    
    bulkinvoicenew(rows: TranscoInvoice[]) {
       
        
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(rows);
        let url = environment.serviceApiUrl+'/invoicetransco/invid';

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
      }
      printTranscoInvoiceCharges(invid: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'invoicetransco/print/transcoInvoiceOMCharges';
        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        url = url + "?dummy=1";  

        if (invid != "") {
            if (invid != "") {
                url = url + "&id=" + invid;
            }
            
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }
  }
      