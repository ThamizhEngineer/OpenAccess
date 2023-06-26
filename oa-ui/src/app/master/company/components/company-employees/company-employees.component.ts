import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CompanyServiceV1 } from './../../services/company.service.v1'; 
import { Company , Employee } from './../../../vo/company.v1'; 

@Component({
  selector: 'company-employees',
  templateUrl: './company-employees.component.html',
 // styleUrls: [],
  providers: []
  
})
export class CompanyEmployeesComponent implements OnInit{ 

  company: Company = new Company();
 
  employee: Employee = new Employee();
  employeeRowIndex: number;

  
  constructor(private companyService: CompanyServiceV1) { 
   this.companyService.companyEvent.subscribe(latestValue =>{ 
     this.company =  latestValue;
     this.initEmployees();
    });
   
  }

  ngOnInit() {
  }


initEmployees(){
    if(!this.company.employees){
        this.company.employees=[];
      }
   this.employee = Object.assign({},{ employeeNo : "",designation : "",name : "", landline : "",
            mobile : "", email: "",fax : "", line1 : "", city : "", district : "",
            state : "", pinCode: "", enabled: true});
   this.employeeRowIndex = -1;
  }



  addEmployee(){
    if(!this.company.employees){
      this.company.employees=[];
    }
    this.company.employees.push(Object.assign({}, this.employee));
    this.companyService.setCompany(this.company);
    this.initEmployees();
  }


  updateEmployee(){
    //this.company.employees[this.employeeRowIndex] = Object.assign({},this.employee);

    var tempArray =[];
    for (var index = 0; index < this.company.employees.length; index++) {
      if(this.employeeRowIndex == index){
        tempArray.push(Object.assign({},this.employee));
      }
      else{
        tempArray.push(this.company.employees[index]);
      }
      
    }
    this.company.employees = tempArray;
    this.companyService.setCompany(this.company);
    this.initEmployees();
  }


  editEmployee(_index: number){
   this.employeeRowIndex =_index; 
   this.employee = Object.assign({},this.company.employees[_index]);
  }

  deleteEmployee(_index: number){ 
   this.company.employees.splice(_index,1);
  }


}
