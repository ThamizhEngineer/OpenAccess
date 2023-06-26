import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
//import { SessionStorage} from "angular2-localstorage/WebStorage";


//import { ConsentEvent } from './../../services/consent.event';
import { BankingBalanceService } from './../../services/banking-balance.service';
import { CommonService } from './../../../../shared/common/common.service';
import { BankingBalance } from './../../../vo/banking-balance';
import { CommonUtils } from '../../../../shared/common/common-utils';


@Component({
  selector: 'banking-balance-detail',
  templateUrl: './banking-balance-detail.component.html',
  providers: [CommonService, BankingBalanceService, DatePipe]
})

export class BankingBalanceDetailComponent {
  bankingBalance: BankingBalance;

  rows: Array<BankingBalance>;
  months = [];
  disableControls: boolean = true;
  result:string;
  orgList=[];
  companyServices=[];
  searchCompanyServiceId:string="";
  serviceNumber:string;
  filteredCompanyServiceList=[];
  sellerCompanyServices=[];
  importResult:string="";


  disableEdc: boolean = false;


  addScreen: boolean = true;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private datePipe: DatePipe,
    private service: BankingBalanceService,





  ) {

  }
  ngOnInit() {

    this.bankingBalance = new BankingBalance();
    this.months = this.commonService.fetchMonths();
 
    this.fetchOrgList();
    this.rows = [];
  
    if(CommonUtils.getLoginUserTypeCode()=='EDC')
    {
      this.bankingBalance.sellerOrgId=CommonUtils.getLoginUserEDC();
      this.onEdcChange();
      this.disableEdc = true;
      //to be changed later --Not Permanent
      this.bankingBalance.month='01';
      this.bankingBalance.year='2019';

    }


    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        .subscribe((_params: Params) => {
          this.service.getBankingBalanceById(_params['_id']).subscribe(_mr => {
            this.bankingBalance = _mr;
            this.onEdcChange();
          });
        });
    }

  }

  onEdcChange(){
    // console.log("In edc change");

    this.service.getSellers(this.bankingBalance.sellerOrgId).subscribe(
      _companyServices=>{
        this.companyServices = _companyServices;
        // console.log(this.companyServices);
      }
    )
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

  listBankingBalance() {
    this.router.navigateByUrl('/banking-balance/banking-balance-list');
  }

//   onServiceChange(){   
//     console.log(this.companyServices);
//     console.log("In service change");
//     console.log(this.bankingBalance.sellerCompanyServiceId);
//    var companyService = this.companyServices.filter((item)=> item.id.includes(this.bankingBalance.sellerCompanyServiceId))[0];

//     // var companyService = this.companyServices.filter((item)=> item.id == this.bankingBalance.sellerCompanyServiceId)[0];
//     console.log(companyService);

//     console.log(companyService.companyId+'-'+companyService.companyName+'-'+companyService.bankingServiceId+'-'+companyService.number);
// if(companyService){
//     this.bankingBalance.bankingCompanyId= companyService.companyId;
//     this.bankingBalance.companyName= companyService.companyName;
//     this.bankingBalance.bankingServiceId= companyService.bankingServiceId;
//     this.serviceNumber=companyService.number;

// }
//     console.log(this.bankingBalance);
   
   
//   }


  onServiceChange(){
    // console.log("Filtered List")
    // console.log(this.filteredCompanyServiceList)
    if(this.filteredCompanyServiceList.length == 1){
      this.bankingBalance.companyName = this.filteredCompanyServiceList[0].companyName; 
      this.bankingBalance.bankingCompanyId = this.filteredCompanyServiceList[0].companyId; 
      this.bankingBalance.sellerCompanyServiceId= this.filteredCompanyServiceList[0].serviceId;
      this.bankingBalance.bankingServiceId= this.filteredCompanyServiceList[0].bankingServiceId; 
      // this.service.getCompanyService(this.bankingBalance.sellerCompanyServiceId).subscribe(result=>{
      //   this.companyServices = result;
      //   console.log(this.companyServices)
      //   this.bankingBalance.bankingCompanyId= this.companyServices[0].companyId;
      //   this.bankingBalance.companyName= this.companyServices[0].companyName;
      //   this.bankingBalance.bankingServiceId= this.companyServices[0].bankingServiceId;
      // });
    }
     
  }

  filterSellerCompanyServiceList(val: string) {
  
    return val ? this.companyServices.filter((s) =>  s.number.includes(val)) : this.companyServices;
  }



  saveBankingBalance(status: string) {
    //save can be add or update
  

    if (this.addScreen) {
      this.addBankingBalance();
    }
    else {
      this.updateBankingBalance();
    }
  }

  addBankingBalance() {

    console.log(this.bankingBalance);
    this.service.addBankingBalance(this.bankingBalance).subscribe(
      result => {
     
        this.listBankingBalance();
      },
      error => {
        console.error('Error adding bankingBalance Application!');
        console.error(error);
      }
    );
  }

  importBankingBalance() {

    console.log(this.bankingBalance);
    this.service.importBankingBalance(this.bankingBalance).subscribe(
      result => {
     this.importResult=result;
     console.log(this.importResult)
        this.listBankingBalance();
      },
      error => {
        console.error('Error Importing bankingBalance Application!');
        console.error(error);
      }
    );
  }
 
  updateBankingBalance() {
    this.service.updateBankingBalance(this.bankingBalance).subscribe(
      result => {
        this.listBankingBalance();
      },
      error => {
        console.error('Error updating bankingBalance!');
        console.error(error);
      }
    );
  }




}