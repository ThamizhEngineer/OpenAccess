export class EnergyAuditForGenUnitsCsv{
        serviceNo?:string;
        companyName?:string;
        orgName?:string;
        fuelTypeCode?:string;
        month?:string;
        year?:string;
        generatedc1?:string;
        generatedc2?:string;
        generatedc3?:string;
        generatedc4?:string;
        generatedc5?:string;
        totalgenerated:number;

        openBc1?:string;
        openBc2?:string;
        openBc3?:string;
        openBc4?:string;
        openBc5?:string;
        totalopenBc:Number;

        adjustedC1?:string;
        adjustedC2?:string;
        adjustedC3?:string;
        adjustedC4?:string;
        adjustedC5?:string;
        totaladjusted:number;

        allotedC1?:string;
        allotedC2?:string;
        allotedC3?:string;
        allotedC4?:string;
        allotedC5?:string;
        totalalloted:number;

        surplusC1?:string;
        surplusC2?:string;
        surplusC3?:string;
        surplusC4?:string;
        surplusC5?:string;
        totalsurplus:number;

        unAllotedC1?:string;
        unAllotedC2?:string;
        unAllotedC3?:string;
        unAllotedC4?:string;
        unAllotedC5?:string;
        totalunAlloted:number;

        closingC1?:string;
        closingC2?:string;
        closingC3?:string;
        closingC4?:string;
        closingC5?:string;
        totalclosing:number;
    }
    
    export class EnergyAuditForConsUnitsCsv{
        genServiceNo?:string;
        conServiceNo?:string;
        fuelType?:string;
        buyerCompany?:string;
        buyerEdc?:string;
        sellerComp?:string;
        sellerEdc?:string;
        allotedC1?:string;
        allotedC2?:string;
        allotedC3?:string;
        allotedC4?:string;
        allotedC5?:string;
        adjustedC1?:string;
        adjustedC2?:string;
        adjustedC3?:string;
        adjustedC4?:string;
        adjustedC5?:string;
        surplusC1?:string;
        surplusC2?:string;
        surplusC3?:string;
        surplusC4?:string;
        surplusC5?:string;
    
    }