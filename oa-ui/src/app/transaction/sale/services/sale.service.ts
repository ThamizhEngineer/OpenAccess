import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CustomRequestOptions } from '../../../shared/common/common.service';

@Injectable()
export class SaleService {

    reportUrl: string;
    assetUrl: string;
	constructor(private http: Http) { 
		this.reportUrl = environment.reportUrl;
		this.assetUrl = environment.assetsApiUrl;
	}

	getUser(){
		return JSON.parse(sessionStorage.getItem('user'));		
	}
	
	getReports(companyId) {
		let c = companyId? companyId: '';
		return this.http.get(this.reportUrl + 'report/pp-summaries?companyId=' + c, new CustomRequestOptions()).map(res => res.json());
    }
	
	getSummaryCount(type: string, date: any,genCompServId:String){
		console.log(genCompServId)
		return this.http.get(this.reportUrl + 'application/summary-counts/'+ type +'?month='+ date.month+'&year='+ date.year+'&genCompServId='+genCompServId, new CustomRequestOptions()).map(res => res.json());
	}
	
	getConfig(){
		return this.http.get(this.assetUrl + '/assets/data/sale-config.json', new CustomRequestOptions()).map(res => res.json());
	}
	
	getSummaryContent(type: string, date: any, saleTypeCode: any, statusCode: any,genCompServId:String, pageNumber?: any, pageSize?: any){
		console.log("type"+type+"saleTypeCode"+saleTypeCode+"statusCode"+statusCode+"----"+date.month+"----"+date.year)
		return this.http.get(this.reportUrl + 'application/summaries/'+ type +'?saleTypeCode='+ saleTypeCode +'&statusCode='+ statusCode+'&genCompServId='+genCompServId+'&page='+ pageNumber+'&size='+pageSize, new CustomRequestOptions()).map(res => res.json());
	}
}
