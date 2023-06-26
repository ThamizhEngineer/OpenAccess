import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
 
import { PowerplantService } from './../../services/powerplant.service'; 
import { Powerplant} from './../../../vo/powerplant'; 
import { Meter } from '../../../vo/company.v1';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-powerplant-view',
  templateUrl: './powerplant-view.component.html',
  providers: [PowerplantService]
})
export class PowerplantViewComponent implements OnInit {

    constructor( private route: ActivatedRoute, private router: Router, private service: PowerplantService ) {
        
    }

	powerplant: Powerplant;
	meter:Meter;
	meters:any;
	companyService:any;
	company:any;

    ngOnInit() {
		if (this.route.params['_value']['_id']) {
		  this.route.params.switchMap((params: Params) => this.service.getPowerplantById(this.route.params['_value']['_id'])).subscribe((_powerplant: Powerplant) => {
				this.powerplant = _powerplant[0];  
				if(this.powerplant!=null){
				// let meter=this.powerplant.company.services[0].meters[0];
				this.meters=this.powerplant.company.services[0].meters[0];
				this.companyService=this.powerplant.company.services[0];
				this.company=this.powerplant.company;
				console.log("meter")
				console.log(this.powerplant.company.services[0]);
				}
				console.log(this.powerplant)      
			});
		}
	   }
	
	listPowerplant() {
		this.router.navigateByUrl('/powerplant/powerplant-list');
	}
	listMyPowerplant() {
		this.router.navigateByUrl('/powerplant/my-powerplant');
	}
	openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "Gen-master"+this.powerplant.companyCode+this.powerplant.code+".pdf";
		console.log(content_type)
		saveAs(data.blob(), x_filename);
  }
  printPowerplantGenerator(){
    this.service.printPPGenMaster(this.powerplant).subscribe(xs=>{
			this.openDownload(xs);
		});

  }

}