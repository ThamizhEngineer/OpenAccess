import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { Gs } from '../../../vo/gs';
import { GsService } from '../../services/gs.service';
import { CommonService } from '../../../../shared/common/common.service';
import { CompanyServiceV1 } from '../../../../master/company/services/company.service.v1';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { GenericReportInput } from '../../../../report/vo/GenericReportInput';
import { concat } from 'rxjs/observable/concat';

@Component({
  selector: 'gs-consumer-list',
  templateUrl: './gs-consumer-list.component.html',
  styleUrls: ['./gs-consumer-list.component.scss'],
  providers: [CompanyServiceV1, CommonUtils]
})
export class GsConsumerListComponent implements OnInit {
  gs: Gs;
  orgList = [];
  companyId = [];
  filterNCES = [];

  rows: Array<Gs>;
  genStatment: Array<Gs>;
  tempResults: Array<Gs>;
  filteredEDCs = [];
  filteredCompanyname = [];
  months = [];
  fuelTypes= [];
  dispFuelTypeName: string = "";
  searchOrgId: string = "";
  searchCompanyName: string = "";
  dispCompanyName: string = "";
  dispOrgName: string = "";
  companyServiceNumber: string = "";
  statementMonth: string = "";
  statementYear: string = "";
  companyServiceId: string="";
  disableEdc: boolean = false;
  disableCompanyName: boolean = false;
  dispCompanyServiceNumber:string="";
  searchcompanyServiceNumber: string = "";
  years: any[];


  count = 0;
  offset = 0;
  limit = 8;
  edc: string;
  buyerDetails: Array<Gs>;
  buy: any;

  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private service: GsService,
    private companyservice: CompanyServiceV1


  ) {
    this.fetchEDCs();

    console.log('router path' + this.router.url);
  }

  ngOnInit() {
    this.buyerDetails = new Array<Gs>();
    this.gs = new Gs();
    this.genStatment=[];
    this.rows = [];
    this.page(this.offset, this.limit);
    this.fetchFuelTypes();
    this.months = this.commonService.fetchMonths();
    this.years = this.commonService.fetchYearList();
    this.gs.orgId = CommonUtils.getLoginUserEDC();
    if (this.gs.orgId != "") {
      console.log("in edc select")
      this.searchOrgId = this.gs.orgId;
      this.disableEdc = true;
    }
    
    this.statementMonth = this.commonUtils.getPerviousMonth();
    this.statementYear = this.commonUtils.getCurrentYear();
   
     console.log(CommonUtils.getLoginUserTypeCode());
       if(CommonUtils.getLoginUserTypeCode()=='GEN')
      {
         console.log(CommonUtils.getLoginUserTypeCode());
         console.log( CommonUtils.getLoginUserCompany());
         this.gs.dispCompanyServiceNumber = CommonUtils.getLoginUserCompany();
         if (this.gs.dispCompanyServiceNumber != "") {
          this.dispCompanyServiceNumber = this.gs.dispCompanyServiceNumber;
          this.searchcompanyServiceNumber = this.gs.dispCompanyServiceNumber;

          this.disableCompanyName = true;
 
          }
      }
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


  page(offset, limit) {
    this.offset = offset;
    this.limit = limit;
  }


  fetchResult() {
    this.rows=[];
    console.log(this.gs.orgId)
    if (this.gs.orgId != "") {
      this.searchOrgId = this.gs.orgId;
    }

    if(CommonUtils.getLoginUserTypeCode()=='GEN'){
    if (this.gs.dispCompanyServiceNumber != "") {
      this.searchcompanyServiceNumber = this.gs.dispCompanyServiceNumber;
    }

    console.log(this.searchcompanyServiceNumber)
    
  }
  this.service.searchBuyerCompanies(this.searchOrgId,this.searchcompanyServiceNumber,this.statementMonth, this.statementYear,this.dispFuelTypeName).subscribe(
            x => {
                this.rows = x;
          // console.log(this.rows); 
          this.gs.orgId = CommonUtils.getLoginUserEDC();
          });  
  }


  fetchEDCs() {
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


  onPage(event) {
    console.log('Page Event', event);
    this.page(event.offset, event.limit);
  }


  editGs(_id: string) {
    try {
      console.log(_id);
      this.router.navigateByUrl('/gsc/gs-edits/' + _id);
    }
    catch (e) {
      console.log(e);
    }

  }

///To allow number only
    numberFormat(event){
      this.commonService.numberOnly(event)
    }

}