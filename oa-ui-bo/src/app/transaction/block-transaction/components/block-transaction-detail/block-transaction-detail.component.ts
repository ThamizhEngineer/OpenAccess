import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import {Component} from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { TimerObservable } from 'rxjs/observable/TimerObservable';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from '../../../../shared/common/common.service';
import { blockTransaction } from '../../../vo/block-transaction';
import { BlockTransactionService } from '../../block-transaction.service';

@Component({
    selector: 'block-transaction-detail',
    templateUrl: './block-transaction-detail.component.html',
    
    providers: [CommonService, BlockTransactionService]
})
export class BlockTransactionDetailComponent {
    
    blocktransaction: blockTransaction ;
    
    edcList = [];
    
    foredit: boolean =false;
    
    result:string;
    Reasons=[];
    ReasonsList=[];
    orgList=[];
    companyServices=[];
    searchCompanyServiceId:string="";
    genServiceNumber:string;
    
    
    error:any;
    addScreen: boolean = true;
    id:any;
    readingMonth:any;
    readingYear:any;
    searchOrgId: string = "";
   transTypesblk = [
      { "key": "01", "name": "ENERGY-ALLOCATION" },
    
    ];
  mCompServiceId: any;
  months: { value: string; name: string; }[];
  month: string;
  year: any;
  
  blkn:number;
  editbk: Params;
   
    constructor( 
      private route: ActivatedRoute,
      private router: Router,
      private commonService: CommonService,
      private commonUtils: CommonUtils,
      // private datePipe: DatePipe,
      private service: BlockTransactionService,
    ) { }    
    
    ngOnInit() {
      
      this.months = this.commonService.fetchMonths();
    
      
      
      
      this.blocktransaction = new blockTransaction();
      
    
      this.fetchOrgList();
     
       this.route.paramMap
       .subscribe((_params: Params) => {
         this.editbk=_params;
         console.log(_params)
         if(this.editbk!=null){
           this.foredit=true;
            }
         
         this.blocktransaction.month=_params.get('month');
      this.blocktransaction.mCompServiceNumber=_params.get('mCompServiceId');
      this.blocktransaction.remarks=_params.get('remarks');
      this.blocktransaction.transactionType=_params.get('transactionType');
      this.blocktransaction.status=_params.get("status");
      this.blocktransaction.year=_params.get("year")

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

          unblock(){
            console.log(this.blocktransaction.status)
            if(this.blocktransaction.status='Block'){
            this.blocktransaction.status="UnBlock";
            console.log(this.blocktransaction.status)
            
             this.service.post(this.blocktransaction.status,this.blocktransaction.mCompServiceNumber).subscribe(
            result => {
              alert("Block transaction successfully completed") ;
              this.listblcTxn();
            });
          }
          }
          
  listblcTxn() {
    this.router.navigateByUrl('/block-transaction/block-transaction-list');
    
  }
          
  }


