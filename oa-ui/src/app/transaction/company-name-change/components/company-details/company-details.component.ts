import {Component, Input,OnInit} from '@angular/core'
import { CompanyNameChange} from './../../../vo/company-name-change'; 
import { SignupService } from './../../../../master/signup/services/signup.service';
import { Service} from './../../../../master/vo/company.v1';
import { CommonService } from './../../../../shared/common/common.service'; 
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CompanyNameChangeService } from './../../services/company-name-change.service';
@Component({
  selector: 'company-details',
  templateUrl: './company-details.component.html',
  providers:[SignupService,CommonUtils]
})

export class CompanyDetails implements OnInit {
  @Input() companyNameChange: CompanyNameChange;
  @Input() stepDisable: boolean;
  minDate = new Date();

  companyServices = [];

  constructor(
  private commonService: CommonService,
  private signupService: SignupService,
  private commonUtils: CommonUtils,
  private service:CompanyNameChangeService,
  ) { 
	this.fetchOrgList();
  }

  ngOnInit() {
    this.companyNameChange = new CompanyNameChange();

  }
  
  orgList = [];
  filteredOrgList = [];
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
 
  onChange(){
   
    this.service.setCompanyNameChange(this.companyNameChange);
  }

  addCompanyNameChange() {
    // this.formatChangesforDB();
    console.log("In add company name in identify powerplant");
    // this.service.addCompanyNameChange(this.companyNameChange).subscribe(
    //   result => {
     
   

    //   },
    //   error => {
    //     console.error('Error adding Cs!');
    //     console.error(error);
    //   }
    // );
  }

  // acChangeFunc(event, item, modelName:string){
	// if (event.source.selected) {
  //      this.cs.sellerOrgCode = item.code;
  //   }
  // }
}