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

@Component({
  selector: 'ten-one-ss-loss',
  templateUrl: './ten-one-ss-loss-add.component.html',

  providers: [MatDatepickerModule, MatNativeDateModule, DatePipe]
})
export class TenOneSSLossAdd implements OnInit {
  orgList = [];
  rows = [];
  subStationList = [];
  substationLoss: SubstationLoss;
  importSubstationLoss: ImportSubstationLoss;
  importSubstationLossList: Array<ImportSubstationLoss>;
  month:string;
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
    this.subStationList = [];
    this.substationLoss = new SubstationLoss();
    this.substationLoss.orgId = CommonUtils.getLoginUserEDC();
    if (this.substationLoss.orgId  != "") {
   
      this.disableEdc = true;
    }
    this.importSubstationLossList = [];
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
  start() {
  
    this.commonService.fetchSubstationsByOrgIdAndTypeOfSs(this.substationLoss.orgId, 'SECTION 10(1)SS').subscribe(
      x => {
        this.subStationList = x;

        this.subStationList.forEach(ss => {
          let imss = new ImportSubstationLoss();
          imss.substationId = ss.id;
          imss.substationName = ss.name;
          imss.orgId = this.substationLoss.orgId;
          imss.month=this.month;
          imss.year=this.year;
          imss.lossPercent="0";
          imss.totalAllWegs="0";
          imss.bulkMeterReading="0";
          this.importSubstationLossList.push(imss);
          
        })
    
      },
      error => {
        console.error('Error loading substaion!');
        console.error(error);
      });
  }

  import() {
    this.disableSave=true;
    this.tenOneSSLossService.addImportSubstationLoss(this.importSubstationLossList).subscribe(x=>{
      this.navigateToListPage();
    },
    error => {
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




