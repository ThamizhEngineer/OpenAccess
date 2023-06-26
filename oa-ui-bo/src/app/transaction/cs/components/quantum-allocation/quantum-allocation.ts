import {Component, Input,OnInit} from '@angular/core'
import { Cs,CsQuantumAllocation} from './../../../vo/cs'; 

import { CsService } from './../../services/cs.service';
import { SignupService } from './../../../../master/signup/services/signup.service';
import { CommonService } from './../../../../shared/common/common.service';

@Component({
  selector: 'quantum-allocation',
  templateUrl: './quantum-allocation.html',
  providers: [CommonService, CsService,SignupService]
})

export class QuantumAllocation implements OnInit  {
  @Input() cs: Cs;
  @Input() stepDisable: boolean;

  quantumAllocation:CsQuantumAllocation;
  quantumAllocationRowIndex: number;
  
    voltages: string;

    buyerCompanyCode: String;
    buyerCompanyName: String;

    disableControls: boolean=false;
  
    orgList = [];
    buyerCompanyServices=[];
    filteredServices = [];
  constructor( 
    private commonService: CommonService,
    private service: CsService,
    private signService: SignupService) { 
      this.fetchEDCs();   
      this.fetchVoltages();
      this.service.csEvent.subscribe(_cs =>{ 
        this.cs =  _cs;
       });

  }
  ngOnInit() {
   
    this.quantumAllocation =new CsQuantumAllocation();
  //   this.csEvent.cs$.subscribe(latestValue =>{ 
  //    this.cs =  latestValue;
  //   });
  //  this.csEvent.load();
  this.service.csEvent.subscribe(_cs =>{ 
    this.cs =  _cs;
   });
   this.initCaptiveQuantumAllocation();
}


onInjectionVoltageSelect(voltage) {
  
     this.quantumAllocation.injectionVoltageCode = voltage.valueCode;
      this.quantumAllocation.injectionVoltageName = voltage.valueDesc;
    }
    onDrawalVoltageSelect(voltage) {
      
       this.quantumAllocation.drawalVoltageCode = voltage.valueCode;
      this.quantumAllocation.drawalVoltageName = voltage.valueDesc;
    }

    fetchVoltages() {
      this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
        result => {
  
          this.voltages = result;
        },
  
        error => {
          console.error('Error loading Voltages');
          console.error(error);
     
      })
    }
  
  
  fetchEDCs() {
    this.orgList =[] ;
      this.commonService.fetchEDCs().subscribe(
        result => {
          
          this.orgList = result ;
          
        },
        error => {
          console.error('Error loading orgs!');
          console.error(error);
        });
  }
    
  
     displayFn(value: any): string {
      return value && typeof value === 'object' ? value.name : value;
    }
    filterBuyerCompanyServices(val: string) {
      return val ? this.buyerCompanyServices.filter((s) => s.companyName.match(new RegExp(val, 'gi'))) : this.buyerCompanyServices;
    }
  
    fetchBuyerCompanyServices(orgId: string) {
      console.log(orgId)
  
      this.signService.getBuyerCompanyServicesByOrgId(orgId).subscribe(
        result => {
          this.buyerCompanyServices = result;
          console.log(this.buyerCompanyServices);
         
        },
        error => {
          console.error('Error loading company!');
          console.error(error);
        });
    }
     
     onConsumerChange() {
      console.log("On consumer change")
      this.filteredServices = this.buyerCompanyServices
        .filter((item) => item.id == this.quantumAllocation.buyerCompServiceId);
      this.quantumAllocation.buyerCompServiceNumber = this.filteredServices[0].number; 
      this.quantumAllocation.buyerCompServiceName = this.filteredServices[0].companyName;
      this.quantumAllocation.buyerCompServiceId = this.filteredServices[0].id;
    }
    
    initCaptiveQuantumAllocation() {
      if (!this.cs.csQuantumAllocations) {
        this.cs.csQuantumAllocations = [];
      }
    //  console.log("In init cap start")
      this.quantumAllocation = new CsQuantumAllocation();
      this.quantumAllocationRowIndex = -1;
    }
  
    addCaptiveQuantumAllocation() {
      if (!this.cs.csQuantumAllocations) {
        this.cs.csQuantumAllocations= [];
      }
    //  console.log("In add cap start")
  
      this.cs.csQuantumAllocations.push(Object.assign({}, this.quantumAllocation));
      this.service.setCs(this.cs);
      console.log(this.cs);
      this.initCaptiveQuantumAllocation();
    }
  
    updateCaptiveQuantumAllocation() {
  
      // this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
      // the above code wasnt updating a row in the table due to somebug
      // as a workaround, creating a new array with same rows except the edited row.
      var tempArray = [];
      for (var index = 0; index < this.cs.csQuantumAllocations.length; index++) {
        if (this.quantumAllocationRowIndex == index) {
          tempArray.push(Object.assign({}, this.quantumAllocation));
        }
        else {
          tempArray.push(this.cs.csQuantumAllocations[index]);
        }
  
      }
  
      this.cs.csQuantumAllocations = tempArray;
    //  this.calculateOwnerShipShare();
    this.service.setCs(this.cs);
  
      this.initCaptiveQuantumAllocation();
    }
  
  
    editCaptiveQuantumAllocation(_index: number) {
      this.quantumAllocationRowIndex = _index;
      this.quantumAllocation= Object.assign({}, this.cs.csQuantumAllocations[_index]);
    }
  
    deleteCaptiveQuantumAllocation(_index: number) {
      this.cs.csQuantumAllocations.splice(_index, 1);
    //  this.calculateOwnerShipShare();
    }
  
  
}