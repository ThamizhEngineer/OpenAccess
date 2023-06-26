import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
//import { SessionStorage} from "angular2-localstorage/WebStorage";
import { CommonUtils } from '../../../../shared/common/common-utils';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { CompanyNameChangeService } from './../../services/company-name-change.service';
import { CommonService } from './../../../../shared/common/common.service';
import { OaaService } from '../../../oaa/services/oaa.service';
import { CompanyNameChange } from './../../../vo/company-name-change';




@Component({
  selector: 'company-name-change-detail',
  templateUrl: './company-name-change-detail.component.html',
  //styleUrls: [],
  providers: [CompanyNameChangeService, CommonUtils, OaaService]
})
export class CompanyNameChangeDetailComponent implements OnInit {

  companyNameChange: CompanyNameChange;

  disableControls: boolean = true;
  orgList = [];
  companyServices = [];
  addScreen: boolean = true;
  rows = [];
  

  accessUpdateFlag: boolean = false;
  accessDeleteFlag: boolean = false;
  accessApproveFlag: boolean = false;
  accessCompleteFlag: boolean = false;
  accessProcessFlag: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: CompanyNameChangeService,
    private commonService: CommonService,

    private oaaService: OaaService
  ) { }

  ngOnInit() {
    this.companyNameChange = new CompanyNameChange();
    this.accessUpdateFlag = this.commonUtils.userHasAccess("AMENDMENT", "UPDATE");
    this.accessDeleteFlag = this.commonUtils.userHasAccess("AMENDMENT", "DELETE");
    this.accessApproveFlag = this.commonUtils.userHasAccess("AMENDMENT", "APPROVE");
    this.accessCompleteFlag = this.commonUtils.userHasAccess("AMENDMENT", "COMPLETE");
    this.accessProcessFlag = this.commonUtils.userHasAccess("AMENDMENT", "PROCESS");

    this.fetchOrgList();

    this.route.queryParams.filter(params=>params.flowType)
    .subscribe(params=>{
      console.log(params.flowType);
      this.companyNameChange.flowTypeCode=params.flowType;
    })
    this.route.queryParams.filter(params=>params.id)
    .switchMap((params: Params) => this.service.getCompanyNameChangeById(params.id))
   .subscribe(_companyNameChange=>{
            this.companyNameChange = _companyNameChange;
            this.addScreen = false;
            this.onEdcChange();
         
        });
    
console.log(this.companyNameChange)
  }



  saveCompanyNameChange(status: string) {
    //save can be add or update

    if (this.addScreen) {
      this.addCompanyNameChange();
    }
    else {
      this.updateCompanyNameChange();
    }
  }


  addCompanyNameChange() {
    this.companyNameChange.statusCode="CREATED";
    this.companyNameChange.oldCompanyName = this.companyNameChange.companyName;
    this.service.addCompanyNameChange(this.companyNameChange).subscribe(
      result => {

        this.listCompanyNameChange();
      },
      error => {
        console.error('Error adding CompanyNameChange Application!');
        console.error(error);
      }
    );
  }


  updateCompanyNameChange() {
    this.service.updateCompanyNameChange(this.companyNameChange).subscribe(
      result => {
        this.listCompanyNameChange();
      },
      error => {
        console.error('Error updating CompanyNameChange!');
        console.error(error);
      }
    );
  }

  listCompanyNameChange() {
    this.router.navigateByUrl('/company-name-change/company-name-change-list');
  }



  filterOrgList(val: string) {
    return val ? this.orgList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.orgList;
  }

  onEdcChange() {
    console.log("In edc change");
    console.log(this.companyNameChange.orgId)
    this.service.getSellers(this.companyNameChange.orgId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }

  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }

  approveCompanyNameChange(){
    this.service.approveCompanyNameChange(this.companyNameChange).subscribe(
      result => {
        this.listCompanyNameChange();
      },
      error => {
        console.error('Error approving CompanyMeterChange!');
        console.error(error);
      }
    );
  }


  onServiceChange() {
    console.log(this.companyServices);
    console.log("In service change");
    var companyService = this.companyServices.filter((item) => item.id == this.companyNameChange.serviceId)[0];
    this.companyNameChange.companyId = companyService.companyId;
    this.companyNameChange.companyName = companyService.companyName;
    this.companyNameChange.serviceNumber = companyService.number;
    // this.esIntent.sellerCompanyName= this.edcs.filter((item)=> item.id == this.esIntent.sellerCompanyServiceId)[0].companyName;
    console.log(this.companyNameChange);


  }

}
