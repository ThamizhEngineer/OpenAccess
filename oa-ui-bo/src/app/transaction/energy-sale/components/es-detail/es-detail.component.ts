
import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding, Inject } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { DatePipe } from '@angular/common';

import { EsEvent } from './../../services/es.event';
import { EsDetailAllocationHelper } from './../../components/es-detail/es-detail-allocation-helper';
import { EsService } from './../../services/es.service';
import { EsDialogComponent } from './../es-dialog/es-dialog.component';

import { Es, EsUsageDetail, EsUsageSummary, generationStatementForPrint, AvailableGsUnits, AllocatedGsUnits } from './../../../vo/es';
import { CommonService } from './../../../../shared/common/common.service';
import { CompanyService } from './../../../../master/company/services/company.service';
import { GsService } from './../../../../transaction/generation-statement/services/gs.service';
import { Gs } from './../../../../transaction/vo/gs';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { TradeRelationshipService } from './../../../../master/trade-relationship/services/trade-relationship.service';
import { TradeRelationship } from './../../../../master/vo/trade-relationship';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { HotRegisterer } from 'angular-handsontable';
import { element } from 'protractor';
import { EnergySaleMultiAddHeader, EnergySaleMultiAddLine } from '../../../vo/energysale-multiadd';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { retryWhen } from 'rxjs/operators';
// import {OaaService} from './../../../../transaction/oaa/services/oaa.service';
// import {Oaa} from './../../../../transaction/vo/oaa';
import { Job} from '../../../../transaction/vo/job';

