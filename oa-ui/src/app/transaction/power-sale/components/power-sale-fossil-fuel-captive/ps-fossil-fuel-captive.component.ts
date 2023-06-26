import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/startWith';

import { Component, OnInit, HostBinding } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule, FormControl } from '@angular/forms';
import { CommonService } from '../../../../shared/common/common.service';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { EsIntent ,EsIntentLine,Edc} from './../../../vo/es-intent';


import { NG_VALIDATORS,Validator,Validators,AbstractControl,ValidatorFn } from '@angular/forms';
import { PowerSaleService } from '../../services/power-sale.service';

@Component({
  selector: 'fossil-fuel-captive',
  templateUrl: './ps-fossil-fuel-captive.component.html',
  providers: [CommonService,PowerSaleService,DatePipe,CommonUtils
  ]

})

export class PsFossilFuelCaptiveComponent {
//---------------------------------------------------------------------------------  
  esIntent:EsIntent;
  esIntentLine:EsIntentLine;
  esIntentLineRowIndex:number;

  searchSellerCompanyServiceId: string = "";
  searchSellerEndOrgId: string = "";
//---------------------------------------------------------------------------------  
  mpFromConfig = {'placeHolder': 'Duration-From', 'readonly':false};
  mpFromModel = {'month':'', 'year':''};
  mpToConfig = {'placeHolder': 'Duration-To', 'readonly':false};
  mpToModel = {'month':'', 'year':''};
//---------------------------------------------------------------------------------
  companyServices=[];
  months= [];
  edcList=[];
  buyerCompanyServices=[];
  isCaptives = [
    { "key": "Y", "name": "Y - Captive" },
  ];
  agmtPeriodCodes=[
    {"key": "STOA", "name": "STOA" },
    {"key": "MTOA", "name": "MTOA" },
    {"key": "LTOA", "name": "LTOA" }
  ]
  userType:String;


//---------------------------------------------------------------------------------
  accessUpdateFlag:boolean = false;
  accessListFlag:boolean = false;
  accessSaveFlag:boolean = false;
  accessApplyFlag:boolean = false;
  accessPsListFlag:boolean=false;
  accessApproveFlag:boolean=false;
  accessViewFlag:boolean=false;
//---------------------------------------------------------------------------------
  disableControls: boolean = false;
  enableProcess:boolean = false;
  enableSave:boolean = false;
  addScreen: boolean = true;

  isGen:boolean=false;
  genDisable:boolean=false;

//---------------------------------------------------------------------------------
  fromDate:any;
  toDate:any;
//---------------------------------------------------------------------------------

  constructor(
    private commonUtils: CommonUtils,
    private route: ActivatedRoute,
    private router: Router,
    private commonService :CommonService,
    private service: PowerSaleService,
    private datePipe: DatePipe
  ) {

  }

  ngOnInit() {
    this.esIntent = new EsIntent();
    this.esIntentLine = new EsIntentLine();
//---------------------------------------------------------------------------------

    this.accessListFlag=this.commonUtils.userHasAccess("SALE","LIST");  

    this.accessUpdateFlag=this.commonUtils.userHasAccess("POWER-SALE","UPDATE");
    this.accessSaveFlag=this.commonUtils.userHasAccess("POWER-SALE","SAVE");
    this.accessPsListFlag=this.commonUtils.userHasAccess("POWER-SALE","LIST");
    this.accessApplyFlag=this.commonUtils.userHasAccess("POWER-SALE","APPLY");
    this.accessViewFlag=this.commonUtils.userHasAccess("POWER-SALE","VIEW");
    this.accessApproveFlag=this.commonUtils.userHasAccess("POWER-SALE","APPROVE");

    
    this.isGen = CommonUtils.getLoginUserTypeCode()=="GEN";
    this.esIntent.statusCode!="CREATED";




//---------------------------------------------------------------------------------


    this.mpFromModel = null;
    this.mpToModel = null;
    // this.route.queryParams.filter(params=>params.flowType)
    // .subscribe(params=>{
    //   this.esIntent.flowTypeCode=params.flowType;
    // })
    this.esIntent.flowTypeCode='CAPTIVE';
    this.route.queryParams.filter(params=>params.id)
    .switchMap((params: Params) => this.service.getEsIntentById(params.id))
    .subscribe(_esIntent=>{
            this.esIntent = _esIntent;
            this.addScreen = false;
            this.onEdcChange();
            this.esIntent.sellerCompanyServiceId = this.esIntent.sellerCompanyServiceId;          
            console.log("Es-intent");
            console.log(this.esIntent);


            // if(this.esIntent.statusCode != ""){
            //   console.log("statusCode")
            //   console.log(this.esIntent.statusCode)
            //   this.genDisable=false;
            // }else 
            
            if(this.esIntent.statusCode=="CREATED"){
              console.log("statusCode")
              console.log(this.esIntent.statusCode)
              this.genDisable=false;
            }else{
              this.genDisable=true;
            }


            this.mpFromModel = {'month':this.esIntent.fromMonth, 'year':this.esIntent.fromYear};
            this.mpToModel = {'month':this.esIntent.toMonth, 'year':this.esIntent.toYear};

        });

     

        this.esIntent.sellerEndOrgId = CommonUtils.getLoginUserEDC();
        this.esIntent.sellerCompanyId = CommonUtils.getLoginUserCompanyId(); 
        this.esIntent.sellerCompanyServiceId = CommonUtils.getLoginUserServiceNumber();
        console.log("service id")
        console.log(this.esIntent.sellerCompanyServiceId)


        if ((this.esIntent.sellerEndOrgId != "") && isFinite(parseInt(this.esIntent.sellerEndOrgId))&&  (this.esIntent.sellerCompanyServiceNumber != "")) {
          this.searchSellerEndOrgId = this.esIntent.sellerEndOrgId;
          this.searchSellerCompanyServiceId = this.esIntent.sellerCompanyServiceId;
          
       
          console.log(this.esIntent.sellerEndOrgId);
          console.log(this.esIntent.sellerCompanyServiceId);
          // this.disableEdc = true;
          this.onEdcChange();
        }


    this.commonService.fetchEDCs().subscribe(
      edc=>{
        console.log("In page load edc")
        this.edcList = edc;
        console.log(this.edcList)
      }
    )
  }


