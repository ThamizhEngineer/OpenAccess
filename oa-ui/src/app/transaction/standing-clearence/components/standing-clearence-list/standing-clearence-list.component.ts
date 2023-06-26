import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

// import { MeterReadingDetailComponent } from './../../components/meter-reading-detail/meter-reading-detail.component';

import { StandingClearenceService } from './../../services/standing-clearence.service';
import { CommonService } from './../../../../shared/common/common.service';
import { StandingClearence } from './../../../vo/standing-clearence';
import { CommonUtils } from '../../../../shared/common/common-utils';
@Component({
  selector: 'standing-clearence-list',
  templateUrl: './standing-clearence-list.component.html',
  providers: [StandingClearenceService, CommonUtils]
})
export class StandingClearenceListComponent implements OnInit {
  standingClearence: StandingClearence;
  voltages = [];
  edcList = [];

  searchOrgId: string = "";

  searchEsIntentCode: string = "";
  searchStandingClearenceCode: string = "";
  searchBuyerCompanyServiceId:string="";
  companyServices = [];

  statuses = [
    { "key": "CREATED", "name": "CREATED" },
    { "key": "APPROVED", "name": "APPROVED" },
    { "key": "COMPLETED", "name": "COMPLETED" }
  ];
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private service: StandingClearenceService
  ) {

    // this.fetchEDCs();
    // this.fetchVoltages();

  }

  rows: Array<StandingClearence>;


  ngOnInit() {
    this.accessAddFlag = this.commonUtils.userHasAccess("STANDING-CLEARENCE", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("STANDING-CLEARENCE", "VIEW");
    this.rows = [];
    this.standingClearence = new StandingClearence();

    this.commonService.fetchEDCs().subscribe(
      edc => {
        console.log("In page load edc")
        this.edcList = edc;
        console.log(this.edcList)
      }
    )

  }

  searchResults() {

    this.service.getAllStandingClearences(this.searchOrgId,this.searchBuyerCompanyServiceId,this.searchEsIntentCode, this.searchStandingClearenceCode).subscribe(
      _standingclearence => {
        this.rows = _standingclearence;
        console.log("In search result")
        console.log(this.rows);
      });
  }

  newStandingClearence() {
    try {
      this.router.navigateByUrl('/standing-clearence/standing-clearence-new');

    }
    catch (e) {
      console.log(e);
    }

  }

  editStandingClearence(_id: string) {
    try {
      console.log("Edit id value" + _id);
      this.router.navigateByUrl('/standing-clearence/standing-clearence-edit/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }

  // fetchVoltages() {
  //   this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
  //     result => {
  //       this.voltages = result;
  //     },

  //     error => {
  //       console.error('Error loading Voltages');
  //       console.error(error);
  //     });
  // }


}







