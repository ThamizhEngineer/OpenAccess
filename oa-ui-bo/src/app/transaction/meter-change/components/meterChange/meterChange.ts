import { Component, OnInit } from "@angular/core";
import { CommonUtils } from "../../../../shared/common/common-utils";
import { CommonService } from "../../../../shared/common/common.service";
import { meterChangeService } from "../services/meterChange.service";

@Component({
    selector: 'meter-change',
    templateUrl: './meterChange.html',
    styleUrls: ["./meterChange.scss"],
    providers: [meterChangeService, CommonUtils]
})

export class MeterChangeComponent implements OnInit {

    months = [];

    message: string = "";
    serviceNo: string = "";
    oldMeterNo: string = "";
    newMeterNo: string = "";
    readingMonth: string = "";
    readingYear: string = "";
    companyName: string = "";
    commissionDate: string = "";
    flowTypeCode: string = "";
    mfValue: number = 0;

    // OLD METER READING VARIABLE'S
    oldIniImpC1: number = 0;
    oldIniImpC2: number = 0;
    oldIniImpC4: number = 0;
    oldIniImpC5: number = 0;
    oldIniExpC1: number = 0;
    oldIniExpC2: number = 0;
    oldIniExpC4: number = 0;
    oldIniExpC5: number = 0;
    oldIniKvahImp: number = 0;
    oldIniKvahExp: number = 0;
    oldIniRkvahImp: number = 0;
    oldIniRkvahExp: number = 0;

    oldFinImpC1: number = 0;
    oldFinImpC2: number = 0;
    oldFinImpC4: number = 0;
    oldFinImpC5: number = 0;
    oldFinExpC1: number = 0;
    oldFinExpC2: number = 0;
    oldFinExpC4: number = 0;
    oldFinExpC5: number = 0;
    oldFinKvahImp: number = 0;
    oldFinKvahExp: number = 0;
    oldFinRkvahImp: number = 0;
    oldFinRkvahExp: number = 0;

    oldDifImpC1: number;
    oldDifImpC2: number;
    oldDifImpC4: number;
    oldDifImpC5: number;
    oldDifExpC1: number;
    oldDifExpC2: number;
    oldDifExpC4: number;
    oldDifExpC5: number;
    oldDifKvahImp: number;
    oldDifKvahExp: number;
    oldDifRkvahImp: number;
    oldDifRkvahExp: number;

    oldTotImpC1: number;
    oldTotImpC2: number;
    oldTotImpC4: number;
    oldTotImpC5: number;
    oldTotExpC1: number;
    oldTotExpC2: number;
    oldTotExpC4: number;
    oldTotExpC5: number;
    oldTotKvahImp: number;
    oldTotKvahExp: number;
    oldTotRkvahImp: number;
    oldTotRkvahExp: number;

    // NEW METER READING VARIBALE'S

    newIniImpC1: number = 0;
    newIniImpC2: number = 0;
    newIniImpC4: number = 0;
    newIniImpC5: number = 0;
    newIniExpC1: number = 0;
    newIniExpC2: number = 0;
    newIniExpC4: number = 0;
    newIniExpC5: number = 0;
    newIniKvahImp: number = 0;
    newIniKvahExp: number = 0;
    newIniRkvahImp: number = 0;
    newIniRkvahExp: number = 0;

    newFinImpC1: number = 0;
    newFinImpC2: number = 0;
    newFinImpC4: number = 0;
    newFinImpC5: number = 0;
    newFinExpC1: number = 0;
    newFinExpC2: number = 0;
    newFinExpC4: number = 0;
    newFinExpC5: number = 0;
    newFinKvahImp: number = 0;
    newFinKvahExp: number = 0;
    newFinRkvahImp: number = 0;
    newFinRkvahExp: number = 0;

    newDifImpC1: number;
    newDifImpC2: number;
    newDifImpC4: number;
    newDifImpC5: number;
    newDifExpC1: number;
    newDifExpC2: number;
    newDifExpC4: number;
    newDifExpC5: number;
    newDifKvahImp: number;
    newDifKvahExp: number;
    newDifRkvahImp: number;
    newDifRkvahExp: number;

    newTotImpC1: number;
    newTotImpC2: number;
    newTotImpC4: number;
    newTotImpC5: number;
    newTotExpC1: number;
    newTotExpC2: number;
    newTotExpC4: number;
    newTotExpC5: number;
    newTotKvahImp: number;
    newTotKvahExp: number;
    newTotRkvahImp: number;
    newTotRkvahExp: number;

    // TOTAL READING'S

    totImpC1: number;
    totImpC2: number;
    totImpC4: number;
    totImpC5: number;
    totalImports: number;

    totExpC1: number;
    totExpC2: number;
    totExpC4: number;
    totExpC5: number;
    totalExports: number;

