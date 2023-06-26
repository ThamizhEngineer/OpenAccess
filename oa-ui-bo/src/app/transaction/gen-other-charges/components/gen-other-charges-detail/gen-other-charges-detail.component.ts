import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding, ChangeDetectionStrategy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { GenOtherChargesService } from '../../services/gen-other-charges.service';
import { CommonService } from './../../../../shared/common/common.service';
import { GenOtherCharges, GenOtherSubCharge } from './../../../vo/geoothercharges';
import { CommonUtils } from '../../../../shared/common/common-utils';
//import { CommonUtils } from '../../../../shared/common/common-utils';



@Component({
  selector: 'gen-other-charges-detail',
  templateUrl: './gen-other-charges-detail.component.html',
  // providers: [CommonService, GenOtherChargesService, DatePipe]
  
})

export class GenOtherChargesDetailComponent {
  geoOtherCharges: GenOtherCharges;
  geoOtherCharges1:GenOtherCharges
  geoOtherCharges2:GenOtherCharges
  charges=[{'chargeCode':'C008','chargeDesc':'Audit Charges'},
  {'chargeCode':'C009','chargeDesc':'Meter Change Charges'},
  {'chargeCode':'C010','chargeDesc':'Administrative Charges'},
  {'chargeCode':'C011','chargeDesc':'Miscellaneous Charges'},
  {'chargeCode':'C012','chargeDesc':'Other Charges'}]
  geoOtherChargesList: Array<GenOtherCharges>;
  months = [];
  genOtherChargeList:Array<GenOtherCharges>;
  disableSave: boolean =false;
  genOtherSubChargeList:Array<GenOtherSubCharge>;
  genOtherSubCharge:GenOtherSubCharge;
  genOtherSubCharge1:GenOtherSubCharge;
  genOtherSubCharge2:GenOtherSubCharge;
  genOtherSubCharge3:GenOtherSubCharge;
  genOtherSubCharge4:GenOtherSubCharge;
  genOtherSubCharge5:GenOtherSubCharge;
  genOtherSubCharge6:GenOtherSubCharge;
  genOtherSubCharge7:GenOtherSubCharge;
  genOtherSubCharge8:GenOtherSubCharge;
  genOtherSubCharge9:GenOtherSubCharge;
  genOtherSubCharge10:GenOtherSubCharge;
  meter:string;
  disableControls: boolean = true;
  result:string;
  orgList=[];
  meterReading:any='0';
  om:any='0';
  transmission:any='0';
  system:any='0';
  rkVah:any='0';
  negative:any='0';
  scheduling:any='0';
  audit:any='0';
  administrative:any='0';
  meterChange:any='0';
  miscellaneous:any='0';
  companyServices=[];
  searchCompanyServiceId:string="";
  serviceNumber:string;
  filteredCompanyServiceList=[];
  sellerCompanyServices=[];
  otherCharges:number=0;
  auditCharges:any;
  adminsCharges:any;
  statementMonth: string = "";
  statementYear: string = "";
  disableEdc: boolean = false;


  addScreen: boolean = true;
  edcList: any;
 
