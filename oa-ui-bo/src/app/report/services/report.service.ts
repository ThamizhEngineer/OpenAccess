import { Injectable, Optional } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType} from '@angular/http';
import { Criteria } from './../vo/report';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import { GenericReportInput } from '../vo/GenericReportInput';
import { Observable } from 'rxjs/Rx'
import {CustomRequestOptions} from '../../shared/common/common.service';
import { Gs } from '../../transaction/vo/gs';

@Injectable()
export class ReportService {
    public criteria: Criteria = new Criteria();
    constructor(private http: Http) {

    }

    getMasterReport(genReportInput: GenericReportInput) {
        var url = environment.serviceApiUrl + '/report/generic-report';
            console.log(genReportInput);
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
            console.log(CRO)
            return this.http.get(url, CRO).map((res:Response) => res.json());
        }

        getWEG10SSlossReport(genReportInput: GenericReportInput) {
            var url = environment.serviceApiUrl + '/report/generic-report';
            console.log(genReportInput);
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
            console.log(CRO)
            return this.http.get(url, CRO).map((res:Response) => res.json());
          }
          
          gettantranscoGeneratorInfoReport(genReportInput: GenericReportInput) {
            var url = environment.serviceApiUrl + '/report/generic-report';
            console.log(genReportInput);
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
            console.log(CRO)
            return this.http.get(url, CRO).map((res:Response) => res.json());
          }

          gettantranscoPaymentInfoReport(genReportInput: GenericReportInput) {
            var url = environment.serviceApiUrl + '/report/generic-report';
            console.log(genReportInput);
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
            console.log(CRO)
            return this.http.get(url, CRO).map((res:Response) => res.json());
          }


          gettechnicalinfo(genReportInput: GenericReportInput){

            var url = environment.serviceApiUrl + '/report/generic-report';
            console.log(genReportInput);
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
            console.log(CRO)
            return this.http.get(url, CRO).map((res:Response) => res.json());
    
    
    
        }

    getAllTnerc(genReportInput: GenericReportInput){
        var url = environment.serviceApiUrl + '/report/generic-report';
        console.log(genReportInput);
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
        console.log(CRO)
        return this.http.get(url, CRO).map((res:Response) => res.json());
    }

    getWegProgressReport(genReportInput: GenericReportInput){
        if (genReportInput.ip1 !=undefined && genReportInput.ip2 !=undefined && genReportInput.ip3 !=undefined){
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
        setTimeout(function () {
            console.log("hide");
         // this.showElement = false;
       }, 3000);
        return this.http.get(url, CRO).map((res:Response) => res.json());
    }
}



    getAllRegions(){
        return this.http.get(environment.serviceApiUrl + '/master/orgs?type_code=REGION',new CustomRequestOptions()).map(res => res.json());
    }


