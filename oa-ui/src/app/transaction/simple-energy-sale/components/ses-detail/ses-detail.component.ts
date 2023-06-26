import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { DatePipe } from '@angular/common';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EsEvent } from './../../../energy-sale/services/es.event';
import { EsService } from './../../../energy-sale/services/es.service';
import { Es, EsUsageDetail, EsUsageSummary, generationStatementForPrint, AvailableGsUnits, AllocatedGsUnits } from './../../../vo/es';
import { CommonService } from './../../../../shared/common/common.service';
import { CompanyService } from './../../../../master/company/services/company.service';
import { GsService } from './../../../../transaction/generation-statement/services/gs.service';
import { Gs } from './../../../../transaction/vo/gs';
import { TradeRelationshipService } from './../../../../master/trade-relationship/services/trade-relationship.service';
import { TradeRelationship } from './../../../../master/vo/trade-relationship';
import { SignupService } from './../../../../master/signup/services/signup.service';
import { HotRegisterer } from 'angular-handsontable';
@Component({
  selector: 'ses-detail',
  templateUrl: './ses-detail.component.html',
  providers: [SignupService, MatDatepickerModule,  MatNativeDateModule, DatePipe, EsService, EsEvent,CommonUtils]

})
export class SesDetailComponent implements OnInit {
  ses: Es;
  gs: Gs;
  esUsageSummary: EsUsageSummary;
  esUsageDetail: EsUsageDetail;
  esUsageDetailList: Array<EsUsageDetail>;
  esUsageSummaryRowIndex: number;
  consumerServices = [];
  consumerCompanies: Array<TradeRelationship>;
  filteredServices = []
  isMandatory = true;
  statusCode: string;
  availableGsUnits: Array<AvailableGsUnits>;
  allocatedGsunits: Array<AllocatedGsUnits>;
  allocationFlag: boolean = false;

