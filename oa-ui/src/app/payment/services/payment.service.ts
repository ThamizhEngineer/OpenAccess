import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Payment } from './payment';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import {CustomRequestOptions} from '../../shared/common/common.service';

@Injectable()
export class PaymentService {
    baseUrl: string = environment.serviceApiUrl + '/payment';
    constructor(private http: Http) {
    }

    getPaymentById(_id: string) {
        return this.http.get(this.baseUrl + '/payment/' + _id,new CustomRequestOptions()).map(res => res.json());
    }

    getPayments() {
        return this.http.get(this.baseUrl + '/payments',new CustomRequestOptions()).map(res => res.json());
    }

    removeUnwantedTags(payment: Payment) {
        delete payment._id;
        return payment;
    }

    addPayment(payment: Payment) {
        payment = this.removeUnwantedTags(payment);
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });
        const body = JSON.stringify(payment);
        const url = this.baseUrl + '/payment';
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    updatePayment(payment: Payment) {
        const key = payment._id;
        payment = this.removeUnwantedTags(payment);
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });
        const body = JSON.stringify(payment);
        const url = this.baseUrl + '/payment/' + key;
        return this.http.put(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
    }

}
