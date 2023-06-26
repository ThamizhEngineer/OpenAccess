import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { Company, Service } from './../../../vo/company.v1';
import { CommonService } from '../../../../shared/common/common.service';
import { CompanyServiceV1 } from './../../services/company.service.v1'; 

@Component({
  selector: 'company-services',
  templateUrl: './company-services.component.html',
  //styleUrls: [],
  providers: []

})
export class CompanyServicesComponent implements OnInit {

  company: Company = new Company();

  service: Service = new Service();
  serviceRowIndex: number;
  orgList = []
  filteredOrgList = []
  serviceTypes = [];
  fuelTypes = [];
  injectingVoltages = [];

  constructor(private commonService: CommonService, private companyService: CompanyServiceV1) {

   this.companyService.companyEvent.subscribe(latestValue =>{ 
     this.company =  latestValue;
    this.initServices();
    });
    this.fetchServiceType();
    this.fetchOrgList();

  }

  ngOnInit() {
    this.fetchVoltages();
    this.fetchFuelTypes();

  }


  displayFn(value: any): string {
    return value && typeof value === 'object' ? value.name : value;
  }

  filterOrgList(val: string) {
    return val ? this.orgList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.orgList;
  }

  fetchServiceType() {
    this.commonService.fetchCodes('SERVICE_TYPE_CODE').subscribe(
      result => {
      this.serviceTypes = result;
      },

      error => {
        console.error('Error loading service typr');
        console.error(error);
      });
  }  

  initServices() {
    if (!this.company.services) {
      this.company.services = [];
    }
    this.service = new Service();
    this.serviceRowIndex = -1;
  }

  fetchOrgList() {
    this.commonService.fetchEDCs().subscribe(
      result => {
        this.orgList = result;
      },
      error => {
        console.error('Error loading states!');
        console.error(error);
      }
    );
  }

  fetchVoltages(){
    this.commonService.fetchCodes('VOLTAGE_CODE').subscribe(
    result => { this.injectingVoltages= result;
    },
    
    error => {
    console.error('Error loading Voltages');
    console.error(error);
    });
   } 

   fetchFuelTypes(){
    this.commonService.fetchCodes('FUEL_TYPE_CODE').subscribe(
    result => { this.fuelTypes= result;
    },
    
    error => {
    console.error('Error loading Fuel');
    console.error(error);
    });
   }

  addService() {
   // this.service.orgCode = this.service.orgName.split("-")[0].trim();
   this.service.typeCode="03";
    if (!this.company.services) {
      this.company.services = [];
    }
    this.company.services.push(Object.assign({}, this.service));
    this.companyService.setCompany(this.company);
    this.initServices();
  }

  updateService() {
    this.service.orgCode = this.service.orgName.split("-")[0].trim();
    var tempArray = [];
    for (var index = 0; index < this.company.services.length; index++) {
      if (this.serviceRowIndex == index) {
        tempArray.push(Object.assign({}, this.service));
      }
      else {
        tempArray.push(this.company.services[index]);
      }

    }
    this.company.services = tempArray;
    this.companyService.setCompany(this.company);
    this.initServices();
  }


  editService(_index: number) {
    this.serviceRowIndex = _index;
    this.service = Object.assign({}, this.company.services[_index]);
  }

  deleteService(_index: number) {
    this.company.services.splice(_index, 1);
  }
}
