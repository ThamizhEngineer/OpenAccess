import { Component, OnInit} from '@angular/core';
import {  Router,ActivatedRoute, Params } from '@angular/router';
import { ReportService } from '../../../../report/services/report.service';
import { CommonService } from '../../../../shared/common/common.service';
import { saveAs } from 'file-saver';
import { EnergyAdjustedOrder } from '../../../vo/EnergyAdjustedOrder';
import { EnergyAuditForConsUnitsCsv } from '../../../vo/AuditReport';
import { ExcelExportService } from '../../../../shared/services/excelExport';

@Component({
    selector:'energy-audit-detail',
    templateUrl:'./energy-audit-detail.component.html',
    providers: [ ReportService]
})

export class EnergyAuditDetailComponent implements OnInit {

    energyAdjOrderList=[];
    energyAdjOrder:EnergyAdjustedOrder;
    energyAdjOrderCsv:EnergyAdjustedOrder;
    tempList=[];
    serviceNo:string;
    month:string;
    months=[];
    year:string;
    sellerCompany:string;
    sellerEdc:string;
    message:string;
    eaListForCsv:Array<EnergyAuditForConsUnitsCsv>;
    fromMonth:string;
    fromYear:string;
    toMonth:string;
    toYear:string;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private reportService:ReportService,
        private commonService:CommonService,
        private excelService: ExcelExportService
    ){}
    ngOnInit(){
        this.energyAdjOrderList=[];
        this.eaListForCsv=[];
        this.months = this.commonService.fetchMonths();
        this.route.params
            .subscribe((_params: Params)=>{
                console.log("gsId",_params['id'])
                this.reportService.getEnergyAdjustedOrder(_params['month'],_params['year'],_params['genServNumber']).subscribe(x=>{
                    this.energyAdjOrderList=x;
                    this.sellerCompany=this.energyAdjOrderList[0].dSellCompName;
                    this.sellerEdc=this.energyAdjOrderList[0].dSellOrgName;
                    this.energyAdjOrderList.forEach(x=>{
                        this.energyAdjOrder=x;
                        var res = this.setCsvList(this.energyAdjOrder);
                        this.eaListForCsv = res["eaListForCsv"];
                    })
                })
                this.serviceNo = _params['genServNumber'];
                this.month = _params['month'];
                this.year = _params['year'];
                this.fromMonth = _params['searchfromMonth'];
                this.fromYear = _params['searchFromYear'];
                this.toMonth = _params['searchtoMonth'];
                this.toYear = _params['searchToYear'];
            });
    }
    
    
    listGss(genServNumber,searchfromMonth,searchFromYear,searchtoMonth,searchToYear){
        this.router.navigateByUrl('/reports/energy-audit-list/'+genServNumber+'/'+searchfromMonth+'/'+searchFromYear+'/'+searchtoMonth+'/'+searchToYear);
    }

    printConsumerWise(){
        this.reportService.printEnergyAuditReport(this.month,this.year,this.serviceNo).subscribe(xs=>{
            this.openDownload(xs);
            this.message = "Downloaded Successfully"
        },
        error=>{
            if (JSON.parse(error._body).message) {
              this.message = JSON.parse(error._body).message;
            } else {
              this.message = "Too many request please try again later"
            }
          });
    }
    openDownload(data:Response){
        let x_filename = "energy-audit-report.pdf";
        saveAs(data.blob(), x_filename);
    }

    setCsvList(ea:EnergyAdjustedOrder){
        this.energyAdjOrderCsv=ea;
        // console.log(this.energyAdjOrderCsv)
        this.eaListForCsv.push({
            "genServiceNo": this.energyAdjOrderCsv.sellerServiceNumber,
            "conServiceNo": this.energyAdjOrderCsv.buyerServiceNumber,
            "fuelType": this.energyAdjOrderCsv.fuelTypeCode,
            "buyerCompany": this.energyAdjOrderCsv.dBuyerCompName,
            "buyerEdc": this.energyAdjOrderCsv.dBuyerOrgName,
            "sellerComp":this.sellerCompany,
            "sellerEdc":this.sellerEdc,
            "allotedC1": this.energyAdjOrderCsv.allotedC1,
            "allotedC2": this.energyAdjOrderCsv.allotedC2,
            "allotedC3": this.energyAdjOrderCsv.allotedC3,
            "allotedC4": this.energyAdjOrderCsv.allotedC4,
            "allotedC5": this.energyAdjOrderCsv.allotedC5,
            "adjustedC1": this.energyAdjOrderCsv.adjustedC1,
            "adjustedC2": this.energyAdjOrderCsv.adjustedC2,
            "adjustedC3": this.energyAdjOrderCsv.adjustedC3,
            "adjustedC4": this.energyAdjOrderCsv.adjustedC4,
           "adjustedC5": this.energyAdjOrderCsv.adjustedC5,
           "surplusC1": this.energyAdjOrderCsv.htBankingWithLossC1,
           "surplusC2": this.energyAdjOrderCsv.htBankingWithLossC2,
            "surplusC3": this.energyAdjOrderCsv.htBankingWithLossC3,
            "surplusC4": this.energyAdjOrderCsv.htBankingWithLossC4,
            "surplusC5": this.energyAdjOrderCsv.htBankingWithLossC5,
        });  
        return {eaListForCsv:this.eaListForCsv}
    }

    exportAsXLSX(){
        console.log(this.eaListForCsv)
        let str = JSON.stringify(this.eaListForCsv);
        str = str.replace(/\"op1\":/g, "\"S.NO\":");
        str = str.replace(/\"op2\":/g, "\"ORG ID\":");
        str = str.replace(/\"op3\":/g, "\"EDC\":");
        str = str.replace(/\"op4\":/g, "\"METER READING\":");
        str = str.replace(/\"op5\":/g, "\"MONTH\":");
        str = str.replace(/\"op6\":/g, "\"YEAR\":");
        str = str.replace(/\"op7\":/g, "\"FUEL TYPE\":");
        str = str.replace(/\"op8\":/g, "\"THIRD PARTY\":");
        str = str.replace(/\"op9\":/g, "\"CAPTIVE ALLOTMENT\":");
        str = str.replace(/\"op10\":/g, "\"TOTAL ALLOTMENT\":");
        str = str.replace(/\"op11\":/g, "\"MANULAR READING\":");
        this.eaListForCsv = JSON.parse(str);
        
     this.excelService.exportAsExcelFile(this.eaListForCsv, 'AUDIT-REPORT-CON-WISE');
    }
}
