import 'rxjs/add/operator/switchMap';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
//import { SessionStorage} from "angular2-localstorage/WebStorage";

import { TariffEvent } from './../../services/tariff.event';
import { TariffService } from './../../services/tariff.service';
import { Tariff } from './../../services/tariff';

@Component({
  selector: 'tariff-detail',
  templateUrl: './tariff-detail.component.html',
  providers: [TariffService]
})
export class TariffDetailComponent implements OnInit {

  //for pagination
  count = 0;
  offset = 0;
  limit = 4;
  rows: Array<Tariff>;
  tempResults: Array<Tariff>;
  
  
  constructor(
    private service: TariffService,
    private tariffEvent: TariffEvent
  ) { }

  ngOnInit() {
    this.rows = [];
	this.page(this.offset, this.limit);
   }

  page(offset, limit) {
    this.offset = offset;
    this.limit = limit;
    this.fetchResults();
  }
  
  updateTariff(tariff: Tariff) {
	var s = (tariff._id != "") ? this.service.updateTariff(tariff): this.service.addTariff(tariff);
    s.subscribe(
      result => {
      },
      error => {
        console.error('Error updating Tariff!');
        console.error(error);
      }
    );
  }
	
  onPage(event) {
	this.page(event.offset, event.limit);
  }
  
  fetchResults(){
	this.service.getTariffs().subscribe(
		_tariffs => {  
		this.tempResults = _tariffs;
		
        this.count = this.tempResults.length;
        const start = this.offset * this.limit;
        const end = start + this.limit;
        this.rows = [];

        for (let i = start; i < end; i++) {
          if(this.tempResults[i]) this.rows.push(this.tempResults[i]);
        }
      });
	  
  } 
  newTariff(){
	var tariff = this.tariffEvent.setEmpty();
	this.rows.push(tariff);
	this.limit++;
  }
}
