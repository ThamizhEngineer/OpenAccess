import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { environment } from "../../../../../environments/environment";
import { CustomRequestOptions } from "../../../../shared/common/common.service";

@Injectable()
export class meterChangeService {

    constructor(private http: Http) {
    }

    getServiceDetails(serviceNo: string) {
        return this.http.get(environment.serviceApiUrl + '/transaction/meterChange/fetchService?serviceNo=' + serviceNo,
            new CustomRequestOptions()).map(res => res.json());
    }

    saveMeterReadings(readings: any) {
        console.log(readings)
        return this.http.post(environment.serviceApiUrl + '/transaction/meterChange/saveReadings', readings,
            new CustomRequestOptions()).map(res => res.json);
    }

}