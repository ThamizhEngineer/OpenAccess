import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { TradeRelationship } from './../../vo/trade-relationship';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { CommonUtils } from '../../../shared/common/common-utils';
import {CustomRequestOptions} from '../../../shared/common/common.service';

@Injectable()
export class TradeRelationshipService {
    constructor(private http: Http,
        private commonUtils: CommonUtils) {
    }
    removeUnwantedTags(tradeRelationship: TradeRelationship) {
        delete tradeRelationship.id;

        return tradeRelationship;
    }
    getTradeRelationshipById(id: string) {
        return this.http.get(environment.serviceApiUrl + '/master/traderelationship/' + id,new CustomRequestOptions()).map(res => res.json());
    }


    //returns all Buyer services based on Org Id
    getBuyerCompanyServicesByOrgId(orgId: string) {
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=02&edc-id='+orgId,new CustomRequestOptions()).map(res => res.json());

    }

            //returns all seller services based on Org Id
    getSellerCompanyServicesByOrgId(orgId: string) {
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&edc-id='+orgId,new CustomRequestOptions()).map(res => res.json());

    }

            //returns all Seller services
    getSellerCompanyServices() {
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03',new CustomRequestOptions()).map(res => res.json());

    }

    getTradeRelationships(companyCode: string, companyName: string, sellerCompanyServiceNumber: string, sellerCompanyServiceId: string, buyerCompanyServiceNumber: string, buyerCompanyServiceId: string, month: string, year: string, quantum:string, buyerCompanyCode:string) {
        //return this.http.get(environment.serviceApiUrl+'/master/traderelationships').map(res => res.json());  
        var url = environment.serviceApiUrl + '/master/traderelationships';
        if (companyCode != "" || companyName != "" || sellerCompanyServiceNumber != "" || sellerCompanyServiceId != "" || buyerCompanyServiceNumber != "" || buyerCompanyServiceId != "" || month != "" || year != "" || quantum != "" || buyerCompanyCode != "") {
            url = url + "?dummy=1";
            if (companyCode != "") {
                url = url + "&seller-company-code=" + companyCode;
            }
            if (companyName != "") {
                url = url + "&seller-company-name=" + companyName;
            }
            if (sellerCompanyServiceNumber != "") {
                url = url + "&seller-company-service-number=" + sellerCompanyServiceNumber;
            }
            if (sellerCompanyServiceId != "") {
                url = url + "&seller-company-service-id=" + sellerCompanyServiceId;
            }
            if (buyerCompanyServiceNumber != "") {
                url = url + "&buyer-company-service-number=" + buyerCompanyServiceNumber;
            }
            if (buyerCompanyServiceId != "") {
                url = url + "&buyer-company-service-id=" + buyerCompanyServiceId;
            }
            if (month != "") {
                url = url + "&month=" + month;
            }
            if (year != "") {
                url = url + "&year=" + year;
            }
            if (quantum != "") {
                url = url + "&quantum=" + quantum;
            }
            if (buyerCompanyCode != "") {
                url = url + "&buyerCompanyCode=" + buyerCompanyCode;
            }



        }

        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });

        return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());

    }

    getCompanies() {
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03',new CustomRequestOptions()).map(res => res.json());

    }

    getServices() {
        return this.http.get(environment.serviceApiUrl + '/master/companies',new CustomRequestOptions()).map(res => res.json());

    }

    getCompanyByEdc(buyerOrgId: string, sellerOrgId: string) {

        var url = environment.serviceApiUrl + '/master/traderelationships';
        if (buyerOrgId != "" || sellerOrgId != "") {
            url = url + "?dummy=1";
            if (buyerOrgId != "") {
                url = url + "&buyer-edc=" + buyerOrgId;
            }
            if (sellerOrgId != "") {
                url = url + "&seller-edc=" + sellerOrgId;
            }
        }
        console.log(url);
        let headers = this.commonUtils.getHeaders();
        let options = new RequestOptions({ headers: headers });

        return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());

    }


    addTradeRelationship(tradeRelationship: TradeRelationship) {
        tradeRelationship = this.removeUnwantedTags(tradeRelationship);
        console.log("in add tradeRelationship");
        console.log(tradeRelationship);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(tradeRelationship);
        let url = environment.serviceApiUrl + '/master/traderelationship';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    updateTradeRelationship(tradeRelationship: TradeRelationship) {
        console.log("in update service");
        var key = tradeRelationship.id;
        tradeRelationship = this.removeUnwantedTags(tradeRelationship);
        console.log("In update TradeRelationship" + key);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(tradeRelationship);
        let url = environment.serviceApiUrl + '/master/traderelationship/' + key;
        console.log('TradeRelationship for update-' + JSON.stringify(tradeRelationship));
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    deleteTradeRelationship(tradeRelationship: TradeRelationship) {
        
		var key = tradeRelationship.id;
        tradeRelationship = this.removeUnwantedTags(tradeRelationship);
        
        let url = environment.serviceApiUrl + '/master/traderelationship/' + key;
        
		return this.http.delete(url, new CustomRequestOptions()).map((res: Response) => res);
    }
 
}
