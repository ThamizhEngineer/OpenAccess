import { Es, EsUsageDetail, EsUsageSummary, generationStatementForPrint, AvailableGsUnits, AllocatedGsUnits } from './../../../vo/es';
import { Gs } from './../../../../transaction/vo/gs';
import { TradeRelationship } from './../../../../master/vo/trade-relationship';
import { Component, OnInit, HostBinding, Inject } from '@angular/core';

export class EsDetailAllocationHelper implements OnInit{
	moreThanTRFlag: boolean = false;
	allocationFlag: boolean = false;
	minimunAllocationFlag: boolean = false;
	minimumChargeFlag: boolean = false;
    es: Es;
    gs: Gs;
	esUsageSummary: EsUsageSummary;
    esUsageDetail: EsUsageDetail;
    availableGsUnits: Array<AvailableGsUnits>;
    allocatedGsunits: Array<AllocatedGsUnits>;
    countC1:any;countC2:any;countC3:any;countC4:any;countC5:any;count:any;
    chargeFlag: boolean = false;
    ngOnInit() {
		this.es = new Es();
		this.es.energySaleUsageSummaries = Array<EsUsageSummary>();
		this.esUsageSummary = new EsUsageSummary();
		this.availableGsUnits = [];
		this.allocatedGsunits = [];

	}
	availableGsUnitsForPrint(es:Es,gs:Gs) {
		this.es=es;
		this.gs=gs;
		this.availableGsUnits=[];
		var totalBank = 0; var totalAlloc = 0;
		var totalGc = 0; var totalC = 0;

		totalGc = (parseInt(this.es.availGc1) + parseInt(this.es.availGc2) + parseInt(this.es.availGc3) + parseInt(this.es.availGc4) + parseInt(this.es.availGc5));
		totalGc = isNaN(totalGc) ? 0 : totalGc;

		totalBank = (parseInt(this.es.availBc1) + parseInt(this.es.availBc2) + parseInt(this.es.availBc3) + parseInt(this.es.availBc4) + parseInt(this.es.availBc5));
		totalBank = isNaN(totalBank) ? 0 : totalBank;

		totalC = (parseInt(this.es.availc1) + parseInt(this.es.availc2) + parseInt(this.es.availc3) + parseInt(this.es.availc4) + parseInt(this.es.availc5));
		totalC = isNaN(totalC) ? 0 : totalC;
		totalAlloc = totalBank + parseInt(this.es.netGeneration);
		var totalBankStr = totalBank.toString();
		var totalCStr = totalC.toString();
		var totalGcStr = totalGc.toString();
		var totalStr = totalAlloc.toString();

		 this.countC1=(parseInt(this.es.availGc1)+parseInt(this.es.availBc1));
		 this.countC2=(parseInt(this.es.availGc2)+parseInt(this.es.availBc2));
		 this.countC3=(parseInt(this.es.availGc3)+parseInt(this.es.availBc3));
		 this.countC4=(parseInt(this.es.availGc4)+parseInt(this.es.availBc4));
		 this.countC5=(parseInt(this.es.availGc5)+parseInt(this.es.availBc5));
		this.count =this.countC1+this.countC2+this.countC3+this.countC4+this.countC5;



		

		this.availableGsUnits.push({
			"heading": "Powerplant",
			"c1": parseInt(this.es.availGc1).toString(),
			"c2": parseInt(this.es.availGc2).toString(),
			"c3": parseInt(this.es.availGc3).toString(),
			"c4": parseInt(this.es.availGc4).toString(),
			"c5": parseInt(this.es.availGc5).toString(),
			"total": totalGcStr,
		});
		this.availableGsUnits.push({
			"heading": "Banking",
			"c1": parseInt(this.es.availBc1).toString(),
			"c2": parseInt(this.es.availBc2).toString(),
			"c3": parseInt(this.es.availBc3).toString(),
			"c4": parseInt(this.es.availBc4).toString(),
			"c5": parseInt(this.es.availBc5).toString(),
			"total": totalBankStr,
		});
		this.availableGsUnits.push({
			"heading": "Total",
			"c1": this.countC1,
			"c2":this.countC2,
			"c3": this.countC3,
			"c4": this.countC4,
			"c5": this.countC5,
			"total": this.count,
		});

		// console.log("es helper availableGsUnits ");
		// console.log(this.availableGsUnits);
		return {availableGsUnits:this.availableGsUnits}
	}
	allocatedGsUnitsForPrint(es:Es) {
		this.es=es;
		
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
	checkAllocatedUnits(es:Es,gs:Gs) {
		this.allocationFlag = false;
		this.chargeFlag = false;
		this.es = es;
		this.gs= gs;
		this.availableGsUnitsForPrint(this.es,this.gs);
		this.allocatedGsUnitsForPrint(this.es);

        this.countC1=(parseInt(this.es.availGc1)+parseInt(this.es.availBc1));
        this.countC2=(parseInt(this.es.availGc2)+parseInt(this.es.availBc2));
        this.countC3=(parseInt(this.es.availGc3)+parseInt(this.es.availBc3));
        this.countC4=(parseInt(this.es.availGc4)+parseInt(this.es.availBc4));
        this.countC5=(parseInt(this.es.availGc5)+parseInt(this.es.availBc5));
        this.count =this.countC1+this.countC2+this.countC3+this.countC4+this.countC5;
		
		var c1Total = 0;
		var c2Total = 0;
		var c3Total = 0;
		var c4Total = 0;
		var c5Total = 0;
		var c1Bank = 0;
		var c2Bank = 0;
		var c3Bank = 0;
		var c4Bank = 0;
		var c5Bank = 0;
		var c1Sum = 0;
		var c2Sum = 0;
		var c3Sum = 0;
		var c4Sum = 0;
		var c5Sum = 0;
		var totalgc = 0;
		var totalbc = 0;
		var totalc = 0;
		var c1Charge=0;
		var c2Charge=0;
		var c3Charge=0;
		var c4Charge=0;
		var c5Charge=0;
		var c6Charge=0;
		var c7Charge=0;
		var c8Charge=0;

		this.es.energySaleUsageSummaries.forEach(function (summary) {
			c1Total = c1Total + parseInt(summary.c1);
			c2Total = c2Total + parseInt(summary.c2);
			c3Total = c3Total + parseInt(summary.c3);
			c4Total = c4Total + parseInt(summary.c4);
			c5Total = c5Total + parseInt(summary.c5);
			summary.c001TotalCharge = ((summary.c001TotalCharge=="" || summary.c001TotalCharge==null?"0":summary.c001TotalCharge));
			summary.c002TotalCharge = ((summary.c002TotalCharge==""|| summary.c002TotalCharge==null?"0":summary.c002TotalCharge));
			summary.c003TotalCharge = ((summary.c003TotalCharge==""|| summary.c003TotalCharge==null?"0":summary.c003TotalCharge));
			summary.c004TotalCharge = ((summary.c004TotalCharge==""|| summary.c004TotalCharge==null?"0":summary.c004TotalCharge));
			summary.c005TotalCharge = ((summary.c005TotalCharge==""|| summary.c005TotalCharge==null?"0":summary.c005TotalCharge));
			summary.c006TotalCharge = ((summary.c006TotalCharge==""|| summary.c006TotalCharge==null?"0":summary.c006TotalCharge));
			summary.c007TotalCharge = ((summary.c007TotalCharge==""|| summary.c007TotalCharge==null?"0":summary.c007TotalCharge));
			summary.c008TotalCharge = ((summary.c008TotalCharge==""|| summary.c008TotalCharge==null?"0":summary.c008TotalCharge));
			c1Charge = c1Charge +parseFloat(summary.c001TotalCharge);
			c2Charge = c2Charge +parseFloat(summary.c002TotalCharge);
			c3Charge = c3Charge +parseFloat(summary.c003TotalCharge);
			c4Charge = c4Charge +parseFloat(summary.c004TotalCharge);
			c5Charge = c5Charge +parseFloat(summary.c005TotalCharge);
			c6Charge = c6Charge +parseFloat(summary.c006TotalCharge);
			c7Charge = c7Charge +parseFloat(summary.c007TotalCharge);
			c8Charge = c8Charge +parseFloat(summary.c008TotalCharge);


		})
	
		// if (c1Total > parseInt(this.gs.c1)) {
		if (c1Total > parseInt(this.countC1)) {
			alert("Allocated Units cannot be more than available Units");
			this.allocationFlag = true;
		
		} else {

			if (c1Total > parseInt(this.gs.generationStatementSlots[0].netUnits)) {				
				this.es.gc1 = this.allocatedGsunits[0].c1 = this.gs.generationStatementSlots[0].netUnits;
				c1Bank = c1Total - parseInt(this.allocatedGsunits[0].c1);
				this.es.bc1 = this.allocatedGsunits[1].c1 = c1Bank.toString();
				c1Sum = parseInt(this.allocatedGsunits[0].c1) + parseInt(this.allocatedGsunits[1].c1);
				this.es.c1 = this.allocatedGsunits[2].c1 = c1Sum.toString();
			} else if (c1Total <= parseInt(this.gs.generationStatementSlots[0].netUnits)) {
				this.es.gc1 = this.allocatedGsunits[0].c1 = c1Total.toString();
				this.es.bc1 = this.allocatedGsunits[1].c1 = c1Bank.toString();
				c1Sum = parseInt(this.allocatedGsunits[0].c1) + parseInt(this.allocatedGsunits[1].c1);
				this.es.c1 = this.allocatedGsunits[2].c1 = c1Sum.toString();
			}

		}

		if (c2Total > parseInt(this.countC2)) {
			alert("Allocated Units cannot be more than available Units");
			this.allocationFlag = true;
		
		} else {

			if (c2Total > parseInt(this.gs.generationStatementSlots[1].netUnits)) {
				this.es.gc2 = this.allocatedGsunits[0].c2 = this.gs.generationStatementSlots[1].netUnits;
				c2Bank = c2Total - parseInt(this.allocatedGsunits[0].c2);
				this.es.bc2 = this.allocatedGsunits[1].c2 = c2Bank.toString();
				c2Sum = parseInt(this.allocatedGsunits[0].c2) + parseInt(this.allocatedGsunits[1].c2);
				this.es.c2 = this.allocatedGsunits[2].c2 = c2Sum.toString();
			} else if (c2Total <= parseInt(this.gs.generationStatementSlots[1].netUnits)) {
				this.es.gc2 = this.allocatedGsunits[0].c2 = c2Total.toString();
				this.es.bc2 = this.allocatedGsunits[1].c2 = c2Bank.toString();
				c1Sum = parseInt(this.allocatedGsunits[0].c2) + parseInt(this.allocatedGsunits[1].c2);
				this.es.c2 = this.allocatedGsunits[2].c2 = c1Sum.toString();
			}
		}

		if (c3Total > parseInt(this.countC3)) {
			alert("Allocated Units cannot be more than available Units");
			this.allocationFlag = true;
			
		} else {

			if (c3Total > parseInt(this.gs.generationStatementSlots[2].netUnits)) {
				this.es.gc3 = this.allocatedGsunits[0].c3 = this.gs.generationStatementSlots[2].netUnits;
				c3Bank = c3Total - parseInt(this.allocatedGsunits[0].c3);
				this.es.bc3 = this.allocatedGsunits[1].c3 = c3Bank.toString();
				c3Sum = parseInt(this.allocatedGsunits[0].c3) + parseInt(this.allocatedGsunits[1].c3);
				this.es.c3 = this.allocatedGsunits[2].c3 = c3Sum.toString();
			} else if (c3Total <= parseInt(this.gs.generationStatementSlots[2].netUnits)) {
				this.es.gc3 = this.allocatedGsunits[0].c3 = c3Total.toString();
				this.es.bc3 = this.allocatedGsunits[1].c3 = c3Bank.toString();
				c3Sum = parseInt(this.allocatedGsunits[0].c3) + parseInt(this.allocatedGsunits[1].c3);
				this.es.c3 = this.allocatedGsunits[2].c3 = c3Sum.toString();
			}
		}

		if (c4Total > parseInt(this.countC4)) {
			alert("Allocated Units cannot be more than available Units");
			this.allocationFlag = true;
	
		} else {

			if (c4Total > parseInt(this.gs.generationStatementSlots[3].netUnits)) {
				this.es.gc4 = this.allocatedGsunits[0].c4 = this.gs.generationStatementSlots[3].netUnits;
				c4Bank = c4Total - parseInt(this.allocatedGsunits[0].c4);
				this.es.bc4 = this.allocatedGsunits[1].c4 = c4Bank.toString();
				c4Sum = parseInt(this.allocatedGsunits[0].c4) + parseInt(this.allocatedGsunits[1].c4);
				this.es.c4 = this.allocatedGsunits[2].c4 = c4Sum.toString();
			} else if (c4Total <= parseInt(this.gs.generationStatementSlots[3].netUnits)) {
				this.es.gc4 = this.allocatedGsunits[0].c4 = c4Total.toString();
				this.es.bc4 = this.allocatedGsunits[1].c4 = c4Bank.toString();
				c4Sum = parseInt(this.allocatedGsunits[0].c4) + parseInt(this.allocatedGsunits[1].c4);
				this.es.c4 = this.allocatedGsunits[2].c4 = c4Sum.toString();
			}
		}

		if (c5Total > parseInt(this.countC5)) {
			alert("Allocated Units cannot be more than available Units");
			this.allocationFlag = true;

		} else {

			if (c5Total > parseInt(this.gs.generationStatementSlots[4].netUnits)) {
				this.es.gc5 = this.allocatedGsunits[0].c5 = this.gs.generationStatementSlots[4].netUnits;
				c5Bank = c5Total - parseInt(this.allocatedGsunits[0].c5);
				this.es.bc5 = this.allocatedGsunits[1].c5 = c5Bank.toString();
				c5Sum = parseInt(this.allocatedGsunits[0].c5) + parseInt(this.allocatedGsunits[1].c5);
				this.es.c5 = this.allocatedGsunits[2].c5 = c5Sum.toString();
			} else if (c5Total <= parseInt(this.gs.generationStatementSlots[4].netUnits)) {
				this.es.gc5 = this.allocatedGsunits[0].c5 = c5Total.toString();
				this.es.bc5 = this.allocatedGsunits[1].c5 = c5Bank.toString();
				c5Sum = parseInt(this.allocatedGsunits[0].c5) + parseInt(this.allocatedGsunits[1].c5);
				this.es.c5 = this.allocatedGsunits[2].c5 = c5Sum.toString();
			}
		}
		
		this.gs.generationStatementCharges.forEach(charge=>{
			
		
			if(charge.totalCharges==null){
				charge.totalCharges = "0";
			}
			if(charge.chargeCode == "C008"){
			
				
				if(c8Charge > parseFloat(charge.totalCharges)){
					alert("Allocated charges cannot be more than available charges");
					this.chargeFlag = true;				
				}
			}
			if(charge.chargeCode == "C007"){
			
				
				if(c7Charge > parseFloat(charge.totalCharges)){
					alert("Allocated charges cannot be more than available charges");
					this.chargeFlag = true;				
				}
			}
			if(charge.chargeCode == "C006"){
			
				
				if(c6Charge > parseFloat(charge.totalCharges)){
					alert("Allocated charges cannot be more than available charges");
					this.chargeFlag = true;		
		
				}
			}
			if(charge.chargeCode == "C005"){
				if(c5Charge > parseFloat(charge.totalCharges)){
					alert("Allocated charges cannot be more than available charges");
					this.chargeFlag = true;
				}
			}
			
			if(charge.chargeCode == "C004"){
				if(c4Charge> parseFloat(charge.totalCharges)){
					alert("Allocated charges cannot be more than available charges");
					this.chargeFlag = true;
				}
			}
			if(charge.chargeCode == "C003"){
				if(c3Charge > parseFloat(charge.totalCharges)){
					alert("Allocated charges cannot be more than available charges");
					this.chargeFlag = true;
				}
			}
		
			if(charge.chargeCode == "C002"){
				if(c2Charge > parseFloat(charge.totalCharges)){
					alert("Allocated charges cannot be more than available charges");
					this.chargeFlag = true;
				}
			}
			if(charge.chargeCode == "C001"){
				
				if(c1Charge > parseFloat(charge.totalCharges)){

					alert("Allocated charges cannot be more than available charges");
					this.chargeFlag = true;
				}
			}
		});
		totalgc = parseInt(this.es.gc1) + parseInt(this.es.gc2) + parseInt(this.es.gc3) + parseInt(this.es.gc4) + parseInt(this.es.gc5);
		totalbc = parseInt(this.es.bc1) + parseInt(this.es.bc2) + parseInt(this.es.bc3) + parseInt(this.es.bc4) + parseInt(this.es.bc5);
		totalc = parseInt(this.es.c1) + parseInt(this.es.c2) + parseInt(this.es.c3) + parseInt(this.es.c4) + parseInt(this.es.c5);
		this.es.netGeneration = this.allocatedGsunits[0].total = totalgc.toString();
		this.es.totalBankUnitsUsed = this.allocatedGsunits[1].total = totalbc.toString();
		this.es.netAllocation = this.allocatedGsunits[2].total = totalc.toString();
		console.log(this.allocatedGsunits)
		return {allocation:this.allocationFlag,charge:this.chargeFlag,es:this.es,esUsageSummary:this.esUsageSummary,allocatedGsunits:this.allocatedGsunits}
	}
	checkMinumumAllocatedUnits(es:Es,gs:Gs) {
		var isFossilFuel: boolean = false;

		if(this.gs.dispFuelTypeGroup=='FF'){
			isFossilFuel = true;
		}
		this.minimumChargeFlag=false;

		// var c1 = 0;
		// var c2 = 0;
		// var c3 = 0;
		// var c4 = 0;
		// var c5 = 0;
		// var gc1 = 0;
		// var gc2 = 0;
		// var gc3 = 0;
		// var gc4 = 0;
		// var gc5 = 0;
	
		var c1Charge=0;
		var c2Charge=0;
		var c3Charge=0;
		var c4Charge=0;
		var c5Charge=0;
		var c6Charge=0;
		var c7Charge=0;
		var c8Charge=0;
        // gc1=parseInt(this.es.availGc1);
        // gc2=parseInt(this.es.availGc2);
		// gc3=parseInt(this.es.availGc3);
		// gc4=parseInt(this.es.availGc4);
		// gc5=parseInt(this.es.availGc5);
		
		this.es.energySaleUsageSummaries.forEach(function (summary) {
		// 	c1 = c1+parseInt(summary.c1);
		// 	c2 = c2+parseInt(summary.c2);
		// 	c3 = c3+parseInt(summary.c3);
		// 	c4 = c4+parseInt(summary.c4);
		// 	c5 = c5+parseInt(summary.c5);

			c1Charge = c1Charge +parseFloat(summary.c001TotalCharge);
			c2Charge = c2Charge +parseFloat(summary.c002TotalCharge);
			c3Charge = c3Charge +parseFloat(summary.c003TotalCharge);
			c4Charge = c4Charge +parseFloat(summary.c004TotalCharge);
			c5Charge = c5Charge +parseFloat(summary.c005TotalCharge);
			c6Charge = c6Charge +parseFloat(summary.c006TotalCharge);
			c7Charge = c7Charge +parseFloat(summary.c007TotalCharge);
			c8Charge = c8Charge +parseFloat(summary.c008TotalCharge);
		


		});
		// if (c1 <gc1) {
		// 	alert("Total C1 Allocated Units cannot be less than available Units");
		// 	this.minimunAllocationFlag = true;
	
		// }
		// if (c2 <gc2) {
		// 	alert("Total C2 Allocated Units cannot be less than available Units");
		// 	this.minimunAllocationFlag = true;
	
		// }
		// if (c3 <gc3) {
		// 	alert("Total C3 Allocated Units cannot be less than available Units");
		// 	this.minimunAllocationFlag = true;
	
		// }
		// if (c4 <gc4) {
		// 	alert("Total C4 Allocated Units cannot be less than available Units");
		// 	this.minimunAllocationFlag = true;
	
		// }
		// if (c5 <gc5) {
		// 	alert("Total C5 Allocated Units cannot be less than available Units");
		// 	this.minimunAllocationFlag = true;
	
		// }

		this.gs.generationStatementCharges.forEach(charge=>{
			 
		
			if(charge.totalCharges==null){
				charge.totalCharges = "0";
			}
			if(!isFossilFuel){ //charges not allocated for fossilfuel
				if(charge.chargeCode == "C008"){
				
					
					if(c8Charge < parseFloat(charge.totalCharges)){
						alert("Total C008 Allocated charges cannot be less than available charges");
						this.minimumChargeFlag = true;				
					}
				}
				if(charge.chargeCode == "C007"){
				
					
					if(c7Charge < parseFloat(charge.totalCharges)){
						alert("Total C007 Allocated charges cannot be less than available charges");
						this.minimumChargeFlag = true;				
					}
				}
				if(charge.chargeCode == "C006"){
				
					
					if(c6Charge < parseFloat(charge.totalCharges)){
						alert("Total C006 Allocated charges cannot be less than available charges");
						this.minimumChargeFlag = true;		
			
					}
				}
				if(charge.chargeCode == "C005"){
					if(c5Charge < parseFloat(charge.totalCharges)){
						alert("Total C005 Allocated charges cannot be less than available charges");
						this.minimumChargeFlag = true;
					}
				}
				
				if(charge.chargeCode == "C004"){
					if(c4Charge < parseFloat(charge.totalCharges)){
						alert("Total C004 Allocated charges cannot be less than available charges");
						this.minimumChargeFlag = true;
					}
				}
				if(charge.chargeCode == "C003"){
					if(c3Charge < parseFloat(charge.totalCharges)){
						alert("Total C003 Allocated charges cannot be less than available charges");
						this.minimumChargeFlag = true;
					}
				}
			
				if(charge.chargeCode == "C002"){
					if(c2Charge < parseFloat(charge.totalCharges)){
						alert("Total C002 Allocated charges cannot be less than available charges");
						this.minimumChargeFlag = true;
					}
				}
				if(charge.chargeCode == "C001"){
					
					if(c1Charge < parseFloat(charge.totalCharges)){
	
						alert("Total C001 Allocated charges cannot be less than available charges");
						this.minimumChargeFlag = true;
					}
				}
			}
			
		
		});
		return {minimunAllocation:this.minimunAllocationFlag,minimumCharge:this.minimumChargeFlag}
	}

	checkSlotsWithCommercials(summary: EsUsageSummary, consumerCompanies: Array<TradeRelationship>){
		var hasIssue: boolean = false;
		var resultMessage = "";
		var tr: TradeRelationship;
		tr = consumerCompanies.filter((item) => item.buyerCompServiceId == summary.buyerCompanyServiceId)[0];
		if(parseInt(summary.c1) > parseInt(tr.c1) ){
			hasIssue = true; resultMessage = "Allocated C2("+summary.c2+") is more than C2 in commercial("+tr.c2+")";
		}
		else if(parseInt(summary.c2) > parseInt(tr.c2) ){
			hasIssue = true; resultMessage = "Allocated C2("+summary.c2+") is more than C2 in commercial("+tr.c2+")";
		}
		else if(parseInt(summary.c3) > parseInt(tr.c3) ){
			hasIssue = true; resultMessage = "Allocated C3("+summary.c3+") is more than C3 in commercial("+tr.c3+")";
		}
		else if(parseInt(summary.c4) > parseInt(tr.c4) ){
			hasIssue = true; resultMessage = "Allocated C4("+summary.c4+") is more than C4 in commercial("+tr.c4+")";
		}
		else if(parseInt(summary.c5) > parseInt(tr.c5) ){
			hasIssue = true; resultMessage = "Allocated C5("+summary.c5+") is more than C5 in commercial("+tr.c5+")";
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
