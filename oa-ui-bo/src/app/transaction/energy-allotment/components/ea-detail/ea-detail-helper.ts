// import { Es, EsUsageDetail, EsUsageSummary, generationStatementForPrint, AvailableGsUnits, AllocatedGsUnits } from './../../../vo/es';
import { Gs } from './../../../../transaction/vo/gs';
import { OnInit} from '@angular/core';
import { Ea, EsUsageDetail, EsUsageSummary,  AvailableGsUnits, AllocatedGsUnits } from './../../../vo/ea';
import { TradeRelationship } from './../../../../master/vo/trade-relationship';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
export class EaDetailHelper implements OnInit{
	util: CommonUtils;
	allocationFlag: boolean = false;
	minimunAllocationFlag: boolean = false;
	minimumChargeFlag: boolean = false;
    es: Ea;
    gs: Gs;
	esUsageSummary: EsUsageSummary;
    esUsageDetail: EsUsageDetail;
    availableGsUnits: Array<AvailableGsUnits>;
    allocatedGsunits: Array<AllocatedGsUnits>;
    countC1:number=0.0;countC2:number=0.0;countC3:number=0.0;countC4:number=0.0;countC5:number=0.0;count:number=0.0;
	chargeFlag: boolean = false;
	errorMessage="";
	constructor(){
		this.util = new CommonUtils();
	}
    ngOnInit() {
		this.es = new Ea();
		this.es.esUsageSummaries = Array<EsUsageSummary>();
		this.esUsageSummary = new EsUsageSummary();
		this.availableGsUnits = [];
		this.allocatedGsunits = [];
		this.util = new CommonUtils();

	}
	availableGsUnitsForPrint(es:Ea,gs:Gs) {
		this.errorMessage="";
		this.es=es;
		this.gs=gs;
		this.availableGsUnits=[];
		var totalBank = 0; var totalAlloc = 0;
		var totalGc = 0; var totalC = 0;

		totalGc = (parseFloat(this.es.availGc1) + parseFloat(this.es.availGc2) + parseFloat(this.es.availGc3) + parseFloat(this.es.availGc4) + parseFloat(this.es.availGc5));
		totalGc = isNaN(totalGc) ? 0 : totalGc;

		totalBank = (parseFloat(this.es.availBc1) + parseFloat(this.es.availBc2) + parseFloat(this.es.availBc3) + parseFloat(this.es.availBc4) + parseFloat(this.es.availBc5));
		totalBank = isNaN(totalBank) ? 0 : totalBank;

		totalC = (parseFloat(this.es.availc1) + parseFloat(this.es.availc2) + parseFloat(this.es.availc3) + parseFloat(this.es.availc4) + parseFloat(this.es.availc5));
		totalC = isNaN(totalC) ? 0 : totalC;
		totalAlloc = totalBank + parseFloat(this.es.netGeneration);
		var totalBankStr = totalBank.toString();
		var totalCStr = totalC.toString();
		var totalGcStr = totalGc.toString();
		var totalStr = totalAlloc.toString();

		// TODO - Dinesh - only if excessUnitType is BANKING, banked should be considered below.
		 this.countC1=(parseFloat(this.es.availGc1)+parseFloat(this.es.availBc1));
		 this.countC2=(parseFloat(this.es.availGc2)+parseFloat(this.es.availBc2));
		 this.countC3=(parseFloat(this.es.availGc3)+parseFloat(this.es.availBc3));
		 this.countC4=(parseFloat(this.es.availGc4)+parseFloat(this.es.availBc4));
		 this.countC5=(parseFloat(this.es.availGc5)+parseFloat(this.es.availBc5));
		this.count =this.countC1+this.countC2+this.countC3+this.countC4+this.countC5;

		this.availableGsUnits.push({
			"heading": "Powerplant",
			"c1": parseFloat(this.es.availGc1).toString(),
			"c2": parseFloat(this.es.availGc2).toString(),
			"c3": parseFloat(this.es.availGc3).toString(),
			"c4": parseFloat(this.es.availGc4).toString(),
			"c5": parseFloat(this.es.availGc5).toString(),
			"total": totalGcStr,
		});
		this.availableGsUnits.push({
			"heading": "Banking",
			"c1": parseFloat(this.es.availBc1).toString(),
			"c2": parseFloat(this.es.availBc2).toString(),
			"c3": parseFloat(this.es.availBc3).toString(),
			"c4": parseFloat(this.es.availBc4).toString(),
			"c5": parseFloat(this.es.availBc5).toString(),
			"total": totalBankStr,
		});
		this.availableGsUnits.push({
			"heading": "Total",
			"c1": this.countC1 +"",
			"c2":this.countC2+"",
			"c3": this.countC3+"",
			"c4": this.countC4+"",
			"c5": this.countC5+"",
			"total": this.count+"",
		});

		// console.log("es helper availableGsUnits ");
		// console.log(this.availableGsUnits);
		return {availableGsUnits:this.availableGsUnits}
	}

