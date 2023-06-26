import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { CommonUtils } from '../../../../shared/common/common-utils';

import { LogService } from './../../services/log.service';

const colorCodes: any = {
	'APPLIED': 'mat-purple',
	'APPROVED': 'mat-lime',
	'PENDING-EXECUTION': 'mat-red',
	'EXECUTED': 'mat-green'
};

@Component({
	selector: 'log-detail',
	templateUrl: './log-detail.component.html',
	styleUrls: ['./log.scss'],
	providers: [CommonUtils
	]
})
export class LogDetailComponent {
    constructor(private route: ActivatedRoute, private router: Router, private service: LogService,private commonUtils: CommonUtils) {}
	id:string;
	timeline: any;
	status: any;
	
	isGen:boolean = false;


	colorCodes = colorCodes;
	ngOnInit(){
		// this.service.getLog().subscribe(x=>{
		// 	this.timeline = x;
		// });

		this.route.queryParams.filter(params=>params.id)
		.switchMap((params: Params) => this.service.getEsForLog(params.id))
		.subscribe(x=>{
			this.timeline = x;	
			console.log("timeline")
			console.log(this.timeline)

			});

			this.route.queryParams.filter(params=>params.id)
			.switchMap((params: Params) => this.service.getEsStatus(params.id))
			.subscribe(x=>{
				this.status = x;	
				console.log("status")
				console.log(this.status)
	
				});
		// this.service.getEsForLog().subscribe(x=>{
		// 	this.timeline = x;
		// });

	

		this.isGen = CommonUtils.getLoginUserTypeCode()=="GEN";
        console.log("Service type code")
		console.log(this.isGen)
		
		
	}
	listEsIntent() {
		// this.router.navigateByUrl('/power-sale/ps-list');
		this.router.navigateByUrl('/sale/list/POWER-SALE');
	  }


	  editNoc(_id: string){
		console.log("Edit id value" + _id);
		this.router.navigateByUrl('/noc/noc-edit/' + _id);	  }


		editConsent(_id: string){
			console.log("Edit id value" + _id);
			this.router.navigateByUrl('/consent/consent-edit/' + _id);
		}
}