@Component({
	selector: 'app-bulk-allocation',
	providers: [DatePipe],
	template: `
	<mat-toolbar color="primary">
	<span>Bulk Add</span>

  </mat-toolbar><mat-card class="mat-card-top">
  <mat-card-title>
  <strong>Available Units</strong>
</mat-card-title>
  <ngx-datatable class="material" [rows]="this.fetchAvailableUnitSummary()" [columnMode]="'force'" [headerHeight]="50" [scrollbarH]="true"
  [rowHeight]="'auto'">
  <ngx-datatable-column name="Source" width="15">
	  <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
		  {{row.heading}}
	  </ng-template>
  </ngx-datatable-column>
  <ngx-datatable-column name="C1 " width="5">
	  <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
		  {{row.c1}}
	  </ng-template>
  </ngx-datatable-column>
  <ngx-datatable-column name="C2 " width="5">
	  <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
		  {{row.c2}}
	  </ng-template>
  </ngx-datatable-column>
  <ngx-datatable-column name="C3 " width="5">
	  <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
		  {{row.c3}}
	  </ng-template>
  </ngx-datatable-column>
  <ngx-datatable-column name="C4 " width="5">
	  <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
		  {{row.c4}}
	  </ng-template>
  </ngx-datatable-column>
  <ngx-datatable-column name="C5 " width="5">
	  <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
		  {{row.c5}}
	  </ng-template>
  </ngx-datatable-column>
  <ngx-datatable-column name="Total" width="5">
	  <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
		  {{row.total}}
	  </ng-template>
  </ngx-datatable-column>
</ngx-datatable>

<div *ngIf="!hideHotColumn"><hot-table height="200" [colWidths]="true"  hotId="hotInstance" [settings]="settings" [data]="hotData">
     <hot-column width="170"></hot-column>
     <hot-column width="90"></hot-column>
     <hot-column width="90"></hot-column>
     <hot-column width="90"></hot-column>
     <hot-column width="90"></hot-column>
     <hot-column width="90"></hot-column>
	<hot-column width="90"></hot-column>
     <hot-column width="90"></hot-column>
     <hot-column width="90"></hot-column>
     <hot-column width="90"></hot-column>
     <hot-column width="90"></hot-column>
     <hot-column width="90"></hot-column>
      <hot-column width="90"></hot-column>
      <hot-column width="90"></hot-column>
      <hot-column width="90"></hot-column>
    </hot-table>{{hotData|json}}</div>

<div *ngIf="hideHotColumn">

<ngx-datatable class="material" [rows]="energySaleMultiAddHeader.energySaleMultiAddLines" [columnMode]="'force'" [headerHeight]="50" [scrollbarH]="true"
                [rowHeight]="'auto'">
                
                <ngx-datatable-column name="Service - EDC" width="10">
                    <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
                        {{row.companyServiceNumber}} 
                    </ng-template>
                </ngx-datatable-column>
              
                <ngx-datatable-column name="C1 " width="5">
                    <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
                        {{row.c1}}
                    </ng-template>
                </ngx-datatable-column>
                <ngx-datatable-column name="C2 " width="5">
                    <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
                        {{row.c2}}
                    </ng-template>
                </ngx-datatable-column>
                <ngx-datatable-column name="C3 " width="5">
                    <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
                        {{row.c3}}
                    </ng-template>
                </ngx-datatable-column>
                <ngx-datatable-column name="C4 " width="5">
                    <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
                        {{row.c4}}
                    </ng-template>
                </ngx-datatable-column>
                <ngx-datatable-column name="C5 " width="5">
                    <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
                        {{row.c5}}
                    </ng-template>
                </ngx-datatable-column>
				<ngx-datatable-column name="M.R.C" width="5" *ngIf="isFossilFuel==false" >
					<ng-template let-row="row"  let-value="value" ngx-datatable-cell-template>
						{{row.c001}}
					
					</ng-template>
				</ngx-datatable-column>
				<ngx-datatable-column name="O.M.C" width="5" *ngIf="isFossilFuel==false" >
					<ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
						{{row.c002}}
					</ng-template>
				</ngx-datatable-column> 
					<ngx-datatable-column name="T.R.C" width="5" *ngIf="isFossilFuel==false" >
					<ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
						{{row.c003}}
					</ng-template>
				</ngx-datatable-column>
				<ngx-datatable-column name="S.O.C" width="5" *ngIf="isFossilFuel==false" >
					<ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
						{{row.c004}}
					</ng-template>
				</ngx-datatable-column>
				<ngx-datatable-column name="R.K.P" width="5" *ngIf="isFossilFuel==false" >
					<ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
						{{row.c005}}
					</ng-template>
				</ngx-datatable-column>
				<ngx-datatable-column name="N.E.C" width="5" *ngIf="isFossilFuel==false" >
					<ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
						{{row.c006}}
					</ng-template>
				</ngx-datatable-column>
				<ngx-datatable-column name="S.H.C" width="5" *ngIf="isFossilFuel==false" >
					<ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
						{{row.c007}}
					</ng-template>
				</ngx-datatable-column>
				<ngx-datatable-column name="O.C" width="5" *ngIf="isFossilFuel==false" >
					<ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
						{{row.c008}}
					</ng-template>	
				</ngx-datatable-column>
				<ngx-datatable-column name="P.O.C" width="5" *ngIf="isFossilFuel==false" >
					<ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
						{{row.c009}}
					</ng-template>
				</ngx-datatable-column>
				<ngx-datatable-column name="Is Clean" width="5">
				<ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
				<div *ngIf="row.isClean=='Y'"><mat-icon color="success">check</mat-icon></div>
				<div *ngIf="row.isClean!='Y'"><mat-icon color="warn">close</mat-icon></div>
				</ng-template>
			</ngx-datatable-column>
		
		<ngx-datatable-column name="Remarks" width="15">
                    <ng-template let-row="row" let-value="value" ngx-datatable-cell-template>
                        {{row.importRemarks}}
                    </ng-template>
                </ngx-datatable-column>
            </ngx-datatable>

</div>
<mat-card-actions>
	<button mat-raised-button color="primary" (click)="validate()" >Validate</button>
	<button mat-raised-button color="primary" *ngIf="this.energySaleMultiAddHeader.id != null" (click)="importMultiAdd()" >Add</button>
	<button mat-raised-button color="primary" (click)="reset()" >Reset</button>
	<button mat-raised-button color="primary" (click)="back()" >Back</button>
</mat-card-actions>
</mat-card>
<mat-card-footer> &nbsp;&nbsp;
 <span class = "md-title"><strong>Note</strong></span>
 <span class = "md-subhead"  *ngIf="isFossilFuel">Please feed ServiceNumber and slots only. The Charges will be ignored. </span><br/>
 <span class = "md-title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
<span class = "md-subhead">Already Existing Service number in this energy sale cannot be added</span><br/>
<span class = "md-title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
<span class = "md-subhead">Invalid Service number cannot be added</span><br/>
<span class = "md-title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
<span class = "md-subhead">Service number without traderelationship cannot be added</span>	</mat-card-footer>
	`
})
export class BulkDialogComponent implements OnInit {
	hotData: any;
	datas = [];
	energySaleMultiAddHeader: EnergySaleMultiAddHeader;
	energySaleMultiAddLine: EnergySaleMultiAddLine;
	es: Es;
	gs: Gs; 
	esUsageSummary: EsUsageSummary;
	availableGsUnits: Array<AvailableGsUnits>;
	instance: string = "hotInstance";
	allocationFlag: boolean = false;
	chargeFlag: boolean = false;
	disableConfirm: boolean = true; 
	zero: any = 0;

	minimumChargeFlag: boolean = false; 

	hideHotColumn: boolean = false;
	isFossilFuel: boolean = false;
	settings = {
		colHeaders: ["Service Number", "C1", "C2", "C3", "C4", "C5", "Meter Reading Charges", "O&M Charges", "Transmission Charges", "System Operation Charges", "RKvah Penalty", "Negative Energy Charges", "Scheduling Charges", "Other Charges", "Pareller Operation Charges"],
		afterChange: this.afterChange
	};
	constructor(private router: Router, private datePipe: DatePipe, private esDetailHelper: EsDetailAllocationHelper, private gsService: GsService, public dialogRef: MatDialogRef<BulkDialogComponent>, private _hotRegisterer: HotRegisterer, @Inject(MAT_DIALOG_DATA) public data: any, private service: EsService) {
	}
	ngOnInit() {
		this.es = new Es();
		this.es.energySaleUsageSummaries = Array<EsUsageSummary>();
		this.esUsageSummary = new EsUsageSummary();
		this.availableGsUnits = [];
		this.es.id = this.data.message.id;
		this.es = this.data.message;
		this.energySaleMultiAddHeader = new EnergySaleMultiAddHeader();
		this.energySaleMultiAddLine = new EnergySaleMultiAddLine();
		this.energySaleMultiAddHeader.energySaleMultiAddLines = Array<EnergySaleMultiAddLine>();
		this.energySaleMultiAddHeader.energySaleId = this.es.id;
		this.energySaleMultiAddHeader.generationStatementId = this.es.generationStatementId;

		this.fetchGsById();

		// this.hideHotColumn=true
		this.disableConfirm = true;

	}



