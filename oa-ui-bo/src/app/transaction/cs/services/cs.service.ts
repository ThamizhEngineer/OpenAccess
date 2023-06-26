import { Injectable,EventEmitter } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { Cs} from '../../vo/cs'; 
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class CsService {
    constructor(private http:Http){
    }
    csEvent: EventEmitter<Cs> = new EventEmitter();
    setCs(cs: Cs) {
        this.csEvent.emit(cs);

    }
    getCsCompanyByEdc(buyerOrgId:string,sellerOrgId:string){
        
            var url = environment.serviceApiUrl+'/transaction/css';
            if(buyerOrgId != "" || sellerOrgId !="" ) {
                url = url + "?dummy=1";  
                if(buyerOrgId != ""){
                    url = url + "&buyer-edc-id="+buyerOrgId ;
                }
                if(sellerOrgId != ""){
                    url = url + "&seller-edc-id="+sellerOrgId ;
                } 
            }
            console.log(url)
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    
        }

        getAllCss(sellerEdcId:string,sellerCompanyServiceId:string, csCode:string, status:string) {
            var url = environment.serviceApiUrl+'/transaction/css';
            
            if( sellerEdcId != "" || csCode != "" || sellerCompanyServiceId != ""||status!="") {
                url = url + "?dummy=1";  
                
               
                if(sellerEdcId != "" && sellerEdcId!=undefined){
                    url = url + "&seller-edc-id="+sellerEdcId ;
                }
    
                if(csCode != ""  && csCode!=undefined){
                    url = url + "&code="+csCode ;
                }
        
                if(sellerCompanyServiceId != "" && sellerCompanyServiceId!=undefined){
                    url = url + "&seller-company-service-id="+sellerCompanyServiceId ;
                }
                if(status != "" && status!=undefined){
                    url = url + "&status="+status ;
                }
            }		
            console.log(url)
            
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
        }

    getCompanyService(id: string){
        console.log(id)
        return this.http.get(environment.serviceApiUrl+'/master/companyservices?comp-service-id='+id,new CustomRequestOptions()).map(res => res.json());      
    }
	
	getCsById(id: string){
        return this.http.get(environment.serviceApiUrl+'/transaction/cs/'+id,new CustomRequestOptions()).map(res => res.json());      
    }


	getCs(){
        var url = environment.serviceApiUrl+'/transaction/css';
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }

	addCs(cs: Cs){ 
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(cs);
      let url = environment.serviceApiUrl+'/transaction/cs';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
    }
  
    updateCs(cs: Cs){
      var key = cs.id;
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(cs);
      let url = environment.serviceApiUrl+'/transaction/cs/'+ key;
      console.log('Cs for update-'+JSON.stringify(cs));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
    }
	
	completeCs(cs: Cs) {
        var key =  cs.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(cs);
        let url = environment.serviceApiUrl+'/transaction/cs/' + key + '/complete';
        console.log('update Cs-' + JSON.stringify(cs));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }

	approveCs(cs: Cs) {
        var key =  cs.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(cs);
        let url = environment.serviceApiUrl+'/transaction/cs/' + key + '/approve';
        console.log('approve Cs-' + JSON.stringify(cs));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }
}