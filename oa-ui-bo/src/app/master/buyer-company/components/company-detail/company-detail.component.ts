import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CompanyServiceV1 } from './../../services/company.service.v1'; 
import { Company , Address, Contact, Employee, ShareHolder, Meter } from './../../../vo/company.v1'; 

@Component({
  selector: 'app-company-detail',
  templateUrl: './company-detail.component.html',
  //styleUrls: [],
  providers: []
})
export class CompanyDetailComponent implements OnInit{ 

  company: Company = new Company();

  rowIndex: number;
  addScreen: boolean = true;

  companySetups =[
    {"key":"01","name":"Sole Proprietary"},
    {"key":"02","name":"Co-Op"},
    {"key":"03","name":"Association Of Person"},
    {"key":"04","name":"Private Ltd"},
    {"key":"05","name":"Others"}
  ];
  

  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: CompanyServiceV1
  ) {
  }

  ngOnInit() {
   this.service.companyEvent.subscribe(latestValue =>{ 
     this.company =  latestValue;
    });
    
   
    if(this.route.params['_value']['_id']){
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
            .switchMap((params: Params) => this.service.getCompanyById(params['_id']))
            .subscribe((_company: Company) => {
                  this.service.setCompany(_company);
                  //sessionStorage.setItem('company', JSON.stringify(this.company));
            });
    }

  }

  onChange(val){
    this.service.setCompany(this.company);
  }
  saveCompany(){
    //save can be add or update
    console.log(JSON.stringify(this.company));
    if(this.company.id!=''){
      this.updateCompany();
    }
    else{
      this.addCompany();
    }
  }

  addCompany(){
    
    this.company.isBuyer="Y";
    this.company.isSeller="N";
    console.log("this.company");
    console.log(this.company);
    this.service.addCompany(this.company).subscribe(
      result => { 
               // this.company.id = result;
               // console.log('Company(id:'+result+') added');
                this.listCompanies();
      },
      error => {
         console.error('Error adding company!');
         console.error(error);
      }
      );
  }
  
  updateCompany(){

    this.service.updateCompany(this.company).subscribe(
      result => { 
               // console.log('Company(id:'+result+') updated');
                this.listCompanies();
      },
      error => {
         console.error('Error updating company!');
         console.error(error);
      }
      );
  }
  listCompanies() {
      this.router.navigateByUrl('/buyer-company/company-list');
      // to-do
      // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle
      
	  }


}
