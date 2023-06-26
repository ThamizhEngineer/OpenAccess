import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Company } from './../../vo/company';
import {  Service } from './../../vo/company.v1';

import 'rxjs/add/operator/map';
//import {PlatformLocation } from '@angular/common';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class CompanyService {
    baseUrl: string = environment.serviceApiUrl+'/master';
	
    constructor(private http:Http){
    //, platformLocation: PlatformLocation


        
    }
    getCompanyById(_id: string){
        return this.http.get(this.baseUrl+'/company/'+_id, new CustomRequestOptions())
            .map(res => res.json());      
    }
  
    getCompanies(){
		return this.http.get(this.baseUrl+'/companies',new CustomRequestOptions())
            .map(res => res.json());      
    }
  
    removeUnwantedTags(company: Company){
        delete company._id;
        for (var index = 0; index < company.addresses.length; index++) {
                delete company.addresses[index]['$$index'];   
        }
        
        for (var index = 0; index < company.contacts.length; index++) {
                delete company.contacts[index]['$$index'];        
        }

        for (var index = 0; index < company.employees.length; index++) {
                delete company.employees[index]['$$index'];        
        }

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
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
    updateCompany(company: Company){
      var key = company._id;
      company = this.removeUnwantedTags(company);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(company);
      let url = this.baseUrl+'/company/'+key;
      console.log('url-'+url);
      console.log('company for update-'+JSON.stringify(company));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
  
    getGeneratorServicesByCompanyId(companyId: string){
        let companies = [];
        let services=[];
        const req = new XMLHttpRequest();
        req.open('GET', this.baseUrl+'/companies');
        req.onload = () => {
            companies = JSON.parse(req.responseText); 
                companies.forEach(company => {
                    if(company.services){
                        company.services.forEach(service => {
                            if(service.type == "Generator"){
                                if(companyId.length>0){
                                    if(company.companyId == companyId){
                                        services.push(service); 
                                    }
                                }
                                else
                                    services.push(service); 

                            }
                        });
                    }
                }); 
            return services;   
        };
        req.send();
    }

    //returns all consumer services
    getAllConsumerServices(){
        return this.http.get(this.baseUrl+'/companies/consumer-services',new CustomRequestOptions())
                    .map(res => res.json());  
    }

    //Returns all companies that have consumer services
    getAllConsumerNames(){
        return this.http.get(this.baseUrl+'/companies/consumer-names',new CustomRequestOptions())
                    .map(res => res.json());  
    }

    //Returns all companies that have generator services
    getAllGeneratorNames(){
        return this.http.get(this.baseUrl+'/companies/generator-names',new CustomRequestOptions())
                    .map(res => res.json());  
    }

    //Returns all companies summaries
    getAllCompanySummaries(){
        return this.http.get(this.baseUrl+'/companies/company-summaries',new CustomRequestOptions())
                    .map(res => res.json());  
    }


    getServiceContactInfoById(_id: string){
        console.log(new CustomRequestOptions())
        return this.http.get(this.baseUrl+'/company/service/'+_id+'/contact-info', new CustomRequestOptions())
            .map(res => res.json());     
    }

    verifyServiceContactInfo(service: Service){
        var key = service.id; 
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(service);
        let url = this.baseUrl+'/company/service/'+key+'/verify-contact-info'; 
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
      }

      getBank(){
        return this.http.get(environment.serviceApiUrl + '/master/bank/FindAll',new CustomRequestOptions()).map(res => res.json());
    }

}