  constructor( 
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private commonUtils: CommonUtils,
    // private datePipe: DatePipe,
    private service: GenOtherChargesService,





  ) {

  }    
  ngOnInit() {
    
    this.geoOtherCharges = new GenOtherCharges();
    this.genOtherSubChargeList= new Array<GenOtherSubCharge>();
    this.genOtherSubCharge = new GenOtherSubCharge();
    this.genOtherSubCharge1 = new GenOtherSubCharge();
    this.genOtherSubCharge2 = new GenOtherSubCharge();
    this.genOtherSubCharge3 = new GenOtherSubCharge();
    this.genOtherSubCharge4 = new GenOtherSubCharge();
    this.genOtherSubCharge5 = new GenOtherSubCharge();
    this.genOtherSubCharge6 = new GenOtherSubCharge();
    this.genOtherSubCharge7 = new GenOtherSubCharge();
    this.genOtherSubCharge8 = new GenOtherSubCharge();
    this.genOtherSubCharge9 = new GenOtherSubCharge();
    this.genOtherSubCharge10 = new GenOtherSubCharge();
    this.geoOtherCharges.genOtherSubCharges= new Array<GenOtherSubCharge>();
    this.months = this.commonService.fetchMonths();
    // this.genOtherChargeList= new Array<GenOtherCharges>();
    this.fetchOrgList();
    this.fetchEDCs();
    // this.geoOtherChargesList = new Array<GenOtherCharges>();
    this.statementMonth = this.commonUtils.getCurrentMonth();
    this.statementYear  = this.commonUtils.getCurrentYear();

    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        .subscribe((_params: Params) => {
          this.service.getGenOtherChargesById(_params['_id']).subscribe(_mr => {
             this.geoOtherCharges = _mr;
      
            this.geoOtherCharges.genOtherSubCharges.forEach(x=>{
              // console.log("x",x);
              if(x.chargeCode=="C001"){
                this.meterReading= x.totalCharge;
                this.genOtherSubCharge.id=x.id;
                this.genOtherSubCharge.chargeCode=x.chargeCode;
                this.genOtherSubCharge.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge.unitCharge=x.unitCharge;
                this.genOtherSubCharge.totalCharge=x.totalCharge;
                this.genOtherSubCharge.remarks=x.remarks;
                this.genOtherSubCharge.enabled=x.enabled;
                this.genOtherSubCharge.createdDt=x.createdDt;
                this.genOtherSubCharge.createdBy=x.createdBy;

              }
              if(x.chargeCode=="C002"){
                this.om=x.totalCharge;
                this.genOtherSubCharge1.id=x.id;
                this.genOtherSubCharge1.chargeCode=x.chargeCode;
                this.genOtherSubCharge1.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge1.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge1.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge1.unitCharge=x.unitCharge;
                this.genOtherSubCharge1.totalCharge=x.totalCharge;
                this.genOtherSubCharge1.remarks=x.remarks;
                this.genOtherSubCharge1.enabled=x.enabled;
                this.genOtherSubCharge1.createdDt=x.createdDt;
                this.genOtherSubCharge1.createdBy=x.createdBy;
              }
              if(x.chargeCode=="C003"){
                this.transmission=x.totalCharge;
                this.genOtherSubCharge2.id=x.id;
                this.genOtherSubCharge2.chargeCode=x.chargeCode;
                this.genOtherSubCharge2.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge2.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge2.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge2.unitCharge=x.unitCharge;
                this.genOtherSubCharge2.totalCharge=x.totalCharge;
                this.genOtherSubCharge2.remarks=x.remarks;
                this.genOtherSubCharge2.enabled=x.enabled;
                this.genOtherSubCharge2.createdDt=x.createdDt;
                this.genOtherSubCharge2.createdBy=x.createdBy;
              }
              if(x.chargeCode=="C004"){
                this.system=x.totalCharge;
                this.genOtherSubCharge3.id=x.id;
                this.genOtherSubCharge3.chargeCode=x.chargeCode;
                this.genOtherSubCharge3.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge3.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge3.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge3.unitCharge=x.unitCharge;
                this.genOtherSubCharge3.totalCharge=x.totalCharge;
                this.genOtherSubCharge3.remarks=x.remarks;
                this.genOtherSubCharge3.enabled=x.enabled;
                this.genOtherSubCharge3.createdDt=x.createdDt;
                this.genOtherSubCharge3.createdBy=x.createdBy;
              }
              if(x.chargeCode=="C005"){
                this.rkVah=x.totalCharge;
                this.genOtherSubCharge4.id=x.id;
                this.genOtherSubCharge4.chargeCode=x.chargeCode;
                this.genOtherSubCharge4.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge4.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge4.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge4.unitCharge=x.unitCharge;
                this.genOtherSubCharge4.totalCharge=x.totalCharge;
                this.genOtherSubCharge4.remarks=x.remarks;
                this.genOtherSubCharge4.enabled=x.enabled;
                this.genOtherSubCharge4.createdDt=x.createdDt;
                this.genOtherSubCharge4.createdBy=x.createdBy;
              }
              if(x.chargeCode=="C006"){
                this.negative=x.totalCharge;
                this.genOtherSubCharge5.id=x.id;
                this.genOtherSubCharge5.chargeCode=x.chargeCode;
                this.genOtherSubCharge5.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge5.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge5.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge5.unitCharge=x.unitCharge;
                this.genOtherSubCharge5.totalCharge=x.totalCharge;
                this.genOtherSubCharge5.remarks=x.remarks;
                this.genOtherSubCharge5.enabled=x.enabled;
                this.genOtherSubCharge5.createdDt=x.createdDt;
                this.genOtherSubCharge5.createdBy=x.createdBy;
              }
              if(x.chargeCode=="C007"){
                this.scheduling=x.totalCharge;
                this.genOtherSubCharge6.id=x.id;
                this.genOtherSubCharge6.chargeCode=x.chargeCode;
                this.genOtherSubCharge6.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge6.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge6.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge6.unitCharge=x.unitCharge;
                this.genOtherSubCharge6.totalCharge=x.totalCharge;
                this.genOtherSubCharge6.remarks=x.remarks;
                this.genOtherSubCharge6.enabled=x.enabled;
                this.genOtherSubCharge6.createdDt=x.createdDt;
                this.genOtherSubCharge6.createdBy=x.createdBy;
              }
              if(x.chargeCode=="C008-01"){
                this.audit=x.totalCharge;
                this.genOtherSubCharge7.id=x.id;
                this.genOtherSubCharge7.chargeCode=x.chargeCode;
                this.genOtherSubCharge7.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge7.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge7.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge7.unitCharge=x.unitCharge;
                this.genOtherSubCharge7.totalCharge=x.totalCharge;
                this.genOtherSubCharge7.remarks=x.remarks;
                this.genOtherSubCharge7.enabled=x.enabled;
                this.genOtherSubCharge7.createdDt=x.createdDt;
                this.genOtherSubCharge7.createdBy=x.createdBy;
              }
              if(x.chargeCode=="C008-02"){
                this.administrative=x.totalCharge;
                this.genOtherSubCharge8.id=x.id;
                this.genOtherSubCharge8.chargeCode=x.chargeCode;
                this.genOtherSubCharge8.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge8.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge8.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge8.unitCharge=x.unitCharge;
                this.genOtherSubCharge8.totalCharge=x.totalCharge;
                this.genOtherSubCharge8.remarks=x.remarks;
                this.genOtherSubCharge8.enabled=x.enabled;
                this.genOtherSubCharge8.createdDt=x.createdDt;
                this.genOtherSubCharge8.createdBy=x.createdBy;
              }
              if(x.chargeCode=="C008-03"){
                this.meterChange=x.totalCharge;
                this.genOtherSubCharge9.id=x.id;
                this.genOtherSubCharge9.chargeCode=x.chargeCode;
                this.genOtherSubCharge9.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge9.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge9.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge9.unitCharge=x.unitCharge;
                this.genOtherSubCharge9.totalCharge=x.totalCharge;
                this.genOtherSubCharge9.remarks=x.remarks;
                this.genOtherSubCharge9.enabled=x.enabled;
                this.genOtherSubCharge9.createdDt=x.createdDt;
                this.genOtherSubCharge9.createdBy=x.createdBy;
              }
              if(x.chargeCode=="C008-04"){
                this.miscellaneous=x.totalCharge;
                this.genOtherSubCharge10.id=x.id;
                this.genOtherSubCharge10.chargeCode=x.chargeCode;
                this.genOtherSubCharge10.chargeDesc=x.chargeDesc;
                this.genOtherSubCharge10.tGenOtherChargeId=x.tGenOtherChargeId;
                this.genOtherSubCharge10.chargeTypeCode=x.chargeTypeCode;
                this.genOtherSubCharge10.unitCharge=x.unitCharge;
                this.genOtherSubCharge10.totalCharge=x.totalCharge;
                this.genOtherSubCharge10.remarks=x.remarks;
                this.genOtherSubCharge10.enabled=x.enabled;
                this.genOtherSubCharge10.createdDt=x.createdDt;
                this.genOtherSubCharge10.createdBy=x.createdBy;
              }


        
             
            })
            console.log( this.geoOtherCharges);
            this.onEdcChange();
          });
        });
    }
  
   
    if(CommonUtils.getLoginUserTypeCode()=='EDC')
    {
      this.geoOtherCharges.sellerOrgId=CommonUtils.getLoginUserEDC();
      this.onEdcChange();
      this.disableEdc = true;
      //to be changed later --Not Permanent
     // this.geoOtherCharges.month='05';
     // this.geoOtherCharges.year='2019';

    }
 



  }

  selectCharge(){
    this.geoOtherCharges.chargeDesc = this.charges.filter((charge)=>charge.chargeCode==this.geoOtherCharges.chargeCode)[0].chargeDesc;
  }
  onEdcChange(){
    // console.log("In edc change");

    this.service.getSellers(this.geoOtherCharges.sellerOrgId).subscribe(
      _companyServices=>{
        this.companyServices = _companyServices;
        // console.log(this.companyServices);
      }
    )
  }
 
  fetchOrgList() {
    this.commonService.fetchWindEDCs().subscribe(
      result => {
        this.orgList = result;
      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }

  listGenOtherCharges() {
    this.router.navigateByUrl('/gen-other-charges/gen-other-charges-list');
  }
  onChange(val){
    // console.log(val)
    this.auditCharges=val;
    // console.log("audit"+this.auditCharges);
  
  }
  adminCharges(val){
    this.adminsCharges=val;
    // console.log("admin"+this.adminsCharges);
  }


  onServiceChange(){
    // console.log("Filtered List")
    // console.log(this.filteredCompanyServiceList)
    if(this.filteredCompanyServiceList.length == 1){
      this.geoOtherCharges.sellerCompanyName = this.filteredCompanyServiceList[0].companyName; 
      this.geoOtherCharges.sellerCompanyId = this.filteredCompanyServiceList[0].companyId; 
      this.geoOtherCharges.sellerCompanyServiceId= this.filteredCompanyServiceList[0].id;
   
      // this.service.getCompanyService(this.geoOtherCharges.sellerCompanyServiceId).subscribe(result=>{
      //   this.companyServices = result;
      //   console.log(this.companyServices)
      //   this.geoOtherCharges.bankingCompanyId= this.companyServices[0].companyId;
      //   this.geoOtherCharges.companyName= this.companyServices[0].companyName;
      //   this.geoOtherCharges.bankingServiceId= this.companyServices[0].bankingServiceId;
      // });
    }
     
  }

  filterSellerCompanyServiceList(val: string) {
  
    return val ? this.companyServices.filter((s) =>  s.number.includes(val)) : this.companyServices;
  }

  saveGenOtherCharges(status: string) {
    //save can be add or update
    console.log(this.geoOtherCharges);
    // this.disableSave =true;
  
    this.genOtherSubCharge.chargeCode='C001';
    this.genOtherSubCharge.chargeDesc='Meter Reading Charges';
    this.genOtherSubCharge.totalCharge=this.meterReading;
    this.genOtherSubCharge1.chargeCode='C002';
    this.genOtherSubCharge1.chargeDesc='O&M Charges';
    this.genOtherSubCharge1.totalCharge=this.om;
    this.genOtherSubCharge2.chargeCode='C003';
    this.genOtherSubCharge2.chargeDesc='Transmission Charges';
    this.genOtherSubCharge2.totalCharge=this.transmission;
    this.genOtherSubCharge3.chargeCode='C004';
    this.genOtherSubCharge3.chargeDesc='System Operation Charges';
    this.genOtherSubCharge3.totalCharge=this.system;
    this.genOtherSubCharge4.chargeCode='C005';
    this.genOtherSubCharge4.chargeDesc='RKvah Penalty';
    this.genOtherSubCharge4.totalCharge=this.rkVah;
    this.genOtherSubCharge5.chargeCode='C006';
    this.genOtherSubCharge5.chargeDesc='Negative Energy Charges';
    this.genOtherSubCharge5.totalCharge=this.negative;
    this.genOtherSubCharge6.chargeCode='C007';
    this.genOtherSubCharge6.chargeDesc='Scheduling Charges';
    this.genOtherSubCharge6.totalCharge=this.scheduling;
    this.genOtherSubCharge7.chargeCode='C008-01';
    this.genOtherSubCharge7.chargeDesc='Audit Charges';
    this.genOtherSubCharge7.totalCharge=this.audit;
    this.genOtherSubCharge8.chargeCode='C008-02';
    this.genOtherSubCharge8.chargeDesc='Administrative Charges';
    this.genOtherSubCharge8.totalCharge=this.administrative;
    this.genOtherSubCharge9.chargeCode='C008-03';
    this.genOtherSubCharge9.chargeDesc='Meter change Charges';
    this.genOtherSubCharge9.totalCharge=this.meterChange;
    this.genOtherSubCharge10.chargeCode='C008-04';
    this.genOtherSubCharge10.chargeDesc='Miscellaneous Charges Charges';
    this.genOtherSubCharge10.totalCharge=this.miscellaneous;
    this.geoOtherCharges.chargeCode='C008';
    this.geoOtherCharges.chargeDesc='Other Charges';
    this.geoOtherCharges.month=this.statementMonth;
    this.geoOtherCharges.year=this.statementYear;

    // this.genOtherSubCharge1.
  //  this.genOtherSubChargeList.push(this.genOtherSubCharge)
 
  
  // console.log(this.addScreen)
    if (this.addScreen) {
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge);
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge1);
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge2);
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge3);
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge4);
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge5);
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge6);
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge7);
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge8);
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge9);
      this.geoOtherCharges.genOtherSubCharges.push(this.genOtherSubCharge10);
      this.addGenOtherCharges();
    }
    else {
      this.geoOtherCharges.genOtherSubCharges.forEach(x=>{
          if(x.chargeCode=='C001'){
            x.totalCharge=this.meterReading;
          }
          if(x.chargeCode=='C002'){
            x.totalCharge=this.om;
          }
          if(x.chargeCode=='C003'){
            x.totalCharge=this.transmission;
          }
          if(x.chargeCode=='C004'){
            x.totalCharge=this.system;
          }
          if(x.chargeCode=='C005'){
            x.totalCharge=this.rkVah;
          }
          if(x.chargeCode=='C006'){
            x.totalCharge=this.negative;
          }
          if(x.chargeCode=='C007'){
            x.totalCharge=this.scheduling;
          }
          
          if(x.chargeCode=='C008-01'){
            x.totalCharge=this.audit;
          }
          if(x.chargeCode=='C008-02'){
            x.totalCharge=this.administrative;
          }
          if(x.chargeCode=='C008-03'){
            x.totalCharge=this.meterChange;
          }
          if(x.chargeCode=='C008-04'){
            x.totalCharge=this.miscellaneous;
          }


      })
      this.updateGenOtherCharges();
    }

  }

 

  addGenOtherCharges() {
    // console.log("add");
    // console.log(this.geoOtherCharges);
    this.service.addGenOtherCharges(this.geoOtherCharges).subscribe(
      result => {
     
        this.listGenOtherCharges();
      },
      error => {
        console.error('Error adding geoOtherCharges Application!');
        console.error(error);
      }
    );  
  }
 
  updateGenOtherCharges() {
    // console.log("update");
    // console.log(this.geoOtherCharges);
    this.service.updateGenOtherCharges(this.geoOtherCharges).subscribe(
      result => {
        this.listGenOtherCharges();
      },
      error => {
        console.error('Error updating geoOtherCharges!');
        console.error(error);
      }
    );
  }

  fetchEDCs() {
    this.commonService.fetchEDCs().subscribe(
        edc=>{
          // console.log("In page load edc")
          this.edcList = edc;
          // console.log(this.edcList)
        });
}

negativeValue(e){
  if(e < 0)
  {
    this.disableSave=true;
  }
  else{
    this.disableSave=false;
  }
}

}