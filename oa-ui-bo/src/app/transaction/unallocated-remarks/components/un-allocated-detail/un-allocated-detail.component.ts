import {Component, Input} from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from '../../../../shared/common/common.service';
import { UnAllocatedRemarks } from '../../../vo/unallocatedRemarks';
import { UnAllocatedService } from '../../services/un-allocated-service';

@Component({
    selector: 'un-allocated-detail',
    templateUrl: './un-allocated-detail.component.html',
    
})

export class UnAllocatedDetailComponent {
    editParam: Params;
    readonly: boolean = true ;

   @Input() getdate:string;

    Unallocated: UnAllocatedRemarks;
    UnallocatedDetail: UnAllocatedRemarks; 
    Message: any;
    
    constructor( 
        private route: ActivatedRoute,
        private router: Router,
        private commonService: CommonService,
        private commonUtils: CommonUtils,
        // private datePipe: DatePipe,
        private service: UnAllocatedService,
      ) { }    


      


    ngOnInit() {


        this.Unallocated = new UnAllocatedRemarks();
       this.getdate
    this.route.paramMap
    .subscribe((_params: Params) => {
        this.Unallocated.dispServiceNumber=_params.get('dispServicenumber');
        this.Unallocated.auditRemarks=_params.get('remarks')
        this.Unallocated.dispOrgName=_params.get('disporgname')
        this.Unallocated.stmtMonth=_params.get('month')
        this.Unallocated.stmtYear=_params.get('year')
        this.DetailForPage()
      
    });

    console.log(this.getdate)
    this.foredit();
 }

    UpdateRemarks(){
          console.log(this.Unallocated.auditRemarks)
        this.service.update(this.Unallocated.dispServiceNumber,this.Unallocated.stmtMonth,this.Unallocated.stmtYear,this.Unallocated.auditRemarks).subscribe(
            result => {
                this.Message = result
              alert(this.Message) ;
                this.unallocatedlist();

            });
    }
    unallocatedlist() {
        this.router.navigateByUrl('/un-allocated/un-allocated-list');
        
      }
      foredit(){
        
        if(this.Unallocated.auditRemarks == "null"  || this.Unallocated.auditRemarks == undefined || this.Unallocated.auditRemarks==null){
          
          this.readonly=false;
          
        }
      }

   DetailForPage(){

    this.service.gettingdetail(this.Unallocated.dispServiceNumber,this.Unallocated.stmtMonth,this.Unallocated.stmtYear).subscribe(
      result => {
          this.UnallocatedDetail = result
          this.Unallocated.dispCompanyName=this.UnallocatedDetail.dispCompanyName

      });

   }



}
