import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding ,ViewChild} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { CommonService } from './../../../../shared/common/common.service';
import { ColumnMode } from '@swimlane/ngx-datatable';
import { EaService } from './../../services/ea.service';
import { Ea } from '../../../vo/ea';
import { AuthUser } from '../../../../user/services/authuser';

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


    isSTBs = [
      { "key": "Y", "name": "SALE-TO-BOARD" },
      { "key": "N", "name": "CAPTIVE" }
    ]
  message: string;
  powerplant: any;
  companyService: any;
  newmessage: string;
  Alluser: boolean;
  CaptiveUser: boolean;

    constructor(
        private commonUtils: CommonUtils,
        private route: ActivatedRoute,
        private router: Router,
        private service: EaService,
        private commonService: CommonService,
        public dialog: MatDialog) {

      }
    
      ngOnInit() {
        if(CommonUtils.getLoginUserTypeCode()=="GEN"){
          this.service.getPowerplantByServiceId(CommonUtils.getLoginUserServiceNumber()).subscribe(
            _powerplants => {
              this.powerplant = _powerplants[0]; 
              if(this.powerplant!=null){  
                this.companyService=this.powerplant.company.services[0];
                if(this.powerplant.fuelTypeCode=='WIND' && this.companyService.flowTypeCode=='IS-CAPTIVE'){
                  this.CaptiveUser=true;
                
                  this.msg();
                  this.newmsg();
                }
                if( this.companyService.flowTypeCode!='STB'){
                  
                  this.Alluser=true;
                  
                  this.newmsg();
                }
              } 
            });
    
            }
        this.es = new Ea();
            
        this.months = this.commonService.fetchMonths();
        this.years = this.commonService.fetchYearList();
        this.rows = [];
        this.fetchEDCs();
        this.es.sellerEndOrgId = CommonUtils.getLoginUserEDC();
        this.searchMonth = this.commonUtils.getPerviousMonth();
        this.searchYear = (this.searchMonth=="12")?this.commonUtils.getpreviousYear():this.commonUtils.getCurrentYear();
        this.fetchss();
        

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
          this.genUser =true;
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

        this.service.getAllEss(this.searchSellerCompanyName, this.sellerCompanyServiceNumber,this.searchOrgId,this.searchSellerCompanyId,this.searchsellerCompanyServiceId,this.searchIsSTB, this.searchMonth, this.searchYear, 'N','').subscribe(
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
        this.commonService.fetchEDCByWind().subscribe(
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

      msg(){        
        this.message = "''As per the tariff orders issued by the Hon’ble TNERC on Wind Power and related issues, the energy generated during April shall be adjusted against consumption in April and the balance if any shall be reckoned as the banked energy. The generation in May shall be first adjusted against the consumption in May. If the consumption exceeds the generation during May, the energy available in the banking shall be drawn to the required extent. If the consumption during May is less than the generation during May, the balance shall be added to the banked energy. This procedure shall be repeated every month.''"
      }
      newmsg(){
        this.newmessage = "Kindly ensure correctness of allotment before giving confirmation, as deletion of allotment shall not be entertained under any circumstances."
      }
    
}