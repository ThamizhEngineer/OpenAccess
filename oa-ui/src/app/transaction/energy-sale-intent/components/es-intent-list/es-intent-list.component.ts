import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';

import { CommonUtils } from '../../../../shared/common/common-utils';
import { EsIntentService } from './../../services/es-intent.service';
import { EsIntent } from '../../../vo/es-intent';
import { CommonService } from './../../../../shared/common/common.service';

@Component({
  selector: 'es-intent-flow-type',
  template: `
 
  <mat-card> 
  <mat-card-content>
  <div fxLayout="row" fxLayoutAlign="start center">
  <mat-form-field class="ml-xs mr-xs" style="width: 100%;">
  <mat-select class="ml-xs mr-xs" style="width: 100%;" placeholder="Flow Type " [(ngModel)]="esIntent.flowTypeCode">
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
export class EsIntentFlowTypeComponent implements OnInit {
  esIntent: EsIntent;
  flowTypes = [
    { "key": "THIRD-PARTY", "name": "Third Party" },
    { "key": "CAPTIVE", "name": "Fossil Fuel Captive" },
    { "key": "WEG", "name": "NCES Captive" },
    { "key": "IEX-CONSUMER", "name": "IEX - Consumer" },
    { "key": "IEX-GENERATOR", "name": "IEX - Generator" },
    { "key": "STB", "name": "Sale to Board" }
  ];

  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: EsIntentService,
    private commonService: CommonService,
    public dialog: MatDialog
  ) {
    console.log('router path' + this.router.url);
  }

  ngOnInit() {
this.esIntent = new EsIntent();


  }
  selectFlowType(){

    if(this.esIntent.flowTypeCode=='IEX-CONSUMER'){
      this.router.navigate(['/es-intent/es-intent-iex-consumer'] ,{queryParams:{flowType:this.esIntent.flowTypeCode}});
  
    }else if(this.esIntent.flowTypeCode=='IEX-GENERATOR'){
      this.router.navigate(['/es-intent/es-intent-iex-generator'] ,{queryParams:{flowType:this.esIntent.flowTypeCode}});
  
    }else if(this.esIntent.flowTypeCode=='STB'){
      this.router.navigate(['/es-intent/es-intent-stb'] ,{queryParams:{flowType:this.esIntent.flowTypeCode}});
    }else{
      this.router.navigate(['/es-intent/es-intent-new'] ,{queryParams:{flowType:this.esIntent.flowTypeCode}});
    }
    this.dialog.closeAll();
  }
}

@Component({
  selector: 'es-intent-list',
  templateUrl: './es-intent-list.component.html',
  providers: [ CommonUtils]
})
export class EsIntentListComponent implements OnInit {
  esIntent: EsIntent;
  rows: Array<EsIntent>;
  accessAddFlag:boolean = false;
  accessViewFlag:boolean = false;
  searchSellerCompanyServiceId: string = "";
  searchSellerCompanyId: string = "";
  searchSellerEndOrgId: string = "";
  searchStatus: string = "";
  searchEsIntentCode: string = "";
  companyServices = [];

  edcList = [];
  voltages = [];
  statuses = [
    { "key": "CREATED", "name": "CREATED" },
    { "key": "APPROVED", "name": "APPROVED" },
    { "key": "COMPLETED", "name": "COMPLETED" }
  ];

  dialogRef: MatDialogRef<EsIntentFlowTypeComponent> | null;
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
			message: this.esIntent,
		}
  };
  
  disableEdc: boolean = false;

  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: EsIntentService,
    private commonService: CommonService,
    public dialog: MatDialog
  ) {

  }

  ngOnInit() {
    this.esIntent = new EsIntent();
  
  this.accessAddFlag=this.commonUtils.userHasAccess("ENERGY-SALE-INTENT","CREATE");
  this.accessViewFlag=this.commonUtils.userHasAccess("ENERGY-SALE-INTENT","VIEW");
    this.rows = [];
   
    this.esIntent.sellerEndOrgId = CommonUtils.getLoginUserEDC();
    this.esIntent.sellerCompanyId = CommonUtils.getLoginUserCompanyId(); 
    if ((this.esIntent.sellerEndOrgId != "") && isFinite(parseInt(this.esIntent.sellerEndOrgId))&&  (this.esIntent.sellerCompanyId != "")) {
      this.searchSellerEndOrgId = this.esIntent.sellerEndOrgId;
      this.searchSellerCompanyId = this.esIntent.sellerCompanyId;
      
   
      console.log(this.esIntent.sellerEndOrgId);
      console.log(this.esIntent.sellerCompanyId);
      this.disableEdc = true;
      this.onEdcChange();
    }
    this.fetchEdcList();

  }




  searchResults() {
    if ((this.esIntent.sellerEndOrgId != "") && isFinite(parseInt(this.esIntent.sellerEndOrgId))) {
      this.searchSellerEndOrgId = this.esIntent.sellerEndOrgId;
      
    }
    this.service.getEsIntentSearch(this.searchSellerEndOrgId, this.searchSellerCompanyServiceId,this.searchSellerCompanyId, this.searchStatus, this.searchEsIntentCode).subscribe(
      _esIntent => {
        this.rows = _esIntent;
 
      });
  }
  onEdcChange() {
    console.log("In edc change");
    console.log(this.searchSellerEndOrgId)
    console.log(this.searchSellerCompanyId)

    this.service.getEsIntentCompanyByEdc(this.searchSellerEndOrgId,this.searchSellerCompanyId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices)

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




  editEsIntent(_id: string,flowTypeCode:string) {
    try {
   
      if(flowTypeCode=="IEX-CONSUMER"){
        this.router.navigate(['/es-intent/es-intent-iex-consumer'] ,{queryParams:{id:_id}});
      }else if(flowTypeCode=="IEX-GENERATOR"){        
        this.router.navigate(['/es-intent/es-intent-iex-generator'] ,{queryParams:{id:_id}});    
      }else if(flowTypeCode=="STB"){
        this.router.navigate(['/es-intent/es-intent-stb'] ,{queryParams:{id:_id}});
      }else{
        // this.router.navigateByUrl('/es-intent/es-intent-edit/' + _id);
        this.router.navigate(['/es-intent/es-intent-edit'] ,{queryParams:{id:_id}});
      }
      
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }
  newEsIntent() {
    
    try {
     
       
        this.dialogRef = this.dialog.open(EsIntentFlowTypeComponent, this.config);
    
        this.dialogRef.afterClosed().subscribe((result: string) => {
          this.lastCloseResult = result;
          this.dialogRef = null;
        
          // this.router.navigateByUrl('/es/es-list');
        });
      
      
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }
  }



}
