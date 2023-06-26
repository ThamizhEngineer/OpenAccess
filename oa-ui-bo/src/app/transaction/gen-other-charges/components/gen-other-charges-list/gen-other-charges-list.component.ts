import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

// import { MeterReadingDetailComponent } from './../../components/gen-other-charges-detail/gen-other-charges-detail.component';

import { GenOtherChargesService } from '../../services/gen-other-charges.service';
import { GenOtherCharges} from '../../../vo/geoothercharges'; 
import { CommonService } from '../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';

@Component({
  selector: 'gen-other-charges-list',
  templateUrl: './gen-other-charges-list.component.html',
  //styleUrls: ['./-list.component.scss']
  providers: [GenOtherChargesService,CommonUtils]
})
export class GenOtherChargesListComponent implements OnInit {

  orgList = [];
  genOtherCharges: GenOtherCharges;



  filteredEDCs = [];
  months = [];
  searchcompanyServiceNumber: string = "";
  searchcompanyServiceId: string = "";
  searchmodemNumber: string = "";
  searchsellermeterNumber: string = "";
  searchcompanyName: string = "";
  searchorgId: string = "";
  searchMonth: string = "";
  searchYear: string = "";
  disableEdc: boolean = false;
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  companyServices=[];
  edcList: any;
  years:any[];
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,

    private service: GenOtherChargesService
  ) {

    // this.fetchEDCs();

  }
  rows: Array<GenOtherCharges>;

  ngOnInit() {
    this.accessAddFlag = this.commonUtils.userHasAccess("METER-READING", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("METER-READING", "VIEW");
    this.rows = [];
    this.genOtherCharges = new GenOtherCharges();
    this.fetchEDCs();
    this.months = this.commonService.fetchMonths();
    this.years = this.commonService.fetchYearList();
    this.genOtherCharges.sellerOrgId = CommonUtils.getLoginUserEDC();
    console.log(CommonUtils.getLoginUserEDC())
    if (this.genOtherCharges.sellerOrgId != "") {
      console.log("in edc select")
      this.searchorgId = this.genOtherCharges.sellerOrgId;
      this.disableEdc = true;
    }


  }


  //     filterEDCs(val: string) {

  //   return val ? this.orgId.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.orgId;
  // }

  //     fetchEDCs(){
  //     this.commonService.fetchEDCs().subscribe(
  //     result => { this.orgId = result;
  //      },    
  //     error => {
  //     console.error('Error loading orgs!');
  //      console.error(error);
  //      }  );
  //     }

  searchResults() {
    if (this.genOtherCharges.sellerOrgId != "") {
      this.searchorgId = this.genOtherCharges.sellerOrgId;
    }


    this.service.getGenOtherCharges(this.searchcompanyServiceId, this.searchMonth, this.searchYear, this.searchorgId).subscribe(
      _es => {
        this.rows = _es;
        console.log("In search result")
        console.log(this.rows);
      });
  }
  editGenOtherCharges(_id: string) {
    try {
      console.log(_id);
      this.router.navigateByUrl('/gen-other-charges/gen-other-charges-detail/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
    }
    catch (e) {
      console.log(e);
    }

  }

  onEdcChange() {
    console.log("In edc change");
    this.service.getGenOtherChargesCompanyByEdc(this.searchorgId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }
  newGenOtherCharges() {
    try {

      this.router.navigateByUrl('/gen-other-charges/gen-other-charges-new');
     
    }
    catch (e) {
      console.log(e);
    }

  }
  // fetchEDCs() {

  //   this.commonService.fetchWindEDCs().subscribe(
  //     result => {

  //       this.orgList = result;

  //     });
  // }

  fetchEDCs() {
    this.commonService.fetchEDCs().subscribe(
        edc=>{
          // console.log("In page load edc")
          this.edcList = edc;
          // console.log(this.edcList)
        });
}


}