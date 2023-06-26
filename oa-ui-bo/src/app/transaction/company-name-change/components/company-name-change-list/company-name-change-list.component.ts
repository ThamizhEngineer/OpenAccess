import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CommonUtils } from '../../../../shared/common/common-utils';
import { CompanyNameChangeService } from './../../services/company-name-change.service';
import { CompanyNameChange } from './../../../vo/company-name-change';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { CommonService } from './../../../../shared/common/common.service';
import { SignupService } from './../../../../master/signup/services/signup.service';

@Component({
  selector: 'company-name-change-flow-type',
  template: `
 
  <mat-card> 
  <mat-card-content>
  <div fxLayout="row" fxLayoutAlign="start center">
  <mat-form-field class="ml-xs mr-xs" style="width: 100%;">
  <mat-select class="ml-xs mr-xs" style="width: 100%;" placeholder="Flow Type " [(ngModel)]="companyNameChange.flowTypeCode">
      <mat-option *ngFor="let flowType of flowTypes" [value]="flowType.key">
          {{flowType.name}}
      </mat-option>
  </mat-select>
</mat-form-field>
</div>

<mat-card-actions>
<button mat-raised-button color="primary" (click)="selectFlowType('')" >Select</button>
</mat-card-actions>
</mat-card-content>
</mat-card> 

`,

})
export class CompanyNameChangeFlowTypeComponent implements OnInit {

  flowTypes = [
    { "key": "NAME-CHANGE", "name": "Changes in Company Name" },
    { "key": "CAPTIVE-CHANGE", "name": "Change in Company Captive Members" },

  ];

  companyNameChange :CompanyNameChange;

  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: CompanyNameChangeService,
    private commonService: CommonService,
    public dialog: MatDialog
  ) {
    console.log('router path' + this.router.url);
  }

  ngOnInit() {
    this.companyNameChange =  new CompanyNameChange();


  }
  selectFlowType(){
 

    if(this.companyNameChange.flowTypeCode=='NAME-CHANGE'){
      this.router.navigate(['/company-name-change/company-name-change-new'] ,{queryParams:{flowType:this.companyNameChange.flowTypeCode}});
  
    }else if(this.companyNameChange.flowTypeCode=='CAPTIVE-CHANGE'){
      this.router.navigate(['/company-name-change/captive-change-new'] ,{queryParams:{flowType:this.companyNameChange.flowTypeCode}});
    }
    this.dialog.closeAll();
  }
}

@Component({
  selector: 'company-name-change-list',
  templateUrl: './company-name-change-list.component.html',
  providers: [CompanyNameChangeService, SignupService,CommonUtils]
})
export class CompanyNameChangeListComponent implements OnInit {

  rows: Array<CompanyNameChange>;
  tempResults: Array<CompanyNameChange>;
  companyNameChange:CompanyNameChange;

  searchBuyerEndOrgId: string = "";
  searchBuyerCompanyServiceNumber: string = "";
  searchBuyerCompanyName: string = "";
  searchSellerEndOrgId: string = "";
  searchSellerCompanyServiceNumber: string = "";
  searchSellerCompanyName: string = "";
  searchBuyerCompanyServiceId: string = "";
  searchStatus: string = "";
  companyServices = [];
  searchCompanyServiceId:string="";
  edcList = [];
  //for pagination
  count = 0;
  offset = 0;
  limit = 8;

  statuses = [
    { "key": "CREATED", "name": "CREATED" },
    { "key": "APPROVED", "name": "APPROVED" },
    { "key": "COMPLETED", "name": "COMPLETED" }
  ];
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  disableEdc: boolean = false;
  dialogRef: MatDialogRef<CompanyNameChangeFlowTypeComponent> | null;
  lastCloseResult: string;
  config = {
		disableClose: false,
		panelClass: 'custom-overlay-pane-class',
		hasBackdrop: true,
		backdropClass: '',
		width: '700px',
		height: '200px',
		position: {
			top: '',
			bottom: '',
			left: '',
			right: ''
		},
		data: {
			message: this.companyNameChange,
		}
  };
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: CompanyNameChangeService,
    private commonService: CommonService,
    private signupService: SignupService,
    public dialog: MatDialog
  ) {

  }

  ngOnInit() {
    this.companyNameChange =  new CompanyNameChange();
    this.accessAddFlag = this.commonUtils.userHasAccess("AMENDMENT", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("AMENDMENT", "VIEW");
    this.rows = [];
    this.companyNameChange.orgId= CommonUtils.getLoginUserDetails().substr(3,3); 

    if (( this.companyNameChange.orgId  != "") && isFinite(parseInt( this.companyNameChange.orgId)) ) {
      this.searchSellerEndOrgId =  this.companyNameChange.orgId;
     
      this.disableEdc = true;
      this.onEdcChange();
    }
      
    this.fetchOrgList();
  
  }



  newCompanyNameChange() {
    // try {
    //   this.router.navigateByUrl('/company-name-change/company-name-change-new');
    // }
    // catch (e) {
    //   console.log(e);
    // }
 
      try {       
          this.dialogRef = this.dialog.open(CompanyNameChangeFlowTypeComponent, this.config);    
          this.dialogRef.afterClosed().subscribe((result: string) => {
            this.lastCloseResult = result;
            this.dialogRef = null;
          });
       }
      catch (e) {
        console.log(e);
      }
    
  }

  editCompanyNameChange(_id: string,flowTypeCode:string) {
    // try {
    //   this.router.navigateByUrl('/company-name-change/company-name-change-edit/' + _id);
    try {
      console.log(_id,flowTypeCode);
         if(flowTypeCode=="NAME-CHANGE"){

           this.router.navigate(['/company-name-change/company-name-change-edit'] ,{queryParams:{id:_id}});
         }else if(flowTypeCode=='CAPTIVE-CHANGE'){
          console.log("in captivechange edit")
          this.router.navigate(['/company-name-change/captive-change-edit'] ,{queryParams:{id:_id}});
        }
    }
    catch (e) {
      console.log(e);
    }
  }


  searchResults() {

    
    this.service.getAllCompanyNameChanges( this.searchSellerEndOrgId,this.searchCompanyServiceId).subscribe(
      _cnc => {
        this.rows = _cnc;
      });
  }




  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.edcList = result;
      },
      error => {
        console.error('Error loading Edcs!');
        console.error(error);
      }
    );
  }

  onEdcChange() {
    console.log("In edc change");
    this.service.getCompanyNameChangeByEdc(this.searchSellerEndOrgId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }
}
