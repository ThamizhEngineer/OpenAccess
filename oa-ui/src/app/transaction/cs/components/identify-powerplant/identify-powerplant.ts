import {Component, Input,OnInit} from '@angular/core'
import { Cs} from './../../../vo/cs'; 
import { SignupService } from './../../../../master/signup/services/signup.service';
import { Service} from './../../../../master/vo/company.v1';
import { CommonService } from './../../../../shared/common/common.service'; 
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CsService } from './../../services/cs.service';
@Component({
  selector: 'identify-powerplant',
  templateUrl: './identify-powerplant.html',
  providers:[SignupService,CommonUtils]
})

export class IdentifyPowerplant implements OnInit {
  @Input() cs: Cs;
  @Input() stepDisable: boolean;
  minDate = new Date();
  companyService:string;
  sellerCompanyServices=[];
  filteredCompanyServiceList=[];
  companyServiceList:Array<Service>;
  constructor(
  private commonService: CommonService,
  private signupService: SignupService,
  private commonUtils: CommonUtils,
  private service:CsService,
  ) { 
	this.fetchOrgList();
  }

  ngOnInit() {
    this.cs = new Cs();
    this.companyServiceList = new Array<Service>();
  }
  
  orgList = [];
  filteredOrgList = [];
  fetchOrgList(){
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

  filterOrgList(val: string) {
    return val ? this.orgList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.orgList;
  }

  filterSellerCompanyServiceList(val: string) {
  
    return val ? this.sellerCompanyServices.filter((s) => s.companyName.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.sellerCompanyServices;
  }
  fetchSellerCompanyServiceList(){
 
    this.cs.sellerOrgId = this.cs.sellerOrgCode;
    this.commonService.getSellerCompanyServicesByOrgId(this.cs.sellerOrgCode).subscribe(
      _sellerCompanyServices => {
        this.sellerCompanyServices = _sellerCompanyServices;
      }
    )
  }
  
  onServiceChange(){
    this.cs.sellerCompanyName = this.filteredCompanyServiceList
    .filter((item) => item.companyName == this.cs.sellerCompanyName)[0].companyName;
    this.cs.sellerCompanyId = this.filteredCompanyServiceList
    .filter((item) => item.companyName == this.cs.sellerCompanyName)[0].companyId;
    this.cs.sellerCompanyServiceId= this.filteredCompanyServiceList
    .filter((item) => item.companyName == this.cs.sellerCompanyName)[0].serviceId;
    this.service.getCompanyService(this.cs.sellerCompanyServiceId).subscribe(result=>{
      this.companyServiceList = result;
      this.cs.approvedCapacity = this.companyServiceList[0].capacity;
      this.cs.voltageName=  this.companyServiceList[0].voltageName;
      this.cs.voltageCode=  this.companyServiceList[0].voltageCode;
      this.cs.fuelTypeName=  this.companyServiceList[0].fuelTypeName;
    });


 
  }
  onChange(val){
   
    this.service.setCs(this.cs);
  }


  acChangeFunc(event, item, modelName:string){
	if (event.source.selected) {
       this.cs.sellerOrgCode = item.code;
    }
  }
}