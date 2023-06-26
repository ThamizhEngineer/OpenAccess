import 'rxjs/add/operator/switchMap';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { GsEvent } from './../../services/gs.event'; 
import { Gs} from './../../../vo/gs'; 

@Component({
  selector: 'gs-slotview',
  templateUrl: './gs-slotview.component.html',
  styleUrls: ['./gs-slotview.component.scss']
})
export class GsSlotviewComponent implements OnInit {
  gs: Gs;
  constructor(private gcEvent: GsEvent) { 
    }

  ngOnInit() {


   this.gcEvent.gs$.subscribe(latestValue =>{ 
     console.log('slotview event load - '+JSON.stringify(latestValue));
     this.gs =  latestValue;
    });
   this.gcEvent.load();

  }

}
