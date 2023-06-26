import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { id } from '@swimlane/ngx-datatable/release/utils';
import 'rxjs/add/operator/switchMap';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from '../../../../shared/common/common.service';
import { FeederLineLossService } from '../../services/feederlineloss.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
    selector: 'feeder-line-loss',
    templateUrl: './feeder-line-loss.component.html',
    styleUrls: ['./feeder-line-loss.component.scss'],
    providers: [FeederLineLossService]
})

export class FeederLineLossComponent implements OnInit {

    orgList = [];
    substationlist = [];
    Feederlist = [];
    months = [];
    rows = [];
    disableEdc: boolean = false;
    disabless: boolean = false;
    disablefeeder: boolean = false;
    searchOrgId: string = "";
    orgName: string = "";
    ssName: any = "";
    feederName: string = "";
    searchssId: string = "";
    searchfeederId: string = "";
    statementMonth: string = "";
    statementYear: string = "";
    message: string = "";
    totalexportunits: any = "";
    totalfeederexportunits: any = "";
    lospercentage: any;
    feedermeterno: String = "";
    ExportunitDifference: any = "";
    fuelType: String = "SOLAR";
    success: any;
    error: boolean = false;
    showEditmode: boolean = false;
    hidetotal: boolean = true;
    show_edit_btn: boolean = true;
    show_edit_btn1: boolean = true;
    show_save_btn: boolean = false;

    selectedValue: any;
    searchTxt: any;
    error_msg: boolean = false;


    constructor(
        private feederlinelossservice: FeederLineLossService,
        private route: ActivatedRoute,
        private router: Router,
        private commonUtils: CommonUtils,
        private commonService: CommonService,
        private _snackBar: MatSnackBar
    ) {
        this.fetchEDCs();
    }

    ngOnInit(): void {
        this.months = this.commonService.fetchMonths();
        this.statementMonth = this.commonUtils.getPerviousMonth();
        this.statementYear = (this.statementMonth == "12") ? this.commonUtils.getPreviousYear() : this.commonUtils.getCurrentYear();
        // this.ssName.sort((a, b) => a.labCode.localeCompare(b.labCode));
        this.show_edit_btn = false;
    }
    //    get Edc datas
    fetchEDCs() {
        this.commonService.fetchEDCs().subscribe(
            result => {
                this.orgList = result;
            });
    }

    // Edc name change
    onEdcNameChange(event: any) {
        this.fetchSubstations(event);
        const org = this.orgList.filter(x => x.id == event)
        console.log("org_name", org[0].name)
        this.orgName = org[0].name;
    }

    // get substation based on edc id
    fetchSubstations(edc_id) {
        this.feederlinelossservice.getSsById(edc_id).subscribe(
            result => {
                console.log("substation_list", result);
                this.substationlist = [];
                this.substationlist = result;
                if (!result) {
                    this.substationlist[0].name.next(this.substationlist[0].name)
                }
                this.searchssId = this.substationlist[0].id;
                // this.ssName = this.substationlist[0].name;
            }
        )
    }

    //  substation name change
    onsubstationNameChange(ss_id: any) {
        console.log(ss_id);
        this.fetchFeederlist(ss_id)
        const ss = this.substationlist.filter(x => x.id == ss_id)
        console.log("ss_name", ss[0].name)
        this.ssName = ss[0].name;
    }

    // get feeder based on ss id
    fetchFeederlist(ss_id) {
        this.feederlinelossservice.getfeedBySSId(ss_id).subscribe(
            result => {
                console.log("feeder_list", result);
                this.Feederlist = [];
                this.Feederlist = result;
                this.searchfeederId = this.Feederlist[0].id;
                this.feederName = this.Feederlist[0].name;
                this.error = false;
            }
        )
    }

    // feedername change
    onfeederNameChange(feederid: any) {
        const ss = this.Feederlist.filter(x => x.id == feederid)
        console.log("ss_name", ss[0].name)
        this.feederName = ss[0].name;
    }

    fetchResult() {

        if (this.searchfeederId == "") {
            this.error = true;
            this.message = "Feeder id required";
            return false
        }
        this.feederlinelossservice.getfeederlistdata(this.searchfeederId, this.statementMonth, this.statementYear).subscribe(
            result => {
                this.totalexportunits = "";
                this.totalfeederexportunits = "";
                this.lospercentage = null;
                this.feedermeterno = "";
                this.ExportunitDifference = "";

                // console.log("list", result['serviceReadings'].length);
                if (result['status'] == 'OK') {
                    this.rows = [];
                    this.rows = result['serviceReadings']
                    this.totalexportunits = result['totalAllWegs'].toString();
                    this.totalfeederexportunits = result['bulkMeterReading'].toString();
                    var loss = result['lineLossPercentage'];
                    this.lospercentage = loss.toFixed(3);
                    this.feedermeterno = result['feederMeterNo'].toString();
                    this.ExportunitDifference = result['exportsDifference'].toString();

                    if (result['totalAllWegs'].toString() == !null && result['bulkMeterReading'].toString() == null) {
                        this.error_msg = true;
                    }
                    if (result.success == true) {
                        this.show_edit_btn1 = result.success;
                    } else {
                        this.show_edit_btn1 = false;
                    }
                    if (this.ExportunitDifference > 0 && result.success == true) {
                        this.success = result.success;
                    } else {
                        this.success = false;
                    }
                    console.log(this.success, "ssss")
                } else {
                    this.rows = [];
                    this.rows = [];
                    this.totalexportunits = "";
                    this.totalfeederexportunits = "";
                    this.lospercentage = 0.0;
                    this.feedermeterno = "";
                }
            }
        )
    }

    Save() {
        var send_data = [
            {
                "orgId": this.searchOrgId,
                "orgName": this.orgName,
                "substationId": this.searchssId,
                "substationName": this.ssName,
                "feederId": this.searchfeederId,
                "feederName": this.feederName,
                "lossPercent": this.lospercentage,
                "month": this.statementMonth,
                "year": this.statementYear,
                "bulkMeterReading": this.totalfeederexportunits,
                "totalAllWegs": this.totalexportunits,
                "fuelType": this.fuelType
            }
        ]

        this.feederlinelossservice.savesolar_data(send_data).subscribe(
            res => {
                console.log(res);
                if (res.status == 200) {
                    alert("Line loss saved sucessfully")
                    const currentRoute = this.router.url;
                    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
                        this.router.navigate([currentRoute]);  //  refresh the current page
                    });
                }
            }
        )
    }

    edit_bulkmeter() {
        this.showEditmode = true;
        this.hidetotal = false;
        this.show_edit_btn = false;
        this.show_save_btn = true;
    }

    save_bulkmeter() {
        console.log(this.totalfeederexportunits, "check value");
        this.ExportunitDifference = this.totalexportunits - this.totalfeederexportunits;
        var loss = (this.ExportunitDifference / this.totalexportunits) * 100;
        this.lospercentage = loss.toFixed(3);

        if (this.ExportunitDifference > 0) {
            this.success = true
        } else {
            this.success = false;
        }

        this.show_edit_btn = true;
        this.show_save_btn = false;
        this.hidetotal = true;
        this.showEditmode = false;
    }

    prevent(event) {
        console.log("jknd");
        event.stopPropagation();
        event.preventDefault();
    }

}