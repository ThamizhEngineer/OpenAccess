import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
 
import { PowerplantService } from './../../services/powerplant.service'; 
import { Powerplant} from './../../../vo/powerplant'; 
import { Meter } from '../../../vo/company.v1';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { saveAs } from 'file-saver';
import { AuthUser } from '../../../../user/services/authuser';
import { CompanyService } from '../../../company/services/company.service';

@Component({
  selector: 'app-powerplant-view',
  templateUrl: './powerplant-view.component.html',
  providers: [PowerplantService]
})
export class PowerplantViewComponent implements OnInit {

    constructor( private route: ActivatedRoute, private router: Router, private service: PowerplantService, private comService: CompanyService , private commonUtils: CommonUtils  ) {
        
    }

	powerplant: Powerplant;
	meter:Meter;
	meters:any;
	companyService:any;
	company:any;
	disableConfirm: boolean = false;
	contact:any;
	// user: AuthUser;

    ngOnInit() {
		if(CommonUtils.getLoginUserTypeCode()=="GEN"){
			this.service.getPowerplantByServiceId(CommonUtils.getLoginUserServiceNumber()).subscribe(
				_powerplants => {
					this.powerplant = _powerplants[0]; 
					if(this.powerplant!=null){
						this.meters=this.powerplant.company.services[0].meters[0];
						this.companyService=this.powerplant.company.services[0];
						this.company=this.powerplant.company;
					} 
				});

        }
        else{
            alert("No Powerplants assoicated this user");
		}
		

		if(CommonUtils.getIsMaterConfirmed()=='Y'){
			this.disableConfirm=true;
		}
		
		var user: AuthUser = JSON.parse(sessionStorage.getItem('user'));
		this.comService.getServiceContactInfoById(user.companyServiceId).subscribe(
			_service => {
				this.contact=_service;
			});
	   }
	
	   openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "Gen-master"+this.powerplant.companyCode+this.powerplant.code+".pdf";
		saveAs(data.blob(), x_filename);
  }
  printPowerplantGenerator(){
    this.service.printPPGenMaster(this.powerplant).subscribe(xs=>{
			this.openDownload(xs);
		});

  }

//   confirmMaster(){
// 	var userName=CommonUtils.getLoginUserDetails();
// 	console.log(userName);
// 	this.service.confirmMaster(userName);
// }
	   
confirmMaster() {
	var userName=CommonUtils.getLoginUserDetails();
    this.service.confirmMaster(userName).subscribe(
      result => {
        this.logout();
      },
      error => {
        console.error('Error updating CompanyMeterChange!');
        console.error(error);
      }
    );
  }

  logout(): void {
	this.router.navigateByUrl('/logout');
}
}