import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
//import { SessionStorage} from "angular2-localstorage/WebStorage";

//import { ConsentEvent } from './../../services/consent.event';
import { PowerplantService } from './../../services/powerplant.service';
import { CompanyService } from './../../../../master/company/services/company.service';
import { Powerplant, Generator } from './../../../vo/powerplant';
import { CommonService } from '../../../../shared/common/common.service';
import {Service,Meter} from '../../../vo/company.v1';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { DatePipe } from '@angular/common';
import { CommonUtils } from '../../../../shared/common/common-utils';
import { Inject } from '@angular/core';
import {EsService} from './../../../../transaction/energy-sale/services/es.service';
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS } from "@angular/material";
import { TradeRelationship } from '../../../vo/trade-relationship';
import { TradeRelationshipService } from '../../../trade-relationship/services/trade-relationship.service';
import { Es } from '../../../../transaction/vo/es';

export class AppDateAdapter extends NativeDateAdapter {
  parse(value: any): Date | null {
    if ((typeof value === 'string') && (value.indexOf('/') > -1)) {
      const str = value.split('/');
      const year = Number(str[2]);
      const month = Number(str[1]) - 1;
      const date = Number(str[0]);
      return new Date(year, month, date);
    }
    const timestamp = typeof value === 'number' ? value : Date.parse(value);
    return isNaN(timestamp) ? null : new Date(timestamp);
  }

  format(date: Date, displayFormat: Object): string {
    if (displayFormat == "input") {
      let day = date.getDate();
      let month = date.getMonth() + 1;
      let year = date.getFullYear();
      return this._to2digit(day) + '/' + this._to2digit(month) + '/' + year;
    } else {
      return date.toDateString();
    }
  }

  private _to2digit(n: number) {
    return ('00' + n).slice(-2);
  }
}

export const APP_DATE_FORMATS =
  {
    parse: {
      dateInput: { month: 'short', year: 'numeric', day: 'numeric' }
    },
    display: {
      // dateInput: { month: 'short', year: 'numeric', day: 'numeric' },
      dateInput: 'input',
      monthYearLabel: { month: 'short', year: 'numeric', day: 'numeric' },
      dateA11yLabel: { year: 'numeric', month: 'long', day: 'numeric' },
      monthYearA11yLabel: { year: 'numeric', month: 'long' },
    }
  }
