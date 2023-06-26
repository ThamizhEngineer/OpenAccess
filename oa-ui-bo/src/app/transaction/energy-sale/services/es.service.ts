import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Es, EsUsageSummary} from '../../../transaction/vo/es';
import { Job} from '../../../transaction/vo/job';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';
import { EnergySaleMultiAddHeader} from '../../vo/energysale-multiadd';

@Injectable()
export class EsService {
    constructor(private http:Http){
    }

    getEsById(_id: string){
        return this.http.get(environment.serviceApiUrl+'/transaction/energysale/'+_id,new CustomRequestOptions()).map(res => res.json());      
    }
  
    getEss(){
        return this.http.get(environment.serviceApiUrl+'/transaction/energysales',new CustomRequestOptions()).map(res => res.json());      
    }
    
    getAllEss(companyName:string,companyServiceNumber:string,edcId:string,companyId:string,IsSTB:string,fuelTypeCode:string,fuelTypeName:string,month:string,year:string,simple:string, flowTypeCode:string){
        var simple = simple == 'Y'? 'Y' : 'N';
        var url = environment.serviceApiUrl+'/transaction/energysales?simple-energy-sale='+simple;
        if(companyName != "" || companyServiceNumber != "" || edcId != "" || companyId != "" || IsSTB != ""  || month != "" || year!="" || fuelTypeCode!="" || fuelTypeName!="") {
            url = url + "&dummy=1";  
            if(companyName != "" && companyName != undefined){
                url = url + "&company-name="+companyName ;
            }
            if(companyServiceNumber != "" && companyServiceNumber != undefined){
                url = url + "&service-number="+companyServiceNumber ;
            } 

            if(edcId != "" && edcId != undefined ){
                url = url + "&edc-id="+edcId ;
            } 
            if(companyId != "" && companyId != undefined){
                url = url + "&company-id="+companyId ;
            }  
            if(IsSTB != "" && IsSTB != undefined){
                url = url + "&is-stb="+IsSTB ;
            } 
            if(month != "" && month != undefined){
                url = url + "&month="+month ;
            } 
            if(year != "" && year != undefined){
                url = url + "&year="+year ;
            }    
            if(fuelTypeCode != "" && fuelTypeCode != undefined){
                url = url + "&fuelTypeCode="+fuelTypeCode ;
            } 
            if(fuelTypeName != "" && fuelTypeName != undefined){
                url = url + "&fuelTypeName="+fuelTypeName ;
            }    
            if(flowTypeCode != "" && flowTypeCode != undefined){
                url = url + "&flowTypeCode="+flowTypeCode ;
            }    
            
            

        }
       console.log(url)
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }
        
    getGsByAllocation(companyServiceId:string, companyServiceNumber:string,companyName:string,month:string,year:string,edcId:string){
         
        var url = environment.serviceApiUrl+'/transaction/generationstatements?allocated=N';
        if(companyServiceId != "" ||companyServiceNumber != "" ||companyName != "" || month != "" || year!=""|| edcId!="") {
            
            
            if(companyServiceId != "" && companyServiceId != undefined){
                url = url + "&service-id="+companyServiceId ;
            } 
            if(companyServiceNumber != "" && companyServiceNumber != undefined){
                url = url + "&service-number="+companyServiceNumber ;
            } 

            if(companyName != "" && companyName != undefined){
                url = url + "&company-name="+companyName ;
            }
            if(month != "" && month != undefined){
                url = url + "&statement-month="+month ;
            } 
            if(year != "" && year != undefined){
                url = url + "&statement-year="+year ;
            } 
            if(edcId != "" && edcId != undefined){
                url = url + "&edc-id="+edcId ;
            }             

        }
       
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }

    removeUnwantedTags(es: Es){
        delete es.id;
        delete es.gs;
        // for (var index = 0; index < es.allocations.length; index++) {
        //         delete ea.allocations[index]['$$index'];   
        //         // for (var index1 = 0; index1 < ea.allocations[index].slots.length; index++) {
        //         //   delete ea.allocations[index].slots[index1]['$$index']; 
        //         //   if (ea.allocations[index].charges[index1]){
        //         //       delete ea.allocations[index].charges[index1]['$$index']; 
        //         //   }
        //         // }
        // }
        return es;    
    }

    addEs(es: Es){
      es = this.removeUnwantedTags(es);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(es);
      let url = environment.serviceApiUrl+'/transaction/energysale';
      return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
    }
  

    triggerEnergySaleEvent(job:Job){
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(job);
        let url = environment.serviceApiUrl+'/transaction/job';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
      }

    updateEs(es: Es){
        //console.log("this.ea.allocations - "+ JSON.stringify(ea.allocations));
      var key = es.id;
    //   es = this.removeUnwantedTags(es);
     
    
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(es);
      let url = environment.serviceApiUrl+'/transaction/energysale/'+ key;
      
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    
    }
    deleteEsUsageSummary(esUsageSummary: EsUsageSummary){
        console.log("service")

        console.log(esUsageSummary)

        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(esUsageSummary);
        let url = environment.serviceApiUrl+'/transaction/esusage/delete';
        
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
      
      }
    deleteEsCharge(esUsageSummary: EsUsageSummary){

    
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(esUsageSummary);
      let url = environment.serviceApiUrl+'/transaction/escharge/delete';
      
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    
    }

    finalEs(es: Es){
        //console.log("this.ea.allocations - "+ JSON.stringify(ea.allocations));
      var key = es.id;
    //   es = this.removeUnwantedTags(es);
      let headers = new Headers({ 'Content-Type': 'application/json'});
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(es);
      let url = environment.serviceApiUrl+'/transaction/energysale/'+ key+'/final';
      console.log('Energy Sale for final-'+JSON.stringify(es));
      return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    multiAddEs(es:Es){
        var key = es.id;
        // es = this.removeUnwantedTags(es);
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(es);
        let url = environment.serviceApiUrl+'/transaction/energysale/'+key+'/multiadd';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());

    }

    addMultiAddEs(es: EnergySaleMultiAddHeader){
        // es = this.removeUnwantedTags(es);
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(es);
        let url = environment.serviceApiUrl+'/transaction/energysale-multiadd';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
      }

      validateMultiAddEs(es: EnergySaleMultiAddHeader){
        // es = this.removeUnwantedTags(es);
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(es);
        let url = environment.serviceApiUrl+'/transaction/energysale-multiadd/validate-multiadd';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
      }
      getEsMultiAddById(_id: string){
        return this.http.get(environment.serviceApiUrl+'/transaction/energysale-multiadd/'+_id,new CustomRequestOptions()).map(res => res.json());      
    }

    deleEsMultiAdd(_id,es: EnergySaleMultiAddHeader ){
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(es);
        return this.http.patch(environment.serviceApiUrl+'/transaction/energysale-multiadd/delete-multiadd/'+_id,body,new CustomRequestOptions()).map(res => res);      
    }

    importMultiAddEs(_id,es: EnergySaleMultiAddHeader){
       
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(es);
        let url = environment.serviceApiUrl+'/transaction/energysale-multiadd/import-multiadd/'+_id;
        return this.http.patch(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }
  
}
