import { Component, OnInit, HostBinding, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Gc, GeneratorUnit } from './../../services/gc';
import { CommonService } from '../../../shared/common/common.service';
import { GcService } from './../../services/gc.service';

@Component({
  selector: 'gc-infra',
  templateUrl: './gc-infra.component.html',
  //styleUrls: [],
  providers: [CommonService]

})
export class GcInfraComponent implements OnInit {

    gc: Gc;

    reactiveEDCNames: any;
    EDCNameCtrl: FormControl;


    ssTypes = [{
            "key": "NCES-SS",
            "name": "NCES-SS"
        },
        {
            "key": "EDC-SS",
            "name": "EDC-SS"
        },
        {
            "key": "WFP-SS",
            "name": "WFP-SS"
        },
        {
            "key": "10 (1) SS",
            "name": "10 (1) SS"
        }
    ];

    feeders = [{
            "key": "Owned",
            "name": "Owned"
        },
        {
            "key": "Dedicated",
            "name": "Dedicated"
        }
    ];

    @Input() orgList = [];
    filteredOrgList = [];
    substationList = [];
    filteredSubstationList = [];
    feederList = [];
    filteredFeederList = [];
    isDisabled = false;
    isMandatory = false;

    constructor(private commonService: CommonService, private gcService: GcService) {
        this.gcService.gcEvent.subscribe(_gc => {
            this.gc = _gc;
        })
    }

    ngOnInit() {
        this.gc = new Gc();
        this.initInfrastructure();
        this.fetchSubstationsByOrgId("");
        this.fetchFeeders("");
    }

    filterFeeders(val: string) {

        return val ? this.feederList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.feederList;
    }

    fetchFeeders(substationId: string) {
        if (substationId == "") {
            return false;
        }
        this.commonService.fetchFeeders(substationId).subscribe(
            result => {
                this.feederList = result;
            },
            error => {
                console.error('Error loading feeders!');
                console.error(error);
            });
    }


    onOrgSelect(org) {
        this.gc.finalOrgName = org.name;
        this.gc.finalOrgId = org.id;
        this.fetchSubstationsByOrgId(this.gc.finalOrgId);

    }

    onSubstationSelect(substation) {
        this.gc.finalSsName = substation.name;
        this.gc.finalSsId = substation.id;
        this.fetchFeeders(this.gc.finalSsId);
    }

    onFeederSelect(feeder) {
        this.gc.finalFeederName = feeder.name;
        this.gc.finalFeederId = feeder.id;
    }
    displayFn(value: any): string {
        return value && typeof value === 'object' ? value.name : value;
    }
    filterSubstations(val: string) {
        return val ? this.substationList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.substationList;
    }

    filterOrgs(val: string) {
        return val ? this.orgList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.orgList;
    }

    fetchSubstationsByOrgId(orgId: string) {
        if (orgId == "") {
            return false;
        }

        this.commonService.fetchSubstationsByOrgId(orgId).subscribe(
            result => {
                this.substationList = result;
            },
            error => {
                console.error('Error loading EDC!');
                console.error(error);
            });
    }
	
    initInfrastructure() {
        if (!this.gc.infrastructure) {
            this.gc.infrastructure = [];
        }
    }

}