@Component({
  selector: 'powerplant-detail',
  templateUrl: './powerplant-detail.component.html',
  providers: [PowerplantService , TradeRelationshipService,CompanyService,CommonService,EsService,MatDatepickerModule, MatNativeDateModule, DatePipe, CommonUtils,
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})


export class PowerplantDetailComponent{

  powerplant: Powerplant;  
  tradeRelationships:Array<TradeRelationship>; 
  tradeRelationship:TradeRelationship; 
  private fieldArray: Array<any> = [];
  private newAttribute: any = {};

  es:Es;
  generator:Generator;
  compServices:Service;
  compMeters:Meter;
  meterRowIndex: number;

  disableTrue: boolean = true;
  disableMasterChange: boolean = false;
  disableEdc: boolean = false;
  disableNces: boolean = false;
  disableNcesEdc: boolean = false;



  tradeRowIndex:number;
  generatorRowIndex: number;
  addScreen: boolean = true;
  disableControls: boolean = true;
  isPrimaries=[
    {"key":"Y","name":"Y"},
    {"key":"N","name":"N"}
  ]
  isEnabled=[
    {"key":"Y","name":"Y"},
    {"key":"N","name":"N"}
  ]
  isGeneratorEnabled=[
    {"key":"Y","name":"Y"},
    {"key":"N","name":"N"}
  ]

  isRec=[
    {"key":"Y","name":"Y"},
    {"key":"N","name":"N"}
  ]

  adBenefits=[
    {"key":"Y","name":"Yes"},
    {"key":"N","name":"N0"}
  ]

  
  orgId=[];
  substationId=[];   
  voltages =[];
  states=[];
  districts=[];
  taluks=[];
  windPassTypes=[]; 
  generatorModelTypes=[];
  fuelTypes=[];
  interfaceVoltageTypes=[];
  plantTypes=[];
  filteredEDCs=[];
  filteredSubstations=[];
  feederList = [];
  filterFeeder = [];

  metermakes = [];
  accuracyclasscodes = [];

  abtMeterValues = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]

  category = [
    { "key": "CAPTIVE", "name": "Captive" },
    { "key": "STB", "name": "Sale to baord" },
    { "key": "WEG-THIRD-PARTY", "name": "Weg third party" }
  ]
  maxDate: Date = new Date();


  constructor(   
    private route: ActivatedRoute,
    private router: Router,
    private service: PowerplantService ,
    private tradeService:TradeRelationshipService,
    private companyService: CompanyService,
    private commonService :CommonService,
    private esService:EsService,
    private datePipe: DatePipe

  ) {
   this.initGenerators();
   this.fetchDistrict();
   this.fetchFuelTypes();
   this.fetchGeneratorModels();
   this.fetchPlantTypes();
   this.fetchInterfaceVoltages();
   this.fetchState();
   this.fetchTaluk();
   this.fetchVoltages();
   this.fetchWindPass();
   this.fetchSubstations();
   this.fetchEDCs();
    
   }

  ngOnInit() { 
    this.powerplant = new Powerplant();
    this.generator=new Generator();
    this.compServices=new Service();
    this.compMeters = new Meter();
    this.es=new Es(); 
      // console.log(this.route.params['_value']);
    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        .switchMap((params: Params) => this.service.getPowerplantById(this.route.params['_value']['_id']))
        .subscribe((_powerplant: Powerplant) => {
          // console.log(_powerplant);
          this.powerplant = _powerplant[0];
          this.compServices= _powerplant[0].services;
          this.tradeRelationships=this.powerplant.tradeRelationships;
          // console.log(this.compServices);
          // this.formatChangesforUI();
          this.fetchFeeders(this.powerplant.subStationId);
          if(this.tradeRelationships.length==0){
            this.addTradeRel();
          }

          if(this.powerplant.company.isCaptive!=null || this.powerplant.company.isCaptive!=undefined ||this.powerplant.company.isCaptive!=""){
            if(this.powerplant.company.isCaptive=='Y'){
              this.esService.getAllEss("",this.compServices.number,"","","","","","","","","").subscribe(
                result => {
                this.es=result[0];
                console.log(this.es)
                if(this.es!=null || this.es!=undefined){
                if(this.es.statusCode!=null || this.es.statusCode!=undefined){
                  if(this.es.statusCode=="APPROVED"){
                    this.disableMasterChange=true;
                    alert("Updation cannot be done. Allotment over");
                  }
                }
              }
                },    
                error => {
                console.error('Error getting energy sale!');
                console.error(error);
                 });
            }
            }
          
        });
        

        if(CommonUtils.getLoginUserTypeCode()=='EDC')
        {
          this.disableEdc=true;
          this.disableNcesEdc=true;
    
        }
        if(CommonUtils.getLoginUserTypeCode()=='NCES')
        {
          this.disableNces=true;
          this.disableNcesEdc=true;

        }
    }
    this.fetchMeterMake();
    this.fetchAccuracyClassCode();

  }

  filterEDCs(val: string) {
    
  return val ? this.orgId.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.orgId;
}

addFieldValue() {
  this.fieldArray.push(this.newAttribute)
  this.newAttribute = {};
}

deleteFieldValue(index) {
  this.fieldArray.splice(index, 1);
}

