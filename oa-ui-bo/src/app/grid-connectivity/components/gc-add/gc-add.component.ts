import 'rxjs/add/operator/startWith';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormControl } from '@angular/forms';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';

//import { GcEvent } from './../../services/gc.event';  
import { GcService } from './../../services/gc.service';
import { Gc, DocCheckListItem } from './../../services/gc';
import { CommonService } from './../../../shared/common/common.service';
import { CompanyService } from './../../../master/company/services/company.service';


@Component({
  selector: 'gc-list',
  templateUrl: './gc-add.component.html',
  styleUrls: ['./gc-add.component.scss']
})
export class GcAddComponent implements OnInit {

  gc: Gc;

  generatingTypeNames = [
    { "key": "Generating Plant", "name": "Generating Plant", "isCaptive": "N" },
    { "key": "Captive Generating plant", "name": "Captive Generating plant", "isCaptive": "Y" },
    { "key": "Co-Generating Plant", "name": "Co-Generating Plant", "isCaptive": "N" },
    { "key": "Captive Co-Generating Plant", "name": "Captive Co-Generating Plant", "isCaptive": "Y" }];

  genPlanTypes = [
    { "key": "Fossil Fuel", "name": "Fossil Fuel" }
    //  { "key": "Non Conventional", "name": "Non Conventional" },
    //  { "key": "Hydro Electricity", "name": "Hydro Electricity" }
  ];

  fuelTypeList = [
   
   { "key": "02", "name": "Wind" },
   // { "key": "Solar", "name": "Solar" },
   // { "key": "Biomass", "name": "Biomass" },
   // { "key": "Bagasse", "name": "Bagasse" },
    {"key": "01", "name": "Coal" },
   { "key": "03", "name": "Bio-Gas" },
   { "key": "04", "name": "NaturalGas" },
   { "key": "05", "name": "Waste Heat " },
   { "key": "06", "name": "Lignite " },
   { "key": "07", "name": "Diesel" },
   { "key": "08", "name": "Furnance Oil (FO)" },
   { "key": "09", "name": "Naptha" },
   { "key": "10", "name": "Propane" },
   { "key": "11", "name": "Butane" },
   { "key": "12", "name": "Low Sulphur Heavy Stock(LSHS)" },
   { "key": "13", "name": "Light Diesel Oil (LDO) " },
   { "key": "14", "name": "Superior Kerosene Oil (SKO) " },
   { "key": "15", "name": "High Speed Diesel (HSD)" },
   { "key": "16", "name": "Re-gasified Liquefied Natural Gas (R-LNG))" },
   { "key": "17", "name": "Liquefied Petroleum Gas (LPG)" },
  
  ];
  onGeneratingTypeSelect(val){
  

    this.gc.generatingTypeName =this.generatingTypeNames.filter(item=>item.name == val)[0].name;
    this.gc.generatingTypeIsCaptive =this.generatingTypeNames.filter(item=>item.name == val)[0].isCaptive;
  
  }
  onSelect(val){
console.log(val)
  }
  companyNames = [];
  checkList = [];
  checkListItems: Array<DocCheckListItem> = [];

  reactiveCompanyNames: any;
  companyNameCtrl: FormControl;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public dialogRef: MatDialogRef<GcAddComponent>,
    private service: GcService,
    private commonService: CommonService,
    private companyService: CompanyService
  ) {

    // this.gc = new GcEvent().setEmpty();

    this.companyNameCtrl = new FormControl({ key: '', name: '' });
    this.companyService.getAllCompanySummaries().subscribe(_companies => {
      this.companyNames = _companies;
      this.companyNameCtrl = new FormControl({ key: '', name: '' });
      this.reactiveCompanyNames = this.companyNameCtrl.valueChanges
        .startWith(this.companyNameCtrl.value)
        .map(val => this.displayFn(val))
        .map(name => this.filterCompanyName(name));
    });
  }

  ngOnInit() {
    this.gc = new Gc();

    this.fetchCheckList();
  }

  // filterFuelType(val: string) {

  //   return val ? this.fuelTypeList.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.fuelTypeList;
  // }

  // filterPlantType(val: string) {

  //   return val ? this.genPlanTypes.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.genPlanTypes;
  // }

  // filterGeneratingType(val: string) {

  //   return val ? this.generatingTypeNames.filter((s) => s.name.match(new RegExp(val, 'gi'))) : this.generatingTypeNames;
  // }
  displayFn(value: any): string {
    return value && typeof value === 'object' ? value.companyName : value;
  }

  filterCompanyName(val: string) {
    return val ? this.companyNames.filter((company) => company.companyName.match(new RegExp(val, 'gi'))) : this.companyNames;
  }
	
	fetchCheckList(){
		this.commonService.fetchCodes().subscribe(
		result => {
			this.checkList = result.filter(x=>x.listCode == "CHECK_LIST_CODE");
		})
	}
  
  assignCheckList() {
    let id = 1;
    this.checkList.forEach(element => {
      let listItem = new DocCheckListItem();
      listItem.id = id.toString();
      listItem.documentCode = element.valueCode;
      listItem.documentDesc = element.valueDesc;
      listItem.docSubmitted = "N";

      this.checkListItems.push(listItem);
      id++;
    });
    console.log(this.checkListItems)
    this.gc.checkList = this.checkListItems;


  }


  addGc() {
    // this.gc.header.status = 'Initiated';
    this.assignCheckList();

    let _company = this.companyNameCtrl.value;
    // this.gc.header.companyCode = _company.companyId;
    // this.gc.header.companyName = _company.companyName;
    console.log(this.gc)

    this.service.addGc(this.gc).subscribe(
      result => {
       console.log(result)
        this.dialogRef.close(result);
       
      },
      error => {
        console.error('Error adding Grid Connectivity!');
        console.error(error);
      }
    );
  }
}
