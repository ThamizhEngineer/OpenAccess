import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EsAddComponent } from './../es-add/es-add.component';
import { EsService } from './../../services/es.service';
import { Es } from '../../../vo/es';
import { CommonService } from './../../../../shared/common/common.service';


@Component({
  selector: 'es-list',
  templateUrl: './es-list.component.html',
  styleUrls: ['./es-list.component.scss']
})
export class EsListComponent implements OnInit {
  es: Es;
  rows: Array<Es>;
  dialogRef: MatDialogRef<EsAddComponent>;
  config: MatDialogConfig = {
    disableClose: false,
    width: '100%',
    height: '70%',
    position: {
      top: '',
      bottom: '',
      left: '10%',
      right: ''
    }
  };
  lastCloseResult: string;

  tempResults: Array<Es>;
  orgList = [];
  //for pagination
  count = 0;
  offset = 0;
  limit = 8;
  months = [];
  fuelTypes = [];
  edcId: string;
  searchSellerCompanyName: string = "";
  searchSellerCompanyServiceNumber: string = "";
  searchSellerCompanyID: string = "";
  searchEdcName: string = "";
  searchMonth: string = "";
  searchYear: string = "";
  searchOrgId: string = "";
  searchIsSTB: string = "";
  fuelTypeName: string ="";
  flowTypeCode: string ="";


  
  
  filterNCES = [];

  searchCompanyId: string = "";
  disableEdc: boolean = false;
  disableCompanyName: boolean = false;
  
  searchsellerCompanyServiceNumber: string = "";
  sellerCompanyServiceNumber:string="";

  accessAddFlag: boolean = false;
  accessViewFlag: boolean = false;
  flowTypeCodes = [
    { "key": "", "name": "" },
    { "key": "STB", "name": "STB" },
    { "key": "IS-CAPTIVE", "name": "IS-CAPTIVE" },
    { "key": "THIRD-PARTY", "name": "THIRD-PARTY" }
  ];
  isSTBs = [
    { "key": "Y", "name": "SALE-TO-BOARD" },
    { "key": "N", "name": "CAPTIVE" }
    ]
  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private service: EsService,
    private commonService: CommonService,
    public dialog: MatDialog
    
  ) {
    console.log('router path' + this.router.url);
  }

  ngOnInit() {
    this.es = new Es();
    this.accessAddFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "CREATE");
    this.accessViewFlag = this.commonUtils.userHasAccess("ENERGY-SALE", "VIEW");
    this.months = this.commonService.fetchMonths();
    this.rows = [];
    this.fetchEDCs();
    this.fetchFuelTypes();
    this.page(this.offset, this.limit);
    this.es.sellerEndOrgId = CommonUtils.getLoginUserEDC();
   
    


this.searchMonth = this.commonUtils.getPerviousMonth();
this.searchYear = this.commonUtils.getCurrentYear();
this.searchResults();
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
      console.log(CommonUtils.getLoginUserTypeCode());
      console.log( CommonUtils.getLoginUserCompany());
       this.es.sellerCompanyServiceNumber = CommonUtils.getLoginUserCompany();
       if (this.es.sellerCompanyServiceNumber != "") {
          this.sellerCompanyServiceNumber = this.es.sellerCompanyServiceNumber;
         // this.disableEdc = true;
         this.disableCompanyName = true;
         console.log(this.sellerCompanyServiceNumber);

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
    //this.fetchResults()


  }


  // fetchEDCs() {
  //   this.commonService.fetchEDCs().subscribe(
  //     result => {
  //       this.orgList = result;
  //     },
  //     error => {
  //       console.error('Error loading orgs!');
  //       console.error(error);
  //     });
  // }

  fetchEDCs() {
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

  searchResults() {

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
        console.log(this.sellerCompanyServiceNumber)
      }
    this.service.getAllEss(this.searchSellerCompanyName, this.sellerCompanyServiceNumber,this.searchOrgId,this.searchSellerCompanyID,this.searchIsSTB,this.fuelTypeName,'',this.searchMonth, this.searchYear ,'N', this.flowTypeCode).subscribe(
      _es => {
        this.rows = _es;
        console.log("In search result")
        console.log(this.rows);

        this.es.sellerEndOrgId = CommonUtils.getLoginUserEDC();
        
        

      });
  }
  fetchResults() {
    this.service.getEss().subscribe(
      _eas => {
        this.tempResults = _eas;

        this.count = this.tempResults.length;
        const start = this.offset * this.limit;
        const end = start + this.limit;
        const rows = [...this.rows];

        for (let i = start; i < end; i++) {
          rows[i] = this.tempResults[i];
        }

        this.rows = rows;
        console.log(rows);
        console.log('Page Results', start, end, rows);
      });
  }

  onPage(event) {
    console.log('Page Event', event);
    this.page(event.offset, event.limit);
  }


  newEs() {
    try {

      this.dialogRef = this.dialog.open(EsAddComponent, this.config);
      this.dialogRef.afterClosed().subscribe(result => {
      if(result!=null && result!=undefined){
        this.editEs(result);
      }
        this.dialogRef = null;
      });

    }
    catch (e) {
      console.log(e);
    }
  }

  editEs(_id: string) {
    try {
      console.log(_id);
      this.router.navigateByUrl('/es/es-edit/' + _id);
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

    }
    catch (e) {
      console.log(e);
    }

  }

}
