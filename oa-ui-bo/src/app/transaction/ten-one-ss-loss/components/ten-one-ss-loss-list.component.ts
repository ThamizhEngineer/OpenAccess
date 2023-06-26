import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import{SubstationLoss} from '../../vo/SubstationLoss'
import{CommonService} from '../../../../app/shared/common/common.service';
import { TenOneSSLossAdd } from './ten-one-ss-add/ten-one-ss-loss-add.component';
import{TenOneSSLossService} from '../services/ten-one-ss-loss.service'
import { CommonUtils } from '../../../shared/common/common-utils';
import { TenOneSSLossView } from './ten-one-ss-view/ten-one-ss-loss-view.component';
@Component({
  selector: 'ten-one-ss-loss',
  templateUrl: './ten-one-ss-loss-list.component.html',

})
export class TenOneSSLossList implements OnInit {
    months = [];
    orgList = [];
    years:any[];
    rows: Array<SubstationLoss>;
    disableEdc: boolean = false;
    substationLoss:SubstationLoss
    config: MatDialogConfig = {
        disableClose: false,
        width: '100%',
        height: '70%',
        position: {
          top: '',
          bottom: '',
          left: '10%',
          right: ''
        }
      };
    dialogRef: MatDialogRef<TenOneSSLossView>;
  constructor(
    private commonService: CommonService,
    private commonUtils: CommonUtils,
    public dialog: MatDialog,
    private router: Router,
    private tenOneSSLossService:TenOneSSLossService
    
  ) {

  }

  ngOnInit() {
  this.substationLoss= new SubstationLoss();
    this.months = this.commonService.fetchMonths();
    this.substationLoss.month = this.commonUtils.getPerviousMonth();
    this.substationLoss.year = this.commonUtils.getCurrentYear();
    this.years = this.commonService.fetchYearList();
    this.substationLoss.orgId = CommonUtils.getLoginUserEDC();
    this.rows = [];

    if (this.substationLoss.orgId  != "") {
   
      this.disableEdc = true;
    }
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
      });
     
}
newEs() {
    try {

      this.dialogRef = this.dialog.open(TenOneSSLossView, this.config);
      this.dialogRef.afterClosed().subscribe(result => {
      if(result!=null && result!=undefined){

      }
        this.dialogRef = null;
      });
     
    }
    catch (e) {
      console.log(e);
    }
  }
  editSubstation(_id: string) {
    try {
    
      this.router.navigateByUrl('/ten-one-ss-loss/ten-one-ss-loss-detail/' + _id);
   
    }
    catch (e) {
      console.log(e);
    }

  }
  searchResults(){
    this.tenOneSSLossService.getAllSubstationLoss(this.substationLoss.orgId,this.substationLoss.month,this.substationLoss.year).subscribe(x=>{
      this.rows = x;
      
      this.substationLoss.orgName = CommonUtils.getLoginUserEDC();
     
    })
  }
  

}
