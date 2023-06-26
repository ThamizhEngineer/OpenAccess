import { Injectable, EventEmitter } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Gc } from './gc';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import {CustomRequestOptions} from '../../shared/common/common.service';

@Injectable()
export class GcService {
    gcEvent: EventEmitter<Gc> = new EventEmitter();
    constructor(private http: Http) {
    }

    setGc(gc: Gc) {
        this.gcEvent.emit(gc);

    }
    getGcById(_id: string) {
        return this.http.get(environment.serviceApiUrl + '/transaction/gridconnectivity/' + _id,new CustomRequestOptions()).map(res => res.json());
    }

    getGcs() {
        return this.http.get(environment.serviceApiUrl + '/transaction/gridconnectivities',new CustomRequestOptions()).map(res => res.json());
    }


    removeUnwantedTags(gc: Gc) {
        if (gc.genUnits != null) {
            for (var index = 0; index < gc.genUnits.length; index++) {
                delete gc.genUnits[index]['$$index'];
            }
        }
        if (gc.transformers != null) {
            for (var index = 0; index < gc.transformers.length; index++) {
                delete gc.transformers[index]['$$index'];
            }
        }

        if (gc.idLoans != null) {
            for (var index = 0; index < gc.idLoans.length; index++) {
                delete gc.idLoans[index]['$$index'];


            }
        }
        if (gc.idCaptiveUserContributions != null) {

            for (var index = 0; index < gc.idCaptiveUserContributions.length; index++) {
                delete gc.idCaptiveUserContributions[index]['$$index'];
            }
        }

        if (gc.idEquityShareVotingRights != null) {

            for (var index = 0; index < gc.idEquityShareVotingRights.length; index++) {
                delete gc.idEquityShareVotingRights[index]['$$index'];
            }
        }

        if (gc.applicationStatus != null) {

            for (var index = 0; index < gc.applicationStatus.length; index++) {
                delete gc.applicationStatus[index]['$$index'];
            }
        }
        if (gc.captiveQuantumAllocation != null) {

            for (var index = 0; index < gc.captiveQuantumAllocation.length; index++) {
                delete gc.captiveQuantumAllocation[index]['$$index'];
            }
        }
        if (gc.checkList != null) {
            for (var index = 0; index < gc.checkList.length; index++) {
                delete gc.checkList[index]['$$index'];
            }
        }
        return gc;
    }

    addGc(gc: Gc) {
        gc = this.removeUnwantedTags(gc);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(gc);
        let url = environment.serviceApiUrl + '/transaction/gridconnectivity';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
    }

    updateGc(gc: Gc) {
        var key = gc.id;
        gc = this.removeUnwantedTags(gc);
        console.log("update gc");
        console.log(gc);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(gc);
        let url = environment.serviceApiUrl + '/transaction/gridconnectivity/' + key;
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.text());
    }

    completeGc(gc: Gc) {
        var key = gc.id;

        console.log("complete gc");
        console.log(gc);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(gc);
        let url = environment.serviceApiUrl + '/transaction/gridconnectivity/' + key + '/complete';
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res);
    }

}
