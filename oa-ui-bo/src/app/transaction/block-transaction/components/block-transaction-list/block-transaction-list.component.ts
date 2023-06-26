import {Component} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from '../../../../shared/common/common.service';
import { blockTransaction } from '../../../vo/block-transaction';
import { BlockTransactionService } from '../../block-transaction.service';

@Component({
    selector: 'block-transaction-list',
    templateUrl: './block-transaction-list.component.html',
    
})
export class BlockTransactionListComponent {
    rows: Array<blockTransaction>;
  months: { value: string; name: string; }[];
  GenServiceNumber: string ;
  Month: string ;
  Year: string ;



    constructor( 
        private route: ActivatedRoute,
        private router: Router,
        private commonService: CommonService,
        private commonUtils: CommonUtils,
        // private datePipe: DatePipe,
        private service: BlockTransactionService,
      ) { }


      ngOnInit() {
      this.GenServiceNumber;
        this.months = this.commonService.fetchMonths();
      }  


        searchResults() {
           this.service.test(this.GenServiceNumber,this.Month,this.Year).subscribe(
        _deltrans => {
          this.rows = _deltrans;
            console.log("In search result")
            console.log(this.rows);
        });
    }

    addDelTxn(){
        try {
            this.router.navigateByUrl('/block-transaction/block-transaction-detail');
          }
          catch (e) {
            console.log(e);
          }
      
        }
        editBlcTxn(mCompServiceNumber: string,month:string,year:string,remarks:string,transactionType:string,status:string){
          try {
          this.router.navigateByUrl('/block-transaction/block-transaction-detail/'+mCompServiceNumber+"/"+month+"/"+year+"/"+remarks+"/"+transactionType+"/"+status);
        }
      
      catch (e) {
        console.log(e);
      }
        }
      
      }