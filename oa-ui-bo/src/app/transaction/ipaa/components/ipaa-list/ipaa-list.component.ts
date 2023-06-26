import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import {  IpaaService } from './../../services/ipaa.service';
import { Ipaa} from './../../../vo/ipaa';
import { CommonService } from './../../../../shared/common/common.service';

import { CommonUtils } from '../../../../shared/common/common-utils';

@Component({
  selector: 'ipaa-list',
  templateUrl: './ipaa-list.component.html',

})
export class IpaaListComponent implements OnInit {
  ipaa: Ipaa;
  rows: Array<Ipaa>;
 
  searchSellerCompanyServiceId: string = "";
  searchSellerEndOrgId: string = "";
  searchStatus: string = "";
  searchEsIntentCode: string = "";
  companyServices = [];
  searchIpaaCode:string="";

  edcList = [];
  voltages = [];
  statuses = [
    { "key": "CREATED", "name": "CREATED" },
    { "key": "APPROVED", "name": "APPROVED" },
    { "key": "COMPLETED", "name": "COMPLETED" }
  ];
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  disableEdc: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service:IpaaService,
    private commonService: CommonService,
    public dialog: MatDialog
  ) {
    console.log('router path' + this.router.url);
  }

  ngOnInit() {
    this.ipaa = new Ipaa();
    this.accessAddFlag = this.commonUtils.userHasAccess("CONSENT", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("CONSENT", "VIEW");
    this.rows = [];
    this.ipaa.sellerOrgId = CommonUtils.getLoginUserDetails().substr(3,3); 
   
    if ((this.ipaa.sellerOrgId  != "") && isFinite(parseInt(this.ipaa.sellerOrgId ))) {
      this.searchSellerEndOrgId = this.ipaa.sellerOrgId;
     
      this.disableEdc = true;
      this.onEdcChange();
    }
    this.fetchEdcList();

  }




  searchResults() {
    this.service.getAllIpaas(this.searchSellerEndOrgId, this.searchSellerCompanyServiceId, this.searchStatus, this.searchEsIntentCode,this.searchIpaaCode).subscribe(
      _esIntent => {
        this.rows = _esIntent;
        console.log("In search result")
        console.log(this.rows);
      });
  }
  onEdcChange() {
    console.log("In edc change");
    this.service.getIpaaCompanyByEdc(this.searchSellerEndOrgId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }

  fetchEdcList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.edcList = result;
      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }




  editIpaa(_id: string) {
    try {
      console.log(_id);
      this.router.navigateByUrl('/ipaa/ipaa-edit/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }
  newEsIntent() {
    try {

      this.router.navigateByUrl('/es-intent/es-intent-new');
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }
  }



}
