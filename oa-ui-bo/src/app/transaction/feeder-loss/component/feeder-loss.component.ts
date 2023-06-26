import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import{CommonService} from '../../../../app/shared/common/common.service';
import { CommonUtils } from '../../../shared/common/common-utils';
import { FeederLoss } from '../../vo/feeder-loss';
import { FeederLossService } from '../service/feeder-loss.service';
import { FeederLossDialogComponent } from './feeder-loss-dialog.component';
import { FeederLossOptionsComponent } from './feeder-loss-options.component';
@Component({
  selector: 'feeder-loss',
  templateUrl: './feeder-loss.component.html',

})
export class FeederLossComponent implements OnInit {

rows: Array<FeederLoss>;
    feederLoss: FeederLoss;
    months: { value: string; name: string; }[];
    orgList: any;
    disableEdc: boolean =false;
    config: MatDialogConfig = {
        disableClose: false,
        width: '120%',
        height: '70%',
        position: {
          top: '',
          bottom: '',
          left: '10%',
          right: ''
        }
      };
    dialogRef: MatDialogRef<FeederLossOptionsComponent>;
constructor(
    private commonService: CommonService,
    private commonUtils: CommonUtils,
    public dialog: MatDialog,
    private router: Router,
    private feederLossService:FeederLossService
  ) {

  }
    ngOnInit() {
        this.feederLoss = new FeederLoss();
        this.months = this.commonService.fetchMonths();
    this.feederLoss.month = this.commonUtils.getPerviousMonth();
    this.feederLoss.year = this.commonUtils.getCurrentYear();
    this.feederLoss.orgId = CommonUtils.getLoginUserEDC();
    this.rows = [];

    if (this.feederLoss.orgId  != "") {
   
        this.disableEdc = true;
      }
      this.commonService.fetchEDCs().subscribe(
        result => {
          this.orgList = result;
        });
    }

    searchResults(){
        this.feederLossService.getAllFunctionLoss(this.feederLoss.orgId,this.feederLoss.month,this.feederLoss.year).subscribe(x=>{
          this.rows = x;
          
          this.feederLoss.orgName = CommonUtils.getLoginUserEDC();
         
        })
      }
      editSubstation(_id: string) {
        try {
          this.router.navigateByUrl('/feeder-loss/feeder-loss-view/' + _id);
        }
        catch (e) {
          console.log(e);
        }
      }

      newEs() {
        try {
    
          this.dialogRef = this.dialog.open(FeederLossOptionsComponent, this.config);
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
}
