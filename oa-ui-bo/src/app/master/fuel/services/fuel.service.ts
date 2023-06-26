import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import {CustomRequestOptions} from '../../../shared/common/common.service';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import { Fuel } from '../../vo/fuel';

@Injectable()
export class FuelService {
    constructor(private http:Http){
        console.log('fuel Service initialised');
    }

    getFuelById(id: string){
        return this.http.get(environment.serviceApiUrl+'/master/fuel/GetBy/'+id,new CustomRequestOptions()).map(res => res.json());      
    }


         getFuel(fuelName:string,fuelCode:string,fuelGroup:string){
            var url = environment.serviceApiUrl+'/master/fuel/GetAll';

            if(fuelName !="" || fuelCode != "" || fuelGroup !="") {
                url = url + "?dummy=1";  
                if(fuelName != ""){
                    url = url + "&fuelName="+fuelName ;
                }
                if(fuelCode != ""){
                    url = url + "&fuelCode="+fuelCode ;
                }
                if(fuelGroup != ""){
                    url = url + "&fuelGroup="+fuelGroup ;
                } 

            }
 
            console.log(url);
            return this.http.get(url,new CustomRequestOptions()).map((res: Response) => res.json());
        }

        removeUnwantedTags(fuel: Fuel) {
            delete fuel.id;
           
             return fuel;
             }

             addFuel(fuel: Fuel){ 
                fuel=this.removeUnwantedTags(fuel);
                let headers = new Headers({ 'Content-Type': 'application/json'});
                let options = new RequestOptions({ headers: headers });
                let body = JSON.stringify(fuel);
                let url = environment.serviceApiUrl+'/master/fuel/Post';
                console.log('Fuel for add-'+JSON.stringify(Fuel));
                return this.http.post(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
              }


              updateFuel(fuel: Fuel){
                var key = fuel.id; 
                console.log('key'+key);
                let headers = new Headers({ 'Content-Type': 'application/json'});
                let options = new RequestOptions({ headers: headers });
                let body = JSON.stringify(fuel);
                let url = environment.serviceApiUrl+'/master/fuel/Update/'+key;
                return this.http.patch(url, body, new CustomRequestOptions()).map((res: Response) => res.json());
            }
                      
}