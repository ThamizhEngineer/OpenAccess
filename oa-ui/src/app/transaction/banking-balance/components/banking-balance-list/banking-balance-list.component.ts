import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

// import { MeterReadingDetailComponent } from './../../components/banking-balance-detail/banking-balance-detail.component';

import { BankingBalanceService } from './../../services/banking-balance.service';
import { BankingBalance} from './../../../vo/banking-balance'; 
import { CommonService } from './../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';

@Component({
  selector: 'banking-balance-list',
  templateUrl: './banking-balance-list.component.html',
  //styleUrls: ['./-list.component.scss']
  providers: [BankingBalanceService,CommonUtils]
})
export class BankingBalanceListComponent implements OnInit {

  orgList = [];
  bankingBalance: BankingBalance;



  filteredEDCs = [];
  months = [];
  searchcompanyServiceNumber: string = "";
  searchcompanyServiceId: string = "";
  searchmodemNumber: string = "";
  searchsellermeterNumber: string = "";
  searchcompanyName: string = "";
  searchorgId: string = "";
  searchMonth: string = "";
  searchYear: string = "";
  disableEdc: boolean = false;
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  companyServices=[];
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,

    private service: BankingBalanceService
  ) {

    // this.fetchEDCs();

  }
  rows: Array<BankingBalance>;

  ngOnInit() {
    this.accessAddFlag = this.commonUtils.userHasAccess("METER-READING", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("METER-READING", "VIEW");
    this.rows = [];
    this.bankingBalance = new BankingBalance();
    this.fetchEDCs();
    this.months = this.commonService.fetchMonths();
    this.bankingBalance.sellerOrgId = CommonUtils.getLoginUserEDC();
    console.log(CommonUtils.getLoginUserEDC())
    if (this.bankingBalance.sellerOrgId != "") {
      console.log("in edc select")
      this.searchorgId = this.bankingBalance.sellerOrgId;
      this.disableEdc = true;
    }


  }


  //     filterEDCs(val: string) {

  //   return val ? this.orgId.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.orgId;
  // }

  //     fetchEDCs(){
  //     this.commonService.fetchEDCs().subscribe(
  //     result => { this.orgId = result;
  //      },    
  //     error => {
  //     console.error('Error loading orgs!');
  //      console.error(error);
  //      }  );
  //     }

  searchResults() {
    if (this.bankingBalance.sellerOrgId != "") {
      this.searchorgId = this.bankingBalance.sellerOrgId;
    }


    this.service.getBankingBalance(this.searchcompanyServiceId, this.searchMonth, this.searchYear, this.searchorgId).subscribe(
      _es => {
        this.rows = _es;
        console.log("In search result")
        console.log(this.rows);
      });
  }
  editBankingBalance(_id: string) {
    try {
      console.log(_id);
      this.router.navigateByUrl('/banking-balance/banking-balance-detail/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
    }
    catch (e) {
      console.log(e);
    }

  }

  onEdcChange() {
    console.log("In edc change");
    this.service.getBankingBalanceCompanyByEdc(this.searchorgId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }
  newBankingBalance() {
    try {

      this.router.navigateByUrl('/banking-balance/banking-balance-new');
     
    }
    catch (e) {
      console.log(e);
    }

  }
  fetchEDCs() {

    this.commonService.fetchEDCs().subscribe(
      result => {

        this.orgList = result;

      });
  }


}