import { Component, OnInit, Inject } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';

import { SaleService } from './../../services/sale.service';

const TITLES = [{"name": "POWER-SALE", "text": "Power Sale"}, {"name": "IPA", "text": "In Principal Application"}, {"name": "NOC", "text": "NOC Consumer"}, {"name": "NOC-GEN", "text": "NOC Generator"}, {"name": "CONSENT", "text": "Consent"}, {"name": "EWA", "text": "Energy Wheeling Application"}, {"name": "EPA", "text": "PPA"}, {"name": "SC", "text": "SC"}, {"name": "OAA", "text": "Open Access Application"}];

const CONFIG = [ {"name": "3rd Party Sale", "key": "THIRD-PARTY", "categories": [], "count": 0 }, {"name": "Fossil Captive Sale", "key": "CAPTIVE", "categories": [], "count": 0 }, {"name": "Sale-to-Board", "key": "STB", "categories": [], "count": 0 }, {"name": "IEX Generator", "key": "IEX-GENERATOR", "categories": [], "count": 0 } ];
@Component({
  selector: 'sale-list',
  templateUrl: './sale-list.component.html',
  styleUrls: ['pricing.scss' ],
  providers: [CommonUtils
  ]
})

export class SaleListComponent implements OnInit {

	mpConfig = {'placeHolder': '', 'readonly':false, type: 'icon'};
	mpModel = {'month':'09', 'year':'2019'};
	constructor(private activatedRoute: ActivatedRoute, private router: Router, private service: SaleService, 
		public dialog: MatDialog,private commonUtils: CommonUtils) {
		let d = new Date();
		this.mpModel = {'month': ("0" + (d.getMonth() + 1)).slice(-2).toString(), 'year': d.getFullYear().toString()};
		
	}
	
	type: string;
	genCompServId:string;
	toUrl: string;
	applicationType: any;
	config: any;
	
	ngOnInit() {
		if (this.activatedRoute.params['_value']['_type']) {
			this.activatedRoute.params.subscribe( (params: Params)=>{
				let t = this.type = params['_type'];
				this.applicationType = TITLES.filter(a => a.name == t).pop();
				
				this.getDetails();				
			});
		}

		this.genCompServId = CommonUtils.getLoginUserServiceNumber();
        console.log("service id")
        console.log(this.genCompServId)
	}
	
	getDetails(){
		this.service.getConfig().subscribe(c=>{
			this.config = c;
			this.service.getSummaryCount(this.type, this.mpModel,this.genCompServId).subscribe(x=>{
				this.modifyObj(x);
			});
		});	
	}
	
	modifyObj(obj){		
		obj.forEach(x=>{
			this.config.forEach(y=>{
				if(x.saleTypeCode==y.key){
					let saleObj = {'statusCode': x.statusCode, 'recCount':x.recCount };
					y.categories.push(saleObj);
					y.count += x.recCount;
				}
			});
		});
	}

	saleDetail(type?: string){
		this.router.navigateByUrl('/sale/sale-detail/' + type);
	}
	
	detailView(obj?: any, con?: any){
		if(obj.recCount < 1) return;
		else{
			const dialogRef = this.dialog.open(DialogDetailViewDialog, {
			  data: {type: this.type, mpModel: this.mpModel, statusCode: obj.statusCode, saleTypeCode: con.key}
			});
			dialogRef.afterClosed().subscribe(result => {
				// if(result) this.redirectIPA(result);
			});
				
			
			
		}
	}
	
	redirectIPA(id){
		console.log('/'+ this.toUrl + '/' + this.toUrl + '-edit' + id);
		this.router.navigateByUrl('/'+ this.toUrl + '/' + this.toUrl + '-edit/' + id);
	}
	
	mpOnChange(e){
		this.mpModel = e;
		this.getDetails();
	}
}

@Component({
  selector: 'dialog-detail-view',
  templateUrl: 'dialog-detail-view.html',

})
export class DialogDetailViewDialog {

	pageNumber = 0;
	pageSize = 10;
	totalElements = 0;
	showPowerSale:boolean=false;
	showNoc:boolean=false;
	showConsent:boolean=false;
	showEwa:boolean=false;
	genCompServId:string;

	constructor( public dialogRef: MatDialogRef<DialogDetailViewDialog>, @Inject(MAT_DIALOG_DATA) public data, private service: SaleService, private router: Router) {}

	ngOnInit(){
		this.setPage({ offset: 0 });


	}
	
	onNoClick(): void {
		this.dialogRef.close();
	}
	
	listPsStatus(_id){
		// this.router.navigate(['/log/detail'] ,{queryParams:{id:_id}});
		this.router.navigateByUrl('/log/detail?id='+_id);
		this.dialogRef.close(_id);
	  }
	
	openPage(_id,processTypeCode){
		if(processTypeCode=='POWER-SALE'){
			this.router.navigate(['/power-sale/fossil-fuel-captive-edit'] ,{queryParams:{id:_id}});
		}

		if(processTypeCode=='NOC'){
			this.router.navigateByUrl('/noc/noc-edit/' + _id);
		}
		
		if(processTypeCode=='CONSENT'){
			this.router.navigateByUrl('/consent/consent-edit/' + _id);
		}
		if(processTypeCode=='EWA'){
			this.router.navigateByUrl('/ewa/ewa-edit/' + _id);
		}
	

		this.dialogRef.close(_id);
	}

	rows: any = [];
	setPage(pageInfo){
		this.genCompServId = CommonUtils.getLoginUserServiceNumber();
        console.log("service id")
        console.log(this.genCompServId)
		this.pageNumber = pageInfo.offset;
		this.service.getSummaryContent(this.data.type, this.data.mpModel, this.data.saleTypeCode, this.data.statusCode,this.genCompServId, this.pageNumber, this.pageSize).subscribe(x=>{
			 this.rows = x.content;
			 console.log(this.rows)
			 this.rows.forEach(x=>{

				console.log(x);
				if(x.processTypeCode=='POWER-SALE'){
						this.showPowerSale =true;
				}

				if(x.processTypeCode=='NOC'){
					this.showNoc =true;
				}

				if(x.processTypeCode=='CONSENT'){
					this.showConsent =true;
				}

				if(x.processTypeCode=='EWA'){
					this.showEwa =true;
				}
				
			 })
			 this.totalElements = x.totalElements;

		});
			
	  }
}