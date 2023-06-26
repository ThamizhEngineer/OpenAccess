import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { CommonService } from './../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';


import { SignupService } from './../../services/signup.service';
import { Signup } from './../../../vo/signup';

@Component({
  selector: 'app-signup-list',
  templateUrl: './signup-list.component.html',
  //styleUrls: ['./signup-list.component.scss'],
  providers: [SignupService]
})
export class SignupListComponent implements OnInit {
  signup: Signup;

  rows: Array<Signup>;
  tempResults: Array<Signup>;
  edcList = [];
  filteredEDCs = [];

  /*
  columns = [
              { prop: 'ssId', name: 'Ss Id'  },
              { prop: 'ssName', name: 'Ss Name' }
            ];
*/
  count = 0;
  offset = 0;
  limit = 55;
  filterNCES = [];
  searchCompanyName: string = "";
  searchOrgid: string = "";
  searchHtscNumber: string;
  searchHtscNumberOld: string;
  disableEdc: boolean = false;

  searchIsCaptive: string;
  searchIsComplete: string;
  searchMeterNumber: string;
  completeList = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]
  isCaptives = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,

    private service: SignupService
  ) {

    this.fetchEDCs();

  }

  ngOnInit() {
    this.rows = [];
    this.signup = new Signup();

    this.signup.orgId = CommonUtils.getLoginUserEDC();
    if (this.signup.orgId != "") {
      console.log("in edc select")
      this.searchOrgid = this.signup.orgId;
      this.disableEdc = true;
    }


    // this.filterNCES = this.fetchEDCs()
    // this.signup.orgId = this.rows.filter((item) => item.orgId
    // =CommonUtils.getLoginUserTypeCode

 
  }

  

  fetchResults() {
    this.service.searchSignups('02', this.searchCompanyName, this.searchOrgid, this.searchHtscNumber,this.searchHtscNumberOld, this.searchIsCaptive, this.searchIsComplete, this.searchMeterNumber).subscribe(
      _snups => {
        this.rows = _snups;
      
        
        this.signup.orgId = CommonUtils.getLoginUserEDC();

      });
  }
  

   


  fetchEDCs() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.edcList = result;
        
        // this.searchOrgid = CommonUtils.getLoginUserEDC();
        // console.log(this.searchOrgid)
       
        if(CommonUtils.getLoginUserTypeCode()=='MRT'){
          console.log(CommonUtils.getLoginUserCompany());
          console.log(this.edcList.filter((item) => item.ncesDivisionCode))
          this.filterNCES = this.edcList.filter((item) => item.ncesDivisionCode == CommonUtils.getLoginUserCompany());
          console.log(this.filterNCES)

          this.edcList =  this.filterNCES;
          console.log(this.edcList)
      

        }
        else if(CommonUtils.getLoginUserTypeCode()=='NCES'){
          console.log(CommonUtils.getLoginUserCompany());
          console.log(this.edcList.filter((item) => item.ncesDivisionCode))
          this.filterNCES = this.edcList.filter((item) => item.ncesDivisionCode);
          console.log(this.filterNCES)

          this.edcList =  this.filterNCES;
          console.log(this.edcList)
      

        }
       
 
      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }

  newSignup() {
    try {
      //this.router.navigate(['/ss-new','_id', _id]);
 
      this.router.navigateByUrl('/signup/signup-new');
    }
    catch (e) {
      console.log(e);
    }
  }


  editSignup(id: string) {
    try {
   
      this.router.navigateByUrl('/signup/signup-edit/' + id);
      // to-do
      
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
    }

    catch (e) {
      console.log(e);
    }

  }

}
