import { Component, Inject ,Input} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { EaService } from './../../services/ea.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Es } from '../../../vo/es';
import { Job} from '../../../../transaction/vo/job';


@Component({
    selector: 'ea-dialog',
    templateUrl: './ea-dialog.component.html'
  })
  export class EaDialogComponent {
      esId:string;
      job:Job;

    constructor(public dialogRef: MatDialogRef<EaDialogComponent>,
		@Inject(MAT_DIALOG_DATA) public data: any,private service: EaService,private router: Router) { 
        // console.log('router path' + this.router.url);
        var str=this.router.url;
        var n = str.length;
        var res = str.slice(14, n);
        this.esId=res;

        }
  

        @Input() es:Es;
	  

	initiateAllotment(){
        this.job = new Job();
        
        console.log(this.esId)
        if(this.esId!=null || this.esId!=undefined || this.esId!=""){
            this.job.tEnergySaleId=this.esId.toString();
            console.log(this.job)
            this.service.triggerEnergySaleEvent(this.job).subscribe(
				result => {

                    this.listEss();
                    this.close();
				},
				error => {
					console.error('Error updating final!');
					console.error(error);
				}
			);
        
            }
    }
    listEss() {
		this.router.navigateByUrl('/ea/ea-list');
	
	}
	close(){
		this.dialogRef.close(false);
	}
  
  }