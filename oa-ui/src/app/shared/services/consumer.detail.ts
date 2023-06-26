import {Component, Input, Output, EventEmitter} from '@angular/core'

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
	addresses? : any[];	
}
export interface ConsumerDetails{
	number?: string;
	companyName?: string;
	orgCode?: string;
	orgName?: string;
	drawalVoltage?: string;
	serviceAddress? : string;
}

@Component({
  selector: 'consumer-detail',
  template: `
	
	<div fxLayout="row" fxLayoutAlign="start center" >
		<mat-select  class="ml-xs mr-xs" fxFlex.gt-sm="100%" placeholder="Service - Company" (change)="setServiceNames($event)" [(ngModel)]='conDetails.number'>
			<mat-option *ngFor="let service of services" [value]="service.number">
				{{ service.number +" - "+service.companyId }}
			</mat-option>
		</mat-select>
		
		<mat-form-field class="ml-xs mr-xs" fxFlex.gt-sm="100%" *ngIf="inArray('edcName')">
		  <input matInput placeholder="EDC Name" [disabled]="true" [(ngModel)]='conDetails.orgName'>
		</mat-form-field>
		
		<mat-form-field class="ml-xs mr-xs" fxFlex.gt-sm="100%" *ngIf="inArray('drawalVoltage')">
			  <input matInput placeholder="Drawal Voltage" [disabled]="true" [(ngModel)]='conDetails.drawalVoltage' >
		</mat-form-field> 

		<mat-form-field class="ml-xs mr-xs" fxFlex.gt-sm="100%" *ngIf="inArray('serviceAddress')">
				<input matInput placeholder="Address" [disabled]="true" [(ngModel)]='conDetails.serviceAddress'>
		</mat-form-field>
	</div>

	`
})
export class ConsumerDetail {
	@Input() services: Service[];
	@Input() conDetails: ConsumerDetails;
	@Input() csDisplayUnits?: any[];

	setServiceNames(_service){
		this.services.forEach(service => {
			if(service.number == _service.value){
				this.conDetails['orgName'] = service.orgName;
				this.conDetails['drawalVoltage'] = service.drawalVoltage;
				
				if(service.addresses && service.addresses.length > 0){
						this.conDetails.serviceAddress = this.joinAddressToString(service.addresses[0]);
				}

			}
		});
	}

	joinAddressToString(array) {
    var str = '';
    if (array && array.addressNo) {
      str = array.addressNo + ', ' + array.line1 + ', ' + array.city
    }
		return str;
  }

	inArray(val){
		return this.csDisplayUnits && this.csDisplayUnits.indexOf(val) >= 0;
	}
}