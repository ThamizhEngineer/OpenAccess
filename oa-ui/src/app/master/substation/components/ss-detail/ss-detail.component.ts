
import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
//import { SessionStorage} from "angular2-localstorage/WebStorage"; 
import { SsService } from './../../services/ss.service'; 
import { Ss} from './../../../vo/ss'; 


@Component({
selector: 'app-ss-detail', 
templateUrl:'./ss-detail.component.html', 
providers: [SsService]

})

export class SsDetailComponent 
{   

    ss: Ss ;
    addScreen: boolean = true;
    
    //isDisabled = false; 

    //orgs=[];
    //filteredorgs=[];

    constructor(    
    private route: ActivatedRoute,
    private router: Router,
    private service: SsService
             ) {}

    ngOnInit() { 

            this.ss = new Ss();
            console.log('route param - '+this.route.params['_value']['_id'])
            if (this.route.params['_value']['_id']) {
            //route.params['value'] will have the '_id' in it, during edit 
            this.addScreen = false;
            this.route.params
            .switchMap((params: Params) => this.service.getSsById(params['_id']))
            .subscribe((_ss: Ss) => {   
            this.ss = _ss;
             console.log("in Substation"+this.ss);
            });
            }
          }
       
       saveSs()
          {
            console.log('In save'+this.addScreen);
            if(this.addScreen){
             this.addSs();
            }
            else
            {
              this.updateSs();
             }
            }

        addSs() 
           {
            this.service.addSs(this.ss).subscribe(
            result => {
            this.ss.id = result;
            this.listSs();
             },
            error => {
            console.error('Error adding substation!');
            console.error(error);
             }
             );
            }

        updateSs()
            {
            this.service.updateSs(this.ss).subscribe(
            result => { 
            this.listSs();
              },
            error => {
            console.error('Error updating substation!');
            console.error(error);
              }
              );
            }

            listSs() 
            {
            this.router.navigateByUrl('/ss/ss-list');
            }
        }

