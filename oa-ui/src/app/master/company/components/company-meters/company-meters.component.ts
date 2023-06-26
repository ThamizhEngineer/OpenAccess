import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CompanyServiceV1 } from './../../services/company.service.v1';
import { Company, Meter } from './../../../vo/company.v1';
import { CommonService } from '../../../../shared/common/common.service';

@Component({
  selector: 'company-meters',
  templateUrl: './company-meters.component.html',
  // styleUrls: [],
  providers: []

})
export class CompanyMetersComponent implements OnInit {

  company: Company = new Company();

  meter: Meter = new Meter();
  meterRowIndex: number;
  services = [];
  metermakes = [];
  accuracyclasscodes = [];

  abtMeterValues = [
    { "key": "Y", "name": "Y" },
    { "key": "N", "name": "N" }
  ]


  constructor(private companyService: CompanyServiceV1, private commonService: CommonService) {
    if (!this.company.meters) {
      this.company.meters = [];
    }

   this.companyService.companyEvent.subscribe(latestValue =>{ 
      this.company = latestValue;
      this.initMeters();
      this.fetchServices();
    });

    this.fetchMeterMake();
    this.fetchAccuracyClassCode();
    


  }

  ngOnInit() {
   
  }

  initMeters() {
    if (!this.company.meters) {
      this.company.meters = [];
    }

    this.meter = new Meter();
    this.meterRowIndex = -1;
  }


  fetchServices() {
    if (!this.company.services) {

    } else {
      this.services = this.company.services;
      console.log("In fetch services");
      console.log(this.services);
    }

  }


  fetchAccuracyClassCode() {
    this.commonService.fetchCodes('ACCURACY_CLASS_TYPE_CODE').subscribe(
      result => {
        this.accuracyclasscodes = result;
        console.log(this.accuracyclasscodes)
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


  addMeter() {
    if (!this.company.meters) {
      this.company.meters = [];
    }
    this.company.meters.push(Object.assign({}, this.meter));
    this.companyService.setCompany(this.company);
    this.initMeters();
  }


  updateMeter() {
    //this.company.shareHolders[this.shareHolderRowIndex] = Object.assign({},this.shareHolder);

    var tempArray = [];
    for (var index = 0; index < this.company.meters.length; index++) {
      if (this.meterRowIndex == index) {
        tempArray.push(Object.assign({}, this.meter));
      }
      else {
        tempArray.push(this.company.meters[index]);
      }

    }
    this.company.meters = tempArray;
    this.companyService.setCompany(this.company);
    this.initMeters();
  }

  editMeter(_index: number) {
    this.meterRowIndex = _index;
    this.meter = Object.assign({}, this.company.meters[_index]);
  }

  deleteMeter(_index: number) {
    this.company.meters.splice(_index, 1);
  }



}