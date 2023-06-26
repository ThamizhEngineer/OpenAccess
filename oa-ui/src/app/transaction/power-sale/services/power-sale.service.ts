import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { EsIntent } from '../../../transaction/vo/es-intent';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';


@Injectable()
export class PowerSaleService {
    constructor(private http: Http) {
        console.log('Power sale Service initialised');
    }


    getEsIntentById(_id: string) {
        return this.http.get(environment.serviceApiUrl + '/transaction/energysaleintent/' + _id,new CustomRequestOptions()).map(res => res.json());
    }
    getSellers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=03&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
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

    sldcApprovalEsIntent(esIntent: EsIntent) {
        //console.log("this.ea.allocations - "+ JSON.stringify(ea.allocations));
        var key = esIntent.id;
        esIntent = this.removeUnwantedTags(esIntent);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(esIntent);
        let url = environment.serviceApiUrl + '/transaction/energysaleintent/' + key + '/SLDC-APPROVED';
        console.log(key);
        console.log('Energy Sale Intent SLDC Approval-' + JSON.stringify(esIntent));
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    sldcRejectEsIntent(esIntent: EsIntent) {
        //console.log("this.ea.allocations - "+ JSON.stringify(ea.allocations));
        var key = esIntent.id;
        esIntent = this.removeUnwantedTags(esIntent);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(esIntent);
        let url = environment.serviceApiUrl + '/transaction/energysaleintent/' + key + '/SLDC-REJECTED';
        console.log(key);
        console.log('Energy Sale Intent SLDC Approval-' + JSON.stringify(esIntent));
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    getBuyers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=02&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }

    getEsIntents() {
        return this.http.get(environment.serviceApiUrl + '/transaction/energysaleintents?flow-type-code=CAPTIVE',new CustomRequestOptions()).map(res => res.json());
    }
}
