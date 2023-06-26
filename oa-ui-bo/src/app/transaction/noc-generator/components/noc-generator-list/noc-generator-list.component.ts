import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

// import { MeterReadingDetailComponent } from './../../components/meter-reading-detail/meter-reading-detail.component';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { NocGeneratorService } from './../../services/noc-generator.service';
import { CommonService } from './../../../../shared/common/common.service';
import { NocGenerator } from './../../../vo/noc-generator';
@Component({
  selector: 'noc-generator-list',
  templateUrl: './noc-generator-list.component.html',
  providers: [NocGeneratorService,CommonUtils]
})
export class NocGeneratorListComponent implements OnInit {
  nocGenerator: NocGenerator;
  voltages = [];
  edcList = [];

  searchOrgId: string = "";
  disableEdc: boolean = false;
  searchEsIntentCode: string = "";
  searchNocGeneratorCode: string = "";
  searchSellerCompanyServiceId:string="";
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
    private service: NocGeneratorService
  ) {

    // this.fetchEDCs();
    this.fetchVoltages();

  }

  rows: Array<NocGenerator>;


  ngOnInit() {
    this.rows = [];
    this.accessAddFlag = this.commonUtils.userHasAccess("NOC-GENERATOR", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("NOC-GENERATOR", "VIEW");
    this.nocGenerator = new NocGenerator();
   
    this.nocGenerator.orgId = CommonUtils.getLoginUserDetails().substr(3,3); 
    if ((this.nocGenerator.orgId  != "") && isFinite(parseInt(this.nocGenerator.orgId ))) {
      this.searchOrgId = this.nocGenerator.orgId ;
     
      this.disableEdc = true;
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
  onEdcChange() {
    console.log("In edc change");
    this.service.getNocGeneratorCompanyByEdc(this.searchOrgId).subscribe(
      _companyServices => {
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }
  searchResults() {

    this.service.getAllNocGenerators(this.searchOrgId, this.searchSellerCompanyServiceId,this.searchEsIntentCode, this.searchNocGeneratorCode).subscribe(
      _noc => {
        this.rows = _noc;
        console.log("In search result")
        console.log(this.rows);
      });
  }
  newNoc() {
    try {
      this.router.navigateByUrl('/noc-generator/noc-generator-new');

    }
    catch (e) {
      console.log(e);
    }

  }

  editNoc(_id: string) {
    try {
      console.log("Edit id value" + _id);
      this.router.navigateByUrl('/noc-generator/noc-generator-edit/' + _id);
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







}