	allocatedGsUnitsForPrint(es:Ea) {
		this.es=es;
		this.errorMessage="";		
		this.allocatedGsunits = [];
		this.allocatedGsunits.push({
			"heading": "Powerplant",
			"c1": this.es.gc1,
			"c2": this.es.gc2,
			"c3": this.es.gc3,
			"c4": this.es.gc4,
			"c5": this.es.gc5,
			"total": this.es.netGeneration,
		});
		this.allocatedGsunits.push({
			"heading": "Banking",
			"c1": this.es.bc1,
			"c2": this.es.bc2,
			"c3": this.es.bc3,
			"c4": this.es.bc4,
			"c5": this.es.bc5,
			"total": this.es.totalBankUnitsUsed,
		});
		this.allocatedGsunits.push({
			"heading": "Total",
			"c1": this.es.c1,
			"c2": this.es.c2,
			"c3": this.es.c3,
			"c4": this.es.c4,
			"c5": this.es.c5,
			"total": this.es.netAllocation,
		});
		// console.log(this.availableGsUnits);
		return {allocated:this.allocatedGsunits}
	}

	/*
		cleanRecords()
		remove spaces, replace null and invalid values with 0.0
	*/
	cleanRecords(rows : Array<EsUsageSummary>){
		var cleansedRows=[];
		rows.forEach(_summary => {
			_summary.c1 = this.util.replaceIfInvalid(_summary.c1);
			_summary.c2 = this.util.replaceIfInvalid(_summary.c2);
			_summary.c3 = this.util.replaceIfInvalid(_summary.c3);
			_summary.c4 = this.util.replaceIfInvalid(_summary.c4);
			_summary.c5 = this.util.replaceIfInvalid(_summary.c5);
			_summary.c001TotalCharge = this.util.replaceIfInvalid(_summary.c001TotalCharge);
			_summary.c002TotalCharge = this.util.replaceIfInvalid(_summary.c002TotalCharge);
			_summary.c003TotalCharge = this.util.replaceIfInvalid(_summary.c003TotalCharge);
			_summary.c004TotalCharge = this.util.replaceIfInvalid(_summary.c004TotalCharge);
			_summary.c005TotalCharge = this.util.replaceIfInvalid(_summary.c005TotalCharge);
			_summary.c006TotalCharge = this.util.replaceIfInvalid(_summary.c006TotalCharge);
			_summary.c007TotalCharge = this.util.replaceIfInvalid(_summary.c007TotalCharge);
			_summary.c008TotalCharge = this.util.replaceIfInvalid(_summary.c008TotalCharge);
			cleansedRows.push(_summary);
		});
		console.log("cleansedRows");
		console.log(cleansedRows)
		return cleansedRows;
	}

