// Angular and other libraires
import { Component, OnInit,  Inject } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material';
import { DatePipe } from '@angular/common';
import { MAT_DIALOG_DATA } from '@angular/material';
import { HotRegisterer } from 'angular-handsontable';

// Helpers
import { EaDetailHelper } from './../../components/ea-detail/ea-detail-helper';
import { EaService } from './../../services/ea.service';
import { GsService } from './../../../generation-statement/services/gs.service';

// Data Structre imports
import { Ea,  EsUsageSummary,  AvailableGsUnits } from './../../../vo/ea';
import { EnergySaleMultiAddHeader, EnergySaleMultiAddLine } from '../../../vo/energysale-multiadd';
import { Gs } from './../../../../transaction/vo/gs';
import { TradeRelationship } from './../../../../master/vo/trade-relationship';
import { CommonUtils } from '../../../../shared/common/common-utils';

@Component({
    selector: 'app-bulk-allocation',
    templateUrl: './ea-bulk.component.html',
    providers: [DatePipe],

})
export class BulkDialogComponent implements OnInit {
	util: CommonUtils;
    hotData: any;
    datas = [];
    energySaleMultiAddHeader: EnergySaleMultiAddHeader;
    energySaleMultiAddLine: EnergySaleMultiAddLine;
    es: Ea;
    gs: Gs;
    esUsageSummary: EsUsageSummary;
    availableGsUnits: Array<AvailableGsUnits>;
    consumerCompanies: Array<TradeRelationship>;
    instance: string = "hotInstance";
    isFossilFuel=false;
    fuelGroup='';
    errorMessage = "";
    allocationFlag: boolean = false;
    chargeFlag: boolean = false;
    disableConfirm: boolean = true;
    minimumChargeFlag: boolean = false;
    validateFlag: boolean = true;

    hideHotColumn: boolean = false;
    settings = {
        colHeaders: ["Service Number", "C1", "C2", "C3", "C4", "C5", "Meter Reading Charges", "O&M Charges", "Transmission Charges", "System Operation Charges", "RKvah Penalty", "Negative Energy Charges", "Scheduling Charges", "Other Charges", "Parallel Operation Charges"],
        afterChange: this.afterChange
    };
    constructor(private router: Router, private datePipe: DatePipe, private eaDetailHelper: EaDetailHelper,   public dialogRef: MatDialogRef<BulkDialogComponent>, private _hotRegisterer: HotRegisterer, public dialog: MatDialog, @Inject(MAT_DIALOG_DATA) public data: any, private service: EaService) {
    	this.util = new CommonUtils();
    }
    ngOnInit() {
        this.es = new Ea();
        this.es.esUsageSummaries = Array<EsUsageSummary>();
        this.esUsageSummary = new EsUsageSummary();
        this.availableGsUnits = [];
        //set values from parent component
        this.es.id = this.data.esInput.id;
        this.es = this.data.esInput;
        this.consumerCompanies = this.data.commercialsInput;
        this.gs = this.data.gsInput;
        this.initMultiAddCollection();
        var res = this.eaDetailHelper.availableGsUnitsForPrint(this.es, this.gs);
        this.availableGsUnits = res["availableGsUnits"];
        this.fuelGroup = this.es.fuelGroupe;
        if(this.fuelGroup=="FF"){
          this.isFossilFuel = true;
        } 
        console.log("bulk opened");
        console.log(this.consumerCompanies)
        console.log(this.gs)
        console.log(this.es)
        // this.hideHotColumn=true
        this.disableConfirm = true;
        this.validateFlag = true;

    }
    initMultiAddCollection(){
        this.energySaleMultiAddHeader = new EnergySaleMultiAddHeader();
        this.energySaleMultiAddLine = new EnergySaleMultiAddLine();
        this.energySaleMultiAddHeader.energySaleMultiAddLines = Array<EnergySaleMultiAddLine>();
        this.energySaleMultiAddHeader.energySaleId = this.es.id;
        this.energySaleMultiAddHeader.generationStatementId = this.es.generationStatementId;
    }

    reset() {
        this.errorMessage = "";
        var deleteMultiAdd = confirm("Are you sure you want to reset!");
        if (deleteMultiAdd) {
            this.validateFlag = true;
            if (this.energySaleMultiAddHeader.id != null) {
                this.service.deleEsMultiAdd(this.energySaleMultiAddHeader.id, this.energySaleMultiAddHeader).subscribe();
            }
            this.initMultiAddCollection();
            this.hideHotColumn = false; 
        }

    }
    back() {
        // window.location.reload();
        this.dialogRef.close();
    }

