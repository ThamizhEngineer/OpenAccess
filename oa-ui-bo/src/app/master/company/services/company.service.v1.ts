import { Injectable,EventEmitter } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Company, Service } from './../../vo/company.v1';
import 'rxjs/add/operator/map';
//import {PlatformLocation } from '@angular/common';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';
import {CommonUtils} from '../../../shared/common/common-utils';

@Injectable()
export class CompanyServiceV1 {
    companyEvent: EventEmitter<Company> = new EventEmitter();
    baseUrl: string = environment.serviceApiUrl+'/master';
	
    constructor(private http:Http,
                private commonUtils:CommonUtils){
  
        
    }

    setCompany(_company: Company){
        this.companyEvent.emit(_company);
    }

    getCompanyById(_id: string){
        return this.http.get(this.baseUrl+'/company/'+_id, new CustomRequestOptions())
            .map(res => res.json());      
    }
  
  
    
    searchCompanies(companyCode:string,companyName:string,serviceAssociated:string,enabled:string){
        var url = environment.serviceApiUrl+'/master/companies?is-seller=Y';
        if(companyCode != "" || companyName != "" || serviceAssociated != "" || enabled !="") {
            // url = url + "?dummy=1";  
            if(companyCode != ""){
                url = url + "&company-code="+companyCode ;
            }
            if(companyName != ""){
                url = url + "&company-name="+companyName ;
            }
            if(serviceAssociated != ""){
                url = url + "&service-associated="+serviceAssociated ;
            }
            if(enabled != ""){
                url = url + "&enabled="+enabled ;
            } 
        }
        
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });
     
        console.log(options);
        console.log('url-'+url);
       
        return this.http.get(url,  new CustomRequestOptions()).map((res: Response) => res.json());

    }

    removeUnwantedTags(company: Company){
        delete company.id;
        // for (var index = 0; index < company.addresses.length; index++) {
        //         delete company.addresses[index]['$$index'];   
        // }
        
        // for (var index = 0; index < company.contacts.length; index++) {
        //         delete company.contacts[index]['$$index'];        
        // }

        // for (var index = 0; index < company.employees.length; index++) {
        //         delete company.employees[index]['$$index'];        
        // }

        for (var index = 0; index < company.shareHolders.length; index++) {
                delete company.shareHolders[index]['$$index'];        
        }

        for (var index = 0; index < company.services.length; index++) {
                delete company.services[index]['$$index'];        
        }
        
        for (var index = 0; index < company.meters.length; index++) {
                delete company.meters[index]['$$index'];        
        }

        return company;
    }

    addCompany(company: Company){
    
      company = this.removeUnwantedTags(company);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(company);
      let url = this.baseUrl+'/company';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
  
    updateCompany(company: Company){
      var key = company.id;
      company = this.removeUnwantedTags(company);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(company);
      let url = this.baseUrl+'/company/'+key;
      console.log('url-'+url);
      console.log('company for update-'+JSON.stringify(company));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
  
  
    getServiceById(_id: string){
        return this.http.get(this.baseUrl+'/Companiesnew/service/'+_id, new CustomRequestOptions())
            .map(res => res.json());      
    }

    verifyServiceContactInfo(service: Service){
        var key = service.id; 
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(service);
        let url = this.baseUrl+'/Companiesnew/service/'+key+'/verify-contact-info'; 
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
      }
  

}