addTradeRel(){
  let t = new TradeRelationship();
  t.sellerCompServiceNumber= this.compServices.number;
  console.log(t)
  console.log(this.tradeRelationships)
  this.tradeRelationships.push(t);

}
deleteTradeRel(index){

  this.tradeRelationships.splice(index, 1)

}
fetchTradeRelationships(){
}
 
  fetchEDCs(){
    this.commonService.fetchEDCs().subscribe(
    result => { this.orgId = result;
     },    
    error => {
    console.error('Error loading orgs!');
     console.error(error);
     }  );
    }
    // formatChangesforUI() {
    //   this.powerplant.commissionDate = (this.powerplant.commissionDate) ? this.powerplant.commissionDate.substr(0, 10) : "";
    //   console.log(this.powerplant.commissionDate)
    // }

    formatChangesforDB() {
      this.powerplant.commissionDate = this.datePipe.transform(this.powerplant.commissionDate, 'yyyy-MM-dd');
      // this.tradeRelationship.fromDate=this.datePipe.transform(this.tradeRelationship.fromDate, 'yyyy-MM-dd');
      // this.tradeRelationship.toDate=this.datePipe.transform(this.tradeRelationship.toDate, 'yyyy-MM-dd');
      // console.log(this.tradeRelationship.fromDate)
      // console.log(this.tradeRelationship.toDate)



    }

    changeFeederName(feeder:any){
      // console.log("feeder");
      // console.log(feeder)
      this.powerplant.feederId=feeder.id;
      this.powerplant.feederName=feeder.name;
    }

    changeSsName(substation:any){
      // console.log("substation");
      // console.log(substation)
      this.powerplant.subStationId=substation.id;
      this.powerplant.subStationName=substation.name;
    }

   filterSubstations(val: string) {
          // console.log(this.substationId)
     return val ? this.substationId.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.substationId;

      }

   fetchSubstations(){
     this.commonService.fetchSubstations().subscribe(
     result => { this.substationId = result;
      // console.log(this.substationId)
     },    
     error => {
     console.error('Error loading substaion!');
     console.error(error);
      }  );
     }

     onSsChange(){
      if(this.filteredSubstations.length == 1){
        this.fetchFeeders(this.filteredSubstations[0].id);
      }
     }

     filterFeeders(val: string) {

      return val ? this.feederList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.feederList;
    }
  
    fetchFeeders(substationId: string) {
      if (substationId == "") {
        return false;
      }
      this.commonService.fetchFeeders(substationId).subscribe(
        result => {
          this.feederList = result;
        },
        error => {
          console.error('Error loading feeders!');
          console.error(error);
        });
    }




    
    
  fetchPlantTypes(){
    this.commonService.fetchCodes('PLANT_TYPE_CODE').subscribe(
    result => { this.plantTypes = result;
     },
    
    error => {
    console.error('Error loading powerplant type!');
     console.error(error);
     });
     }

      

    fetchFuelTypes(){
    this.commonService.fetchCodes('FUEL_TYPE_CODE').subscribe(
    result => { this.fuelTypes = result;
     },
    
    error => {
    console.error('Error loading fueltypes');
     console.error(error);
     });
    }
    

    fetchInterfaceVoltages(){
    this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
    result => { this.interfaceVoltageTypes = result;
     },
    
    error => {
    console.error('Error loading Interface voltage');
     console.error(error);
     });
    }

    fetchGeneratorModels(){
    this.commonService.fetchCodes('GENERATOR_MAKE_CODE').subscribe(
    result => { this.generatorModelTypes = result;
     },
    
    error => {
    console.error('Error loading generatorModel');
     console.error(error);
     });
    }
    
    fetchWindPass(){
    this.commonService.fetchCodes('WIND_PASS_CODE').subscribe(
    result => { this.windPassTypes = result;
     },
    
    error => {
    console.error('Error loading WINDPASS');
     console.error(error);
     });
    }

   

      
     fetchTaluk(){
     this.commonService.fetchCodes('TALUK_CODE').subscribe(
     result => { this.taluks = result;
      },
    
      error => {
      console.error('Error loading taluks');
       console.error(error);
      });
     }
    

     fetchDistrict(){
     this.commonService.fetchCodes('DISTRICT_CODE').subscribe(
     result => { this.districts = result;
      },
    
     error => {
      console.error('Error loading Districts');
       console.error(error);
      });
    }
    

     fetchState(){
     this.commonService.fetchCodes('STATE_CODE').subscribe(
     result => { this.states= result;
      },
    
     error => {
      console.error('Error loading State Codes');
       console.error(error);
      });
    }
    
     fetchVoltages(){
     this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
     result => { this.voltages= result;
      },
    
     error => {
      console.error('Error loading Voltages');
       console.error(error);
      });
      }
    
     
  

  savePowerplant() {    
    console.log('In save'+ this.addScreen );
    if (this.addScreen) {
      this.addPowerplant();
    }
    else {
      this.updatePowerplant();
    }
  }

  addPowerplant() {
    this.service.addPowerplant(this.powerplant).subscribe(
      result => {
       // this.powerplant.id = result;
        this.listPowerplant();
      },
      error => {
        console.error('Error adding Powerplant!');
        console.error(error);
      }
    );
  }

  updatePowerplant() {
    this.formatChangesforDB();
    // console.log(this.powerplant)
    this.service.updatePowerplant(this.powerplant).subscribe(
      result => {
        this.listPowerplant();
      },
      error => {
        console.error('Error updating Powerplant!');
        console.error(error);
      }
    );
  }
  listPowerplant() {
    this.router.navigateByUrl('/powerplant/powerplant-list');
  }