	reset() {

		var deleteMultiAdd = confirm("Are you sure you want to reset!");

		if(deleteMultiAdd){
			if (this.energySaleMultiAddHeader.id != null) {
		
				this.service.deleEsMultiAdd(this.energySaleMultiAddHeader.id,this.energySaleMultiAddHeader).subscribe();
	
			}
			this.energySaleMultiAddHeader = new EnergySaleMultiAddHeader();
			this.energySaleMultiAddLine = new EnergySaleMultiAddLine(); 
			this.energySaleMultiAddHeader.energySaleMultiAddLines = Array<EnergySaleMultiAddLine>();
			this.hideHotColumn = false;
			console.log(this.energySaleMultiAddHeader.id);
		} 

		
		
	}
	back() {
		// window.location.reload();

		this.dialogRef.close();

	}

	importMultiAdd(){
		this.service.importMultiAddEs(this.energySaleMultiAddHeader.id, this.energySaleMultiAddHeader).subscribe(x=>{

		})
		this.back();
	}
	validate() {
		const hot = this._hotRegisterer.getInstance(this.instance);
		//WRITE YOUR INSERT LOGIC HERE
		hot.getData().forEach(element => {
			if (element[0] != null) {
				if(this.isFossilFuel){
					this.clearCharges(element);
				}
				this.energySaleMultiAddLine.companyServiceNumber = this.esUsageSummary.buyerCompanyServiceNumber = element[0];
				this.energySaleMultiAddLine.c1 = this.esUsageSummary.c1 = element[1];
				this.energySaleMultiAddLine.c2 = this.esUsageSummary.c2 = element[2];
				this.energySaleMultiAddLine.c3 = this.esUsageSummary.c3 = element[3];
				this.energySaleMultiAddLine.c4 = this.esUsageSummary.c4 = element[4];
				this.energySaleMultiAddLine.c5 = this.esUsageSummary.c5 = element[5];
				this.esUsageSummary.c001ChargeCode = "C001";
				this.energySaleMultiAddLine.c001 = this.esUsageSummary.c001TotalCharge = element[6];
				this.esUsageSummary.c002ChargeCode = "C002";
				this.energySaleMultiAddLine.c002 = this.esUsageSummary.c002TotalCharge = element[7];
				this.esUsageSummary.c003ChargeCode = "C003";
				this.energySaleMultiAddLine.c003 = this.esUsageSummary.c003TotalCharge = element[8];
				this.esUsageSummary.c004ChargeCode = "C004";
				this.energySaleMultiAddLine.c004 = this.esUsageSummary.c004TotalCharge = element[9];
				this.esUsageSummary.c005ChargeCode = "C005";
				this.energySaleMultiAddLine.c005 = this.esUsageSummary.c005TotalCharge = element[10];
				this.esUsageSummary.c006ChargeCode = "C006";
				this.energySaleMultiAddLine.c006 = this.esUsageSummary.c006TotalCharge = element[11];
				this.esUsageSummary.c007ChargeCode = "C007";
				this.energySaleMultiAddLine.c007 = this.esUsageSummary.c007TotalCharge = element[12];
				this.esUsageSummary.c008ChargeCode = "C008";
				this.energySaleMultiAddLine.c008 = this.esUsageSummary.c008TotalCharge = element[13];

				this.esUsageSummary.c009ChargeCode = "C009";
				this.energySaleMultiAddLine.c009 = this.esUsageSummary.c009TotalCharge = element[14];

				this.energySaleMultiAddHeader.energySaleMultiAddLines.push(Object.assign({}, this.energySaleMultiAddLine));
				this.es.energySaleUsageSummaries.push(Object.assign({}, this.esUsageSummary));

			}



		});
		this.gsService.getGsById(this.es.generationStatementId).subscribe(
			_gs => {
				this.gs = _gs;
				var result = this.esDetailHelper.checkAllocatedUnits(this.es, this.gs);
				this.allocationFlag = result["allocation"];

				this.chargeFlag = result["charge"];
				this.es = result["es"];
				console.log(result["es"]);
				if (this.allocationFlag) {
					alert("Cannot update Energy Sale!!! - Error in Allocation ");
					this.dialogRef.close();

				} 
				// else if (this.chargeFlag)
				else if ((!this.isFossilFuel) && this.chargeFlag)
				 {
					alert("Cannot update Energy Sale!!! - Error in Charges ");
					this.dialogRef.close();
				} else {
					this.es.fromDate = this.datePipe.transform(this.es.fromDate, 'yyyy-MM-dd');
					this.es.toDate = this.datePipe.transform(this.es.toDate, 'yyyy-MM-dd');



					this.service.validateMultiAddEs(this.energySaleMultiAddHeader).subscribe(x => {
			
						console.log(x.id)
						if(x.id!=null && x.id!=undefined){
							this.service.getEsMultiAddById(x.id).subscribe(x=>{
								this.energySaleMultiAddHeader=x
								this.hideHotColumn = true;
							});
						}
					});

					// this.service.multiAddEs(this.es).subscribe(result => {

					// 	this.es = result;			
					// 	this.dialogRef.close();

					// })

				}

				// this.availableGsUnitsForPrint();
				// this.allocatedGsUnitsForPrint();
			}
		)




	}

