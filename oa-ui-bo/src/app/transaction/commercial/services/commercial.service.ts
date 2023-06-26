import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { environment } from "../../../../environments/environment";
import { CustomRequestOptions } from "../../../shared/common/common.service";

@Injectable()
export class commercialService {

    constructor(private http: Http) {
    }

    getByServiceNumber(serviceNo:any) {
        return this.http.get(environment.serviceApiUrl+'/transaction/edc-commercials/fetchSeller?serviceNo='+serviceNo, 
                                          new CustomRequestOptions()).map(res => res.json());
    }

    getBuyerDetails(serviceNo:any) {
        return this.http.get(environment.serviceApiUrl+'/transaction/edc-commercials/fetchBuyer?serviceNo='+serviceNo, 
                                          new CustomRequestOptions()).map(res => res.json());
    }

    saveConsumers(data:any) {
        return this.http.post(environment.serviceApiUrl+'/transaction/edc-commercials/saveCommercials',data,new CustomRequestOptions()).map(res => res.json);
    }

}