  mpOnFromModelChange(e){
	  this.esIntent.fromMonth = e.month;
	  this.esIntent.fromYear= e.year;

  }
  mpOnToModelChange(e){
	  this.esIntent.toMonth = e.month;
	  this.esIntent.toYear= e.year;

  }
//---------------------ON CHANGE------------------------------------------------------------

  onEdcChange(){
    console.log("In edc change");
    console.log(this.esIntent.sellerEndOrgId)
    this.service.getSellers(this.esIntent.sellerEndOrgId).subscribe(
      _companyServices=>{
        this.companyServices = _companyServices;
        console.log(this.companyServices);
      }
    )
  }
  onServiceChange(){   
    console.log(this.companyServices);
    console.log("In service change");
    var sellerCompanyService = this.companyServices.filter((item)=> item.id == this.esIntent.sellerCompanyServiceId)[0];
    this.esIntent.sellerCompanyId= sellerCompanyService.companyId;
    this.esIntent.sellerCompanyName= sellerCompanyService.companyName;
    if(sellerCompanyService.fuelTypeCode!=null){
      this.esIntent.fuelTypeCode =sellerCompanyService.fuelTypeCode;
    }
    console.log(this.esIntent);
  }

  onBuyerEdcChange(){
    console.log("In edc change");
    console.log(this.esIntentLine.buyerEndOrgId);
    this.service.getBuyers(this.esIntentLine.buyerEndOrgId).subscribe(
      _buyerCompanyServices=>{
        this.buyerCompanyServices = _buyerCompanyServices;
        console.log(this.buyerCompanyServices);
      }
    )
  }

  onBuyerServiceChange(){   
    console.log(this.buyerCompanyServices);
    console.log("In service change");
    var buyerCompanyService = this.buyerCompanyServices.filter((item)=> item.id == this.esIntentLine.buyerCompanyServiceId)[0];
    this.esIntentLine.buyerCompanyId= buyerCompanyService.companyId;
    this.esIntentLine.buyerCompanyName= buyerCompanyService.companyName;
    this.esIntentLine.buyerCompanyServiceNumber= buyerCompanyService.number;
    this.esIntentLine.nocStatusCode="Not Started";
    this.esIntentLine.consentStatusCode="Not Started";
    this.esIntentLine.oaAgmtStatusCode="Not Started";
    console.log(this.esIntentLine);
    
  }

  listPsStatus(){
    // this.router.navigate(['/log/detail'] ,{queryParams:{id:_id}});
    this.router.navigateByUrl('/log/detail?id='+this.esIntent.id);

  }

//---------------------------MONTH CONVERTER------------------------------------------------------

  monthConvertor() {
    
    var fromMonthStr = this.esIntent.fromMonth;
    var fromYearStr = this.esIntent.fromYear;
    var fromMonth = +fromMonthStr;
      fromMonth = fromMonth - 1;
    var fromYear = +fromYearStr;
    this.fromDate = new Date(fromYear,fromMonth, 1);

    var toMonthStr = this.esIntent.toMonth;
    var toYearStr = this.esIntent.toYear;
    var toMonth = +toMonthStr;
    toMonth = toMonth - 1;
    var toYear = +toYearStr;
    this.toDate = new Date(toYear,toMonth + 1, 0);
    this.esIntent.fromDate = this.fromDate;
    this.esIntent.toDate = this.toDate;   

  }

  //---------------------------------------------------------------------------------

