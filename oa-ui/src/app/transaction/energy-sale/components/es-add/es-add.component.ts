import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { DatePipe } from '@angular/common';

import { EsService } from './../../services/es.service';
import { Es } from './../../../vo/es';
import { Gs } from './../../../vo/gs';
import { GsService } from './../../../generation-statement/services/gs.service';
import { CommonService } from './../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';

@Component({
  selector: 'es-list',
  templateUrl: './es-add.component.html',
  styleUrls: ['./es-add.component.scss'],
  providers: [MatDatepickerModule,  MatNativeDateModule, DatePipe]
})
export class EsAddComponent implements OnInit {

  es: Es;
  rows: Array<Gs>;
  tempResults: Array<Gs>;
  months = [];
  //for pagination
  count = 0;
  offset = 0;
  limit = 8;
  searchSellerCompanyName: string = "";
  searchCompanyServiceNumber: string = "";
  searchCompanyServiceId: string = "";
  searchMonth: string = "";
  searchYear: string = "";
  searchEdcId: string = "";
  orgList = [];
  filteredCompanyList = [];
  companyServices = [];
  disableCompanyName: boolean = false;
  sellerCompanyServiceNumber:string="";
  searchsellerCompanyServiceNumber: string = "";
  disableCreate: boolean = false;
  disableEdc: boolean = false;
  isFinalDisabled: boolean = false;


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public dialogRef: MatDialogRef<EsAddComponent>,
    private service: EsService,
    private gsService: GsService,
    private commonService: CommonService,
    private commonUtils: CommonUtils,
    private datePipe: DatePipe
  ) {
    
  }

  ngOnInit() {
    this.es = new Es();
    this.rows = [];
    this.fetchEDCs();
    this.fetchCompanyList();
    this.months = this.commonService.fetchMonths();
    this.searchMonth=this.commonUtils.getPerviousMonth();
    this.searchYear=this.commonUtils.getpreviousYear();
    if(CommonUtils.getLoginUserTypeCode()=='GEN')
    {
     this.es.sellerCompanyServiceNumber = CommonUtils.getLoginUserCompany();
     if (this.es.sellerCompanyServiceNumber != "") {
       this.sellerCompanyServiceNumber = this.es.sellerCompanyServiceNumber;
      // this.disableEdc = true;
       this.disableCompanyName = true;
 
     }
     this.checkToCreateEs();
     this.searchResults();

    }
  }

  checkToCreateEs(){
    // console.log(this.searchMonth +"------"+this.searchYear)
    if((this.searchMonth!=null && this.searchMonth!=undefined &&this.searchMonth!="")&&(this.searchYear!=null && this.searchYear!=undefined&&this.searchYear!="")){
      this.service.getAllEss('',this.sellerCompanyServiceNumber,'','','',this.searchMonth, this.searchYear,'','').subscribe(
        _es => {
         _es.forEach(element => {
          this.es.generationStatementId=element.generationStatementId;
          console.log("In search Loop result")
          console.log(this.es.generationStatementId);
          if(this.es.generationStatementId!="" || this.es.generationStatementId!=null){
            this.isFinalDisabled=true;
          }
          });;
        });
    }
   
  }

  fetchEDCs() { 
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }
  filterCompanyList(val: string) {

    return val ? this.companyServices.filter((s) => s.dispCompanyServiceNumber.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.companyServices;
  }
  fetchCompanyList() {
    this.service.getGsByAllocation("", "", "", "", "","").subscribe(
      _gs => {
        this.companyServices = _gs;
      }
    )

  }
  searchResults() {
    if(CommonUtils.getLoginUserTypeCode()=='GEN'){
      if (this.es.sellerCompanyServiceNumber != "") {
        this.searchsellerCompanyServiceNumber = this.es.sellerCompanyServiceNumber;
      }
      // console.log(this.searchsellerCompanyServiceNumber)
    }
    // console.log(this.searchMonth +"------"+this.searchYear)
    if((this.searchMonth!=null && this.searchMonth!=undefined&&this.searchMonth!="")&&(this.searchYear!=null && this.searchYear!=undefined&&this.searchYear!="")){
    this.service.getGsByAllocation(this.searchCompanyServiceId, this.searchsellerCompanyServiceNumber,this.searchSellerCompanyName, this.searchMonth, this.searchYear, this.searchEdcId).subscribe(
      _gs => {
        this.rows = _gs;
      }
    )
    }
  }
  formatChangesforDB() {

    this.es.fromDate = this.datePipe.transform(this.es.fromDate, 'yyyy-MM-dd');
    this.es.toDate = this.datePipe.transform(this.es.toDate, 'yyyy-MM-dd');

  }


  addEs(gs: Gs) {
    this.isFinalDisabled = true;

    this.es.generationStatementId = gs.id;
    // this.es.sellerCompanyServiceId = gs.companyServiceId;
    // this.es.sellerEndOrgId = gs.orgId;
    // this.es.month = gs.statementMonth;
    // this.es.year = gs.statementYear;
    // this.es.fromDate = gs.initStatementDate;
    // this.es.toDate = gs.finalStatementDate;
    // this.es.injectingVoltageCode = gs.injectingVoltageCode;
    // this.es.c1 = "0";
    // this.es.c2 = "0";
    // this.es.c3 = "0";
    // this.es.c4 = "0";
    // this.es.c5 = "0";
    // this.es.simpleEnergySale = "N";
    // this.es.isStb = "N";
    // this.es.netGeneration = gs.netGeneration;
    // this.formatChangesforDB();
 

    this.service.addEs(this.es).subscribe(
      result => {

        this.dialogRef.close(result);

      },
      error => {

        console.error('Error adding Energy allocation!');
        console.error(error);
        this.dialogRef.close();
      }
    );
  }
}
