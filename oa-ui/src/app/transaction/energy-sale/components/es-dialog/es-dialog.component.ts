import { Component, Inject ,Input} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { EsService } from './../../services/es.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Es } from '../../../vo/es';
import { Job} from '../../../../transaction/vo/job';


@Component({
    selector: 'es-dialog',
    templateUrl: './es-dialog.component.html'
  })
  export class EsDialogComponent {
      esId:string;
      job:Job;

    constructor(public dialogRef: MatDialogRef<EsDialogComponent>,
		@Inject(MAT_DIALOG_DATA) public data: any,private service: EsService,private router: Router) { 
        // console.log('router path' + this.router.url);
        var str=this.router.url;
        var n = str.length;
        console.log('n-'+n);
        var res = str.slice(12, n);
        console.log('res-'+res);
        this.esId=res;
        console.log('this.esId'+this.esId);
        }
  

        @Input() es:Es;
	  

	initiateAllotment(){
        this.job = new Job();
        console.log(this.esId) ;
        if(this.esId!=null || this.esId!=undefined || this.esId!=""){
            this.job.tEnergySaleId=this.esId.toString();
            console.log(this.job);
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
		this.router.navigateByUrl('/es/es-list');
	
	}
	close(){
		this.dialogRef.close(false);
	}
  
  }