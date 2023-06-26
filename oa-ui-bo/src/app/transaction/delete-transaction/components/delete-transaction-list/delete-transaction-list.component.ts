import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CommonUtils } from '../../../../shared/common/common-utils';
import { DeleteTransactionService } from '../../services/delete-transaction.service';

import { CommonService } from '../../../../shared/common/common.service';
import { deleteTransaction } from '../../../vo/delete-transaction';


@Component({
  selector: 'delete-transaction-list',
  templateUrl: './delete-transaction-list.component.html',
  providers: [DeleteTransactionService,CommonUtils]
})
export class DeleteTransactionListComponent implements OnInit {

  rows: Array<deleteTransaction>;
  tempResults: Array<deleteTransaction>;
  DeleteTransaction:deleteTransaction;

  
  searchOrgId: string = "";
  searchGenServiceNumber: string = "";
  searchReadingMonth: string = "";
  searchReadingYear: string = "";
  companyServices = [];
  edcList = [];
  voltages = [];
  months = [];
  years:any[];
  //for pagination
  count = 0;
  offset = 0;
  limit = 8;

  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  disableEdc: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: DeleteTransactionService,
    private commonService: CommonService,
    
  ) {

  }

  ngOnInit() {
    this.DeleteTransaction =  new deleteTransaction();
    this.accessAddFlag = this.commonUtils.userHasAccess("DELETE-TRANSACTION", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("DELETE-TRANSACTION", "VIEW");
    this.rows = [];
    this.DeleteTransaction.orgId = CommonUtils.getLoginUserEDC(); 
    this.months = this.commonService.fetchMonths();
   // this.DeleteTransaction.genCompanyId = CommonUtils.getLoginUserCompanyId();
   this.years = this.commonService.fetchYearList();
    this.searchReadingMonth = this.commonUtils.getPerviousMonth();
    this.searchReadingYear = this.commonUtils.getCurrentYear();
    console.log(this.DeleteTransaction.orgId);
    console.log(this.DeleteTransaction.genCompanyId);
   
    if ((this.DeleteTransaction.orgId  != "") && isFinite(parseInt(this.DeleteTransaction.orgId )) ) {
      this.searchOrgId = this.DeleteTransaction.orgId;
      this.searchGenServiceNumber = this.DeleteTransaction.genServiceNumber;
     // this.searchReadingMonth = this.DeleteTransaction.readingMonth;
     // this.searchReadingYear = this.DeleteTransaction.readingYear;
      console.log(this.searchOrgId);
      console.log(this.searchGenServiceNumber);
      this.disableEdc = true;
      this.onEdcChange();
     
    }
      
    this.fetchOrgList();

  }

  listDelTxn() {
    this.router.navigateByUrl('/delete-transaction/delete-transaction-list');
  }

  addDelTxn() {
    try {
      this.router.navigateByUrl('/delete-transaction/delete-transaction-new');
    }
    catch (e) {
      console.log(e);
    }

  }

  editDelTxn(_id: string,readingMonth:string,readingYear:string) {
    try {
      console.log("into it")
      this.router.navigateByUrl('/delete-transaction/delete-transaction-detail/' + _id+"/"+readingMonth+"/"+readingYear);
      console.log("into it")
    }
    catch (e) {
      console.log(e);
    }
  }


  searchResults() { 

    this.service.getAllDelTxn(this.searchOrgId, this.searchGenServiceNumber,this.searchReadingMonth,this.searchReadingYear).subscribe(
      _deltrans => {
        this.rows = _deltrans;
          console.log("In search result")
          console.log(this.rows);
      });
  }




  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.edcList = result;
      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }


  onEdcChange() {
    console.log("In edc change");
    this.service.getDelTxnCompanyByEdc("", this.searchOrgId, this.searchGenServiceNumber).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }
  
  ///To allow number only
  numberFormat(event){
    this.commonService.numberOnly(event)
}

}
