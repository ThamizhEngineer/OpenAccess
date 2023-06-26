import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { CommonUtils } from '../../../../shared/common/common-utils'
//import { SessionStorage} from "angular2-localstorage/WebStorage";
//import { ConsentEvent } from './../../services/consent.event';
import { NocService } from './../../services/noc.service';
import { CommonService } from './../../../../shared/common/common.service';
import { Noc } from './../../../vo/noc';
import { OaaService } from '../../../oaa/services/oaa.service';
import { Oaa } from './../../../vo/oaa';

import { FormControl } from '@angular/forms';

@Component({
  selector: 'noc-detail',
  templateUrl: './noc-detail.component.html',
  providers: [CommonService, NocService, DatePipe, OaaService, CommonUtils]
})

export class NocDetailComponent {
  addScreen: boolean = true;
  noc: Noc;
  oaa: Oaa;
  rows = [];
  Voltages = [];
  feederId = [];
  disableControls: boolean = true;

  exemptRc = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]
  hasDues = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]
  typeCode = [
    { "key": "BUYER-NOC", "name": "BUYER-NOC" },
    { "key": "SELLER-NOC", "name": "SELLER-NOC" }
  ]
  agmtPeriodCodes = [
    { "key": "STOA", "name": "STOA" },
    { "key": "MTOA", "name": "MTOA" },
    { "key": "LTOA", "name": "LTOA" }
  ];
  mpFromConfig = { 'placeHolder': 'Duration-From', 'readonly': false };
  mpFromModel = { 'month': '', 'year': '' };
  mpToConfig = { 'placeHolder': 'Duration-To', 'readonly': false };
  mpToModel = { 'month': '', 'year': '' };
  accessUpdateFlag: boolean = false;
  accessDeleteFlag: boolean = false;
  accessApproveFlag: boolean = false;
  accessCompleteFlag: boolean = false;
  accessProcessFlag: boolean = false;
  buyerSanctionedCapacityMW: number;
  approvedTotalUnitsSum: number=0;
  totalApprovedQuantum: number=0;

  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private datePipe: DatePipe,
    private service: NocService,
    private oaaService: OaaService


  ) {

    this.fetchVoltages();

  }

  ngOnInit() {

    this.noc = new Noc();
    this.accessUpdateFlag = this.commonUtils.userHasAccess("NOC", "UPDATE");
    this.accessDeleteFlag = this.commonUtils.userHasAccess("NOC", "DELETE");
    this.accessApproveFlag = this.commonUtils.userHasAccess("NOC", "APPROVE");
    this.accessCompleteFlag = this.commonUtils.userHasAccess("NOC", "COMPLETE");
    this.accessProcessFlag = this.commonUtils.userHasAccess("NOC", "PROCESS");
    console.log(this.route.params['_value']);
    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getNocById(this.route.params['_value']['_id']))
        .subscribe((_noc: Noc) => {

          this.noc = _noc;
          console.log(this.noc)
          this.mpFromModel = { 'month': this.noc.fromMonth, 'year': this.noc.fromYear };
          this.mpToModel = { 'month': this.noc.toMonth, 'year': this.noc.toYear };
          this.fetchOaa();


          console.log(this.noc.buyerSanctionedCapacity)
          console.log(parseFloat(this.noc.buyerSanctionedCapacity))

          this.buyerSanctionedCapacityMW = (parseFloat(this.noc.buyerSanctionedCapacity) * 0.9 / 1000);
          console.log("sellerSanctionedDemandMW")
          console.log(this.buyerSanctionedCapacityMW)


        });

    }

  }

  fetchOaa() {
    console.log(this.noc.buyerCompanyServiceId);
    //  this.oaaService.getAllOaas("","","","","","","","COMPLETED","",this.noc.sellerCompanyServiceId,"").subscribe(
    this.oaaService.getAllOaas("", "", "", "", "", "", this.noc.buyerCompanyServiceId, "COMPLETED", "", "", "").subscribe(
      result => {
        this.rows = result
        console.log(this.rows)
        let a = 0;
        this.rows.forEach(element => {
          console.log(element.approvedTotalUnits)
          console.log(this.approvedTotalUnitsSum += parseFloat(element.approvedTotalUnits))
          console.log('TotalSum: ' + (this.approvedTotalUnitsSum));
          console.log('SanctionedQuantum in mW: ' + (this.buyerSanctionedCapacityMW));
          this.totalApprovedQuantum=this.approvedTotalUnitsSum-this.buyerSanctionedCapacityMW;
          console.log('ApprovedTotal: ' + (this.totalApprovedQuantum));

        });

      });
  }

  fetchVoltages() {
    this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
      result => {
        this.Voltages = result;
      },

      error => {
        console.error('Error loading Voltages');
        console.error(error);
      });
  }


  saveNoc() {


    console.log("this.noc")
    console.log(this.noc)
    console.log('In save' + this.addScreen);
    if (this.addScreen) {
      this.addNoc();
    }
    else {
      this.updateNoc();
    }
  }

  addNoc() {
    console.log(this.buyerSanctionedCapacityMW)
    console.log(this.approvedTotalUnitsSum)

    this.service.addNoc(this.noc).subscribe(
      result => {
        console.log(result)

        this.listNoc();
      },
      error => {
        console.error('Error adding Noc!');
        console.error(error);
      }
    );
  }

  updateNoc() {
    console.log('Sanctioned demand: ' + (this.buyerSanctionedCapacityMW))
    console.log('Total Sum: ' + (this.approvedTotalUnitsSum))
    console.log('ApprovedTotal: ' + (this.totalApprovedQuantum))

    if(this.totalApprovedQuantum == null){
      if(parseFloat(this.noc.approvedCapacity) > this.buyerSanctionedCapacityMW){
        alert("Proposed quantum should not be more than "+this.buyerSanctionedCapacityMW);
        // this.listNoc();
        return false;
      }
      else{
        this.service.updateNoc(this.noc).subscribe(
          result => {
            this.listNoc();
          },
          error => {
            console.error('Error updating Noc!');
            console.error(error);
          }
        );
      }

    }else if(this.totalApprovedQuantum != null){
      if((parseFloat(this.noc.approvedCapacity)>this.totalApprovedQuantum)){
        alert("Proposed quantum should not be more than "+this.totalApprovedQuantum);
        // this.listNoc();
        return false;
      }
      else{
        this.service.updateNoc(this.noc).subscribe(
          result => {
            this.listNoc();
          },
          error => {
            console.error('Error updating Noc!');
            console.error(error);
          }
        );
      }
    }

  }

  listNoc() {
    this.router.navigateByUrl('/noc/noc-list');
    // this.router.navigateByUrl('/sale/list/NOC');
  }

  completeNoc() {
    this.service.completeNoc(this.noc).subscribe(
      result => {
        this.listNoc();
      },
      error => {
        console.error('Error completing noc!');
        console.error(error);
      }
    );
  }

  approveNoc() {
    this.service.approveNoc(this.noc).subscribe(
      result => {
        this.listNoc();
      },
      error => {
        console.error('Error approving Noc!');
        console.error(error);
      }
    );
  }
}