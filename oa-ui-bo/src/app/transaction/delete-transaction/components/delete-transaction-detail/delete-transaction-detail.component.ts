import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding, ChangeDetectionStrategy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DeleteTransactionService } from '../../services/delete-transaction.service';
import { CommonService } from './../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { deleteTransaction } from '../../../vo/delete-transaction';
import { TradeRelationshipService } from '../../../../master/trade-relationship/services/trade-relationship.service';


@Component({
  selector: 'delete-transaction-detail',
  templateUrl: './delete-transaction-detail.component.html',
  providers: [CommonService, DeleteTransactionService]
})

export class DeleteTransactionDetailComponent {
  
  DeleteTransaction : deleteTransaction;
  months = [];
  
  disableSave: boolean =false;
  disableControls: boolean = true;
  result:string;
  Reasons=[];
  ReasonsList=[];
  orgList=[];
  companyServices=[];
  searchCompanyServiceId:string="";
  genServiceNumber:string;
  genOrgId:string;
  filteredCompanyServiceList=[];
  genCompanyServices=[];
  statementMonth: string = "";
  statementYear: string = "";
  disableEdc: boolean = false;
  genCompanyName:any;
  genCompanyId:any;
  genServiceId:any;
  DeleteTransactionList:Array<deleteTransaction>;
  error:any;
  addScreen: boolean = true;
  id:any;
  readingMonth:any;
  readingYear:any;
  



  transTypes = [
    { "key": "01", "name": "Delete MeterReading||GenStmt||EnergyAllotment||EnergyAllotmentOrder" },
    { "key": "02", "name": "Delete EnergyAllotment||EnergyAllotmentOrder" }
  ];
 
  constructor( 
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private commonUtils: CommonUtils,
    // private datePipe: DatePipe,
    private service: DeleteTransactionService,
  ) { }    
  
  ngOnInit() {
    this.DeleteTransactionList= new Array<deleteTransaction>();
    this.months = this.commonService.fetchMonths();
    this.fetchOrgList();
    this.FetchReasonForDeleteTxns() ;
    this.DeleteTransaction = new deleteTransaction();
    this.statementMonth = this.commonUtils.getCurrentMonth();
    this.statementYear  = this.commonUtils.getCurrentYear();
    this.onEdcChange();

   
      this.route.paramMap
        .subscribe((_params: Params) => {
          this.id=_params.get('_genServiceNumber');
          this.readingMonth=_params.get('_readingMonth');
          this.readingYear=_params.get('_readingYear')
          console.log(this.id);
          console.log(this.readingMonth);
          console.log(this.readingYear);
          this.service.getDelTxnById(this.id,this.readingMonth,this.readingYear).subscribe(_mr => {
             this.DeleteTransaction = _mr;
        });
    });
 
   
    if(CommonUtils.getLoginUserTypeCode()=='EDC')
    {
      this.DeleteTransaction.orgId = CommonUtils.getLoginUserEDC();
      this.onEdcChange();
      this.disableEdc = true;
    }
  }


  
FetchReasonForDeleteTxns(){
  this.commonService.FetchReasonForDeleteTxn()
  .subscribe(
    Reasons => {this.Reasons = Reasons;},
    error => {
      console.error("Error loading deleteRemarks");
      console.error(error);
    });
}

  
  onEdcChange(){
    console.log("In edc change");
    console.log(this.DeleteTransaction.orgId);
    this.service.getDelTxns(this.DeleteTransaction.orgId).subscribe(
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
  
  
  onServiceChange(){
    console.log("Filtered List")
    console.log(this.filteredCompanyServiceList)
    if(this.filteredCompanyServiceList.length == 1){
      this.genCompanyName = this.filteredCompanyServiceList[0].companyName; 
      this.genCompanyId = this.filteredCompanyServiceList[0].companyId; 
      this.genServiceId= this.filteredCompanyServiceList[0].id;
    }
     
  }

  filterSellerCompanyServiceList(val: string) {
    return val ? this.companyServices.filter((s) =>  s.number.includes(val)) : this.companyServices;
  }

  listDelTxn() {
    this.router.navigateByUrl('/delete-transaction/delete-transaction-list');
  }

addDelTxn() {
this.DeleteTransactionList.push(this.DeleteTransaction);
console.log("list"+this.DeleteTransactionList);
  this.service.addDelTxn(this.DeleteTransactionList,this.DeleteTransaction.transactionTypeCode).subscribe(
      result => {
        alert("Del Txn Successfully Completed") ;
        this.listDelTxn();
      },
      error => {
        this.error=JSON.parse(error._body);
        console.log(this.error.message);
        alert(this.error.message)
        this.listDelTxn();
        console.error('Error adding DeleteTxn!');
        console.error(error);
      }
    );  
  }
 
  updateDelTxn() {
    this.service.updateDelTxn(this.DeleteTransaction).subscribe(
      result => {
        this.listDelTxn();
      },
      error => {
        console.error('Error updating Deltxns!');
        console.error(error);
      }
    );
  }
}