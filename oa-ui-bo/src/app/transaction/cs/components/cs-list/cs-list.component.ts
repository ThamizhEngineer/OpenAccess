import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { CsService } from './../../services/cs.service';
import { Cs} from './../../../vo/cs'; 
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from './../../../../shared/common/common.service';
@Component({
    selector: 'cs-list',
    templateUrl: './cs-list.component.html',
    providers: [CsService,CommonUtils,CommonService]
})
export class CsListComponent implements OnInit {
    cs: Cs;
	searchSellerOrgId:string;
	sellerCompanyServices=[];
	edcList=[];
	statuses =[{ "key": "CREATED", "name": "Created" },
	{ "key": "COMPLETED", "name": "Completed" },]
	searchSellerCompanyServiceId:string;
	rows: Array<Cs>;
	searchCode: string;
	tempResults: Array<Cs>;
	searchStatus:string;
    constructor(
        private route: ActivatedRoute,
        private router: Router,
		private service: CsService,
		private commonUtils: CommonUtils,
		private commonService: CommonService,
    ) { }

	ngOnInit() {
		this.rows = [];
		this.fetchOrgList();

	}


	fetchResults() {

		this.service.getAllCss(this.searchSellerOrgId,this.searchSellerCompanyServiceId,this.searchCode,this.searchStatus).subscribe(
		_cs => {
			
			this.rows = _cs;

		});
	}

	fetchOrgList(){
		this.commonService.fetchEDCs().subscribe(
		  result => { 
			this.edcList = result;
		  },
		  error => {
			 console.error('Error loading states!');
			 console.error(error);
		  }
		  );
	  }
	  onEdcChange() {
		console.log("In edc change");
		
		this.service.getCsCompanyByEdc("",this.searchSellerOrgId).subscribe(
		  _companyServices => {
			this.sellerCompanyServices = _companyServices;
			console.log(this.sellerCompanyServices)
		 
		  }
		)
	  }
	newCs() {
		try{
			this.router.navigateByUrl('/cs/cs-new');
		}
		catch(e){
			console.log(e);
		}
	}

	editCs(_id: string) {
		try{
			this.router.navigateByUrl('/cs/cs-edit/'+ _id);
		}
		catch(e){
			console.log(e);
		}
	}

}