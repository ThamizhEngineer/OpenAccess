import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { MeterReadingDetailComponent } from './../../components/meter-reading-detail/meter-reading-detail.component';

import { MeterReadingService } from './../../services/meter-reading.service';
// import { MeterReadingImport} from './../../../vo/meter-reading-import'; 
import { CommonService } from './../../../../shared/common/common.service';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { MeterReading } from './../../services/meter-reading';
@Component({
  selector: 'meter-reading-list',
  templateUrl: './meter-reading-list.component.html',
  //styleUrls: ['./-list.component.scss']
  providers: [MeterReadingService,CommonUtils]
})
export class MeterReadingListComponent implements OnInit {

  orgList = [];
  meterReading: MeterReading;

  flowTypes = [
    { "key": "STB", "name": "SALE-TO-BOARD" },
    { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
    { "key": "THIRD-PARTY", "name": "THIRD-PARTY" }

  ]

  filteredEDCs = [];
  months = [];
  filterNCES = [];
  fuelTypes = [];
  searchcompanyServiceNumber: string = "";
  searchmodemNumber: string = "";
  searchsellermeterNumber: string = "";
  searchcompanyName: string = "";
  searchorgId: string = "";
  searchMonth: string = "";
  searchYear: string = "";
  selFuel: string= "";
  flowTypeCode: string ="";
  disableEdc: boolean = false;
  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  years: any[];
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,

    private service: MeterReadingService
  ) {

    // this.fetchEDCs();

  }
  rows: Array<MeterReading>;

  ngOnInit() {
    this.accessAddFlag = this.commonUtils.userHasAccess("METER-READING", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("METER-READING", "VIEW");
    this.rows = [];
    this.meterReading = new MeterReading();
    this.fetchEDCs();
    this.fetchFuelTypes();
    this.searchMonth = this.commonUtils.getPerviousMonth();
  
    this.years = this.commonService.fetchYearList();
    this.months = this.commonService.fetchMonths();
    this.meterReading.orgId = CommonUtils.getLoginUserEDC();
    console.log(CommonUtils.getLoginUserEDC())
    if (this.meterReading.orgId != "") {
      console.log("in edc select")
      this.searchorgId = this.meterReading.orgId;
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
  // fetchFuelTypes(){
  //   this.commonService.fetchCodes("FUEL_TYPE_CODE")
  //   .subscribe(
  //     fuelTypes => {this.fuelTypes = fuelTypes;},
  //     error => {
  //       console.error("Error loading Fuel Types");
  //       console.error(error);
  //     });
  // }

  fetchFuelTypes(){
    this.commonService.FetchFuels()
    .subscribe(
      fuelTypes => {this.fuelTypes = fuelTypes;},
      error => {
        console.error("Error loading Fuel Types");
        console.error(error);
      });
  }

  searchResults() {
    if (this.meterReading.orgId != "") {
      this.searchorgId = this.meterReading.orgId;
    }


    this.service.getMeterReading(this.searchcompanyServiceNumber,this.searchmodemNumber, this.searchsellermeterNumber, this.searchMonth, this.searchYear, this.searchorgId, this.searchcompanyName,this.selFuel,this.flowTypeCode).subscribe(
      _es => {
        this.rows = _es;
        console.log("In search result")
        console.log(this.rows);

        this.meterReading.orgId = CommonUtils.getLoginUserEDC();
        //this.fuelTypes = 

      });
  }
  openMr(_id: string) {
    try {
      console.log(_id);
      this.router.navigateByUrl('/meter-reading/meter-reading-detail/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
    }
    catch (e) {
      console.log(e);
    }

  }
  // fetchEDCs() {

  //   this.commonService.fetchEDCs().subscribe(
  //     result => {

  //       this.orgList = result;

  //     });
  // }

  fetchEDCs() {
    // this.commonService.fetchWindEDCs().subscribe(
      this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
        console.log(result)

        // this.searchOrgid = CommonUtils.getLoginUserEDC();
        // console.log(this.searchOrgid)
       
        if(CommonUtils.getLoginUserTypeCode()=='MRT'){
          console.log(CommonUtils.getLoginUserCompany());
          console.log(this.orgList.filter((item) => item.ncesDivisionCode))
          this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode == CommonUtils.getLoginUserCompany());
          console.log(this.filterNCES)

          this.orgList =  this.filterNCES;
          console.log(this.orgList)
      

        }
        else if(CommonUtils.getLoginUserTypeCode()=='NCES'){
          console.log(CommonUtils.getLoginUserCompany());
          console.log(this.orgList.filter((item) => item.ncesDivisionCode))
          this.filterNCES = this.orgList.filter((item) => item.ncesDivisionCode);
          console.log(this.filterNCES)

          this.orgList =  this.filterNCES;
          console.log(this.orgList)
      

        }
       
 
      },
      error => {
        console.error('Error loading orgs!');
        console.error(error);
      });
  }

  ///To allow number only
  numberFormat(event){
    this.commonService.numberOnly(event)
}

}