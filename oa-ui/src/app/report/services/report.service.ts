import { Injectable, Optional } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType} from '@angular/http';
import { Criteria } from './../vo/report';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import { GenericReportInput } from '../vo/GenericReportInput';
import { saveAs } from 'file-saver';
import { Observable } from 'rxjs/Rx'
import {CustomRequestOptions} from '../../shared/common/common.service';

@Injectable()
export class ReportService {
    public criteria: Criteria = new Criteria();
    constructor(private http: Http) {

    }
    getAllRegions(){
        return this.http.get(environment.serviceApiUrl + '/master/orgs?type_code=REGION',new CustomRequestOptions()).map(res => res.json());
    }
    fetchGeneratorBasedConsumerUsage(voltageCode: string,parentOrgCode:string,isCaptive: string) {
        var url = environment.serviceApiUrl + '/report/gen-consumer-usage';
        if (voltageCode!= "" || parentOrgCode != "" || isCaptive != "") {
            url = url + "?dummy=1";

            if (voltageCode != "") {
                url = url + "&voltage-code=" + voltageCode;
            }
            if (parentOrgCode != "") {
                url = url + "&parent-org-code=" + parentOrgCode;
            }
            if (isCaptive != "") {
                url = url + "&is-captive=" + isCaptive;
            }
        }
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());
    }
   
