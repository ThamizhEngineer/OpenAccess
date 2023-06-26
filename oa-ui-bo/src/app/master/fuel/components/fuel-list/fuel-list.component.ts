import { Component, OnInit } from '@angular/core';
import { FuelService } from '../../services/fuel.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Fuel } from '../../../vo/fuel';



@Component({
    selector: 'app-fuel-list',
    templateUrl: './fuel-list.component.html',
    styleUrls: ['./fuel-list.component.scss'],
    providers: [FuelService]
})

export class FuelListComponent implements OnInit {

    rows: Array<Fuel>;
    fuelName:string="";
    fuelCode:string="";
    fuelGroup:string="";





    constructor(      
        private route: ActivatedRoute,
        private router: Router,
        private service: FuelService) { }

    ngOnInit() {
        this.rows = [];
     }

    newFuel() {
        try{
         this.router.navigateByUrl('/fuel/fuel-new');    
            }
          catch(e){
          console.log(e);
          }
        }

        fetchResults()
        {
            this.service.getFuel(this.fuelName,this.fuelCode,this.fuelGroup).subscribe(
            _fuels => {  
            this.rows = _fuels;
             console.log(this.rows) ;
    
            });
       }
       editFuel(_id: String) {
        try{
         console.log("In edit"+_id);
         this.router.navigateByUrl('fuel/fuel-edit/'+_id);

           }
          
        catch(e){
          console.log(e);
           }
    
       }
}