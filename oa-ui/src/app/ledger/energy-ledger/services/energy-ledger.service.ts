import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { EnergyLedger } from './../../vo/energy-ledger';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { CommonUtils } from '../../../shared/common/common-utils';
import { DatePipe } from '@angular/common';
import { Observable } from 'rxjs/Observable';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class EnergyLedgerService {
    constructor(private http: Http,
        private commonUtils: CommonUtils) {
        console.log('Energy Ledger Service initialised');
    }
    removeUnwantedTags(energyLedger: EnergyLedger) {
        delete energyLedger.id;

        return energyLedger;
    }
    getEnergyLedgerById(id: string) {
        return this.http.get(environment.serviceApiUrl + '/ledger/energyledger/' + id,new CustomRequestOptions()).map(res => res.json());
    }

    getEnergyLedgers(orgId: string, companyName: string, companyServiceNumber: string,ledgerDate:string, month: string, year: string) {
        //return this.http.get(environment.serviceApiUrl+'/master/traderelationships').map(res => res.json());  
        console.log("In trade service")
        var url = environment.serviceApiUrl + '/ledger/energyledgers';
        if (orgId != "" || companyName != "" || companyServiceNumber != "" || ledgerDate!=""|| month != "" || year != "") {
            url = url + "?dummy=1";
            if (orgId != "") {
                url = url + "&org-id=" + orgId;
            }
            if (companyName != "") {
                url = url + "&company-name=" + companyName;
            }
            if (companyServiceNumber != "") {
                url = url + "&company-service-number=" + companyServiceNumber;
            }
            if(ledgerDate!="" ){
                url = url + "&ledger-date="+ledgerDate;
            }
            if (month != "") {
                url = url + "&month=" + month;
            }
            if (year != "") {
                url = url + "&year=" + year;
            }


        }
        // let userToken =this.commonUtils.getUserTokens();
        // //console.log(userToken);
        //  console.log(userToken.userToken);
        // let headers = new Headers({ 'Content-Type': 'application/json'});
        // headers.append('token',userToken.userToken);
        // let options = new RequestOptions({ headers: headers });

        // console.log(options);
        console.log('url-' + url);

        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }

    getCompanies() {
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03',new CustomRequestOptions()).map(res => res.json());

    }

    getServices() {
        return this.http.get(environment.serviceApiUrl + '/master/companies',new CustomRequestOptions()).map(res => res.json());

    }


    // addTradeRelationship(tradeRelationship: TradeRelationship){ 
    //     tradeRelationship=this.removeUnwantedTags(tradeRelationship);
    //   console.log("in add tradeRelationship");
    //   console.log(tradeRelationship);
    //   let headers = new Headers({ 'Content-Type': 'application/json'});
    //   let options = new RequestOptions({ headers: headers });
    //   let body = JSON.stringify(tradeRelationship);
    //   let url = environment.serviceApiUrl+'/master/traderelationship';
    //   return this.http.post(url, body, options).map((res: Response) => res.json());
    // }

    // updateTradeRelationship(tradeRelationship: TradeRelationship){
    //     console.log("in update service");
    //   var key = tradeRelationship.id;
    //   tradeRelationship=this.removeUnwantedTags(tradeRelationship);
    //   console.log("In update TradeRelationship"+key);
    //   let headers = new Headers({ 'Content-Type': 'application/json'});
    //   let options = new RequestOptions({ headers: headers });
    //   let body = JSON.stringify(tradeRelationship);
    //   let url = environment.serviceApiUrl+'/master/traderelationship/'+ key;
    //   console.log('TradeRelationship for update-'+JSON.stringify(tradeRelationship));
    //   return this.http.put(url, body, options).map((res: Response) => res.json());
    // }

}
