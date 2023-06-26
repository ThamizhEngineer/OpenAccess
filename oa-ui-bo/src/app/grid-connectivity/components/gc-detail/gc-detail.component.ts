import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { CommonUtils } from '../../../shared/common/common-utils';

import { GcService } from './../../services/gc.service';
//import { CompanyService } from './../../../master/company/services/company.service';

import { CommonService } from './../../../shared/common/common.service';
import { Gc } from './../../services/gc';

import { GcInvestmentDetailComponent } from './../../components/gc-investment-detail/gc-investment-detail.component';
import { GcQuantumAllocationComponent } from './../../components/gc-quantum-allocation/gc-quantum-allocation.component';
import { DatePipe } from '@angular/common';
import { MatDatepickerModule, MatNativeDateModule, NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from './../../../shared/common/date.adapter';

import { NG_VALIDATORS, Validator, Validators, AbstractControl, ValidatorFn, FormsModule } from '@angular/forms';

@Component({
    selector: 'gc-detail',
    templateUrl: './gc-detail.component.html',
    //styleUrls: [],
    providers: [GcService, CommonUtils, GcInvestmentDetailComponent, GcQuantumAllocationComponent, MatDatepickerModule, MatNativeDateModule, DatePipe,
        { provide: DateAdapter, useClass: AppDateAdapter },
        { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
    ]
})

export class GcDetailComponent implements OnInit {

    gc: Gc;
    testStr: string = "";
    isWind: boolean = false;
    hideCaptive: boolean = false;
    hideApprove: boolean = true;

    orgList = [];
    filteredOrgList = [];

    gcStatuses = [{
            "key": "Initiated",
            "name": "Initiated"
        },
        {
            "key": "Applied",
            "name": "Applied"
        },
        {
            "key": "Approved",
            "name": "Approved"
        },
        {
            "key": "Activated",
            "name": "Activated"
        }
    ];

    nextGcStatus: string = 'Applied';
    nextAction: string = 'Apply';
    disableControls: boolean = true;

    availingHTSupply = [{
            "key": "Y",
            "name": "Y"
        },
        {
            "key": "N",
            "name": "N"
        }
    ];

    rows = [{
            fuelSource: '',
            linkageFrom: '',
            linkageTo: '',
            rate: '',
            docUrl: ''
        },
        {
            fuelSource: '',
            linkageFrom: '',
            linkageTo: '',
            rate: '',
            docUrl: ''
        },
        {
            fuelSource: '',
            linkageFrom: '',
            linkageTo: '',
            rate: '',
            docUrl: ''
        }
    ];

    substationList = [];
    filteredSubstationList = [];
    voltageList = [];
    filteredVoltageList = [];
    voltages = [];
    states = [];
    district = [];
    taluk = [];
    districtList = [];
    filteredDistrictList = [];
    talukList = [];
    filteredTalukList = [];
    plDistrictList = [];
    filteredPlDistrictList = [];
    plTalukList = [];
    filteredPlTalukList = [];
	generatorModelTypes = []
	meterMake = []
	checkListCodes = []

    stateList = [];
    filteredStateList = [];
    rowIndex: number;
    addScreen: boolean = true;

    accessUpdateFlag: boolean = false;
    accessDeleteFlag: boolean = false;
    accessApproveFlag: boolean = false;
    accessCompleteFlag: boolean = false;
    accessProcessFlag: boolean = false;
    accessCreateFlag: boolean = false;

    constructor(
        private commonUtils: CommonUtils,
        private route: ActivatedRoute,
        private router: Router,
        private service: GcService,
        //private companyService: CompanyService,
        private commonService: CommonService,
        // private gcEvent: GcEvent
        private gcInvestment: GcInvestmentDetailComponent,
        private datePipe: DatePipe
    ) {

    }

    ngOnInit() {
        this.gc = new Gc();
        this.accessUpdateFlag = this.commonUtils.userHasAccess("GRID-CONNECTIVITY-APPLICATION", "UPDATE");
        this.accessDeleteFlag = this.commonUtils.userHasAccess("GRID-CONNECTIVITY-APPLICATION", "DELETE");
        this.accessApproveFlag = this.commonUtils.userHasAccess("GRID-CONNECTIVITY-APPLICATION", "APPROVE");
        this.accessCompleteFlag = this.commonUtils.userHasAccess("GRID-CONNECTIVITY-APPLICATION", "COMPLETE");
        this.accessProcessFlag = this.commonUtils.userHasAccess("GRID-CONNECTIVITY-APPLICATION", "PROCESS");

        this.fetchOrgList();
        this.fetchSubstationsByOrgId("");
        this.fetchCodes();

        this.service.gcEvent.subscribe(_gc => {
            this.gc = _gc;

        });
        //this.setCompanyNames();

        if (this.route.params['_value']['_id']) {
            //route.params['value'] will have the '_id' in it, during edit 
            this.addScreen = false;
            this.route.params
                .switchMap((params: Params) => this.service.getGcById(params['_id']))
                .subscribe((_gc: Gc) => {
                    this.service.setGc(_gc);
                    this.gc = _gc;
                    
                    if (this.gc.generatingTypeIsCaptive != "Y") {
                        this.hideCaptive = true;
                    }
                    let approve = true;
                    if (this.gc.applicationStatus != null) {
                        this.gc.applicationStatus.forEach(function(loop) {

                            if (loop.gcStatusTypeCode == "Approval For Taking Up Work") {
                                approve = false;

                            }
                        });
                    }
                    this.hideApprove = approve;

                    if (CommonUtils.getLoginUserTypeCode() == "GEN" || "GEN-COMMON") {
                        this.hideApprove = true;

                        let accessCreate = true;
                        if (this.gc.applicationStatus != null) {
                            this.gc.applicationStatus.forEach(function(loop) {

                                if (loop.gcStatusTypeCode != null) {
                                    accessCreate = false;

                                }
                            });
                        }
                        this.accessCreateFlag = accessCreate;
                    }

                    if (!(CommonUtils.getLoginUserTypeCode() == "GEN" || CommonUtils.getLoginUserTypeCode() == "GEN-COMMON")) {
                        this.accessCreateFlag = false;
                        this.hideApprove = false;
                    }

                    this.formatChangesforUI();
                });
        }
    }

    onOrgSelect(org) {
        this.gc.orgId = org.id;
        this.gc.orgName = org.name;
        this.fetchSubstationsByOrgId(this.gc.orgId);
    }

    onSubstationSelect(substaion) {
        this.gc.ssCode = substaion.code;
        this.gc.ssName = substaion.name;
        this.gc.ssId = substaion.id;
    }

    onVoltageSelect(voltage) {
        this.gc.ssVoltageCode = voltage.valueCode;
        this.gc.ssVoltageName = voltage.valueDesc;
    }
    onDistrictSelect(district) {

        this.gc.districtCode = district.valueCode;
        this.gc.districtName = district.valueDesc;
    }
    onPlDistrictSelect(district) {
        this.gc.plDistrictCode = district.valueCode;
        this.gc.plDistrictName = district.valueDesc;
    }
    onTalukSelect(taluk) {
        this.gc.talukCode = taluk.valueCode;
        this.gc.talukName = taluk.valueDesc;
    }
    onPlTalukSelect(taluk) {
        this.gc.plTalukCode = taluk.valueCode;
        this.gc.plTalukName = taluk.valueDesc;
    }
    onStateSelect(state) {
        this.gc.stateCode = state.valueCode;
        this.gc.stateName = state.valueDesc;
    }
    onChange(val) {
        this.service.setGc(this.gc);
    }
    onParallelChargeSelect(val) {
        this.service.setGc(this.gc);
    }

    setNextStatus(status: string) {
        switch (status) {
            case 'Initiated':
                this.nextGcStatus = 'Applied';
                this.nextAction = 'Apply';
                break;
            case 'Applied':
                this.nextGcStatus = 'Approved';
                this.nextAction = 'Approve';
                break;
            case 'Approved':
                this.nextGcStatus = 'Activated';
                this.nextAction = 'Activate';
                break;
            case 'Activated':
                this.nextGcStatus = '';
                this.nextAction = 'Activated';
                break;

            default:
                break;
        }
        this.disableControls = (this.nextGcStatus === '') ? true : false;

    }

    filterOrgList(val: string) {
        return val ? this.orgList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.orgList;
    }

    fetchOrgList() {
        this.commonService.fetchEDCs().subscribe(
            result => {
                this.orgList = result;
            },
            error => {
                console.error('Error loading states!');
                console.error(error);
            }
        );
    }

    filterSubstations(val: string) {
        return val ? this.substationList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.substationList;
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
	
	fetchCodes(){
		this.commonService.fetchCodes().subscribe(
		result => {
			this.voltages = result.filter(x=>x.listCode == "VOLTAGE_CODE");
			this.talukList = result.filter(x=>x.listCode == "TALUK_CODE");
			this.plTalukList = result.filter(x=>x.listCode == "TALUK_CODE");
			this.districtList = result.filter(x=>x.listCode == "DISTRICT_CODE");
			this.plDistrictList = result.filter(x=>x.listCode == "DISTRICT_CODE");
			this.stateList = result.filter(x=>x.listCode == "STATE_CODE");
			this.generatorModelTypes = result.filter(x=>x.listCode == "GENERATOR_MAKE_CODE");
			this.meterMake = result.filter(x=>x.listCode == "METER_MAKE_CODE");
			this.checkListCodes = result.filter(x=>x.listCode == "CHECK_LIST_CODE");
		})
	}

    filterVoltages(val: string) {
        return val ? this.voltageList.filter((s) => s.valueDesc.match(new RegExp(val, 'gi'))) : this.voltageList;
    }

    filterTaluk(val: string) {
        return val ? this.talukList.filter((s) => s.valueDesc.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.talukList;
    }
    filterPlTaluk(val: string) {
        return val ? this.plTalukList.filter((s) => s.valueDesc.match(new RegExp(val, 'gi'))) : this.plTalukList;
    }
    
    filterDistrict(val: string) {
        return val ? this.districtList.filter((s) => s.valueDesc.match(new RegExp(val, 'gi'))) : this.districtList;
    }

    filterPlDistrict(val: string) {
        return val ? this.plDistrictList.filter((s) => s.valueDesc.match(new RegExp(val, 'gi'))) : this.plDistrictList;
    }

    filterState(val: string) {
        return val ? this.stateList.filter((s) => s.valueDesc.match(new RegExp(val, 'gi'))) : this.stateList;
    }

    formatChangesforUI() {
        this.gc.proposedCommissionDate = (this.gc.proposedCommissionDate) ? this.gc.proposedCommissionDate.substr(0, 10) : "";
        this.gc.finalStbTenderDate = (this.gc.finalStbTenderDate) ? this.gc.finalStbTenderDate.substr(0, 10) : "";
        this.gc.finalWheelingFromDate = (this.gc.finalWheelingFromDate) ? this.gc.finalWheelingFromDate.substr(0, 10) : "";
        this.gc.finalWheelingToDate = (this.gc.finalWheelingToDate) ? this.gc.finalWheelingToDate.substr(0, 10) : "";
        this.gc.genServiceDate = (this.gc.genServiceDate) ? this.gc.genServiceDate.substr(0, 10) : "";
        this.gc.finalCod = (this.gc.finalCod) ? this.gc.finalCod.substr(0, 10) : "";
        this.gc.finalCopd = (this.gc.finalCopd) ? this.gc.finalCopd.substr(0, 10) : "";
    }

    formatChangesforDB() {
        this.gc.finalCod = this.datePipe.transform(this.gc.finalCod, 'yyyy-MM-dd');
        this.gc.finalCopd = this.datePipe.transform(this.gc.finalCopd, 'yyyy-MM-dd');
        this.gc.proposedCommissionDate = this.datePipe.transform(this.gc.proposedCommissionDate, 'yyyy-MM-dd');
        this.gc.finalStbTenderDate = this.datePipe.transform(this.gc.finalStbTenderDate, 'yyyy-MM-dd');
        this.gc.finalWheelingFromDate = this.datePipe.transform(this.gc.finalWheelingFromDate, 'yyyy-MM-dd');
        this.gc.finalWheelingToDate = this.datePipe.transform(this.gc.finalWheelingToDate, 'yyyy-MM-dd');
        this.gc.genServiceDate = this.datePipe.transform(this.gc.genServiceDate, 'yyyy-MM-dd');
    }

    saveGc(status: string) {
        this.formatChangesforDB();
        if (this.gc.id == '') 
         this.addGc() ;
        else
         this.updateGc();
    }

    addGc() {
        this.service.addGc(this.gc).subscribe(
            result => {
                this.listGcs();
            },
            error => {
                console.error('Error adding GC Application!');
                console.error(error);
                this.listGcs();
            }
        );
    }

    updateGc() {
        this.service.updateGc(this.gc).subscribe(
            result => {
                this.listGcs();
            },
            error => {
                console.error('Error updating Gc!');
                console.error(error);
            }
        );
    }

    completeGc() {
        this.service.completeGc(this.gc).subscribe(
            result => {
                this.listGcs();
            },
            error => {
                console.error('Error completing Gc!');
                console.error(error);
            }
        );

    }
    listGcs() {
        this.router.navigateByUrl('/gc/gc-list');
    }
    displayFn(value: any): string {
        return value && typeof value === 'object' ? value.name : value;
    }

    //TO-DO - NEED-TO-BE-FIXED
    /*setCompanyNames() {
        this.companyService.getCompanies().subscribe(
            _companies => {
                _companies.forEach(company => {
                    this.companyNames.push({
                        'key': company.companyName,
                        'name': company.companyName
                    });
                    if (company.services) {
                        company.services.forEach(service => {
                            if (service.type == "Consumer") {
                                service.companyId = company.companyName;
                                this.consumerServices.push(service);

                            }
                        });
                    }
                });

            }
        );
    }

    setServiceNames(_company) {
        this.serviceNames = [];
        this.consumerServices.forEach(service => {
            if (service.companyId == _company.value)
                this.serviceNames.push({
                    'key': service.key,
                    'name': service.edcName + ' - ' + service.number
                });
        });

    }*/
}