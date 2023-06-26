import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType} from '@angular/http'; 
import { Powerplant } from './../../vo/powerplant';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';
import { Observable } from 'rxjs/Rx'
import { TradeRelationship } from '../../vo/trade-relationship';

@Injectable()
export class PowerplantService {
    constructor(private http: Http) { }

    getPowerplantById(id: string) {
        return this.http.get(environment.serviceApiUrl + 'master/powerplantnew/' + id,new CustomRequestOptions()).map(res => res.json());
    }

    // getPowerplantBySearchCriteria(orgId?: string, fuelCode?: string, code?: string,serviceId?:string) {
    //     var url = environment.serviceApiUrl + 'master/powerplantnew'+'?serviceId='+serviceId;
    //     if (orgId != "" || fuelCode != "" || code != "") {
    //         // url = url + '?dummy=1';

    //         if (fuelCode != "") {
    //             url = url + '&fuelTypeCode=' + fuelCode;
    //         }
    //         if (orgId != "") {
    //             url = url + '&orgId=' + orgId;
    //         }
    //         if (code != "") {
    //             url = url + '&code=' + code;
    //         }
    //     }
    //     let headers = new Headers({
    //         'Content-Type': 'application/json'
    //     });
    //     let options = new RequestOptions({
    //         headers: headers
    //     });

    //     return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());
    // }

    getPowerplantBySearchCriteria(orgId?: string,meterNumber?: string,sellerServiceNumber?:string) {
        var url = environment.reportUrl + 'report/pp-summaries';
        if (orgId != "" || meterNumber != "" || sellerServiceNumber != "") {
            url = url + '?dummy=1';

            if (orgId != "") {
                url = url + '&orgId=' + orgId;
            }
            if (meterNumber != "" && meterNumber !=undefined) {
                url = url + '&meterNumber=' + meterNumber;
            }
            if (sellerServiceNumber != "" && sellerServiceNumber !=undefined) {
                url = url + '&sellerServiceNumber=' + sellerServiceNumber;
            }
        }
        let headers = new Headers({
            'Content-Type': 'application/json'
        });
        let options = new RequestOptions({
            headers: headers
        });
        console.log(url)
        return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());
    }


    // getPowerplantById(id: string) {
    //     return this.http.get(environment.serviceApiUrl + 'master/powerplant/' + id,new CustomRequestOptions()).map(res => res.json());
    // }

    // getPowerplantBySearchCriteria(orgId?: string, fuelCode?: string, code?: string) {
    //     var url = environment.serviceApiUrl + 'master/powerplants';
    //     if (orgId != "" || fuelCode != "" || code != "") {
    //         url = url + '?dummy=1';

    //         if (fuelCode != "") {
    //             url = url + '&fuel-code=' + fuelCode;
    //         }
    //         if (orgId != "") {
    //             url = url + '&org-id=' + orgId;
    //         }
    //         if (code != "") {
    //             url = url + '&code=' + code;
    //         }
    //     }
    //     let headers = new Headers({
    //         'Content-Type': 'application/json'
    //     });
    //     let options = new RequestOptions({
    //         headers: headers
    //     });

    //     return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());
    // }

    addPowerplant(powerplant: Powerplant) {
        let headers = new Headers({
            'Content-Type': 'application/json'
        });
        let options = new RequestOptions({
            headers: headers
        });
        let body = JSON.stringify(powerplant);
        let url = environment.serviceApiUrl + 'master/powerplant';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    // updatePowerplant(powerplant: Powerplant) {
    //     var key = powerplant.id;
    //     let headers = new Headers({
    //         'Content-Type': 'application/json'
    //     });
    //     let options = new RequestOptions({
    //         headers: headers
    //     });
    //     let body = JSON.stringify(powerplant);
    //     let url = environment.serviceApiUrl + 'master/powerplant' + key;

    //     return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    // }

    updatePowerplant(powerplant: Powerplant) {
        var key = powerplant.id;
        let headers = new Headers({
            'Content-Type': 'application/json'
        });
        let options = new RequestOptions({
            headers: headers
        });
        let body = JSON.stringify(powerplant);
        let url = environment.serviceApiUrl + 'master/powerplantnew/updatepp/' + key;

        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

    printPPGenMaster(powerplant:Powerplant){
        
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/api/my-powerplants/'+powerplant.id+'/print';
          return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    } 
}