//-------------------------------------------------------------------

fetchAccuracyClassCode() {
  this.commonService.fetchCodes('ACCURACY_CLASS_TYPE_CODE').subscribe(
    result => {
      this.accuracyclasscodes = result;
      // console.log(this.accuracyclasscodes)
    },

    error => {
      console.error('Error loading Accuracy class code');
      console.error(error);
    });
}


fetchMeterMake() {
  this.commonService.fetchCodes('METER_MAKE_CODE').subscribe(
    result => {
      this.metermakes = result;
    },

    error => {
      console.error('Error loading Meter Make!');
      console.error(error);
    });
}

initGenerators(){
    // if(!this.generator.genUnits){
    //     this.generator.genUnits=[];
    //   }
   this.generator = new Generator();
   this.generatorRowIndex = -1;
  }


  addGenerator(){
    if(!this.powerplant.generators){
        this.powerplant.generators=[];
      }
    this.powerplant.generators.push(Object.assign({}, this.generator));   
    this.initGenerators();
  } 

  updateGenerator(){
    
    //this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
    // the above code wasnt updating a row in the table due to somebug
    // as a workaround, creating a new array with same rows except the edited row.
    var tempArray =[];
    for (var index = 0; index < this.powerplant.generators.length; index++) {
      if(this.generatorRowIndex == index){
        tempArray.push(Object.assign({},this.generator));
      }
      else{
        tempArray.push(this.powerplant.generators[index]);
      }
      
    }
    this.powerplant.generators = tempArray;
    this.initGenerators();
  }


  editGenerator(_index: number){
   this.generatorRowIndex =_index; 
   this.generator = Object.assign({},this.powerplant.generators[_index]);
  }

  deleteGenerator(_index: number){ 
   this.powerplant.generators.splice(_index,1);
  }



  //-------------------------------------------------------------------

  initMeters() {
    this.compMeters = new Meter();
    this.meterRowIndex = -1;
  }

  addMeter() {
    if (!this.compServices.meters) {
      this.compServices.meters = [];
    }
    this.compServices.meters.push(Object.assign({}, this.compMeters));
    this.initMeters();
  }

  updateMeter() {
    var tempArray = [];
    for (var index = 0; index < this.compServices.meters.length; index++) {
      if (this.meterRowIndex == index) {
        tempArray.push(Object.assign({}, this.compMeters));
      }
      else {
        tempArray.push(this.compServices.meters[index]);
      }

    }
    this.compServices.meters = tempArray;
    this.initMeters();
  }


  editMeter(_index: number) {
    this.meterRowIndex = _index;
    this.compMeters = Object.assign({}, this.compServices.meters[_index]);
  }

  deleteMeter(_index: number) {
    this.compServices.meters.splice(_index, 1);
  }


    //-------------------------------------------------------------------

    // initTradeRelationship(){
    //  this.tradeRelationship = new TradeRelationship();
    //  console.log(this.tradeRelationship);
    //  console.log(this.tradeRowIndex);

    //  this.tradeRowIndex = -1;
    // }

    // addTrade(){
    //   this.tradeRelationships.push(Object.assign({}, this.tradeRelationship));
    //   console.log(this.tradeRelationships);
    //   console.log(this.tradeRelationship);

   
    //   this.initTradeRelationship();
    // } 
    // updateTrade() {
    //   var tempArray = [];
    //   for (var index = 0; index < this.tradeRelationships.length; index++) {

    //     console.log(this.tradeRelationships);

    //     if (this.tradeRowIndex == index) {
    //       tempArray.push(Object.assign({}, this.tradeRelationship));
    //       console.log(this.tradeRelationship);
    //     }
    //     else {
    //       tempArray.push(this.tradeRelationship[index]);
    //       console.log(this.tradeRelationship);

    //     }
  
    //   }
    //   this.tradeRelationships = tempArray;
    //   this.initTradeRelationship();
    // }
  
  
    // editTrade(_index: number) {
    //   this.tradeRowIndex = _index;
    //   this.tradeRelationship = Object.assign({}, this.tradeRelationship[_index]);
    // }
  
    // deleteTrade(_index: number) {
    //   this.tradeRelationships.splice(_index, 1);
    // }
}