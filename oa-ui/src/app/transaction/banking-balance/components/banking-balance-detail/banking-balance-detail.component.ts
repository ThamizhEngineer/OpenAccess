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
    console.log("In edc change");

    this.service.getSellers(this.bankingBalance.sellerOrgId).subscribe(
      _companyServices=>{
        this.companyServices = _companyServices;
        console.log(this.companyServices);
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

  onServiceChange(){   
    console.log(this.companyServices);
    console.log("In service change");
    var companyService = this.companyServices.filter((item)=> item.id == this.bankingBalance.sellerCompanyServiceId)[0];
    this.bankingBalance.bankingCompanyId= companyService.companyId;
    this.bankingBalance.companyName= companyService.companyName;
    this.bankingBalance.bankingServiceId= companyService.bankingServiceId;

    
    console.log(this.bankingBalance);
   
   
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