import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { Gs } from './../../../vo/gs';
import { GsService } from './../../services/gs.service';
import { CommonService } from './../../../../shared/common/common.service';
import { CompanyServiceV1 } from '../../../../master/company/services/company.service.v1';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { GenericReportInput } from '../../../../report/vo/GenericReportInput';
import { concat } from 'rxjs/observable/concat';

@Component({
  selector: 'gs-list',
  templateUrl: './gs-list.component.html',
  styleUrls: ['./gs-list.component.scss'],
  providers: [CompanyServiceV1, CommonUtils]
})
export class GsListComponent implements OnInit {
  gs: Gs;
  orgList = [];
  companyId = [];
  filterNCES = [];


  flowTypes = [
    { "key": "STB", "name": "SALE-TO-BOARD" },
    { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
    { "key": "THIRD-PARTY", "name": "THIRD-PARTY" }

  ]

  flowTypeCode: string ="";
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



  //for pagination
  count = 0;
  offset = 0;
  limit = 8;
  edc: string;
  buyerDetails: Array<Gs>;
  buy: any;
  years: any[];
  // searchEDCCode:string="";

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
    // this.getbuyerservicenoSerach()
    this.buyerDetails = new Array<Gs>();
    this.gs = new Gs();
    this.genStatment=[];
    this.rows = [];
    this.page(this.offset, this.limit);
    this.fetchFuelTypes();
    this.months = this.commonService.fetchMonths();
    this.gs.orgId = CommonUtils.getLoginUserEDC();
    if (this.gs.orgId != "") {
      console.log("in edc select")
      this.searchOrgId = this.gs.orgId;
      this.disableEdc = true;
    }
    
    this.statementMonth = this.commonUtils.getPerviousMonth();
    this.years = this.commonService.fetchYearList();
   
  //when we need generator first name for login use this eg: Premier cotton textiles login
     console.log(CommonUtils.getLoginUserTypeCode());
       if(CommonUtils.getLoginUserTypeCode()=='GEN')
      {
         console.log(CommonUtils.getLoginUserTypeCode());
         console.log( CommonUtils.getLoginUserCompany());
         this.gs.dispCompanyServiceNumber = CommonUtils.getLoginUserCompany();
         if (this.gs.dispCompanyServiceNumber != "") {
          this.dispCompanyServiceNumber = this.gs.dispCompanyServiceNumber;
          this.searchcompanyServiceNumber = this.gs.dispCompanyServiceNumber;

            // this.disableEdc = true;
          this.disableCompanyName = true;
 
          }
      }
  }

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


  page(offset, limit) {
    this.offset = offset;
    this.limit = limit;
    // this.fetchResults()


  }

  // filterEDCs(val: string) {
  //   console.log();
  //   return val ? this.orgList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.orgList;
  // }

  // fetchEDCs() {
  //   this.commonService.fetchEDCs().subscribe(
  //     result => {
  //     this.orgList = result;
  //     },
  //     error => {
  //       console.error('Error loading orgs!');
  //       console.error(error);
  //     });
  // }


  fetchResult() {
    this.rows=[];
    console.log(this.gs.orgId)
    console.log(this.flowTypeCode)
    if (this.gs.orgId != "") {
      this.searchOrgId = this.gs.orgId;
    }

    if(CommonUtils.getLoginUserTypeCode()=='GEN'){
    if (this.gs.dispCompanyServiceNumber != "") {
      this.searchcompanyServiceNumber = this.gs.dispCompanyServiceNumber;
    }

    console.log(this.searchcompanyServiceNumber)
    
  }
  this.service.searchCompanies(this.searchCompanyName, this.searchOrgId, this.searchcompanyServiceNumber, this.statementMonth, this.statementYear,this.companyServiceId,this.dispFuelTypeName, '',this.flowTypeCode).subscribe(
    _generationstatements => {

      // if(CommonUtils.getLoginUserTypeCode()=='EDC'){
      //   this.genStatment = _generationstatements;
      //   console.log(this.genStatment);


      //   this.service.searchBuyerCompanies(this.searchOrgId,this.searchcompanyServiceNumber,this.statementMonth, this.statementYear,this.dispFuelTypeName).subscribe(
      //     x => {
      //         this.buyerDetails = x;
      //         console.log(this.buyerDetails);
      //         this.genStatment.push(...this.buyerDetails);
      //       this.rows=this.genStatment;
      //   console.log(this.rows); 
      //   });

      //   }
      //   else{
      //     this.rows = _generationstatements;
      //     console.log(this.rows);
      //   }

        this.rows = _generationstatements;
          console.log(this.rows);
        this.gs.orgId = CommonUtils.getLoginUserEDC();

      });
     
  }




