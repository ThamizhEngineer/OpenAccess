import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { CommonService } from '../../../../shared/common/common.service';
 
import { PowerplantService } from './../../services/powerplant.service'; 
import { CommonUtils } from '../../../../shared/common/common-utils';

import { Powerplant} from './../../../vo/powerplant'; 

@Component({
  selector: 'app-powerplant-list',
  templateUrl: './powerplant-list.component.html',
  styleUrls: ['./powerplant-list.component.scss'],
  providers: [PowerplantService,CommonService]
})
export class PowerplantListComponent implements OnInit {
    powerplant: Powerplant;
    rows: Array < Powerplant > ;
    tempResults: Array < Powerplant > ;
    searchSellerServiceNumber: string = "";
    searchMeterNumber: string = "";
    searchOrgId: string = "";
    filteredEDCs = [];
    filteredCode = [];
    edcList=[];

    orgId = [];
    fuelTypes = [];
    regions = [];
	powerplantType: string = '';
    edcId:string;
	userType:string;
    isGen:boolean=false;
    isEdc:boolean=false;

    constructor( private route: ActivatedRoute, private router: Router, private service: PowerplantService, private commonService: CommonService,private commonUtils: CommonUtils ) {
        
    }

    ngOnInit() {
		this.fetchEDCs();
        this.fetchFuelTypes();
        this.rows = [];
        this.rows = [];
        this.isEdc = CommonUtils.getLoginUserTypeCode()=="EDC";

		if(this.route.snapshot.url[0].path == 'my-powerplant'){
			this.powerplantType = 'my';
		}
		else if(this.route.snapshot.url[0].path == 'nces-powerplant'){
			this.powerplantType = 'nces';
		}
		if (this.route.params['_value']['_id']) {
            this.route.params.switchMap((params: Params) => this.service.getPowerplantBySearchCriteria(params['_id'], '', '')).subscribe(x => {
				this.rows = x;
			});
        }

        this.powerplant = new Powerplant();

        if(CommonUtils.getLoginUserTypeCode()=="EDC"){
            this.searchOrgId = CommonUtils.getLoginUserEDC();
            console.log("edc id")
            console.log(this.searchOrgId)
        }else{
            this.searchOrgId="";
        }

    }



    filterEDCs(val: string) {
        return val ? this.orgId.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.orgId;
    }

    filterCode(val: string) {
        return val ? this.fuelTypes.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.fuelTypes;
    }

    fetchEDCs() {
        this.commonService.fetchEDCs().subscribe(
            edc=>{
              console.log("In page load edc")
              this.edcList = edc;
              console.log(this.edcList)
            }
          )
        // this.commonService.fetchEDCs().subscribe(
        //     result => {
        //         this.orgId = result;
		// 		this.regions = result.filter(x=>x.typeCode == "REGION");
        //     },
        //     error => {
        //         console.error('Error loading orgs!', error);
        //     });
    }
    fetchFuelTypes() {
        this.commonService.fetchCodes('FUEL_TYPE_CODE').subscribe(
            result => {
                this.fuelTypes = result;
            },
            error => {
                console.error('Error loading fueltypes', error);
            });
    }

    fetchResults() {
        this.service.getPowerplantBySearchCriteria(this.searchOrgId, this.searchMeterNumber, this.searchSellerServiceNumber).subscribe(
            _powerplants => {
                this.rows = _powerplants;
            });
    }

    newPowerplant() {
        try {
            this.router.navigateByUrl('/powerplant/powerplant-new');
        } catch (e) {
            console.log(e);
        }
    }

    editPowerplant(_id: string) {
        try {
            this.router.navigateByUrl('/powerplant/powerplant-edit/' + _id);
        } catch (e) {
            console.log(e);
        }
    }
	
	viewPowerplant(_id: string) {
        try {
            this.router.navigateByUrl('/powerplant/powerplant-view/' + _id);
        } catch (e) {
            console.log(e);
        }
    }
///To allow number only
    numberFormat(event){
        this.commonService.numberOnly(event)
    }
}