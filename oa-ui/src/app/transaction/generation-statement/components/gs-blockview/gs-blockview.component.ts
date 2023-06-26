import 'rxjs/add/operator/switchMap';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { GsEvent } from './../../services/gs.event'; 
import { Gs} from './../../../vo/gs'; 

@Component({
  selector: 'gs-blockview',
  templateUrl: './gs-blockview.component.html',
  styleUrls: ['./gs-blockview.component.scss']
})
export class GsBlockviewComponent implements OnInit {
  gs: Gs;
  constructor(private gcEvent: GsEvent) { 
    }

  ngOnInit() {


   this.gcEvent.gs$.subscribe(latestValue =>{ 
     console.log('blockview event load - '+JSON.stringify(latestValue));
     this.gs =  latestValue;
    });
   this.gcEvent.load();

  }

}
