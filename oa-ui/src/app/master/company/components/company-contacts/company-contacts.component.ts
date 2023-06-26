import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CompanyServiceV1} from './../../services/company.service.v1'; 
import { Company , Contact } from './../../../vo/company.v1'; 

@Component({
  selector: 'company-contacts',
  templateUrl: './company-contacts.component.html',
  //styleUrls: [],
  providers: []
  
})
export class CompanyContactsComponent implements OnInit{ 

  company: Company = new Company();
 
  contact: Contact = new Contact();
  contactRowIndex: number;

  
  constructor(private companyService: CompanyServiceV1) { 
   this.companyService.companyEvent.subscribe(latestValue =>{ 
     this.company =  latestValue;
     this.initContacts();
    });
   
  }

  ngOnInit() {
  }


initContacts(){
    if(!this.company.contacts){
        this.company.contacts=[];
      }
   this.contact = Object.assign({},{  contactNo : "",purpose : "",name : "",designation : "", landline : "",
            mobile : "", email: "",enabled: true });
   this.contactRowIndex = -1;
  }

  addContact(){
    if(!this.company.contacts){
      this.company.contacts=[];
    }
    this.company.contacts.push(Object.assign({}, this.contact));
    this.companyService.setCompany(this.company);
    this.initContacts();
  }

  updateContact(){
    //this.company.contacts[this.contactRowIndex] = Object.assign({},this.contact);
    var tempArray =[];
    for (var index = 0; index < this.company.contacts.length; index++) {
      if(this.contactRowIndex == index){
        tempArray.push(Object.assign({},this.contact));
      }
      else{
        tempArray.push(this.company.contacts[index]);
      }
      
    }
    this.company.contacts = tempArray;
    this.companyService.setCompany(this.company);
    this.initContacts();
  }


  editContact(_index: number){
   this.contactRowIndex =_index; 
   this.contact = Object.assign({},this.company.contacts[_index]);
  }
  
  deleteContact(_index: number){ 
   this.company.contacts.splice(_index,1);
  }
}
