import { Injectable , EventEmitter} from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';


// Data structure imports
import { Job} from '../../../transaction/vo/job';
import { Es, EsUsageSummary} from '../../../transaction/vo/es';
import { EnergySaleMultiAddHeader} from '../../vo/energysale-multiadd';
import { Ea } from '../../vo/ea';
import { blockTransaction } from '../../vo/block-transaction';



@Injectable()
export class EaService {
    constructor(private http:Http){
    }


    esEvent: EventEmitter<Ea> = new EventEmitter();
    setCs(es: Ea) {
        this.esEvent.emit(es);
    }

    getEsById(_id: string){
        // var url = 'http://localhost:4200/assets/data/getEsByIdCreated.json';
        // return this.http.get(url,new CustomRequestOptions()).map(res => res.json());      

        return this.http.get(environment.serviceApiUrl+'/transaction/energyallotment/'+_id,new CustomRequestOptions()).map(res => res.json());      
    }

    // getAllEss(companyName:string,companyServiceNumber:string,edcId:string,companyId:string,isSTB:string,month:string,year:string,simple:string){
    //     var simple = simple == 'Y'? 'Y' : 'N';
    //     var url = environment.serviceApiUrl+'/transaction/energyallotment?simple-energy-sale='+simple;
    //     // var url = environment.assetsApiUrl='/assets/data/getalles.json';

    //     if(companyName != "" || companyServiceNumber != "" || edcId != "" || companyId != ""  || isSTB != "" || month != "" || year!="") {
    //         url = url + "&dummy=1";  
    //         if(companyName != "" && companyName != undefined){
    //             url = url + "&company-name="+companyName ;
    //         }
    //         if(companyServiceNumber != "" && companyServiceNumber != undefined){
    //             url = url + "&service-number="+companyServiceNumber ;
    //         } 

    //         if(edcId != "" && edcId != undefined ){
    //             url = url + "&edc-id="+edcId ;
    //         } 
    //         if(companyId != "" && companyId != undefined){
    //             url = url + "&company-id="+companyId ;
    //         }  
    //         if(isSTB != "" && isSTB != undefined){
    //             url = url + "&is-stb="+isSTB ;
    //         }
    //         if(month != "" && month != undefined){
    //             url = url + "&month="+month ;
    //         } 
    //         if(year != "" && year != undefined){
    //             url = url + "&year="+year ;
    //         }            

    //     }
    //    console.log(url)
    //     return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    // }

    getAllEss(sellerCompanyName:string,sellerCompanyServiceNumber:string,sellerEndOrgId:string,sellerCompanyId:string,sellerCompanyServiceId:string,isStb:string,month:string,year:string,simple:string, flowTypeCode:string){
        var simple = simple == 'Y'? 'Y' : 'N';
        var url = environment.serviceApiUrl+'/transaction/energyallotment?simple-energy-sale='+simple;
        // var url = environment.assetsApiUrl='/assets/data/getalles.json';

        if(sellerCompanyName != "" || sellerCompanyServiceNumber != "" || sellerEndOrgId != "" || sellerCompanyId!= "" || sellerCompanyServiceId != ""  || isStb != "" || month != "" || year!="" ) {
            url = url + "&dummy=1";  
            if(sellerCompanyName != "" && sellerCompanyName != undefined){
                url = url + "&companyname="+sellerCompanyName ;
            }
            if(sellerCompanyServiceNumber != "" && sellerCompanyServiceNumber != undefined){
                url = url + "&servicenumber="+sellerCompanyServiceNumber;
            } 

            if(sellerEndOrgId != "" && sellerEndOrgId != undefined ){
                url = url + "&edcid="+sellerEndOrgId ;
            } 
            if(sellerCompanyId != "" && sellerCompanyId != undefined){
                url = url + "&companyid="+sellerCompanyId ;
            }  
            if(sellerCompanyServiceId != "" && sellerCompanyServiceId != undefined){
                url = url + "&serviceid="+sellerCompanyServiceId ;
            } 
            if(isStb != "" && isStb != undefined){
                url = url + "&isstb="+isStb;
            }
            if(month != "" && month != undefined){
                url = url + "&month="+month ;
            } 
            if(year != "" && year != undefined){
                url = url + "&year="+year ;
            }       
            if(flowTypeCode != "" && flowTypeCode != undefined){
                url = url + "&flowTypeCode="+flowTypeCode ;
            }         

        }
       console.log(url)
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }

      

    getGsByAllocation(companyServiceId:string, companyServiceNumber:string,companyName:string,month:string,year:string,edcId:string){
         
        // var url = 'http://localhost:4200/assets/data/getallgs.json';

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

    
        test(companyServiceNumber:string,month:string,year:string){
        
            var url = environment.serviceApiUrl+'blocktransaction/test?allocated=N';
            if(companyServiceNumber != "" && companyServiceNumber != undefined){
                url = url + "&service-number="+companyServiceNumber ;
            } 

            if(month != "" && month != undefined){
                url = url + "&statement-month="+month ;
            } 
            if(year != "" && year != undefined){
                url = url + "&statement-year="+year ;
            } 
    
            console.log('url-'+url);
           
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
        }
    

    getGsById(_id: string){
        // var url='http://localhost:4200/assets/data/gsById.json';
        // return this.http.get(url,new CustomRequestOptions()).map(res => res.json());    
        return this.http.get(environment.serviceApiUrl+'/api/gs/generationstatement/'+_id,new CustomRequestOptions()).map(res => res.json());    
    }

    // Multi Add ---------------------------------------------------------------------------------------------

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


    // ---------------------------------------------------------------------------------------------------------------------


    triggerEnergySaleEvent(job:Job){
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(job);
        let url = environment.serviceApiUrl+'/transaction/job';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
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

     updateEa(es: Ea){
        //console.log("this.ea.allocations - "+ JSON.stringify(ea.allocations));
        console.log(es);
        es.fromDate=null
        es.toDate=null
        var key = es.id;
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(es);
       // let url = environment.serviceApiUrl+'/transaction/energysale/'+ key;
        let url = environment.serviceApiUrl+'/transaction/energyallotment/update/'+ key;

      return this.http.patch(url, body, new CustomRequestOptions()).map((res: Response) => res);
    
    }

    addNewEa(es: Ea){
        es = this.removeUnwantedTags(es);
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(es);
     //   let url = environment.serviceApiUrl+'/transaction/energysale';
        let url = environment.serviceApiUrl+'/transaction/energyallotment/createFromDb';
        console.log(es)
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
      }


      removeUnwantedTags(es: Ea){
        delete es.id;
        delete es.gs;
        return es;    
    }  

    getPowerplantByServiceId(serviceId: string) {
        return this.http.get(environment.serviceApiUrl + 'api/my-powerplants/' + serviceId,new CustomRequestOptions()).map(res => res.json());
    }
}