import { Component, OnInit } from '@angular/core';
import { FuelService } from '../../services/fuel.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Fuel } from '../../../vo/fuel';

@Component({
    selector: 'app-fuel-detail',
    templateUrl: './fuel-detail.component.html',
    styleUrls: ['./fuel-detail.component.scss'],
    providers: [FuelService]
})

export class FuelDetailComponent implements OnInit {

    fuel: Fuel ;
    addScreen: boolean = true;

    constructor(        
        private route: ActivatedRoute,
        private router: Router,
        private service: FuelService) { }

    ngOnInit() { 
        this.fuel = new Fuel();
        console.log('route param - '+this.route.params['_value']['_id'])
        if (this.route.params['_value']['_id']) {
        //route.params['value'] will have the '_id' in it, during edit 
        this.addScreen = false;
        this.route.params
        .switchMap((params: Params) => this.service.getFuelById(params['_id']))
        .subscribe((_fuel: Fuel) => {   
        this.fuel = _fuel;
         console.log("in Fuel"+this.fuel);
        });
        }
    }
    saveFuel()
    {
      console.log('In save'+this.addScreen);
      if(this.addScreen){
        console.log('add screen')
       this.addFuel();
      }
      else
      {
        console.log('update screen')
        this.updateFuel();
       }
      }
      addFuel() 
      {
       this.service.addFuel(this.fuel).subscribe(
       result => {
       this.fuel.id = result;
       this.listFuel();
        },
       error => {
       console.error('Error adding Fuel!');
       console.error(error);
        }
        );
       }

       updateFuel()
       {
        console.error('Fuel');
       this.service.updateFuel(this.fuel).subscribe(
       result => { 
      //  this.listFuel();
      this.router.navigateByUrl('/fuel/fuel-list');

         },
       error => {
       console.error('Error updating fuel!');
       console.error(error);
         }
         );
       }

       listFuel() 
       {
       this.router.navigateByUrl('/fuel/fuel-list');
       }
}