  months = [];
  orgList = [];
  filteredOrgList = []
  voltages = [];
  generationStatementForPrint: Array<generationStatementForPrint>;
  c1Name: string;
  c2Name: string;
  c3Name: string;
  c4Name: string;
  c5Name: string;
  c1: string; c2: string; c3: string; c4: string; c5: string;
  total: any;
  // c1Total:any;
  buyerName: any;
  drawalVoltageName: any;
  filteredSubstation = [];
  mpConfig = { 'placeHolder': 'MM/YYYY', 'readonly': false };
  mpModel = { 'month': '', 'year': '' };
  settings = {
    colHeaders: ["Date", "C1", "C2", "C3", "C4", "C5", "Total"],
    

  };
  accessUpdateFlag:boolean = false;
  accessDeleteFlag:boolean = false;
  accessApproveFlag:boolean = false;
  accessCompleteFlag:boolean = false;
  accessProcessFlag:boolean = false;
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: EsService,
    private commonService: CommonService,
    private companyService: CompanyService,
    private esEvent: EsEvent,
    private gsService: GsService,
    private tradeService: TradeRelationshipService,
    private datePipe: DatePipe,
    private _hotRegisterer: HotRegisterer,
    private signupService: SignupService
  ) {

  }

  ngOnInit() {
    this.accessUpdateFlag=this.commonUtils.userHasAccess("ENERGY-STATEMENT","UPDATE");
    this.accessDeleteFlag=this.commonUtils.userHasAccess("ENERGY-STATEMENT","DELETE");
    this.accessApproveFlag=this.commonUtils.userHasAccess("ENERGY-STATEMENT","APPROVE");
    this.accessCompleteFlag=this.commonUtils.userHasAccess("ENERGY-STATEMENT","COMPLETE");
    this.accessProcessFlag=this.commonUtils.userHasAccess("ENERGY-STATEMENT","PROCESS"); 
    this.fetchOrgList()
    this.ses = new Es();
    this.gs = new Gs();
    this.esUsageSummary = new EsUsageSummary();
    this.esUsageDetail = new EsUsageDetail();
    this.esUsageDetailList = Array<EsUsageDetail>();
    this.consumerServices = new Array<TradeRelationship>();
    this.mpModel = null;
    this.months = this.commonService.fetchMonths();
    this.generationStatementForPrint = [];
    this.availableGsUnits = [];
    this.allocatedGsunits = [];

    this.esEvent.es$.subscribe(latestValue => {
      this.ses = latestValue;
    });
    this.esEvent.load();


    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 

      this.route.params
        .subscribe((_params: Params) => {
          this.service.getEsById(_params['_id']).subscribe(_es => {

            this.ses = _es;
            this.esUsageDetailList = this.ses.energySaleUsageDetails;
            this.ses.id = _params['_id'];

            this.filteredOrgList[0] = { 'id': this.ses.sellerEndOrgId, 'name': this.ses.sellerEndOrgName };
            this.buyerName = this.ses.energySaleUsageSummaries[0].buyerCompanyName;
            //this.drawalVoltageName = this.ses.energySaleUsageSummaries[0].drawalVoltageName;
            this.filteredBuyerList[0] = { 'buyerCompServiceId': this.ses.energySaleUsageSummaries[0].buyerCompanyServiceId, 'name': this.buyerName, 'buyerEndOrgId': this.ses.energySaleUsageSummaries[0].buyerEndOrgId, 'tradeRelationshipId': this.ses.energySaleUsageSummaries[0].tradeRelationshipId };
            console.log("filteredBuyerList");
            console.log(this.filteredBuyerList)
            this.fetchBuyerCompanyServices();
            this.fetchSubstations();

            this.fetchTradeRelationship();
            this.mpModel = { 'month': this.ses.month, 'year': this.ses.year };
            this.populateUsageDetailTable();
            this.formatChangesforDB();
          });
        });
    }
  }

  fetchTradeRelationship() {
    if (this.ses.sellerEndOrgId && this.filteredOrgList.length == 1) {
      this.tradeService.getTradeRelationships("", "", "", this.ses.sellerEndOrgId, "", "", this.ses.month, this.ses.year, "", "").subscribe(
        _consumerNames => {
          this.consumerCompanies = _consumerNames;
        });
      if (this.consumerCompanies && this.consumerCompanies.length > 0) this.ses.energySaleUsageSummaries[0].tradeRelationshipId = this.consumerCompanies
        .filter((item) => item.buyerCompanyId == this.ses.sellerEndOrgId)[0].id;
    }
  }
  datewiseConsumption = [];
  populateUsageDetailTable() {
    this.datewiseConsumption = [];
    if (!this.ses.year && this.ses.year.length != 4 && !this.ses.month) return;
    var y = parseInt(this.ses.year), m = (parseInt(this.ses.month) - 1);
    var numOfDays = new Date(y, m, 0).getDate();

    for (var i = 0; i <= numOfDays; i++) {
      var d = new Date(y, m, i + 1);
      var dstring = d.getFullYear() + "-" + ("0" + (d.getMonth() + 1)).slice(-2) + "-" + ("0" + d.getDate()).slice(-2);
      for (var j in this.ses.energySaleUsageDetails) {
        if (dstring + " 00:00:00" == this.ses.energySaleUsageDetails[j].usageDate) {
          this.ses.energySaleUsageDetails[j].usageDate = (this.ses.energySaleUsageDetails[j].usageDate) ? this.ses.energySaleUsageDetails[j].usageDate.substr(0, 10) : "";
          this.datewiseConsumption[i] = this.ses.energySaleUsageDetails[j];


          break;
        }
        else {

          this.datewiseConsumption[i] = { usageDate: dstring, c1: 0, c2: 0, c3: 0, c4: 0, c5: 0, total: 0 }
        }
      }
    }

  }
  instance: string = "hotInstance";

  getData() {
    const hot = this._hotRegisterer.getInstance(this.instance)  ;
    this.datewiseConsumption = hot.getData();
    // console.log('here', hot.getData());  //WRITE YOUR INSERT LOGIC HERE

    this.ses.c1 = this.ses.c2 = this.ses.c3 = this.ses.c4 = this.ses.c5 = this.ses.netAllocation = '0';

    for (var j in this.datewiseConsumption) {

      var total;
      if (this.datewiseConsumption[j][0] != null) {
        this.datewiseConsumption[j][6] = (parseInt(this.datewiseConsumption[j][1]) || 0) + (parseInt(this.datewiseConsumption[j][2]) || 0) + (parseInt(this.datewiseConsumption[j][3]) || 0) + (parseInt(this.datewiseConsumption[j][4]) || 0) + (parseInt(this.datewiseConsumption[j][5]) || 0);
        this.ses.c1 = ((parseInt(this.ses.c1) || 0) + (parseInt(this.datewiseConsumption[j][1]) || 0)).toString();
        this.ses.c2 = ((parseInt(this.ses.c2) || 0) + (parseInt(this.datewiseConsumption[j][2]) || 0)).toString();
        this.ses.c3 = ((parseInt(this.ses.c3) || 0) + (parseInt(this.datewiseConsumption[j][3]) || 0)).toString();
        this.ses.c4 = ((parseInt(this.ses.c4) || 0) + (parseInt(this.datewiseConsumption[j][4]) || 0)).toString();
        this.ses.c5 = ((parseInt(this.ses.c5) || 0) + (parseInt(this.datewiseConsumption[j][5]) || 0)).toString();
        this.ses.netAllocation = ((parseInt(this.ses.netAllocation) || 0) + (parseInt(this.datewiseConsumption[j][6]) || 0)).toString();

      }


    }

  }
  formatChangesforDB() {
    this.ses.fromDate = this.datePipe.transform(this.ses.fromDate, 'yyyy-MM-dd');
    this.ses.toDate = this.datePipe.transform(this.ses.toDate, 'yyyy-MM-dd');
    this.esUsageDetail.usageDate = this.datePipe.transform(this.esUsageDetail.usageDate, 'yyyy-MM-dd');
    this.ses.energySaleUsageDetails = [];
    this.ses.simpleEnergySale = 'Y';
  
    this.ses.energySaleUsageDetails = [];
    for (var j in this.datewiseConsumption) {
      console.log(this.datewiseConsumption[j])

      console.log(this.datewiseConsumption[j][7])
      if (this.datewiseConsumption[j][6] > 0) {
        if (this.filteredBuyerList.length == 1) {
          this.datewiseConsumption[j].buyerCompanyServiceId = this.filteredBuyerList[0]['buyerCompServiceId'];
        }
        if (this.datewiseConsumption[j][0] != null) {
       
          this.esUsageDetail = new EsUsageDetail();
          this.datewiseConsumption[j][6] = ((parseInt(this.datewiseConsumption[j][1]) || 0) + (parseInt(this.datewiseConsumption[j][2]) || 0) + (parseInt(this.datewiseConsumption[j][3]) || 0) + (parseInt(this.datewiseConsumption[j][4]) || 0) + (parseInt(this.datewiseConsumption[j][5]) || 0)).toString();
          this.esUsageDetail.buyerCompanyServiceId = this.datewiseConsumption[j].buyerCompanyServiceId;
          this.esUsageDetail.usageDate = this.datewiseConsumption[j][0];
          this.esUsageDetail.c1 = this.datewiseConsumption[j][1];
          this.esUsageDetail.c2 = this.datewiseConsumption[j][2];
          this.esUsageDetail.c3 = this.datewiseConsumption[j][3];
          this.esUsageDetail.c4 = this.datewiseConsumption[j][4];
          this.esUsageDetail.c5 = this.datewiseConsumption[j][5];
          this.esUsageDetail.total = this.datewiseConsumption[j][6];
          if (this.ses.id != '') {
  
            this.esUsageDetail.id = this.esUsageDetailList.filter(s => s.usageDate == this.datewiseConsumption[j][0])[0].id;
            this.esUsageDetail.buyerCompanyServiceId = this.esUsageDetailList.filter(s => s.usageDate == this.datewiseConsumption[j][0])[0].buyerCompanyServiceId;
            this.esUsageDetail.energySaleId = this.esUsageDetailList.filter(s => s.usageDate == this.datewiseConsumption[j][0])[0].energySaleId;
          }

          this.ses.energySaleUsageDetails.push(this.esUsageDetail);
        }


      }
    }
    this.ses.energySaleUsageSummaries[0].c1 = this.ses.gc1 = this.ses.availc1 = this.ses.availGc1 = this.ses.c1;
    this.ses.energySaleUsageSummaries[0].c2 = this.ses.gc2 = this.ses.availc2 = this.ses.availGc2 = this.ses.c2;
    this.ses.energySaleUsageSummaries[0].c3 = this.ses.gc3 = this.ses.availc3 = this.ses.availGc3 = this.ses.c3;
    this.ses.energySaleUsageSummaries[0].c4 = this.ses.gc4 = this.ses.availc4 = this.ses.availGc4 = this.ses.c4;
    this.ses.energySaleUsageSummaries[0].c5 = this.ses.gc5 = this.ses.availc5 = this.ses.availGc5 = this.ses.c5;
    this.ses.energySaleUsageSummaries[0].total = this.ses.netGeneration = this.ses.netAllocation;

    if (this.filteredSubstation.length == 1) {
      this.ses.energySaleUsageSummaries[0].drawalVoltageCode = this.filteredSubstation[0]['code'];
      this.ses.injectingVoltageCode = this.filteredSubstation[0]['code'];
    }


    this.ses.energySaleUsageSummaries[0].buyerCompanyServiceId = this.filteredBuyerList[0].buyerCompServiceId;
    this.ses.energySaleUsageSummaries[0].buyerEndOrgId = this.filteredBuyerList[0].buyerOrgId;
    if (this.ses.id == '') {
      this.ses.energySaleUsageSummaries[0].tradeRelationshipId = this.filteredBuyerList[0].id;
    }
    else {
      this.ses.energySaleUsageSummaries[0].tradeRelationshipId = this.filteredBuyerList[0].tradeRelationshipId;
    }


  }

  // updateDetailTotal(){
  // this.ses.c1 = this.ses.c2 = this.ses.c3 = this.ses.c4 = this.ses.c5 = this.ses.netAllocation = '0';
  // for(var j in this.datewiseConsumption){
  // 	this.datewiseConsumption[j].total = (parseInt(this.datewiseConsumption[j].c1)||0)+(parseInt(this.datewiseConsumption[j].c2)||0)+(parseInt(this.datewiseConsumption[j].c3)||0)+(parseInt(this.datewiseConsumption[j].c4)||0)+(parseInt(this.datewiseConsumption[j].c5)||0);
  // 	this.ses.c1 = ((parseInt(this.ses.c1) || 0) + (parseInt(this.datewiseConsumption[j].c1)||0)).toString();
  // 	this.ses.c2 = ((parseInt(this.ses.c2) || 0) +(parseInt(this.datewiseConsumption[j].c2)||0)).toString();
  // 	this.ses.c3 = ((parseInt(this.ses.c3) || 0) +(parseInt(this.datewiseConsumption[j].c3)||0)).toString();
  // 	this.ses.c4 = ((parseInt(this.ses.c4) || 0) +(parseInt(this.datewiseConsumption[j].c4)||0)).toString();
  // 	this.ses.c5 = ((parseInt(this.ses.c5) || 0) +(parseInt(this.datewiseConsumption[j].c5)||0)).toString();
  // 	this.ses.netAllocation = ((parseInt(this.ses.netAllocation) || 0) +(parseInt(this.datewiseConsumption[j].total)||0)).toString();
  // }

  // }
  listSess() {
    this.router.navigateByUrl('/ses/ses-list');

  }

  saveSes() {
    this.getData();
    this.formatChangesforDB();
    console.log(this.ses)


    if (this.allocationFlag) {
      alert("Cannot update Energy Sale!!! - Error in Allocation ")
    } else {
      if (this.ses.id == '') {
        this.addSes();
      }
      else {
        this.updateSes();
      }

    }

  }
  filteredBuyerList = [];

  
  addSes() {


    this.service.addEs(this.ses).subscribe(
      result => {
        this.ses.id = JSON.stringify(result);
        this.listSess();
      },
      error => {
        console.error('Error adding Simple energy sale Application!');
        console.error(error);
        this.listSess();
      }
    );
  }

  updateSes() {
    if (this.allocationFlag) {
      alert("Cannot update Energy Sale!!! - Error in Allocation ")
    } else {
      this.service.updateEs(this.ses).subscribe(
        result => {
          console.log('Energy Sale(_id:' + result + ') updated');
          this.listSess();
        },
        error => {
          console.error('Error updating company!');
          console.error(error);
          this.listSess();
        }
      );
    }
  }
  finalSes() {
    this.formatChangesforDB();
    if (this.allocationFlag) {
      alert("Cannot Approve Energy Sale!!! - Error in Allocation ")
    } else {
      this.service.finalEs(this.ses).subscribe(
        result => {
          console.log('Energy Sale(_id:' + result + ') final');
          this.listSess();
        },
        error => {
          console.error('Error updating final!');
          console.error(error);
        }
      );
    }

  }

  filterOrgList(val: string) {
    return val ? this.orgList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.orgList;
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

  buyerCompanyServices = [];
  filterBuyerList(val: string) {
    return val ? this.buyerCompanyServices.filter((s) => s.buyerCompanyName.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.buyerCompanyServices;
  }
  fetchBuyerCompanyServices() {
    if (this.filteredOrgList.length == 1) {
      console.log(this.ses)
      console.log("IEX-" + this.filteredOrgList[0]['id']);
      var edc = "IEX-" + this.filteredOrgList[0]['id'];

      this.ses.sellerCompanyServiceId = "IEX-" + this.filteredOrgList[0]['id'];
      this.ses.sellerEndOrgId = this.filteredOrgList[0]['id'];
      this.tradeService.getTradeRelationships("", "", "", edc, "", "", this.ses.month, this.ses.year, "", "").subscribe(
        _consumerNames => {
          this.buyerCompanyServices = _consumerNames;
          // console.log("In trade company2")
          console.log(this.buyerCompanyServices)
          // console.log(this.es)
          // this.statusCode = this.es.statusCode;
          console.log("status code" + this.statusCode)
        });
      // this.signupService.getBuyerCompanyServicesByOrgId(this.filteredOrgList[0]['id']).subscribe(
      //   result => {
      //     this.buyerCompanyServices = result;
      //   },
      //   error => {
      //     console.error('Error loading company!');
      //     console.error(error);
      //   });
    }
  }


  substationList = [];
  filterSubstations(val: string) {
    return val ? this.substationList.filter((s) => s.name.replace(/[^\w\s]/gi, '').match(new RegExp(val.replace(/[^\w\s]/gi, ''), 'gi'))) : this.substationList;
  }

  fetchSubstations() {
    if (this.filteredOrgList.length == 1) {
      this.commonService.fetchSubstationsByOrgId(this.filteredOrgList[0]['id']).subscribe(
        result => {
          this.substationList = result;
        },
        error => {
          console.error('Error loading substaion!');
          console.error(error);
        });
    }
  }
  mpOnModelChange(e) {
    this.ses.month = e.month;
    this.ses.year = e.year;
    this.populateUsageDetailTable();
  }
}