	checkAllocatedUnits(es:Ea,gs:Gs,UserType:string) {
		this.es = es;
		this.gs= gs;
		
		this.errorMessage="";
		this.allocationFlag = false;
		this.chargeFlag = false;
		console.log("stage1")
        this.countC1=(parseFloat(this.es.availGc1)+parseFloat(this.es.availBc1));
        this.countC2=(parseFloat(this.es.availGc2)+parseFloat(this.es.availBc2));
        this.countC3=(parseFloat(this.es.availGc3)+parseFloat(this.es.availBc3));
        this.countC4=(parseFloat(this.es.availGc4)+parseFloat(this.es.availBc4));
        this.countC5=(parseFloat(this.es.availGc5)+parseFloat(this.es.availBc5));
        this.count =this.countC1+this.countC2+this.countC3+this.countC4+this.countC5;
		
		var c1Total = 0.0;
		var c2Total = 0.0;
		var c3Total = 0.0;
		var c4Total = 0.0;
		var c5Total = 0.0;
		var c1Bank = 0.0;
		var c2Bank = 0.0;
		var c3Bank = 0.0;
		var c4Bank = 0.0;
		var c5Bank = 0.0;
		var c1Sum = 0.0;
		var c2Sum = 0.0;
		var c3Sum = 0.0;
		var c4Sum = 0.0;
		var c5Sum = 0.0;
		var totalgc = 0.0;
		var totalbc = 0.0;
		var totalc = 0.0;
		var c1Charge=0.0;
		var c2Charge=0.0;
		var c3Charge=0.0;
		var c4Charge=0.0;
		var c5Charge=0.0;
		var c6Charge=0.0;
		var c7Charge=0.0;
		var c8Charge=0.0;
		// this.es.esUsageSummaries.forEach(function (summary:EsUsageSummary) {
		// 	summary.total =  ""+ (parseFloat(summary.c1)+ parseFloat(summary.c2)+ parseFloat(summary.c3)+ parseFloat(summary.c4)+ parseFloat(summary.c5));			
		// });

		this.es.esUsageSummaries = this.cleanRecords(this.es.esUsageSummaries);
		console.log("this.es.esUsageSummaries after cleanRecords")
		console.log(this.es.esUsageSummaries )
		// calculate unit sums and charge sums
		this.es.esUsageSummaries.forEach(summary => {
			c1Total = c1Total + parseFloat(summary.c1);
			c2Total = c2Total + parseFloat(summary.c2);
			c3Total = c3Total + parseFloat(summary.c3);
			c4Total = c4Total + parseFloat(summary.c4);
			c5Total = c5Total + parseFloat(summary.c5);
			c1Charge = c1Charge +parseFloat(summary.c001TotalCharge);
			c2Charge = c2Charge +parseFloat(summary.c002TotalCharge);
			c3Charge = c3Charge +parseFloat(summary.c003TotalCharge);
			c4Charge = c4Charge +parseFloat(summary.c004TotalCharge);
			c5Charge = c5Charge +parseFloat(summary.c005TotalCharge);
			c6Charge = c6Charge +parseFloat(summary.c006TotalCharge);
			c7Charge = c7Charge +parseFloat(summary.c007TotalCharge);
			c8Charge = c8Charge +parseFloat(summary.c008TotalCharge);
			summary.total =  ""+ (parseFloat(summary.c1)+ parseFloat(summary.c2)+ parseFloat(summary.c3)+ parseFloat(summary.c4)+ parseFloat(summary.c5));			
		});
	
		console.log("stage2")
		
		// if (c1Total > parseFloat(this.gs.c1)) {
			if(this.gs.dispFuelTypeCode=='WIND'&&this.gs.flowTypeCode=='IS-CAPTIVE'&&UserType!='SUP'&&parseFloat(this.es.availGc1) > c1Total){
				this.errorMessage= "Entire current month wind generation should be allotted";
				this.allocationFlag = true;
			}
		if (c1Total > this.countC1 ) {
			this.errorMessage= "Total Allocated C1 Units("+c1Total+") cannot be more than Total Available C1 Units("+this.countC1+")";
			this.allocationFlag = true;		
		} else {
			if (c1Total > parseFloat(this.gs.generationStatementSlots[0].netUnits)) {				
				this.es.gc1 = this.allocatedGsunits[0].c1 = this.gs.generationStatementSlots[0].netUnits;
				c1Bank = c1Total - parseFloat(this.allocatedGsunits[0].c1);		
				this.es.bc1 = this.allocatedGsunits[1].c1 = c1Bank.toString();				
				c1Sum = parseFloat(this.allocatedGsunits[0].c1) + parseFloat(this.allocatedGsunits[1].c1);			
				this.es.c1 = this.allocatedGsunits[2].c1 = c1Sum.toString();				
			} else if (c1Total <= parseFloat(this.gs.generationStatementSlots[0].netUnits)) {
				this.es.gc1 = this.allocatedGsunits[0].c1 = c1Total.toString();
				this.es.bc1 = this.allocatedGsunits[1].c1 = c1Bank.toString();
				c1Sum = parseFloat(this.allocatedGsunits[0].c1) + parseFloat(this.allocatedGsunits[1].c1);
				this.es.c1 = this.allocatedGsunits[2].c1 = c1Sum.toString();
			}
		}
		console.log("stage3")
		if(this.gs.dispFuelTypeCode=='WIND'&&this.gs.flowTypeCode=='IS-CAPTIVE'&&UserType!='SUP'&& parseFloat(this.es.availGc2) > c2Total){
			this.errorMessage= "Entire current month wind generation should be allotted";
			this.allocationFlag = true;
		}
		if (c2Total > this.countC2) {
			this.errorMessage=  "Total Allocated C2 Units("+c2Total+") cannot be more than Total Available C2 Units("+this.countC2+")";
			this.allocationFlag = true;
		
		} else {

			if (c2Total > parseFloat(this.gs.generationStatementSlots[1].netUnits)) {
				this.es.gc2 = this.allocatedGsunits[0].c2 = this.gs.generationStatementSlots[1].netUnits;
				c2Bank = c2Total - parseFloat(this.allocatedGsunits[0].c2);
				this.es.bc2 = this.allocatedGsunits[1].c2 = c2Bank.toString();
				c2Sum = parseFloat(this.allocatedGsunits[0].c2) + parseFloat(this.allocatedGsunits[1].c2);
				this.es.c2 = this.allocatedGsunits[2].c2 = c2Sum.toString();
			} else if (c2Total <= parseFloat(this.gs.generationStatementSlots[1].netUnits)) {
				this.es.gc2 = this.allocatedGsunits[0].c2 = c2Total.toString();
				this.es.bc2 = this.allocatedGsunits[1].c2 = c2Bank.toString();
				c1Sum = parseFloat(this.allocatedGsunits[0].c2) + parseFloat(this.allocatedGsunits[1].c2);
				this.es.c2 = this.allocatedGsunits[2].c2 = c1Sum.toString();
			}
		}

		console.log("stage3")
		if(this.gs.dispFuelTypeCode=='WIND'&&this.gs.flowTypeCode=='IS-CAPTIVE'&&UserType!='SUP'&&parseFloat(this.es.availGc3) > c3Total){
			this.errorMessage= "Entire current month wind generation should be allotted";
			this.allocationFlag = true;
		}
		if (c3Total > this.countC3) {
			this.errorMessage= "Total Allocated C3 Units("+c3Total+") cannot be more than Total Available C3 Units("+this.countC3+")";
			this.allocationFlag = true;
			
		} else {

			if (c3Total > parseFloat(this.gs.generationStatementSlots[2].netUnits)) {
				this.es.gc3 = this.allocatedGsunits[0].c3 = this.gs.generationStatementSlots[2].netUnits;
				c3Bank = c3Total - parseFloat(this.allocatedGsunits[0].c3);
				this.es.bc3 = this.allocatedGsunits[1].c3 = c3Bank.toString();
				c3Sum = parseFloat(this.allocatedGsunits[0].c3) + parseFloat(this.allocatedGsunits[1].c3);
				this.es.c3 = this.allocatedGsunits[2].c3 = c3Sum.toString();
			} else if (c3Total <= parseFloat(this.gs.generationStatementSlots[2].netUnits)) {
				this.es.gc3 = this.allocatedGsunits[0].c3 = c3Total.toString();
				this.es.bc3 = this.allocatedGsunits[1].c3 = c3Bank.toString();
				c3Sum = parseFloat(this.allocatedGsunits[0].c3) + parseFloat(this.allocatedGsunits[1].c3);
				this.es.c3 = this.allocatedGsunits[2].c3 = c3Sum.toString();
			}
		}

		console.log("stage3")
		if(this.gs.dispFuelTypeCode=='WIND'&&this.gs.flowTypeCode=='IS-CAPTIVE'&&UserType!='SUP'&&parseFloat(this.es.availGc4) > c4Total){
			this.errorMessage= "Entire current month wind generation should be allotted";
			this.allocationFlag = true;
		}
		if (c4Total > this.countC4) {
			this.errorMessage=  "Total Allocated C4 Units("+c4Total+") cannot be more than Total Available C4 Units("+this.countC4+")";
			this.allocationFlag = true;
	
		} else {

			if (c4Total > parseFloat(this.gs.generationStatementSlots[3].netUnits)) {
				this.es.gc4 = this.allocatedGsunits[0].c4 = this.gs.generationStatementSlots[3].netUnits;
				c4Bank = c4Total - parseFloat(this.allocatedGsunits[0].c4);
				this.es.bc4 = this.allocatedGsunits[1].c4 = c4Bank.toString();
				c4Sum = parseFloat(this.allocatedGsunits[0].c4) + parseFloat(this.allocatedGsunits[1].c4);
				this.es.c4 = this.allocatedGsunits[2].c4 = c4Sum.toString();
			} else if (c4Total <= parseFloat(this.gs.generationStatementSlots[3].netUnits)) {
				this.es.gc4 = this.allocatedGsunits[0].c4 = c4Total.toString();
				this.es.bc4 = this.allocatedGsunits[1].c4 = c4Bank.toString();
				c4Sum = parseFloat(this.allocatedGsunits[0].c4) + parseFloat(this.allocatedGsunits[1].c4);
				this.es.c4 = this.allocatedGsunits[2].c4 = c4Sum.toString();
			}
		}

		console.log("stage3")
		if(this.gs.dispFuelTypeCode=='WIND'&&this.gs.flowTypeCode=='IS-CAPTIVE'&&UserType!='SUP'&&parseFloat(this.es.availGc5) > c5Total){
			this.errorMessage= "Entire current month wind generation should be allotted";
			this.allocationFlag = true;
		}
		if (c5Total > this.countC5) {
			this.errorMessage=  "Total Allocated C5 Units("+c5Total+") cannot be more than Total Available C5 Units("+this.countC5+")";
			this.allocationFlag = true;

		} else {

			if (c5Total > parseFloat(this.gs.generationStatementSlots[4].netUnits)) {
				this.es.gc5 = this.allocatedGsunits[0].c5 = this.gs.generationStatementSlots[4].netUnits;
				c5Bank = c5Total - parseFloat(this.allocatedGsunits[0].c5);
				this.es.bc5 = this.allocatedGsunits[1].c5 = c5Bank.toString();
				c5Sum = parseFloat(this.allocatedGsunits[0].c5) + parseFloat(this.allocatedGsunits[1].c5);
				this.es.c5 = this.allocatedGsunits[2].c5 = c5Sum.toString();
			} else if (c5Total <= parseFloat(this.gs.generationStatementSlots[4].netUnits)) {
				this.es.gc5 = this.allocatedGsunits[0].c5 = c5Total.toString();
				this.es.bc5 = this.allocatedGsunits[1].c5 = c5Bank.toString();
				c5Sum = parseFloat(this.allocatedGsunits[0].c5) + parseFloat(this.allocatedGsunits[1].c5);
				this.es.c5 = this.allocatedGsunits[2].c5 = c5Sum.toString();
			}
		}
		
		console.log("stage3")
		this.gs.generationStatementCharges.forEach(charge=>{
			if(charge.totalCharges==null){
				charge.totalCharges = "0";
			}
			if(charge.chargeCode == "C008"){
				if(c8Charge > parseFloat(charge.totalCharges)){
					this.errorMessage= charge.chargeDesc+"--> Allocated charges ("+c8Charge+") cannot be more than available charges ("+charge.totalCharges+") ";
					this.chargeFlag = true;				
				}
			}
			if(charge.chargeCode == "C007"){
				if(c7Charge > parseFloat(charge.totalCharges)){
					this.errorMessage= charge.chargeDesc+"--> Allocated charges ("+c7Charge+") cannot be more than available charges ("+charge.totalCharges+") ";
					this.chargeFlag = true;				
				}
			}
			if(charge.chargeCode == "C006"){
				if(c6Charge > parseFloat(charge.totalCharges)){
					this.errorMessage= charge.chargeDesc+"--> Allocated charges ("+c6Charge+") cannot be more than available charges ("+charge.totalCharges+") ";
					this.chargeFlag = true;	
				}
			}
			if(charge.chargeCode == "C005"){
				if(c5Charge > parseFloat(charge.totalCharges)){
					this.errorMessage= charge.chargeDesc+"--> Allocated charges ("+c5Charge+") cannot be more than available charges ("+charge.totalCharges+") ";
					this.chargeFlag = true;
				}
			}
			if(charge.chargeCode == "C004"){
				if(c4Charge> parseFloat(charge.totalCharges)){
					this.errorMessage= charge.chargeDesc+"--> Allocated charges ("+c4Charge+") cannot be more than available charges ("+charge.totalCharges+") ";
					this.chargeFlag = true;
				}
			}
			if(charge.chargeCode == "C003"){
				if(c3Charge > parseFloat(charge.totalCharges)){
					this.errorMessage= charge.chargeDesc+"--> Allocated charges ("+c3Charge+") cannot be more than available charges ("+charge.totalCharges+") ";
					this.chargeFlag = true;
				}
			}
		
			if(charge.chargeCode == "C002"){
				if(c2Charge > parseFloat(charge.totalCharges)){
					this.errorMessage= charge.chargeDesc+"--> Allocated charges ("+c2Charge+") cannot be more than available charges ("+charge.totalCharges+") ";
					this.chargeFlag = true;
				}
			}
			if(charge.chargeCode == "C001"){
				
				if(c1Charge > parseFloat(charge.totalCharges)){

					this.errorMessage= charge.chargeDesc+"--> Allocated charges ("+c1Charge+") cannot be more than available charges ("+charge.totalCharges+") ";
					this.chargeFlag = true;
				}
			}
		});
		
		console.log("stage3")
		totalgc = parseFloat(this.es.gc1) + parseFloat(this.es.gc2) + parseFloat(this.es.gc3) + parseFloat(this.es.gc4) + parseFloat(this.es.gc5);
		totalbc = parseFloat(this.es.bc1) + parseFloat(this.es.bc2) + parseFloat(this.es.bc3) + parseFloat(this.es.bc4) + parseFloat(this.es.bc5);
		totalc = parseFloat(this.es.c1) + parseFloat(this.es.c2) + parseFloat(this.es.c3) + parseFloat(this.es.c4) + parseFloat(this.es.c5); 
		this.es.netGeneration = this.allocatedGsunits[0].total = totalgc.toString();
		this.es.totalBankUnitsUsed = this.allocatedGsunits[1].total = totalbc.toString();
		this.es.netAllocation = this.allocatedGsunits[2].total = totalc.toString(); 
		console.log("es output from checkAllocationUnits")
		console.log(this.es)
		return {allocation:this.allocationFlag,charge:this.chargeFlag,es:this.es,esUsageSummary:this.esUsageSummary,allocatedGsunits:this.allocatedGsunits, errorMessage:this.errorMessage}
	}

