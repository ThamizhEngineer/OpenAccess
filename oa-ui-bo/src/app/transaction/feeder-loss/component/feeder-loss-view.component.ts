import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import{CommonService} from '../../../../app/shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { FeederLoss } from '../../vo/feeder-loss';
import { FeederLossService } from '../service/feeder-loss.service';
@Component({
  selector: 'feeder-loss-view',
  templateUrl: './feeder-loss-view.component.html',

})
export class FeederLossViewComponent implements OnInit {

    feederLoss: FeederLoss;

constructor(
    private route: ActivatedRoute,
    private commonService: CommonService,
    private commonUtils: CommonUtils,
    public dialog: MatDialog,
    private router: Router,
    private feederLossService:FeederLossService
  ) {

  }
  ngOnInit() {
    this.feederLoss = new FeederLoss();
    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.route.params
        .subscribe((_params: Params) => {
          this.feederLossService.getAllFeederLossById(_params['_id']).subscribe(x => {
            this.feederLoss = x;
          });

        });
    }
  }
  navigateToListPage() {
    this.router.navigateByUrl('/feeder-loss/feeder-loss/');
  }

}