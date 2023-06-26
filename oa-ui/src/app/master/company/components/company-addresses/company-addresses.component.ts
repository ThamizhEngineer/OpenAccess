import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CompanyServiceV1 } from './../../services/company.service.v1'; 
import { Company , Address } from './../../../vo/company.v1'; 
import { CommonService } from '../../../../shared/common/common.service';

@Component({
  selector: 'company-addresses',
  templateUrl: './company-addresses.component.html',
 // styleUrls: [],
  providers: []
  
})
export class CompanyAddressesComponent implements OnInit{ 

  company: Company = new Company();
 
  address: Address = new Address();
  addressRowIndex: number;


  states = [];
  filteredStates = [];
  addressTypes =[
    {"key":"AT01","name":"Registered Office"},
    {"key":"AT02","name":"Head Office"},
    {"key":"AT03","name":"Site Office"}
  ];
  isDisabled = false;
  isMandatory= false;
  
  constructor(private commonService: CommonService,private companyService: CompanyServiceV1) { 
   this.fetchStates();
   this.companyService.companyEvent.subscribe(latestValue =>{ 
     this.company =  latestValue;
     this.initAddresses();
    });
   
    
  }

  ngOnInit() {
  }


  displayFn(value: any): string {
    return value && typeof value === 'object' ? value.name : value;
  }

  filterStates(val: string) {
    return val ? this.states.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.states;
  }

  onChange(event: string) {
    console.log(event);
}


initAddresses(){
    if(!this.company.addresses){
        this.company.addresses=[];
      }
   this.address = Object.assign({},{ addressNo :  "", type : "", line1 : "", city : "", district : "",
            state : "", pinCode: "",enabled: true });
   this.addressRowIndex = -1;
  }

  fetchStates(){
    this.commonService.fetchStates().subscribe(
      result => { this.states = result;
      },
      error => {
         console.error('Error loading states!');
         console.error(error);
      }
      );
  }

  addAddress(){
    if(!this.company.addresses){
      this.company.addresses=[];
    }
    this.company.addresses.push(Object.assign({}, this.address));
    this.companyService.setCompany(this.company);
    this.initAddresses();
  }
  updateAddress(){
    
    //this.company.addresses[this.addressRowIndex-1] = Object.assign({},this.address);
    // the above code wasnt updating a row in the table due to somebug
    // as a workaround, creating a new array with same rows except the edited row.
    var tempArray =[];
    for (var index = 0; index < this.company.addresses.length; index++) {
      if(this.addressRowIndex == index){
        tempArray.push(Object.assign({},this.address));
      }
      else{
        tempArray.push(this.company.addresses[index]);
      }
      
    }
    this.company.addresses = tempArray;
    this.companyService.setCompany(this.company);
    this.initAddresses();
  }


  editAddress(_index: number){
   this.addressRowIndex =_index; 
   this.address = Object.assign({},this.company.addresses[_index]);
  }

  deleteAddress(_index: number){ 
   this.company.addresses.splice(_index,1);
  }

}
