import { Router, ActivatedRoute, Params } from '@angular/router';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { Component, OnInit, OnDestroy, ViewChild, HostListener, Inject } from '@angular/core';

import { DashboardService } from './dashboard.service';

import { ChartOptions } from './_models/chart.option';
import { CommonUtils } from '../shared/common/common-utils';

const ChartColors: any[] = [{
		backgroundColor:["#3f51b5", "#85144B", "#5B481A", "#FF4136", "#3D9970", "#FF851B", "#B10DC9", "#111111", "#01FF70", "#FFDC00", "#FF4136", "#001F3F"] 
      }];
	  
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  
	barChartOptions1: any;
	barChartOptions2: any;
	pieChartOptions: any;
	
	barChartLabels1: string[];
	barChartLabels2: string[];
	
	barChartData1: any[];
	barChartData2: any[];
	
	pieChartLabels:string[];
	pieChartData:any[];
	
	chartColors: any = ChartColors;
	constructor(private route: ActivatedRoute, private router: Router, private service: DashboardService, public dialog: MatDialog) {	}
	
	uCode: string;
	ngOnInit() {
		let user = this.service.getUserDetail();
		this.uCode = user.userTypeCode;
		//this.service.getDataSetById(user.id).subscribe(x=>{ });
		
		 
		if(this.uCode == 'A' || this.uCode == 'CON' || this.uCode == 'EDC'){
			this.barChartOptions1 = new ChartOptions('Units Generated In Last 6 Months', 'Months', "Total Units Generated (MW)").barChartOptions;
			this.barChartLabels1 = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'];
			this.barChartData1 = [
				{data: [5, 6, 8, 5, 9, 10], label: 'MW'}
			];
			
			this.barChartOptions2 = new ChartOptions('Units Adjusted to each buyer during last month', 'Service #', "Total Units Adjusted (MW)").barChartOptions;
			this.barChartLabels2 = ['#001', '#002', '#003', '#005', '#008'];
			this.barChartData2 = [
				{data: [5, 6, 8, 5, 9], label: 'MW'}
			];
		}
		
		
		if(this.uCode == 'A' || this.uCode == 'EDC'){
			this.pieChartOptions = new ChartOptions('Total Units generated last month').pieChartOptions;
			this.pieChartLabels = ['Seller 1', 'Seller 2', 'Seller 3', 'Seller 4', 'Seller 5', 'Seller 6'];
			this.pieChartData = [20, 60, 50, 70, 80, 30]
		}


		if(this.uCode == 'SLDC' || this.uCode == 'PPP'){
			this.barChartOptions1 = new ChartOptions('Aggrement Approved In Last 6 Months', 'Months', "Total Pending Approvals").barChartOptions;
			this.barChartLabels1 = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'];
			this.barChartData1 = [
				{data: [2, 1, 3, 3, 6, 5], label: 'Aggrement'}
			];

		}

		if(this.uCode == 'SLDC'){
			this.pieChartOptions = new ChartOptions('Total OA Aggrement Approved last month').pieChartOptions;
			this.pieChartLabels = ['STOA', 'MTOA', 'LTOA', 'IEX-CON', 'IEX-GEN'];
			this.pieChartData = [70, 40, 30, 5, 10]
		}

		if(this.uCode == 'PPP'){
			this.pieChartOptions = new ChartOptions('Total Consent Approved last month').pieChartOptions;
			this.pieChartLabels = ['Captive', 'Third-Party'];
			this.pieChartData = [10, 5]
		}

		// if(this.uCode == 'EDC'){
		// 	this.barChartOptions1 = new ChartOptions('Units Generated In Last 6 Months', 'Months', "Total Units Generated (MW)").barChartOptions;
		// 	this.barChartLabels1 = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'];
		// 	this.barChartData1 = [
		// 		{data: [5, 6, 8, 5, 9, 10], label: 'MW'}
		// 	];
		// }

		// if(this.uCode == 'EDC'){
		// 	this.pieChartOptions = new ChartOptions('Total EWA Approved last month').pieChartOptions;
		// 	this.pieChartLabels = ['Captive', 'Third-Party'];
		// 	this.pieChartData = [20, 10]
		// }

		if(CommonUtils.getIsMaterConfirmed()=='N' || CommonUtils.getIsMaterConfirmed()==null || CommonUtils.getIsMaterConfirmed()==""){
			this.confirmMaster();

		}

	}

	
	confirmMaster() {
		setTimeout(() => this.dialog.open(masterConfirmComponent))
    }
    
}

@Component({
	selector: 'master-confirm-dialog-handler',
	template: `

	<section class="mat-typography">
	  <h1>Important</h1>
	 <h3> Welcome to Open Access Accounting and Adjustment Software</h3>
	</section>

	<section class="mat-typography">
	<h2>For the kind attention of Generators</h2>
	</section>

	<section class="mat-typography">
	<div><p>1) First go to the WEG Generator details screen and verify all the details under different Tabs.</p></div>
	<div><p>2) If the details are found correct, please click the ‘CONFIRM ‘ button.</p></div>
	<div><p>3) In case of any discrepancy, press ‘CANCEL’ button and contact the Generating end EDC concerned, for modification of details along with records.</p></div>
	<div><p>4) After confirming the correctness of the Master data, other screens will be enabled.</p></div>
	</section>

	<section class="mat-typography">
	<h2>Thank you for your Cooperation</h2>
  	</section>

	<section class="mat-typography">
	<p>Click open to view and confirm master data </p>
	</section>

	  <div mat-dialog-actions>
		  <button type="button" color="primary" mat-raised-button (click)="openMaster()">View weg Generator details</button>

	  </div>
   `,
  })
  export class masterConfirmComponent {
  
	  constructor(public dialogRef: MatDialogRef<masterConfirmComponent>, 
		@Inject(MAT_DIALOG_DATA) public data: any,private service: DashboardService,private router: Router) { 
		console.log('router path' + this.router.url);
		}
	  

	openMaster(){
		this.router.navigateByUrl('/my-powerplant/view');
		this.close();
	}
	close(){
		this.dialogRef.close(false);
		// this.logout();
	}

    logout(): void {
        this.router.navigateByUrl('/logout');
    }

  }
