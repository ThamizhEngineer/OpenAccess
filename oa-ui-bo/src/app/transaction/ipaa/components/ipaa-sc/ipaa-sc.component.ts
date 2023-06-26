import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { DatePipe } from '@angular/common';
import {MAT_DIALOG_DATA} from '@angular/material'


import { CommonService } from './../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { IpaaService } from '../../services/ipaa.service';
import { IpaStandingClearance } from '../../../vo/ipa-sc';
import { Inject } from '@angular/core';

@Component({
  selector: 'ipaa-list',
  templateUrl: './ipaa-sc.component.html',
  providers: [MatDatepickerModule,  MatNativeDateModule, DatePipe]
})
export class IpaScComponent implements OnInit {
    ipaScId:string="";
    rows: Array<IpaStandingClearance>;
    ipaStandingClearance:IpaStandingClearance;
    maxDate: Date = new Date();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public dialogRef: MatDialogRef<IpaScComponent>,
    private commonService: CommonService,
    private datePipe: DatePipe,
    private service:IpaaService,
    @Inject(MAT_DIALOG_DATA) public data: any

  ) {
    
  }

  ngOnInit() {
    console.log(this.data)
    console.log(this.data._id)
    this.ipaScId=this.data._id;
    this.ipaStandingClearance=new IpaStandingClearance();
    this.service.findExistigStandingClearance(this.ipaScId).subscribe(
        _ipaSc => {
          this.rows = _ipaSc;
          
        });
  }

  addIpaSc() {
    console.log(this.ipaScId)
    this.ipaStandingClearance.ipaId=this.ipaScId;
    console.log(this.ipaStandingClearance)
    this.service.addIpaSc(this.ipaStandingClearance).subscribe(
      result => {
        console.log(result)
        // this.listNoc();
      },
      error => {
        console.error('Error adding Noc!');
        console.error(error);
      }
    );
  }






}
