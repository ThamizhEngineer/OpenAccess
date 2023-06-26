import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType} from '@angular/http'; 

import { EsOrder } from './esorder';
import 'rxjs/add/operator/map';
import { environment } from '../../../../../environments/environment';
import { CommonUtils } from '../../../../shared/common/common-utils';
import {CustomRequestOptions} from '../../../../shared/common/common.service';
import { Observable } from 'rxjs/Rx'



@Injectable()
export class EsorderService {
    constructor(private http: Http,
        private commonUtils: CommonUtils) {
        console.log('Energy Sale order initialised');
    }
    getEso() {
        return this.http.get(environment.serviceApiUrl + '/ledger/energysaleorders',new CustomRequestOptions()).map(res => res.json());
    }
    getEsoById(_id: string) {
        return this.http.get(environment.serviceApiUrl + '/ledger/energysaleorder/' + _id,new CustomRequestOptions()).map(res => res.json());
    }


    getAllEso(companyName: string, companyServiceNumber: string, orgId: string, month: string, year: string, fuelTypeCode: string, fuelTypeName: string) {

        var url = environment.serviceApiUrl + '/ledger/energysaleorders';
        if (companyName != "" || companyServiceNumber != "" || orgId != "" || month != "" || year != "" || fuelTypeCode !="" || fuelTypeName !="") {
            url = url + "?dummy=1";
            if (companyName != "") {
                url = url + "&company-name=" + companyName;
            }
            if (orgId != "") {
                url = url + "&org-id=" + orgId;
            }
            if (companyServiceNumber != "") {
                url = url + "&company-service-number=" + companyServiceNumber;
            }
            if (month != "") {
                url = url + "&month=" + month;
            }
            if (year != "") {
                url = url + "&year=" + year;
            }
            if (fuelTypeCode != "") {
                url = url + "&fuelTypeCode=" + fuelTypeCode;
            }
            if (fuelTypeName != "") {
                url = url + "&fuelTypeName=" + fuelTypeName;
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

    getAllEsoBuyers(orgId: string, companyServiceNumber: string, month: string, year: string, fuelTypeCode: string) {

        var url = environment.serviceApiUrl + '/ledger/energysaleordersbuyers';
        if (orgId != "" || companyServiceNumber != "" || month != "" || year != "" || fuelTypeCode !="") {
            url = url + "?dummy=1";
            if (orgId != "") {
                url = url + "&org-id=" + orgId;
            }
            if (companyServiceNumber != "") {
                url = url + "&company-service-number=" + companyServiceNumber;
            }
            if (month != "") {
                url = url + "&month=" + month;
            }
            if (year != "") {
                url = url + "&year=" + year;
            }
            if (fuelTypeCode != "") {
                url = url + "&fuelTypeCode=" + fuelTypeCode;
            }
        }
        console.log('url-' + url);

        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }


    printEsOrder(esOrder:EsOrder){
        
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        // let url =environment.serviceApiUrl+'/ledger/energysaleorder/'+esOrder.id+'/print';
        let url =environment.documentUrl+'/api/print/EnergyAllotmentOrderReport/'+esOrder.id;
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    } 
}
