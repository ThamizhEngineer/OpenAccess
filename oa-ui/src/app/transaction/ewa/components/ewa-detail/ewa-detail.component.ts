import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding,ChangeDetectionStrategy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
//import { SessionStorage} from "angular2-localstorage/WebStorage";
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EwaEvent } from './../../services/ewa.event';
import { EwaService } from './../../services/ewa.service';
import { Ewa, EwaLines } from './../../../vo/ewa';
import { CommonService } from './../../../../shared/common/common.service';
import { FileUploader } from 'ng2-file-upload/ng2-file-upload';
// const URL = '/api/';
const URL = 'https://evening-anchorage-3159.herokuapp.com/api/';
@Component({
  selector: 'ewa-detail',
  templateUrl: './ewa-detail.component.html',
  //styleUrls: [],
  providers: [EwaService, CommonUtils],
  
})

export class EwaDetailComponent implements OnInit {

  ewa: Ewa;

  nextEwaStatus: string = 'Applied';
  nextAction: string = 'Apply';
  disableControls: boolean = true;
  totalInjectionPeakUnits: string = "";
  totalInjectionOffPeakUnits: string = "";

  rowIndex: number;
  addScreen: boolean = true;
  // waType: string = "consumer";
  uploader: FileUploader = new FileUploader({
    url: URL,
    isHTML5: true
  });
  hasBaseDropZoneOver = false;
  hasAnotherDropZoneOver = false;

  fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }

  fileOverAnother(e: any): void {
    this.hasAnotherDropZoneOver = e;
  }
  accessUpdateFlag: boolean = false;
  accessDeleteFlag: boolean = false;
  accessApproveFlag: boolean = false;
  accessCompleteFlag: boolean = false;
  accessProcessFlag: boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: EwaService,
    private commonService: CommonService,
    private ewaEvent: EwaEvent
  ) {
    //this.fetchVoltageList();
  }

  ngOnInit() {

    this.ewa = new Ewa();
    this.accessUpdateFlag = this.commonUtils.userHasAccess("EWA", "UPDATE");
    this.accessDeleteFlag = this.commonUtils.userHasAccess("EWA", "DELETE");
    this.accessApproveFlag = this.commonUtils.userHasAccess("EWA", "APPROVE");
    this.accessCompleteFlag = this.commonUtils.userHasAccess("EWA", "COMPLETE");
    this.accessProcessFlag = this.commonUtils.userHasAccess("EWA", "PROCESS");
    this.ewaEvent.ewa$.subscribe(latestValue => {
      this.ewa = latestValue;
      this.setNextStatus(this.ewa.statusDesc);

    });
    this.ewaEvent.load();

    if (this.route.params['_value']['_id']) {
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getEwaById(params['_id']))
        .subscribe((_ewa: Ewa) => {
          this.ewa = _ewa;
          this.ewaEvent.set(_ewa);
          this.setNextStatus(this.ewa.statusDesc);
        });
    }

  }

  setNextStatus(status: string) {

    switch (status) {
      case 'Initiated':
        this.nextEwaStatus = 'Applied';
        this.nextAction = 'Apply';
        break;
      case 'Applied':
        this.nextEwaStatus = 'Approved';
        this.nextAction = 'Approve';
        break;
      case 'Approved':
        this.nextEwaStatus = '';
        this.nextAction = '';
        break;
      case 'Disapproved':
        this.nextEwaStatus = '';
        this.nextAction = '';
        break;

      default:
        break;
    }
    this.disableControls = (this.nextEwaStatus === '') ? true : false;

  }

  saveEwa(status: string) {
    //save can be add or update
    if (status != '') {
      this.ewa.statusDesc = status;
      var date = new Date();
      var dateString = date.getDate() + '-' + date.getMonth() + '-' + date.getFullYear();
      switch (status) {
        case 'Applied':
          this.ewa.appliedDate = dateString;
          break;
        case 'Approved':
          this.ewa.approvedDate = dateString;
          break;
        case 'Disapproved':
          //this.ewa.disapprovedDate = dateString;
          break;

        default:
          break;
      }
    }

    if (this.ewa.id == '') {
      this.addEwa();
    }
    else {
      this.updateEwa();
    }
  }

  addEwa() {
    this.service.addEwa(this.ewa).subscribe(
      result => {

        this.listEwas();
      },
      error => {
        console.error('Error adding NOC Application!');
        console.error(error);
      }
    );
  }

  updateEwa() {
    this.service.updateEwa(this.ewa).subscribe(
      result => {
        this.listEwas();
      },
      error => {
        console.error('Error updating Ewa!');
        console.error(error);
      }
    );
  }

  approveEwa() {
    this.service.approveEwa(this.ewa).subscribe(
      result => {
        this.listEwas();
      },
      error => {
        console.error('Error approveing Ewa!');
        console.error(error);
      }
    );
  }

  completeEwa() {
    this.service.completeEwa(this.ewa).subscribe(
      result => {
        this.listEwas();
      },
      error => {
        console.error('Error complete Ewa!');
        console.error(error);
      }
    );
  }
  listEwas() {
    // this.router.navigateByUrl('/ewa/ewa-list');
    this.router.navigateByUrl('/sale/list/EWA');
  }

  ewaLines: any;
  isCaptive: boolean = false;
  isAdd: boolean = false;
  addCaptive() {
    this.isCaptive = true;
    this.isAdd = true;
    this.ewaLines = { 'buyerOrgName': '', 'buyerCompanyName': '', 'buyerCapacity': '', 'drawalVoltageDesc': '', 'remarks': '', 'injectionPeakUnits': '', 'injectionOffPeakUnits': '', 'loss': '', 'drawalPeakUnits': '', 'drawalOffPeakUnits': '' }
    if (this.ewa && this.ewa.ewaLines.length) {
      this.ewaLines.buyerOrgName = this.ewa.ewaLines[0].buyerOrgName;
      this.ewaLines.buyerCompanyName = this.ewa.ewaLines[0].buyerCompanyName;
      this.ewaLines.buyerCapacity = this.ewa.ewaLines[0].buyerCapacity;
      this.ewaLines.drawalVoltageDesc = this.ewa.ewaLines[0].drawalVoltageDesc;
    }
  }
  editCaptive(obj) {
    this.isCaptive = true;
    this.isAdd = false;
    this.ewaLines = obj;
  }
  calculateApprovedUnits() {
    console.log(this.ewaLines.drawalPeakUnits + this.ewaLines.drawalOffPeakUnits);
    var totalApprovedUnits = 0;
    var totalDrawalUnits = parseInt(this.ewaLines.drawalPeakUnits) + parseInt(this.ewaLines.drawalOffPeakUnits);
    console.log(totalDrawalUnits)
    if (totalDrawalUnits > parseInt(this.ewaLines.proposedUnits)) {

    } else {
      this.ewaLines.approvedUnits = totalDrawalUnits.toString();
    }

    this.ewa.ewaLines.forEach(function (lines) {
      // this.totalInjectionPeakUnits+=parseInt(lines.drawalPeakUnits);
      // this.totalInjectionOffPeakUnits+=parseInt(lines.drawalOffPeakUnits);
      totalApprovedUnits += parseInt(lines.approvedUnits);



    });

    console.log("totalApprovedUnits" + totalApprovedUnits)

    if (totalApprovedUnits > parseInt(this.ewa.totalProposedUnits)) {

    } else {
      this.ewa.totalApprovedUnits = totalApprovedUnits.toString();
    }

    console.log(this.ewa.totalApprovedUnits)

  }

  updateCaptive() {

    this.calculateApprovedUnits();
    this.isCaptive = false;
    if (this.isAdd) {
      this.ewa.ewaLines.push(this.ewaLines);
      console.log(this.ewa.ewaLines);
    }
  }
}