    getOrgWiseGneration(month: string,year: string,flowTypeCode: string,dispFuelTypeGroup: string,isRec: string,orgId:string){
        var url=environment.reportUrl+'report/gen-summaries/processed-report/ORG-WISE-SUMMARY/_internal';
        if(month!="" || year !="" || flowTypeCode !="" || dispFuelTypeGroup !=""  || isRec!="" || orgId!="" ){
            url=url+"?dummy=1";

            if(month!=""){
                url=url+"&month="+month;
            }
            if(year!=""){
                url=url+"&year="+year;
            }
            if(flowTypeCode!=""){
                url=url+"&flowTypeCode="+flowTypeCode;
            }
            if(dispFuelTypeGroup!=""){
                url=url+"&dispFuelTypeGroup="+dispFuelTypeGroup;
            }
            if(isRec!=""){
                url=url+"&isRec="+isRec;
            }
            if(orgId!=""){
                url=url+"&orgId="+orgId;
            }
            console.log(url)
    }
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get(url, new CustomRequestOptions()).map((res:Response) => res.json());
}

printOrgWiseGneration(month: string,year: string,flowTypeCode: string,dispFuelTypeGroup: string,isRec: string,orgId:string){
    let options = {responseType: ResponseContentType.ArrayBuffer };

    var url=environment.reportUrl+'report/gen-summaries/print';
    if(month!="" || year !="" || flowTypeCode !="" || dispFuelTypeGroup !=""  || isRec!="" || orgId!="" ){
        url=url+"?dummy=1";

        if(month!=""){
            url=url+"&month="+month;
        }
        if(year!=""){
            url=url+"&year="+year;
        }
        if(flowTypeCode!=""){
            url=url+"&flowTypeCode="+flowTypeCode;
        }
        if(dispFuelTypeGroup!=""){
            url=url+"&dispFuelTypeGroup="+dispFuelTypeGroup;
        }
        if(isRec!=""){
            url=url+"&isRec="+isRec;
        }
        if(orgId!=""){
            url=url+"&orgId="+orgId;
        }
}
let headers = new Headers({ 'Content-Type': 'application/json' });
return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));

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
  
    getSectionByOrgId(selOrgName:string){
        let url =environment.serviceApiUrl+'master/sections?mOrgId='+selOrgName;
        console.log(url);
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());       
    }

    getSubstaion(Orgid:string){
        let url =environment.reportUrl+'report/substation-summaries?orgId='+Orgid;
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json()); 
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

    getUtilityReport(genReportInput: GenericReportInput){
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
    getDeleteTransactionReport(genReportInput: GenericReportInput){
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

    getEnergyAdjustedOrder(month:string,year:string,serviceNumber:string){
        let url =environment.reportUrl+'/report/energy-adjusted-order';
        if(month!="" || year!="" || serviceNumber!="") {
            url = url+ "?dummy=1";
            if(month != ""){
                url = url + "&readingMonth="+month ;
            }
            if(year != ""){
                url = url + "&readingYear="+year;
            }
            if(serviceNumber != ""){
                url = url + "&seller-service-number="+serviceNumber;
            } 
        }
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());
    }


    getEnergyChargeOrder(month:string,year:string,serviceNumber:string){
        let url =environment.reportUrl+'/report/eschargereport/get/_internal';


        if(month != "" || year != "" || serviceNumber != "" ) {
            url = url + "?dummy=1";  
            if(month != ""){
                url = url + "&month="+month ;
            }
            if(year != ""){
                url = url + "&year="+year;
            }
            if(serviceNumber != ""){
                url = url + "&serviceNumber="+serviceNumber;
            }          

        }
        console.log(url);
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());       
    }
    getAllCee(month:string,year:string,typeOfShare:string,fuelTypeCode:string){
        let url =environment.reportUrl+'report/ceereport/getall';


        if(month != "" ||year != "" || typeOfShare != "" || fuelTypeCode != "") {
            url = url + "?dummy=1";  
            
            if(month != ""){
                url = url + "&month="+month;
            }
            if(year != ""){
                url = url + "&year="+year;
            }
            if(typeOfShare != ""){
                url = url + "&typeOfShare="+typeOfShare;
            }   
            if(fuelTypeCode != ""){    
                url = url + "&fuelTypeCode="+fuelTypeCode;
            }   

        }
        console.log(url);
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());       
    }

    // getAllAllocations(serviceNo:string,month:string,year:string){
    //     let url =environment.reportUrl+'energyallocationreport/getall';


    //     if(serviceNo != "" || month != "" ||year != "" ) {
    //         url = url + "?dummy=1";  
            
    //         if(month != ""){
    //             url = url + "&month="+month;
    //         }
    //         if(year != ""){
    //             url = url + "&year="+year;
    //         } 

    //     }
    //     console.log(url);
    //     return this.http.get(url,new CustomRequestOptions()).map(res => res.json());       
    // }

    getAllsrcp(month:string,year:string){
        let url =environment.reportUrl+'/report/srcpreport/getall';


        if(month != ""||year != "") {
            url = url + "?dummy=1";  
            
            if(month != ""){
                url = url + "&month="+month;
            }
            if(year != ""){
                url = url + "&year="+year;
            }
         
        }
        console.log(url);
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());       
    }


    getAllprogress(month:string,year:string){
        let url =environment.reportUrl+'report/progress-report';


        if(month != ""||year != "") {
            url = url + "?dummy=1";  
            
            if(month != ""){
                url = url + "&month="+month;
            }
            if(year != ""){
                url = url + "&year="+year;
            }
         
        }
        console.log(url);
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());       
    }

    getAllUnutilBanking(edcNo:string,month:string,year:string){
        let url =environment.reportUrl+'report/financial-unutil-report';


        if(month != ""||year != "") {
            url = url + "?dummy=1";  
            if(edcNo != ""){
                url = url + "&edcNo="+edcNo;
            }
            if(month != ""){
                url = url + "&month="+month;
            }
            if(year != ""){
                url = url + "&year="+year;
            }
         
        }
        console.log(url);
        return this.http.get(url,new CustomRequestOptions()).map(res => res.json());       
    }


    printProgressReport(month:string,year:string){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.reportUrl+'report/progress-report/print';
        if(month != "" || year != "" ) {
            url = url + "?dummy=1";  
            if(month != ""){
                url = url + "&month="+month ;
            }
            if(year != ""){
                url = url + "&year="+year;
            }       

        }
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    }


    printUnutilBankingReport(edcNo:string,month:string,year:string){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.reportUrl+'report/financial-unutil-report/print';
        if(month != "" || year != "" ) {
            url = url + "?dummy=1";  
            if(edcNo != ""){
                url = url + "&edcNo="+edcNo ;
            }
            if(month != ""){
                url = url + "&month="+month ;
            }
            if(year != ""){
                url = url + "&year="+year;
            }       

        }
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    }


    printEnergyAuditReport(searchMonth:string,searchYear:string,searchSellerServiceNumber:string){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.documentUrl+'/api/print/EnergyAuditReport/1';
        if(searchMonth != "" || searchYear != "" || searchSellerServiceNumber != "" ){
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

    // printEnergyAuditReportGenUnits(gsId){
    //     let options = {responseType: ResponseContentType.ArrayBuffer };
    //     let url =environment.documentUrl+'/api/print/EnergyAuditReportGenUnits/'+gsId;
    //     return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    // }

    printEnergyAuditReportGenUnits(gsList:Gs){
        // let options = {responseType: ResponseContentType.ArrayBuffer };
        let body = JSON.stringify(gsList);
        let url =environment.documentUrl+'/api/print/EnergyAuditReportGenUnits/';
        // return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
        // return this.http.post(url, body,{responseType:ResponseContentType.ArrayBuffer}).catch((err: Response) => Observable.throw(err.json()));
        }


    printEnergyAdjustedOrder(searchMonth:string,searchYear:string,searchSellerServiceNumber:string){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        // let url =environment.reportUrl+'report/energy-adjusted-order/print';
        let url =environment.documentUrl+'/api/print/EnergyAdjustedOrderReport/1';
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

    printCeeReport(searchmonth:string,searchYear:string,searchtypeOfShare:string,searchfuelType:string){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.reportUrl+'report/ceereport/print';
        if(searchmonth != "" || searchYear != "" || searchtypeOfShare != "" || searchfuelType != "" ) {
            url = url + "?dummy=1";  
            
            if(searchmonth != ""){
                url = url + "&month="+searchmonth;
            }
            if(searchYear != ""){
                url = url + "&year="+searchYear;
            }
            if(searchtypeOfShare != ""){
                url = url + "&typeOfShare="+searchtypeOfShare;
            }   
            if(searchfuelType != ""){
                url = url +"&fuelTypeCode="+searchfuelType;
            }   

        }
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    }

    printSrcpReport(searchncesDivisionCode:string,searchmonth:string,searchYear:string){
        let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.reportUrl+'report/srcpreport/print';
        if(searchncesDivisionCode != "" || searchmonth != "" ||searchYear != "" ) {
            url = url + "?dummy=1";  
            if(searchncesDivisionCode != ""){
                url = url + "&ncesDivisionCode="+searchncesDivisionCode;
            }
            if(searchmonth != ""){
                url = url + "&month="+searchmonth;
            }    
            if(searchYear != ""){
                url = url + "&year="+searchYear;
            }      

        }
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    }


    // printEnergyAllocationReport(searchServiceNo:string,searchmonth:string,searchYear:string){
    //     let options = {responseType: ResponseContentType.ArrayBuffer };
    //     let url =environment.reportUrl+'energyallocationreport/print';
    //     if(searchServiceNo != "" || searchmonth != "" ||searchYear != "" ) {
    //         url = url + "?dummy=1";  
    //         if(searchServiceNo != ""){
    //             url = url + "&serviceNo="+searchServiceNo;
    //         }
    //         if(searchmonth != ""){
    //             url = url + "&month="+searchmonth;
    //         }    
    //         if(searchYear != ""){
    //             url = url + "&year="+searchYear;
    //         }      

    //     }
    //     return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    // }


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

    printGenWiseGeneration(ip1: string, ip2: string, ip3: String, ip4:String, ip5:String, ip6:String, ip7:String){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/GeneratorWiseGenerationReport';
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" || ip3 !="" || ip4 !="" || ip5 !="" || ip6 !="" || ip7 !="") {
            if (ip1 != "") {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "") {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "") {
                url = url + "&ip3=" + ip3;
            }
            if (ip4 != "") {
                url = url + "&ip4=" + ip4;
            }
            if (ip5 != "") {
                url = url + "&ip5=" + ip5;
            }
            if (ip6 != "") {
                url = url + "&ip6=" + ip6;
            }
            if (ip7 != "") {
                url = url + "&ip7=" + ip7;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    printConsumerWiseEnergyAdjustmentOrderReport(ip1: string, ip2: string, ip3: String, ip4:String, ip5:String, ip6:String, fileType:String){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/ConsumerWiseEnergyAdjustmentOrderReport';
        url = url + "?dummy=1"+"&fileType="+fileType;  
        if (ip1 != "" || ip2 != "" || ip3 !="" || ip4 !="" || ip5 !="" || ip6 !="" ) {
            if (ip1 != "" && ip1 != null) { 
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "" && ip2 != null) {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "" && ip3 != null) {
                url = url + "&ip3=" + ip3;
            }
            if (ip4 != "" && ip4 != null) {
                url = url + "&ip4=" + ip4;
            }
            if (ip5 != "" && ip5 != null) {
                url = url + "&ip5=" + ip5;
            }
            if (ip6 != "" && ip6 != null) {
                url = url + "&ip6=" + ip6;
            }
            
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    printUnutilisedEnergyReport(ip1: string, ip2: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/UnutilisedBankingReport';
        url = url + "?dummy=1";  
        if (ip1 != "" || ip2 != "") {
            if (ip1 != "") {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "") {
                url = url + "&ip2=" + ip2;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    printConsEnergyReport(ip1: string, ip2: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/ConsolidateEnergyAdjustmentReport';
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "") {
            if (ip1 != "") {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "") {
                url = url + "&ip2=" + ip2;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    printConsEnergyCharge(ip1: string, ip2: string,ip3: string, ip4: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/ConsolidateEnergyAdjustedChargesReport';
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
            if (ip4 != "") {
                url = url + "&ip4=" + ip4;
            }
        }   console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    printStbLedger(ip1: string, ip2: string,ip3: string, ip4: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/SaleToBoardLedgerReport';
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" || ip3 != "" || ip4 != "") {
            if (ip1 != "") {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "") {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "") {
                url = url + "&ip3=" + ip3;
            }
            if (ip4 != "") {
                url = url + "&ip4=" + ip4;
            }
        }   console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    printGenWiseAllocToHt(ip1: string, ip2: string,ip3: string, ip4: string, ip5: string, ip6: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/GenChargesAllocToHtReport';
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" || ip3 != "" || ip4 != "" || ip5 != "" || ip6 != "") {
            if (ip1 != "" && ip1 != undefined) {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "" && ip2 != undefined) {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "" && ip3 != undefined) {
                url = url + "&ip3=" + ip3;
            }
            if (ip4 != "" && ip4 != undefined) {
                url = url + "&ip4=" + ip4;
            }
            if (ip5 != "" && ip5 != undefined) {
                url = url + "&ip5=" + ip5;
            }
            if (ip6 != "" && ip6 != undefined) {
                url = url + "&ip6=" + ip6;
            }
        }   console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    getAmrdownlode(genReportInput: GenericReportInput ){
        var url = environment.serviceApiUrl + '/report/generic-report';
        console.log(genReportInput);
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
        console.log(CRO)
        return this.http.get(url, CRO).map((res:Response) => res.json());


    }

    unutilisedBankingMarNew(ip1: string, ip2: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/UnutilisedBankingMarNew';
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" ) {
            if (ip1 != "" && ip1 != undefined) {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "" && ip2 != undefined) {
                url = url + "&ip2=" + ip2;
            }
        }   console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    printProgress(ip1: string, ip2: string, ip3: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/ProgressReportNew';
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" ) {
            if (ip1 != "" && ip1 != undefined) {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "" && ip2 != undefined) {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "" && ip3 != undefined) {
                url = url + "&ip3=" + ip3;
            }
        }   console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    UnutilisedBankingMar2020(ip1: string, ip2: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/UnutilisedBankingMar2020';
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" ) {
            if (ip1 != "" && ip1 != undefined) {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "" && ip2 != undefined) {
                url = url + "&ip2=" + ip2;
            }
        }   console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    printTnercEnergySummary(ip1: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/TnercEnergySummaryReport';
        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        url = url + "?dummy=1";  

        if (ip1 != "") {
            if (ip1 != "") {
                url = url + "&ip1=" + ip1;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });
          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }

    printUnimportedReport(ip1: string, ip2: string, ip3: string,ip4: string,ip5: string,ip6: string){
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
            if (ip4 != "") {
                url = url + "&ip4=" + ip4;
            }
            if (ip5 != "") {
                url = url + "&ip5=" + ip5;
            }
            if (ip6 != "") {
                url = url + "&ip6=" + ip6;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));

        // return this.http.get(url, options).map((res: Response) => res.json());

    }
    
    nilGensmtsReport1(ip3: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/nilGenerationReport';
        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        url = url + "?dummy=1";  

        if (ip3 != "" ) {
            if (ip3 != "") {
                url = url + "&ip3=" + ip3;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });
          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));

        // return this.http.get(url, options).map((res: Response) => res.json());

    }

    nilGensmtsReport(ip1: string, ip2: string, ip3: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/nilGenerationReport';
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
    
    UnallocatedGenReport(ip1: string, ip2: string, ip3: string){
      
        
    var token = sessionStorage.getItem('token');
    let headers = new Headers();
    headers.append('Authorization', token);
    headers.append('Response-Type', 'ArrayBuffer' );

        // let options = [{responseType: ResponseContentType.ArrayBuffer },{'Authorization': sessionStorage.getItem('token')}];
        let options = {responseType: ResponseContentType.ArrayBuffer };

        let url =environment.serviceApiUrl+'/report/print-report/unallocatedGenReport';
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
        }   
        console.log('url-'+url);
        
          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));

        // return this.http.get(url, options).map((res: Response) => res.json());

    }

    printAmrReport(ip1: string, ip2: string, ip3: string,ip4: string,ip5: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/AMR DOWNLODE STATUS REPORT';
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
            if (ip4 != "") {
                url = url + "&ip4=" + ip4;
            }
            if (ip5 != "") {
                url = url + "&ip5=" + ip5;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }
    
    TechnicalinfoReport(ip1: string, ip2: string, ip3: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/TECHNICAL INFO REPORT';
        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" || ip3 != "") {
            if (ip1 != "" && ip1 !="undefined") {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "" && ip2 !="undefined") {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "" && ip3 !="undefined") {
                url = url + "&ip3=" + ip3;
            }
            
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }
        
    getMeterDownloadCalc(genReportInput: GenericReportInput ){
        var url = environment.serviceApiUrl + '/report/generic-report';
        console.log(genReportInput);
        let params = {
            
            name: genReportInput.reportName,
            ip1: genReportInput.ip1,
			ip2: genReportInput.ip2,
			ip3: genReportInput.ip3,
            ip4: genReportInput.ip4,
		};
		let CRO = new CustomRequestOptions();
		CRO.setParams(params);
        console.log(CRO)
        return this.http.get(url, CRO).map((res:Response) => res.json());
    }

    printtnercreport092022(genReportInput: GenericReportInput ){
        var url = environment.serviceApiUrl + '/report/generic-report';
        console.log(genReportInput);
        let params = {
            
            name: genReportInput.reportName,
            ip1: genReportInput.ip1,
            ip2: genReportInput.ip2,
			
		};
		let CRO = new CustomRequestOptions();
		CRO.setParams(params);
        console.log(CRO)
        return this.http.get(url, CRO).map((res:Response) => res.json());


    }
    
    printtnercbanking092022Report(ip1: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/TNERC-BANKING-REPORT09-2022';
        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        url = url + "?dummy=1";  

        
            if (ip1 != "") {
                url = url + "&ip1=" + ip1;
            }
               console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }
    getSolarFeederwise(genReportInput: GenericReportInput ){
        var url = environment.serviceApiUrl + '/report/generic-report';
        console.log(genReportInput);
        let params = {
            
            name: genReportInput.reportName,
            ip1: genReportInput.ip1,
			ip2: genReportInput.ip2,
			ip3: genReportInput.ip3,
            ip4: genReportInput.ip4,
		};
		let CRO = new CustomRequestOptions();
		CRO.setParams(params);
        console.log(CRO)
        return this.http.get(url, CRO).map((res:Response) => res.json());
    }

    solarfeeederPrint(ip1: string, ip2: string, ip3: string, ip4: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/SOLAR FEEDER EDC WISE REPORT';
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" || ip3 != "" || ip4 != "") {
            if (ip1 != "" && ip1 !=undefined) {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "" && ip2 !=undefined) {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "" && ip3 !=undefined) {
                url = url + "&ip3=" + ip3;
            }
            if (ip4 != "" && ip4 !=undefined) {
                url = url + "&ip4=" + ip4;
            }
            
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }
    printMeterStatusReport(ip1: string, ip2: string, ip3: string,ip4: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/METER READING SERVICE STATUS REPORT';
        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" || ip3 != "" || ip4 != "") {
            if (ip1 != "" && ip1 ) {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "") {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "") {
                url = url + "&ip3=" + ip3;
            }
            if (ip4 != ""&& ip4 !=undefined) {
                url = url + "&ip4=" + ip4;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }
    getMonthlyProgressService(genReportInput: GenericReportInput ){
        var url = environment.serviceApiUrl + '/report/generic-report';
        console.log(genReportInput);
        let params = {
            
            name: genReportInput.reportName,
            ip1: genReportInput.ip1,
			ip2: genReportInput.ip2,
			ip3: genReportInput.ip3,
            ip4: genReportInput.ip4,
            ip5: genReportInput.ip5,
            ip6: genReportInput.ip6,
            ip7: genReportInput.ip7,
		};
		let CRO = new CustomRequestOptions();
		CRO.setParams(params);
        console.log(CRO)
        return this.http.get(url, CRO).map((res:Response) => res.json());
    }

    printReportTotalService(ip1: string, ip2: string, ip3: string,ip4: string,ip5: string,ip6: string,ip7: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/METER READING SERVICE DETAIL REPORT';
        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" || ip3 != "" || ip4 != "" || ip5 != "" || ip6 != "" || ip7 !="") {
            if (ip1 != "" && ip1 !="undefined") {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "" && ip2 !="undefined") {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "" && ip3 !="undefined") {
                url = url + "&ip3=" + ip3;
            }
            if (ip4 != "" && ip4 !="undefined") {
                url = url + "&ip4=" + ip4;
            }
            if (ip5 != "" && ip5 !="undefined") {
                url = url + "&ip5=" + ip5;
            }
            if (ip6 != "" && ip6 !="undefined") {
                url = url + "&ip6=" + ip6;
            }
            if (ip7 != "" && ip7 !="undefined") {
                url = url + "&ip7=" + ip7;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }
    getFiscalYear(genReportInput: GenericReportInput ){
        var url = environment.serviceApiUrl + '/report/generic-report';
        console.log(genReportInput);
        let params = {
           name: genReportInput.reportName,
        ip1: genReportInput.ip1, 
        ip2: genReportInput.ip2,
        ip3: genReportInput.ip3,
        ip4: genReportInput.ip4,
        ip5: genReportInput.ip5,
        ip6: genReportInput.ip6,
        ip7: genReportInput.ip7,
    };
    let CRO = new CustomRequestOptions();
    CRO.setParams(params);
        console.log(CRO)
        return this.http.get(url, CRO).map((res:Response) => res.json());
    }  
    printfiscal(ip1: String, ip2: String, ip3: String,ip4: String ,ip5: String,ip6: String,ip7: String){

        let options = {responseType: ResponseContentType.ArrayBuffer };   
          let url =environment.serviceApiUrl+'/report/print-report/MASTER SOLAR REPORT';   
          url = url + "?dummy=1";   
          if (ip1 != "" || ip2 != ""|| ip3 != "" || ip4 != "" || ip5 != "" || ip6 != "" || ip7 != "") {   
            if (ip1 != "" && ip1 !=undefined) {   
                url = url + "&ip1=" + ip1;    
            }   
            if (ip2 != "" && ip2 !=undefined) {   
                url = url + "&ip2=" + ip2;  
            }
            if (ip3 != "" && ip3 !=undefined) {   
                url = url + "&ip3=" + ip3;  
            }
            if (ip4 != "" && ip4 !=undefined) {   
                url = url + "&ip4=" + ip4;  
            }
            if (ip5 != "" && ip5 !=undefined) {   
                url = url + "&ip5=" + ip5;  
            }
            if (ip6 != "" && ip6 !=undefined) {   
                url = url + "&ip6=" + ip6;  
            }
            if (ip7 != "" && ip7 !=undefined) {   
                url = url + "&ip7=" + ip7;  
            }
        }     console.log(options);
            console.log('url-'+url); 
          let headers = new Headers({ 'Content-Type': 'application/json' });
            return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
      }
      printMasterWind(ip1: String, ip2: String, ip3: String,ip4: String ,ip5: String,ip6: String,ip7: String){

        let options = {responseType: ResponseContentType.ArrayBuffer };   
          let url =environment.serviceApiUrl+'/report/print-report/MASTER WIND REPORT';   
          url = url + "?dummy=1";   
          if (ip1 != "" || ip2 != "" || ip3 != "" || ip4 != "" || ip5 != "" || ip6 != "" || ip7 !="") {   
            if (ip1 != "" && ip1 !=undefined) {   
                url = url + "&ip1=" + ip1;    
            }   
            if (ip2 != "" && ip2 !=undefined) {   
                url = url + "&ip2=" + ip2;  
            }
            if (ip3 != "" && ip3 !=undefined) {   
                url = url + "&ip3=" + ip3;  
            }
            if (ip4 != "" && ip4 !=undefined) {   
                url = url + "&ip4=" + ip4;  
            }
            if (ip5 != "" && ip5 !=undefined) {   
                url = url + "&ip5=" + ip5;  
            }
            if (ip6 != "" && ip6 !=undefined) {   
                url = url + "&ip6=" + ip6;  
            }
            if (ip7 != "" && ip7 !=undefined) {   
                url = url + "&ip7=" + ip7;  
            }
        }     console.log(options);
            console.log('url-'+url); 
          let headers = new Headers({ 'Content-Type': 'application/json' });
            return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
      }

      getGenerationstmt(genReportInput: GenericReportInput ){
        var url = environment.serviceApiUrl + '/report/generic-report';
        console.log(genReportInput);
        let params = {
            
            name: genReportInput.reportName,
            ip1: genReportInput.ip1,
			ip2: genReportInput.ip2,
			ip3: genReportInput.ip3,
            ip4: genReportInput.ip4,
		};
		let CRO = new CustomRequestOptions();
		CRO.setParams(params);
        console.log(CRO)
        return this.http.get(url, CRO).map((res:Response) => res.json());
    }

    printGenerationstmtReport(ip1: string, ip2: string, ip3: string,ip4: string){
    	let options = {responseType: ResponseContentType.ArrayBuffer };
        let url =environment.serviceApiUrl+'/report/print-report/GENERATION REPORT';
        //   return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
        url = url + "?dummy=1";  

        if (ip1 != "" || ip2 != "" || ip3 != "" || ip4 != "") {
            if (ip1 != "" && ip1 ) {
                url = url + "&ip1=" + ip1;
            }
            if (ip2 != "") {
                url = url + "&ip2=" + ip2;
            }
            if (ip3 != "") {
                url = url + "&ip3=" + ip3;
            }
            if (ip4 != ""&& ip4 !=undefined) {
                url = url + "&ip4=" + ip4;
            }
        }    console.log(options);
        console.log('url-'+url);
        let headers = new Headers({ 'Content-Type': 'application/json' });

          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
    }
}