    totNetC1: number;
    totNetC2: number;
    totNetC4: number;
    totNetC5: number;
    totalNetUnits: number;

    totImpKvah: number;
    totExpKvah: number;
    totalKvah: number;

    totImpRkvah: number;
    totExpRkvah: number;
    totalRkvah: number;

    constructor(private meterChangeService: meterChangeService,
        private commonUtils: CommonUtils, private commonService: CommonService) {
    }

    ngOnInit() {
        this.months = this.commonService.fetchMonths();
        this.readingMonth = this.commonUtils.getPerviousMonth();
        this.readingYear = (this.readingYear == "12") ? this.commonUtils.getPreviousYear() : this.commonUtils.getCurrentYear();
        //   this.calculateOldMeter();
        //   this.calculateNewMeter();
    }

    calculateOldMeter() {
        this.oldDifImpC1 = parseFloat((this.oldFinImpC1 - this.oldIniImpC1).toFixed(2));
        this.oldTotImpC1 = this.oldDifImpC1 * this.mfValue;

        this.oldDifImpC2 = parseFloat((this.oldFinImpC2 - this.oldIniImpC2).toFixed(2));
        this.oldTotImpC2 = this.oldDifImpC2 * this.mfValue;

        this.oldDifImpC4 = parseFloat((this.oldFinImpC4 - this.oldIniImpC4).toFixed(2));
        this.oldTotImpC4 = this.oldDifImpC4 * this.mfValue;

        this.oldDifImpC5 = parseFloat((this.oldFinImpC5 - this.oldIniImpC5).toFixed(2));
        this.oldTotImpC5 = this.oldDifImpC5 * this.mfValue;

        this.oldDifExpC1 = parseFloat((this.oldFinExpC1 - this.oldIniExpC1).toFixed(2));
        this.oldTotExpC1 = this.oldDifExpC1 * this.mfValue;

        this.oldDifExpC2 = parseFloat((this.oldFinExpC2 - this.oldIniExpC2).toFixed(2));
        this.oldTotExpC2 = this.oldDifExpC2 * this.mfValue;

        this.oldDifExpC4 = parseFloat((this.oldFinExpC4 - this.oldIniExpC4).toFixed(2));
        this.oldTotExpC4 = this.oldDifExpC4 * this.mfValue;

        this.oldDifExpC5 = parseFloat((this.oldFinExpC5 - this.oldIniExpC5).toFixed(2));
        this.oldTotExpC5 = this.oldDifExpC5 * this.mfValue;

        this.oldDifKvahImp = parseFloat((this.oldFinKvahImp - this.oldIniKvahImp).toFixed(2));
        this.oldTotKvahImp = this.oldDifKvahImp * this.mfValue;

        this.oldDifKvahExp = parseFloat((this.oldFinKvahExp - this.oldIniKvahExp).toFixed(2));
        this.oldTotKvahExp = this.oldDifKvahExp * this.mfValue;

        this.oldDifRkvahImp = parseFloat((this.oldFinRkvahImp - this.oldIniRkvahImp).toFixed(2));
        this.oldTotRkvahImp = this.oldDifRkvahImp * this.mfValue;

        this.oldDifRkvahExp = parseFloat((this.oldFinRkvahExp - this.oldIniRkvahExp).toFixed(2));
        this.oldTotRkvahExp = this.oldDifRkvahExp * this.mfValue;
    }