    importMultiAdd() {
        this.errorMessage = "";
        if(this.isFossilFuel){
            var _result = this.eaDetailHelper.checkSlotsWithCommercialsForAll(this.es.esUsageSummaries, this.consumerCompanies);
            var _hasIssue = _result["hasIssue"];
            var _resultMessage = _result["resultMessage"];
            if(_hasIssue){
                this.errorMessage =_resultMessage;
                return false;
            }
        }
        this.service.importMultiAddEs(this.energySaleMultiAddHeader.id, this.energySaleMultiAddHeader).subscribe(x => {

        })
        this.validateFlag = true;
        this.back();
    }
    validate() { 
        this.validateFlag = false;
        this.errorMessage = "";
        var hasRecords = false;
        const hot = this._hotRegisterer.getInstance(this.instance);
        console.log("hot.getData()")
        console.log(hot.getData())
        //WRITE YOUR INSERT LOGIC HERE
        hot.getData().forEach(element => {
        console.log("element")
        console.log(element)
            if (element[0] != null) {
                hasRecords = true;
                this.replaceNulls(element);
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
                this.es.esUsageSummaries.push(Object.assign({}, this.esUsageSummary));

            }
        }); 
        console.log("hasRecords-"+hasRecords)
        if(hasRecords){
            this.es.stringFromDate = this.datePipe.transform(this.es.fromDate, 'yyyy-MM-dd');
                this.es.stringToDate = this.datePipe.transform(this.es.toDate, 'yyyy-MM-dd');
                this.service.validateMultiAddEs(this.energySaleMultiAddHeader).subscribe(x => {
                    if (x.id != null && x.id != undefined) {
                        this.service.getEsMultiAddById(x.id).subscribe(x => {
                            this.energySaleMultiAddHeader = x
                            this.hideHotColumn = true;
    
                        })
                    }
                })
            // // var result = this.eaDetailHelper.checkAllocatedUnits(this.es, this.gs);
            // // this.allocationFlag = result["allocation"];
            // // this.chargeFlag = result["charge"];
            // // this.errorMessage =result["errorMessage"];
            // // this.es = result["es"];
            // console.log("this.allocationFlag-"+this.allocationFlag)
            // console.log("this.chargeFlag-"+this.chargeFlag)
            // console.log("this.chargeFlag-"+this.chargeFlag)
            // console.log(this.es.esUsageSummaries)

            // if (this.allocationFlag) {
            //     this.errorMessage ="Cannot update Energy Allotment!!! - Error in Allotment ";
            //     return false;
    
            // } else if ((!this.isFossilFuel) && this.chargeFlag){
            //     this.errorMessage ="Cannot update Energy Allotment!!! - Error in Charges ";
            //     return false;
            // } else {
            //     this.es.stringFromDate = this.datePipe.transform(this.es.fromDate, 'yyyy-MM-dd');
            //     this.es.stringToDate = this.datePipe.transform(this.es.toDate, 'yyyy-MM-dd');
            //     this.service.validateMultiAddEs(this.energySaleMultiAddHeader).subscribe(x => {
            //         if (x.id != null && x.id != undefined) {
            //             this.service.getEsMultiAddById(x.id).subscribe(x => {
            //                 this.energySaleMultiAddHeader = x
            //                 this.hideHotColumn = true;
    
            //             })
            //         }
            //     })
            // }
        }
        else{
            this.errorMessage = "No Records to validate";
            return false;
        }

       

    }

    afterChange(e) {
        if (e) {
        }
    }
 

     
    replaceNulls(element){
		element[2]=this.util.replaceIfInvalid(element[2]);
		element[3]=this.util.replaceIfInvalid(element[3]);
		element[4]=this.util.replaceIfInvalid(element[4]);
		element[5]=this.util.replaceIfInvalid(element[5]);
		element[6]=this.util.replaceIfInvalid(element[6]);
		element[7]=this.util.replaceIfInvalid(element[7]);
		element[8]=this.util.replaceIfInvalid(element[8]);
		element[9]=this.util.replaceIfInvalid(element[9]);
		element[10]=this.util.replaceIfInvalid(element[10]);
		element[11]=this.util.replaceIfInvalid(element[11]);
		element[12]=this.util.replaceIfInvalid(element[12]);
		element[13]=this.util.replaceIfInvalid(element[13]);
        element[14]=this.util.replaceIfInvalid(element[14]);
	}
	clearCharges(element){
		element[6]=0.0;
		element[7]=0.0; 
		element[8]=0.0; 
		element[9]=0.0; 
		element[10]=0.0; 
		element[11]=0.0; 
		element[12]=0.0; 
		element[13]=0.0;  
		element[14]=0.0; 
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