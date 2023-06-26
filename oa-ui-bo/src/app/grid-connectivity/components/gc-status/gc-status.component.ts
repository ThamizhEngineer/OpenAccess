import { Component, OnInit, HostBinding } from '@angular/core';
import { CommonUtils } from '../../../shared/common/common-utils';

import { DatePipe } from '@angular/common';
import { GcService } from './../../services/gc.service';
import { Gc, GeneratorUnit, ApplicationStatus } from './../../services/gc';

import { MatDatepickerModule, MatNativeDateModule, NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from '@angular/material';

export class AppDateAdapter extends NativeDateAdapter {
    parse(value: any): Date | null {
        if ((typeof value === 'string') && (value.indexOf('/') > -1)) {
            const str = value.split('/');
            const year = Number(str[2]);
            const month = Number(str[1]) - 1;
            const date = Number(str[0]);
            return new Date(year, month, date);
        }
        const timestamp = typeof value === 'number' ? value : Date.parse(value);
        return isNaN(timestamp) ? null : new Date(timestamp);
    }

    format(date: Date, displayFormat: Object): string {
        if (displayFormat == "input") {
            let day = date.getDate();
            let month = date.getMonth() + 1;
            let year = date.getFullYear();
            return this._to2digit(day) + '/' + this._to2digit(month) + '/' + year;
        } else {
            return date.toDateString();
        }
    }

    private _to2digit(n: number) {
        return ('00' + n).slice(-2);
    }
}

export const APP_DATE_FORMATS = {
    parse: {
        dateInput: {
            month: 'short',
            year: 'numeric',
            day: 'numeric'
        }
    },
    display: {
        // dateInput: { month: 'short', year: 'numeric', day: 'numeric' },
        dateInput: 'input',
        monthYearLabel: {
            month: 'short',
            year: 'numeric',
            day: 'numeric'
        },
        dateA11yLabel: {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        },
        monthYearA11yLabel: {
            year: 'numeric',
            month: 'long'
        },
    }
}
@Component({
    selector: 'gc-status',
    templateUrl: './gc-status.component.html',
    //styleUrls: [],
    providers: [CommonUtils, MatDatepickerModule, MatNativeDateModule, DatePipe,
        {
            provide: DateAdapter,
            useClass: AppDateAdapter
        },
        {
            provide: MAT_DATE_FORMATS,
            useValue: APP_DATE_FORMATS
        }
    ]
})
export class GcStatusComponent implements OnInit {

    gc: Gc;
    applicationStatus: ApplicationStatus;
    appStatusRowIndex: number;
    isDisabled = false;
    isMandatory = false;
    accessCreateFlag: boolean = false;
    disableControls: boolean = false;


    history = [{
        'status': 'Saved',
        'updatedOn': '01/01/2017',
        'updatedBy': 'Test',
        "remarks": "Content Will be changed"
    }, {
        'status': 'Submitted',
        'updatedOn': '01/02/2017',
        'updatedBy': 'Test',
        "remarks": "Content Will be changed"
    }, ];
    // statuses = ['Applied', 'Submitted', 'Saved', 'Approved', "Rejected", 'Pending'];
    statuses = [{
            "key": "Application Received",
            "name": "Application Received"
        },
        {
            "key": "Application Received-Queries On application",
            "name": "Application Received-Queries On application"
        },
        {
            "key": "Application Registered",
            "name": "Application Registered"
        },
        {
            "key": "Application submitted for Load Flow Study",
            "name": "Application submitted for Load Flow Study"
        },
        {
            "key": "Load Flow Study Result Received",
            "name": "Load Flow Study Result Received"
        },
        {
            "key": "Load Flow Study Result Communicated to generator",
            "name": "Load Flow Study Result Communicated to generator"
        },
        {
            "key": "Received Concurrence From Generator",
            "name": "Received Concurrence From Generator"
        },
        {
            "key": "Approval For Taking Up Work",
            "name": "Approval For Taking Up Work"
        },
        {
            "key": "Work Completed",
            "name": "Work Completed"
        },
        {
            "key": "Statutory Clearence Obtained",
            "name": "Statutory Clearence Obtained"
        },
        {
            "key": "Issue of Order For Grid Connectivity",
            "name": "Issue of Order For Grid Connectivity"
        }
    ];

    constructor(private gcService: GcService, private datePipe: DatePipe) {
        this.gcService.gcEvent.subscribe(_gc => {
            this.gc = _gc;
        });
    }

    ngOnInit() {
        this.gc = new Gc();

        this.applicationStatus = new ApplicationStatus();
        this.initApplicationStatus();
        this.formatChangesforUI();
        if ((CommonUtils.getLoginUserTypeCode() == "GEN" || CommonUtils.getLoginUserTypeCode() == "GEN-COMMON")) {
            this.accessCreateFlag = true;
        }
    }

    formatChangesforUI() {
        this.applicationStatus.gcStatusUpdateDate = (this.applicationStatus.gcStatusUpdateDate) ? this.applicationStatus.gcStatusUpdateDate.substr(0, 10) : "";
    }

    formatChangesforDB() {
        this.applicationStatus.gcStatusUpdateDate = this.datePipe.transform(this.applicationStatus.gcStatusUpdateDate, 'yyyy-MM-dd');
    }
    initApplicationStatus() {
        if (!this.gc.applicationStatus) {
            this.gc.applicationStatus = [];
        }
        this.formatChangesforDB();
        this.applicationStatus = Object.assign({}, {
            gcStatusTypeName: '',
            gcStatusUpdateDate: '',
            gcStatusUpdateBy: '',
            gcStatusRemarks: ''
        })
        this.appStatusRowIndex = -1;
    }


    filterAppStatus(val: string) {

        return val ? this.statuses.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.statuses;
    }
    updateStatus() {
        console.log("In applicationStatus");
        console.log(this.applicationStatus);
        this.formatChangesforDB();
        this.gc.applicationStatus.push(Object.assign({}, this.applicationStatus));
        this.gcService.setGc(this.gc);

        console.log(this.gc);

    }

    addApplicationStatus() {
        if (!this.gc.applicationStatus) {
            this.gc.applicationStatus = [];
        }
        this.formatChangesforDB();
        this.gc.applicationStatus.push(Object.assign({}, this.applicationStatus));
        this.gcService.setGc(this.gc);
        console.log(this.gc);
        this.initApplicationStatus();
    }

    updateApplicationStatus() {
        this.formatChangesforDB();
        let tempArray = [];
        for (let index = 0; index < this.gc.applicationStatus.length; index++) {
            if (this.appStatusRowIndex == index) {
                tempArray.push(Object.assign({}, this.applicationStatus));
            } else {
                tempArray.push(this.gc.applicationStatus[index]);
            }

        }
        this.gc.applicationStatus = tempArray;
        this.gcService.setGc(this.gc);
        this.initApplicationStatus();
    }


    editApplicationStatus(_index: number) {
        this.appStatusRowIndex = _index;
        this.applicationStatus = Object.assign({}, this.gc.applicationStatus[_index]);
    }

    deleteApplicationStatus(_index: number) {
        this.gc.applicationStatus.splice(_index, 1);
    }
}