	afterChange(e) {
		if (e) {
			//WRITE YOUR AFTERCHANGE OF CELL EVENTS IF REQUIRED

		}
	}

	fetchGsById() {
		this.gsService.getGsById(this.es.generationStatementId).subscribe(
			_gs => {
				this.gs = _gs;
				if(this.gs.dispFuelTypeGroup=='FF'){
					this.isFossilFuel = true;
				}
				var res = this.esDetailHelper.availableGsUnitsForPrint(this.es, this.gs);
				this.availableGsUnits = res["availableGsUnits"];

			}
		)
	}

	clearCharges(element){
		element[6]=0;
		element[7]=0; 
		element[8]=0; 
		element[9]=0; 
		element[10]=0; 
		element[11]=0; 
		element[12]=0; 
		element[13]=0;  
		element[14]=0; 
	}
	fetchAvailableUnitSummary(){
		if(this.isFossilFuel){
			return this.availableGsUnits.filter((item) => item.heading == "Total");
		}
		else{
			return this.availableGsUnits;
		}
	}
}

@Component({
	selector: 'es-detail',
	templateUrl: './es-detail.component.html',
	styleUrls: ['./es-detail.component.scss'],
	providers: [MatDatepickerModule, MatNativeDateModule, DatePipe]

})
export class EsDetailComponent implements OnInit {
	job: Job;
	es: Es;
	gs: Gs;
	esUsageSummary: EsUsageSummary;
	esUsageDetail: EsUsageDetail;
	// oaas:Array<Oaa>;
	esUsageSummaryRowIndex: number;
	consumerServices = [];
	consumerServicesForQuantum = [];
	consumerCompanies: Array<TradeRelationship>;
	filteredServices = []
	isMandatory = true;
	statusCode: string;
	quantumChanged: any;
	availableGsUnits: Array<AvailableGsUnits>;
	allocatedGsunits: Array<AllocatedGsUnits>;
	allocationFlag: boolean = false;
	chargeFlag: boolean = false;
	minimunAllocationFlag: boolean = false;
	minimumChargeFlag: boolean = false;
	months = [];
	voltages = [];
	generationStatementForPrint: Array<generationStatementForPrint>;
	c1Name: string;
	c2Name: string;
	c3Name: string;
	c4Name: string;
	c5Name: string;
	c1: string; c2: string; c3: string; c4: string; c5: string;
	total: any;
	checkBuyer: boolean = false;
	sumC1: any; sumC2: any; sumC3: any; sumC4: any; sumC5: any; sum: any;
	accessUpdateFlag: boolean = false;
	accessDeleteFlag: boolean = false;
	accessApproveFlag: boolean = false;
	accessCompleteFlag: boolean = false;
	accessProcessFlag: boolean = false;
	isFinalDisabled: boolean = false;
	isFossilFuel: boolean = false;
	disableSave: boolean =false;
	disableAllButtons: boolean =true;
	processingMessage: string = '';
	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private service: EsService,
		private commonService: CommonService,
		private companyService: CompanyService,
		private commonUtils: CommonUtils,
		private esEvent: EsEvent,
		private gsService: GsService,
		private tradeService: TradeRelationshipService,
		// private oaaService: OaaService, 
		private datePipe: DatePipe,
		public dialog: MatDialog,
		private esDetailHelper: EsDetailAllocationHelper
	) {
		this.fetchVoltages();
	}
	isAllowLowerSlotAdj = [
		{ "key": "Y" ,"name":"YES"},
		{ "key": "N" ,"name":"NO" }
	]
	ngOnInit() {
		this.es = new Es();
		this.gs = new Gs();
		this.job = new Job();
		this.accessUpdateFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "UPDATE");
		this.accessDeleteFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "DELETE");
		this.accessApproveFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "APPROVE");
		this.accessCompleteFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "COMPLETE");
		this.accessProcessFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "PROCESS");
		this.esUsageSummary = new EsUsageSummary();
		this.esUsageDetail = new EsUsageDetail();
		this.consumerServices = new Array<TradeRelationship>();

		this.months = this.commonService.fetchMonths();
		this.generationStatementForPrint = [];
		this.availableGsUnits = [];
		this.allocatedGsunits = [];
		
		this.processingMessage = "Please wait. Page Loading ...";
		if (this.route.params['_value']['_id']) {
			//route.params['value'] will have the '_id' in it, during edit 
			this.route.params
				.subscribe((_params: Params) => {
					this.service.getEsById(_params['_id']).subscribe(_es => {
						this.es = _es;
						this.disableAllButtons = true;
						this.disableSave =false;
						var quantum = 0;
						var days = this.commonUtils.getDaysInMonth(parseInt(this.es.month), parseInt(this.es.year));

						this.tradeService.getTradeRelationships("", "", "", this.es.sellerCompanyServiceId, "", "", this.es.month, this.es.year, "", "").subscribe(
							_consumerNames => {
								this.consumerCompanies = _consumerNames;
								console.log(this.consumerCompanies)
								this.consumerServicesForQuantum = this.consumerCompanies;
								this.consumerServicesForQuantum.forEach(trade => {
									this.es.energySaleUsageSummaries.forEach(summary => {

										if (summary.buyerCompanyServiceId == trade.buyerCompServiceId) {


											if (trade.intervalTypeCode == "04") {
												if (trade.sharePercent != null && parseFloat(trade.sharePercent) > 0) {
													quantum = (parseFloat(this.es.sanctionedCapacity) * parseFloat(trade.sharePercent)) / 100;
													summary.quantum = quantum.toString();
													summary.quantum = this.commonUtils.convertQuantumToUnits(summary.quantum);
												}
											}

											if (trade.intervalTypeCode == "01" || trade.intervalTypeCode == "02") {

												if (summary.quantum != null && parseInt(summary.quantum) > 0) {
													summary.quantum = this.commonUtils.calculateProposedQuantum(parseInt(summary.quantum), days).toString();

												}
											}

										}


									})

								})

								// console.log(this.es)
								this.statusCode = this.es.statusCode;

								this.consumerServicesForQuantum

							});

						// this.oaaService.getAllOaas("","","","","","","","","","","").subscribe(
						// 	_Oaas => {
						// 			this.oaas =_Oaas;



						// 	});






						this.fetchGsById();

					});
				});
		}


	}

	getValuesFromDatabase() {
		this.route.params
			.subscribe((_params: Params) => {
				this.service.getEsById(this.es.id).subscribe(_es => {
					this.es = _es;

					var quantum = 0;
					var days = this.commonUtils.getDaysInMonth(parseInt(this.es.month), parseInt(this.es.year));

					this.tradeService.getTradeRelationships("", "", "", this.es.sellerCompanyServiceId, "", "", this.es.month, this.es.year, "", "").subscribe(
						_consumerNames => {
							this.consumerCompanies = _consumerNames;
							// console.log("In trade company2")
							this.consumerServicesForQuantum = this.consumerCompanies;
							this.consumerServicesForQuantum.forEach(trade => {
								this.es.energySaleUsageSummaries.forEach(summary => {

									if (summary.buyerCompanyServiceId == trade.buyerCompServiceId) {


										if (trade.intervalTypeCode == "04") {
											if (trade.sharePercent != null && parseFloat(trade.sharePercent) > 0) {
												quantum = (parseFloat(this.es.sanctionedCapacity) * parseFloat(trade.sharePercent)) / 100;
												summary.quantum = quantum.toString();
												summary.quantum = this.commonUtils.convertQuantumToUnits(summary.quantum);
											}
										}

										if (trade.intervalTypeCode == "01" || trade.intervalTypeCode == "02") {

											if (summary.quantum != null && parseFloat(summary.quantum) > 0) {
												summary.quantum = this.commonUtils.calculateProposedQuantum(parseFloat(summary.quantum), days).toString();

											}
										}

									}


								})

							})

							// console.log(this.es)
							this.statusCode = this.es.statusCode;

							this.consumerServicesForQuantum

						});
					this.fetchGsById();
					var result1 = this.esDetailHelper.checkAllocatedUnits(this.es, this.gs);
					this.es = result1["es"];
					this.allocatedGsunits = result1["allocatedGsunits"];
				});
			});
	}

	fetchGsById() {
		this.gsService.getGsById(this.es.generationStatementId).subscribe(
			_gs => {
				this.gs = _gs;
				if(this.gs.dispFuelTypeGroup=='FF'){
					this.isFossilFuel = true;
					if(CommonUtils.getLoginUserTypeCode() == "GEN"){
						this.accessApproveFlag = false;
					} 
					else if(CommonUtils.getLoginUserTypeCode() == "EDC"){
						this.accessApproveFlag = true;
					}
					if(this.gs.flowTypeCode=='IS-CAPTIVE'){
						this.es.allowLowerSlotAdmt = 'Y'
					}
					if(this.gs.flowTypeCode=='THIRD-PARTY'){
						this.es.allowLowerSlotAdmt = 'N'
					}
				}
				var res =this.esDetailHelper.availableGsUnitsForPrint(this.es,this.gs);
				this.availableGsUnits = res["availableGsUnits"];

				var allocatedResult = this.esDetailHelper.allocatedGsUnitsForPrint(this.es);
				this.allocatedGsunits = allocatedResult["allocated"];
				this.disableAllButtons = false;
			}
		)
	}
	listEss() {
		this.router.navigateByUrl('/es/es-list');

	}

	fetchVoltages() {
		this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
			result => {
				this.voltages = result;
			},
			error => {
				console.error('Error loading Voltages');
				console.error(error);
			});
	}

	fetchAvailableUnitSummary(){
		if(this.isFossilFuel){
			return this.availableGsUnits.filter((item) => item.heading == "Total");
		}
		else{
			return this.availableGsUnits;
		}
	}
	fetchAllocatedUnitSummary(){
		if(this.isFossilFuel){
			return this.allocatedGsunits.filter((item) => item.heading == "Total");
		}
		else{
			return this.allocatedGsunits;
		}
	}
	openConfirmDilog(){
		// let dialogRef =
		 this.dialog.open(EsDialogComponent)
		// let instance = dialogRef.componentInstance;
		// instance.es.id = "Updated text";
		// console.log('dialogRef',dialogRef);

	}


	initAllocations() {
		if (!this.es.energySaleUsageSummaries) {
			this.es.energySaleUsageSummaries = [];
		}
		this.esUsageSummary = new EsUsageSummary();
		this.esUsageSummaryRowIndex = -1;
	}

	checkDuplicateBuyer() {
		this.es.energySaleUsageSummaries.forEach(element => {


			if (this.esUsageSummary.buyerCompanyServiceId == element.buyerCompanyServiceId) {
				this.checkBuyer = true;

			}
		})
	}
	addAllocation() {
		this.allocationFlag = false;
		this.checkDuplicateBuyer();

		if (this.checkBuyer) {
			alert("Cannot add Buyer Already Exist");
			this.initAllocations();
			this.checkBuyer = false;
		} else {

			this.total = (parseInt(this.esUsageSummary.c1) + parseInt(this.esUsageSummary.c2) + parseInt(this.esUsageSummary.c3) + parseInt(this.esUsageSummary.c4) + parseInt(this.esUsageSummary.c5));
			this.esUsageSummary.total = this.total;
			if (parseInt(this.total) > parseInt(this.esUsageSummary.quantum)) {

				alert("Allocated cannot be more than approved");
				this.allocationFlag = true;
				return false;
			} else {
				this.esUsageSummary.energySaleId=this.es.id;
				this.esUsageSummary.total = this.total;
				if (!this.es.energySaleUsageSummaries) {
					this.es.energySaleUsageSummaries = [];
				}
				if (this.esUsageSummary.buyerCompanyServiceId != null) {
					this.es.energySaleUsageSummaries.push(Object.assign({}, this.esUsageSummary));
				}


				var result = this.esDetailHelper.checkAllocatedUnits(this.es, this.gs);
				this.allocationFlag = result["allocation"];
				this.chargeFlag = result["charge"];
				this.es = result["es"];
				this.allocatedGsunits = result["allocatedGsunits"];
				this.initAllocations();
			}
		}



	}
	updateAllocation() {
		this.allocationFlag = false;
		//this.ea.allocations[this.allocationRowIndex-1] = Object.assign({},this.allocation);
		// the above code wasnt updating a row in the table due to somebug
		// as a workaround, creating a new array with same rows except the edited row.
		this.total = (parseInt(this.esUsageSummary.c1) + parseInt(this.esUsageSummary.c2) + parseInt(this.esUsageSummary.c3) + parseInt(this.esUsageSummary.c4) + parseInt(this.esUsageSummary.c5));
		this.esUsageSummary.total = this.total;

		if (parseInt(this.total) > parseInt(this.esUsageSummary.quantum)) {
			alert("Allocated cannot be more than approved");
			this.allocationFlag = true;
			return false;
		} else {
			var _result = this.esDetailHelper.checkSlotsWithCommercials(this.esUsageSummary, this.consumerCompanies);
			var _hasIssue = _result["hasIssue"];
			var _resultMessage = _result["resultMessage"];
			if(_hasIssue){
				alert(_resultMessage);
				return false;
			}
			this.esUsageSummary.total = this.total;
			var tempArray = [];
			for (var index = 0; index < this.es.energySaleUsageSummaries.length; index++) {
				if (this.esUsageSummaryRowIndex == index) {
					tempArray.push(Object.assign({}, this.esUsageSummary));
				}
				else {
					tempArray.push(this.es.energySaleUsageSummaries[index]);
				}
			}
			this.es.energySaleUsageSummaries = tempArray;
			// this.checkAllocatedUnits();
			var result = this.esDetailHelper.checkAllocatedUnits(this.es, this.gs);
			this.allocationFlag = result["allocation"];
			this.chargeFlag = result["charge"];
			this.es = result["es"];
			this.allocatedGsunits = result["allocatedGsunits"];
			this.initAllocations();
		}
	}

	editAllocation(_index: number) {
		this.esUsageSummaryRowIndex = _index;
		this.esUsageSummary = Object.assign({}, this.es.energySaleUsageSummaries[_index]);
		//this.onConsumerChange();
	}
	deleteAllocation(_index: number,esUsageSummary:EsUsageSummary) {
		if(esUsageSummary.id!=null || esUsageSummary.id==undefined){
			// this.service.deleteEsCharge(esUsageSummary);

		this.service.deleteEsUsageSummary(esUsageSummary).subscribe(
		result => { 
		// this.listEss();
		this.es.energySaleUsageSummaries.splice(_index, 1);

		},
		error => {
			console.error('Error updating!');
			console.error(error);
		}
		);

		}else{
			this.es.energySaleUsageSummaries.splice(_index, 1);
		}
		
		// this.recalculateValues();
	}
	onConsumerChange() {
		var days = this.commonUtils.getDaysInMonth(parseInt(this.es.month), parseInt(this.es.year));
		this.consumerCompanies.forEach(element => {
			if (element.intervalTypeCode == '01') {
				element.c1 = (this.commonUtils.calculateC1Units(parseInt(element.quantum), days)).toString();
				element.c2 = (this.commonUtils.calculateC2Units(parseInt(element.quantum), days)).toString();
				element.c3 = (this.commonUtils.calculateC3Units(parseInt(element.quantum), days)).toString();
				element.c4 = (this.commonUtils.calculateC4Units(parseInt(element.quantum), days)).toString();
				element.c5 = (this.commonUtils.calculateC5Units(parseInt(element.quantum), days)).toString();

			}
		});

		// console.log(this.consumerCompanies)
		var quantum = 0;
		this.esUsageSummary.buyerCompanyName = this.consumerCompanies
			.filter((item) => item.buyerCompanyId == this.esUsageSummary.buyerCompanyId)[0].buyerCompanyName;
		this.esUsageSummary.tradeRelationshipId = this.consumerCompanies
			.filter((item) => item.buyerCompanyId == this.esUsageSummary.buyerCompanyId)[0].id;
		this.esUsageSummary.buyerCompanyServiceId = this.consumerCompanies
			.filter((item) => item.buyerCompanyId == this.esUsageSummary.buyerCompanyId)[0].buyerCompServiceId;
		this.esUsageSummary.quantum = this.consumerCompanies
			.filter((item) => item.buyerCompanyId == this.esUsageSummary.buyerCompanyId)[0].quantum;
		this.esUsageSummary.sharePercent = this.consumerCompanies
			.filter((item) => item.buyerCompanyId == this.esUsageSummary.buyerCompanyId)[0].sharePercent;
		this.esUsageSummary.intervalTypeCode = this.consumerCompanies
			.filter((item) => item.buyerCompanyId == this.esUsageSummary.buyerCompanyId)[0].intervalTypeCode;

		if (this.esUsageSummary.intervalTypeCode == "04") {
			if (this.esUsageSummary.sharePercent != null && parseFloat(this.esUsageSummary.sharePercent) > 0) {
				quantum = (parseFloat(this.es.sanctionedCapacity) * parseFloat(this.esUsageSummary.sharePercent)) / 100;

				this.esUsageSummary.quantum = quantum.toString();
				this.esUsageSummary.quantum = this.commonUtils.convertQuantumToUnits(this.esUsageSummary.quantum);
			}
		}

		if (this.esUsageSummary.intervalTypeCode == "01" || this.esUsageSummary.intervalTypeCode == "02") {

			if (this.esUsageSummary.quantum != null && parseInt(this.esUsageSummary.quantum) > 0) {
				this.esUsageSummary.quantum = this.commonUtils.calculateProposedQuantum(parseInt(this.esUsageSummary.quantum), days).toString();

			}
		}

		if (this.esUsageSummary.quantum == "0" && this.esUsageSummary.quantum == null) {
			alert("Consumer Data issue - Quantum data is not available");
			this.initAllocations();
		} else if (this.esUsageSummary.sharePercent == "0" && this.esUsageSummary.sharePercent == null) {
			alert("Consumer Data issue - Share Percent data is not available");
			this.initAllocations();
		} else {

			//this.quantumChanged = parseFloat (this.esUsageSummary.quantum )* 720 ;

			//this.esUsageSummary.quantum = this.quantumChanged.toString() ;

			this.esUsageSummary.buyerCompanyId = this.consumerCompanies
				.filter((item) => item.buyerCompanyId == this.esUsageSummary.buyerCompanyId)[0].buyerCompanyId;

			var assignTotal = 0;
			this.esUsageSummary.total = assignTotal.toString();
		}


	}
	saveEs() {
		// this.disableSave=true;
		this.disableAllButtons = true;
		this.processingMessage = "Please wait. Energy Allotment is saving ...";
		var result=this.esDetailHelper.checkMinumumAllocatedUnits(this.es,this.gs);
		// this.minimunAllocationFlag = result["minimunAllocation"];
		this.minimumChargeFlag = result["minimumCharge"];
		//save can be add or update
		this.formatChangesforDB();
		this.es.simpleEnergySale = 'N';
		this.es.usageDetailAvail = 'Y';
		//this.es.multipleBuyers = 'Y';
		console.log("this.es",this.es);

		if(this.es.allowLowerSlotAdmt==null){
			alert("Update the higher order to lower order slot and then proceed ");	
			this.disableAllButtons=false;
			return false;
		}
		if (this.allocationFlag) {
			alert("Cannot update Energy Allotment!!! - Error in Allocation of Units ");	
			this.disableAllButtons=false;
			return false;
		} else if ((!this.isFossilFuel) && this.chargeFlag) {
			alert("Cannot update Energy Allotment!!! - Error in Allocation of Charges ");	
			this.disableAllButtons=false;
			return false;
		} 
		// else if (this.minimunAllocationFlag) {
		// 	alert("Cannot update Energy Allotment!!! - Allocated Units cannot be more than available Units")
		// }
		 else if ((!this.isFossilFuel) && this.minimumChargeFlag) {
			alert("Cannot update Energy Allotment!!! - Allocated charges cannot be less than available charges");	
			this.disableAllButtons=false;
			return false;
		}  else {
			if(this.isFossilFuel){
				var _result = this.esDetailHelper.checkSlotsWithCommercialsForAll(this.es.energySaleUsageSummaries, this.consumerCompanies);
				var _hasIssue = _result["hasIssue"];
				var _resultMessage = _result["resultMessage"];
				if(_hasIssue){
					alert(_resultMessage);
					this.disableAllButtons = false;
					return false;
				}

			}

			if (this.es.id == '') {
				if(this.es.allowLowerSlotAdmt==null){
					alert("Update the higher order to lower order slot and then proceed ");	
					this.disableAllButtons=false;
					return false;	
				}
				this.addEs();
			}
			else {
				this.updateEs();
			}
		}
	}

	formatChangesforDB() {
		this.es.fromDate = this.datePipe.transform(this.es.fromDate, 'yyyy-MM-dd');
		this.es.toDate = this.datePipe.transform(this.es.toDate, 'yyyy-MM-dd');
		this.esUsageDetail.usageDate = this.datePipe.transform(this.esUsageDetail.usageDate, 'yyyy-MM-dd');
	}

	addEs() {
		// this.service.addEs(this.ea).subscribe(
		// result => { 
		// this.ea._id = result;

		// this.listEss();
		// },
		// error => {

		// }
		// );
	}

	resetEss(){	
		this.disableAllButtons=true;
		this.processingMessage = "Please wait. Energy Allotment is resetting ...";
		this.es.energySaleUsageSummaries.forEach(element => {
			element.c1='0';
			element.c2='0';
			element.c3='0';
			element.c4='0';
			element.c5='0';
			element.c001TotalCharge='0';
			element.c002TotalCharge='0';
			element.c003TotalCharge='0';
			element.c004TotalCharge='0';
			element.c005TotalCharge='0';
			element.c006TotalCharge='0';
			element.c007TotalCharge='0';
			element.c008TotalCharge='0';	
			element.c009TotalCharge='0';	
			element.total='0';
		});
		
		 this.es.gc1='0';
		 this.es.gc2='0';
		 this.es.gc3='0';
		 this.es.gc4='0';
		 this.es.gc5='0';
		 this.es.bc1='0';	
		 this.es.bc2='0';
		 this.es.bc3='0';
		 this.es.bc4='0';
		 this.es.bc5='0';
		 this.es.c1='0';
		 this.es.c2='0';
		 this.es.c3='0';
		 this.es.c4='0';
		 this.es.c5='0';
		this.allocatedGsunits[0].c1='0';
		this.allocatedGsunits[1].c1='0';
		this.allocatedGsunits[2].c1='0';
		this.allocatedGsunits[0].c2='0';
		this.allocatedGsunits[1].c2='0';
		this.allocatedGsunits[2].c2='0';
		this.allocatedGsunits[0].c3='0';
		this.allocatedGsunits[1].c3='0';
		this.allocatedGsunits[2].c3='0';
		this.allocatedGsunits[0].c4='0';
		this.allocatedGsunits[1].c4='0';
		this.allocatedGsunits[2].c4='0';
		this.allocatedGsunits[0].c5='0';
		this.allocatedGsunits[1].c5='0';
		this.allocatedGsunits[2].c5='0';
		this.allocatedGsunits[0].total='0';
		this.allocatedGsunits[1].total='0';
		this.allocatedGsunits[2].total='0';
		this.formatChangesforDB();
		this.updateEs() ;
		//console.error(this.updateEs());
	}

	updateEs() {


		if (this.allocationFlag) {
			alert("Cannot update Energy Sale!!! - Error in Allocation ")	
			this.disableAllButtons=false;
			return false;
		} else {
			if(this.es.allowLowerSlotAdmt==null){
				alert("Update the higher order to lower order slot and then proceed ")		
				this.disableAllButtons=false;
				return false;
			}
			this.service.updateEs(this.es).subscribe(
				result => {
					console.log(this.es);
					this.disableAllButtons=false;
					this.listEss();

				},
				error => {
					console.error('Error updating company!');
					console.error(error);
					this.disableAllButtons=false;
				}
			);
		}
	}
	finalEs() {
		this.disableAllButtons = true;
		this.processingMessage = "Please wait. Energy Allotment is finalising ...";
		//save can be add or update
		this.formatChangesforDB();
		if (this.allocationFlag) {
			alert("Cannot Approve Energy Sale!!! - Error in Allocation ")
			this.disableAllButtons = false;
		} else {
			this.openConfirmDilog();
			// this.service.finalEs(this.es).subscribe(
			this.job.tEnergySaleId=this.es.id;
			// console.log(this.job)
			this.service.triggerEnergySaleEvent(this.job).subscribe(
			result => {
					this.disableAllButtons=false;
					this.listEss();
				},
				error => {
					console.error('Error updating final!');
					console.error(error);
					this.disableAllButtons = false;
				}
			);
		}

	}
	dialogRef: MatDialogRef<BulkDialogComponent> | null;
	lastCloseResult: string;
	config = {
		disableClose: false,
		panelClass: 'custom-overlay-pane-class',
		hasBackdrop: true,
		backdropClass: '',
		width: '1580px',
		height: '650px',
		position: {
			top: '',
			bottom: '',
			left: '',
			right: ''
		},
		data: {
			message: this.es,
		}
	};
	numTemplateOpens = 0;
	bulkAllocation() {
		this.config.data = { "message": this.es }
		this.dialogRef = this.dialog.open(BulkDialogComponent, this.config);

		this.dialogRef.afterClosed().subscribe((result: string) => {
			this.lastCloseResult = result;
			this.dialogRef = null;

			this.getValuesFromDatabase();
			// this.router.navigateByUrl('/es/es-list');
		});
	}
}

