import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CompanyServiceV1 } from './../../services/company.service.v1';
import { Company, ShareHolder } from './../../../vo/company.v1';

@Component({
  selector: 'company-share-holders',
  templateUrl: './company-share-holders.component.html'
  // styleUrls: 

})
export class CompanyShareHoldersComponent implements OnInit {

  company: Company = new Company();

  shareHolder: ShareHolder = new ShareHolder();
  shareHolderRowIndex: number;
  companies = [];
  filteredCompanies = [];


  constructor(private companyService: CompanyServiceV1) {


    console.log("sh constructor")
    this.companyService.companyEvent.subscribe(latestValue => {

    console.log("sh latest vale")
    console.log(latestValue)
      this.company = latestValue;
      this.initShareHolders();
    });
    this.fetchCompanies();
  }

  ngOnInit() {

  }

  initShareHolders() {
    if (!this.company.shareHolders) {
      this.company.shareHolders = [];
    }
    this.shareHolder = new ShareHolder();
    //  this.shareHolder = Object.assign({},{id: "", share: "",companyId: "",companyCode: "",companyName: "", 
    //                   shareHolderCompanyId: "", shareHolderCompanyCode: "",shareHolderCompanyName: "",measure: "PERCENT", enabled: "Y"});
    this.shareHolderRowIndex = -1;
  }

  filterCompanies(val: string) {

    return val ? this.companies.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.companies;
  }

  fetchCompanies() {

    this.companyService.searchCompanies("","","","").subscribe(
      result => {
        this.companies = result;
        console.log(result)
      },
      error => {
        console.error('Error loading companies');
        console.error(error);
      });



  }

  addShareHolder() {
    if (!this.company.shareHolders) {
      this.company.shareHolders = [];
    }
    this.company.shareHolders.push(Object.assign({}, this.shareHolder));
    this.companyService.setCompany(this.company);
    this.initShareHolders();
  }


  updateShareHolder() {
    //this.company.shareHolders[this.shareHolderRowIndex] = Object.assign({},this.shareHolder);

    var tempArray = [];
    for (var index = 0; index < this.company.shareHolders.length; index++) {
      if (this.shareHolderRowIndex == index) {
        tempArray.push(Object.assign({}, this.shareHolder));
      }
      else {
        tempArray.push(this.company.shareHolders[index]);
      }

    }
    this.company.shareHolders = tempArray;
    this.companyService.setCompany(this.company);
    this.initShareHolders();
  }

  editShareHolder(_index: number) {
    this.shareHolderRowIndex = _index;
    this.shareHolder = Object.assign({}, this.company.shareHolders[_index]);
  }

  deleteShareHolder(_index: number) {
    this.company.shareHolders.splice(_index, 1);
  }



}