printEnergyChargeOrder(searchMonth:string,searchYear:string,searchSellerServiceNumber:string){
    let options = {responseType: ResponseContentType.ArrayBuffer };
    let url =environment.reportUrl+'report/eschargereport/print';
    if(searchMonth != "" || searchYear != "" || searchSellerServiceNumber != "" ) {
        url = url + "?dummy=1";  
        if(searchSellerServiceNumber != ""){
            url = url + "&suplrCode="+searchSellerServiceNumber ;
        }
        if(searchMonth != ""){
            url = url + "&readingMnth="+searchMonth;
        }
        if(searchYear != ""){
            url = url + "&readingYr="+searchYear;
        }          

    }
    return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
}


    getWegUnitsReportByOrg(month: string, year: string) {
        var url = environment.serviceApiUrl + '/report/org-quantum';
        if (month != "" || year != "") {
            url = url + "?fuel-type-code=02";

            if (month != "") {
                url = url + "&month=" + month;
            }
            if (year != "") {
                url = url + "&year=" + year;
            }
        }
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    getWegUnitsReportByService(orgId: string, month: string, year: string) {
        var url = environment.serviceApiUrl + '/report/service-quantum';
        if (month != "" || year != "" || orgId != "") {
            url = url + "?fuel-type-code=02";
            if (orgId != "") {
                url = url + "&org-id=" + orgId;
            }
            if (month != "") {
                url = url + "&month=" + month;
            }
            if (year != "") {
                url = url + "&year=" + year;
            }
        }
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());
    }
    printEnergyAdjustedOrder(searchMonth:string,searchYear:string,searchSellerServiceNumber:string){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        // let url =environment.reportUrl+'report/energy-adjusted-order/print';
        let url =environment.docApiUrl+'/api/print/EnergyAdjustedOrderReport/1';
        if(searchMonth != "" || searchYear != "" || searchSellerServiceNumber != "" ) {
            url = url + "?dummy=1";  
            if(searchMonth != ""){
                url = url + "&readingMonth="+searchMonth ;
            }
            if(searchYear != ""){
                url = url + "&readingYear="+searchYear;
            }
            if(searchSellerServiceNumber != ""){
                url = url + "&seller-service-number="+searchSellerServiceNumber;
            }          

        }
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    }


    getCaptiveWheelingReportByOrg(month: string, year: string) {      
        var url = environment.serviceApiUrl + '/report/org-quantum';
        if (month != "" || year != "") {
            url = url + "?is-captive=Y";

            if (month != "") {
                url = url + "&month=" + month;
            }
            if (year != "") {
                url = url + "&year=" + year;
            }
        }
        console.log(url)

        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());
    }

    getCaptiveWheelingReportByService(orgId: string, month: string, year: string) {
        var url = environment.serviceApiUrl + '/report/service-quantum';
        if (month != "" || year != "" || orgId != "") {
            url = url + "?is-captive=Y";
            if (orgId != "") {
                url = url + "&org-id=" + orgId;
            }
            if (month != "") {
                url = url + "&month=" + month;
            }
            if (year != "") {
                url = url + "&year=" + year;
            }
        }
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(url, new CustomRequestOptions()).map((res: Response) => res.json());
    }



    getGenericReport(genReportInput: GenericReportInput){
        var url = environment.serviceApiUrl + '/report/generic-report';
        // return this.http.get(url, {params: {
        //     name: genReportInput.reportName,
        //     ip1: genReportInput.ip1,
        //     ip2: genReportInput.ip2,
        //     ip3: genReportInput.ip3,
        //     ip4: genReportInput.ip4,
        //     ip5: genReportInput.ip5,
        //     ip6: genReportInput.ip6,
        //     ip7: genReportInput.ip7,
        //     ip8: genReportInput.ip8,
        //     ip9: genReportInput.ip9,
        //     ip10: genReportInput.ip10
        // }}).map((res:Response) => res.json());

        let params = {
			name: genReportInput.reportName,
			ip1: genReportInput.ip1,
			ip2: genReportInput.ip2,
			ip3: genReportInput.ip3,
			ip4: genReportInput.ip4,
			ip5: genReportInput.ip5,
			ip6: genReportInput.ip6,
			ip7: genReportInput.ip7,
			ip8: genReportInput.ip8,
			ip9: genReportInput.ip9,
			ip10: genReportInput.ip10
		};
		let CRO = new CustomRequestOptions();
		CRO.setParams(params);
        return this.http.get(url, CRO).map((res:Response) => res.json());
        
    }


    getUnimportedWegServicesReport(genReportInput: GenericReportInput){
        var url = environment.serviceApiUrl + '/report/generic-report';
		
		
		let params = {
			name: genReportInput.reportName,
			ip1: genReportInput.ip1,
			ip2: genReportInput.ip2,
			ip3: genReportInput.ip3,
			ip4: genReportInput.ip4,
			ip5: genReportInput.ip5,
			ip6: genReportInput.ip6,
			ip7: genReportInput.ip7,
			ip8: genReportInput.ip8,
			ip9: genReportInput.ip9,
			ip10: genReportInput.ip10
		};
		let CRO = new CustomRequestOptions();
		CRO.setParams(params);
        return this.http.get(url, CRO).map((res:Response) => res.json());
        
    }
    getWegWithBuyer(orgId:string,capacity:string,makeCode:string){
        let url =environment.reportUrl+'report/weg-with-consumers';


        if(orgId != "" || capacity != "" || makeCode != "" ) {
            url = url + "?dummy=1";  
            if(orgId != ""){
                url = url + "&orgId="+orgId ;
            }
            if(capacity != ""){
                url = url + "&capacity="+capacity;
            }
            if(makeCode != ""){
                url = url + "&makeCode="+makeCode;
            }          

        }
        console.log(url);
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());       
    }

    printReport(orgId:string,capacity:string,makeCode:string){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'master/powerplant/print-report/WegWithBuyerReport';
        if(orgId != "" || capacity != "" || makeCode != "" ) {
            url = url + "?dummy=1";  
            if(orgId != ""){
                url = url + "&orgId="+orgId ;
            }
            if(capacity != ""){
                url = url + "&capacity="+capacity;
            }
            if(makeCode != ""){
                url = url + "&makeCode="+makeCode;
            }          

        }
        console.log(url);

        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        // let headers = new Headers({ 'Content-Type': 'application/json' });

        return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    }  

    printEnergyAllotmentReport(){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/EnergyAllotmentReport';
          return this.http.get(url, new CustomRequestOptions()).catch((err: Response) => Observable.throw(err.json()));
    }  
    printUnimportedReport(ip1: string, ip2: string, ip3: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/UnimportedWegReport';
        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" || ip3 != "") {
            if (ip1 != "") {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "") {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "") {
                url = url + "&ip3=" + ip3;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));

        // return this.http.get(url, options).map((res: Response) => res.json());

    }  

    
}
