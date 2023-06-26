import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { EsIntent } from '../../../transaction/vo/es-intent';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class EsIntentService {
    constructor(private http: Http) {
        console.log('Energy Sale Intent Service initialised');
    }

    getEsIntentById(_id: string) {
        return this.http.get(environment.serviceApiUrl + '/transaction/energysaleintent/' + _id,new CustomRequestOptions()).map(res => res.json());
    }

    getEsIntents() {
        return this.http.get(environment.serviceApiUrl + '/transaction/energysaleintents',new CustomRequestOptions()).map(res => res.json());
    }

    getSellers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }

    getBuyers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=02&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }
    getEsIntentCompanyByEdc(sellerEndOrgId: string, sellerCompanyId: string) {

        var url = environment.serviceApiUrl + '/transaction/energysaleintents';
        if (sellerEndOrgId != "") {
            url = url + "?dummy=1";

            if (sellerEndOrgId != "") {
                url = url + "&seller-edc-id=" + sellerEndOrgId;
            }

            if(sellerCompanyId != ""){
                url = url + "&seller-company-id="+sellerCompanyId ;
            }
        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }
    getEsIntentSearch(sellerEdcId: string, sellerCompanyServiceId: string,sellerCompanyId: string, status: string, esIntentCode: string) {

        var url = environment.serviceApiUrl + '/transaction/energysaleintents';
        if (sellerEdcId != "" || sellerCompanyServiceId != "" || sellerCompanyId != "" || status != "" || esIntentCode != "") {
            url = url + "?dummy=1";
            if (sellerEdcId != "") {
                url = url + "&seller-edc-id=" + sellerEdcId;
            }
            if (sellerCompanyServiceId != "") {
                url = url + "&seller-company-service-id=" + sellerCompanyServiceId;
            }
            if (sellerCompanyId !="") {
                url = url + "&seller-company-id=" + sellerCompanyId;
                }
            if (status != "") {
                url = url + "&status=" + status;
            }
            if (esIntentCode != "") {
                url = url + "&es-intent-code=" + esIntentCode;
            }



        }
        // let userToken =this.commonUtils2.getUserTokens();
        // //console.log(userToken);
        //  console.log(userToken.userToken);
        // let headers = new Headers({ 'Content-Type': 'application/json'});
        // headers.append('token',userToken.userToken);
        // let options = new RequestOptions({ headers: headers });

        // console.log(options);
        console.log('url-' + url);

        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
    }


    removeUnwantedTags(esIntent: EsIntent) {
        delete esIntent.id;


        return esIntent;
    }

    addEsIntent(esIntent: EsIntent) {
        esIntent = this.removeUnwantedTags(esIntent);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(esIntent);
        let url = environment.serviceApiUrl + '/transaction/energysaleintent';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    updateEsIntent(esIntent: EsIntent) {
        //console.log("this.ea.allocations - "+ JSON.stringify(ea.allocations));
        var key = esIntent.id;
        esIntent = this.removeUnwantedTags(esIntent);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(esIntent);
        let url = environment.serviceApiUrl + '/transaction/energysaleintent/' + key;
        console.log('Energy Sale Intent for update-' + JSON.stringify(esIntent));
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    processEsIntent(esIntent: EsIntent) {
        //console.log("this.ea.allocations - "+ JSON.stringify(ea.allocations));
        var key = esIntent.id;
        esIntent = this.removeUnwantedTags(esIntent);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(esIntent);
        let url = environment.serviceApiUrl + '/transaction/energysaleintent/' + key + '/process';
        console.log('Energy Sale Intent for process-' + JSON.stringify(esIntent));
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }



}
