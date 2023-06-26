import { filter } from 'rxjs/operator/filter';
import { Importsellers } from './../../../vo/importsellers';
import { Component, SystemJsNgModuleLoader } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { CommonService } from '../../../../shared/common/common.service';
import { DatePipe } from '@angular/common';
import { importsellerService } from '../../services/importseller.service';
import { DateAdapter } from '@angular/material';

@Component({
  selector: 'importseller',
  templateUrl: './importseller.component.html',
  styleUrls: ['./importseller.component.scss'],
  providers: [importsellerService, DatePipe]
})

export class ImportsellerComponent {
  importsellers:Importsellers;
  data:any;
  serviceNo:String;
  substationId=[];   
  fuelTypes = [
    {"key": "COAL", "name": "Coal"},
    {"key": "WIND", "name": "Wind"},
    {"key": "NATURAL GAS", "name": "Natural Gas"},
    {"key": "DIESEL", "name": "Diesel"},
    {"key": "SOLAR", "name": "Solar"},
    {"key": "WASTE GAS", "name": "Waste Gas"},
    {"key": "BIO MASS", "name": "Bio Mass"}
  ]
  accuracy = [
    {"key": "0.2S", "name": "0.2 Class"},
    {"key": "0.5S", "name": "0.5 Class"}
  ]
  decisions = [
    { "key": "Y", "name": "YES" },
    { "key": "N", "name": "NO" }
  ]
  flowTypes = [
    { "key": "STB", "name": "SALE-TO-BOARD" },
    { "key": "IS-CAPTIVE", "name": "CAPTIVE" },
    { "key": "THIRD-PARTY", "name": "THIRD-PARTY" }
  ]
  meterMake:any;
  voltage = [
    {"key": "11KV", "name": "11KV"},
    {"key": "22KV", "name": "22KV"},
    {"key": "33KV", "name": "33KV"},
    {"key": "110KV", "name": "110KV"},
    {"key": "230KV", "name": "230KV"}
  ]
  edcList: any;
  edc:any;
  edcSub:any;


  constructor(
    private importService: importsellerService, private datepipe: DatePipe, private router: Router, private commonService:CommonService
    ,private dateAdapter: DateAdapter<Date>) {
      this.dateAdapter.setLocale('en-GB');
  }

  ngOnInit() {
    this.importsellers=new Importsellers();
    this.fetchMeterMake();
    this.fetchOrgList();
    this.fetchSubstations();
  }

save(){
 
    if(this.importsellers.mOrgId&&this.importsellers.generatorServiceNoNew&&this.importsellers.isRec!=null&&this.importsellers.flowTypeCode&&this.importsellers.meterNumber&&this.importsellers.commisionDate
         &&this.importsellers.injectionVoltage!=null&&this.importsellers.noOfGenUnits!=null&&this.importsellers.genUnitCapacity!=null&&this.importsellers.mf!=null&&this.importsellers.fuelCode){
          
          
          this.importService.fetchServNo(this.importsellers.generatorServiceNoNew).subscribe(x=> {
            this.serviceNo=x;
          console.log(this.serviceNo); 

            if(this.serviceNo =="New service no"){
              var number=this.importsellers.generatorServiceNoNew;
              var splitOrgId=number.substring(5,8);
              if(this.importsellers.mOrgId==splitOrgId){
          this.importService.saveSeller(this.importsellers).subscribe(x=>{
           console.log(this.edc);
    
    alert("Data Saved Successfully");
    setTimeout(() => {
     this.ngOnInit();
    }, 1000);
   })
  }
  else{
    alert("EDC "+ this.importsellers.mOrgId+" does not match to Service No " +number)
  }
  }
  else{
    alert("Service Number Already Exists");
    setTimeout(() => {
      this.ngOnInit();
     }, 1000);
  } })

} 
    else{
    alert("Mandatory Fields Required")
  }

}


fetchOrgList() {
  this.commonService.fetchEDCs().subscribe(
    result => {
      this.edcList = result;
    },
    error => {
      console.error('Error loading states!');
    }
  );
}

fetchMeterMake(){
  this.commonService.fetchCodes("METER_MAKE_CODE").subscribe(x=>{
  this.meterMake=x;
})
  
}

fetchSubstations(){
  this.commonService.fetchSubstations().subscribe(
  result => { this.substationId = result;
   
  },    
  error => {
  console.error('Error loading substaion!');
  console.error(error);
   }  );
  }

filterSubstation(){
 const subStaion=this.substationId.filter(item => item.orgId==this.importsellers.mOrgId)
 this.data=subStaion;
 console.log(this.data);
  }
  numberFormat(event){
    this.commonService.numberOnly(event);
  }

}