  saveEsIntent() {
    this.monthConvertor() ;
    this.formatChangesforDB();
    console.log('In save' + this.addScreen);
    if (this.addScreen) {
      this.addEsIntent();
    }
    else {
      this.updateEsIntent();
    }
  }

  formatChangesforDB() {

    this.esIntent.fromDate = this.datePipe.transform(this.esIntent.fromDate, 'yyyy-MM-dd');
    this.esIntent.toDate = this.datePipe.transform(this.esIntent.toDate, 'yyyy-MM-dd');
    this.esIntent.sldcAwaitedDt = this.datePipe.transform(this.esIntent.sldcAwaitedDt, 'yyyy-MM-dd');
    this.esIntent.sldcApprovalDt = this.datePipe.transform(this.esIntent.sldcApprovalDt, 'yyyy-MM-dd');
    this.esIntent.sldcRejectedDt = this.datePipe.transform(this.esIntent.sldcRejectedDt, 'yyyy-MM-dd');

  }

  addEsIntent() {
    this.monthConvertor() ;
    this.formatChangesforDB();
    this.esIntent.statusCode="CREATED";
    this.service.addEsIntent(this.esIntent).subscribe(
      result => {
         console.log(result);
         alert(result.message);
        this.listEsIntent();
      },
      error => {
        console.error('Error adding EsIntent!');
        console.error(error);
        this.listEsIntent();
      }
    );
  }

  updateEsIntent() {
    this.monthConvertor() ;
    this.formatChangesforDB();
   this.service.updateEsIntent(this.esIntent).subscribe(
      result => {
        this.listEsIntent();
      },
      error => {
        console.error('Error updating EsIntent!');
        console.error(error);
        this.listEsIntent();
      }
    );
  }


  processEsIntent() {
    this.esIntent.statusCode="PROCESS";
        this.formatChangesforDB();
        this.service.processEsIntent(this.esIntent).subscribe(
          result => {
            this.listEsIntent();
          },
          error => {
            console.error('Error Processing EsIntent!');
            console.error(error);
            this.listEsIntent();
          }
        );
      }

      sldcApprovalEsIntent() {
            this.formatChangesforDB();
            this.service.sldcApprovalEsIntent(this.esIntent).subscribe(
              result => {
                this.listEsIntent();
              },
              error => {
                console.error('Error Approving EsIntent!');
                console.error(error);
                this.listEsIntent();
              }
            );
          }
          sldcRejectEsIntent() {
            this.formatChangesforDB();
            this.service.sldcRejectEsIntent(this.esIntent).subscribe(
              result => {
                this.listEsIntent();
              },
              error => {
                console.error('Error Rejecting EsIntent!');
                console.error(error);
                this.listEsIntent();
              }
            );
          }
  //---------------------------------------------------------------------------------  

  initEsIntentLines(){

  this.esIntentLine = new EsIntentLine();
  this.esIntentLineRowIndex = -1;
  }
  //---------------------------------------------------------------------------------

  addEsIntentLine(){
    if(!this.esIntent.energySaleIntentLines){
      this.esIntent.energySaleIntentLines=[];
      }
      this.esIntent.energySaleIntentLines.push(Object.assign({}, this.esIntentLine));  
      console.log(this.esIntent.energySaleIntentLines);
      console.log(this.esIntentLine);

    this.initEsIntentLines();
  } 
  
  
  updateEsIntentLine(){
    
    //this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
    // the above code wasnt updating a row in the table due to somebug
    // as a workaround, creating a new array with same rows except the edited row.
    var tempArray =[];
    for (var index = 0; index < this.esIntent.energySaleIntentLines.length; index++) {
      if(this.esIntentLineRowIndex == index){
        tempArray.push(Object.assign({},this.esIntentLine));
      }
      else{
        tempArray.push(this.esIntent.energySaleIntentLines[index]);
      }
      
    }
    this.esIntent.energySaleIntentLines = tempArray;
    this.initEsIntentLines();
  }
  
  
  editEsIntentLine(_index: number){
   this.esIntentLineRowIndex =_index; 
   var _esIntentLine=Object.assign({},this.esIntent.energySaleIntentLines[_index]); 
   this.onBuyerEdcChange();
   this.esIntentLine =_esIntentLine;
   console.log(this.esIntentLine)
   
  }
  
  deleteEsIntentLine(_index: number){ 
   this.esIntent.energySaleIntentLines.splice(_index,1);
  }
//---------------------------------------------------------------------------------

  listEsIntent() {
    this.router.navigateByUrl('/sale/sale-create');
  }

  // listEsIntentid() {
  //   var id = this.esIntent.id;
  //   console.log(this.esIntent.id)

  //   console.log(id)
  //   this.router.navigateByUrl('/power-sale/fossil-fuel-captive-edit?id='+id);
  // }

//---------------------------------------------------------------------------------

 



 



  






}