import { Component, OnInit, HostBinding, Input } from '@angular/core';

import { FormControl } from '@angular/forms';
import { Gc,QuantumAllocation} from './../../services/gc';
import { GcService } from './../../services/gc.service';
import { SignupService } from './../../../master/signup/services/signup.service';
import { CommonService } from './../../../shared/common/common.service';

@Component({
  selector: 'gc-quantum-allocation',
  templateUrl: './gc-quantum-allocation.component.html',
  providers: [SignupService]

})
export class GcQuantumAllocationComponent implements OnInit {

    gc: Gc;
    callFlag: boolean = false;
    quantumAllocation: QuantumAllocation;
    quantumAllocationRowIndex: number;

    @Input() voltages = [];
	@Input() orgList = [];
	
    compCtrl: FormControl;
    buyerCompanyCode: String;
    buyerCompanyName: String;
    reactiveComp: any;
    disableControls: boolean = false;    
    buyerCompanyServices = [];
    filteredServices = [];


    constructor(private gcService: GcService, private service: SignupService,private commonService: CommonService) {
        this.compCtrl = new FormControl({
            companyCode: this.buyerCompanyCode,
            companyName: this.buyerCompanyName
        });

        // this.fetchBuyerCompanyServices("");

        this.gcService.gcEvent.subscribe(_gc => {
            this.gc = _gc;
        })

    }
    onInjectionVoltageSelect(voltage) {
        this.quantumAllocation.injectionVoltageCode = voltage.valueCode;
        this.quantumAllocation.injectionVoltageName = voltage.valueDesc;
    }
    onDrawalVoltageSelect(voltage) {

        this.quantumAllocation.drawalVoltageCode = voltage.valueCode;
        this.quantumAllocation.drawalVoltageName = voltage.valueDesc;
    }

    ngOnInit() {
        this.gc = new Gc();
        this.quantumAllocation = new QuantumAllocation();
        this.initCaptiveQuantumAllocation();
    }

    displayFn(value: any): string {
        return value && typeof value === 'object' ? value.name : value;
    }
    filterBuyerCompanyServices(val: string) {
        return val ? this.buyerCompanyServices.filter((s) => s.companyName.match(new RegExp(val, 'gi'))) : this.buyerCompanyServices;
    }

    fetchBuyerCompanyServices(orgId: string) {
        this.commonService.getBuyerCompanyServicesByOrgId(orgId).subscribe(
            result => {
                this.buyerCompanyServices = result;
                console.log("fetchBuyerCompanyServices")

                console.log(result)
                this.compCtrl = new FormControl({
                    companyCode: this.buyerCompanyCode,
                    companyName: this.buyerCompanyName
                });
                this.reactiveComp = this.compCtrl.valueChanges.startWith(this.compCtrl.value)
                    .map(val => this.displayFn(val))
                    .map(companyName => this.filterBuyerCompanyServices(companyName));
            },
            error => {
                console.error('Error loading company!');
                console.error(error);
            });
    }

    onConsumerChange() {
        this.filteredServices = this.buyerCompanyServices
            .filter((item) => item.serviceId == this.quantumAllocation.buyerCompServiceId);
            console.log("filteredServices")

            console.log(this.filteredServices)
        this.quantumAllocation.buyerCompServiceNumber = this.filteredServices[0].serviceNumber;
        this.quantumAllocation.buyerCompServiceName = this.filteredServices[0].companyName;
        this.quantumAllocation.buyerCompServiceId = this.filteredServices[0].serviceId;
        this.quantumAllocation.captiveCompanyName = this.filteredServices[0].companyName;
        this.quantumAllocation.drawalVoltageName = this.filteredServices[0].voltageName;
    }

    initCaptiveQuantumAllocation() {
        if (!this.gc.captiveQuantumAllocation) {
            this.gc.captiveQuantumAllocation = [];
        }
        this.quantumAllocation = new QuantumAllocation();
        this.quantumAllocationRowIndex = -1;
    }

    addCaptiveQuantumAllocation() {
        if (!this.gc.captiveQuantumAllocation) {
            this.gc.captiveQuantumAllocation = [];
        }

        this.gc.captiveQuantumAllocation.push(Object.assign({}, this.quantumAllocation));
        this.gcService.setGc(this.gc);

        this.initCaptiveQuantumAllocation();
    }

    updateCaptiveQuantumAllocation() {
        let tempArray = [];
        for (let index = 0; index < this.gc.captiveQuantumAllocation.length; index++) {
            if (this.quantumAllocationRowIndex == index) {
                tempArray.push(Object.assign({}, this.quantumAllocation));
            } else {
                tempArray.push(this.gc.captiveQuantumAllocation[index]);
            }
        }

        this.gc.captiveQuantumAllocation = tempArray;
        this.gcService.setGc(this.gc);

        this.initCaptiveQuantumAllocation();
    }


    editCaptiveQuantumAllocation(_index: number) {
        this.quantumAllocationRowIndex = _index;
        this.quantumAllocation = Object.assign({}, this.gc.captiveQuantumAllocation[_index]);
    }

    deleteCaptiveQuantumAllocation(_index: number) {
        this.gc.captiveQuantumAllocation.splice(_index, 1);
    }
}