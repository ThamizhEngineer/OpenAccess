import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { MatDatepickerModule, MatNativeDateModule, NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from './../../../../shared/common/date.adapter';

import { SaleService } from './../../services/sale.service';
import { Sale } from './../../../vo/sale';

const SALETYPES: any = {
	'IPA': 'IPA 3rd Party Sale',
	'FOSSIL': 'Fossil Captive Sale',
	'STB': 'Sale-to-Board',
	'IEX': 'IEX Sale'
};

const AGREEMENTPERIOD: any = ['STOA', 'MTOA', 'LTOA'];

@Component({
  selector: 'sale-detail',
  templateUrl: './sale-detail.component.html',
  providers: [
        { provide: DateAdapter, useClass: AppDateAdapter },
        { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
    ]
})
export class SaleDetailComponent implements OnInit {

	constructor(private activatedRoute: ActivatedRoute, private router: Router, private service: SaleService) {}
	
	types = SALETYPES;
	agreementPeriods = AGREEMENTPERIOD;
	saleType: string;
	currentUser: any;
	sale: any;

	companyNames: any = [];	
	allCompanies: any = [];	
	orgList: any = [];	
	allOrgs: any = [];	
	sellerList: any = [];	
	allSellers: any = [];
	
	ngOnInit() {
		this.sale = new Sale();
		if (this.activatedRoute.params['_value']['_type']) {
			this.activatedRoute.params.subscribe( (params: Params)=>{
				this.saleType = this.types[params['_type']];
			});
		}
		let usr = this.currentUser = this.service.getUser();

		this.service.getReports(usr.companyId).subscribe(lists=>{
			this.modifyObj(lists);
		});
		
	}

	previous(){
		this.router.navigateByUrl('/sale/sale-create/');
	}
	
	modifyObj(lists){
		lists.forEach(x=>{
			this.allCompanies.push({'id': x.companyId, 'name': x.companyName});
			this.allOrgs.push({'id': x.orgId, 'name': x.orgName, 'code': x.orgCode});
			this.allSellers.push({'id': x.sellerServiceId, 'serviceNumber': x.sellerServiceNumber});
		});
		let a = this.allOrgs.map(obj=>obj.id);		
		this.orgList = this.allOrgs = this.allOrgs.filter((x, i) => a.indexOf(x.id) == i);
		
		let b = this.allCompanies.map(obj=>obj.id);		
		this.companyNames = this.allCompanies = this.allCompanies.filter((x, i) => b.indexOf(x.id) == i);
		
		let c = this.allSellers.map(obj=>obj.id);		
		this.sellerList = this.allSellers = this.allSellers.filter((x, i) => c.indexOf(x.id) == i);
	}
	
	checkDate(){
		let oneDay = 24*60*60*1000;
		if(this.sale.fromDate && this.sale.toDate){
			let a = new Date(this.sale.fromDate);
			let b = new Date(this.sale.toDate);
			
			let diffDays = Math.round(Math.abs((b.getTime() - a.getTime())/(oneDay)));
			this.sale.agreementPeriod = diffDays <= 90 ? this.agreementPeriods[0] : (diffDays >= 1095? this.agreementPeriods[2]: this.agreementPeriods[1]);
		}
	}
	
	filterCompanies(val: string) {
		return val ? this.allCompanies.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.allCompanies;
    }
	
	filterOrgs(val: string) {
        return val ? this.allOrgs.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.allOrgs;
    }
	
	filterSellers(val: string) {
        return val ? this.allSellers.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.allSellers;
    }
	
	save(){
		console.log(this.sale);
	}
}
