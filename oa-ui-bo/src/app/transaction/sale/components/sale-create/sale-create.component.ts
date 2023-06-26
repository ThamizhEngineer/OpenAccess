import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { SaleService } from './../../services/sale.service';

const types: any = {
	'IPA': 'IPA 3rd Party Sale',
	'FOSSIL': 'Fossil Captive Sale',
	'STB': 'Sale-to-Board',
	'IEX': 'IEX Sale'
};

@Component({
  selector: 'sale-create',
  templateUrl: './sale-create.component.html',
  styleUrls: ['pricing.scss' ]
})

export class SaleCreateComponent implements OnInit {

	constructor(private route: ActivatedRoute, private router: Router, private service: SaleService) {}

	ngOnInit() {
	}

	saleDetail(type?: string){
		this.router.navigateByUrl('/power-sale/fossil-fuel-captive-new');
	}

	iexDetail(type?: string){
		this.router.navigateByUrl('/power-sale/iex-generator');
	}

	pslist(type?: string){
		// this.router.navigateByUrl('/power-sale/ps-list');
		this.router.navigateByUrl('/sale/list/POWER-SALE');
	}
}
