import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { DatePipe } from '@angular/common';

import { EsorderService } from '../../../../ledger/energy-sale-order/components/services/esorder.service';
import { EsOrder, EnergySaleOrderLines, EnergyCharge, EsOrderForPrint,EsOrderChargeForPrint } from '../../../../ledger/energy-sale-order/components/services/esorder';
import { CommonService } from './../../../../shared/common/common.service';
import { BankingBalanceService} from '../../../../transaction/banking-balance/services/banking-balance.service'
import { saveAs } from 'file-saver';

@Component({
  selector: 'es-order-detail',
  templateUrl: './es-order-detail.component.html',
  styleUrls: ['./es-order-detail.component.scss'],
  providers: [MatDatepickerModule,  MatNativeDateModule, DatePipe,CommonUtils]

})
export class EsOrderDetailComponent implements OnInit {
  esorder: EsOrder;
  energySaleOrderLines: EnergySaleOrderLines;
  energySaleOrderLinesList:Array<EnergySaleOrderLines>;
  esOrderChargeForPrint:EsOrderChargeForPrint;
  energyCharge: EnergyCharge;
  months = [];
  searchsellerEndOrgId: string = "";
  searchsellerCompanyServiceId: string = "";
  searchMonth: string = "";
  searchYear: string = "";
  searchEdcName: string = "";
  voltages = [];
  esorderForPrint: Array<EsOrderForPrint>;
  excessunit:any=[];
  bankingBalanceArray=[];
  bankingArray=[];
  esOrderChargeListForPrint:Array<EsOrderChargeForPrint>;
  message: string;
  availBc1 : number = 0;
  availBc2: number = 0;
  availBc3: number = 0;
  availBc4: number = 0;
  availBc5: number = 0;
  availBcTotal:number=0;  
  allotedBc1 : number = 0;
  allotedBc2: number = 0;
  allotedBc3: number = 0;
  allotedBc4: number = 0;
  allotedBc5: number = 0;
  allotedBcTotal:number=0;  
  bankingCurrc1 : number = 0;
  bankingCurrc2 : number = 0;
  bankingCurrc3 : number = 0;
  bankingCurrc4 : number = 0;
  bankingCurrc5 : number = 0;
  bankingCurrTotal : number = 0;
  lapsedCurrc1 : number = 0;
  lapsedCurrc2 : number = 0;
  lapsedCurrc3 : number = 0;
  lapsedCurrc4 : number = 0;
  lapsedCurrc5 : number = 0;
  lapsedCurrTotal : number = 0;
  surplusC1 : number = 0;
  surplusC2 : number = 0;
  surplusC3 : number = 0;
  surplusC4 : number = 0;
  surplusC5 : number = 0;
  surplusCurrTotal : number = 0;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private datePipe: DatePipe,
    private service: EsorderService,
    private bankingBalanceService: BankingBalanceService) {

  }
  rows: Array<EsOrder>;

  ngOnInit() { 
    this.esorder = new EsOrder();
    this.energySaleOrderLines = new EnergySaleOrderLines();
    this.energySaleOrderLinesList = new Array<EnergySaleOrderLines>();
    
    this.esOrderChargeListForPrint = new Array<EsOrderChargeForPrint>();
    this.months = this.commonService.fetchMonths();
    this.esorderForPrint = [];
    this.rows = [];
    this.fetchVoltages();



    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 

      this.route.params
        .subscribe((_params: Params) => {
          this.service.getEsoById(_params['_id']).subscribe(_eso => {

            this.esorder = _eso;
            this.energySaleOrderLinesList =this.esorder.energySaleOrderLines;
            // console.log(this.esorder)
            // this.esordersForPrint();
            this.setCharges();          
            this.fetchBankingBalance();

          });


        });
    }
    // this.fetchEsoById();


  }

  // formatChangesforUI() {
  //   this.esorder.modifiedDate =  (this.esorder.modifiedDate) ? this.esorder.modifiedDate.substr(0,10):"";
  // }
 
  setCharges(){
    this.energySaleOrderLinesList.forEach(line=>{
      this.esOrderChargeForPrint = new EsOrderChargeForPrint();
      this.esOrderChargeForPrint.companyServiceNumber = line.buyerCompanyServiceNumber;
      this.esOrderChargeForPrint.companyName = line.buyerCompanyName;
      // console.log(  this.esOrderChargeForPrint.companyServiceNumber);
      this.esorder.energyCharge.forEach(charge=>{
        if(charge.companyServiceNumber==this.esOrderChargeForPrint.companyServiceNumber){
          // console.log( "inside if");
          // console.log(charge);
          if(charge.chargeCode=='C001'){
            // console.log( "inside second if");
            this.esOrderChargeForPrint.C001TotalCharge=charge.totalCharges;
          }
          if(charge.chargeCode=='C002'){
            this.esOrderChargeForPrint.C002TotalCharge=charge.totalCharges;
          }
          if(charge.chargeCode=='C003'){
            this.esOrderChargeForPrint.C003TotalCharge=charge.totalCharges;
          }
          if(charge.chargeCode=='C004'){
            this.esOrderChargeForPrint.C004TotalCharge=charge.totalCharges;
          }
          if(charge.chargeCode=='C005'){
            this.esOrderChargeForPrint.C005TotalCharge=charge.totalCharges;
          }
          if(charge.chargeCode=='C006'){
            this.esOrderChargeForPrint.C006TotalCharge=charge.totalCharges;
          }
          if(charge.chargeCode=='C007'){
            this.esOrderChargeForPrint.C007TotalCharge=charge.totalCharges;
          }
          if(charge.chargeCode=='C008'){
            this.esOrderChargeForPrint.C008TotalCharge=charge.totalCharges;
          }
          if(charge.chargeCode=='C009'){
            this.esOrderChargeForPrint.C009TotalCharge=charge.totalCharges;
          }
        }
       
      })
      this.esOrderChargeListForPrint.push(this.esOrderChargeForPrint);
    });
  }
  fetchVoltages() {
    this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
      result => {
        this.voltages = result;
      },

      error => {
        console.error('Error loading Voltages');
        console.error(error);
      });
  }

  fetchBankingBalance(){
    // this.bankingBalanceService.getBankingBalance(this.esorder.sellerCompanyServiceId,this.esorder.month,this.esorder.year,'').subscribe(x=>{
    //   console.debug(x);
     
    //  if(x.length>0){      
    //     console.log(x);
    //     this.availBc1 = x[0].c1;
    //     this.availBc2 = x[0].c2;
    //     this.availBc3 = x[0].c3;
    //     this.availBc4 = x[0].c4;
    //     this.availBc5 = x[0].c5;      
    //     this.availBcTotal = parseFloat(this.availBc1+"")+parseFloat(this.availBc2+"")+parseFloat(this.availBc3+"")+parseFloat(this.availBc4+"")+parseFloat(this.availBc5+"")

    //     this.allotedBc1 = x[0].currC1;
    //     this.allotedBc2 = x[0].currC2;
    //     this.allotedBc3 = x[0].currC3;
    //     this.allotedBc4 = x[0].currC4;
    //     this.allotedBc5 = x[0].currC5;      
    //     this.allotedBcTotal = parseFloat(this.allotedBc1+"")+parseFloat(this.allotedBc2+"")+parseFloat(this.allotedBc3+"")+parseFloat(this.allotedBc4+"")+parseFloat(this.allotedBc5+"")
    //   } 
    //   this.bankingBalanceArray.push({
    //     "heading": "Banking Balance",
    //     "c1": this.availBc1,
    //     "c2": this.availBc2,
    //     "c3": this.availBc3,
    //     "c4": this.availBc4,
    //     "c5": this.availBc5,
    //     "total": this.availBcTotal,
    //   });
    //   this.esordersForPrint();

    // });

    this.bankingBalanceService.getExcessUnit(this.esorder.sellerCompanyServiceId,this.esorder.month,this.esorder.year).subscribe(x=>{
      this.excessunit=x;
      this.excessunit.forEach(loop=>{
        if(loop.excessUnitType=="BANKING"){
          this.bankingCurrc1=loop.currC1;
          this.bankingCurrc2=loop.currC2;
          this.bankingCurrc3=loop.currC3;
          this.bankingCurrc4=loop.currC4;
          this.bankingCurrc5=loop.currC5;
          this.bankingCurrTotal = parseFloat(this.bankingCurrc1+"")+parseFloat(this.bankingCurrc2+"")+parseFloat(this.bankingCurrc3+"")+parseFloat(this.bankingCurrc4+"")+parseFloat(this.bankingCurrc5+"")
        }
        if(loop.excessUnitType=="LAPSED"){
          this.lapsedCurrc1=loop.currC1;
          this.lapsedCurrc2=loop.currC2;
          this.lapsedCurrc3=loop.currC3;
          this.lapsedCurrc4=loop.currC4;
          this.lapsedCurrc5=loop.currC5;
          this.lapsedCurrTotal = parseFloat(this.lapsedCurrc1+"")+parseFloat(this.lapsedCurrc2+"")+parseFloat(this.lapsedCurrc3+"")+parseFloat(this.lapsedCurrc4+"")+parseFloat(this.lapsedCurrc5+"")
        }
        if(loop.excessUnitType=="SURPLUS-STB"){
          this.surplusC1=loop.currC1;
          this.surplusC2=loop.currC2;
          this.surplusC3=loop.currC3;
          this.surplusC4=loop.currC4;
          this.surplusC5=loop.currC5;
          this.surplusCurrTotal= parseFloat(this.surplusC1+"")+parseFloat(this.surplusC2+"")+parseFloat(this.surplusC3+"")+parseFloat(this.surplusC4+"")+parseFloat(this.surplusC5+"")
        }
      })
        this.bankingArray.push({
          "heading": "Banking",
          "c1": this.bankingCurrc1,
          "c2": this.bankingCurrc2,
          "c3": this.bankingCurrc3,
          "c4": this.bankingCurrc4,
          "c5": this.bankingCurrc5,
          "total": this.bankingCurrTotal,
          "headinglap": "Lapsed",
          "lapc1": this.lapsedCurrc1,
          "lapc2": this.lapsedCurrc2,
          "lapc3": this.lapsedCurrc3,
          "lapc4": this.lapsedCurrc4,
          "lapc5": this.lapsedCurrc5,
          "laptotal": this.lapsedCurrTotal,
          "headingsurp": "Surplus",
          "surpc1": this.surplusC1,
          "surpc2": this.surplusC2,
          "surpc3": this.surplusC3,
          "surpc4": this.surplusC4,
          "surpc5": this.surplusC5,
          "surptotal": this.surplusCurrTotal,
        });
        this.esordersForPrint();
        
    });
  }

  
  // searchResults() {

  //   this.service.getAllEso(this.esorder.orgCode, this.esorder.sellerEndOrgId, this.esorder.sellerCompanyServiceId, this.esorder.month, this.esorder.year).subscribe(
  //     _es => {
  //       this.esorder = _es;
  //       console.log("In search result")
  //       console.log(this.esorder);
  //     });

  //   this.esordersForPrint();
  // }

  // fetchEsoById(){
  //     this.service.getEsoById(this.esorder.sellerCompanyServiceId).subscribe(
  //       _eso=>{
  //         this.esorder = _eso;
  //         console.log("in fetch stuff")
  //         console.log(this.esorder)
  //         this.esordersForPrint();
  //       }
  //     )
  //   }


  listEso() {
    this.router.navigateByUrl('/es-order/es-order-list');
    // to-do
    // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

  }

   


  esordersForPrint() {

    this.esorderForPrint.push({
      "heading": "From Powerplant",
      "totalGc1": this.esorder.totalGc1,
      "totalGc2": this.esorder.totalGc2,
      "totalGc3": this.esorder.totalGc3,
      "totalGc4": this.esorder.totalGc4,
      "totalGc5": this.esorder.totalGc5,
      "total": this.esorder.totalGenUnitsSold,
    });

    this.esorderForPrint.push({
      "heading": "From Bank",
      "totalGc1": this.allotedBc1+"",
      "totalGc2": this.allotedBc2+"",
      "totalGc3": this.allotedBc3+"",
      "totalGc4": this.allotedBc4+"",
      "totalGc5": this.allotedBc5+"",
      "total": this.allotedBcTotal+"",
    });
    this.esorderForPrint.push({
      "heading": "Total",
      "totalGc1": this.esorder.totalC1,
      "totalGc2": this.esorder.totalC2,
      "totalGc3": this.esorder.totalC3,
      "totalGc4": this.esorder.totalC4,
      "totalGc5": this.esorder.totalC5,
      "total": this.esorder.totalUnitsSold,
    });


  }

  openDownload(data: Response) {
		let content_type = data.headers.get('Content-type');
		let x_filename = "Es-order-"+this.esorder.companyServiceNumber+"-"+this.esorder.month+".pdf";
    console.log(content_type)
    console.log(x_filename)
		saveAs(data.blob(), x_filename);
  }
  printEsOrder(){
    console.log(this.esorder)
    this.service.printEsOrder(this.esorder).subscribe(xs=>{
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


}