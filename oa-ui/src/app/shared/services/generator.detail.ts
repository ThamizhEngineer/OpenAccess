import { Component, Input, Output, EventEmitter } from '@angular/core'

export interface Service {
	type: string; //Generator, Consumer
	companyId?: string;
	companyName?: string; //used for view only
	number: string;
	org?: any[];
	orgCode: string;
	orgName: string;
	sanctionedQuantum: string;
	tariff: string;
	proposedSupply?: string; //used for view only
	installedCapacity?: string; //used for view only
	drawalVoltage: string; // applicable for Consumer only
	refNumber: string; // Generator-->GridAppln#, Consumer-->None
	enabled: boolean;
	addresses?: any[];
	
}
export interface GeneratorDetails {
	number?: string;
	companyName?: string;
	orgCode?: string;
	orgName?: string;
	drawalVoltage?: string;
}


@Component({
	selector: 'generator-detail',
	template: `
	<div mat-subheader>Generator Details</div><br>
		<div fxLayout="row" fxLayoutAlign="start center">
			<mat-select class="ml-xs mr-xs" fxFlex.gt-sm="100%" placeholder="Service - Company" (change)="setServiceNames($event)" [(ngModel)]='genDetails.number'>
				<mat-option *ngFor="let service of services" [value]="service.number">
					{{ service.number +" - "+service.companyId }}
				</mat-option>
			</mat-select>

			<mat-form-field class="ml-xs mr-xs" fxFlex.gt-sm="100%" *ngIf="inArray('edcName')">
				<input matInput placeholder="EDC Name" [disabled]="true" [(ngModel)]='genDetails.orgName'>
			</mat-form-field>

		</div><br/>

		<div fxLayout="row" fxLayoutAlign="start center">
			<mat-form-field class="ml-xs mr-xs" fxFlex.gt-sm="100%" *ngIf="inArray('injectionVoltage')">
				<input matInput placeholder="Injection Voltage" [(ngModel)]='genDetails.injectionVoltage'>
			</mat-form-field>

			<mat-form-field class="ml-xs mr-xs" fxFlex.gt-sm="100%" *ngIf="inArray('installedCapacity')">
				<input matInput placeholder="Installed capacity" [(ngModel)]='genDetails.installedCapacity'>
			</mat-form-field>

			<mat-form-field class="ml-xs mr-xs" fxFlex.gt-sm="100%" *ngIf="inArray('proposedSupply')">
				<input matInput placeholder="Proposed Supply (in MW)" [(ngModel)]='genDetails.proposedSupply'>
			</mat-form-field>

			<mat-form-field class="ml-xs mr-xs" fxFlex.gt-sm="100%" *ngIf="inArray('drawalVoltage')">
				<input matInput placeholder="Supply Proposed to be received from CGP/3rd Party (in MW)" [(ngModel)]='genDetails.drawalVoltage'>
			</mat-form-field>
			
			<mat-form-field class="ml-xs mr-xs" fxFlex.gt-sm="100%" *ngIf="inArray('tariff')">
				<input matInput placeholder="Quantum of supply proposed to be received through open access (in MW)" [(ngModel)]='genDetails.tariff'>
			</mat-form-field>
			
		</div><br/>
  `
})
export class GeneratorDetail {
	@Input() services: Service[];
	@Input() genDetails: GeneratorDetails;
	@Input() gsDisplayUnits?: any[];

	subStations = [
		{ "key": "TNEB - Erode", "name": "TNEB - Erode" },
		{ "key": "TNEB - Kanchipuram", "name": "TNEB - Kanchipuram" },
		{ "key": "TNEB - Thuuthukudi", "name": "TNEB - Thuuthukudi" },
		{ "key": "TNEB - Nagercoil", "name": "TNEB - Nagercoil" }
	];

	setServiceNames(_service) {
		this.services.forEach(service => {
			if (service.number == _service.value) {
				this.genDetails['orgName'] = service.orgName;
			}
		});
	}

	inArray(val) {
		return this.gsDisplayUnits && this.gsDisplayUnits.indexOf(val) >= 0;
	}
}