import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding ,ViewChild} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from './../../../../shared/common/common.service';
import { ColumnMode } from '@swimlane/ngx-datatable';
import { EaService } from './../../services/ea.service';
import { Ea } from '../../../vo/ea';

@Component({
  selector: 'ea-list',
  templateUrl: './ea-list.component.html',
  styleUrls: ['./ea-list.component.scss']
})
export class EaListComponent implements OnInit {
    es : Ea;
    rows: Array<Ea>;
   // rows = [];
    tempResults: Array<Ea>;
    months = [];
    years = [];
    orgList = [];
    filterNCES = [];


    ColumnMode = ColumnMode;
    searchSellerCompanyName: string = "";
    searchSellerCompanyServiceNumber: string = "";
    searchSellerCompanyID: string = "";
    searchEdcName: string = "";
    searchMonth: string = "";
    searchYear: string = "";
    searchOrgId: string = "";
    searchIsSTB: string = "";
    searchsellerCompanyServiceId: string = "";
    searchSellerCompanyId: string = "";
    sellerCompanyServiceNumber:string="";
    genUser:boolean = false;
    disableEdc: boolean = false;
    disableServiceNumber: boolean = false;

    flowTypes = [
      { "key": "STB", "name": "SALE-TO-BOARD" },
      { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
      { "key": "THIRD-PARTY", "name": "THIRD-PARTY" }

    ]

    isSTBs = [
      { "key": "Y", "name": "SALE-TO-BOARD" },
      { "key": "N", "name": "CAPTIVE" }
    ]
  fuelTypes: any;
  fuelCode: string;
  flowTypeCode: any;

    constructor(
        private commonUtils: CommonUtils,
        private route: ActivatedRoute,
        private router: Router,
        private service: EaService,
        private commonService: CommonService,
        public dialog: MatDialog) {

      }
    
      ngOnInit() {
        this.es = new Ea();
            
        this.months = this.commonService.fetchMonths();
        this.years = this.commonService.fetchYearList();
        this.rows = [];
        this.fetchEDCs();
        this.es.sellerEndOrgId = CommonUtils.getLoginUserEDC();
        this.searchMonth = this.commonUtils.getPerviousMonth();
        // this.searchYear = this.commonUtils.getCurrentYear();
        this.searchYear = this.commonUtils.getEaYear();
        this.fetchss();
        this.fetchFuelTypes();

        if (this.es.sellerEndOrgId != "") {
          this.searchOrgId = this.es.sellerEndOrgId;
          this.disableEdc = true; 
        }

     //   this.es.sellerCompanyId = CommonUtils.getLoginUserCompany();
  //   if (this.es.sellerCompanyId != "") {
  //     this.searchCompanyId = this.es.sellerCompanyId;
  //  //   this.disableEdc = true;
  //   }

    //when we need generator first name for login use this eg: Premier cotton textiles login
        if(CommonUtils.getLoginUserTypeCode()=='GEN')
        {
          this.genUser = true;
          console.log(CommonUtils.getLoginUserTypeCode());
          console.log( CommonUtils.getLoginUserCompany());
          this.es.sellerCompanyServiceNumber = CommonUtils.getLoginUserCompany();
          if (this.es.sellerCompanyServiceNumber != "") {
              this.sellerCompanyServiceNumber = this.es.sellerCompanyServiceNumber;
            this.disableServiceNumber = true;
            console.log("1--"+this.sellerCompanyServiceNumber);

          }
          }

   }

    fetchss(){

        if (this.es.sellerEndOrgId != "") {
          this.searchOrgId = this.es.sellerEndOrgId;
        } 
        console.log(this.searchOrgId)
    
        // if (this.es.sellerCompanyId != "") {
         //   this.searchCompanyId = this.es.sellerCompanyId;
        // }

            if(CommonUtils.getLoginUserTypeCode()=='GEN'){
            if (this.es.sellerCompanyServiceNumber != "") {
              this.sellerCompanyServiceNumber = CommonUtils.getLoginUserCompany();
            }
            console.log("2--"+this.sellerCompanyServiceNumber)
          }

        this.service.getAllEss(this.searchSellerCompanyName, this.sellerCompanyServiceNumber,this.searchOrgId,this.searchSellerCompanyId,this.searchsellerCompanyServiceId,this.searchIsSTB, this.searchMonth, this.searchYear,'N',this.fuelCode,this.flowTypeCode).subscribe(
            _es => {
              this.rows = _es;
              console.log("In search result")
              console.log(this.rows);   
              
              this.es.sellerEndOrgId = CommonUtils.getLoginUserEDC();
            });
      }


      // -------Navigations-------------------------------------------------------------------

      validateEa(){
        this.router.navigateByUrl('/ea/ea-process');
      }

      editEa(_id: string) {
        try {
          console.log(_id);
          console.log("came in new");
          this.router.navigateByUrl('/ea/ea-detail/'+ _id);
        }
        catch (e) {
          console.log(e);
        }
    
      }

      // ----Combo feteches-------------------------------------------------------------


      fetchEDCs() {
        // this.commonService.fetchWindEDCs().subscribe(
          this.commonService.fetchEDCs().subscribe(
          result => {
            this.orgList = result;
            console.log(result)
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
    
      fetchFuelTypes(){
        this.commonService.FetchFuels()
        .subscribe(
          fuelTypes => {this.fuelTypes = fuelTypes;},
          error => {
            console.error("Error loading Fuel Types");
            console.error(error);
          });
      }

      ///To allow number only
    numberFormat(event){
      this.commonService.numberOnly(event)
  }
  
}