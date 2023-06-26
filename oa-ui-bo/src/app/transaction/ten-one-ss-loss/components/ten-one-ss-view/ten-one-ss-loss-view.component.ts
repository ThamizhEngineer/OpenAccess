import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { DatePipe } from '@angular/common';
import { CommonService } from './../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { TenOneSSLossService } from '../../services/ten-one-ss-loss.service'
import { SubstationLoss } from '../../../vo/SubstationLoss';
import { TenOneSSLossAdd } from '../ten-one-ss-add/ten-one-ss-loss-add.component';
import { TenOneSSLossSub } from '../ten-one-ss-sub/ten-one-ss-loss-sub.component';
@Component({
  selector: 'ten-one-ss-loss-view',
  templateUrl: './ten-one-ss-loss-view.component.html',

  providers: [MatDatepickerModule, MatNativeDateModule, DatePipe]
})
export class TenOneSSLossView implements OnInit {
  rows: Array<SubstationLoss>;
  substationLoss: SubstationLoss;
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
  dialogRef: MatDialogRef<TenOneSSLossAdd>;
  dialogRef2: MatDialogRef<TenOneSSLossSub>;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public dialog: MatDialog,
    private tenOneSSLossService: TenOneSSLossService,
    private commonService: CommonService,
    private datePipe: DatePipe
  ) {

  }
  types = [
		{ "key": "By SubStation" ,"name":"By SubStation"},
		{ "key": "By Edc" ,"name":"By Edc" }
	]
  ngOnInit() {
    this.rows = [];
    this.substationLoss = new SubstationLoss();

  }
  onChange(val) {
    if(val=='By EDC'){
      this.dialogRef = this.dialog.open(TenOneSSLossAdd, this.config);
      this.dialogRef.afterClosed().subscribe(result => {
      if(result!=null && result!=undefined){

      }
        this.dialogRef = null;
      });
     
    }else{
      this.dialogRef2 = this.dialog.open(TenOneSSLossSub, this.config);
      this.dialogRef2.afterClosed().subscribe(result => {
      if(result!=null && result!=undefined){

      }
        this.dialogRef2 = null;
      }); 
    }
    
}
 
  
}



