import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

// import { MeterReadingDetailComponent } from './../../components/meter-reading-detail/meter-reading-detail.component';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { NocService } from './../../services/noc.service';
import { CommonService } from './../../../../shared/common/common.service';
import { Noc } from './../../../vo/noc';
@Component({
  selector: 'noc-list',
  templateUrl: './noc-list.component.html',
  providers: [NocService, CommonUtils]
})
export class NocListComponent implements OnInit {
  noc: Noc;
  voltages = [];
  edcList = [];

  searchBuyerEndOrgId: string = "";
  searchBuyerCompanyServiceNumber: string = "";
  searchBuyerCompanyName: string = "";
  searchSellerEndOrgId: string = "";
  searchSellerCompanyServiceNumber: string = "";
  searchSellerCompanyId: string = "";
  searchSellerCompanyName: string = "";
  searchBuyerVoltageCode: string = "";
  searchBuyerCompanyServiceId: string = "";
  searchStatus: string = "";
  searchEsIntentCode: string = "";
  searchNocCode: string = "";
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
    private service: NocService
  ) {

    // this.fetchEDCs();
    this.fetchVoltages();

  }

  rows: Array<Noc>;

  disableEdc: boolean = false;
  isEdc:boolean=false;

  ngOnInit() {
    this.rows = [];
    this.noc = new Noc();
    this.isEdc = CommonUtils.getLoginUserTypeCode()=="EDC";

    this.accessAddFlag = this.commonUtils.userHasAccess("NOC", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("NOC", "VIEW");
    this.noc.sellerOrgId = CommonUtils.getLoginUserEDC();
   // this.noc.buyerEndOrgId = CommonUtils.getLoginUserEDC();  
    this.noc.sellerCompanyId = CommonUtils.getLoginUserCompanyId();
    if(CommonUtils.getLoginUserTypeCode()=="EDC"){
      this.searchBuyerEndOrgId= CommonUtils.getLoginUserEDC();
    }

    console.log(this.noc.sellerOrgId);
    console.log(this.noc.sellerCompanyId);
    if ((this.noc.sellerOrgId  != "") && isFinite(parseInt(this.noc.sellerOrgId )) && (this.noc.sellerCompanyId != "")) {
      this.searchSellerEndOrgId = this.noc.sellerOrgId;
     // this.searchBuyerEndOrgId = this.noc.buyerEndOrgId;
      this.searchSellerCompanyId = this.noc.sellerCompanyId;
      this.disableEdc = true;
       
      console.log(this.searchSellerEndOrgId);
      console.log(this.searchSellerCompanyServiceNumber);
      this.onEdcChange();
    }

    this.commonService.fetchEDCs().subscribe(
      edc => {
        console.log("In page load edc")
        this.edcList = edc;
        console.log(this.edcList)
      }
    )

  }

  searchResults() {

    if ((this.noc.sellerOrgId  != "") && isFinite(parseInt(this.noc.sellerOrgId )) && (this.noc.buyerEndOrgId  != "") && isFinite(parseInt(this.noc.buyerEndOrgId )) && (this.noc.sellerCompanyId  != "")) {
      this.searchSellerEndOrgId = this.noc.sellerOrgId;
      this.searchBuyerEndOrgId = this.noc.buyerEndOrgId;
      this.searchSellerCompanyId = this.noc.sellerCompanyId ;
    }

    this.service.getAllNocs(this.searchBuyerEndOrgId, this.searchBuyerCompanyServiceNumber, this.searchBuyerCompanyName, this.searchSellerCompanyServiceNumber,this.searchSellerCompanyId, this.searchSellerCompanyName, this.searchBuyerVoltageCode, this.searchStatus, this.searchEsIntentCode, this.searchBuyerCompanyServiceId, this.searchNocCode).subscribe(
      _noc => {
        this.rows = _noc;
        console.log("In search result")
        console.log(this.rows);
      });
  }
  newNoc() {
    try {
      this.router.navigateByUrl('/noc/noc-new');

    }
    catch (e) {
      console.log(e);
    }

  }

  editNoc(_id: string) {
    try {
      console.log("Edit id value" + _id);
      this.router.navigateByUrl('/noc/noc-edit/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }

  fetchVoltages() {
    this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
      result => {
        this.voltages = result;
      },

      error => {
        console.error('Error loading Voltages');
        console.error(error);
      });
  }



  onEdcChange() {
    console.log("In edc change");
    this.service.getNocCompanyByEdc("", this.searchSellerEndOrgId, this.searchSellerCompanyId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;

        console.log(this.companyServices);
      }
    )
  }



}