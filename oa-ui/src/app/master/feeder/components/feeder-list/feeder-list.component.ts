import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonService } from '../../../../shared/common/common.service';

import { FeederService } from './../../services/feeder.service'; 
import { Feeder} from './../../../vo/feeder'; 

@Component({
  selector: 'app-feeder-list',
  templateUrl: './feeder-list.component.html',
  styleUrls: ['./feeder-list.component.scss'],
  providers: [FeederService]
})
export class FeederListComponent implements OnInit{ 

  rows: Array<Feeder>;
  tempResults: Array<Feeder>;
  /*
  columns = [
              { prop: 'feederId', name: 'Feeder Id'  },
              { prop: 'feederName', name: 'Feeder Name' }
            ];
*/
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
  
  searchSsId:string="";
  searchName:string="";
  searchVoltage:string="";
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: FeederService
  ) {}

  ngOnInit() {
   this.rows = [];   
  }
 
 

  fetchResults(){
    this.service.getFeeders(this.searchSsId,this.searchName,this.searchVoltage).subscribe(
       _feeders => {  
        this.rows = _feeders;
        console.log(this.rows)
     });
  }
 
  newFeeder() {
	  try{
	    //this.router.navigate(['/feeder-new','_id', _id]);
      this.router.navigateByUrl('/feeder/feeder-new');
      // to-do
      // this still needs to be fixed as giving '/order' is unecefeederary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
    
  }

  editFeeder(_id: string) {
	  try{
	  console.log("Edit id value"+_id);
     this.router.navigateByUrl('/feeder/feeder-edit/'+ _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecefeederary maintenance hazzle
      
	  }
	  catch(e){
	  console.log(e);
	  }
    
  }

}
