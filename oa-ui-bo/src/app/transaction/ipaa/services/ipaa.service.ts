import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import {Ipaa } from '../../../transaction/vo/ipaa';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';
import { IpaStandingClearance } from '../../vo/ipa-sc';


@Injectable()
export class IpaaService {
    constructor(private http: Http) {
        console.log('Energy Sale Intent Service initialised');
    }

    getIpaaById(_id: string) {
        return this.http.get(environment.serviceApiUrl + '/transaction/ipaa/' + _id,new CustomRequestOptions()).map(res => res.json());
    }
   
    getIpaaCompanyByEdc(sellerEndOrgId: string) {

        var url = environment.serviceApiUrl + '/transaction/ipaas';
        if (sellerEndOrgId != "") {
            url = url + "?dummy=1";

            if (sellerEndOrgId != "") {
                url = url + "&seller-edc-id=" + sellerEndOrgId;
            }
        }
        return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());

    }
    getBuyers(edcId: string) {
        console.log(edcId)
        console.log("In service edc")
        return this.http.get(environment.serviceApiUrl + '/master/companyservices?type=02&edc-id=' + edcId,new CustomRequestOptions()).map(res => res.json());
    }

    getAllIpaas(sellerEdcId: string, sellerCompanyServiceId: string, status: string, esIntentCode: string,code:string) {

        var url = environment.serviceApiUrl + '/transaction/ipaas';
        if (sellerEdcId != "" || sellerCompanyServiceId != "" || status != "" || esIntentCode != ""|| code != "") {
            url = url + "?dummy=1";
            if (sellerEdcId != "") {
                url = url + "&seller-edc-id=" + sellerEdcId;
            }
            if (sellerCompanyServiceId != "") {
                url = url + "&seller-company-service-id=" + sellerCompanyServiceId;
            }
            if (status != "") {
                url = url + "&status=" + status;
            }
            if (code != "") {
                url = url + "&code=" + code;
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


    removeUnwantedTags(ipaa: Ipaa) {
        delete ipaa.id;


        return ipaa;
    }

    addIpaa(ipaa: Ipaa) {
        ipaa = this.removeUnwantedTags(ipaa);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(ipaa);
        let url = environment.serviceApiUrl + '/transaction/ipaa';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    addIpaSc(ipaStandingClearance:IpaStandingClearance) {
        // ipaStandingClearance = this.removeUnwantedTags(ipaStandingClearance);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(ipaStandingClearance);
        let url = environment.serviceApiUrl + '/transaction/ipaa/applysc';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    updateIpaa(ipaa: Ipaa) {
        //console.log("this.ea.allocations - "+ JSON.stringify(ea.allocations));
        var key = ipaa.id;
        ipaa = this.removeUnwantedTags(ipaa);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(ipaa);
        let url = environment.serviceApiUrl + '/transaction/ipaa/' + key;
        console.log('Ipaa for update-' + JSON.stringify(ipaa));
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }
    completeIpaa(ipaa: Ipaa) {
        var key =  ipaa.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(ipaa);
        let url = environment.serviceApiUrl+'/transaction/ipaa/' + key + '/complete';
        console.log('update ipaa-' + JSON.stringify(ipaa));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }

            approveIpaa(ipaa: Ipaa) {
        var key =  ipaa.id;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(ipaa);
        let url = environment.serviceApiUrl+'/transaction/ipaa/' + key + '/approve';
        console.log('update ipaa-' + JSON.stringify(ipaa));
        console.log(url);
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);

    }


    findExistigStandingClearance(ipaScId: string){
        return this.http.get(environment.serviceApiUrl + '/transaction/ipaa-sc/ipaid/' + ipaScId,new CustomRequestOptions()).map(res => res.json());

    }


}
