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
@Component({
  selector: 'ten-one-ss-loss-detail',
  templateUrl: './ten-one-ss-loss-detail.component.html',

  providers: [MatDatepickerModule, MatNativeDateModule, DatePipe]
})
export class TenOneSSLossDetail implements OnInit {
  rows: Array<SubstationLoss>;
  substationLoss: SubstationLoss;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tenOneSSLossService: TenOneSSLossService,
    private commonService: CommonService,
    private datePipe: DatePipe
  ) {

  }

  ngOnInit() {
    this.rows = [];
    this.substationLoss = new SubstationLoss();
    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.route.params
        .subscribe((_params: Params) => {
          this.tenOneSSLossService.getAllSubstationLossById(_params['_id']).subscribe(x => {
            this.substationLoss = x;
          });

        });
    }
  }
  navigateToListPage() {
    this.router.navigateByUrl('/ten-one-ss-loss/ten-one-ss-loss-list/');
  }

}



