import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { environment } from "../../../../environments/environment"
import { CustomRequestOptions } from "../../../shared/common/common.service";


@Injectable()
export class FeederLineLossService{

    constructor(private http:Http) {   
    }

    getSsById(id: string){
        return this.http.get(environment.serviceApiUrl+'/master/substations?m_org_id='+id,new CustomRequestOptions()).map(res => res.json());      
    }

    getfeedBySSId(id:string){
        return this.http.get(environment.serviceApiUrl+'/master/feeders?substation-id='+id,new CustomRequestOptions()).map(res => res.json());      
    }

    getfeederlistdata(feeder_id:string,month:string,year:string){
        return this.http.get(environment.serviceApiUrl+'/transaction/solar-line-loss?feeder_id='+feeder_id+"&reading_month="+month+"&reading_year="+year,new CustomRequestOptions()).map(res => res.json())
    }

    savesolar_data(data:any){
        return this.http.post(environment.serviceApiUrl+'/transaction/import-substation-loss',data,new CustomRequestOptions()).map(res=>res);
    }
}