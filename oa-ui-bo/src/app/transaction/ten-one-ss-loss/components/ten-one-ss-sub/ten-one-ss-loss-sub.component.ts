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
import { TenOneSSLossService } from '../../services/ten-one-ss-loss.service';
import { SubstationLoss } from '../../../vo/SubstationLoss';
import { ImportSubstationLoss } from '../../../vo/ImportSubstationLoss';
import { TenOneSSLossAdd } from '../ten-one-ss-add/ten-one-ss-loss-add.component';

@Component({
  selector: 'ten-one-ss-loss-sub',
  templateUrl: './ten-one-ss-loss-sub.component.html',

  providers: [MatDatepickerModule, MatNativeDateModule, DatePipe]
})
export class TenOneSSLossSub implements OnInit {
  orgList = [];
  rows = [];
  subStationList = [];
  substationName=[];
  substationLoss: SubstationLoss;
  importSubstationLoss: ImportSubstationLoss;
  importSubstationLossList: Array<ImportSubstationLoss>;
  substationLossList: Array<ImportSubstationLoss>;
  month:string;
  subStationname:string;
  year:string;
  months=[];
  disableEdc: boolean = false;
  disableSave: boolean = false;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public dialogRef: MatDialogRef<TenOneSSLossAdd>,
    private commonUtils:CommonUtils,
    private commonService: CommonService,
    private tenOneSSLossService: TenOneSSLossService,
    private datePipe: DatePipe
  ) {

  }

  ngOnInit() {
    this.rows = [];
    this.substationName=[];
    this.subStationList = [];
    this.substationLoss = new SubstationLoss();
    this.substationLoss.orgId = CommonUtils.getLoginUserEDC();
    if (this.substationLoss.orgId  != "") {
   this.subStationNames(this.substationLoss.orgId);
      this.disableEdc = true;
    }
    this.importSubstationLossList = [];
    this.substationLossList = [];
    this.months = this.commonService.fetchMonths();
    this.month = this.commonUtils.getPerviousMonth();
    this.year = this.commonUtils.getCurrentYear();
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
        // console.log(this.orgList);
        this.substationLoss.orgName = CommonUtils.getLoginUserEDC();
      });
      
  }
  subStationNames(val){
    this.commonService.fetchSubstationsByOrgIdAndTypeOfSs(val, 'SECTION 10(1)SS').subscribe(
      x => {
        this.subStationList = x;
      
      });
    
  }
  
  start() {
    
    
      let imss = new ImportSubstationLoss();
      imss.substationId = this.substationLoss.substationId;
      //  imss.substationName = this.substationLoss.substationName;
      imss.orgId = this.substationLoss.orgId;
      imss.month=this.month;
      imss.year=this.year;
      imss.lossPercent="0";
      imss.totalAllWegs="0";
      imss.bulkMeterReading="0";
      this.substationLossList.push(imss);
      
    // console.log(this.substationLoss);
    
  }

  import() {
    this.disableSave=true;
    this.tenOneSSLossService.addImportSubstationLoss(this.substationLossList).subscribe(x=>{
     console.log(this.substationLossList);
      this.navigateToListPage();
    },
    error => {
       alert("10(1) Loss Percent for this substation has been entered for this month.For further clarification please contact TANGEDCO IT Team");
      console.error('Error loading substaion!');
      console.error(error);
      this.navigateToListPage();
    });
    this.navigateToListPage();
  }

  navigateToListPage(){
    this.dialogRef.close();
  }
}