  fetchResult1() {
    this.rows=[];
    console.log(this.gs.orgId)
    if (this.gs.orgId != "") {
      this.searchOrgId = this.gs.orgId;
    }
    console.log(this.searchOrgId)
    
    // if (this.gs.dispCompanyName != "") {
    //   this.searchCompanyName = this.gs.dispCompanyName;
    // }

    // console.log(this.searchCompanyName)
    if(CommonUtils.getLoginUserTypeCode()=='EDC'){
      this.service.searchBuyerCompanies(this.searchOrgId,this.searchcompanyServiceNumber,this.statementMonth, this.statementYear,this.dispFuelTypeName).subscribe(
        x => {
            this.buyerDetails = x;
            console.log(this.buyerDetails);

    });
  }

if(CommonUtils.getLoginUserTypeCode()=='GEN'){
    if (this.gs.dispCompanyServiceNumber != "") {
      this.searchcompanyServiceNumber = this.gs.dispCompanyServiceNumber;
    }

    console.log(this.searchcompanyServiceNumber)
    
  }
  +    this.service.searchCompanies(this.searchCompanyName, this.searchOrgId, this.searchcompanyServiceNumber, this.statementMonth, this.statementYear,this.companyServiceId,this.dispFuelTypeName, '',this.flowTypeCode).subscribe(
    _generationstatements => {
      // this.rows = _generationstatements;
        // console.log(this.rows)
        
      if(CommonUtils.getLoginUserTypeCode()=='EDC'){
        this.genStatment = _generationstatements;
        console.log(this.genStatment);
        this.genStatment.push(...this.buyerDetails);
        this.rows=this.genStatment;
        // this.rows = this.genStatment.concat(this.buyerDetails);
        console.log(this.rows); 
        }
        else{
          this.rows = _generationstatements;
          console.log(this.rows);
        }

        this.gs.orgId = CommonUtils.getLoginUserEDC();

      });
    //   if(CommonUtils.getLoginUserTypeCode()=='EDC'){
    //     console.log(this.searchcompanyServiceNumber)
        
    //     this.service.searchBuyerCompanies(this.searchOrgId,this.searchcompanyServiceNumber,this.statementMonth, this.statementYear,this.dispFuelTypeName).subscribe(
    //       x => {
    //           this.buyerDetails = x;
  
    //           // this.rows = this.genStatment;
    //           // this.rows = this.buyerDetails;
    //           console.log(this.genStatment)
    //           this.rows.push(this.buyerDetails);
    //           // this.rows = this.genStatment.concat(this.buyerDetails);
    //           console.log(this.rows);
    //           // this.rows=this.buyerDetails;
              
  
    //   });
    // }
     
  }


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


  // fetchResults(){
  //   this.service.getGss().subscribe(
  //     _gss => {  
  //       this.tempResults = _gss;

  //       this.count = this.tempResults.length;
  //       const start = this.offset * this.limit;
  //       const end = start + this.limit;
  //       const rows = [...this.rows];

  //       for (let i = start; i < end; i++) {
  //         rows[i] = this.tempResults[i];
  //       }

  //       this.rows = rows;
  //       console.log("In fetch result rows");
  //       console.log(this.rows);
  //       console.log('Page Results', start, end, rows);
  //     });
  // }



  onPage(event) {
    console.log('Page Event', event);
    this.page(event.offset, event.limit);
  }

  // ** no ui for statement creation using UI
  // newGc() {
  //   try{
  //     this.router.navigateByUrl('/gs/gc-new');
  //     // to-do
  //     // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

  //   }
  //   catch(e){
  //   console.log(e);
  //   }

  // }

  editGs(_id: string) {
    try {
      console.log(_id);
      //this.router.navigateByUrl('/gs/gs-edit/'+ _id+'/view/'+_viewType);
      this.router.navigateByUrl('/gs/gs-edit/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

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