    calculateNewMeter() {
        this.newDifImpC1 = parseFloat((this.newFinImpC1 - this.newIniImpC1).toFixed(2));
        this.newTotImpC1 = this.newDifImpC1 * this.mfValue;

        this.newDifImpC2 = parseFloat((this.newFinImpC2 - this.newIniImpC2).toFixed(2));
        this.newTotImpC2 = this.newDifImpC2 * this.mfValue;

        this.newDifImpC4 = parseFloat((this.newFinImpC4 - this.newIniImpC4).toFixed(2));
        this.newTotImpC4 = this.newDifImpC4 * this.mfValue;

        this.newDifImpC5 = parseFloat((this.newFinImpC5 - this.newIniImpC5).toFixed(2));
        this.newTotImpC5 = this.newDifImpC5 * this.mfValue;

        this.newDifExpC1 = parseFloat((this.newFinExpC1 - this.newIniExpC1).toFixed(2));
        this.newTotExpC1 = this.newDifExpC1 * this.mfValue;

        this.newDifExpC2 = parseFloat((this.newFinExpC2 - this.newIniExpC2).toFixed(2));
        this.newTotExpC2 = this.newDifExpC2 * this.mfValue;

        this.newDifExpC4 = parseFloat((this.newFinExpC4 - this.newIniExpC4).toFixed(2));
        this.newTotExpC4 = this.newDifExpC4 * this.mfValue;

        this.newDifExpC5 = parseFloat((this.newFinExpC5 - this.newIniExpC5).toFixed(2));
        this.newTotExpC5 = this.newDifExpC5 * this.mfValue;

        this.newDifKvahImp = parseFloat((this.newFinKvahImp - this.newIniKvahImp).toFixed(2));
        this.newTotKvahImp = this.newDifKvahImp * this.mfValue;

        this.newDifKvahExp = parseFloat((this.newFinKvahExp - this.newIniKvahExp).toFixed(2));
        this.newTotKvahExp = this.newDifKvahExp * this.mfValue;

        if (isNaN(this.newTotKvahExp)) {
            this.newTotKvahExp = 0;
        }

        this.newDifRkvahImp = parseFloat((this.newFinRkvahImp - this.newIniRkvahImp).toFixed(2));
        this.newTotRkvahImp = this.newDifRkvahImp * this.mfValue;

        this.newDifRkvahExp = parseFloat((this.newFinRkvahExp - this.newIniRkvahExp).toFixed(2));
        this.newTotRkvahExp = this.newDifRkvahExp * this.mfValue;

        this.totImpC1 = this.oldTotImpC1 + this.newTotImpC1;
        this.totImpC2 = this.oldTotImpC2 + this.newTotImpC2;
        this.totImpC4 = this.oldTotImpC4 + this.newTotImpC4;
        this.totImpC5 = this.oldTotImpC5 + this.newTotImpC5;

        this.totExpC1 = this.oldTotExpC1 + this.newTotExpC1;
        this.totExpC2 = this.oldTotExpC2 + this.newTotExpC2;
        this.totExpC4 = this.oldTotExpC4 + this.newTotExpC4;
        this.totExpC5 = this.oldTotExpC5 + this.newTotExpC5;

        this.totImpKvah = this.oldTotKvahImp + this.newTotKvahImp;
        this.totExpKvah = this.oldTotKvahExp + this.newTotKvahExp;

        this.totImpRkvah = this.oldTotRkvahImp + this.newTotRkvahImp;
        this.totExpRkvah = this.oldTotRkvahExp + this.newTotRkvahExp;

        this.totNetC1 = this.totExpC1 - this.totImpC1;
        this.totNetC2 = this.totExpC2 - this.totImpC2;
        this.totNetC4 = this.totExpC4 - this.totImpC4;
        this.totNetC5 = this.totExpC5 - this.totImpC5;

        if (this.totImpC1 != null && this.totImpC2 != null && this.totImpC4 != null && this.totImpC5 != null) {
            this.totalImports = this.totImpC1 + this.totImpC2 + this.totImpC4 + this.totImpC5;
        }

        if (this.totExpC1 != null && this.totExpC2 != null && this.totExpC4 != null && this.totExpC5 != null) {
            this.totalExports = this.totExpC1 + this.totExpC2 + this.totExpC4 + this.totExpC5;
        }

        if (this.totNetC1 != null && this.totNetC2 != null && this.totNetC4 != null && this.totNetC5 != null) {
            this.totalNetUnits = this.totNetC1 + this.totNetC2 + this.totNetC4 + this.totNetC5;
        }

    }

    getServiceDetails() {
        this.mfValue;
        this.newMeterNo;
        this.oldMeterNo;
        this.meterChangeService.getServiceDetails(this.serviceNo).subscribe(
            result => {
                if (result.status == "OK") {
                    this.mfValue = result.mfValue;
                    this.newMeterNo = result.meterNumber;
                    this.companyName = result.companyName;
                    this.flowTypeCode = result.flowTypeCode;
                    this.commissionDate = result.commissionDate;
                } else {
                    this.message = "Enter valid service number";
                    alert(this.message);
                }
            }
        );
    }

    saveMeterReadings() {
        var readings = {
            serviceNo: this.serviceNo,
            oldMeterNo: this.oldMeterNo,
            newMeterNo: this.newMeterNo,
            readingMonth: this.readingMonth,
            readingYear: this.readingYear,
            totalImportUnits: this.totalImports,
            totalExportUnits: this.totalExports,
            rkvahUnits: this.totImpRkvah,
            totalNetUnits: this.totalNetUnits,
            impC1Uinits: this.totImpC1,
            impC2Units: this.totImpC2,
            impC4Units: this.totImpC4,
            impC5Units: this.totImpC5,
            expC1Units: this.totExpC1,
            expC2Units: this.totExpC2,
            expC4Units: this.totExpC4,
            expC5Units: this.totExpC5,
            netC1Units: this.totNetC1,
            netC2Units: this.totNetC2,
            netC4Units: this.totNetC4,
            netC5Units: this.totNetC5
        }
        this.meterChangeService.saveMeterReadings(readings).subscribe(
            result => {
                console.log(result);
            }
        )
    }

    numberFormat(event){
        this.commonService.numberOnly(event)
    }

}