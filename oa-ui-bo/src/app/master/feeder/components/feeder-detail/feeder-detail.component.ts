import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonService } from '../../../../shared/common/common.service';

import { FeederService } from './../../services/feeder.service'; 
import { Feeder} from './../../../vo/feeder'; 


@Component({
  selector: 'feeder-detail',
  templateUrl: './feeder-detail.component.html',
  providers: [FeederService,CommonService]
})

export class FeederDetailComponent{
    feeder: Feeder;
    addScreen: boolean = true;
    disableControls: boolean = true;
    voltages =[
                {"key":"11KV","name":"11KV"},
                {"key":"22KV","name":"22KV"},
                {"key":"22-11KV","name":"22-11KV"},
                {"key":"23KV","name":"23KV"},
                {"key":"33KV","name":"33KV"},
                {"key":"33-11KV","name":"33-11KV"},
                {"key":"66KV","name":"66KV"},
                {"key":"110KV","name":"110KV"},
                {"key":"230KV","name":"230KV"},
                {"key":"UNKNOWN","name":"UNKNOWN"},
    ];
    
    isEnabled =[
        {"key":"Yes","name":"Yes"},
        {"key":"No","name":"No"}
    ];

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private service: FeederService,
        private commonService :CommonService
    ) {
    
   }

  ngOnInit() { 
    this.feeder = new Feeder();
    
    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getFeederById(params['_id']))
        .subscribe((_feeder: Feeder) => {
          this.feeder = _feeder;
        console.log("in feeder"+this.feeder);

        });
    }

  }
 
  

  saveFeeder() {    
    console.log('In save'+ this.addScreen );
    if (this.addScreen) {
      this.addFeeder();
    }
    else {
      this.updateFeeder();
    }
  }

  addFeeder() {
    this.service.addFeeder(this.feeder).subscribe(
      result => {
        this.feeder.id = result;
        this.listFeeder();
      },
      error => {
        console.error('Error adding Feeder!');
        console.error(error);
      }
    );
  }

  updateFeeder() {
    this.service.updateFeeder(this.feeder).subscribe(
      result => {
        this.listFeeder();
      },
      error => {
        console.error('Error updating Feeder!');
        console.error(error);
      }
    );
  }
  listFeeder() {
    this.router.navigateByUrl('/feeder/feeder-list');
  }

  
}