	checkMinumumAllocatedUnits(es:Ea,gs:Gs) {
		this.es=es;
		this.gs=gs;
		this.errorMessage="";
		this.minimumChargeFlag=false;
		var isFossilFuel: boolean = false;

		if(this.gs.dispFuelTypeGroup=='FF'){
			isFossilFuel = true;
		} 
		var c1Charge=0;
		var c2Charge=0;
		var c3Charge=0;
		var c4Charge=0;
		var c5Charge=0;
		var c6Charge=0;
		var c7Charge=0;
		var c8Charge=0; 
		this.es.esUsageSummaries.forEach(function (summary) { 
			c1Charge = c1Charge +parseFloat(summary.c001TotalCharge); 
			c2Charge = c2Charge +parseFloat(summary.c002TotalCharge);
			c3Charge = c3Charge +parseFloat(summary.c003TotalCharge);
			c4Charge = c4Charge +parseFloat(summary.c004TotalCharge);
			c5Charge = c5Charge +parseFloat(summary.c005TotalCharge);
			c6Charge = c6Charge +parseFloat(summary.c006TotalCharge);
			c7Charge = c7Charge +parseFloat(summary.c007TotalCharge);
			c8Charge = c8Charge +parseFloat(summary.c008TotalCharge); 
		}); 

		this.gs.generationStatementCharges.forEach(charge=>{ 		
			if(charge.totalCharges==null){
				charge.totalCharges = "0";
			}
			if(!isFossilFuel){ //charges not allocated for fossilfuel
				if(charge.chargeCode == "C008"){					
					if(c8Charge < parseFloat(charge.totalCharges)){
						this.errorMessage=this.errorMessage+"\n"+"Total C008 Allocated charges cannot be less than available charges";
						this.minimumChargeFlag = true;				
					}
				}
				if(charge.chargeCode == "C007"){
					if(c7Charge < parseFloat(charge.totalCharges)){
						this.errorMessage=this.errorMessage+"\n"+"Total C007 Allocated charges cannot be less than available charges";
						this.minimumChargeFlag = true;				
					}
				}
				if(charge.chargeCode == "C006"){					
					if(c6Charge < parseFloat(charge.totalCharges)){
						this.errorMessage=this.errorMessage+"\n"+"Total C006 Allocated charges cannot be less than available charges";
						this.minimumChargeFlag = true;					
					}
				}
				if(charge.chargeCode == "C005"){
					if(c5Charge < parseFloat(charge.totalCharges)){
						this.errorMessage=this.errorMessage+"\n"+"Total C005 Allocated charges cannot be less than available charges";
						this.minimumChargeFlag = true;
					}
				}				
				if(charge.chargeCode == "C004"){
					if(c4Charge < parseFloat(charge.totalCharges)){
						this.errorMessage=this.errorMessage+"\n"+"Total C004 Allocated charges cannot be less than available charges";
						this.minimumChargeFlag = true;
					}
				}
				if(charge.chargeCode == "C003"){
					if(c3Charge < parseFloat(charge.totalCharges)){
						this.errorMessage=this.errorMessage+"\n"+"Total C003 Allocated charges cannot be less than available charges";
						this.minimumChargeFlag = true;
					}
				}
			
				if(charge.chargeCode == "C002"){
					if(c2Charge < parseFloat(charge.totalCharges)){
						this.errorMessage=this.errorMessage+"\n"+"Total C002 Allocated charges cannot be less than available charges";
						this.minimumChargeFlag = true;
					}
				}
				if(charge.chargeCode == "C001"){					
					if(c1Charge < parseFloat(charge.totalCharges)){
						this.errorMessage=this.errorMessage+"\n"+"Total C001 Allocated charges cannot be less than available charges";
						this.minimumChargeFlag = true;
					}
				}
			}
		});
		return {minimumCharge:this.minimumChargeFlag, errorMessage:this.errorMessage}
	}
 
	checkSlotsWithCommercials(summary: EsUsageSummary, consumerCompanies: Array<TradeRelationship>){
		var hasIssue: boolean = false;
		var resultMessage = "";
		var tr: TradeRelationship;
		tr = consumerCompanies.filter((item) => item.buyerCompServiceId == summary.buyerCompanyServiceId)[0];
		if(parseInt(summary.c1) > parseInt(tr.c1) ){
			hasIssue = true; resultMessage = summary.buyerCompanyServiceNumber+" --> Allocated C1("+summary.c1+") is more than C1 in commercial("+tr.c1+")";
		}
		else if(parseInt(summary.c2) > parseInt(tr.c2) ){
			hasIssue = true; resultMessage = summary.buyerCompanyServiceNumber+" --> Allocated C2("+summary.c2+") is more than C2 in commercial("+tr.c2+")";
		}
		else if(parseInt(summary.c3) > parseInt(tr.c3) ){
			hasIssue = true; resultMessage = summary.buyerCompanyServiceNumber+" --> Allocated C3("+summary.c3+") is more than C3 in commercial("+tr.c3+")";
		}
		else if(parseInt(summary.c4) > parseInt(tr.c4) ){
			hasIssue = true; resultMessage = summary.buyerCompanyServiceNumber+" --> Allocated C4("+summary.c4+") is more than C4 in commercial("+tr.c4+")";
		}
		else if(parseInt(summary.c5) > parseInt(tr.c5) ){
			hasIssue = true; resultMessage = summary.buyerCompanyServiceNumber+" --> Allocated C5("+summary.c5+") is more than C5 in commercial("+tr.c5+")";
		}
		return {hasIssue:hasIssue, resultMessage:resultMessage};
	}

	checkSlotsWithCommercialsForAll(summaries: Array<EsUsageSummary>, consumerCompanies: Array<TradeRelationship>){
		var hasIssue: boolean = false;
		var resultMessage = "";
		for (let summary of summaries){
			var _result = this.checkSlotsWithCommercials(summary,consumerCompanies );
			if(!_result["hasIssue"]){
				hasIssue = _result["hasIssue"];
				resultMessage = summary.buyerCompanyServiceNumber +" - "+_result["resultMessage"];
				break;
			}
		}
		return {hasIssue:hasIssue, resultMessage:resultMessage};

	}
       
}